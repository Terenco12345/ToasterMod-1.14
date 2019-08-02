package dev.cheesegr8er.toastermod.tick;

import dev.cheesegr8er.toastermod.ToasterMod;
import net.minecraftforge.event.entity.EntityEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@Mod.EventBusSubscriber(modid = ToasterMod.MOD_ID, bus = Bus.FORGE)
public class DebugLogic {

	@SubscribeEvent
	public static void OnEntityCreation(EntityEvent e) {
		if(e.getEntity() != null) {
			if(e.getEntity().getEntityString() != null) {
				// System.out.println(e.getEntity().getEntityString() + " event ticked!");
			}
		}
	}
}
