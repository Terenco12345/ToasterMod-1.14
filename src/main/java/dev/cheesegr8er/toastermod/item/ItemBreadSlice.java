package dev.cheesegr8er.toastermod.item;

import dev.cheesegr8er.toastermod.ToasterMod;
import net.minecraft.item.Food;
import net.minecraft.item.Item;

public class ItemBreadSlice extends Item{

	public ItemBreadSlice() {
		super(new Properties().group(ToasterMod.TOASTER_MOD_TAB).
				food(new Food.Builder().
				fastToEat().
				hunger(3).
				saturation(3).
				build()));
	}
}
