package dev.cheesegr8er.toastermod.proxy;

import dev.cheesegr8er.toastermod.init.ModBlocks;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class ModSetup {

    public ItemGroup itemGroup = new ItemGroup("toaster_mod") {
        @Override
        public ItemStack createIcon() {
            return new ItemStack(ModBlocks.toaster);
        }
    };

    public void init() {

    }
}