package com.peak.anion.core.block;

import com.mojang.serialization.MapCodec;
import com.peak.anion.api.block.ChargeableBlock;
import com.peak.anion.api.block.InformationalBlock;
import com.peak.anion.core.block.entity.AttractorBlockEntity;
import com.peak.anion.core.util.AnionUtil;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.BlockWithEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.block.entity.HopperBlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

import static com.peak.anion.core.util.AnionUtil.SHOCKIES;

/**
 * @author AcoYT
 * @author Chemthunder
 */
public class AttractorBlock extends BlockWithEntity implements ChargeableBlock, InformationalBlock {
    public static final MapCodec<AttractorBlock> CODEC = createCodec(AttractorBlock::new);

    public static final BooleanProperty HAS_HOPPER_BELOW = BooleanProperty.of("has_hopper_below");

    public MapCodec<? extends BlockWithEntity> getCodec() {
        return CODEC;
    }

    public AttractorBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.getDefaultState().with(SHOCKIES, 0).with(HAS_HOPPER_BELOW, false));
    }

    public void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(SHOCKIES, HAS_HOPPER_BELOW);
    }

    public void neighborUpdate(BlockState state, World world, BlockPos pos, Block sourceBlock, BlockPos sourcePos, boolean notify) {
        boolean bl = world.getBlockEntity(pos.down()) instanceof HopperBlockEntity;

        if (bl != state.get(HAS_HOPPER_BELOW)) {
            world.setBlockState(pos, state.with(HAS_HOPPER_BELOW, bl), Block.NOTIFY_LISTENERS);
        }

        super.neighborUpdate(state, world, pos, sourceBlock, sourcePos, notify);
    }

    @Nullable
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return this.getDefaultState().with(HAS_HOPPER_BELOW, ctx.getWorld().getBlockEntity(ctx.getBlockPos().down()) instanceof HopperBlockEntity);
    }

    @Nullable
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new AttractorBlockEntity(pos, state);
    }

    @Nullable
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World ignoredWorld, BlockState ignoredState, BlockEntityType<T> type) {
        return (world, pos, state, blockEntity) -> {
            if (blockEntity instanceof AttractorBlockEntity attractorBlock) {
                attractorBlock.tick(world, pos, state);
            }
        };
    }

    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    public List<Text> getInfoDisplayTexts(BlockState state, PlayerEntity viewer) {
        return List.of(
                Text.translatable("display.anion.attractor.0", state.get(SHOCKIES)),
                AnionUtil.SPACER,
                Text.translatable(state.get(HAS_HOPPER_BELOW) ? "display.anion.attractor.1.has_hopper" : "display.anion.attractor.1.no_hopper")
        );
    }
}
