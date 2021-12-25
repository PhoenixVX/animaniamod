package com.animania.addons.catsdogs.client.models.dogs.poses;

import com.animania.client.models.render.ModelRendererAnimania;

import net.minecraft.client.model.ModelBase;

public class PoseDachshundSleeping extends ModelBase
{
	public static final PoseDachshundSleeping INSTANCE = new PoseDachshundSleeping();
	ModelRendererAnimania body;
	ModelRendererAnimania leg1;
	ModelRendererAnimania toe2;
	ModelRendererAnimania leg2;
	ModelRendererAnimania toe21;
	ModelRendererAnimania leg3;
	ModelRendererAnimania toe1;
	ModelRendererAnimania leg4;
	ModelRendererAnimania toe211;
	ModelRendererAnimania head;
	ModelRendererAnimania wolf_head;
	ModelRendererAnimania ear1;
	ModelRendererAnimania ear2;
	ModelRendererAnimania nose;
	ModelRendererAnimania tail;

	public PoseDachshundSleeping()
	{
		this.body = new ModelRendererAnimania(this, 25, 0);
		this.body.setTextureSize(128, 64);
		this.body.addBox(-2.0F, -2.0F, -6.0F, 4, 4, 12);
		this.body.setRotationPoint(0.0F, 23.0F, -0.0F);
		this.body.setOffset(0.0F, -0.0F, -0.0F);
		this.leg1 = new ModelRendererAnimania(this, 0, 18);
		this.leg1.setTextureSize(128, 64);
		this.leg1.addBox(-1.0F, -2.0F, -1.0F, 2, 4, 2);
		this.leg1.setRotationPoint(-1.5F, 0.0F, 5.0F);
		this.leg1.setOffset(0.0F, 2.0F, -0.0F);
		this.toe2 = new ModelRendererAnimania(this, 0, 22);
		this.toe2.setTextureSize(128, 64);
		this.toe2.addBox(-1.0F, -0.5F, -0.5F, 2, 1, 1);
		this.toe2.setRotationPoint(0.0F, -2.0F, -0.0F);
		this.toe2.setOffset(0.0F, 3.5F, -1.0F);
		this.leg2 = new ModelRendererAnimania(this, 0, 18);
		this.leg2.setTextureSize(128, 64);
		this.leg2.addBox(-1.0F, -2.0F, -1.0F, 2, 4, 2);
		this.leg2.setRotationPoint(1.5F, 0.0F, 5.0F);
		this.leg2.setOffset(0.0F, 2.0F, -0.0F);
		this.toe21 = new ModelRendererAnimania(this, 0, 22);
		this.toe21.setTextureSize(128, 64);
		this.toe21.addBox(-1.0F, -0.5F, -0.5F, 2, 1, 1);
		this.toe21.setRotationPoint(0.0F, -2.0F, -0.0F);
		this.toe21.setOffset(0.0F, 3.5F, -1.0F);
		this.leg3 = new ModelRendererAnimania(this, 0, 18);
		this.leg3.setTextureSize(128, 64);
		this.leg3.addBox(-1.0F, -2.0F, -1.0F, 2, 4, 2);
		this.leg3.setRotationPoint(-1.5F, 0.0F, -5.0F);
		this.leg3.setOffset(0.0F, 2.0F, -0.0F);
		this.toe1 = new ModelRendererAnimania(this, 0, 22);
		this.toe1.setTextureSize(128, 64);
		this.toe1.addBox(-1.0F, -0.5F, -0.5F, 2, 1, 1);
		this.toe1.setRotationPoint(0.0F, -2.0F, -0.0F);
		this.toe1.setOffset(0.0F, 3.5F, -1.0F);
		this.leg4 = new ModelRendererAnimania(this, 0, 18);
		this.leg4.setTextureSize(128, 64);
		this.leg4.addBox(-1.0F, -2.0F, -1.0F, 2, 4, 2);
		this.leg4.setRotationPoint(1.5F, 0.0F, -5.0F);
		this.leg4.setOffset(0.0F, 2.0F, -0.0F);
		this.toe211 = new ModelRendererAnimania(this, 0, 22);
		this.toe211.setTextureSize(128, 64);
		this.toe211.addBox(-1.0F, -0.5F, -0.5F, 2, 1, 1);
		this.toe211.setRotationPoint(0.0F, -2.0F, -0.0F);
		this.toe211.setOffset(0.0F, 3.5F, -1.0F);
		this.head = new ModelRendererAnimania(this, 16, 16);
		this.head.setTextureSize(128, 64);
		this.head.addBox(-0.0F, -0.0F, -0.0F, 0, 0, 0);
		this.head.setRotationPoint(0.0F, -1.0F, -6.0F);
		this.head.setOffset(0.0F, -0.0F, -0.0F);
		this.wolf_head = new ModelRendererAnimania(this, 0, 0);
		this.wolf_head.setTextureSize(128, 64);
		this.wolf_head.addBox(-2.0F, -2.0F, -2.0F, 4, 4, 4);
		this.wolf_head.setRotationPoint(1.0F, 0.0F, 1.0F);
		this.wolf_head.setOffset(-1.0F, -1.0F, -2.0F);
		this.ear1 = new ModelRendererAnimania(this, 16, 14);
		this.ear1.setTextureSize(128, 64);
		this.ear1.addBox(-0.5F, -1.5F, -1.0F, 1, 3, 2);
		this.ear1.setRotationPoint(-2.0F, -2.0F, -0.0F);
		this.ear1.setOffset(-0.5F, 1.5F, -0.0F);
		this.ear2 = new ModelRendererAnimania(this, 16, 14);
		this.ear2.setTextureSize(128, 64);
		this.ear2.addBox(-0.5F, -1.5F, -1.0F, 1, 3, 2);
		this.ear2.setRotationPoint(2.0F, -2.0F, -0.0F);
		this.ear2.setOffset(0.5F, 1.5F, -0.0F);
		this.nose = new ModelRendererAnimania(this, 0, 10);
		this.nose.setTextureSize(128, 64);
		this.nose.addBox(-1.0F, -1.0F, -1.5F, 2, 2, 3);
		this.nose.setRotationPoint(1.0F, 0.0F, 1.0F);
		this.nose.setOffset(-1.0F, 1.0F, -3.5F);
		this.tail = new ModelRendererAnimania(this, 9, 18);
		this.tail.setTextureSize(128, 64);
		this.tail.addBox(-0.5F, -2.5F, -0.5F, 1, 5, 1);
		this.tail.setRotationPoint(-0.5F, -1.0F, 5.0F);
		this.tail.setOffset(0.5F, 2.5F, -0.5F);
		this.leg1.addChild(this.toe2);
		this.body.addChild(this.leg1);
		this.leg2.addChild(this.toe21);
		this.body.addChild(this.leg2);
		this.leg3.addChild(this.toe1);
		this.body.addChild(this.leg3);
		this.leg4.addChild(this.toe211);
		this.body.addChild(this.leg4);
		this.wolf_head.addChild(this.ear1);
		this.wolf_head.addChild(this.ear2);
		this.wolf_head.addChild(this.nose);
		this.head.addChild(this.wolf_head);
		this.body.addChild(this.head);
		this.body.addChild(this.tail);
		this.setupAngles();
	}

