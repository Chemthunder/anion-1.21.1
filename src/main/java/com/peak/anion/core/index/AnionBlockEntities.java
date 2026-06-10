package com.peak.anion.core.index;

import com.peak.anion.core.Anion;
import com.peak.anion.core.block.entity.AttractorBlockEntity;
import com.peak.anion.core.block.entity.GeneratorBlockEntity;
import net.acoyt.acornlib.api.registrants.BlockEntityTypeRegistrant;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntityType;

/**
 * @author AcoYT
 */
@SuppressWarnings("deprecation")
public interface AnionBlockEntities {
    BlockEntityTypeRegistrant BLOCK_ENTITIES = new BlockEntityTypeRegistrant(Anion.MOD_ID);

    BlockEntityType<GeneratorBlockEntity> GENERATOR = BLOCK_ENTITIES.register("generator", FabricBlockEntityTypeBuilder.create(
            GeneratorBlockEntity::new, AnionBlocks.ANION_GENERATOR));

    BlockEntityType<AttractorBlockEntity> ATTRACTOR = BLOCK_ENTITIES.register("attractor", FabricBlockEntityTypeBuilder.create(
            AttractorBlockEntity::new, AnionBlocks.ATTRACTOR));

    static void init() {}
}
