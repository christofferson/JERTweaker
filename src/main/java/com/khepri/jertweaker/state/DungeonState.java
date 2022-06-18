package com.khepri.jertweaker.state;

import com.blamejared.crafttweaker.api.CraftTweakerAPI;
import com.khepri.jertweaker.util.DungeonUtils;
import jeresources.entry.DungeonEntry;
import jeresources.util.LootTableHelper;
import net.minecraft.world.level.storage.loot.LootTables;

import java.util.*;
import java.util.function.Function;

public final class DungeonState {
    private static final List<String> removedChests = new LinkedList<>();
    private static final Map<String, Function<LootTables, DungeonEntry>> addedChests = new LinkedHashMap<>();

    public static void removeChest(String name) { removedChests.add(name); }
    public static void undoRemoveChest(String name) { removedChests.remove(name); }

    public static void addChest(String name, Function<LootTables, DungeonEntry> generator) {
        addedChests.put(name, generator);
    }
    public static void undoAddChest(String name) { addedChests.remove(name); }

    public static void commit() {
        removedChests.forEach(name -> {
            final DungeonEntry entry = DungeonUtils.getEntry(name);
            if (entry == null)
                CraftTweakerAPI.LOGGER.warn("Could not find chest with name \"{}\" in the registry", name);
            else
                DungeonUtils.unregisterEntry(entry);
        });
        final LootTables lootTables = LootTableHelper.getLootTables();
        addedChests.values().stream()
                .map(g -> g.apply(lootTables))
                .filter(Objects::nonNull)
                .forEach(DungeonUtils::registerEntry);
    }
}
