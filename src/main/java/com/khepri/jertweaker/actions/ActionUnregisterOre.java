package com.khepri.jertweaker.actions;

import com.blamejared.crafttweaker.api.action.base.IUndoableAction;
import com.blamejared.crafttweaker.api.item.IItemStack;
import com.blamejared.crafttweaker.api.zencode.IScriptLoadSource;
import com.blamejared.crafttweaker.platform.Services;
import com.khepri.jertweaker.state.WorldState;
import com.khepri.jertweaker.zen.component.JERRestriction;
import jeresources.api.restrictions.Restriction;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class ActionUnregisterOre implements IUndoableAction {
    private final ItemStack block;
    private final Restriction restriction;

    public ActionUnregisterOre(@NotNull IItemStack block, @Nullable JERRestriction restriction) {
        this.block = block.getInternal();
        this.restriction = restriction == null ? null : restriction.getInternal();
    }

    @Override
    public void apply() {
        WorldState.removeOre(block, restriction);
    }

    @Override
    public void undo() {
        WorldState.undoRemoveOre(block, restriction);
    }

    @Override
    public String describe() {
        return "Unregistering worldgen for " + block.getItem().getRegistryName();
    }

    @Override
    public String describeUndo() {
        return "Undo unregistering worldgen for " + block.getItem().getRegistryName();
    }

    @Override
    public boolean shouldApplyOn(IScriptLoadSource source) {
        return Services.DISTRIBUTION.getDistributionType().isClient();
    }
}
