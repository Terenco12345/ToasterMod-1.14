package dev.cheesegr8er.toastermod.model;

import com.mojang.blaze3d.platform.GlStateManager;

import dev.cheesegr8er.toastermod.ToasterMod;
import dev.cheesegr8er.toastermod.entities.EntityToasterProjectile;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.registry.IRenderFactory;

@OnlyIn(Dist.CLIENT)
public class RenderToasterProjectile extends EntityRenderer<EntityToasterProjectile>{

	public static final ResourceLocation texture = new ResourceLocation(ToasterMod.MOD_ID+":textures/entity/toaster_projectile.png");
	public ModelToasterProjectile toaster_projectile = new ModelToasterProjectile();


	public RenderToasterProjectile(EntityRendererManager renderManager) {
		super(renderManager);

		System.out.println("Toaster projectile renderer generated.");
	}

	@Override
	public void doRender(EntityToasterProjectile entity, double x, double y, double z, float entityYaw, float partialTicks) {
		this.bindEntityTexture(entity);
		GlStateManager.color4f(1.0F, 1.0F, 1.0F, 1.0F);
		GlStateManager.pushMatrix();
		GlStateManager.translatef((float)x, (float)y-1.2f, (float)z);
		
		toaster_projectile.render(0.06f);

		if (this.renderOutlines) {
			GlStateManager.tearDownSolidRenderingTextureCombine();
			GlStateManager.disableColorMaterial();
		}

		GlStateManager.disableRescaleNormal();
		GlStateManager.popMatrix();
		super.doRender(entity, x, y, z, 0, partialTicks);
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
