package dev.cheesegr8er.toastermod.init;

import dev.cheesegr8er.toastermod.ToasterMod;
import dev.cheesegr8er.toastermod.block.BlockToaster;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.Item.Properties;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.ObjectHolder;

@Mod.EventBusSubscriber(modid = ToasterMod.MOD_ID, bus = Bus.MOD)
@ObjectHolder(ToasterMod.MOD_ID)
public class ModBlocks {
	
	// Fields to hold blocks
	public static final BlockToaster toaster = null;
	
	// Event to register all blocks
	@SubscribeEvent
	public static void registerBlocks(RegistryEvent.Register<Block> event) {
		final IForgeRegistry<Block> registry = event.getRegistry();

		final Block[] blocks = {
			new BlockToaster().setRegistryName(new ResourceLocation(ToasterMod.MOD_ID+":toaster")),
		};

		for (final Block block : blocks) {
			registry.register(block);
		}
	}
	
	// Event to register all blockitems
	@SubscribeEvent
	public static void registerItemBlocks(RegistryEvent.Register<Item> event) {
		final IForgeRegistry<Item> registry = event.getRegistry();
		
		final BlockItem[] blockItems = {
			new BlockItem(toaster, new Properties().maxStackSize(1).group(ToasterMod.TOASTER_MOD_TAB)),
		};

		for (final BlockItem itemBlock : blockItems) {
			registry.register(itemBlock.setRegistryName(itemBlock.getBlock().getRegistryName()));
		}
	}
}
