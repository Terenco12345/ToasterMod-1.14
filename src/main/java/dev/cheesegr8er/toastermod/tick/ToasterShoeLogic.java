package dev.cheesegr8er.toastermod.tick;

import java.util.Random;

import org.lwjgl.glfw.GLFW;

import dev.cheesegr8er.toastermod.ToasterMod;
import dev.cheesegr8er.toastermod.init.ModItems;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.world.World;
import net.minecraftforge.client.event.InputEvent.KeyInputEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.common.gameevent.TickEvent.PlayerTickEvent;

@Mod.EventBusSubscriber(modid = ToasterMod.MOD_ID, bus = Bus.FORGE)
public class ToasterShoeLogic {
	
	private static boolean toasterShoesEquipped = false;
	private static boolean spacePressed = false;
	private static final int JUMP_COOLDOWN_DEFAULT = 5;
	private static int jumpCooldown = 2;
	
	@SubscribeEvent
	public static void onPlayerTick(PlayerTickEvent event) {
		Random random = new Random();
		
		World world = event.player.world;
		PlayerEntity player = event.player;
		toasterShoesEquipped = false;
		
		// Check if toaster shoes are equipped
		for(ItemStack item: player.getEquipmentAndArmor()) {
			if(item.getItem().getRegistryName().toString().equals(ToasterMod.MOD_ID+":toaster_shoes")) {
				toasterShoesEquipped = true;
			}
		}
		
		// Applying jump boost
		if(toasterShoesEquipped) {
			player.addPotionEffect(new EffectInstance(Effects.JUMP_BOOST, 5, 1, false, false, false));
		} else {
			player.clearActivePotions();
		}
		
		// Jump logic
		if(toasterShoesEquipped && spacePressed && jumpCooldown <= 0) {
			player.jump();
			player.fallDistance = 0;
			
			// Spawn four slices of toast upon jumping
			for(int i = 0; i < 4; i++) {
				player.world.addEntity(new ItemEntity(world, 
						(double)player.posX+(random.nextFloat()-0.5f), 
						(double)player.posY-1.0, 
						(double)player.posZ+(random.nextFloat()-0.5f), 
						new ItemStack(ModItems.toast_slice,1)));
			}
			
			jumpCooldown = JUMP_COOLDOWN_DEFAULT;
		}
		
		// Cooldown decrementation
		if(jumpCooldown > 0) {
			jumpCooldown--;
		}
	}
	
	@SubscribeEvent
	public static void onKeyInput(KeyInputEvent event) {
		if(event.getKey() == GLFW.GLFW_KEY_SPACE && event.getAction() == GLFW.GLFW_PRESS) {
			spacePressed = true;
		} else if (event.getKey() == GLFW.GLFW_KEY_SPACE && event.getAction() == GLFW.GLFW_RELEASE){
			spacePressed = false;
		}
	}
}
