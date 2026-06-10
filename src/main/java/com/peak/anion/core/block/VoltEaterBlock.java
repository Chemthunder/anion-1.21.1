package com.peak.anion.core.block;

import com.mojang.serialization.MapCodec;
import com.peak.anion.api.block.ChargeableBlock;
import com.peak.anion.api.block.InformationalBlock;
import com.peak.anion.api.event.VoltEaterChargeEvent;
import com.peak.anion.core.util.AnionUtil;
import net.acoyt.acornlib.impl.util.interfaces.LangDiffering;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.registry.Registries;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;
import net.minecraft.text.Text;
import net.minecraft.util.Util;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * @author Chemthunder
 */
public class VoltEaterBlock extends Block implements InformationalBlock, ChargeableBlock, LangDiffering<Block> {
    public static final MapCodec<VoltEaterBlock> CODEC = createCodec(VoltEaterBlock::new);

    public static final IntProperty CHARGES = IntProperty.of("charges", 0, 8);

    public VoltEaterBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.getDefaultState().with(CHARGES, 0));
    }

    public void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(CHARGES);
    }

    public List<Text> getInfoDisplayTexts(BlockState state, PlayerEntity viewer) {
        return Arrays.asList(
                Text.translatable("display.anion.volt_eater.0", state.get(CHARGES)),
                AnionUtil.SPACER,
                Text.translatable("display.anion.volt_eater.1")
        );
    }

    public void onCharged(World world, BlockPos pos, BlockState state) {
        world.setBlockState(pos, state.with(CHARGES, MathHelper.clamp(state.get(CHARGES) + world.random.nextBetween(1, 3), 0, 8)), Block.NOTIFY_LISTENERS);
        VoltEaterChargeEvent.EVENT.invoker().onCharge(world, pos, state);

        world.playSound(
                null,
                pos,
                SoundEvents.ITEM_TRIDENT_THUNDER.value(), SoundCategory.BLOCKS,
                1.0F, 0.4F
        );

        world.playSound(
                null,
                pos,
                SoundEvents.ENTITY_WITHER_HURT, SoundCategory.BLOCKS,
                0.7F, 0.2F
        );
    }

    public Optional<String> getDifferedKey(Block object) { /// btw this just allows the hyphen
        return Optional.of(Util.createTranslationKey("block", Registries.BLOCK.getId(Blocks.AIR)));
    }
}
