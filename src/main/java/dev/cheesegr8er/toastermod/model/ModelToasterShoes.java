package dev.cheesegr8er.toastermod.model;

import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.client.renderer.model.ModelBox;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;

public class ModelToasterShoes extends BipedModel<PlayerEntity>{

	public final RendererModel group;
	public float xOffset = 0.0f;
	public float yOffset = -8.0f;
	public float zOffset = 0.0f;
	
	public ModelToasterShoes(float xOffset, float yOffset, float zOffset) {
		
		this.xOffset = xOffset;
		this.yOffset = yOffset;
		this.zOffset = zOffset;
		
		textureWidth = 16;
		textureHeight = 16;

		group = new RendererModel(this);
		group.setRotationPoint(-8.0F, 16.0F, 8.0F);
		
		// Toaster body
		group.cubeList.add(new ModelBox(group, 0, 1, -4.0F+xOffset, 0.0F+yOffset, -7.0F, 8, 7, 2, 0.0F, false));
		group.cubeList.add(new ModelBox(group, 0, 1, -4.0F+xOffset, 0.0F+yOffset, 4.0F, 8, 7, 2, 0.0F, false));
		group.cubeList.add(new ModelBox(group, 0, 1, 3.0F+xOffset, 0.0F+yOffset, -5.0F, 2, 7, 9, 0.0F, false));
		group.cubeList.add(new ModelBox(group, 0, 1, -1.0F+xOffset, 0.0F+yOffset, -5.0F, 2, 7, 9, 0.0F, false));
		group.cubeList.add(new ModelBox(group, 0, 1, -5.0F+xOffset, 0.0F+yOffset, -5.0F, 2, 7, 9, 0.0F, false));
		group.cubeList.add(new ModelBox(group, 0, 1, -3.0F+xOffset, 6.0F+yOffset, -5.0F, 6, 1, 9, 0.0F, false));
		
		// Toaster base
		group.cubeList.add(new ModelBox(group, 0, 1, 4.0F+xOffset, 8.0F+yOffset, 5.0F, 1, 1, 1, 0.0F, false));
		group.cubeList.add(new ModelBox(group, 0, 1, 4.0F+xOffset, 8.0F+yOffset, -7.0F, 1, 1, 1, 0.0F, false));
		group.cubeList.add(new ModelBox(group, 0, 1, -5.0F+xOffset, 8.0F+yOffset, 5.0F, 1, 1, 1, 0.0F, false));
		group.cubeList.add(new ModelBox(group, 0, 1, -5.0F+xOffset, 8.0F+yOffset, -7.0F, 1, 1, 1, 0.0F, false));
		group.cubeList.add(new ModelBox(group, 0, 1, -5.0F+xOffset, 7.0F+yOffset, -7.0F, 10, 1, 13, 0.0F, false));
		
		// Toaster switch
		group.cubeList.add(new ModelBox(group, 0, 1, -1.0F+xOffset, 1.0F+yOffset, -8.0F, 2, 1, 1, 0.0F, false));
	}

	public void render(PlayerEntity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		group.render(f5);
	}
	
	public void setRotationAngle(RendererModel modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}