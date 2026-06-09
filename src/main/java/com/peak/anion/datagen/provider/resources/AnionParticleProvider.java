package com.peak.anion.datagen.provider.resources;

import com.peak.anion.core.index.AnionParticles;
import net.acoyt.acornlib.data.provider.resources.AcornParticleProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.util.Identifier;

import java.util.List;

/**
 * @author AcoYT
 */
public class AnionParticleProvider extends AcornParticleProvider {
    public AnionParticleProvider(FabricDataOutput output) {
        super(output);
    }

    public void generate(ParticleDataConsumer consumer) {
        consumer.accept(AnionParticles.DOUBLE_HELIX, List.of(Identifier.ofVanilla("bubble")));
    }
}
