package com.animania.addons.farm.client.model.sheep;

import com.animania.addons.farm.common.entity.sheep.EntityAnimaniaSheep;
import com.animania.addons.farm.common.entity.sheep.SheepJacob.EntityEweJacob;
import com.animania.addons.farm.common.entity.sheep.SheepJacob.EntityLambJacob;
import com.animania.addons.farm.common.entity.sheep.SheepJacob.EntityRamJacob;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.entity.LivingEntity;

public class ModelJacobSheep extends ModelBase
{	
	private float headRotationAngleX;
	public ModelRenderer HeadNode = new ModelRenderer(this, 0, 0);

	ModelRenderer Body;
	ModelRenderer RightFrontLeg;
	ModelRenderer RightFrontLegWool;
	ModelRenderer Hips;
	ModelRenderer RightBackLeg;
	ModelRenderer RightBackLegWool;
	ModelRenderer Tail;
	ModelRenderer WoolBody1;
	ModelRenderer WoolBody2;
	ModelRenderer WoolHips;
	ModelRenderer LeftBackLeg;
	ModelRenderer LeftBackLegWool;
	ModelRenderer Neck;
	ModelRenderer Head;
	ModelRenderer UpperJaw;
	ModelRenderer Nose;
	ModelRenderer UpperJawDetail1;
	ModelRenderer UpperJawDetail2;
	ModelRenderer LowerJaw;
	ModelRenderer LeftEar1;
	ModelRenderer LeftEar2;
	ModelRenderer RightEar1;
	ModelRenderer RightEar2;
	ModelRenderer HeadWool;
	ModelRenderer LeftHorn4;
	ModelRenderer LeftHorn5;
	ModelRenderer LeftHorn6;
	ModelRenderer LeftHorn1;
	ModelRenderer LeftHorn2;
	ModelRenderer LeftHorn3;
	ModelRenderer RightHorn4;
	ModelRenderer RightHorn5;
	ModelRenderer RightHorn6;
	ModelRenderer RightHorn1;
	ModelRenderer RightHorn2;
	ModelRenderer RightHorn3;
	ModelRenderer NeckWool;
	ModelRenderer LeftFrontLeg;
	ModelRenderer LeftFrontLegWool;


	public ModelJacobSheep()
	{
		this(0.0f);
	}

