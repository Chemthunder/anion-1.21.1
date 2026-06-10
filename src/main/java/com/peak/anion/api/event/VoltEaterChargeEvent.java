package com.peak.anion.api.event;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * @author Chemthunder
 */
public interface VoltEaterChargeEvent {
    Event<VoltEaterChargeEvent> EVENT = EventFactory.createArrayBacked(VoltEaterChargeEvent.class, events -> (world, pos, state) -> {
        List<VoltEaterChargeEvent> sortedEvents = new ArrayList<>(Arrays.asList(events));
        sortedEvents.sort(Comparator.comparingInt(VoltEaterChargeEvent::getPriority));
        for (VoltEaterChargeEvent event : sortedEvents) {
            event.onCharge(world, pos, state);
        }
    });

    default int getPriority() {
        return 1000;
    }

    void onCharge(World world, BlockPos pos, BlockState state);
}
