package com.khepri.jertweaker.zen;

import com.blamejared.crafttweaker.api.CraftTweakerAPI;
import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import com.blamejared.crafttweaker.api.item.IItemStack;
import com.khepri.jertweaker.actions.ActionAddOreDrops;
import com.khepri.jertweaker.actions.ActionRegisterOre;
import com.khepri.jertweaker.actions.ActionUnregisterOre;
import com.khepri.jertweaker.zen.component.JERDistribution;
import com.khepri.jertweaker.zen.component.JERLootDrop;
import com.khepri.jertweaker.zen.component.JERRestriction;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.openzen.zencode.java.ZenCodeType;

@ZenRegister
@ZenCodeType.Name("mods.jer.WorldGenRegistryManager")
public final class WorldGenRegistryManager {

    @ZenCodeType.Method
    public static void register(@NotNull IItemStack block, @NotNull JERDistribution distribution, JERLootDrop[] drops) {
        register(block, distribution, null, false, drops);
    }

    @ZenCodeType.Method
    public static void register(@NotNull IItemStack block, @NotNull JERDistribution distribution,
                                boolean silkTouch, JERLootDrop[] drops) {
        register(block, distribution, null, silkTouch, drops);
    }

    @ZenCodeType.Method
    public static void register(@NotNull IItemStack block, @NotNull JERDistribution distribution,
                                @Nullable JERRestriction restriction, JERLootDrop[] drops) {
        register(block, distribution, restriction, false, drops);
    }

    @ZenCodeType.Method
    public static void register(@NotNull IItemStack block, @NotNull JERDistribution distribution,
                                @Nullable JERRestriction restriction, boolean silkTouch, JERLootDrop[] drops) {
        CraftTweakerAPI.apply(new ActionRegisterOre(block.getInternal(), distribution, restriction, silkTouch, drops));
    }

    @ZenCodeType.Method
    public static void registerDrops(@NotNull IItemStack block, JERLootDrop[] drops) {
        CraftTweakerAPI.apply(new ActionAddOreDrops(block, drops));
    }

    @ZenCodeType.Method
    public static void unregister(@NotNull IItemStack block) { unregister(block, null); }

    @ZenCodeType.Method
    public static void unregister(@NotNull IItemStack block, @Nullable JERRestriction restriction) {
        CraftTweakerAPI.apply(new ActionUnregisterOre(block, restriction));
    }
}
