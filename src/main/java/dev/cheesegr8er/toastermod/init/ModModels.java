package dev.cheesegr8er.toastermod.init;

import dev.cheesegr8er.toastermod.ToasterMod;
import dev.cheesegr8er.toastermod.model.ModelToasterShoes;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.registries.ObjectHolder;

@Mod.EventBusSubscriber(modid = ToasterMod.MOD_ID, bus = Bus.MOD)
public class ModModels {

	public static final ModelToasterShoes toaster_shoes_model_right = new ModelToasterShoes(6.0f, 5.0f, 0.0f);
	public static final ModelToasterShoes toaster_shoes_model_left = new ModelToasterShoes(10.0f, 5.0f, 0.0f);
	
}
