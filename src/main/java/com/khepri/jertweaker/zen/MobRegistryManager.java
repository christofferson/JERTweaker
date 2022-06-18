package com.khepri.jertweaker.zen;

import com.blamejared.crafttweaker.api.CraftTweakerAPI;
import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import com.khepri.jertweaker.actions.ActionRegisterMob;
import com.khepri.jertweaker.actions.ActionUnregisterMob;
import com.khepri.jertweaker.zen.component.JERLightLevel;
import com.khepri.jertweaker.zen.component.JERLootDrop;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.openzen.zencode.java.ZenCodeType;

@ZenRegister
@ZenCodeType.Name("mods.jer.MobRegistryManager")
public final class MobRegistryManager {

    @ZenCodeType.Method
    public static void register(@NotNull EntityType<? extends LivingEntity> entityType,
                                @Nullable JERLightLevel lightLevel, int minExp, int maxExp, @NotNull String[] biomes,
                                @Nullable String lootTable) {
        CraftTweakerAPI.apply(new ActionRegisterMob(entityType, lightLevel, minExp, maxExp, biomes, lootTable));
    }

    @ZenCodeType.Method
    public static void register(@NotNull EntityType<? extends LivingEntity> entityType,
                                @Nullable JERLightLevel lightLevel, int minExp, int maxExp, @NotNull String[] biomes,
                                @Nullable JERLootDrop[] drops) {
        CraftTweakerAPI.apply(new ActionRegisterMob(entityType, lightLevel, minExp, maxExp, biomes, drops));
    }

    @ZenCodeType.Method
    public static void register(@NotNull EntityType<? extends LivingEntity> entityType,
                                @Nullable JERLightLevel lightLevel, int minExp, int maxExp,
                                @Nullable String lootTable) {
        CraftTweakerAPI.apply(new ActionRegisterMob(entityType, lightLevel, minExp, maxExp, lootTable));
    }

    @ZenCodeType.Method
    public static void register(@NotNull EntityType<? extends LivingEntity> entityType,
                                @Nullable JERLightLevel lightLevel, int minExp, int maxExp,
                                @Nullable JERLootDrop[] drops) {
        CraftTweakerAPI.apply(new ActionRegisterMob(entityType, lightLevel, minExp, maxExp, drops));
    }

    @ZenCodeType.Method
    public static void register(@NotNull EntityType<? extends LivingEntity> entityType,
                                @Nullable JERLightLevel lightLevel, int exp, @NotNull String[] biomes,
                                @Nullable String lootTable) {
        CraftTweakerAPI.apply(new ActionRegisterMob(entityType, lightLevel, exp, exp, biomes, lootTable));
    }

    @ZenCodeType.Method
    public static void register(@NotNull EntityType<? extends LivingEntity> entityType,
                                @Nullable JERLightLevel lightLevel, int exp, @NotNull String[] biomes,
                                @Nullable JERLootDrop[] drops) {
        CraftTweakerAPI.apply(new ActionRegisterMob(entityType, lightLevel, exp, exp, biomes, drops));
    }

    @ZenCodeType.Method
    public static void register(@NotNull EntityType<? extends LivingEntity> entityType,
                                @Nullable JERLightLevel lightLevel, int exp, @Nullable String lootTable) {
        CraftTweakerAPI.apply(new ActionRegisterMob(entityType, lightLevel, exp, exp, lootTable));
    }

    @ZenCodeType.Method
    public static void register(@NotNull EntityType<? extends LivingEntity> entityType,
                                @Nullable JERLightLevel lightLevel, int exp, @Nullable JERLootDrop[] drops) {
        CraftTweakerAPI.apply(new ActionRegisterMob(entityType, lightLevel, exp, exp, drops));
    }

    @ZenCodeType.Method
    public static void register(@NotNull EntityType<? extends LivingEntity> entityType,
                                @Nullable JERLightLevel lightLevel, @NotNull String[] biomes,
                                @Nullable String lootTable) {
        CraftTweakerAPI.apply(new ActionRegisterMob(entityType, lightLevel, biomes, lootTable));
    }

    @ZenCodeType.Method
    public static void register(@NotNull EntityType<? extends LivingEntity> entityType,
                                @Nullable JERLightLevel lightLevel, @NotNull String[] biomes,
                                @Nullable JERLootDrop[] drops) {
        CraftTweakerAPI.apply(new ActionRegisterMob(entityType, lightLevel, biomes, drops));
    }

    @ZenCodeType.Method
    public static void register(@NotNull EntityType<? extends LivingEntity> entityType,
                                @Nullable JERLightLevel lightLevel, @Nullable String lootTable) {
        CraftTweakerAPI.apply(new ActionRegisterMob(entityType, lightLevel, lootTable));
    }

    @ZenCodeType.Method
    public static void register(@NotNull EntityType<? extends LivingEntity> entityType,
                                @Nullable JERLightLevel lightLevel, @Nullable JERLootDrop[] drops) {
        CraftTweakerAPI.apply(new ActionRegisterMob(entityType, lightLevel, drops));
    }

    @ZenCodeType.Method
    public static void register(@NotNull EntityType<? extends LivingEntity> entityType, @Nullable String lootTable) {
        CraftTweakerAPI.apply(new ActionRegisterMob(entityType, lootTable));
    }

    @ZenCodeType.Method
    public static void register(@NotNull EntityType<? extends LivingEntity> entityType, @Nullable JERLootDrop[] drops) {
        CraftTweakerAPI.apply(new ActionRegisterMob(entityType, drops));
    }

    @ZenCodeType.Method
    public static void unregister(@NotNull EntityType<? extends LivingEntity> entityType) {
        CraftTweakerAPI.apply(new ActionUnregisterMob(entityType));
    }
}
