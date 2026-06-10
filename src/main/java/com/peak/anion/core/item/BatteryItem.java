package com.peak.anion.core.item;

import com.peak.anion.api.block.ChargeableBlock;
import com.peak.anion.core.Anion;
import com.peak.anion.core.block.VoltEaterBlock;
import com.peak.anion.core.index.AnionComponents;
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
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.List;

/**
 * @author Chemthunder
 */
public class BatteryItem extends Item implements ModelVaryingItem {
    public static final int MAX_CHARGES = 8;

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
            int stackCharges = stack.getOrDefault(AnionComponents.STORED_CHARGES, 0);

            if (stackCharges > 0) {
                if (state.contains(VoltEaterBlock.CHARGES)) {
                    int charges = state.get(VoltEaterBlock.CHARGES);

                    if (charges + stackCharges > MAX_CHARGES) {
                        stack.set(AnionComponents.STORED_CHARGES, MAX_CHARGES);
                        world.setBlockState(pos, state.with(VoltEaterBlock.CHARGES, (charges + stackCharges) - MAX_CHARGES));
                    } else {
                        stack.set(AnionComponents.STORED_CHARGES, charges);
                        world.setBlockState(pos, state.with(VoltEaterBlock.CHARGES, 0));
                    }

                    return world.isClient ? ActionResult.CONSUME : ActionResult.SUCCESS;
                }

                if (world.getBlockState(pos).getBlock() instanceof ChargeableBlock chargeable) {
                    stack.set(AnionComponents.STORED_CHARGES, stackCharges - chargeable.increment(world, pos, state, stackCharges));
                    Anion.LOGGER.info("Prev: {}, New: {}", stackCharges, stack.get(AnionComponents.STORED_CHARGES));

                    player.swingHand(context.getHand(), !world.isClient);
                    return world.isClient ? ActionResult.CONSUME : ActionResult.SUCCESS;
                }
            }
        }

        return ActionResult.PASS;
    }

    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        int stackCharges = stack.getOrDefault(AnionComponents.STORED_CHARGES, 0);

        tooltip.add(
                Text.literal("[").withColor(0xFF2e5950)
                        .append(Text.literal("▌".repeat(MathHelper.clamp(stackCharges, 0, MAX_CHARGES))).withColor(0xFF43968d))
                        .append(Text.literal("▌".repeat(MathHelper.clamp(MAX_CHARGES - stackCharges, 0, MAX_CHARGES))).withColor(0xFF1b3936))
                        .append(Text.literal("]").withColor(0xFF2e5950))
        );
    }

    public Identifier getModel(ModelTransformationMode renderMode, ItemStack stack, @Nullable LivingEntity entity) {
        Identifier modelId = Anion.id("battery_" + stack.getOrDefault(AnionComponents.STORED_CHARGES, 0));
        return getModelsToLoad().contains(modelId) ? modelId : Anion.id("battery");
    }

    public List<Identifier> getModelsToLoad() {
        return Arrays.asList(
                Anion.id("battery"),
                Anion.id("battery_1"),
                Anion.id("battery_2"),
                Anion.id("battery_3"),
                Anion.id("battery_4"),
                Anion.id("battery_5"),
                Anion.id("battery_6"),
                Anion.id("battery_7"),
                Anion.id("battery_8")
        );
    }
}
