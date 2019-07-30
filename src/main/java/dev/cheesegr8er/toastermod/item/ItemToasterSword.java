package dev.cheesegr8er.toastermod.item;

import dev.cheesegr8er.toastermod.ToasterMod;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.LightningBoltEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTier;
import net.minecraft.item.SwordItem;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;

public class ItemToasterSword extends SwordItem{

	public static final int attack_damage = 7;
	public static final float attack_speed = 1.6f;

	public ItemToasterSword() {
		super(ItemTier.IRON, attack_damage, attack_speed, new Properties().group(ToasterMod.setup.itemGroup));
	}

	/**
	 * Casts a bolt of lightning if an entity is hit with this weapon.
	 */
	@Override
	public boolean hitEntity(ItemStack stack, LivingEntity target, LivingEntity attacker) {
		ToasterMod.LOGGER.info("Attempting to summon lightning bolt.");
		LightningBoltEntity lightning = new LightningBoltEntity(target.world, target.posX, target.posY, target.posZ, false);

		target.world.playSound(null, target.posX, target.posY, target.posZ, SoundEvents.BLOCK_ANVIL_BREAK, SoundCategory.BLOCKS, 1.0f, 1.0f);
		super.hitEntity(stack, target, attacker);
		target.getServer().getWorld(target.dimension).addLightningBolt(lightning);
		return true; 
	}
}