	public ModelJacobSheep(float par1)
	{

		Body = new ModelRenderer( this, 0, 39 );
		Body.setTextureSize( 128, 128 );
		Body.addBox( -3.5F, -5.5F, -13.5F, 7, 13, 17);
		Body.setRotationPoint( 0F, 7F, 0F + 7F);
		RightFrontLeg = new ModelRenderer( this, 107, 4 );
		RightFrontLeg.setTextureSize( 128, 128 );
		RightFrontLeg.addBox( -1.5F, 0F, -1.5F, 3, 16, 3);
		RightFrontLeg.setRotationPoint( -4F, 9.149483F, -10.08116F + 7F);
		RightFrontLegWool = new ModelRenderer( this, 98, 65 );
		RightFrontLegWool.setTextureSize( 128, 128 );
		RightFrontLegWool.addBox( -2F, -2F, -2.5F, 4, 10, 5);
		RightFrontLegWool.setRotationPoint( -4F, 9.149483F, -10.08116F+ 7F );
		Hips = new ModelRenderer( this, 56, 25 );
		Hips.setTextureSize( 128, 128 );
		Hips.addBox( -4F, -1F, 0F, 8, 12, 11);
		Hips.setRotationPoint( 0F, 2.675018F, 1.651946F + 7F);
		RightBackLeg = new ModelRenderer( this, 107, 26 );
		RightBackLeg.setTextureSize( 128, 128 );
		RightBackLeg.addBox( -1.5F, -0.5F, -1.5F, 3, 18, 3);
		RightBackLeg.setRotationPoint( -4.5F, 7.543364F, 9.238067F + 7F);
		RightBackLegWool = new ModelRenderer( this, 99, 83 );
		RightBackLegWool.setTextureSize( 128, 128 );
		RightBackLegWool.addBox( -2F, -1F, -2.5F, 4, 12, 5);
		RightBackLegWool.setRotationPoint( -4.5F, 7.043413F, 9.245048F + 7F);
		Tail = new ModelRenderer( this, 0, 0 );
		Tail.setTextureSize( 128, 128 );
		Tail.addBox( -1.5F, -1F, -2F, 3, 7, 3);
		Tail.setRotationPoint( 0F, 3.352909F, 12.66545F+ 7F );
		WoolBody1 = new ModelRenderer( this, 40, 100 );
		WoolBody1.setTextureSize( 128, 128 );
		WoolBody1.addBox( -5F, -6F, -6F, 10, 14, 12);
		WoolBody1.setRotationPoint( 0F, 7.204525F, 0.0007457264F + 7F);
		WoolBody2 = new ModelRenderer( this, 1, 104 );
		WoolBody2.setTextureSize( 128, 128 );
		WoolBody2.addBox( -5.5F, -6.5F, -5F, 11, 15, 8);
		WoolBody2.setRotationPoint( 0F, 7.153107F, -8.771488F+ 7F );
		WoolHips = new ModelRenderer( this, 85, 104 );
		WoolHips.setTextureSize( 128, 128 );
		WoolHips.addBox( -6F, -6F, -6F, 12, 14, 8);
		WoolHips.setRotationPoint( 0F, 7.308489F, 11.23427F+ 7F );
		LeftBackLeg = new ModelRenderer( this, 107, 26 );
		LeftBackLeg.setTextureSize( 128, 128 );
		LeftBackLeg.addBox( -1.5F, -0.5F, -1.5F, 3, 18, 3);
		LeftBackLeg.setRotationPoint( 4.5F, 7.543364F, 9.238067F+ 7F );
		LeftBackLegWool = new ModelRenderer( this, 99, 83 );
		LeftBackLegWool.setTextureSize( 128, 128 );
		LeftBackLegWool.addBox( -2F, -1F, -2.5F, 4, 12, 5);
		LeftBackLegWool.setRotationPoint( 4.5F, 7.043413F, 9.245048F+ 7F );
		LeftFrontLeg = new ModelRenderer( this, 107, 4 );
		LeftFrontLeg.setTextureSize( 128, 128 );
		LeftFrontLeg.addBox( -1.5F, 0F, -1.5F, 3, 16, 3);
		LeftFrontLeg.setRotationPoint( 4F, 9.149483F, -10.08116F + 7F);
		LeftFrontLegWool = new ModelRenderer( this, 98, 65 );
		LeftFrontLegWool.setTextureSize( 128, 128 );
		LeftFrontLegWool.addBox( -2F, -2F, -2.5F, 4, 10, 5);
		LeftFrontLegWool.setRotationPoint( 4F, 9.149483F, -10.08116F + 7F);

		HeadNode = new ModelRenderer(this, 0, 18);
		HeadNode.setTextureSize(128, 128);
		HeadNode.addBox(0F, 0F, 0F, 0, 0, 0);
		HeadNode.setRotationPoint(0F, 5.110041F, -11.14083F+ 7F);

		Neck = new ModelRenderer( this, 0, 18 );
		Neck.setTextureSize( 128, 128 );
		Neck.addBox( -2.5F, -1.5F, -10.5F, 5, 7, 11);
		Neck.setRotationPoint( 0F, 0F, 0F );

		Head = new ModelRenderer( this, 32, 25 );
		Head.setTextureSize( 128, 128 );
		Head.addBox( -3F, -2F, -4F, 6, 7, 5);
		Head.setRotationPoint( 0F, -0.022192F - 5.110041F, -19.57529F + 11.14083F );

		UpperJaw = new ModelRenderer( this, 32, 39 );
		UpperJaw.setTextureSize( 128, 128 );
		UpperJaw.addBox( -2F, 0F, -5F, 4, 3, 5);
		UpperJaw.setRotationPoint( 0F, 2.052254F - 5.110041F, -23.13853F + 11.14083F );
		Nose = new ModelRenderer( this, 0, 36 );
		Nose.setTextureSize( 128, 128 );
		Nose.addBox( 0F, 0F, 0F, 2, 2, 2);
		Nose.setRotationPoint( -1F, 3.780466F - 5.110041F, -28.36378F  + 11.14083F);
		UpperJawDetail1 = new ModelRenderer( this, 0, 10 );
		UpperJawDetail1.setTextureSize( 128, 128 );
		UpperJawDetail1.addBox( -1.5F, 0F, -5F, 3, 2, 5);
		UpperJawDetail1.setRotationPoint( 0F, -0.6369667F - 5.110041F, -23.91834F + 11.14083F );
		UpperJawDetail2 = new ModelRenderer( this, 14, 0 );
		UpperJawDetail2.setTextureSize( 128, 128 );
		UpperJawDetail2.addBox( -1.5F, 0F, -5F, 3, 2, 5);
		UpperJawDetail2.setRotationPoint( 0F, 0.3615341F - 5.110041F, -23.8442F + 11.14083F );
		LowerJaw = new ModelRenderer( this, 12, 8 );
		LowerJaw.setTextureSize( 128, 128 );
		LowerJaw.addBox( -1.5F, 0F, -5F, 3, 1, 5);
		LowerJaw.setRotationPoint( 0F, 4.670414F - 5.110041F, -21.75461F + 11.14083F );
		LeftEar1 = new ModelRenderer( this, 20, 15 );
		LeftEar1.setTextureSize( 128, 128 );
		LeftEar1.addBox( 0F, -1F, -1F, 4, 2, 1);
		LeftEar1.setRotationPoint( 2.5F, -0.2142792F - 5.110041F, -19.63099F + 11.14083F );
		LeftEar2 = new ModelRenderer( this, 0, 26 );
		LeftEar2.setTextureSize( 128, 128 );
		LeftEar2.addBox( 0F, -0.5F, -1F, 1, 1, 1);
		LeftEar2.setRotationPoint( 6.138942F, 0.7618008F - 5.110041F, -20.12634F + 11.14083F );
		RightEar1 = new ModelRenderer( this, 20, 15 );
		RightEar1.setTextureSize( 128, 128 );
		RightEar1.addBox( -4F, -1F, -1F, 4, 2, 1);
		RightEar1.setRotationPoint( -2.5F, -0.2142792F - 5.110041F, -19.63099F + 11.14083F );
		RightEar2 = new ModelRenderer( this, 0, 26 );
		RightEar2.setTextureSize( 128, 128 );
		RightEar2.addBox( -1F, -0.5F, -1F, 1, 1, 1);
		RightEar2.setRotationPoint( -6.139007F, 0.7617092F - 5.110041F, -20.12604F + 11.14083F );
		HeadWool = new ModelRenderer( this, 30, 75 );
		HeadWool.setTextureSize( 128, 128 );
		HeadWool.addBox( -4F, -1F, -2F, 8, 2, 4);
		HeadWool.setRotationPoint( 0F, -1.602097F - 5.110041F, -19.51283F + 11.14083F );

		LeftHorn1 = new ModelRenderer( this, 80, 15 );
		LeftHorn1.setTextureSize( 128, 128 );
		LeftHorn1.addBox( 0.5F, -1F, 0F, 7, 2, 2);
		LeftHorn1.setRotationPoint( 1F, -0.4880867F - 5.110041F, -23.35457F  + 11.14083F);

		LeftHorn2 = new ModelRenderer( this, 80, 15 );
		LeftHorn2.setTextureSize( 128, 128 );
		LeftHorn2.addBox( -0.5F, -1F, -1F, 5, 2, 2);
		LeftHorn2.setRotationPoint( 5.091446F + .1F, -2.92992F - 5.110041F - 2.5F, -19.57336F  + 11.14083F - 2.0F);

		LeftHorn3 = new ModelRenderer( this, 80, 15 );
		LeftHorn3.setTextureSize( 128, 128 );
		LeftHorn3.addBox( 0F, -1F, -1F, 6, 2, 2);
		LeftHorn3.setRotationPoint( 6.256174F - .5F, -0.7019844F - 5.110041F -3.0F, -16.46214F  + 11.14083F - 1.3F);


		LeftHorn4 = new ModelRenderer( this, 80, 15 );
		LeftHorn4.setTextureSize( 128, 128 );
		LeftHorn4.addBox( 1.5F, -1F, -1F, 5, 2, 2);
		LeftHorn4.setRotationPoint( 0F, 0.8565235F - 5.110041F, -22.96467F  + 11.14083F);

		LeftHorn5 = new ModelRenderer( this, 80, 15 );
		LeftHorn5.setTextureSize( 128, 128 );
		LeftHorn5.addBox( 0F, -1F, -1F, 4, 2, 2);
		LeftHorn5.setRotationPoint( 2.291364F -.5F, -3.678375F - 5.110041F -1F, -19.77337F  + 11.14083F - 1.2F);

		LeftHorn6 = new ModelRenderer( this, 80, 15 );
		LeftHorn6.setTextureSize( 128, 128 );
		LeftHorn6.addBox( 0F, -0.5F, -1F, 6, 1, 2);
		LeftHorn6.setRotationPoint( 3.275353F - .3F, -5.587458F - 5.110041F -2F, -17.67881F  + 11.14083F -1F );


		RightHorn1 = new ModelRenderer( this, 80, 15 );
		RightHorn1.setTextureSize( 128, 128 );
		RightHorn1.addBox( -7.5F, -1F, 0F, 7, 2, 2);
		RightHorn1.setRotationPoint( -1F, -0.4880867F - 5.110041F, -23.35457F + 11.14083F );

		RightHorn2 = new ModelRenderer( this, 80, 15 );
		RightHorn2.setTextureSize( 128, 128 );
		RightHorn2.addBox( -4.5F, -1F, -1F, 5, 2, 2);
		RightHorn2.setRotationPoint( -5.091445F - .1F, -2.92992F - 5.110041F - 2.5F, -19.57336F + 11.14083F - 2.0F );

		RightHorn3 = new ModelRenderer( this, 80, 15 );
		RightHorn3.setTextureSize( 128, 128 );
		RightHorn3.addBox( -6F, -1F, -1F, 6, 2, 2);
		RightHorn3.setRotationPoint( -6.256174F + .5F, -0.7019844F - 5.110041F -3.0F, -16.46214F  + 11.14083F - 1.3F);


		RightHorn4 = new ModelRenderer( this, 80, 15 );
		RightHorn4.setTextureSize( 128, 128 );
		RightHorn4.addBox( -6.5F, -1F, -1F, 5, 2, 2);
		RightHorn4.setRotationPoint( 0F, 0.8565235F - 5.110041F, -22.96467F + 11.14083F );

		RightHorn5 = new ModelRenderer( this, 80, 15 );
		RightHorn5.setTextureSize( 128, 128 );
		RightHorn5.addBox( -4F, -1F, -1F, 4, 2, 2);
		RightHorn5.setRotationPoint( -2.291363F + .5F, -3.678375F - 5.110041F - 1F, -19.77337F + 11.14083F - 1.2F);

		RightHorn6 = new ModelRenderer( this, 80, 15 );
		RightHorn6.setTextureSize( 128, 128 );
		RightHorn6.addBox( -6F, -0.5F, -1F, 6, 1, 2);
		RightHorn6.setRotationPoint( -3.275352F +.3F, -5.587458F - 5.110041F -2F, -17.67881F + 11.14083F - 1F );


		NeckWool = new ModelRenderer( this, 59, 69 );
		NeckWool.setTextureSize( 128, 128 );
		NeckWool.addBox( -3.5F, -2.5F, -5F, 7, 10, 10);
		NeckWool.setRotationPoint( 0F, 2.858284F - 5.110041F, -13.18523F + 11.14083F);


		this.HeadNode.addChild(Head);
		this.HeadNode.addChild(Neck);
		this.HeadNode.addChild(UpperJaw);
		this.HeadNode.addChild(Nose);
		this.HeadNode.addChild(UpperJawDetail1);
		this.HeadNode.addChild(UpperJawDetail2);
		this.HeadNode.addChild(LowerJaw);
		this.HeadNode.addChild(LeftEar1);
		this.HeadNode.addChild(LeftEar2);
		this.HeadNode.addChild(RightEar1);
		this.HeadNode.addChild(RightEar2);
		this.HeadNode.addChild(RightHorn1);
		this.HeadNode.addChild(RightHorn2);
		this.HeadNode.addChild(RightHorn3);
		this.HeadNode.addChild(RightHorn4);
		this.HeadNode.addChild(RightHorn5);
		this.HeadNode.addChild(RightHorn6);
		this.HeadNode.addChild(LeftHorn1);
		this.HeadNode.addChild(LeftHorn2);
		this.HeadNode.addChild(LeftHorn3);
		this.HeadNode.addChild(LeftHorn4);
		this.HeadNode.addChild(LeftHorn5);
		this.HeadNode.addChild(LeftHorn6);
		this.HeadNode.addChild(NeckWool);


	}

