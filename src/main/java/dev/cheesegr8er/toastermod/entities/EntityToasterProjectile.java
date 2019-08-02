package dev.cheesegr8er.toastermod.entities;

import dev.cheesegr8er.toastermod.init.ModEntities;
import dev.cheesegr8er.toastermod.init.ModItems;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.network.IPacket;
import net.minecraft.network.play.server.SSpawnObjectPacket;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.network.NetworkHooks;

public class EntityToasterProjectile extends ArrowEntity{

	public EntityToasterProjectile(EntityType<? extends ArrowEntity> entityType, final World worldIn) {
		super((EntityType<? extends ArrowEntity>) ModEntities.toaster_projectile, worldIn);
	}

	public EntityToasterProjectile(World worldIn, LivingEntity livingEntity) {
		super(worldIn, livingEntity);
	}

	public EntityToasterProjectile(World worldIn) {
		super((EntityType<? extends ArrowEntity>) ModEntities.toaster_projectile, worldIn);
	}
	
	@Override
	@OnlyIn(Dist.CLIENT)
	public boolean isInRangeToRenderDist(double distance) {
		return true;
	}

	@Override
	public EntityType<?> getType() {
		return ModEntities.toaster_projectile;
	}

	@Override
	public void setPotionEffect(final ItemStack stack) {
		super.setPotionEffect(new ItemStack(ModItems.toaster_arrow)); // Mod arrows can't have potion effects
	}

	@Override
	protected ItemStack getArrowStack() {
		return new ItemStack(ModItems.toaster_arrow);
	}

	@Override
	public IPacket<?> createSpawnPacket() {
		return NetworkHooks.getEntitySpawningPacket(this);
	}
}
