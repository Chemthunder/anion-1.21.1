package com.peak.anion.core.block.entity;

import com.peak.anion.core.block.GeneratorBlock;
import com.peak.anion.core.index.AnionBlockEntities;
import com.peak.anion.core.index.AnionParticles;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

/**
 * @author AcoYT
 */
public class GeneratorBlockEntity extends BlockEntity {
    public float lookAngle = 0.0F;

    public GeneratorBlockEntity(BlockPos pos, BlockState state) {
        super(AnionBlockEntities.GENERATOR, pos, state);
    }

    public void tick(World world, BlockPos pos, BlockState state) {
        if (state.get(GeneratorBlock.POWERED)) {
            if (state.get(GeneratorBlock.SUBMERGED)) {
                if (world.isClient) {
                    float yPos = 0.6F;
                    float yVelocity = 0.2F;
                    float spread = 0.35F;
                    ParticleEffect particle = AnionParticles.DOUBLE_HELIX;

                    Vec3d front = pos.toCenterPos().add(this.getRotationVector().multiply(spread));
                    world.addParticle(
                            particle,
                            true,
                            front.x, front.y + yPos, front.z,
                            0.0, yVelocity, 0.0
                    );

                    Vec3d back = pos.toCenterPos().subtract(this.getRotationVector().multiply(spread));
                    world.addParticle(
                            particle,
                            true,
                            back.x, back.y + yPos, back.z,
                            0.0, yVelocity, 0.0
                    );
                }
            }

            this.lookAngle = MathHelper.wrapDegrees(this.lookAngle + 15.0F);
        }
    }

    public void readNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registries) {
        this.lookAngle = nbt.getFloat("LookAngle");
    }

    public void writeNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registries) {
        nbt.putFloat("LookAngle", this.lookAngle);
    }

    private Vec3d getRotationVector() {
        float pitch = 0.0F * (float) (Math.PI / 180.0);
        float yaw = -this.lookAngle * (float) (Math.PI / 180.0);
        float yawCos = MathHelper.cos(yaw);
        float yawSin = MathHelper.sin(yaw);
        float pitchCos = MathHelper.cos(pitch);
        float pitchSin = MathHelper.sin(pitch);
        return new Vec3d(yawSin * pitchCos, -pitchSin, yawCos * pitchCos);
    }
}
