package com.khepri.jertweaker.util;

import com.blamejared.crafttweaker.api.CraftTweakerAPI;
import jeresources.api.drop.LootDrop;
import jeresources.entry.MobEntry;
import jeresources.registry.MobRegistry;
import jeresources.util.LootTableHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;
import java.util.Set;

public final class MobUtils {
    private MobUtils() {}
    private static Set<MobEntry> mobRegistry;

    @SuppressWarnings("unchecked")
    private static void init() {
        if (mobRegistry != null)
            return;
        try {
            final java.lang.reflect.Field registryField = MobRegistry.class.getDeclaredField("registry");
            registryField.setAccessible(true);
            mobRegistry = (Set<MobEntry>)registryField.get(MobRegistry.getInstance());
        } catch(NoSuchFieldException | IllegalAccessException e) {
            CraftTweakerAPI.LOGGER.error("Could not initialize mob utilities: {}", e.getMessage());
            throw new IllegalStateException("Error initializing mob utilities", e);
        }
    }

    @Nullable
    public static MobEntry getEntry(@NotNull EntityType<? extends LivingEntity> entityType) {
        init();
        final ResourceLocation typeName = Objects.requireNonNull(entityType.getRegistryName());
        for (MobEntry entry : mobRegistry) {
            if (entryMatchesTypeName(entry, typeName))
                return entry;
        }
        return null;
    }

    public static void registerEntry(@NotNull MobEntry entry) {
        init();
        mobRegistry.add(entry);
    }

    public static void unregisterEntry(@NotNull MobEntry entry) {
        init();
        mobRegistry.remove(entry);
    }

    @NotNull
    public static LivingEntity spawn(@NotNull EntityType<? extends LivingEntity> entityType) {
        ClientLevel level = Minecraft.getInstance().level;
        if (level == null)
            throw new IllegalStateException("Client level unavailable");
        return Objects.requireNonNull(entityType.create(level));
    }

    @NotNull
    public static LootDrop[] tableToDrops(String tableLocation) {
        return LootTableHelper.toDrops(new ResourceLocation(tableLocation)).toArray(new LootDrop[0]);
    }

    private static boolean entryMatchesTypeName(@NotNull MobEntry entry, @NotNull ResourceLocation typeName) {
        final LivingEntity entity = entry.getEntity();
        final ResourceLocation entityName = entity.getType().getRegistryName();
        return entityName != null && entityName.equals(typeName);
    }
}
