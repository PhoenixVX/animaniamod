package com.animania.addons.farm.client.model.horse;

import com.animania.addons.farm.common.entity.horses.EntityAnimaniaHorse;
import com.animania.addons.farm.common.entity.horses.HorseDraft.EntityStallionDraftHorse;
import com.animania.config.AnimaniaConfig;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.entity.LivingEntity;

public class ModelDraftHorseStallion extends ModelBase
{
	private float headRotationAngleX;
	public ModelRenderer HeadNode = new ModelRenderer(this, 0, 0);

	ModelRenderer Body;
	ModelRenderer TailNode;
	ModelRenderer tailA;
	ModelRenderer tailB;
	ModelRenderer tailC;
	ModelRenderer tailD;
	ModelRenderer BackLeftMuscle;
	ModelRenderer BackLeftA;
	ModelRenderer BackLeftB;
	ModelRenderer BackLeftHoof;
	ModelRenderer BackLeftHoof2;
	ModelRenderer BackLeftFluff;
	ModelRenderer BackLeftFluffb;
	ModelRenderer BackRightMuscle;
	ModelRenderer BackRightA;
	ModelRenderer BackRightB;
	ModelRenderer BackRightHoof;
	ModelRenderer BackRightHoof2;
	ModelRenderer BackRightFluff;
	ModelRenderer BackRightFluffb;
	ModelRenderer FrontLeftMuscle;
	ModelRenderer FrontLeftA;
	ModelRenderer FrontLeftB;
	ModelRenderer FrontLeftHoof;
	ModelRenderer FrontLeftHoof2;
	ModelRenderer FrontLeftFluff;
	ModelRenderer FrontLeftFluffb;
	ModelRenderer FrontRightMuscle;
	ModelRenderer FrontRightA;
	ModelRenderer FrontRightB;
	ModelRenderer FrontRightHoof;
	ModelRenderer FrontRightHoof2;
	ModelRenderer FrontRightFluff;
	ModelRenderer FrontRightFluffb;

	ModelRenderer Neck;
	ModelRenderer Head;
	ModelRenderer Nose;
	ModelRenderer Mouth;
	ModelRenderer Cheek;
	ModelRenderer Cheek2;
	ModelRenderer EarR;
	ModelRenderer EarL;
	ModelRenderer Fleco;
	ModelRenderer Fleco2;
	ModelRenderer Fleco3;
	ModelRenderer Fleco4;
	ModelRenderer Mane;

	ModelRenderer Block1;
	ModelRenderer Block2;

	ModelRenderer Reins1;
	ModelRenderer SaddleBase;
	ModelRenderer SaddleBase2;
	ModelRenderer SaddleBase3;
	ModelRenderer Saddle;
	ModelRenderer Saddle2;
	ModelRenderer Saddle3;
	ModelRenderer Saddle4;
	ModelRenderer Saddle5;
	ModelRenderer Saddle6;
	ModelRenderer Footstrap;
	ModelRenderer foot1;
	ModelRenderer foot2;
	ModelRenderer foot3;
	ModelRenderer foot4;
	ModelRenderer Footstrap2;
	ModelRenderer foot1a;
	ModelRenderer foot2a;
	ModelRenderer foot3a;
	ModelRenderer foot4a;
	ModelRenderer Saddle7;
	ModelRenderer SaddleHump;
	ModelRenderer SaddleHump2;
	ModelRenderer Strap1;
	ModelRenderer Strap2;
	ModelRenderer Strap3;

