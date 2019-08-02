package dev.cheesegr8er.toastermod.init;

import dev.cheesegr8er.toastermod.ToasterMod;
import dev.cheesegr8er.toastermod.network.ToasterProjectileSpawnPacket;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.simple.SimpleChannel;

public class ModNetwork {
	public static final ResourceLocation CHANNEL_NAME = new ResourceLocation(ToasterMod.MOD_ID, "network");

	public static final String NETWORK_VERSION = new ResourceLocation(ToasterMod.MOD_ID, "1").toString();

	public static SimpleChannel getNetworkChannel() {
		final SimpleChannel channel = NetworkRegistry.ChannelBuilder.named(CHANNEL_NAME)
				.clientAcceptedVersions(version -> true)
				.serverAcceptedVersions(version -> true)
				.networkProtocolVersion(() -> NETWORK_VERSION)
				.simpleChannel();

		channel.messageBuilder(ToasterProjectileSpawnPacket.class, 1)
			.encoder(ToasterProjectileSpawnPacket::encode)
			.decoder(ToasterProjectileSpawnPacket::decode)
			.consumer(ToasterProjectileSpawnPacket::handle)
			.add();

		return channel;
	}
}
