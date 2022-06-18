package com.khepri.jertweaker.util;

import com.blamejared.crafttweaker.api.CraftTweakerAPI;
import jeresources.entry.PlantEntry;
import jeresources.registry.PlantRegistry;
import jeresources.util.MapKeys;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Map;

public final class PlantUtils {
    private PlantUtils() {}
    private static Map<String, PlantEntry> plantRegistry;

    @SuppressWarnings("unchecked")
    private static void init() {
        if (plantRegistry != null)
            return;
        try {
            final java.lang.reflect.Field registryField = PlantRegistry.class.getDeclaredField("registry");
            registryField.setAccessible(true);
            plantRegistry = (Map<String, PlantEntry>)registryField.get(PlantRegistry.getInstance());
        } catch(NoSuchFieldException | IllegalAccessException e) {
            CraftTweakerAPI.LOGGER.error("Could not initialize plant utilities: {}", e.getMessage());
            throw new IllegalStateException("Error initializing plant utilities", e);
        }
    }

    @Nullable
    public static PlantEntry getEntry(@NotNull ItemStack plant) {
        init();
        final String key = MapKeys.getKey(plant);
        if (key == null)
            return null;
        return plantRegistry.get(key);
    }

    public static void registerEntry(@NotNull PlantEntry entry) {
        init();
        final String key = MapKeys.getKey(entry.getPlantItemStack());
        if (key != null)
            plantRegistry.put(key, entry);
    }

    public static void unregisterEntry(@NotNull PlantEntry entry) {
        init();
        final String key = MapKeys.getKey(entry.getPlantItemStack());
        if (key != null)
            plantRegistry.remove(key);
    }
}
