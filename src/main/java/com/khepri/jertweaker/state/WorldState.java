package com.khepri.jertweaker.state;

import com.blamejared.crafttweaker.api.CraftTweakerAPI;
import com.khepri.jertweaker.util.WorldUtils;
import jeresources.api.drop.LootDrop;
import jeresources.api.restrictions.Restriction;
import jeresources.entry.WorldGenEntry;
import jeresources.util.MapKeys;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public final class WorldState {
    private record RestrictedBlock(ItemStack block, Restriction restriction){}
    private record BonusDrops(ItemStack block, LootDrop[] drops){}

    private static final List<RestrictedBlock> removedOres = new LinkedList<>();
    private static final List<WorldGenEntry> addedOres = new LinkedList<>();
    private static final List<BonusDrops> addedOreDrops = new LinkedList<>();

    public static void removeOre(@NotNull ItemStack block, @Nullable Restriction restriction) {
        removedOres.add(new RestrictedBlock(block, restriction));
    }
    public static void undoRemoveOre(@NotNull ItemStack block, @Nullable Restriction restriction) {
        RestrictedBlock entry = null;
        for(RestrictedBlock removed : removedOres) {
            if (removed.block == block && removed.restriction == restriction) {
                // Because this is an "undo" function checking the instances are the same should be fine
                entry = removed;
                break;
            }
        }
        if (entry != null)
            removedOres.remove(entry);
    }

    public static void addOre(@NotNull WorldGenEntry entry) { addedOres.add(entry); }
    public static void undoAddOre(@NotNull WorldGenEntry entry) { addedOres.remove(entry); }

    public static void addOreDrops(@NotNull ItemStack block, LootDrop[] drops) {
        addedOreDrops.add(new BonusDrops(block, drops));
    }

    public static void commit() {
        removedOres.forEach(rb -> {
            if (rb.restriction == null) {
                Collection<WorldGenEntry> entries = WorldUtils.getEntries(rb.block);
                if (entries.size() == 0)
                    CraftTweakerAPI.LOGGER.warn("Could not find any registry entries for {}",
                            rb.block.getItem().getRegistryName());
                else
                    entries.forEach(WorldUtils::unregisterEntry);
            } else {
                WorldGenEntry entry = WorldUtils.getEntry(rb.block, rb.restriction);
                if (entry == null)
                    CraftTweakerAPI.LOGGER.warn("Could not find registry entry for {}",
                            MapKeys.getKey(rb.block, rb.restriction));
                else
                    WorldUtils.unregisterEntry(entry);
            }
        });
        addedOres.forEach(WorldUtils::registerEntry);
        addedOreDrops.forEach(d -> WorldUtils.addDrops(d.block, d.drops));
    }
}
