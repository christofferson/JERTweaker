package com.khepri.jertweaker.util;

import com.blamejared.crafttweaker.api.CraftTweakerAPI;
import jeresources.entry.DungeonEntry;
import jeresources.registry.DungeonRegistry;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Map;

public final class DungeonUtils {
    private DungeonUtils() {}
    private static Map<String, DungeonEntry> dungeonRegistry;
    private static java.lang.reflect.Field entryName;

    @SuppressWarnings("unchecked")
    private static void init() {
        if (dungeonRegistry != null)
            return;
        try {
            entryName = DungeonEntry.class.getDeclaredField("name");
            entryName.setAccessible(true);

            final java.lang.reflect.Field registryField = DungeonRegistry.class.getDeclaredField("registry");
            registryField.setAccessible(true);
            dungeonRegistry = (Map<String, DungeonEntry>)registryField.get(DungeonRegistry.getInstance());
        } catch(NoSuchFieldException | IllegalAccessException e) {
            CraftTweakerAPI.LOGGER.error("Could not initialize dungeon utilities: {}", e.getMessage());
            throw new IllegalStateException("Error initializing dungeon utilities", e);
        }
    }

    public static void registerTranslation(@NotNull String name, @NotNull String translation) {
        DungeonRegistry.categoryToLocalKeyMap.put(name, translation);
    }

    @Nullable
    public static DungeonEntry getEntry(@NotNull String name) {
        init();
        DungeonEntry found = null;
        for (DungeonEntry entry : dungeonRegistry.values()) {
            if (name.equalsIgnoreCase(getName(entry))) {
                found = entry;
                break;
            }
        }
        return found;
    }

    public static void registerEntry(@NotNull DungeonEntry entry) {
        init();
        dungeonRegistry.put(entry.getName(), entry);
    }

    public static void unregisterEntry(@NotNull DungeonEntry entry) {
        init();
        dungeonRegistry.remove(entry.getName());
    }

    @Nullable
    private static String getName(@NotNull DungeonEntry entry) {
        // Needed because DungeonEntry.getName() only provides the translation key if available
        try {
            return (String)entryName.get(entry);
        } catch(IllegalAccessException e) {
            return null;
        }
    }
}
