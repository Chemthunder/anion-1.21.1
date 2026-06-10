package com.peak.anion.core.index;

import com.peak.anion.core.Anion;
import com.peak.anion.core.item.BatteryItem;
import net.acoyt.acornlib.api.registrants.ItemRegistrant;
import net.minecraft.item.Item;

/**
 * @author Chemthunder
 */
public interface AnionItems {
    ItemRegistrant ITEMS = new ItemRegistrant(Anion.MOD_ID);

    Item CHARGED_IRON_INGOT = ITEMS.register("charged_iron_ingot", Item::new, new Item.Settings());

    Item BATTERY = ITEMS.register("battery", BatteryItem::new, new Item.Settings()
            .component(AnionComponents.STORED_CHARGES, 0)
            .maxCount(1)
            .fireproof());

    static void init() {}
}
