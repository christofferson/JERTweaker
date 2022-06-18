package com.khepri.jertweaker.actions;

import com.blamejared.crafttweaker.api.action.base.IUndoableAction;
import com.blamejared.crafttweaker.api.zencode.IScriptLoadSource;
import com.blamejared.crafttweaker.platform.Services;
import com.khepri.jertweaker.state.DungeonState;
import org.jetbrains.annotations.NotNull;

public final class ActionUnregisterDungeonChest implements IUndoableAction {
    private final String name;

    public ActionUnregisterDungeonChest(@NotNull String name) { this.name = name; }

    @Override
    public void apply() { DungeonState.removeChest(name); }

    @Override
    public void undo() { DungeonState.undoRemoveChest(name); }

    @Override
    public String describe() { return "Unregistering chest with name " + name; }

    @Override
    public String describeUndo() { return "Undo unregistering of chest with name " + name; }

    @Override
    public boolean shouldApplyOn(IScriptLoadSource source) {
        return Services.DISTRIBUTION.getDistributionType().isClient();
    }
}
