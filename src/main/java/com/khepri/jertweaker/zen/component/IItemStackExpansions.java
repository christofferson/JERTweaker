package com.khepri.jertweaker.zen.component;

import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import com.blamejared.crafttweaker.api.item.IItemStack;
import org.openzen.zencode.java.ZenCodeType;

@ZenRegister
@ZenCodeType.Expansion("crafttweaker.api.item.IItemStack")
public final class IItemStackExpansions {

    @ZenCodeType.Caster(implicit = true)
    public static JERLootDrop asLootDrop(final IItemStack stack) {
        return JERLootDrop.from(stack);
    }

    @ZenCodeType.Caster(implicit = true)
    public static JERPlantDrop asPlantDrop(final IItemStack stack) {
        return JERPlantDrop.from(stack);
    }
}
