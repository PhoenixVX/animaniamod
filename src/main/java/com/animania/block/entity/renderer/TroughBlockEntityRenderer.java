package com.animania.block.entity.renderer;

import com.animania.AnimaniaMod;
import com.animania.block.TroughBlock;
import com.animania.block.entity.TroughBlockEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.minecraft.client.model.Model;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

public class TroughBlockEntityRenderer implements BlockEntityRenderer<TroughBlockEntity> {
    public static final ResourceLocation TEXTURE = AnimaniaMod.getId("textures/entity/trough.png");
    public static final ModelLayerLocation TROUGH = new ModelLayerLocation(AnimaniaMod.getId("trough"), "main");

    public final TroughModel model;

    public TroughBlockEntityRenderer(BlockEntityRendererProvider.Context context) {
        model = new TroughModel(context.bakeLayer(TROUGH));
    }

    @Override
    public void render(@NotNull TroughBlockEntity blockEntity, float partialTick, @NotNull PoseStack poseStack, @NotNull MultiBufferSource bufferSource, int packedLight, int packedOverlay) {
        BlockState blockState = blockEntity.getBlockState();
        Direction direction = blockState.getValue(TroughBlock.FACING);

        poseStack.pushPose();

        switch (direction) {
            case SOUTH -> poseStack.translate(-0.50F, -0.75F, 0.5F);
            case NORTH -> {
                poseStack.rotateAround(Axis.YP.rotationDegrees(180.0F), 0.0F, 1.0F, 0.0F);
                poseStack.translate(-1.5F, -0.75F, -0.5F);
            }
            case EAST -> {
                poseStack.rotateAround(Axis.YP.rotationDegrees(270.0F), 0.0F, 1.0F, 0.0F);
                poseStack.translate(0.50F, -0.75F, -0.50F);
            }
            case WEST -> {
                poseStack.rotateAround(Axis.YP.rotationDegrees(90.0F), 0.0F, 1.0F, 0.0F);
                poseStack.translate(-0.50F, -0.75F, 0.5F);
            }
        }
        VertexConsumer vertexConsumer = bufferSource.getBuffer(RenderType.entityTranslucent(TEXTURE));
        this.model.renderToBuffer(poseStack, vertexConsumer, packedLight, packedOverlay);
        poseStack.popPose();
    }

    // Utility methods
    public static LayerDefinition createBodyLayer() {
        MeshDefinition mesh = new MeshDefinition();
        PartDefinition rootPart = mesh.getRoot();

        rootPart.addOrReplaceChild("block1", CubeListBuilder.create().texOffs(2, 2).addBox(-1F, -5F, -6F, 2, 10, 12, new CubeDeformation(0.0F)), PartPose.offset(-7F, 17F, 0F));
        rootPart.addOrReplaceChild("base2", CubeListBuilder.create().texOffs(4, 4).addBox(-1F, -1F, -5F, 2, 2, 10, new CubeDeformation(0.0F)), PartPose.offset(22F, 23F, 0F));
        rootPart.addOrReplaceChild("base1", CubeListBuilder.create().texOffs(4, 4).addBox(-1F, -1F, -5F, 2, 2, 10, new CubeDeformation(0.0F)), PartPose.offset(-6F, 23F, 0F));
        rootPart.addOrReplaceChild("block2", CubeListBuilder.create().texOffs(2, 2).addBox(-1F, -5F, -6F, 2, 10, 12, new CubeDeformation(0.0F)), PartPose.offset(23F, 17F, 0F));
        rootPart.addOrReplaceChild("block3", CubeListBuilder.create().texOffs(1, 26).addBox(-14F, -4F, -1F, 28, 8, 2, new CubeDeformation(0.0F)), PartPose.offset(8F, 18F, -5F));
        rootPart.addOrReplaceChild("block4", CubeListBuilder.create().texOffs(1, 26).addBox(-14F, -4F, -1F, 28, 8, 2, new CubeDeformation(0.0F)), PartPose.offset(8F, 18F, 5F));
        rootPart.addOrReplaceChild("block5", CubeListBuilder.create().texOffs(3, 42).addBox(-14F, -8.5F, -4F, 28, 1, 8, new CubeDeformation(0.0F)), PartPose.offset(8F, 21.5F, 0F));

        return LayerDefinition.create(mesh, 128, 64);
    }

    public static final class TroughModel extends Model {
        public final ModelPart root;

        private final ModelPart block1;
        private final ModelPart base2;
        private final ModelPart base1;
        private final ModelPart block2;
        private final ModelPart block3;
        private final ModelPart block4;
        private final ModelPart block5;
        /*private final ModelPart feed;
        private final ModelPart feedA;
        private final ModelPart feedB;
        private final ModelPart feedC;
        private final ModelPart feedD;
        private final ModelPart feedE;
        private final ModelPart feedF;
        private final ModelPart feedG;
        private final ModelPart feedH;
        private final ModelPart feedA1;
        private final ModelPart feedB1;
        private final ModelPart feedC1;
        private final ModelPart feedD1;
        private final ModelPart feedE1;
        private final ModelPart feedF1;
        private final ModelPart feedG1;
        private final ModelPart feedH1;
        private final ModelPart slop1;
        private final ModelPart slop2;
        private final ModelPart slop3;
        private final ModelPart water1;
        private final ModelPart water2;
        private final ModelPart water3;*/

        public TroughModel(ModelPart root) {
            super(root, RenderType::entityCutoutNoCull);
            this.root = root;
            this.block1 = root.getChild("block1");
            this.base2 = root.getChild("base2");
            this.base1 = root.getChild("base1");
            this.block2 = root.getChild("block2");
            this.block3 = root.getChild("block3");
            this.block4 = root.getChild("block4");
            this.block5 = root.getChild("block5");
            /*this.feed = root.getChild("feed");
            this.feedA = root.getChild("feedA");
            this.feedB = root.getChild("feedB");
            this.feedC = root.getChild("feedC");
            this.feedD = root.getChild("feedD");
            this.feedE = root.getChild("feedE");
            this.feedF = root.getChild("feedF");
            this.feedG = root.getChild("feedG");
            this.feedH = root.getChild("feedH");
            this.feedA1 = root.getChild("feedA1");
            this.feedB1 = root.getChild("feedB1");
            this.feedC1 = root.getChild("feedC1");
            this.feedD1 = root.getChild("feedD1");
            this.feedE1 = root.getChild("feedE1");
            this.feedF1 = root.getChild("feedF1");
            this.feedG1 = root.getChild("feedG1");
            this.feedH1 = root.getChild("feedH1");
            this.slop1 = root.getChild("slop1");
            this.slop2 = root.getChild("slop2");
            this.slop3 = root.getChild("slop3");
            this.water1 = root.getChild("water1");
            this.water2 = root.getChild("water2");
            this.water3 = root.getChild("water3");*/
        }

        /*@Override
        public void renderToBuffer(@NotNull PoseStack poseStack, @NotNull VertexConsumer buffer, int packedLight, int packedOverlay, int color) {
            this.block1.render(poseStack, buffer, packedLight, packedOverlay);
            this.block2.render(poseStack, buffer, packedLight, packedOverlay);
            this.block3.render(poseStack, buffer, packedLight, packedOverlay);
            this.block4.render(poseStack, buffer, packedLight, packedOverlay);
            this.block5.render(poseStack, buffer, packedLight, packedOverlay);
            this.base1.render(poseStack, buffer, packedLight, packedOverlay);
            this.base2.render(poseStack, buffer, packedLight, packedOverlay);
        }*/
    }
}
