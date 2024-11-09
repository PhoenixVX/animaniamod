package com.animania.block.entity.renderer;

import com.animania.AnimaniaMod;
import com.animania.block.entity.NestBlockEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.minecraft.client.model.Model;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public class NestBlockEntityRenderer implements BlockEntityRenderer<NestBlockEntity> {
    public static final ModelLayerLocation NEST = new ModelLayerLocation(AnimaniaMod.getId("nest"), "main");
    public static final ResourceLocation NEST_BLUE_TEXTURE = AnimaniaMod.getId("textures/entity/nest_blue.png");
    public static final ResourceLocation NEST_WHITE_TEXTURE = AnimaniaMod.getId("textures/entity/nest_white.png");
    public final NestModel nestModel;

    public NestBlockEntityRenderer(BlockEntityRendererProvider.Context context) {
        this.nestModel = new NestModel(context.bakeLayer(NEST));
    }

    @Override
    public void render(@NotNull NestBlockEntity blockEntity, float partialTick, @NotNull PoseStack poseStack, @NotNull MultiBufferSource bufferSource, int packedLight, int packedOverlay) {
        poseStack.pushPose();
        poseStack.translate(0.5F, -0.5F, 0.5F);
        poseStack.rotateAround(Axis.XP.rotationDegrees(180.0F), 0.0F, 1.0F, 0.0F);
        VertexConsumer vertexConsumer = bufferSource.getBuffer(RenderType.entityCutoutNoCull(NEST_WHITE_TEXTURE));
        this.nestModel.renderToBuffer(poseStack, vertexConsumer, packedLight, packedOverlay);
        poseStack.popPose();
    }

    // Utility methods
    public static LayerDefinition createBodyLayer() {
        MeshDefinition mesh = new MeshDefinition();
        PartDefinition rootPart = mesh.getRoot();

        rootPart.addOrReplaceChild("nest1", CubeListBuilder.create().texOffs(0, 0).addBox(-3F, -2F, -8F, 6, 4, 3), PartPose.offset(0F, 22F, 0F));
        rootPart.addOrReplaceChild("fluff3", CubeListBuilder.create().texOffs(-16, 38).addBox(-8F, 0F, -8F, 16, 0, 16), PartPose.offset(0F, 21.5F, 0F));
        rootPart.addOrReplaceChild("fluff1", CubeListBuilder.create().texOffs(-16, 38).addBox(-8F, 0F, -8F, 16, 0, 16), PartPose.offset(0F, 22.5F, 0F));
        rootPart.addOrReplaceChild("nest2", CubeListBuilder.create().texOffs(0, 7).addBox(-3F, -2F, 5F, 6, 4, 3), PartPose.offset(0F, 22F, 0F));
        rootPart.addOrReplaceChild("nest3", CubeListBuilder.create().texOffs(0, 14).addBox(-3F, -2F, 5F, 6, 4, 3), PartPose.offset(0F, 22F, 0F));
        rootPart.addOrReplaceChild("nest4", CubeListBuilder.create().texOffs(19, 0).addBox(-3F, -2F, 5F, 6, 4, 3), PartPose.offset(0F, 22F, 0F));
        rootPart.addOrReplaceChild("nest5", CubeListBuilder.create().texOffs(18, 7).addBox(-3.5F, -2F, 5F, 7, 4, 3), PartPose.offset(0F, 21.9F, 0F));
        rootPart.addOrReplaceChild("nest6", CubeListBuilder.create().texOffs(18, 14).addBox(-3.5F, -2F, 5F, 7, 4, 3), PartPose.offset(0F, 21.9F, 0F));
        rootPart.addOrReplaceChild("nest7", CubeListBuilder.create().texOffs(18, 20).addBox(-3.5F, -2F, 5F, 7, 4, 3), PartPose.offset(0F, 21.9F, 0F));
        rootPart.addOrReplaceChild("nest8", CubeListBuilder.create().texOffs(41, 0).addBox(-3.5F, -2F, 5F, 7, 4, 3), PartPose.offset(0F, 21.9F, 0F));
        rootPart.addOrReplaceChild("block", CubeListBuilder.create().texOffs(13, 8).addBox(-5.5F, -1.5F, -5.5F, 11, 3, 11), PartPose.offset(0F, 22.5F, 0F));
        rootPart.addOrReplaceChild("fluff2", CubeListBuilder.create().texOffs(-16, 38).addBox(-8F, 0F, -8F, 16, 0, 16), PartPose.offset(0F, 23.8F, 0F));
        rootPart.addOrReplaceChild("fluff4", CubeListBuilder.create().texOffs(18, 38).addBox(-8F, 0F, -8F, 16, 0, 16), PartPose.offset(0F, 19.7F, 0F));
        rootPart.addOrReplaceChild("fluff5", CubeListBuilder.create().texOffs(18, 38).addBox(-8F, 0F, -8F, 16, 0, 16), PartPose.offset(0F, 19.65F, 0F));

        return LayerDefinition.create(mesh, 128, 64);
    }

    public static final class NestModel extends Model {
        public final ModelPart root;

        public final ModelPart nest1;
        public final ModelPart fluff3;
        public final ModelPart fluff1;
        public final ModelPart nest2;
        public final ModelPart nest3;
        public final ModelPart nest4;
        public final ModelPart nest5;
        public final ModelPart nest6;
        public final ModelPart nest7;
        public final ModelPart nest8;
        public final ModelPart block;
        public final ModelPart fluff2;
        public final ModelPart fluff4;
        public final ModelPart fluff5;

        public NestModel(ModelPart root) {
            super(RenderType::entityCutoutNoCull);
            this.root = root;

            this.nest1 = this.root.getChild("nest1");
            this.fluff3 = this.root.getChild("fluff3");
            this.fluff1 = this.root.getChild("fluff1");
            this.nest2 = this.root.getChild("nest2");
            this.nest3 = this.root.getChild("nest3");
            this.nest4 = this.root.getChild("nest4");
            this.nest5 = this.root.getChild("nest5");
            this.nest6 = this.root.getChild("nest6");
            this.nest7 = this.root.getChild("nest7");
            this.nest8 = this.root.getChild("nest8");
            this.block = this.root.getChild("block");
            this.fluff2 = this.root.getChild("fluff2");
            this.fluff4 = this.root.getChild("fluff4");
            this.fluff5 = this.root.getChild("fluff5");
        }

        @Override
        public void renderToBuffer(@NotNull PoseStack poseStack, @NotNull VertexConsumer buffer, int packedLight, int packedOverlay, int color) {
            this.nest1.render(poseStack, buffer, packedLight, packedOverlay, color);
            this.fluff3.render(poseStack, buffer, packedLight, packedOverlay, color);
            this.fluff1.render(poseStack, buffer, packedLight, packedOverlay, color);
            this.nest2.render(poseStack, buffer, packedLight, packedOverlay, color);
            this.nest3.render(poseStack, buffer, packedLight, packedOverlay, color);
            this.nest4.render(poseStack, buffer, packedLight, packedOverlay, color);
            this.nest5.render(poseStack, buffer, packedLight, packedOverlay, color);
            this.nest6.render(poseStack, buffer, packedLight, packedOverlay, color);
            this.nest7.render(poseStack, buffer, packedLight, packedOverlay, color);
            this.nest8.render(poseStack, buffer, packedLight, packedOverlay, color);
            this.block.render(poseStack, buffer, packedLight, packedOverlay, color);
            this.fluff2.render(poseStack, buffer, packedLight, packedOverlay, color);
            this.fluff4.render(poseStack, buffer, packedLight, packedOverlay, color);
            this.fluff5.render(poseStack, buffer, packedLight, packedOverlay, color);
        }
    }
}
