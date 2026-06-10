package com.peak.anion.core;

import com.peak.anion.core.client.particle.DoubleHelixParticle;
import com.peak.anion.core.client.render.GeneratorBlockEntityRenderer;
import com.peak.anion.core.event.client.DisplayHudEvent;
import com.peak.anion.core.index.AnionBlockEntities;
import com.peak.anion.core.index.AnionParticles;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactories;

/**
 * @author Chemthunder
 */
@Environment(EnvType.CLIENT)
public class AnionClient implements ClientModInitializer {
    public void onInitializeClient() {
        BlockEntityRendererFactories.register(AnionBlockEntities.GENERATOR, GeneratorBlockEntityRenderer::new);

        ParticleFactoryRegistry.getInstance().register(AnionParticles.DOUBLE_HELIX, DoubleHelixParticle.Factory::new);

        HudRenderCallback.EVENT.register(new DisplayHudEvent());
    }
}
