package com.khepri.jertweaker.util;

import com.blamejared.crafttweaker.api.CraftTweakerAPI;
import jeresources.api.drop.LootDrop;
import jeresources.api.restrictions.Restriction;
import jeresources.entry.WorldGenEntry;
import jeresources.registry.WorldGenRegistry;
import jeresources.util.MapKeys;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;
import java.util.Map;
import java.util.Objects;

public final class WorldUtils {
    private WorldUtils() {}
    private static Map<String, WorldGenEntry> oreRegistry;

    @SuppressWarnings("unchecked")
    private static void init() {
        if (oreRegistry != null)
            return;
        try {
            final java.lang.reflect.Field registryField = WorldGenRegistry.class.getDeclaredField("worldGenMap");
            registryField.setAccessible(true);
            oreRegistry = (Map<String, WorldGenEntry>)registryField.get(WorldGenRegistry.getInstance());
        } catch(NoSuchFieldException | IllegalAccessException e) {
            CraftTweakerAPI.LOGGER.error("Could not initialize world utilities: {}", e.getMessage());
            throw new IllegalStateException("Error initializing world utilities", e);
        }
    }

    @NotNull
    public static Collection<WorldGenEntry> getEntries(@NotNull ItemStack block) {
        init();
        final ResourceLocation blockName = Objects.requireNonNull(block.getItem().getRegistryName());
        return oreRegistry.values().stream()
                .filter(e -> blockName.equals(e.getBlock().getItem().getRegistryName()))
                .toList();
    }

    @Nullable
    public static WorldGenEntry getEntry(@NotNull ItemStack block, @NotNull Restriction restriction) {
        return oreRegistry.get(MapKeys.getKey(block, restriction));
    }

    public static void registerEntry(@NotNull WorldGenEntry entry) {
        init();
        oreRegistry.put(MapKeys.getKey(entry), entry);
    }

    public static void unregisterEntry(@NotNull WorldGenEntry entry) {
        init();
        oreRegistry.remove(MapKeys.getKey(entry));
    }

    public static void addDrops(@NotNull ItemStack block, LootDrop[] drops) {
        WorldGenRegistry.getInstance().addDrops(block, drops);
    }
}
