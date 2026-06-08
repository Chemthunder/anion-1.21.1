package com.peak.anion.core;

import com.peak.anion.core.index.AnionBlocks;
import com.peak.anion.core.index.AnionItems;
import net.fabricmc.api.ModInitializer;

import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Anion implements ModInitializer {
	public static final String MOD_ID = "anion";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	public void onInitialize() {
        /* Registration */
        AnionItems.init();
        AnionBlocks.init();

		LOGGER.info("Hello Fabric world!");
	}

    public static Identifier id(String path) {
        return Identifier.of(MOD_ID, path);
    }
}