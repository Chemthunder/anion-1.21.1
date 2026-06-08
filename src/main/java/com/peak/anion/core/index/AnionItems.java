package com.peak.anion.core.index;

import com.peak.anion.core.Anion;
import net.acoyt.acornlib.api.registrants.ItemRegistrant;
import net.minecraft.item.Item;

/**
 * @author Chemthunder
 */
public interface AnionItems {
    ItemRegistrant ITEMS = new ItemRegistrant(Anion.MOD_ID);

    Item CHARGED_IRON = ITEMS.register("charged_iron_ingot", Item::new, new Item.Settings());

    static void init() {}
}