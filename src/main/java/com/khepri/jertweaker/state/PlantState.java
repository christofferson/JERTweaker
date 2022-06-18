package com.khepri.jertweaker.state;

import com.blamejared.crafttweaker.api.CraftTweakerAPI;
import com.khepri.jertweaker.util.PlantUtils;
import jeresources.entry.PlantEntry;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.LinkedList;
import java.util.List;

public final class PlantState {
    private PlantState() {}

    private static final List<ItemStack> removedPlants = new LinkedList<>();
    private static final List<PlantEntry> addedPlants = new LinkedList<>();

    public static void removePlant(@NotNull ItemStack plant) { removedPlants.add(plant); }
    public static void undoRemovePlant(@NotNull ItemStack plant) { removedPlants.remove(plant); }

    public static void addPlant(@NotNull PlantEntry entry) { addedPlants.add(entry); }
    public static void undoAddPlant(@NotNull PlantEntry entry) { addedPlants.remove(entry); }

    public static void commit() {
        removedPlants.forEach(plant -> {
            final PlantEntry entry = PlantUtils.getEntry(plant);
            if (entry == null)
                CraftTweakerAPI.LOGGER.warn("Could not find plant {} in the registry",
                        plant.getItem().getRegistryName());
            else
                PlantUtils.unregisterEntry(entry);
        });
        addedPlants.forEach(PlantUtils::registerEntry);
    }
}
