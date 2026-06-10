package com.peak.anion.core;

import com.mojang.logging.LogUtils;
import com.peak.anion.core.index.*;
import net.fabricmc.api.ModInitializer;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;

/**
 * @author Chemthunder
 * @author AcoYT
 */
public class Anion implements ModInitializer {
    public static final String MOD_ID = "anion";
    public static final Logger LOGGER = LogUtils.getLogger();

    public void onInitialize() {
        /* Registration */
        AnionBlockEntities.init();
        AnionBlocks.init();
        AnionComponents.init();
        AnionItemGroups.init();
        AnionItems.init();
        AnionParticles.init();

        LOGGER.info("Charging!!!");
    }

    public static Identifier id(String path) {
        return Identifier.of(MOD_ID, path);
    }
}
