package com.khepri.jertweaker.actions;

import com.blamejared.crafttweaker.api.action.base.IUndoableAction;
import com.blamejared.crafttweaker.api.zencode.IScriptLoadSource;
import com.blamejared.crafttweaker.platform.Services;
import com.khepri.jertweaker.state.WorldState;
import com.khepri.jertweaker.zen.component.JERDistribution;
import com.khepri.jertweaker.zen.component.JERLootDrop;
import com.khepri.jertweaker.zen.component.JERRestriction;
import jeresources.api.drop.LootDrop;
import jeresources.api.restrictions.Restriction;
import jeresources.entry.WorldGenEntry;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class ActionRegisterOre implements IUndoableAction {
    private final WorldGenEntry entry;

    public ActionRegisterOre(@NotNull ItemStack block, @NotNull JERDistribution distribution,
                             @Nullable JERRestriction restriction, boolean silkTouch, JERLootDrop[] drops) {
        final LootDrop[] internalDrops = drops == null ? new LootDrop[0] : convertDrops(drops);
        entry = new WorldGenEntry(block, distribution.getInternal(),
                restriction == null ? Restriction.OVERWORLD : restriction.getInternal(), silkTouch, internalDrops);
    }

    @Override
    public void apply() {
        WorldState.addOre(entry);
    }

    @Override
    public void undo() {
        WorldState.undoAddOre(entry);
    }

    @Override
    public String describe() {
        return "Registering worldgen for " + entry.getBlock().getItem().getRegistryName();
    }

    @Override
    public String describeUndo() {
        return "Undo registering worldgen for " + entry.getBlock().getItem().getRegistryName();
    }

    @Override
    public boolean shouldApplyOn(IScriptLoadSource source) {
        return Services.DISTRIBUTION.getDistributionType().isClient();
    }

    @NotNull
    private static LootDrop[] convertDrops(@NotNull JERLootDrop[] drops) {
        LootDrop[] converted = new LootDrop[drops.length];
        for (int i = 0; i < drops.length; ++i)
            converted[i] = drops[i].getInternal();
        return converted;
    }
}
