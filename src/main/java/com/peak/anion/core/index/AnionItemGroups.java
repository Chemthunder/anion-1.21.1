package com.peak.anion.core.index;

import com.peak.anion.core.Anion;
import net.acoyt.acornlib.api.registrants.ItemGroupRegistrant;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.text.Text;

/**
 * @author Chemthunder
 */
public interface AnionItemGroups {
    ItemGroupRegistrant GROUPS = new ItemGroupRegistrant(Anion.MOD_ID);

    RegistryKey<ItemGroup> GROUP_KEY = RegistryKey.of(RegistryKeys.ITEM_GROUP, Anion.id(Anion.MOD_ID));
    ItemGroup ITEM_GROUP = GROUPS.register(GROUP_KEY.getValue().getPath(), FabricItemGroup.builder()
            .icon(() -> new ItemStack(AnionItems.CHARGED_IRON))
            .displayName(Text.translatable("itemGroup." + Anion.MOD_ID).withColor(0xFF545158)) // 0xFF611437
            .build());

    static void init() {
        ItemGroupEvents.modifyEntriesEvent(GROUP_KEY).register(AnionItemGroups::addEntries);
    }

    private static void addEntries(FabricItemGroupEntries itemGroup) {
        ItemGroup.DisplayContext context = itemGroup.getContext();

        // Anion
        itemGroup.add(AnionItems.CHARGED_IRON);
        itemGroup.add(AnionBlocks.CHARGED_IRON_BLOCK);
        itemGroup.add(AnionBlocks.ANION_GENERATOR);

        // Electric
        itemGroup.add(AnionItems.BATTERY);
        itemGroup.add(AnionBlocks.VOLTEATER);
        itemGroup.add(AnionBlocks.ATTRACTOR);
    }
}
