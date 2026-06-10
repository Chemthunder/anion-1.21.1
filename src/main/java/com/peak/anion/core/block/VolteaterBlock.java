package com.peak.anion.core.block;

import com.mojang.serialization.MapCodec;
import com.peak.anion.api.block.InformationalBlock;
import com.peak.anion.api.event.VolteaterChargeCallback;
import com.peak.anion.core.util.AnionUtil;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

import java.util.Arrays;
import java.util.List;

/**
 * @author Chemthunder
 */
public class VolteaterBlock extends Block implements InformationalBlock {
    public static final MapCodec<VolteaterBlock> CODEC = createCodec(VolteaterBlock::new);

    public static final IntProperty CHARGES = IntProperty.of("charges", 0, 8);

    public VolteaterBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.getDefaultState().with(CHARGES, 0));
    }

    public static void charge(World world, BlockPos pos, BlockState state) {
        world.setBlockState(pos, state.with(CHARGES, MathHelper.clamp(state.get(CHARGES) + 1, 0, 8)), Block.NOTIFY_LISTENERS);

        VolteaterChargeCallback.EVENT.invoker().onCharge(world, pos, state);
    }

    public static int getCharges(BlockState state) {
        return state.get(CHARGES);
    }

    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(CHARGES);
    }

    public List<Text> getInfoDisplayTexts(BlockState state, PlayerEntity viewer) {
        return Arrays.asList(
                Text.translatable("display.anion.volteater.0", state.get(CHARGES)),
                AnionUtil.SPACER,
                Text.translatable("display.anion.volteater.1")
        );
    }
}