	public ModelDraftHorseStallion()
	{
		Body = new ModelRenderer( this, 0, 0 );
		Body.setTextureSize( 256, 128 );
		Body.addBox( -7F, -7F, -16F, 14, 14, 32);
		Body.setRotationPoint( 0F, -3F, 3F );


		TailNode = new ModelRenderer( this, 108, 108 );
		TailNode.setTextureSize( 256, 128 );
		TailNode.addBox( 0F, 0F, 0F, 0, 0, 0);
		TailNode.setRotationPoint( 0F, -10F, 19F );

		tailA = new ModelRenderer( this, 108, 108 );
		tailA.setTextureSize( 256, 128 );
		tailA.addBox( -1F, -1F, 0F, 2, 2, 3);
		tailA.setRotationPoint( 0F, 0F, 0F );

		tailB = new ModelRenderer( this, 102, 115 );
		tailB.setTextureSize( 256, 128 );
		tailB.addBox( -1.5F, -2F, 3F, 3, 4, 7);
		tailB.setRotationPoint( 0F, -10F + 10F, 19F - 19F );

		tailC = new ModelRenderer( this, 88, 111 );
		tailC.setTextureSize( 256, 128 );
		tailC.addBox( -1.5F, -4.5F, 9F, 3, 4, 7);
		tailC.setRotationPoint( 0F, -10F + 10F, 19F  - 19F);

		tailD = new ModelRenderer( this, 54, 56 );
		tailD.setTextureSize( 256, 128 );
		tailD.addBox( -2F, -2.5F, 0.5F, 4, 5, 11);
		tailD.setRotationPoint( 0F, 4.186213F + 10F, 24.47736F - 19F);

		BackLeftMuscle = new ModelRenderer( this, 60, 2 );
		BackLeftMuscle.setTextureSize( 256, 128 );
		BackLeftMuscle.addBox( -4F, -6F, -6F, 7, 12, 12);
		BackLeftMuscle.setRotationPoint( 5F, -1F, 14F );

		BackLeftA = new ModelRenderer( this, 1, 67 );
		BackLeftA.setTextureSize( 256, 128 );
		BackLeftA.addBox( -3F, 0.5F, -3F, 5, 9, 6);
		BackLeftA.setRotationPoint( 5.5F - 5F, 3.5F + 1F, 15F - 14F);

		BackLeftB = new ModelRenderer( this, 77, 72 );
		BackLeftB.setTextureSize( 256, 128 );
		BackLeftB.addBox( -2.5F, -1F, -2F, 4, 8, 4);
		BackLeftB.setRotationPoint( 5.5F - 5F, 12.84808F+ 1F, 16.73648F - 14F);

		BackLeftHoof = new ModelRenderer( this, 75, 84 );
		BackLeftHoof.setTextureSize( 256, 128 );
		BackLeftHoof.addBox( -3F, 5.1F, -2.5F, 5, 3, 5);
		BackLeftHoof.setRotationPoint( 5.5F - 5F, 13.98481F+ 1F, 15.67365F - 14F);

		BackLeftHoof2 = new ModelRenderer( this, 97, 92 );
		BackLeftHoof2.setTextureSize( 256, 128 );
		BackLeftHoof2.addBox( -3.5F, 5.1F, -3F, 6, 3, 6);
		BackLeftHoof2.setRotationPoint( 5.5F - 5F, 15.98481F+ 1F, 15.67365F - 14F);

		BackLeftFluff = new ModelRenderer( this, 74, 49 );
		BackLeftFluff.setTextureSize( 256, 128 );
		BackLeftFluff.addBox( -1F, -1.5F, -2F, 1, 9, 4);
		BackLeftFluff.setRotationPoint( 6 - 5F, 15.16499F+ 1F, 18.3588F - 14F);

		BackLeftFluffb = new ModelRenderer( this, 74, 49 );
		BackLeftFluffb.setTextureSize( 256, 128 );
		BackLeftFluffb.addBox( -1F, -1.5F, -2F, 1, 9, 4);
		BackLeftFluffb.setRotationPoint( 5.000259F - 5F, 15.18772F+ 1F, 18.3578F - 14F);


		BackRightMuscle = new ModelRenderer( this, 60, 2 );
		BackRightMuscle.setTextureSize( 256, 128 );
		BackRightMuscle.addBox( -4F, -6F, -6F, 7, 12, 12);
		BackRightMuscle.setRotationPoint( -4F, -1F, 14F );

		BackRightA = new ModelRenderer( this, 23, 67 );
		BackRightA.setTextureSize( 256, 128 );
		BackRightA.addBox( -3F, 0.5F, -3F, 5, 9, 6);
		BackRightA.setRotationPoint( -4.5F + 4F, 3.5F + 1F, 15F -14F );

		BackRightB = new ModelRenderer( this, 93, 72 );
		BackRightB.setTextureSize( 256, 128 );
		BackRightB.addBox( -2.5F, -1F, -2F, 4, 8, 4);
		BackRightB.setRotationPoint( -4.499999F + 4F, 13F + 1, 16.56283F - 14F );

		BackRightHoof = new ModelRenderer( this, 75, 84 );
		BackRightHoof.setTextureSize( 256, 128 );
		BackRightHoof.addBox( -3F, 5.1F, -2.5F, 5, 3, 5);
		BackRightHoof.setRotationPoint( -4.499999F + 4F, 14.13673F + 1F, 15.5F -14F );

		BackRightHoof2 = new ModelRenderer( this, 97, 92 );
		BackRightHoof2.setTextureSize( 256, 128 );
		BackRightHoof2.addBox( -3.5F, 5.1F, -3F, 6, 3, 6);
		BackRightHoof2.setRotationPoint( -4.499999F + 4F, 16.13673F + 1F, 15.5F -14F );

		BackRightFluff = new ModelRenderer( this, 74, 49 );
		BackRightFluff.setTextureSize( 256, 128 );
		BackRightFluff.addBox( -1F, -1.5F, -2F, 1, 9, 4);
		BackRightFluff.setRotationPoint( -3.999999F + 4F, 15.16499F  + 1F, 18.3588F -14F);

		BackRightFluffb = new ModelRenderer( this, 74, 49 );
		BackRightFluffb.setTextureSize( 256, 128 );
		BackRightFluffb.addBox( -1F, -1.5F, -2F, 1, 9, 4);
		BackRightFluffb.setRotationPoint( -4.99974F + 4F, 15.18772F  + 1F, 18.3578F -14F);

		FrontLeftMuscle = new ModelRenderer( this, 60, 2 );
		FrontLeftMuscle.setTextureSize( 256, 128 );
		FrontLeftMuscle.addBox( -4F, -6F, -6F, 7, 12, 12);
		FrontLeftMuscle.setRotationPoint( 5F, -1F, -8F );

		FrontLeftA = new ModelRenderer( this, 1, 67 );
		FrontLeftA.setTextureSize( 256, 128 );
		FrontLeftA.addBox( -3F, 0.5F, -3F, 5, 9, 6);
		FrontLeftA.setRotationPoint( 5.5F - 5F, 3F + 1F, -9F + 8F);

		FrontLeftB = new ModelRenderer( this, 77, 72 );
		FrontLeftB.setTextureSize( 256, 128 );
		FrontLeftB.addBox( -2.5F, -1F, -2F, 4, 8, 4);
		FrontLeftB.setRotationPoint( 5.5F - 5F, 12.84808F + 1F, -9F+ 8F );

		FrontLeftHoof = new ModelRenderer( this, 75, 84 );
		FrontLeftHoof.setTextureSize( 256, 128 );
		FrontLeftHoof.addBox( -3F, 5.1F, -2.5F, 5, 3, 5);
		FrontLeftHoof.setRotationPoint( 5.5F - 5F, 13.98481F + 1F, -9F + 8F);

		FrontLeftHoof2 = new ModelRenderer( this, 73, 92 );
		FrontLeftHoof2.setTextureSize( 256, 128 );
		FrontLeftHoof2.addBox( -3.5F, 5.1F, -3F, 6, 3, 6);
		FrontLeftHoof2.setRotationPoint( 5.5F - 5F, 15.98481F + 1F, -9F+ 8F );

		FrontLeftFluff = new ModelRenderer( this, 74, 49 );
		FrontLeftFluff.setTextureSize( 256, 128 );
		FrontLeftFluff.addBox( -1F, -1.5F, -2F, 1, 9, 4);
		FrontLeftFluff.setRotationPoint( 6F - 5F, 15.16499F + 1F, -6F + 8F);

		FrontLeftFluffb = new ModelRenderer( this, 74, 49 );
		FrontLeftFluffb.setTextureSize( 256, 128 );
		FrontLeftFluffb.addBox( -1F, -1.5F, -2F, 1, 9, 4);
		FrontLeftFluffb.setRotationPoint( 5.015076F - 5F, 15.18012F + 1F, -6.172327F + 8F);

		FrontRightMuscle = new ModelRenderer( this, 60, 2 );
		FrontRightMuscle.setTextureSize( 256, 128 );
		FrontRightMuscle.addBox( -4F, -6F, -6F, 7, 12, 12);
		FrontRightMuscle.setRotationPoint( -4F, -1F, -8F );

		FrontRightA = new ModelRenderer( this, 23, 67 );
		FrontRightA.setTextureSize( 256, 128 );
		FrontRightA.addBox( -3F, 0.5F, -3F, 5, 9, 6);
		FrontRightA.setRotationPoint( -4.5F + 4F, 3F + 1F, -9F + 8F );

		FrontRightB = new ModelRenderer( this, 93, 72 );
		FrontRightB.setTextureSize( 256, 128 );
		FrontRightB.addBox( -2.5F, -1F, -2F, 4, 8, 4);
		FrontRightB.setRotationPoint( -4.5F + 4F, 12.84808F + 1F, -9F + 8F );

		FrontRightHoof = new ModelRenderer( this, 75, 84 );
		FrontRightHoof.setTextureSize( 256, 128 );
		FrontRightHoof.addBox( -3F, 5.1F, -2.5F, 5, 3, 5);
		FrontRightHoof.setRotationPoint( -4.5F + 4F, 13.98481F + 1F, -9F + 8F );

		FrontRightHoof2 = new ModelRenderer( this, 73, 92 );
		FrontRightHoof2.setTextureSize( 256, 128 );
		FrontRightHoof2.addBox( -3.5F, 5.1F, -3F, 6, 3, 6);
		FrontRightHoof2.setRotationPoint( -4.5F + 4F, 15.98481F + 1F, -9F + 8F );

		FrontRightFluff = new ModelRenderer( this, 74, 49 );
		FrontRightFluff.setTextureSize( 256, 128 );
		FrontRightFluff.addBox( -1F, -1.5F, -2F, 1, 9, 4);
		FrontRightFluff.setRotationPoint( -3.999999F + 4F, 15.16499F + 1F, -6F  + 8F );

		FrontRightFluffb = new ModelRenderer( this, 74, 49 );
		FrontRightFluffb.setTextureSize( 256, 128 );
		FrontRightFluffb.addBox( -1F, -1.5F, -2F, 1, 9, 4);
		FrontRightFluffb.setRotationPoint( -4.999883F + 4F, 15.18012F + 1F, -5.998679F  + 8F );


		HeadNode = new ModelRenderer( this, 87, 46 );
		HeadNode.setTextureSize( 256, 128 );
		HeadNode.addBox(0F, 0F, 0F, 0, 0, 0);
		HeadNode.setRotationPoint( 0F, -7F, -9F );

		Neck = new ModelRenderer( this, 87, 46 );
		Neck.setTextureSize( 256, 128 );
		Neck.addBox( -4.05F, -14F, -5F, 8, 14, 10);
		Neck.setRotationPoint( 0F, 0F, 0F );

		Head = new ModelRenderer( this, 0, 46 );
		Head.setTextureSize( 256, 128 );
		Head.addBox( -4F, -4F, -6F, 8, 8, 12);
		Head.setRotationPoint( 4.842877E-08F, -18.87436F +7F, -16.43301F + 9F);

		Nose = new ModelRenderer( this, 28, 46 );
		Nose.setTextureSize( 256, 128 );
		Nose.addBox( -3.5F, -2.5F, -6F, 7, 5, 6);
		Nose.setRotationPoint( 4.842877E-08F, -17.4234F +7F, -21.94615F + 9F);

		Mouth = new ModelRenderer( this, 40, 57 );
		Mouth.setTextureSize( 256, 128 );
		Mouth.addBox( -3F, -0.5F, -6F, 6, 3, 6);
		Mouth.setRotationPoint( 4.842877E-08F, -16.15272F +7F, -20.32277F + 9F);

		Cheek = new ModelRenderer( this, 94, 29 );
		Cheek.setTextureSize( 256, 128 );
		Cheek.addBox( -1F, -3.5F, -4.5F, 2, 7, 9);
		Cheek.setRotationPoint( 4F, -15.30978F +7F, -16.10705F  + 9F);

		Cheek2 = new ModelRenderer( this, 94, 29 );
		Cheek2.setTextureSize( 256, 128 );
		Cheek2.addBox( -1F, -3.5F, -4.5F, 2, 7, 9);
		Cheek2.setRotationPoint( -4F, -15.30978F +7F, -16.10705F + 9F);

		EarR = new ModelRenderer( this, 1, 25 );
		EarR.setTextureSize( 256, 128 );
		EarR.addBox( -1.5F, -4F, -0.5F, 3, 4, 1);
		EarR.setRotationPoint( -2.5F, -23.40545F +7F, -15.58494F  + 9F);

		EarL = new ModelRenderer( this, 10, 25 );
		EarL.setTextureSize( 256, 128 );
		EarL.addBox( -1.5F, -4F, -0.5F, 3, 4, 1);
		EarL.setRotationPoint( 2.5F, -23.40545F +7F, -15.58494F  + 9F);

		Fleco = new ModelRenderer( this, 88, 113 );
		Fleco.setTextureSize( 256, 128 );
		Fleco.addBox( -0.5F, -0.5F, -4F, 1, 1, 8);
		Fleco.setRotationPoint( 0.5000001F, -24.77147F +7F, -15.21891F  + 9F);

		Fleco2 = new ModelRenderer( this, 104, 116 );
		Fleco2.setTextureSize( 256, 128 );
		Fleco2.addBox( -0.5F, -0.5F, -3.5F, 1, 1, 7);
		Fleco2.setRotationPoint( -0.4999999F, -25.02147F +7F, -14.7859F + 9F);

		Fleco3 = new ModelRenderer( this, 103, 118 );
		Fleco3.setTextureSize( 256, 128 );
		Fleco3.addBox( -0.5F, -0.5F, -3.5F, 1, 1, 7);
		Fleco3.setRotationPoint( 1.5F, -25.02147F +7F, -14.7859F  + 9F);

		Fleco4 = new ModelRenderer( this, 92, 114 );
		Fleco4.setTextureSize( 256, 128 );
		Fleco4.addBox( -0.5F, -0.5F, -3F, 1, 1, 6);
		Fleco4.setRotationPoint( -1.5F, -25.27147F +7F, -14.35288F + 9F);

		Mane = new ModelRenderer( this, 65, 101 );
		Mane.setTextureSize( 256, 128 );
		Mane.addBox( -1.5F, -10F, -2.5F, 3, 20, 5);
		Mane.setRotationPoint( 4.842877E-08F, -16.9282F +7F, -7.803848F + 9F);

		Block1 = new ModelRenderer( this, 46, 0 );
		Block1.setTextureSize( 256, 128 );
		Block1.addBox( -2F, -2F, -1.5F, 4, 4, 3);
		Block1.setRotationPoint( 0F, 6F, 16F );

		Block2 = new ModelRenderer( this, 46, 13 );
		Block2.setTextureSize( 256, 128 );
		Block2.addBox( -1F, -2F, -3F, 2, 4, 6);
		Block2.setRotationPoint( 0F, 4.5F, 12F );

		Reins1 = new ModelRenderer( this, 128, 2 );
		Reins1.setTextureSize( 256, 128 );
		Reins1.addBox( -5.5F, -5F, -6.5F, 11, 10, 13);
		Reins1.setRotationPoint( 4.842877E-08F, -18.69135F + 7F, -15.75F + 9F);

		SaddleBase = new ModelRenderer( this, 0, 82 );
		SaddleBase.setTextureSize( 256, 128 );
		SaddleBase.addBox( -7.5F, -0.5F, -10F, 15, 1, 20);
		SaddleBase.setRotationPoint( 0F, -10F, 8F );
		SaddleBase2 = new ModelRenderer( this, 0, 82 );
		SaddleBase2.setTextureSize( 256, 128 );
		SaddleBase2.addBox( -0.5F, -0.5F, -10F, 1, 7, 20);
		SaddleBase2.setRotationPoint( 7F, -9F, 8F );
		SaddleBase3 = new ModelRenderer( this, 0, 82 );
		SaddleBase3.setTextureSize( 256, 128 );
		SaddleBase3.addBox( -0.5F, -0.5F, -10F, 1, 7, 20);
		SaddleBase3.setRotationPoint( -7F, -9F, 8F );
		Saddle = new ModelRenderer( this, 0, 114 );
		Saddle.setTextureSize( 256, 128 );
		Saddle.addBox( -8F, -0.5F, -6.5F, 16, 1, 13);
		Saddle.setRotationPoint( 0F, -11F, 6F );
		Saddle2 = new ModelRenderer( this, 12, 123 );
		Saddle2.setTextureSize( 256, 128 );
		Saddle2.addBox( -6.5F, -1F, -1F, 13, 2, 2);
		Saddle2.setRotationPoint( 0F, -11.5F, 13.5F );
		Saddle3 = new ModelRenderer( this, 4, 113 );
		Saddle3.setTextureSize( 256, 128 );
		Saddle3.addBox( -0.5F, -0.5F, -4.5F, 1, 5, 9);
		Saddle3.setRotationPoint( 7.5F, -10.5F, 6F );
		Saddle4 = new ModelRenderer( this, 20, 119 );
		Saddle4.setTextureSize( 256, 128 );
		Saddle4.addBox( -0.5F, -1F, -3F, 1, 2, 6);
		Saddle4.setRotationPoint( 8.923505F, -5.187408F, 6F );
		Saddle5 = new ModelRenderer( this, 17, 113 );
		Saddle5.setTextureSize( 256, 128 );
		Saddle5.addBox( -0.5F, -0.5F, -4.5F, 1, 5, 9);
		Saddle5.setRotationPoint( -7.5F, -10.5F, 6F );
		Saddle6 = new ModelRenderer( this, 23, 118 );
		Saddle6.setTextureSize( 256, 128 );
		Saddle6.addBox( -0.5F, -1F, -3F, 1, 2, 6);
		Saddle6.setRotationPoint( -8.923504F, -5.187408F, 6F );
		Footstrap = new ModelRenderer( this, 11, 120 );
		Footstrap.setTextureSize( 256, 128 );
		Footstrap.addBox( -0.5F, 0F, -1F, 1, 6, 2);
		Footstrap.setRotationPoint( 8F, -6.5F, 6F );
		foot1 = new ModelRenderer( this, 2, 89 );
		foot1.setTextureSize( 256, 128 );
		foot1.addBox( -0.5F, -0.5F, -2F, 1, 1, 4);
		foot1.setRotationPoint( 9.682323F, -0.2214813F, 6F );
		foot2 = new ModelRenderer( this, 2, 89 );
		foot2.setTextureSize( 256, 128 );
		foot2.addBox( -0.5F, -0.5F, -2F, 1, 1, 4);
		foot2.setRotationPoint( 9.682324F, 2.778519F, 6F );
		foot3 = new ModelRenderer( this, 5, 92 );
		foot3.setTextureSize( 256, 128 );
		foot3.addBox( -0.5F, -1F, -0.5F, 1, 2, 1);
		foot3.setRotationPoint( 9.682323F, 1.278519F, 4.5F );
		foot4 = new ModelRenderer( this, 5, 92 );
		foot4.setTextureSize( 256, 128 );
		foot4.addBox( -0.5F, -1F, -0.5F, 1, 2, 1);
		foot4.setRotationPoint( 9.682323F, 1.278519F, 7.5F );
		Footstrap2 = new ModelRenderer( this, 11, 120 );
		Footstrap2.setTextureSize( 256, 128 );
		Footstrap2.addBox( -0.5F, 0F, -1F, 1, 6, 2);
		Footstrap2.setRotationPoint( -8F, -6.5F, 6F );
		foot1a = new ModelRenderer( this, 2, 89 );
		foot1a.setTextureSize( 256, 128 );
		foot1a.addBox( -0.5F, -0.5F, -2F, 1, 1, 4);
		foot1a.setRotationPoint( -9.682323F, -0.2214813F, 6F );
		foot2a = new ModelRenderer( this, 2, 89 );
		foot2a.setTextureSize( 256, 128 );
		foot2a.addBox( -0.5F, -0.5F, -2F, 1, 1, 4);
		foot2a.setRotationPoint( -9.682323F, 2.778519F, 6F );
		foot3a = new ModelRenderer( this, 5, 92 );
		foot3a.setTextureSize( 256, 128 );
		foot3a.addBox( -0.5F, -1F, -0.5F, 1, 2, 1);
		foot3a.setRotationPoint( -9.682323F, 1.278519F, 4.5F );
		foot4a = new ModelRenderer( this, 5, 92 );
		foot4a.setTextureSize( 256, 128 );
		foot4a.addBox( -0.5F, -1F, -0.5F, 1, 2, 1);
		foot4a.setRotationPoint( -9.682323F, 1.278519F, 7.5F );
		Saddle7 = new ModelRenderer( this, 11, 110 );
		Saddle7.setTextureSize( 256, 128 );
		Saddle7.addBox( -5.5F, -0.5F, -1F, 11, 1, 2);
		Saddle7.setRotationPoint( 0F, -11F, 15.5F );
		SaddleHump = new ModelRenderer( this, 2, 111 );
		SaddleHump.setTextureSize( 256, 128 );
		SaddleHump.addBox( -3F, -1.5F, -1.5F, 6, 3, 3);
		SaddleHump.setRotationPoint( 0F, -12F, 0F );
		SaddleHump2 = new ModelRenderer( this, 2, 113 );
		SaddleHump2.setTextureSize( 256, 128 );
		SaddleHump2.addBox( -2F, -1F, -2F, 4, 2, 4);
		SaddleHump2.setRotationPoint( 0F, -14.49329F, -0.1829692F );
		Strap1 = new ModelRenderer( this, 10, 124 );
		Strap1.setTextureSize( 256, 128 );
		Strap1.addBox( -8F, -0.5F, -1.5F, 16, 1, 3);
		Strap1.setRotationPoint( 0F, 4.5F, 6F );
		Strap2 = new ModelRenderer( this, 40, 110 );
		Strap2.setTextureSize( 256, 128 );
		Strap2.addBox( -0.5F, -4.5F, -1.5F, 1, 9, 3);
		Strap2.setRotationPoint( -7.5F, -0.5F, 6F );
		Strap3 = new ModelRenderer( this, 41, 110 );
		Strap3.setTextureSize( 256, 128 );
		Strap3.addBox( -0.5F, -4.5F, -1.5F, 1, 9, 3);
		Strap3.setRotationPoint( 7.5F, -0.5F, 6F );

		this.HeadNode.addChild(this.Neck);
		this.HeadNode.addChild(this.Head);
		this.HeadNode.addChild(this.Nose);
		this.HeadNode.addChild(this.Mouth);
		//this.HeadNode.addChild(this.Cheek);
		//this.HeadNode.addChild(this.Cheek2);
		this.HeadNode.addChild(this.EarL);
		this.HeadNode.addChild(this.EarR);
		this.HeadNode.addChild(this.Fleco);
		this.HeadNode.addChild(this.Fleco2);
		this.HeadNode.addChild(this.Fleco3);
		this.HeadNode.addChild(this.Fleco4);
		this.HeadNode.addChild(this.Mane);

		this.BackLeftMuscle.addChild(this.BackLeftA);
		this.BackLeftMuscle.addChild(this.BackLeftB);
		this.BackLeftMuscle.addChild(this.BackLeftHoof);
		this.BackLeftMuscle.addChild(this.BackLeftHoof2);
		this.BackLeftMuscle.addChild(this.BackLeftFluff);
		this.BackLeftMuscle.addChild(this.BackLeftFluffb);

		this.BackRightMuscle.addChild(this.BackRightA);
		this.BackRightMuscle.addChild(this.BackRightB);
		this.BackRightMuscle.addChild(this.BackRightHoof);
		this.BackRightMuscle.addChild(this.BackRightHoof2);
		this.BackRightMuscle.addChild(this.BackRightFluff);
		this.BackRightMuscle.addChild(this.BackRightFluffb);

		this.FrontLeftMuscle.addChild(this.FrontLeftA);
		this.FrontLeftMuscle.addChild(this.FrontLeftB);
		this.FrontLeftMuscle.addChild(this.FrontLeftHoof);
		this.FrontLeftMuscle.addChild(this.FrontLeftHoof2);
		this.FrontLeftMuscle.addChild(this.FrontLeftFluff);
		this.FrontLeftMuscle.addChild(this.FrontLeftFluffb);

		this.FrontRightMuscle.addChild(this.FrontRightA);
		this.FrontRightMuscle.addChild(this.FrontRightB);
		this.FrontRightMuscle.addChild(this.FrontRightHoof);
		this.FrontRightMuscle.addChild(this.FrontRightHoof2);
		this.FrontRightMuscle.addChild(this.FrontRightFluff);
		this.FrontRightMuscle.addChild(this.FrontRightFluffb);

		this.TailNode.addChild(this.tailA);
		this.TailNode.addChild(this.tailB);
		this.TailNode.addChild(this.tailC);
		this.TailNode.addChild(this.tailD);

	}


