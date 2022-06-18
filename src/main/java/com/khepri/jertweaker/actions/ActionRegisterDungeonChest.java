package com.khepri.jertweaker.actions;

import com.blamejared.crafttweaker.api.CraftTweakerAPI;
import com.blamejared.crafttweaker.api.action.base.IUndoableAction;
import com.blamejared.crafttweaker.api.zencode.IScriptLoadSource;
import com.blamejared.crafttweaker.platform.Services;
import com.khepri.jertweaker.state.DungeonState;
import jeresources.entry.DungeonEntry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.LootTables;
import org.jetbrains.annotations.NotNull;

public final class ActionRegisterDungeonChest implements IUndoableAction {
    private final String name, tableLocation;
    private DungeonEntry entry;

    public ActionRegisterDungeonChest(@NotNull String name, @NotNull String tableLocation) {
        this.name = name;
        this.tableLocation = tableLocation;
    }

    @Override
    public void apply() { DungeonState.addChest(name, this::makeEntry); }

    @Override
    public void undo() { DungeonState.undoAddChest(name); }

    @Override
    public String describe() { return "Registering loot table for " + name; }

    @Override
    public String describeUndo() { return "Undoing registration of loot table for " + name; }

    @Override
    public boolean shouldApplyOn(IScriptLoadSource source) {
        return Services.DISTRIBUTION.getDistributionType().isClient();
    }

    private DungeonEntry makeEntry(LootTables lootTables) {
        if (entry == null) {
            try {
                entry = new DungeonEntry(name, lootTables.get(new ResourceLocation(tableLocation)));
            } catch (Exception e) {
                CraftTweakerAPI.LOGGER.warn("Bad dungeon chest registry for name \"{}\"", name);
            }
        }
        return entry;
    }
}