	public void setupAngles()
	{
		this.body.rotateAngleX = 0.0F;
		this.body.rotateAngleY = 0.0F;
		this.body.rotateAngleZ = 0.0F;
		this.leg1.rotateAngleX = -1.2459905529987518F;
		this.leg1.rotateAngleY = 0.7487462491055673F;
		this.leg1.rotateAngleZ = 0.0F;
		this.toe2.rotateAngleX = 0.0F;
		this.toe2.rotateAngleY = 0.0F;
		this.toe2.rotateAngleZ = 0.0F;
		this.leg2.rotateAngleX = -1.300793891511374F;
		this.leg2.rotateAngleY = -0.6234298634831226F;
		this.leg2.rotateAngleZ = 0.0F;
		this.toe21.rotateAngleX = 0.0F;
		this.toe21.rotateAngleY = 0.0F;
		this.toe21.rotateAngleZ = 0.0F;
		this.leg3.rotateAngleX = -1.1978194656437082F;
		this.leg3.rotateAngleY = 0.7908086840786308F;
		this.leg3.rotateAngleZ = 0.0F;
		this.toe1.rotateAngleX = 0.0F;
		this.toe1.rotateAngleY = 0.0F;
		this.toe1.rotateAngleZ = 0.0F;
		this.leg4.rotateAngleX = -1.3425072606340382F;
		this.leg4.rotateAngleY = -1.4800374603619393F;
		this.leg4.rotateAngleZ = 0.0F;
		this.toe211.rotateAngleX = -1.7453292519943296E-6F;
		this.toe211.rotateAngleY = 0.0F;
		this.toe211.rotateAngleZ = 0.0F;
		this.head.rotateAngleX = 0.0F;
		this.head.rotateAngleY = 0.0F;
		this.head.rotateAngleZ = 0.0F;
		this.wolf_head.rotateAngleX = 0.5423889903252698F;
		this.wolf_head.rotateAngleY = -0.41134617942703156F;
		this.wolf_head.rotateAngleZ = -0.22148053674882842F;
		this.ear1.rotateAngleX = 0.0F;
		this.ear1.rotateAngleY = 0.0F;
		this.ear1.rotateAngleZ = 0.17453292519943295F;
		this.ear2.rotateAngleX = 0.0F;
		this.ear2.rotateAngleY = 0.0F;
		this.ear2.rotateAngleZ = -0.17453292519943295F;
		this.nose.rotateAngleX = 0.0F;
		this.nose.rotateAngleY = 0.0F;
		this.nose.rotateAngleZ = 0.0F;
		this.tail.rotateAngleX = 0.8482474697617641F;
		this.tail.rotateAngleY = 0.0F;
		this.tail.rotateAngleZ = 0.0F;
	}

}
