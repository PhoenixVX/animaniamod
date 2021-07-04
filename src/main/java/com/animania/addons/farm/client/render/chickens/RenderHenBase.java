package com.animania.addons.farm.client.render.chickens;

import org.lwjgl.opengl.GL11;

import com.animania.addons.farm.client.model.chicken.ModelHen;
import com.animania.addons.farm.common.entity.chickens.EntityAnimaniaChicken;
import com.animania.addons.farm.common.entity.chickens.EntityHenBase;
import com.animania.client.render.layer.LayerBlinking;
import com.animania.common.handler.BlockHandler;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Dist.CLIENT)
public class RenderHenBase<T extends EntityHenBase> extends RenderLiving<T>
{

	public static final Factory FACTORY = new Factory();

	private LayerBlinking blinkingLayer;
	
	public RenderHenBase(RenderManager rm)
	{
		super(rm, new ModelHen(), 0.3F);
		this.addLayer(blinkingLayer = new LayerBlinking(this, new ResourceLocation("animania:textures/entity/chickens/chicken_blink.png"), 0));
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

	protected void preRenderScale(T LivingEntity, float f)
	{
		GL11.glScalef(0.9F, 0.9F, 0.9F);

		double x = LivingEntity.posX;
		double y = LivingEntity.posY;
		double z = LivingEntity.posZ;

		BlockPos pos = new BlockPos(x, y, z);

		Block blockchk = LivingEntity.world.getBlockState(pos).getBlock();

		EntityAnimaniaChicken entityChk = (EntityAnimaniaChicken) LivingEntity;
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

	static class Factory<T extends EntityHenBase> implements IRenderFactory<T>
	{
		@Override
		public Render<? super T> createRenderFor(RenderManager manager)
		{
			return new RenderHenBase(manager);
		}
	}
}