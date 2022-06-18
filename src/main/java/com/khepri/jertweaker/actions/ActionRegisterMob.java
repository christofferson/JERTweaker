package com.khepri.jertweaker.actions;

import com.blamejared.crafttweaker.api.action.base.IUndoableAction;
import com.blamejared.crafttweaker.api.zencode.IScriptLoadSource;
import com.blamejared.crafttweaker.platform.Services;
import com.khepri.jertweaker.state.MobState;
import com.khepri.jertweaker.util.MobUtils;
import com.khepri.jertweaker.zen.component.JERLightLevel;
import com.khepri.jertweaker.zen.component.JERLootDrop;
import jeresources.api.conditionals.LightLevel;
import jeresources.api.drop.LootDrop;
import jeresources.entry.MobEntry;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public final class ActionRegisterMob implements IUndoableAction {
    private final EntityType<? extends LivingEntity> entityType;
    private final LightLevel lightLevel;
    private final int minExp, maxExp;
    private final String[] biomes;
    private final LootDrop[] drops;

    private MobEntry entry;
    private LivingEntity livingEntity;

    public ActionRegisterMob(@NotNull EntityType<? extends LivingEntity> entityType, @Nullable JERLightLevel lightLevel,
                             int minExp, int maxExp, @NotNull String[] biomes, @Nullable String lootTable) {
        if (minExp < 0 || maxExp < 0)
            throw new IllegalArgumentException("Experience cannot be negative");
        this.entityType = entityType;
        this.lightLevel = lightLevel == null ? null : lightLevel.getInternal();
        this.minExp = minExp;
        this.maxExp = maxExp;
        this.biomes = biomes;
        this.drops = lootTable == null ? null : MobUtils.tableToDrops(lootTable);
    }

    public ActionRegisterMob(@NotNull EntityType<? extends LivingEntity> entityType, @Nullable JERLightLevel lightLevel,
                             int minExp, int maxExp, @NotNull String[] biomes, JERLootDrop[] drops) {
        if (minExp < 0 || maxExp < 0)
            throw new IllegalArgumentException("Experience cannot be negative");
        this.entityType = entityType;
        this.lightLevel = lightLevel == null ? null : lightLevel.getInternal();
        this.minExp = minExp;
        this.maxExp = maxExp;
        this.biomes = biomes;
        this.drops = drops == null ? null : convertDrops(drops);
    }

    public ActionRegisterMob(@NotNull EntityType<? extends LivingEntity> entityType, @Nullable JERLightLevel lightLevel,
                             int minExp, int maxExp, @Nullable String lootTable) {
        if (minExp < 0 || maxExp < 0)
            throw new IllegalArgumentException("Experience cannot be negative");
        this.entityType = entityType;
        this.lightLevel = lightLevel == null ? null : lightLevel.getInternal();
        this.minExp = minExp;
        this.maxExp = maxExp;
        this.biomes = null;
        this.drops = lootTable == null ? null : MobUtils.tableToDrops(lootTable);
    }

    public ActionRegisterMob(@NotNull EntityType<? extends LivingEntity> entityType, @Nullable JERLightLevel lightLevel,
                             int minExp, int maxExp, JERLootDrop[] drops) {
        if (minExp < 0 || maxExp < 0)
            throw new IllegalArgumentException("Experience cannot be negative");
        this.entityType = entityType;
        this.lightLevel = lightLevel == null ? null : lightLevel.getInternal();
        this.minExp = minExp;
        this.maxExp = maxExp;
        this.biomes = null;
        this.drops = drops == null ? null : convertDrops(drops);
    }

    public ActionRegisterMob(@NotNull EntityType<? extends LivingEntity> entityType, @Nullable JERLightLevel lightLevel,
                             @NotNull String[] biomes, @Nullable String lootTable) {
        this.entityType = entityType;
        this.lightLevel = lightLevel == null ? null : lightLevel.getInternal();
        this.minExp = this.maxExp = -1;
        this.biomes = biomes;
        this.drops = lootTable == null ? null : MobUtils.tableToDrops(lootTable);
    }

    public ActionRegisterMob(@NotNull EntityType<? extends LivingEntity> entityType, @Nullable JERLightLevel lightLevel,
                             @NotNull String[] biomes, JERLootDrop[] drops) {
        this.entityType = entityType;
        this.lightLevel = lightLevel == null ? null : lightLevel.getInternal();
        this.minExp =  this.maxExp = -1;
        this.biomes = biomes;
        this.drops = drops == null ? null : convertDrops(drops);
    }

    public ActionRegisterMob(@NotNull EntityType<? extends LivingEntity> entityType, @Nullable JERLightLevel lightLevel,
                             @Nullable String lootTable) {
        this.entityType = entityType;
        this.lightLevel = lightLevel == null ? null : lightLevel.getInternal();
        this.minExp = this.maxExp = -1;
        this.biomes = null;
        this.drops = lootTable == null ? null : MobUtils.tableToDrops(lootTable);
    }

    public ActionRegisterMob(@NotNull EntityType<? extends LivingEntity> entityType, @Nullable JERLightLevel lightLevel,
                             JERLootDrop[] drops) {
        this.entityType = entityType;
        this.lightLevel = lightLevel == null ? null : lightLevel.getInternal();
        this.minExp =  this.maxExp = -1;
        this.biomes = null;
        this.drops = drops == null ? null : convertDrops(drops);
    }

    public ActionRegisterMob(@NotNull EntityType<? extends LivingEntity> entityType, @Nullable String lootTable) {
        this.entityType = entityType;
        this.lightLevel = null;
        this.minExp =  this.maxExp = -1;
        this.biomes = null;
        this.drops = lootTable == null ? null : MobUtils.tableToDrops(lootTable);
    }

    public ActionRegisterMob(@NotNull EntityType<? extends LivingEntity> entityType, JERLootDrop[] drops) {
        this.entityType = entityType;
        this.lightLevel = null;
        this.minExp =  this.maxExp = -1;
        this.biomes = null;
        this.drops = drops == null ? null : convertDrops(drops);
    }

    @Override
    public void apply() { MobState.addEntity(entityType, this::makeEntry); }

    @Override
    public void undo() { MobState.undoAddEntity(entityType); }

    @Override
    public String describe() { return "Registering mob " + Objects.requireNonNull(entityType.getRegistryName()); }

    @Override
    public String describeUndo() {
        return "Undoing registering of mob " + Objects.requireNonNull(entityType.getRegistryName());
    }

    @Override
    public boolean shouldApplyOn(IScriptLoadSource source) {
        return Services.DISTRIBUTION.getDistributionType().isClient();
    }

    @NotNull
    private static LootDrop[] convertDrops(@NotNull JERLootDrop[] drops) {
        LootDrop[] converted = new LootDrop[drops.length];
        for (int i = 0; i < drops.length; ++i)
            converted[i] = drops[i].getInternal();
        return converted;
    }

    @NotNull
    private LivingEntity doSpawn() {
        if (livingEntity == null)
            livingEntity = MobUtils.spawn(entityType);
        return livingEntity;
    }

    @NotNull
    private MobEntry makeEntry() {
        if (entry == null) {
            if (minExp == -1 && maxExp == -1) {
                if (biomes == null) {
                    entry = drops == null
                            ? MobEntry.create(this::doSpawn, lightLevel)
                            : MobEntry.create(this::doSpawn, lightLevel, drops);
                } else
                    entry = drops == null
                            ? MobEntry.create(this::doSpawn, lightLevel, biomes)
                            : MobEntry.create(this::doSpawn, lightLevel, biomes, drops);
            } else {
                if (biomes == null) {
                    entry = drops == null
                            ? MobEntry.create(this::doSpawn, lightLevel, minExp, maxExp)
                            : MobEntry.create(this::doSpawn, lightLevel, minExp, maxExp, drops);
                } else {
                    entry = drops == null
                            ? MobEntry.create(this::doSpawn, lightLevel, minExp, maxExp, biomes)
                            : MobEntry.create(this::doSpawn, lightLevel, minExp, maxExp, biomes, drops);
                }
            }
        }
        return entry;
    }
}
