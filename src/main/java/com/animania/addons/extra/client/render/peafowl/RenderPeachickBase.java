package com.animania.addons.extra.client.render.peafowl;

import org.lwjgl.opengl.GL11;

import com.animania.addons.extra.client.model.peafowl.ModelPeachick;
import com.animania.addons.extra.common.entity.peafowl.EntityAnimaniaPeacock;
import com.animania.addons.extra.common.entity.peafowl.EntityPeachickBase;
import com.animania.client.render.layer.LayerBlinking;
import com.animania.common.handler.BlockHandler;
import com.mojang.blaze3d.platform.GlStateManager;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Dist.CLIENT)
public class RenderPeachickBase<T extends EntityPeachickBase> extends RenderLiving<T>
{
	public static final Factory FACTORY = new Factory();

	private LayerBlinking blinkingLayer;
	
	public RenderPeachickBase(RenderManager rm)
	{
		super(rm, new ModelPeachick(), 0.15F);
		this.addLayer(blinkingLayer = new LayerBlinking(this, new ResourceLocation("animania:textures/entity/peacocks/peachick_blink.png"), 0));
	}

	@Override
	protected float handleRotationFloat(T livingBase, float partialTicks)
	{
		float f = livingBase.oFlap + (livingBase.wingRotation - livingBase.oFlap) * partialTicks;
		float f1 = livingBase.oFlapSpeed + (livingBase.destPos - livingBase.oFlapSpeed) * partialTicks;
		return (MathHelper.sin(f) + 1.0F) * f1;
	}

	@Override
	protected void preRenderCallback(T LivingEntity, float f)
	{
		this.preRenderScale(LivingEntity, f);
		blinkingLayer.setColors(LivingEntity.lidCol, LivingEntity.lidCol);
	}

	protected void preRenderScale(T entity, float f)
	{
		float age = entity.getEntityAge();
		GL11.glScalef(0.3F + (age / entity.getSizeDividend()), 0.3F + (age / entity.getSizeDividend()), 0.3F + (age / entity.getSizeDividend()));

		double x = entity.getX();
		double y = entity.getY();
		double z = entity.getZ();

		BlockPos pos = new BlockPos(x, y, z);

		Block blockchk = entity.level.getBlockState(pos).getBlock();
		EntityAnimaniaPeacock entityChk = (EntityAnimaniaPeacock) entity;
		if (blockchk == BlockHandler.blockNest || entityChk.getSleeping())
		{
			GlStateManager.translate(-0.25F, 0.35F, -0.25F);
			this.shadowSize = 0;
		}
		else
		{
			this.shadowSize = 0.3F;
			entityChk.setSleeping(false);
		}

	}

	@Override
	protected ResourceLocation getEntityTexture(T entity)
	{
		return entity.getResourceLocation();
	}

	static class Factory<T extends EntityPeachickBase> implements IRenderFactory<T>
	{
		@Override
		public Render<? super T> createRenderFor(RenderManager manager)
		{
			return new RenderPeachickBase(manager);
		}
	}

}