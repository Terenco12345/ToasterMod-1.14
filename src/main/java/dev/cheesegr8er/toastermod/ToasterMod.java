package dev.cheesegr8er.toastermod;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import dev.cheesegr8er.toastermod.tabs.ToasterModTab;
import net.minecraftforge.fml.common.Mod;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(ToasterMod.MOD_ID)
public class ToasterMod
{
	public static final String MOD_ID = "toastermod";
	public static final String NAME = "Toaster Mod";
	
    // Directly reference a log4j logger.
	public static final Logger LOGGER = LogManager.getLogger();
    
    public static final ToasterModTab TOASTER_MOD_TAB = new ToasterModTab();
    
    public ToasterMod() {
    	LOGGER.info("ToasterMod toasts their way in!");
    }
    
}
