package com.animania.addons.farm.client.render.tileentity;

import com.animania.Animania;
import com.animania.addons.farm.common.handler.FarmAddonBlockHandler;
import com.animania.addons.farm.common.tileentity.TileEntityHive;
import com.leviathanstudio.craftstudio.client.model.ModelCraftStudio;
import com.leviathanstudio.craftstudio.common.animation.simpleImpl.CSTileEntitySpecialRenderer;
import com.mojang.blaze3d.platform.GlStateManager;

import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Dist.CLIENT)
public class TileEntityHiveRenderer extends TileEntitySpecialRenderer<TileEntityHive>
{
	private static final ResourceLocation HIVE_TEXTURE = new ResourceLocation("animania:textures/entity/props/bee_hive.png");

	private ModelCraftStudio modelBeeHive = new ModelCraftStudio(Animania.MODID, "model_bee_hive", 128, 64);
	private ModelCraftStudio modelWildHive = new ModelCraftStudio(Animania.MODID, "model_wild_hive", 128, 64);

	public static TileEntityHiveRenderer instance;

	@Override
	public void render(TileEntityHive te, double x, double y, double z, float partialTicks, int destroyStage, float alpha)
	{

		Direction enumfacing = Direction.getFront(te.getBlockMetadata() & 7);
		GlStateManager.pushMatrix();
		this.bindTexture(HIVE_TEXTURE);
		if (te.getHiveType() == FarmAddonBlockHandler.blockHive)
		{
			GlStateManager.translate(x + 0.5D, y + 1.5D, z + 0.5D);
			GlStateManager.multMatrix(CSTileEntitySpecialRenderer.ROTATION_CORRECTOR);
			GlStateManager.rotate(enumfacing.getHorizontalAngle(), 0, 1, 0);
			this.modelBeeHive.render(te);
		}
		else
		{
			if (enumfacing == Direction.NORTH)
			{
				GlStateManager.translate(x + 0.5D, y + 1D, z + 0.75D);
			}
			else if (enumfacing == Direction.SOUTH)
			{
				GlStateManager.translate(x + 0.5D, y + 1D, z + 0.25D);
			}
			else if (enumfacing == Direction.EAST || enumfacing != Direction.WEST)
			{
				GlStateManager.translate(x + 0.25D, y + 1D, z + 0.5D);
			}
			else
			{
				GlStateManager.translate(x + 0.75D, y + 1D, z + 0.5D);
			}
			GlStateManager.multMatrix(CSTileEntitySpecialRenderer.ROTATION_CORRECTOR);
			GlStateManager.rotate(enumfacing.getHorizontalAngle(), 0, 1, 0);
			this.modelWildHive.render(te);
		}
		GlStateManager.popMatrix();
	}

	@Override
	public void setRendererDispatcher(TileEntityRendererDispatcher rendererDispatcherIn)
	{
		super.setRendererDispatcher(rendererDispatcherIn);
		TileEntityHiveRenderer.instance = this;
	}
}