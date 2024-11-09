package com.animania.block.entity.renderer;

import com.animania.AnimaniaConfig;
import com.animania.AnimaniaMod;
import com.animania.block.entity.SaltLickBlockEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public class SaltLickBlockEntityRenderer implements BlockEntityRenderer<SaltLickBlockEntity> {
    public static final ResourceLocation TEXTURE = AnimaniaMod.getId("textures/block/salt_lick_block.png");
    public static final ModelLayerLocation SALT_LICK = new ModelLayerLocation(AnimaniaMod.getId("salt_lick"), "main");

    private final ModelPart block;

    public SaltLickBlockEntityRenderer(BlockEntityRendererProvider.Context context) {
        ModelPart root = context.bakeLayer(SALT_LICK);
        this.block = root.getChild("block");
    }

    @Override
    public void render(@NotNull SaltLickBlockEntity blockEntity, float partialTick, @NotNull PoseStack poseStack, @NotNull MultiBufferSource bufferSource, int packedLight, int packedOverlay) {
        float scale = 0.625F * (blockEntity.getUsesLeft() / (float) AnimaniaConfig.saltLickMaxUses);

        VertexConsumer buffer = bufferSource.getBuffer(RenderType.entityCutout(TEXTURE));
        poseStack.pushPose();
        poseStack.translate(0.50F, 0.65F, 0.50F);
        poseStack.scale(0.7F, scale, 0.7F);
        this.block.render(poseStack, buffer, packedLight, packedOverlay);
        poseStack.popPose();
    }

    // Utility methods
    public static LayerDefinition createBodyLayer() {
        MeshDefinition mesh = new MeshDefinition();
        PartDefinition root = mesh.getRoot();

        root.addOrReplaceChild("block", CubeListBuilder.create().texOffs(0, 0).addBox(-8.0F, -16.0F, -8.0F, 16, 16, 16), PartPose.ZERO);

        return LayerDefinition.create(mesh, 16, 16);
    }
}
