package com.peak.anion.core;

import com.mojang.logging.LogUtils;
import com.peak.anion.core.index.AnionBlockEntities;
import com.peak.anion.core.index.AnionBlocks;
import com.peak.anion.core.index.AnionItems;
import net.fabricmc.api.ModInitializer;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;

public class Anion implements ModInitializer {
    public static final String MOD_ID = "anion";
    public static final Logger LOGGER = LogUtils.getLogger();

    public void onInitialize() {
        /* Registration */
        AnionBlockEntities.init();
        AnionBlocks.init();
        AnionItems.init();

        LOGGER.info("Hello Fabric world!");
    }

    public static Identifier id(String path) {
        return Identifier.of(MOD_ID, path);
    }
}