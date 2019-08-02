package dev.cheesegr8er.toastermod.item;

import java.util.function.BiFunction;

import dev.cheesegr8er.toastermod.ToasterMod;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.item.ArrowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class ItemToasterArrow extends ArrowItem{

	private final BiFunction<World, LivingEntity, ArrowEntity> entityFactory;
	
	public ItemToasterArrow(final BiFunction<World, LivingEntity, ArrowEntity> entityFactory) {
		super(new Properties().group(ToasterMod.setup.itemGroup));
		this.entityFactory = entityFactory;
	}
	
	@Override
	public AbstractArrowEntity createArrow(World worldIn, ItemStack stack, LivingEntity shooter) {
		final ArrowEntity toasterArrow = entityFactory.apply(worldIn, shooter);
		System.out.println("Toaster arrow entity added into the world.");
		return toasterArrow;
	}
}
