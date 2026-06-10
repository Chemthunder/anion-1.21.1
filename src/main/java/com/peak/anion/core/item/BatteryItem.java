package com.peak.anion.core.item;

import com.peak.anion.api.block.ChargeableBlock;
import com.peak.anion.core.Anion;
import com.peak.anion.core.block.VolteaterBlock;
import com.peak.anion.core.index.AnionBlocks;
import com.peak.anion.core.index.AnionComponentTypes;
import com.peak.anion.core.util.AnionUtil;
import com.peak.anion.datagen.provider.resources.AnionModelProvider;
import net.acoyt.acornlib.api.item.ModelVaryingItem;
import net.minecraft.block.BlockState;
import net.minecraft.client.render.model.json.ModelTransformationMode;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * @author Chemthunder
 */
public class BatteryItem extends Item implements ModelVaryingItem {
    private static final int MAX_CHARGES = 8;

    public BatteryItem(Settings settings) {
        super(settings);
    }

    public ActionResult useOnBlock(ItemUsageContext context) {
        PlayerEntity player = context.getPlayer();
        ItemStack stack = context.getStack();
        World world = context.getWorld();

        if (player != null && stack != null && world != null) {
            BlockState state = world.getBlockState(context.getBlockPos());
            BlockPos pos = context.getBlockPos();

            if (state.isOf(AnionBlocks.VOLTEATER)) {
                int charges = stack.getOrDefault(AnionComponentTypes.STORED_CHARGES, 0);

                if (charges < MAX_CHARGES) {
                    stack.set(
                            AnionComponentTypes.STORED_CHARGES,
                            MathHelper.clamp(state.get(VolteaterBlock.CHARGES), 0, 8)
                    );

                    world.setBlockState(
                            pos,
                            AnionBlocks.VOLTEATER.getDefaultState()
                    );

                    if (world.isClient()) {
                        player.swingHand(context.getHand());
                    }
                }
            }

            if (world.getBlockState(pos).getBlock() instanceof ChargeableBlock chargeableBlock) {
                int charges = stack.getOrDefault(AnionComponentTypes.STORED_CHARGES, 0);
                chargeableBlock.set(world, pos, state, charges);

                if (world.isClient()) {
                    player.swingHand(player.getActiveHand());
                }
            }
        }
        return super.useOnBlock(context);
    }

    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        int charges = stack.getOrDefault(AnionComponentTypes.STORED_CHARGES, 0);

        tooltip.add(Text.translatable("tooltip.anion.battery", charges).withColor(batteryChargeToColor(stack)));
    }

    public static int batteryChargeToColor(ItemStack stack) {
        int charges = stack.getOrDefault(AnionComponentTypes.STORED_CHARGES, 0);

        return switch (charges) {
            case 1 -> 0xFF00626a;
            case 2 -> 0xFF008791;
            case 3 -> 0xFF00a6b3;
            case 4 -> 0xFF00bccb;
            case 5 -> 0xFF00d8e9;
            case 6 -> 0xFF54f3ff;
            case 7 -> 0xFFa5f9ff;
            case 8 -> 0xFFcffcff;
            default -> 0xFF3e4949;
        };
    }

    public Identifier getModel(ModelTransformationMode renderMode, ItemStack stack, @Nullable LivingEntity entity) {
        return Anion.id("battery_" + stack.getOrDefault(AnionComponentTypes.STORED_CHARGES, 0));
    }

    public List<Identifier> getModelsToLoad() {
        return AnionModelProvider.BATTERY_MODELS;
    }

    public boolean isItemBarVisible(ItemStack stack) {
        return stack.getOrDefault(AnionComponentTypes.STORED_CHARGES, 0) > 0;
    }

    public int getItemBarStep(ItemStack stack) {
        int charges = stack.getOrDefault(AnionComponentTypes.STORED_CHARGES, 0);
        return AnionUtil.itemBarStep(stack, charges, 8);
    }

    public int getItemBarColor(ItemStack stack) {
        return batteryChargeToColor(stack);
    }
}
