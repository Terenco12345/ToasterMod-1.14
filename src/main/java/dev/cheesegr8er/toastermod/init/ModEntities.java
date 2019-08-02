package dev.cheesegr8er.toastermod.init;

import dev.cheesegr8er.toastermod.ToasterMod;
import dev.cheesegr8er.toastermod.entities.EntityToasterProjectile;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.registries.ObjectHolder;

@ObjectHolder(ToasterMod.MOD_ID)
@Mod.EventBusSubscriber(modid = ToasterMod.MOD_ID, value = Dist.CLIENT, bus = Bus.MOD)
public class ModEntities{

	@ObjectHolder(ToasterMod.MOD_ID + ":toaster_projectile")
	public static final EntityType<EntityToasterProjectile> toaster_projectile = null;

	@SubscribeEvent
	public static void registerEntities(final RegistryEvent.Register<EntityType<?>> event) {
		event.getRegistry().register(		    	
				EntityType.Builder.<EntityToasterProjectile>create(EntityToasterProjectile::new,EntityClassification.MISC)
				.setTrackingRange(80)
				.setShouldReceiveVelocityUpdates(true)
				.setUpdateInterval(60)
				.size(1f, 1f)
				.setCustomClientFactory((spawnEntity, world) -> new EntityToasterProjectile(world))
				.build(ToasterMod.MOD_ID+":toaster_projectile").setRegistryName(ToasterMod.MOD_ID+":toaster_projectile")
				);
		System.out.println("Entities registered!");
	}
}
