package com.khepri.jertweaker.zen;

import com.blamejared.crafttweaker.api.CraftTweakerAPI;
import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import com.blamejared.crafttweaker.api.item.IItemStack;
import com.khepri.jertweaker.actions.ActionRegisterPlant;
import com.khepri.jertweaker.actions.ActionUnregisterPlant;
import com.khepri.jertweaker.zen.component.JERPlantDrop;
import com.khepri.jertweaker.zen.component.PropertyWrapper;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;
import org.openzen.zencode.java.ZenCodeType;

@ZenRegister
@ZenCodeType.Name("mods.jer.PlantRegistryManager")
public final class PlantRegistryManager {

    @ZenCodeType.Method
    public static void register(@NotNull Block plant, @NotNull BlockState soil, @NotNull PropertyWrapper ageProperty,
                                JERPlantDrop[] drops) {
        CraftTweakerAPI.apply(new ActionRegisterPlant(plant, soil, ageProperty.getInternal(), drops));
    }

    @ZenCodeType.Method
    public static void register(@NotNull Block plant, @NotNull PropertyWrapper ageProperty, JERPlantDrop[] drops) {
        CraftTweakerAPI.apply(new ActionRegisterPlant(plant, null, ageProperty.getInternal(), drops));
    }

    @ZenCodeType.Method
    public static void register(@NotNull Block plant, @NotNull BlockState soil, JERPlantDrop[] drops) {
        CraftTweakerAPI.apply(new ActionRegisterPlant(plant, soil, null, drops));
    }

    @ZenCodeType.Method
    public static void register(@NotNull Block plant, JERPlantDrop[] drops) {
        CraftTweakerAPI.apply(new ActionRegisterPlant(plant, null, null, drops));
    }

    @ZenCodeType.Method
    public static void register(@NotNull BlockState plantState, @NotNull BlockState soil,
                                @NotNull PropertyWrapper ageProperty, JERPlantDrop[] drops) {
        CraftTweakerAPI.apply(new ActionRegisterPlant(plantState, soil, ageProperty.getInternal(), drops));
    }

    @ZenCodeType.Method
    public static void register(@NotNull BlockState plantState, @NotNull PropertyWrapper ageProperty,
                                JERPlantDrop[] drops) {
        CraftTweakerAPI.apply(new ActionRegisterPlant(plantState, null, ageProperty.getInternal(), drops));
    }

    @ZenCodeType.Method
    public static void register(@NotNull BlockState plantState, @NotNull BlockState soil, JERPlantDrop[] drops) {
        CraftTweakerAPI.apply(new ActionRegisterPlant(plantState, soil, null, drops));
    }

    @ZenCodeType.Method
    public static void register(@NotNull BlockState plantState, JERPlantDrop[] drops) {
        CraftTweakerAPI.apply(new ActionRegisterPlant(plantState, null, null, drops));
    }

    @ZenCodeType.Method
    public static void unregister(@NotNull IItemStack plant) {
        CraftTweakerAPI.apply(new ActionUnregisterPlant(plant.getInternal()));
    }

    @ZenCodeType.Method
    public static void unregister(@NotNull Block plant) { CraftTweakerAPI.apply(new ActionUnregisterPlant(plant)); }
}
