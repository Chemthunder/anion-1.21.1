package com.peak.anion.core.index;

import com.mojang.serialization.Codec;
import com.peak.anion.core.Anion;
import net.acoyt.acornlib.api.registrants.ComponentTypeRegistrant;
import net.minecraft.component.ComponentType;
import net.minecraft.network.codec.PacketCodecs;

/**
 * @author Chemthunder
 */
public interface AnionComponents {
    ComponentTypeRegistrant COMPONENTS = new ComponentTypeRegistrant(Anion.MOD_ID);

    ComponentType<Integer> STORED_CHARGES = COMPONENTS.register("stored_charges", Codec.INT, PacketCodecs.INTEGER);

    static void init() {}
}
