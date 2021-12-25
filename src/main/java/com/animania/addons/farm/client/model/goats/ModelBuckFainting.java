package com.animania.addons.farm.client.model.goats;

import com.animania.addons.farm.common.entity.goats.EntityAnimaniaGoat;
import com.animania.addons.farm.common.entity.goats.GoatAlpine.EntityBuckAlpine;
import com.animania.addons.farm.common.entity.goats.GoatFainting.EntityBuckFainting;
import com.animania.config.AnimaniaConfig;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.entity.LivingEntity;

public class ModelBuckFainting extends ModelBase
{
	private float headRotationAngleX;
	public ModelRenderer HeadNode = new ModelRenderer(this, 0, 0);

	ModelRenderer Body;
	ModelRenderer Butt;
	ModelRenderer Tail;
	ModelRenderer BackLeg_L;
	ModelRenderer BackLeg_R;
	ModelRenderer FrontLeg_L;
	ModelRenderer FrontLeg_R;
	ModelRenderer Reproductive1;
	ModelRenderer Reproductive2;
	ModelRenderer Neck;
	ModelRenderer Head;
	ModelRenderer Ear_R;
	ModelRenderer Ear2_R;
	ModelRenderer Ear_L;
	ModelRenderer Ear2_L;
	ModelRenderer Bud_R;
	ModelRenderer Horn1_R;
	ModelRenderer Horn2_R;
	ModelRenderer Mouth;
	ModelRenderer Beard;
	ModelRenderer Snout3;
	ModelRenderer Nose;
	ModelRenderer Snout1;
	ModelRenderer Snout2;
	ModelRenderer Bud_L;
	ModelRenderer Horn1_L;
	ModelRenderer Horn2_L;

	public ModelBuckFainting()
	{
		this(0.0f);
	}

