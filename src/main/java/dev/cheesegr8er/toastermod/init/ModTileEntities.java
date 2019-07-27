package dev.cheesegr8er.toastermod.init;

import dev.cheesegr8er.toastermod.ToasterMod;
import dev.cheesegr8er.toastermod.tileentities.TileEntityToaster;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.registries.ObjectHolder;

@Mod.EventBusSubscriber(modid = ToasterMod.MOD_ID, bus = Bus.MOD)
public class ModTileEntities {
	
	// Fields to hold Tile Entities
	@ObjectHolder(ToasterMod.MOD_ID+":toaster_tile_entity")
	public static final TileEntityType<TileEntityToaster> toaster_tile_entity = null;
	
	// Event to register all items
	@SubscribeEvent
	public static void registerTileEntities(RegistryEvent.Register<TileEntityType<?>> event) {
		// Register toaster tile entity
		event.getRegistry().register(TileEntityType.Builder
				.create(TileEntityToaster::new, ModBlocks.toaster).build(null)
				.setRegistryName(new ResourceLocation(ToasterMod.MOD_ID+":toaster_tile_entity")));
	}
}
