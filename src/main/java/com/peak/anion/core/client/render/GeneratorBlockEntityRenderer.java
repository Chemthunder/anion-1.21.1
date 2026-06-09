package com.peak.anion.core.client.render;

import com.peak.anion.core.block.entity.GeneratorBlockEntity;
import com.peak.anion.core.util.AnionUtil;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.render.debug.DebugRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import org.jetbrains.annotations.NotNull;

/**
 * @author AcoYT
 */
public class GeneratorBlockEntityRenderer implements BlockEntityRenderer<GeneratorBlockEntity> {
    public GeneratorBlockEntityRenderer(BlockEntityRendererFactory.@NotNull Context context) {
        //
    }

    public void render(GeneratorBlockEntity entity, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
        if (entity.getWorld() != null) {
            BlockPos pos = entity.getPos();
            Vec3d cameraPos = MinecraftClient.getInstance().gameRenderer.getCamera().getPos();
            Box box = AnionUtil.getUpwardsOfWater(entity.getWorld(), pos, cameraPos);

            matrices.push();

            //matrices.translate(cameraPos.x, cameraPos.y, cameraPos.z);
            matrices.scale(-1, -1, -1);

            DebugRenderer.drawBox(
                    matrices,
                    vertexConsumers,
                    box,
                    1.0F, 1.0F, 0.0F, 0.35F
            );

            matrices.pop();
        }
    }
}