package com.peak.anion.core.index;

import com.peak.anion.core.Anion;
import com.peak.anion.core.item.ChargedIronIngotItem;
import net.acoyt.acornlib.api.registrants.ItemRegistrant;
import net.minecraft.item.Item;

/**
 * @author Chemthunder
 */
public interface AnionItems {
    ItemRegistrant ITEMS = new ItemRegistrant(Anion.MOD_ID);

    Item CHARGED_IRON = ITEMS.register("charged_iron_ingot", ChargedIronIngotItem::new, new Item.Settings()
            .maxCount(16)
    );

    static void init() {}
}
