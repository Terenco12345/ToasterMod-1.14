package dev.cheesegr8er.toastermod.item;

import dev.cheesegr8er.toastermod.ToasterMod;
import net.minecraft.item.Food;
import net.minecraft.item.Item;

public class ItemToastSlice extends Item{
	
	public ItemToastSlice() {
		super(new Properties().group(ToasterMod.setup.itemGroup).
				food(new Food.Builder().
				fastToEat().
				hunger(6).
				saturation(6).
				build()));
	}
}
