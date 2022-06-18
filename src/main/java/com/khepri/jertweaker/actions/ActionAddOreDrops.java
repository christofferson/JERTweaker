package com.khepri.jertweaker.actions;

import com.blamejared.crafttweaker.api.action.base.IRuntimeAction;
import com.blamejared.crafttweaker.api.item.IItemStack;
import com.blamejared.crafttweaker.api.zencode.IScriptLoadSource;
import com.blamejared.crafttweaker.platform.Services;
import com.khepri.jertweaker.state.WorldState;
import com.khepri.jertweaker.zen.component.JERLootDrop;
import jeresources.api.drop.LootDrop;
import net.minecraft.world.item.ItemStack;

public final class ActionAddOreDrops implements IRuntimeAction {
    private final ItemStack block;
    private final LootDrop[] drops;

    public ActionAddOreDrops(IItemStack block, JERLootDrop[] drops) {
        this.block = block.getInternal();
        if (drops == null)
            this.drops = new LootDrop[0];
        else {
            this.drops = new LootDrop[drops.length];
            for (int i = 0; i < drops.length; ++i)
                this.drops[i] = drops[i].getInternal();
        }
    }

    @Override
    public void apply() { WorldState.addOreDrops(block, drops); }

    @Override
    public String describe() { return "Adding more drops to block " + block.getItem().getRegistryName(); }

    @Override
    public boolean shouldApplyOn(IScriptLoadSource source) {
        return Services.DISTRIBUTION.getDistributionType().isClient();
    }
}
