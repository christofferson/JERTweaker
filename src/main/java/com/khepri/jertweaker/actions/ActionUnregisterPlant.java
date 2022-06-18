package com.khepri.jertweaker.actions;

import com.blamejared.crafttweaker.api.action.base.IUndoableAction;
import com.blamejared.crafttweaker.api.zencode.IScriptLoadSource;
import com.blamejared.crafttweaker.platform.Services;
import com.khepri.jertweaker.state.PlantState;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;

public final class ActionUnregisterPlant implements IUndoableAction {
    private final ItemStack plant;

    public ActionUnregisterPlant(ItemStack plant) { this.plant = plant; }

    public ActionUnregisterPlant(Block plant) { this.plant = new ItemStack(plant); }

    @Override
    public void apply() { PlantState.removePlant(plant); }

    @Override
    public void undo() { PlantState.undoRemovePlant(plant); }

    @Override
    public String describe() { return "Unregistering plant " + plant.getItem().getRegistryName(); }

    @Override
    public String describeUndo() { return "Undo unregistering plant " + plant.getItem().getRegistryName(); }

    @Override
    public boolean shouldApplyOn(IScriptLoadSource source) {
        return Services.DISTRIBUTION.getDistributionType().isClient();
    }
}
