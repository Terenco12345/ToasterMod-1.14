package dev.cheesegr8er.toastermod.tabs;

import dev.cheesegr8er.toastermod.ToasterMod;
import dev.cheesegr8er.toastermod.init.ModBlocks;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class ToasterModTab extends ItemGroup{

	public ToasterModTab() {
		super(ToasterMod.MOD_ID);
	}

	@Override
	public ItemStack createIcon() {
		return new ItemStack(ModBlocks.toaster.asItem());
	}	
}
