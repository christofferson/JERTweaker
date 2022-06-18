package com.khepri.jertweaker.zen.component;

import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import com.blamejared.crafttweaker.api.item.IItemStack;
import jeresources.api.drop.PlantDrop;
import org.jetbrains.annotations.NotNull;
import org.openzen.zencode.java.ZenCodeType;

@ZenRegister
@ZenCodeType.Name("mods.jer.component.PlantDrop")
public final class JERPlantDrop {
    private final PlantDrop wrapped;
    private JERPlantDrop(PlantDrop drop) { wrapped = drop; }
    public PlantDrop getInternal() { return wrapped; }

    @ZenCodeType.Method
    public static JERPlantDrop from(@NotNull IItemStack stack) {
        return new JERPlantDrop(new PlantDrop(stack.getInternal(), stack.getAmount(), stack.getAmount()));
    }

    @ZenCodeType.Method
    public static JERPlantDrop from(@NotNull IItemStack stack, float chance) {
        return new JERPlantDrop(new PlantDrop(stack.getInternal(), chance));
    }

    @ZenCodeType.Method
    public static JERPlantDrop from(@NotNull IItemStack stack, int minDrop, int maxDrop) {
        return new JERPlantDrop(new PlantDrop(stack.getInternal(), minDrop, maxDrop));
    }
}
