package dev.cheesegr8er.toastermod.network;

import java.util.UUID;
import java.util.function.Supplier;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.registry.Registry;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.network.NetworkEvent;

public class ToasterProjectileSpawnPacket {

	private int entityId;
	private UUID uniqueId;
	private double x;
	private double y;
	private double z;
	private int speedX;
	private int speedY;
	private int speedZ;
	private int pitch;
	private int yaw;
	private EntityType<?> type;
	private int data;

	public ToasterProjectileSpawnPacket(int entityId, UUID uniqueId, double x, double y, double z, float pitch, float yaw, EntityType<?> type, int data, Vec3d speedVector) {
		this.entityId = entityId;
		this.uniqueId = uniqueId;
		this.x = x;
		this.y = y;
		this.z = z;
		this.pitch = MathHelper.floor(pitch * 256.0F / 360.0F);
		this.yaw = MathHelper.floor(yaw * 256.0F / 360.0F);
		this.type = type;
		this.data = data;
		this.speedX = (int)(MathHelper.clamp(speedVector.x, -3.9D, 3.9D) * 8000.0D);
		this.speedY = (int)(MathHelper.clamp(speedVector.y, -3.9D, 3.9D) * 8000.0D);
		this.speedZ = (int)(MathHelper.clamp(speedVector.z, -3.9D, 3.9D) * 8000.0D);
	}

	public ToasterProjectileSpawnPacket() {

	}

	public static void encode(final ToasterProjectileSpawnPacket message, final PacketBuffer buffer) {
		buffer.writeVarInt(message.entityId);
		buffer.writeUniqueId(message.uniqueId);
		buffer.writeVarInt(Registry.ENTITY_TYPE.getId(message.type));
		buffer.writeDouble(message.x);
		buffer.writeDouble(message.y);
		buffer.writeDouble(message.z);
		buffer.writeByte(message.pitch);
		buffer.writeByte(message.yaw);
		buffer.writeInt(message.data);
		buffer.writeShort(message.speedX);
		buffer.writeShort(message.speedY);
		buffer.writeShort(message.speedZ);
	}

	public static ToasterProjectileSpawnPacket decode(final PacketBuffer buffer) {
		ToasterProjectileSpawnPacket message = new ToasterProjectileSpawnPacket();
		
		message.entityId = buffer.readVarInt();
		message.uniqueId = buffer.readUniqueId();
		message.type = Registry.ENTITY_TYPE.getByValue(buffer.readVarInt());
		message.x = buffer.readDouble();
		message.y = buffer.readDouble();
		message.z = buffer.readDouble();
		message.pitch = buffer.readByte();
		message.yaw = buffer.readByte();
		message.data = buffer.readInt();
		message.speedX = buffer.readShort();
		message.speedY = buffer.readShort();
		message.speedZ = buffer.readShort();
		
		return message;
	}

	public static void handle(ToasterProjectileSpawnPacket message, Supplier<NetworkEvent.Context> ctx) {
		ctx.get().enqueueWork(() -> {
			
		});
		ctx.get().setPacketHandled(true);
	}
	
	@OnlyIn(Dist.CLIENT)
	   public int getEntityID() {
	      return this.entityId;
	   }

	   @OnlyIn(Dist.CLIENT)
	   public UUID getUniqueId() {
	      return this.uniqueId;
	   }

	   @OnlyIn(Dist.CLIENT)
	   public double getX() {
	      return this.x;
	   }

	   @OnlyIn(Dist.CLIENT)
	   public double getY() {
	      return this.y;
	   }

	   @OnlyIn(Dist.CLIENT)
	   public double getZ() {
	      return this.z;
	   }

	   @OnlyIn(Dist.CLIENT)
	   public double func_218693_g() {
	      return (double)this.speedX / 8000.0D;
	   }

	   @OnlyIn(Dist.CLIENT)
	   public double func_218695_h() {
	      return (double)this.speedY / 8000.0D;
	   }

	   @OnlyIn(Dist.CLIENT)
	   public double func_218692_i() {
	      return (double)this.speedZ / 8000.0D;
	   }

	   @OnlyIn(Dist.CLIENT)
	   public int getPitch() {
	      return this.pitch;
	   }

	   @OnlyIn(Dist.CLIENT)
	   public int getYaw() {
	      return this.yaw;
	   }

	   @OnlyIn(Dist.CLIENT)
	   public EntityType<?> getType() {
	      return this.type;
	   }

	   @OnlyIn(Dist.CLIENT)
	   public int getData() {
	      return this.data;
	   }
}
