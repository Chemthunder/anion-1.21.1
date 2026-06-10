package com.peak.anion.core.index.tag;

import com.peak.anion.core.Anion;
import net.acoyt.acornlib.api.builder.TagBuilder;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;

/**
 * @author Chemthunder
 */
public interface AnionItemTags {
    TagBuilder<Item> ITEMS = new TagBuilder<>(Anion.MOD_ID, RegistryKeys.ITEM);

    TagKey<Item> MAGNETIC = ITEMS.register("magnetic");
}
