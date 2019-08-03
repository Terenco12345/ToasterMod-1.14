package dev.cheesegr8er.toastermod.model;

import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.client.renderer.model.Model;
import net.minecraft.client.renderer.model.ModelBox;

public class ModelToasterProjectile extends Model{

	private final RendererModel bone;

	public ModelToasterProjectile() {
		textureWidth = 64;
		textureHeight = 64;

		bone = new RendererModel(this);
		bone.setRotationPoint(0.0F, 20.5833F, 0.0F);
		bone.cubeList.add(new ModelBox(bone, 20, 9, -4.0F, -3.5833F, -3.0F, 2, 6, 6, 0.0F, false));
		bone.cubeList.add(new ModelBox(bone, 10, 15, -1.0F, -3.5833F, -3.0F, 2, 6, 6, 0.0F, false));
		bone.cubeList.add(new ModelBox(bone, 0, 9, 2.0F, -3.5833F, -3.0F, 2, 6, 6, 0.0F, false));
		bone.cubeList.add(new ModelBox(bone, 25, 26, -3.0F, -3.5833F, -4.0F, 6, 6, 1, 0.0F, false));
		bone.cubeList.add(new ModelBox(bone, 24, 0, -3.0F, -3.5833F, 3.0F, 6, 6, 1, 0.0F, false));
		bone.cubeList.add(new ModelBox(bone, 0, 0, -4.0F, 2.4167F, -4.0F, 8, 1, 8, 0.0F, false));
	}

	public void render(float f5) {
		bone.render(f5);
	}
	
	public void setRotationAngle(RendererModel modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}