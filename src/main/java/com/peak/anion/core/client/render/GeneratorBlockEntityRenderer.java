package com.peak.anion.core.client.render;

import com.peak.anion.core.block.entity.GeneratorBlockEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import org.jetbrains.annotations.NotNull;

/**
 * @author AcoYT
 */
public class GeneratorBlockEntityRenderer implements BlockEntityRenderer<GeneratorBlockEntity> {
    public GeneratorBlockEntityRenderer(BlockEntityRendererFactory.@NotNull Context context) {
        //
    }

    public void render(GeneratorBlockEntity entity, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
        //
    }
}
