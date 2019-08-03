package dev.cheesegr8er.toastermod.tick;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import org.lwjgl.glfw.GLFW;

import dev.cheesegr8er.toastermod.ToasterMod;
import dev.cheesegr8er.toastermod.entities.EntityToasterProjectile;
import dev.cheesegr8er.toastermod.init.ModSounds;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;
import net.minecraftforge.client.event.InputEvent.KeyInputEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.common.gameevent.TickEvent.PlayerTickEvent;

@Mod.EventBusSubscriber(modid = ToasterMod.MOD_ID, bus = Bus.FORGE)
public class BombDetonateLogic {

	private static boolean detonatePressed = false;
	
	private static final int DETONATE_DEFAULT_COOLDOWN = 30;
	private static int detonateCooldown = DETONATE_DEFAULT_COOLDOWN;
	public static HashMap<PlayerEntity, List<EntityToasterProjectile>> playersAndBombs = new HashMap<PlayerEntity, List<EntityToasterProjectile>>();
	public static List<EntityToasterProjectile> allBombs = new ArrayList<EntityToasterProjectile>();
	
	@SubscribeEvent
	public static void onPlayerTick(PlayerTickEvent event) {
		World world = event.player.world;
		PlayerEntity player = event.player;
		
		// Detonate all bombs upon pressing e.
		if(detonatePressed && detonateCooldown <= 0) {
			System.out.println("Detonate button pressed by player.");
			for(EntityToasterProjectile e: allBombs) {
				e.arrowExplode();
				world.playSound(null, e.posX, e.posY, e.posZ, ModSounds.toaster_down, SoundCategory.BLOCKS, 1.0f, 1.0f);
				detonateCooldown = DETONATE_DEFAULT_COOLDOWN;
			}
			allBombs = new ArrayList<EntityToasterProjectile>();
		}
		
		// Decrement cooldown
		if(detonateCooldown > 0) {
			detonateCooldown--;
		}
	}
	
	@SubscribeEvent
	public static void onKeyInput(KeyInputEvent event) {
		if(event.getKey() == GLFW.GLFW_KEY_G && event.getAction() == GLFW.GLFW_PRESS) {
			detonatePressed = true;
		} else if (event.getKey() == GLFW.GLFW_KEY_G && event.getAction() == GLFW.GLFW_RELEASE){
			detonatePressed = false;
		}
	}
}
