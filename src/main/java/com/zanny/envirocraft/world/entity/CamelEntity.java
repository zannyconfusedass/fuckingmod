package com.zanny.envirocraft.world.entity;

import com.zanny.envirocraft.EnviroCraft;
import net.minecraft.block.BlockState;
import net.minecraft.entity.ai.RangedAttackMob;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import org.jetbrains.annotations.Nullable;
import net.minecraft.entity.*;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.passive.*;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.recipe.Ingredient;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.World;
import net.minecraft.entity.attribute.DefaultAttributeContainer.Builder;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

@SuppressWarnings("ALL")
public class CamelEntity extends LlamaEntity implements IAnimatable, Saddleable, RangedAttackMob {

    private static final TrackedData<Integer> CAMEL = DataTracker.registerData(CamelEntity.class, TrackedDataHandlerRegistry.INTEGER);
    private static final Ingredient TAMING_INGREDIENT = Ingredient.ofItems(Items.WHEAT, Items.CARROT, Items.HAY_BLOCK);
    private static final Ingredient TEMPTATION_ITEMS = Ingredient.ofItems(Items.WHEAT, Items.CARROT, Items.HAY_BLOCK);
    private static final Ingredient BREEDING_ITEMS = Ingredient.ofItems(Items.WHEAT, Items.CARROT, Items.HAY_BLOCK);
    private AnimationFactory animationFactory = new AnimationFactory(this);
    protected float jumpStrength = 0.5F;

    public CamelEntity(EntityType<? extends CamelEntity> type, World worldIn) {
        super(type, worldIn);
        createCamelAttributes();
    }

    public static Builder createCamelAttributes() {
        return MobEntity.createMobAttributes().add(EntityAttributes.GENERIC_MAX_HEALTH, 40.0D).add(EntityAttributes.GENERIC_MOVEMENT_SPEED, .2F).add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 3.0);
    }

    public boolean canBeSaddled() {
        return this.isAlive() && !this.isBaby() && this.isTame();
    }

    public boolean hasChest() {
        if (this.isSaddled());
        return (true);
    }

    public boolean canBeControlledByRider() {
        return this.getPrimaryPassenger() instanceof LivingEntity;
    }

    @Override
    public double getJumpStrength() {
        return this.jumpStrength;
    }

    @Override
    public void travel(Vec3d movementInput) {
        super.travel(movementInput);
            if (this.hasPassengers() && this.canBeControlledByRider() && this.isSaddled()) {
                LivingEntity livingEntity = (LivingEntity)this.getPrimaryPassenger();
                this.yaw = livingEntity.yaw;
                this.prevYaw = this.yaw;
                this.pitch = livingEntity.pitch * 0.5F;
                this.setRotation(this.yaw, this.pitch);
                this.bodyYaw = this.yaw;
                this.headYaw = this.bodyYaw;
                float f = livingEntity.sidewaysSpeed * 0.02F;
                float g = livingEntity.forwardSpeed * 0.02F;
                {
                    g *= 0.001F;
                    f *= 0.001f;
                }
            }
        }

        @Override
        public void updatePassengerPosition(Entity passenger) {
        if (this.hasPassenger(passenger)) {
            float f = MathHelper.cos(this.bodyYaw * 0.017453292F);
            float g = MathHelper.sin(this.bodyYaw * 0.017453292F);
            float h = 0.3F;
            passenger.updatePosition(this.getX() + (double)(0.3F * g), this.getY() + this.getMountedHeightOffset() + passenger.getHeightOffset(), this.getZ() - (double)(0.3F * f));
        }
    }

    private <E extends IAnimatable > PlayState predicate(AnimationEvent < E > event) {
        if (event.isMoving()) {
            event.getController().setAnimation(
                    new AnimationBuilder().addAnimation("walk", true));
            return PlayState.CONTINUE;
        }
        if (!event.isMoving())
            event.getController().setAnimation(
                    new AnimationBuilder().addAnimation("idle", true));
            return PlayState.CONTINUE;
        }

    @Override
    public void registerControllers(AnimationData animationData) {
        animationData.addAnimationController(new AnimationController(this, "controller", 0, this::predicate));
    }

    @Override
    public AnimationFactory getFactory() {
        return this.animationFactory;
    }

    @Override
    protected void initDataTracker() {
        super.initDataTracker();
        this.dataTracker.startTracking(CAMEL, 0);
    }

    @Nullable
    @Override
    public EntityData initialize(ServerWorldAccess world, LocalDifficulty difficulty, SpawnReason spawnReason, @Nullable EntityData entityData, @Nullable CompoundTag entityTag) {
        return super.initialize(world, difficulty, spawnReason, (EntityData) entityData, entityTag);
    }

    public CamelEntity createChild(ServerWorld serverWorld, PassiveEntity mate) {
        CamelEntity child = EnviroCraft.CAMEL.create(this.world);
        return child;
    }

    protected void walkToParent() {
        if (!this.isFollowing() && this.isBaby()) {
            super.walkToParent();
        }
    }

    @Override
    public boolean hasArmorSlot() {
        return false;
    }

    @Override
    public int getInventoryColumns() {
        if (this.isSaddled()) {
            return 5;
        }
        else return 0;
    }

    @Override
    protected void initGoals() {
        this.goalSelector.add(3, new ProjectileAttackGoal(this, .50D, 40, 20.0F));
        this.goalSelector.add(3, new EscapeDangerGoal(this, .75D));
        this.goalSelector.add(1, new HorseBondWithPlayerGoal(this, 1.2D));
        this.goalSelector.add(4, new TemptGoal(this, 0.50D, Ingredient.ofItems(Items.WHEAT, Items.CARROT, Items.HAY_BLOCK), false));
        this.goalSelector.add(5, new AnimalMateGoal(this, 0.50D));
        this.goalSelector.add(6, new FollowParentGoal(this, .50D));
        this.goalSelector.add(7, new WanderAroundFarGoal(this, 1D));
        this.goalSelector.add(8, new LookAtEntityGoal(this, PlayerEntity.class, 6.0F));
        this.goalSelector.add(9, new LookAroundGoal(this));
        this.targetSelector.add(3, new RevengeGoal(this));
    }

    @Override
    protected float getActiveEyeHeight(EntityPose pose, EntityDimensions size) {
        if (this.isBaby()) return size.height * .25F;
        return size.height * .75F;
    }

    @Override
    protected SoundEvent getAmbientSound() {
        if (this.isBaby()) return (EnviroCraft.CALF_HUM);
        return EnviroCraft.CAMEL_AMBIENT;
    }

    @Override
    protected SoundEvent getAngrySound() {
        return EnviroCraft.CAMEL_GROWL;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return EnviroCraft.CAMEL_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return EnviroCraft.CAMEL_DEATH;
    }

    @Override
    protected void playStepSound(BlockPos pos, BlockState state) {
        this.playSound(EnviroCraft.CAMEL_STEP, 0.15F, 1.0F);
    }
}