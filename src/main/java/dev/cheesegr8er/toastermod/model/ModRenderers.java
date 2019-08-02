package dev.cheesegr8er.toastermod.model;

import dev.cheesegr8er.toastermod.ToasterMod;
import dev.cheesegr8er.toastermod.entities.EntityToasterProjectile;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = ToasterMod.MOD_ID, value = Dist.CLIENT, bus = Bus.MOD)
public class ModRenderers {
	@SubscribeEvent
	public static void registerRenderers(final FMLClientSetupEvent event) {
		RenderingRegistry.registerEntityRenderingHandler(EntityToasterProjectile.class, new RenderToasterProjectile.RenderFactory());
		
		System.out.println("Renderers registered!");
	}
}