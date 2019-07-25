package dev.cheesegr8er.toastermod.init;

import dev.cheesegr8er.toastermod.ToasterMod;
import dev.cheesegr8er.toastermod.item.ItemBreadSlice;
import dev.cheesegr8er.toastermod.item.ItemToastSlice;
import dev.cheesegr8er.toastermod.item.ItemToasterSword;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.ObjectHolder;

@Mod.EventBusSubscriber(modid = ToasterMod.MOD_ID, bus = Bus.MOD)
@ObjectHolder(ToasterMod.MOD_ID)
public class ModItems {
	
	// Fields to hold items
	public static final ItemBreadSlice bread_slice = null;
	public static final ItemToastSlice toast_slice = null;
	public static final ItemToasterSword toaster_sword = null;
	
	// Event to register all items
	@SubscribeEvent
	public static void registerItems(final RegistryEvent.Register<Item> event) {
		final IForgeRegistry<Item> registry = event.getRegistry();

		final Item[] items = {
			new ItemBreadSlice().setRegistryName(new ResourceLocation(ToasterMod.MOD_ID+":bread_slice")),
			new ItemToastSlice().setRegistryName(new ResourceLocation(ToasterMod.MOD_ID+":toast_slice")),
			new ItemToasterSword().setRegistryName(new ResourceLocation(ToasterMod.MOD_ID+":toaster_sword"))
		};

		for (final Item item : items) {
			registry.register(item);
		}
	}
}
