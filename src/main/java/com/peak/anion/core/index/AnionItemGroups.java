package com.peak.anion.core.index;

import com.peak.anion.core.Anion;
import com.peak.anion.core.item.BatteryItem;
import net.acoyt.acornlib.api.registrants.ItemGroupRegistrant;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.component.ComponentType;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.text.Text;

import static com.peak.anion.core.index.AnionBlocks.*;
import static com.peak.anion.core.index.AnionItems.BATTERY;
import static com.peak.anion.core.index.AnionItems.CHARGED_IRON_INGOT;

/**
 * @author AcoYT
 * @author Chemthunder
 */
public interface AnionItemGroups {
    ItemGroupRegistrant GROUPS = new ItemGroupRegistrant(Anion.MOD_ID);

    RegistryKey<ItemGroup> GROUP_KEY = RegistryKey.of(RegistryKeys.ITEM_GROUP, Anion.id(Anion.MOD_ID));
    ItemGroup ITEM_GROUP = GROUPS.register(GROUP_KEY.getValue().getPath(), FabricItemGroup.builder()
            .icon(() -> new ItemStack(CHARGED_IRON_INGOT))
            .displayName(Text.translatable("itemGroup." + Anion.MOD_ID).withColor(0xFF545158)) // 0xFF611437
            .build());

    static void init() {
        ItemGroupEvents.modifyEntriesEvent(GROUP_KEY).register(AnionItemGroups::addEntries);
    }

    private static void addEntries(FabricItemGroupEntries itemGroup) {
        ItemGroup.DisplayContext context = itemGroup.getContext();

        // Anion
        itemGroup.add(CHARGED_IRON_INGOT);
        itemGroup.add(CHARGED_IRON_BLOCK);
        itemGroup.add(ANION_GENERATOR);

        // Electric
        itemGroup.add(get(BATTERY.getDefaultStack(), AnionComponents.STORED_CHARGES, BatteryItem.MAX_CHARGES));
        itemGroup.add(VOLT_EATER);
        itemGroup.add(ATTRACTOR);
    }

    static <T> ItemStack get(ItemStack stack, ComponentType<T> type, T value) {
        stack.set(type, value);
        return stack;
    }
}
