package com.peak.anion.core.event.client;

import com.peak.anion.api.block.InformationalBlock;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.block.BlockState;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.render.RenderTickCounter;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.world.World;

/**
 * @author Chemthunder
 */
public class DisplayHudEvent implements HudRenderCallback {
    public void onHudRender(DrawContext context, RenderTickCounter renderTickCounter) {
        PlayerEntity player = MinecraftClient.getInstance().player;
        if (player == null) return;

        World world = MinecraftClient.getInstance().world;
        if (world == null) return;

        if (player.isSneaking()) {
            if (MinecraftClient.getInstance().crosshairTarget instanceof BlockHitResult hitResult) {
                BlockState state = world.getBlockState(hitResult.getBlockPos());
                if (state.getBlock() instanceof InformationalBlock info) {
                    context.drawTooltip(
                            MinecraftClient.getInstance().textRenderer,
                            info.getInfoDisplayTexts(state, player),
                            context.getScaledWindowWidth() / 2,
                            context.getScaledWindowHeight() / 2
                    );
                }
            }
        }
    }
}
