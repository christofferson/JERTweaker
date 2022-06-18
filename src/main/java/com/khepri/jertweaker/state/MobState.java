package com.khepri.jertweaker.state;

import com.blamejared.crafttweaker.api.CraftTweakerAPI;
import com.khepri.jertweaker.util.MobUtils;
import jeresources.entry.MobEntry;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.function.Supplier;

public final class MobState {
    private MobState() {}

    private static final List<EntityType<? extends LivingEntity>> removedEntities = new LinkedList<>();
    private static final Map<EntityType<? extends LivingEntity>, Supplier<MobEntry>> addedEntities =
            new LinkedHashMap<>();

    public static void removeEntity(@NotNull EntityType<? extends LivingEntity> entity) {
        removedEntities.add(entity);
    }
    public static void undoRemoveEntity(@NotNull EntityType<? extends LivingEntity> entity) {
        removedEntities.remove(entity);
    }

    public static void addEntity(@NotNull EntityType<? extends LivingEntity> entity,
                                 @NotNull Supplier<MobEntry> entrySupplier) {
        addedEntities.put(entity, entrySupplier);
    }
    public static void undoAddEntity(@NotNull EntityType<? extends LivingEntity> entity) {
        addedEntities.remove(entity);
    }

    public static void commit() {
        removedEntities.forEach(entity -> {
            final MobEntry entry = MobUtils.getEntry(entity);
            if (entry == null)
                CraftTweakerAPI.LOGGER.warn("Could not find mob {} in the registry",
                        Objects.requireNonNull(entity.getRegistryName()));
            else
                MobUtils.unregisterEntry(entry);
        });
        addedEntities.values().forEach(supplier -> MobUtils.registerEntry(supplier.get()));
    }
}
