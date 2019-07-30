package dev.cheesegr8er.toastermod;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import dev.cheesegr8er.toastermod.proxy.ClientProxy;
import dev.cheesegr8er.toastermod.proxy.IProxy;
import dev.cheesegr8er.toastermod.proxy.ModSetup;
import dev.cheesegr8er.toastermod.proxy.ServerProxy;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(ToasterMod.MOD_ID)
public class ToasterMod
{
	public static final String MOD_ID = "toastermod";
	public static final String NAME = "Toaster Mod";
	
	public static IProxy proxy = DistExecutor.runForDist(() -> () -> new ClientProxy(), () -> () -> new ServerProxy());
    public static ModSetup setup = new ModSetup();
    // Directly reference a log4j logger.
	public static final Logger LOGGER = LogManager.getLogger();
    
    public ToasterMod() {
        // Register the setup method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
    }
    
    private void setup(final FMLCommonSetupEvent event) {
    	setup.init();
        proxy.init();
    }
    
}