	public ModelBuckFainting(float par1)
	{
		this.Body = new ModelRenderer(this, 81, 5);
		this.Body.setTextureSize(128, 128);
		this.Body.addBox(-5F, -6.5F, -6.5F, 10, 13, 13);
		this.Body.setRotationPoint(0F, 8F, 2F);
		this.Butt = new ModelRenderer(this, 71, 40);
		this.Butt.setTextureSize(128, 128);
		this.Butt.addBox(-4.5F, -6F, 0F, 9, 12, 18);
		this.Butt.setRotationPoint(0F, 8.435779F, 6.980974F);
		this.Tail = new ModelRenderer(this, 38, 10);
		this.Tail.setTextureSize(128, 128);
		this.Tail.addBox(-1.5F, -5F, -1.5F, 3, 8, 3);
		this.Tail.setRotationPoint(0F, 3.104151F, 24.88555F);
		this.BackLeg_L = new ModelRenderer(this, 47, 28);
		this.BackLeg_L.setTextureSize(128, 128);
		this.BackLeg_L.addBox(-1.5F, -3F, -2.5F, 3, 18, 5);
		this.BackLeg_L.setRotationPoint(5F, 9.213954F, 18.9974F);
		this.BackLeg_R = new ModelRenderer(this, 47, 28);
		this.BackLeg_R.setTextureSize(128, 128);
		this.BackLeg_R.addBox(-1.5F, -3F, -2.5F, 3, 18, 5);
		this.BackLeg_R.setRotationPoint(-5F, 9.213954F, 18.9974F);
		this.FrontLeg_L = new ModelRenderer(this, 62, 4);
		this.FrontLeg_L.setTextureSize(128, 128);
		this.FrontLeg_L.addBox(-1.5F, -2F, -2F, 3, 16, 4);
		this.FrontLeg_L.setRotationPoint(4F, 10.72712F, -1.250051F);
		this.FrontLeg_R = new ModelRenderer(this, 62, 4);
		this.FrontLeg_R.setTextureSize(128, 128);
		this.FrontLeg_R.addBox(-1.5F, -2F, -2F, 3, 16, 4);
		this.FrontLeg_R.setRotationPoint(-4F, 10.72712F, -1.250051F);
		this.Reproductive1 = new ModelRenderer(this, 22, 38);
		this.Reproductive1.setTextureSize(128, 128);
		this.Reproductive1.addBox(-1F, -2F, -2F, 2, 4, 4);
		this.Reproductive1.setRotationPoint(0F, 13.29212F, 16.5943F);
		this.Reproductive2 = new ModelRenderer(this, 10, 37);
		this.Reproductive2.setTextureSize(128, 128);
		this.Reproductive2.addBox(-1.5F, -2F, -1F, 3, 4, 2);
		this.Reproductive2.setRotationPoint(0F, 13.46643F, 18.58669F);

		this.HeadNode = new ModelRenderer(this, 85, 78);
		this.HeadNode.setTextureSize(128, 128);
		this.HeadNode.addBox(0F, 0F, 0F, 0, 0, 0);
		this.HeadNode.setRotationPoint(0F, 7.738533F, -0.9885842F);

		this.Neck = new ModelRenderer(this, 85, 78);
		this.Neck.setTextureSize(128, 128);
		this.Neck.addBox(-2F, -4F, -14.5F, 4, 8, 15);
		this.Neck.setRotationPoint(0F, 0F, 0F);

		this.Head = new ModelRenderer(this, 9, 60);
		this.Head.setTextureSize(128, 128);
		this.Head.addBox(-3F, -3.5F, -7.5F, 6, 7, 7);
		this.Head.setRotationPoint(0F, -3.019106F - 7.738533F, -5.760714F + 0.9885842F);

		this.Ear_R = new ModelRenderer(this, 60, 84);
		this.Ear_R.setTextureSize(128, 128);
		this.Ear_R.addBox(-4F, -1F, -0.5F, 4, 2, 1);
		this.Ear_R.setRotationPoint(-2.5F, -4.118599F - 7.738533F, -9.194533F + 0.9885842F);

		this.Ear2_R = new ModelRenderer(this, 49, 80);
		this.Ear2_R.setTextureSize(128, 128);
		this.Ear2_R.addBox(-2.5F, -0.5F, -0.5F, 1, 1, 1);
		this.Ear2_R.setRotationPoint(-4.761655F, -4.845879F - 7.738533F, -9.97298F + 0.9885842F);

		this.Ear_L = new ModelRenderer(this, 60, 84);
		this.Ear_L.setTextureSize(128, 128);
		this.Ear_L.addBox(0F, -1F, -0.5F, 4, 2, 1);
		this.Ear_L.setRotationPoint(2.5F, -4.118599F - 7.738533F, -9.194533F + 0.9885842F);

		this.Ear2_L = new ModelRenderer(this, 49, 80);
		this.Ear2_L.setTextureSize(128, 128);
		this.Ear2_L.addBox(1.5F, -0.5F, -0.5F, 1, 1, 1);
		this.Ear2_L.setRotationPoint(4.761655F, -4.845879F - 7.738533F, -9.97298F + 0.9885842F);

		this.Bud_R = new ModelRenderer(this, 38, 64);
		this.Bud_R.setTextureSize(128, 128);
		this.Bud_R.addBox(-1.5F, -0.5F, -1.5F, 3, 1, 3);
		this.Bud_R.setRotationPoint(-1.8F, -6.123981F - 7.738533F, -8.727151F + 0.9885842F);

		this.Horn1_R = new ModelRenderer(this, 38, 71);
		this.Horn1_R.setTextureSize(128, 128);
		this.Horn1_R.addBox(-1F, -7.5F, -1F, 2, 7, 2);
		this.Horn1_R.setRotationPoint(-1.691772F, -5.63872F - 7.738533F, -8.63936F + 0.9885842F);

		this.Horn2_R = new ModelRenderer(this, 38, 71);
		this.Horn2_R.setTextureSize(128, 128);
		this.Horn2_R.addBox(-1F, -7.5F, -1F, 2, 7, 2);
		this.Horn2_R.setRotationPoint(-3.712005F, -10.69292F - 7.738533F, -5.073734F + 0.9885842F);

		this.Mouth = new ModelRenderer(this, 8, 111);
		this.Mouth.setTextureSize(128, 128);
		this.Mouth.addBox(-1.5F, -0.5F, -5.5F, 3, 1, 6);
		this.Mouth.setRotationPoint(0F, 1.416758F - 7.738533F, -10.73395F + 0.9885842F);

		this.Beard = new ModelRenderer(this, 31, 111);
		this.Beard.setTextureSize(128, 128);
		this.Beard.addBox(-1F, -2F, 0F, 2, 8, 4);
		this.Beard.setRotationPoint(0F, 3.526743F - 7.738533F, -14.81134F + 0.9885842F);

		this.Snout3 = new ModelRenderer(this, 9, 86);
		this.Snout3.setTextureSize(128, 128);
		this.Snout3.addBox(-1.5F, 0F, -5F, 3, 2, 5);
		this.Snout3.setRotationPoint(0F, -2.642731F - 7.738533F, -13.45297F + 0.9885842F);

		this.Nose = new ModelRenderer(this, 40, 91);
		this.Nose.setTextureSize(128, 128);
		this.Nose.addBox(-1F, 0F, 0F, 2, 2, 2);
		this.Nose.setRotationPoint(0F, 0.481411F - 7.738533F, -18.02077F + 0.9885842F);

		this.Snout1 = new ModelRenderer(this, 11, 76);
		this.Snout1.setTextureSize(128, 128);
		this.Snout1.addBox(-1.5F, 0F, -5.5F, 3, 2, 6);
		this.Snout1.setRotationPoint(-0.01F, -3.844696F - 7.738533F, -13.79616F + 0.9885842F);

		this.Snout2 = new ModelRenderer(this, 10, 96);
		this.Snout2.setTextureSize(128, 128);
		this.Snout2.addBox(-2F, -0.5F, -5F, 4, 3, 6);
		this.Snout2.setRotationPoint(0F, -1.047844F - 7.738533F, -12.85815F + 0.9885842F);

		this.Bud_L = new ModelRenderer(this, 38, 64);
		this.Bud_L.setTextureSize(128, 128);
		this.Bud_L.addBox(-1.5F, -0.5F, -1.5F, 3, 1, 3);
		this.Bud_L.setRotationPoint(1.8F, -6.123981F - 7.738533F, -8.727151F + 0.9885842F);

		this.Horn1_L = new ModelRenderer(this, 38, 71);
		this.Horn1_L.setTextureSize(128, 128);
		this.Horn1_L.addBox(-1F, -7.5F, -1F, 2, 7, 2);
		this.Horn1_L.setRotationPoint(1.691772F, -5.63872F - 7.738533F, -8.63936F + 0.9885842F);

		this.Horn2_L = new ModelRenderer(this, 38, 71);
		this.Horn2_L.setTextureSize(128, 128);
		this.Horn2_L.addBox(-1F, -7.5F, -1F, 2, 7, 2);
		this.Horn2_L.setRotationPoint(3.712005F, -10.69292F - 7.738533F, -5.073734F + 0.9885842F);

		this.HeadNode.addChild(this.Head);
		this.HeadNode.addChild(this.Neck);
		this.HeadNode.addChild(this.Ear_R);
		this.HeadNode.addChild(this.Ear2_R);
		this.HeadNode.addChild(this.Ear_L);
		this.HeadNode.addChild(this.Ear2_L);
		this.HeadNode.addChild(this.Bud_R);
		this.HeadNode.addChild(this.Bud_L);
		this.HeadNode.addChild(this.Horn1_R);
		this.HeadNode.addChild(this.Horn2_R);
		this.HeadNode.addChild(this.Horn1_L);
		this.HeadNode.addChild(this.Horn2_L);
		this.HeadNode.addChild(this.Mouth);
		this.HeadNode.addChild(this.Beard);
		this.HeadNode.addChild(this.Snout1);
		this.HeadNode.addChild(this.Snout2);
		this.HeadNode.addChild(this.Snout3);

	}

