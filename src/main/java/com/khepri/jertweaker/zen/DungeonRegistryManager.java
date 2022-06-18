package com.khepri.jertweaker.zen;

import com.blamejared.crafttweaker.api.CraftTweakerAPI;
import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import com.khepri.jertweaker.actions.ActionRegisterDungeonTranslation;
import com.khepri.jertweaker.actions.ActionRegisterDungeonChest;
import com.khepri.jertweaker.actions.ActionUnregisterDungeonChest;
import org.jetbrains.annotations.NotNull;
import org.openzen.zencode.java.ZenCodeType;

@ZenRegister
@ZenCodeType.Name("mods.jer.DungeonRegistryManager")
public final class DungeonRegistryManager {

    @ZenCodeType.Method
    public static void registerTranslation(@NotNull String name, @NotNull String localization) {
        CraftTweakerAPI.apply(new ActionRegisterDungeonTranslation(name, localization));
    }

    @ZenCodeType.Method
    public static void registerChest(@NotNull String name, @NotNull String tableLocation) {
        CraftTweakerAPI.apply(new ActionRegisterDungeonChest(name, tableLocation));
    }

    @ZenCodeType.Method
    public static void unregisterChest(@NotNull String name) {
        CraftTweakerAPI.apply(new ActionUnregisterDungeonChest(name));
    }
}