	public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale)
	{

		EntityAnimaniaHorse eh = (EntityAnimaniaHorse) entityIn;

		tailA.rotateAngleX = -1.105676F;
		tailB.rotateAngleX = -1.105676F;
		tailC.rotateAngleX = -1.367476F;
		tailD.rotateAngleX = -1.507424F;
		BackLeftA.rotateAngleX = 0.1745329F;
		BackLeftB.rotateAngleX = -0.1745329F;
		BackLeftHoof.rotateAngleX = 4.00787E-08F;
		BackLeftHoof2.rotateAngleX = 4.007869E-08F;
		BackLeftFluff.rotateAngleX = 0.08790723F;
		BackLeftFluff.rotateAngleY = 0.08461326F;
		BackLeftFluff.rotateAngleZ = -0.01519369F;
		BackLeftFluffb.rotateAngleX = 0.08393065F;
		BackLeftFluffb.rotateAngleY = -0.09052216F;
		BackLeftFluffb.rotateAngleZ = -0.0302609F;
		BackRightA.rotateAngleX = 0.1745329F;
		BackRightB.rotateAngleX = -0.1745329F;
		BackRightHoof.rotateAngleX = 4.00787E-08F;
		BackRightHoof2.rotateAngleX = 4.007869E-08F;
		BackRightFluff.rotateAngleX = 0.08790723F;
		BackRightFluff.rotateAngleY = 0.08461326F;
		BackRightFluff.rotateAngleZ = -0.01519369F;
		BackRightFluffb.rotateAngleX = 0.08393065F;
		BackRightFluffb.rotateAngleY = -0.09052216F;
		BackRightFluffb.rotateAngleZ = -0.0302609F;
		FrontLeftFluff.rotateAngleX = 0.1745329F;
		FrontLeftFluff.rotateAngleY = -0.08726647F;
		FrontLeftFluff.rotateAngleZ = 1.599395E-10F;
		FrontLeftFluffb.rotateAngleX = 0.1718547F;
		FrontLeftFluffb.rotateAngleY = -0.2644362F;
		FrontLeftFluffb.rotateAngleZ = -0.03060928F;
		FrontRightFluff.rotateAngleX = 0.1745329F;
		FrontRightFluff.rotateAngleY = 0.08726647F;
		FrontRightFluff.rotateAngleZ = -1.599395E-10F;
		FrontRightFluffb.rotateAngleX = 0.1718547F;
		FrontRightFluffb.rotateAngleY = -0.08990332F;
		FrontRightFluffb.rotateAngleZ = -0.03060928F;

		Neck.rotateAngleX = 0.5235988F;
		Head.rotateAngleX = 0.5235988F;
		Nose.rotateAngleX =  0.6616941F;
		Mouth.rotateAngleX = 0.836227F;
		Cheek2.rotateAngleX = 1.489178E-08F;
		EarR.rotateAngleX = 0.5040353F;
		EarR.rotateAngleY = -0.3000592F;
		EarR.rotateAngleZ = -0.4101312F;
		EarL.rotateAngleX = 0.5040353F;
		EarL.rotateAngleY = 0.3000592F;
		EarL.rotateAngleZ = 0.4101312F;
		Fleco.rotateAngleX = 0.5235988F;
		Fleco2.rotateAngleX = 0.5235988F;
		Fleco3.rotateAngleX = 0.5235988F;
		Fleco4.rotateAngleX = 0.5235988F;
		Mane.rotateAngleX = 0.5235988F;
		Block2.rotateAngleX = 0.2617994F;
		
		SaddleBase2.rotateAngleZ = -0.2617994F;
		SaddleBase3.rotateAngleZ = 0.2617994F;
		Saddle3.rotateAngleZ = -0.2617994F;
		Saddle4.rotateAngleZ = -0.2617994F;
		Saddle5.rotateAngleZ = 0.2617994F;
		Saddle6.rotateAngleZ = 0.2617994F;
		Footstrap.rotateAngleZ = -0.2617994F;
		Footstrap2.rotateAngleZ = 0.2617994F;
		SaddleHump.rotateAngleX = 0.07325316F;
		SaddleHump2.rotateAngleX = 0.1765722F;

		this.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entityIn);

		
		
		
		EntityAnimaniaHorse ech = (EntityAnimaniaHorse) entityIn;
		if (ech.getSleeping()) {
			float sleepTimer = ech.getSleepTimer();
			this.FrontLeftMuscle.rotateAngleX = sleepTimer * -1.8F;
			this.FrontLeftMuscle.render(scale * .95F);
			this.FrontRightMuscle.rotateAngleX = sleepTimer * -1.8F;
			this.FrontRightMuscle.render(scale * .97F);
			this.BackLeftMuscle.rotateAngleX = sleepTimer * 1.7F;
			this.BackLeftMuscle.render(scale * .97F);
			this.BackRightMuscle.rotateAngleX = sleepTimer * 1.75F;
			this.BackRightMuscle.render(scale * .95F);
			this.HeadNode.rotateAngleY = sleepTimer * - 2.8F;

			if (sleepTimer > -.28) {
				this.Body.rotateAngleX = - (sleepTimer/3);
			} else {
				this.Body.rotateAngleX = (sleepTimer/3);
			}

		} else {

			this.BackLeftMuscle.rotateAngleZ = 0;
			this.BackLeftMuscle.render(scale);
			this.BackRightMuscle.rotateAngleZ = 0;
			this.BackRightMuscle.render(scale);
			this.FrontLeftMuscle.rotateAngleZ = 0;
			this.FrontLeftMuscle.render(scale);
			this.FrontRightMuscle.rotateAngleZ = 0;
			this.FrontRightMuscle.render(scale);
			this.HeadNode.rotateAngleY = 0F;
			this.Body.rotateAngleX = 0;
			

		}
		
		Body.render(scale);
		TailNode.render(scale);

		BackLeftMuscle.render(scale);
		BackRightMuscle.render(scale);
		FrontLeftMuscle.render(scale);
		FrontRightMuscle.render(scale);

		HeadNode.render(scale);
		
		if (AnimaniaConfig.gameRules.showParts) {
			Block1.render(scale);
			Block2.render(scale);
		}

		if (eh.isHorseSaddled()) {
			SaddleBase.render(scale);
			SaddleBase2.render(scale);
			SaddleBase3.render(scale);
			Saddle.render(scale);
			Saddle2.render(scale);
			Saddle3.render(scale);
			Saddle4.render(scale);
			Saddle5.render(scale);
			Saddle6.render(scale);
			Footstrap.render(scale);
			foot1.render(scale);
			foot2.render(scale);
			foot3.render(scale);
			foot4.render(scale);
			Footstrap2.render(scale);
			foot1a.render(scale);
			foot2a.render(scale);
			foot3a.render(scale);
			foot4a.render(scale);
			Saddle7.render(scale);
			SaddleHump.render(scale);
			SaddleHump2.render(scale);
			Strap1.render(scale);
			Strap2.render(scale);
			Strap3.render(scale);
		}


	}


	@Override
	public void setLivingAnimations(LivingEntity LivingEntityIn, float p_78086_2_, float p_78086_3_, float partialTickTime)
	{
		super.setLivingAnimations(LivingEntityIn, p_78086_2_, p_78086_3_, partialTickTime);

		Entity horse = (Entity)LivingEntityIn;

		if (!horse.isBeingRidden()) {
			this.HeadNode.rotationPointY = -7.0F + ((EntityStallionDraftHorse)LivingEntityIn).getHeadAnchorPointY(partialTickTime) * 10.0F;
			this.headRotationAngleX = ((EntityStallionDraftHorse)LivingEntityIn).getHeadAngleX(partialTickTime);
		}
	}

	public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, Entity scale)
	{

		this.HeadNode.rotateAngleX = par5 / (180F / (float)Math.PI);
		this.HeadNode.rotateAngleY = par4 / (180F / (float)Math.PI);
		this.HeadNode.rotateAngleX = this.headRotationAngleX;

		EntityStallionDraftHorse HorseEntity = (EntityStallionDraftHorse) scale;
		if (HorseEntity.eatTimer > 4 && HorseEntity.eatTimer < 160) {
			this.HeadNode.rotateAngleX = .687F + this.headRotationAngleX;
		} else {
			this.HeadNode.rotateAngleX = this.headRotationAngleX;
		}

		this.BackLeftMuscle.rotateAngleX = MathHelper.cos(par1 * 0.6662F) * 1.0F * par2;
		this.BackRightMuscle.rotateAngleX = MathHelper.cos(par1 * 0.6662F + (float)Math.PI) * 1.0F * par2;
		this.FrontLeftMuscle.rotateAngleX = MathHelper.cos(par1 * 0.6662F + (float)Math.PI) * 1.0F * par2;
		this.FrontRightMuscle.rotateAngleX = MathHelper.cos(par1 * 0.6662F) * 1.0F * par2;
	}
}
