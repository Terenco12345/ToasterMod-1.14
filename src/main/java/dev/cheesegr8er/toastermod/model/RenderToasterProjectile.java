package dev.cheesegr8er.toastermod.model;

import com.mojang.blaze3d.platform.GlStateManager;

import dev.cheesegr8er.toastermod.entities.EntityToasterProjectile;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.registry.IRenderFactory;

@OnlyIn(Dist.CLIENT)
public class RenderToasterProjectile extends EntityRenderer<EntityToasterProjectile>{

	public static final ResourceLocation texture = new ResourceLocation("minecraft:textures/block/iron_block.png");
	public ModelToasterProjectile toaster_projectile = new ModelToasterProjectile(6.0f, 5.0f, 0.0f);


	public RenderToasterProjectile(EntityRendererManager renderManager) {
		super(renderManager);

		System.out.println("Toaster projectile renderer generated.");
	}

	@Override
	public void doRender(EntityToasterProjectile entity, double x, double y, double z, float entityYaw, float partialTicks) {
		this.bindEntityTexture(entity);
		GlStateManager.color4f(1.0F, 1.0F, 1.0F, 1.0F);
		GlStateManager.pushMatrix();
		GlStateManager.disableLighting();
		GlStateManager.translatef((float)x, (float)y, (float)z);
		GlStateManager.rotatef(MathHelper.lerp(partialTicks, entity.prevRotationYaw, entity.rotationYaw) - 90.0F, 0.0F, 1.0F, 0.0F);
		GlStateManager.rotatef(MathHelper.lerp(partialTicks, entity.prevRotationPitch, entity.rotationPitch), 0.0F, 0.0F, 1.0F);

		toaster_projectile.render(1);

		if (this.renderOutlines) {
			GlStateManager.tearDownSolidRenderingTextureCombine();
			GlStateManager.disableColorMaterial();
		}

		GlStateManager.disableRescaleNormal();
		GlStateManager.enableLighting();
		GlStateManager.popMatrix();
		super.doRender(entity, x, y, z, entityYaw, partialTicks);
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityToasterProjectile entity) {
		return texture;
	}

	public static class RenderFactory implements IRenderFactory<EntityToasterProjectile>{
		@Override
		public EntityRenderer<? super EntityToasterProjectile> createRenderFor(EntityRendererManager manager) {
			return new RenderToasterProjectile(manager);
		}

	}
}
