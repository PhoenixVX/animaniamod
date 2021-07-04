package com.animania.addons.farm.client.render.cows;

import org.lwjgl.opengl.GL11;

import com.animania.addons.farm.common.entity.cows.CowAngus.EntityCowAngus;
import com.animania.addons.farm.client.model.cow.ModelCowAngus;
import com.animania.addons.farm.common.entity.cows.EntityAnimaniaCow;
import com.animania.client.render.layer.LayerBlinking;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Dist.CLIENT)
public class RenderCowAngus<T extends EntityCowAngus> extends RenderLiving<T>
{
	public static final Factory FACTORY = new Factory();

	private static final ResourceLocation cowTextures = new ResourceLocation("animania:textures/entity/cows/cow_angus.png");
	private static final ResourceLocation cowTexturesBlink = new ResourceLocation("animania:textures/entity/cows/cow_blink.png");

	public RenderCowAngus(RenderManager rm)
	{
		super(rm, new ModelCowAngus(), 0.75F);
		addLayer(new LayerBlinking(this, cowTexturesBlink, 0x333333));

	}

	@Override
	protected void preRenderCallback(T LivingEntity, float f)
	{
		this.preRenderScale(LivingEntity, f);
	}

	protected ResourceLocation getCowTextures(EntityCowAngus par1EntityCow)
	{
		return RenderCowAngus.cowTextures;
	}

	protected ResourceLocation getCowTexturesBlink(EntityCowAngus par1EntityCow)
	{
		return RenderCowAngus.cowTexturesBlink;
	}

	protected void preRenderScale(T entity, float f)
	{
		GL11.glScalef(1.34F, 1.34F, 1.34F);

		EntityAnimaniaCow entityCow = (EntityAnimaniaCow) entity;
		if (entityCow.getSleeping())
		{
			float sleepTimer = entityCow.getSleepTimer();
			if (sleepTimer > -0.55F)
			{
				sleepTimer = sleepTimer - 0.01F;
			}
			entity.setSleepTimer(sleepTimer);

			GlStateManager.translate(-0.25F, entity.height - 1.85F - sleepTimer, -0.25F);
			GlStateManager.rotate(6.0F, 0.0F, 0.0F, 1.0F);
		}
		else
		{
			entityCow.setSleeping(false);
			entityCow.setSleepTimer(0F);
		}

	}

	@Override
	protected ResourceLocation getEntityTexture(T entity)
	{
		return this.getCowTextures(entity);
	}

	static class Factory<T extends EntityCowAngus> implements IRenderFactory<T>
	{
		@Override
		public Render<? super T> createRenderFor(RenderManager manager)
		{
			return new RenderCowAngus(manager);
		}
	}
}
