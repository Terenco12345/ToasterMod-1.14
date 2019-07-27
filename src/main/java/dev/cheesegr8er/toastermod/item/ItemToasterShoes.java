package dev.cheesegr8er.toastermod.item;

import javax.annotation.Nullable;

import dev.cheesegr8er.toastermod.ToasterMod;
import dev.cheesegr8er.toastermod.init.ModModels;
import dev.cheesegr8er.toastermod.model.ModelToasterShoes;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.extensions.IForgeItem;

public class ItemToasterShoes extends ArmorItem implements IForgeItem{

	public ItemToasterShoes() {
		super(ArmorMaterial.IRON, EquipmentSlotType.FEET, new Properties().group(ToasterMod.TOASTER_MOD_TAB));
	}

	@Override
    public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlotType slot, String type) {
        return "minecraft:textures/block/iron_block.png";
    }
	
	@OnlyIn(Dist.CLIENT)
    @Nullable
    @Override
    public <A extends BipedModel<?>> A getArmorModel(LivingEntity entityLiving, ItemStack itemStack, EquipmentSlotType armorSlot, A defaultModel)
    {
		
        defaultModel.bipedLeftLeg = ModModels.toaster_shoes_model_left.group;
        defaultModel.bipedRightLeg = ModModels.toaster_shoes_model_right.group;
        
		return defaultModel;
        
    }
}
