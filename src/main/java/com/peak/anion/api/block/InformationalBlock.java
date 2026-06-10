package com.peak.anion.api.block;

import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Text;

import java.util.List;

/**
 * @author Chemthunder
 */
public interface InformationalBlock {
    List<Text> getInfoDisplayTexts(BlockState state, PlayerEntity viewer);
}
