package com.animania.client.render.rabbits;

import org.lwjgl.opengl.GL11;

import com.animania.client.models.rabbits.ModelRex;
import com.animania.client.render.layer.LayerBlinking;
import com.animania.common.entities.rodents.rabbits.EntityAnimaniaRabbit;
import com.animania.common.entities.rodents.rabbits.EntityRabbitDoeRex;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderDoeRex<T extends EntityRabbitDoeRex> extends RenderLiving<T>
{
	public static final Factory FACTORY = new Factory();
	private static final ResourceLocation rabbitTextures = new ResourceLocation("animania:textures/entity/rabbits/rabbit_rex.png");
	private static final ResourceLocation rabbitTexturesBlink = new ResourceLocation("animania:textures/entity/rabbits/rabbit_blink.png");
	private static final ResourceLocation killerRabbitTextures = new ResourceLocation("animania:textures/entity/rabbits/rabbit_killer.png");

	public RenderDoeRex(RenderManager rm)
	{
		super(rm, new ModelRex(), 0.25F);
		this.addLayer(new LayerBlinking(this, rabbitTexturesBlink, 0x574133));
	}

	protected void preRenderScale(EntityRabbitDoeRex entity, float f)
	{
		if (entity.getCustomNameTag().equals("Killer")) {
			GlStateManager.scale(0.7D, 0.7D, 0.7D);
		} else {	
			GL11.glScalef(0.58F, 0.58F, 0.58F);
		}
		GL11.glTranslatef(0f, 0f, -0.5f);

		double x = entity.posX;
		double y = entity.posY;
		double z = entity.posZ;
		BlockPos pos = new BlockPos(x, y, z);
		Block blockchk = entity.world.getBlockState(pos).getBlock();
		EntityAnimaniaRabbit entityChk = (EntityAnimaniaRabbit) entity;
		if (entityChk.getSleeping())
		{
			this.shadowSize = 0;
			GlStateManager.translate(-.25F, 0.25F, -.25F);
		}
		else
		{
			this.shadowSize = 0.25F;
			entityChk.setSleeping(false);
		}
	}

	@Override
	protected void preRenderCallback(T entityliving, float f)
	{
		this.preRenderScale(entityliving, f);
	}

	@Override
	protected ResourceLocation getEntityTexture(T entity)
	{
		if (entity.getCustomNameTag().trim().equals("Killer")) {
			return RenderDoeRex.killerRabbitTextures;
		} else {
			return RenderDoeRex.rabbitTextures;
		}
	}

	static class Factory<T extends EntityRabbitDoeRex> implements IRenderFactory<T>
	{
		@Override
		public Render<? super T> createRenderFor(RenderManager manager)
		{
			return new RenderDoeRex(manager);
		}

	}
}
