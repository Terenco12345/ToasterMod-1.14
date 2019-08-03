package dev.cheesegr8er.toastermod.item;

import java.util.function.Predicate;

import dev.cheesegr8er.toastermod.ToasterMod;
import dev.cheesegr8er.toastermod.entities.EntityToasterProjectile;
import dev.cheesegr8er.toastermod.init.ModItems;
import dev.cheesegr8er.toastermod.init.ModSounds;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ArrowItem;
import net.minecraft.item.BowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.stats.Stats;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;

public class ItemToasterLauncher extends BowItem{

	public static final Predicate<ItemStack> TOASTERS = (itemStack) -> {
		return itemStack.getItem() == ModItems.toaster_arrow;
	};

	public ItemToasterLauncher() {
		super(new Properties().group(ToasterMod.setup.itemGroup));
	}

	@Override
	public Predicate<ItemStack> getAmmoPredicate() {
		return TOASTERS;
	}

	@Override
	public Predicate<ItemStack> getInventoryAmmoPredicate() {
		return TOASTERS;
	}

	/**
	 * Called when the player stops using an Item (stops holding the right mouse button).
	 */
	@Override
	public void onPlayerStoppedUsing(ItemStack stack, World worldIn, LivingEntity entityLiving, int timeLeft) {
		if (entityLiving instanceof PlayerEntity) {
			PlayerEntity playerentity = (PlayerEntity)entityLiving;
			boolean flag = playerentity.abilities.isCreativeMode || EnchantmentHelper.getEnchantmentLevel(Enchantments.INFINITY, stack) > 0;
			ItemStack itemstack = playerentity.findAmmo(stack);

			int i = this.getUseDuration(stack) - timeLeft;
			i = net.minecraftforge.event.ForgeEventFactory.onArrowLoose(stack, worldIn, playerentity, i, !itemstack.isEmpty() || flag);
			if (i < 0) return;

			if (!itemstack.isEmpty() || flag) {
				if (itemstack.isEmpty()) {
					itemstack = new ItemStack(ModItems.toaster_arrow);
				}

				float f = getArrowVelocity(i);
				if (!((double)f < 0.1D)) {
					boolean flag1 = playerentity.abilities.isCreativeMode || (itemstack.getItem() instanceof ArrowItem && ((ArrowItem)itemstack.getItem()).isInfinite(itemstack, stack, playerentity));
					if (!worldIn.isRemote) {
						ItemToasterArrow arrowitem = (ItemToasterArrow)(itemstack.getItem() instanceof ItemToasterArrow ? itemstack.getItem() : ModItems.toaster_arrow);
						EntityToasterProjectile toasterBombEntity = (EntityToasterProjectile) arrowitem.createArrow(worldIn, itemstack, playerentity);
						toasterBombEntity = (EntityToasterProjectile) customeArrow(toasterBombEntity);
						toasterBombEntity.shoot(playerentity, playerentity.rotationPitch, playerentity.rotationYaw, 0.0F, f * 3.0F, 1.0F);
						if (f == 1.0F) {
							toasterBombEntity.setIsCritical(true);
						}

						int j = EnchantmentHelper.getEnchantmentLevel(Enchantments.POWER, stack);
						if (j > 0) {
							toasterBombEntity.setDamage(toasterBombEntity.getDamage() + (double)j * 0.5D + 0.5D);
						}

						int k = EnchantmentHelper.getEnchantmentLevel(Enchantments.PUNCH, stack);
						if (k > 0) {
							toasterBombEntity.setKnockbackStrength(k);
						}

						if (EnchantmentHelper.getEnchantmentLevel(Enchantments.FLAME, stack) > 0) {
							toasterBombEntity.setFire(100);
						}

						stack.damageItem(1, playerentity, (p_220009_1_) -> {
							p_220009_1_.sendBreakAnimation(playerentity.getActiveHand());
						});
						if (flag1 || playerentity.abilities.isCreativeMode && (itemstack.getItem() == Items.SPECTRAL_ARROW || itemstack.getItem() == Items.TIPPED_ARROW)) {
							toasterBombEntity.pickupStatus = toasterBombEntity.pickupStatus.CREATIVE_ONLY;
						}

						toasterBombEntity.addToBombList();
						worldIn.addEntity(toasterBombEntity);
					}

					worldIn.playSound(null, playerentity.posX, playerentity.posY, playerentity.posZ, ModSounds.toaster_down, SoundCategory.BLOCKS, 1.0f, 1.0f);
					if (!flag1 && !playerentity.abilities.isCreativeMode) {
						itemstack.shrink(1);
						if (itemstack.isEmpty()) {
							playerentity.inventory.deleteStack(itemstack);
						}
					}

					playerentity.addStat(Stats.ITEM_USED.get(this));
				}
			}
		}
	}
}
