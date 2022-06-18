package com.khepri.jertweaker.actions;

import com.blamejared.crafttweaker.api.action.base.IRuntimeAction;
import com.blamejared.crafttweaker.api.zencode.IScriptLoadSource;
import com.blamejared.crafttweaker.platform.Services;
import com.khepri.jertweaker.util.DungeonUtils;
import org.jetbrains.annotations.NotNull;

public final class ActionRegisterDungeonTranslation implements IRuntimeAction {
    private final String name, localization;

    public ActionRegisterDungeonTranslation(@NotNull String name, @NotNull String localization) {
        this.name = name;
        this.localization = localization;
    }

    @Override
    public void apply() { DungeonUtils.registerTranslation(name, localization); }

    @Override
    public String describe() { return "Registering dungeon chest name translation for " + name; }

    @Override
    public boolean shouldApplyOn(IScriptLoadSource source) {
        return Services.DISTRIBUTION.getDistributionType().isClient();
    }
}