	public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale)
	{
		Body.rotateAngleX = -0.03490658F;
		RightFrontLeg.rotateAngleX = -0.009773841F;
		RightFrontLegWool.rotateAngleX = -0.009773839F;
		Hips.rotateAngleX = 0.0174533F;
		RightBackLeg.rotateAngleX = -0.01396263F;
		RightBackLegWool.rotateAngleX = -0.01396263F;
		Tail.rotateAngleX = 0.6251218F;
		WoolBody1.rotateAngleX = 0.01443809F;
		WoolBody2.rotateAngleX = -0.03490658F;
		WoolHips.rotateAngleX = -0.03490658F;
		LeftBackLeg.rotateAngleX = -0.01396263F;
		LeftBackLegWool.rotateAngleX = -0.01396263F;
		Neck.rotateAngleX = -0.6684765F;
		Head.rotateAngleX = 0.2822346F;
		UpperJaw.rotateAngleX = 0.3557643F;
		Nose.rotateAngleX = 0.4814279F;
		UpperJawDetail1.rotateAngleX = 0.9514487F;
		UpperJawDetail2.rotateAngleX = 0.6938384F;
		LowerJaw.rotateAngleX = 0.3450664F;
		LeftEar1.rotateAngleX = 0.2499767F;
		LeftEar1.rotateAngleY = 0.2032021F;
		LeftEar1.rotateAngleZ = 0.2683108F;
		LeftEar2.rotateAngleX = 0.1961231F;
		LeftEar2.rotateAngleY = 0.3884683F;
		LeftEar2.rotateAngleZ = 0.3094194F;
		RightEar1.rotateAngleX = 0.2500451F;
		RightEar1.rotateAngleY = -0.2031327F;
		RightEar1.rotateAngleZ = -0.2682903F;
		RightEar2.rotateAngleX = 0.196194F;
		RightEar2.rotateAngleY = -0.3884026F;
		RightEar2.rotateAngleZ = -0.3094122F;
		HeadWool.rotateAngleX = 0.1118027F;

		LeftHorn4.rotateAngleX = -0.5085339F;
		LeftHorn4.rotateAngleY =  -0.3490964F;
		LeftHorn4.rotateAngleZ = -1.245775F;

		LeftHorn5.rotateAngleX = -0.4881583F;
		LeftHorn5.rotateAngleY = -0.58615F;
		LeftHorn5.rotateAngleZ = -1.1681811F;

		LeftHorn6.rotateAngleX = -0.361659F;
		LeftHorn6.rotateAngleY = -0.7571205F;
		LeftHorn6.rotateAngleZ = -1.2210798F;

		LeftHorn1.rotateAngleX = -0.8541339F;
		LeftHorn1.rotateAngleY = -0.1428768F;
		LeftHorn1.rotateAngleZ = -0.9456399F;
		LeftHorn2.rotateAngleX = -0.9789219F;
		LeftHorn2.rotateAngleY = -1F;
		LeftHorn2.rotateAngleZ = 1.628694F;
		LeftHorn3.rotateAngleX = 0.2919102F;
		//LeftHorn3.rotateAngleY = -2.795338F;
		LeftHorn3.rotateAngleZ = 1.604513F;

		RightHorn4.rotateAngleX = -0.508534F;
		RightHorn4.rotateAngleY = 0.3490964F;
		RightHorn4.rotateAngleZ = 1.245775F;

		RightHorn5.rotateAngleX = -0.4881585F;
		RightHorn5.rotateAngleY = 0.58615F;
		RightHorn5.rotateAngleZ = 1.1681811F;

		RightHorn6.rotateAngleX = -0.3616591F;
		RightHorn6.rotateAngleY = 0.7571207F;
		RightHorn6.rotateAngleZ = 1.2210801F;

		RightHorn1.rotateAngleX = -0.8541341F;
		RightHorn1.rotateAngleY = +0.1428769F;
		RightHorn1.rotateAngleZ = 0.9456401F;
		RightHorn2.rotateAngleX = -0.9789221F;
		RightHorn2.rotateAngleY = 1F;
		RightHorn2.rotateAngleZ = -1.628695F;
		RightHorn3.rotateAngleX = 0.2919102F;
		//RightHorn3.rotateAngleY = 2.795338F;
		RightHorn3.rotateAngleZ = -1.604513F;

		NeckWool.rotateAngleX = -0.5530767F;
		LeftFrontLeg.rotateAngleX = -0.009773842F;
		LeftFrontLegWool.rotateAngleX = -0.009773841F;

		this.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entityIn);

		boolean isSleeping = false;
		EntityAnimaniaSheep ech = (EntityAnimaniaSheep) entityIn;
		if (ech.getSleeping()) {
			isSleeping = true;
		}
		float sleepTimer = ech.getSleepTimer();

		if (isSleeping) {

			this.LeftFrontLeg.rotateAngleX = sleepTimer * -1.8F;
			this.LeftFrontLeg.render(scale * .95F);
			this.LeftFrontLegWool.rotateAngleX = sleepTimer * -1.8F;
			this.LeftFrontLegWool.render(scale * .95F);

			this.RightFrontLeg.rotateAngleX = sleepTimer * -1.8F;
			this.RightFrontLeg.render(scale * .97F);
			this.LeftFrontLegWool.rotateAngleX = sleepTimer * -1.8F;
			this.RightFrontLegWool.render(scale * .97F);

			this.LeftBackLeg.rotateAngleX = sleepTimer * 1.7F;
			this.LeftBackLeg.render(scale * .97F);
			this.LeftFrontLegWool.rotateAngleX = sleepTimer * -1.8F;
			this.LeftBackLegWool.render(scale * .97F);

			this.RightBackLeg.rotateAngleX = sleepTimer * 1.75F;
			this.RightBackLeg.render(scale * .95F);
			this.LeftFrontLegWool.rotateAngleX = sleepTimer * -1.8F;
			this.RightBackLegWool.render(scale * .95F);

			this.HeadNode.rotateAngleY = sleepTimer * 4.0F;

			if (sleepTimer > -.28) {
				this.Body.rotateAngleX = - (sleepTimer/3);
			} else {
				this.Body.rotateAngleX = + (sleepTimer/3);
			}

		} else {

			this.LeftBackLeg.rotateAngleZ = 0;
			this.LeftBackLeg.render(scale);
			this.LeftBackLegWool.rotateAngleZ = 0;
			this.LeftBackLegWool.render(scale);

			this.RightBackLeg.rotateAngleZ = 0;
			this.RightBackLeg.render(scale);
			this.RightBackLegWool.rotateAngleZ = 0;
			this.RightBackLegWool.render(scale);

			this.LeftFrontLeg.rotateAngleZ = 0;
			this.LeftFrontLeg.render(scale);
			this.LeftFrontLegWool.render(scale);
			this.LeftFrontLegWool.rotateAngleZ = 0;

			this.RightFrontLeg.rotateAngleZ = 0;
			this.RightFrontLeg.render(scale);
			this.RightFrontLegWool.rotateAngleZ = 0;
			this.RightFrontLegWool.render(scale);

			this.HeadNode.rotateAngleY = 0F;
			this.Body.rotateAngleX = 0F;

		}

		Body.render(scale);
		HeadNode.render(scale);
		Hips.render(scale);
		Tail.render(scale);
		WoolHips.render(scale);
		WoolBody1.render(scale);
		WoolBody2.render(scale);


	}

	@Override
	public void setLivingAnimations(LivingEntity entity, float p_78086_2_, float p_78086_3_, float partialTickTime)
	{
		super.setLivingAnimations(entity, p_78086_2_, p_78086_3_, partialTickTime);

		if (entity instanceof EntityRamJacob ) {
			EntityRamJacob entityRamJacob = (EntityRamJacob)entity;
			this.HeadNode.rotationPointY = 4F + entityRamJacob.getHeadAnchorPointY(partialTickTime) * 4.0F;
			this.headRotationAngleX = entityRamJacob.getHeadAngleX(partialTickTime);
		} else if (entity instanceof EntityEweJacob) {
			EntityEweJacob entityEweJacob = (EntityEweJacob)entity;
			this.HeadNode.rotationPointY = 4F + entityEweJacob.getHeadAnchorPointY(partialTickTime) * 4.0F;
			this.headRotationAngleX = entityEweJacob.getHeadAngleX(partialTickTime);
		} else if (entity instanceof EntityLambJacob) {
			EntityLambJacob entityLambJacob = (EntityLambJacob)entity;
			this.HeadNode.rotationPointY = 4F + entityLambJacob.getHeadAnchorPointY(partialTickTime) * 4.0F;
			this.headRotationAngleX = entityLambJacob.getHeadAngleX(partialTickTime);
		}
	}

	@Override
	public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, Entity entity)
	{
		float f6 = 180F / (float) Math.PI;
		this.HeadNode.rotateAngleX = par5 / (180F / (float) Math.PI);
		this.HeadNode.rotateAngleY = par4 / (180F / (float) Math.PI);
		this.HeadNode.rotateAngleX = this.headRotationAngleX;

		boolean isSleeping = false;
		EntityAnimaniaSheep ech = (EntityAnimaniaSheep) entity;
		if (ech.getSleeping()) {
			isSleeping = true;
		}
		if (!isSleeping) {
			this.Tail.rotateAngleY = MathHelper.sin(par3 * 3.141593F * 0.05F) * MathHelper.sin(par3 * 3.141593F * .03F * 0.05F) * 0.15F * 3.141593F;
		} else {
			this.Tail.rotateAngleY = MathHelper.sin(1 * 3.141593F * 0.05F) * MathHelper.sin(1 * 3.141593F * .03F * 0.05F) * 0.15F * 3.141593F;
		}

		this.LeftBackLeg.rotateAngleX =  MathHelper.cos(par1 * 0.6662F) * 1.2F * par2; //Left Back
		this.LeftBackLegWool.rotateAngleX = MathHelper.cos(par1 * 0.6662F) * 1.2F * par2;//Left Back

		this.RightBackLegWool.rotateAngleX = MathHelper.cos(par1 * 0.6662F + (float) Math.PI) * 1.4F * par2;//Right Back
		this.RightBackLeg.rotateAngleX = MathHelper.cos(par1 * 0.6662F + (float) Math.PI) * 1.4F * par2;//Right Back

		this.LeftFrontLeg.rotateAngleX = MathHelper.cos(par1 * 0.6662F + (float) Math.PI) * 1.4F * par2;//Left Front
		this.LeftFrontLegWool.rotateAngleX = MathHelper.cos(par1 * 0.6662F + (float) Math.PI) * 1.4F * par2;//Left Front

		this.RightFrontLeg.rotateAngleX = MathHelper.cos(par1 * 0.6662F) * 1.4F * par2;//Right Front
		this.RightFrontLegWool.rotateAngleX = MathHelper.cos(par1 * 0.6662F) * 1.4F * par2;//Right Front



	}

}