	public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale)
	{
		this.Body.rotateAngleX = -0.08726646F;
		this.Butt.rotateAngleX = 0.01847221F;
		this.Tail.rotateAngleX = -0.6475495F;
		this.BackLeg_L.rotateAngleX = 0.01847221F;
		this.BackLeg_R.rotateAngleX = 0.01847221F;
		this.Reproductive1.rotateAngleX = 0.07853982F;
		this.Reproductive2.rotateAngleX = 0.07853982F;
		this.Neck.rotateAngleX = -0.9705301F;
		this.Head.rotateAngleX = 0.2771511F;
		this.Ear_R.rotateAngleX = 0.3975939F;
		this.Ear_R.rotateAngleY = -0.2030238F;
		this.Ear_R.rotateAngleZ = 0.321354F;
		this.Ear2_R.rotateAngleX = 0.397594F;
		this.Ear2_R.rotateAngleY = -0.2030239F;
		this.Ear2_R.rotateAngleZ = 0.3213542F;
		this.Ear_L.rotateAngleX = 0.3975939F;
		this.Ear_L.rotateAngleY = 0.2030238F;
		this.Ear_L.rotateAngleZ = -0.321354F;
		this.Ear2_L.rotateAngleX = 0.397594F;
		this.Ear2_L.rotateAngleY = 0.2030239F;
		this.Ear2_L.rotateAngleZ = -0.3213542F;
		this.Bud_R.rotateAngleX = 0.1493225F;
		this.Bud_R.rotateAngleY = -0.1260779F;
		this.Bud_R.rotateAngleZ = -0.09751795F;
		this.Mouth.rotateAngleX = 0.2771511F;
		this.Snout3.rotateAngleX = 0.6541421F;
		this.Nose.rotateAngleX = 0.4028149F;
		this.Snout1.rotateAngleX = 1.100248F;
		this.Snout2.rotateAngleX = 0.4028149F;
		this.Bud_L.rotateAngleX = 0.1493225F;
		this.Bud_L.rotateAngleY = 0.1260779F;
		this.Bud_L.rotateAngleZ = 0.09751794F;

		this.Horn1_R.rotateAngleX = -0.7273443F;
		this.Horn1_R.rotateAngleY = -0.4953492F;
		this.Horn1_R.rotateAngleZ = -0.01269654F;

		this.Horn2_R.rotateAngleX = -1.890043F;
		// Horn2_R.rotateAngleY = -1.887417F;
		this.Horn2_R.rotateAngleZ = 1.380272F;

		this.Horn1_L.rotateAngleX = -0.7273443F;
		this.Horn1_L.rotateAngleY = 0.4953492F;
		this.Horn1_L.rotateAngleZ = 0.01269654F;

		this.Horn2_L.rotateAngleX = -1.890043F;
		// Horn2_L.rotateAngleY = 1.887417F;
		this.Horn2_L.rotateAngleZ = -1.380272F;

		this.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entityIn);

		boolean isSleeping = false;
		EntityAnimaniaGoat ech = (EntityAnimaniaGoat) entityIn;
		if (ech.getSleeping())
		{
			isSleeping = true;
		}
		float sleepTimer = ech.getSleepTimer();

		if (isSleeping)
		{

			this.FrontLeg_L.rotateAngleX = sleepTimer * -1.8F;
			this.FrontLeg_L.render(scale * .95F);
			this.FrontLeg_R.rotateAngleX = sleepTimer * -1.8F;
			this.FrontLeg_R.render(scale * .97F);
			this.BackLeg_L.rotateAngleX = sleepTimer * 1.7F;
			this.BackLeg_L.render(scale * .97F);
			this.BackLeg_R.rotateAngleX = sleepTimer * 1.75F;
			this.BackLeg_R.render(scale * .95F);
			this.HeadNode.rotateAngleY = sleepTimer * -2.8F;

			if (sleepTimer > -.28)
			{
				this.Body.rotateAngleX = -(sleepTimer / 3);
			}
			else
			{
				this.Body.rotateAngleX = +(sleepTimer / 3);
			}

		}
		else
		{

			this.BackLeg_L.rotateAngleZ = 0;
			this.BackLeg_L.render(scale);
			this.BackLeg_R.rotateAngleZ = 0;
			this.BackLeg_R.render(scale);
			this.FrontLeg_L.rotateAngleZ = 0;
			this.FrontLeg_L.render(scale);
			this.FrontLeg_R.rotateAngleZ = 0;
			this.FrontLeg_R.render(scale);
			this.HeadNode.rotateAngleY = 0F;
			this.Body.rotateAngleX = 0F;

		}

		this.Body.render(scale);
		this.Butt.render(scale);
		this.Tail.render(scale);
		if (AnimaniaConfig.gameRules.showParts)
		{
			this.Reproductive1.render(scale);
			this.Reproductive2.render(scale);
		}
		this.HeadNode.render(scale);

	}

	@Override
	public void setLivingAnimations(LivingEntity LivingEntityIn, float p_78086_2_, float p_78086_3_, float partialTickTime)
	{
		super.setLivingAnimations(LivingEntityIn, p_78086_2_, p_78086_3_, partialTickTime);

		this.HeadNode.rotationPointY = +7.75F + ((EntityBuckFainting) LivingEntityIn).getHeadAnchorPointY(partialTickTime) * 6.0F;
		this.headRotationAngleX = ((EntityBuckFainting) LivingEntityIn).getHeadAngleX(partialTickTime);

	}

	@Override
	public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, Entity entity)
	{
		float f6 = 180F / (float) Math.PI;

		if ((entity instanceof EntityBuckAlpine eb) && (eb.getFighting() && eb.getRivalUniqueId() != null))
		{
			// this.HeadNode.rotationPointY = 13.75F + ((EntityBuckAlpine)
			// entity).getHeadAnchorPointY(1) * 12.0F;
			this.HeadNode.rotateAngleX = 0.687F;
		}
		else
		{
			this.HeadNode.rotateAngleX = par5 / (180F / (float) Math.PI);
			this.HeadNode.rotateAngleY = par4 / (180F / (float) Math.PI);
			this.HeadNode.rotateAngleX = this.headRotationAngleX;
		}

		boolean isSleeping = false;

		EntityAnimaniaGoat ech = (EntityAnimaniaGoat) entity;
		if (ech.getSleeping())
		{
			isSleeping = true;
		}

		if (!isSleeping)
		{
			this.Tail.rotateAngleY = MathHelper.sin(par3 * 3.141593F * 0.05F) * MathHelper.sin(par3 * 3.141593F * .03F * 0.05F) * 0.15F * 3.141593F;
		}
		else
		{
			this.Tail.rotateAngleY = MathHelper.sin(1 * 3.141593F * 0.05F) * MathHelper.sin(1 * 3.141593F * .03F * 0.05F) * 0.15F * 3.141593F;
		}

		this.BackLeg_L.rotateAngleX = MathHelper.cos(par1 * 0.6662F) * 1.4F * par2;
		this.BackLeg_R.rotateAngleX = MathHelper.cos(par1 * 0.6662F + (float) Math.PI) * 1.4F * par2;
		this.FrontLeg_L.rotateAngleX = MathHelper.cos(par1 * 0.6662F + (float) Math.PI) * 1.4F * par2;
		this.FrontLeg_R.rotateAngleX = MathHelper.cos(par1 * 0.6662F) * 1.4F * par2;
	}

}
