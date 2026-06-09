package com.peak.anion.core.index;

import com.peak.anion.core.Anion;
import net.acoyt.acornlib.api.registrants.ParticleTypeRegistrant;
import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.particle.SimpleParticleType;

/**
 * @author AcoYT
 */
public interface AnionParticles {
    ParticleTypeRegistrant PARTICLES = new ParticleTypeRegistrant(Anion.MOD_ID);

    SimpleParticleType DOUBLE_HELIX = PARTICLES.register("double_helix", FabricParticleTypes.simple(true));

    static void init() {}
}
