package com.khepri.jertweaker.zen.component;

import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import com.blamejared.crafttweaker.api.bracket.CommandStringDisplayable;
import jeresources.api.conditionals.LightLevel;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.openzen.zencode.java.ZenCodeType;

import java.util.Arrays;

@ZenRegister
@ZenCodeType.Name("mods.jer.component.LightLevel")
public final class JERLightLevel implements CommandStringDisplayable {
    public static final JERLightLevel any, bat, hostile, blaze;
    private static final JERLightLevel[] FIXED_LEVELS;
    private static final java.lang.reflect.Constructor<LightLevel> construct;

    static {
        any = new JERLightLevel(LightLevel.Relative.above, -1, LightLevel.any);
        bat = new JERLightLevel(LightLevel.Relative.below, 4, LightLevel.bat);
        hostile = new JERLightLevel(LightLevel.Relative.below, 8, LightLevel.hostile);
        blaze = new JERLightLevel(LightLevel.Relative.below, 12, LightLevel.blaze);
        FIXED_LEVELS = new JERLightLevel[]{any, bat, hostile, blaze};

        java.lang.reflect.Constructor<LightLevel> temp = null;
        try {
            temp = LightLevel.class.getDeclaredConstructor(int.class, LightLevel.Relative.class);
        } catch(NoSuchMethodException ignored) {}
        construct = temp;
    }


    private final int level;
    private final LightLevel.Relative relative;
    private LightLevel wrapped;

    public JERLightLevel(String relative, int level) {
        switch (relative) {
            case "below" -> this.relative = LightLevel.Relative.below;
            case "above" -> this.relative = LightLevel.Relative.above;
            default -> throw new IllegalArgumentException("\"" + relative + "\" is not a valid relative value.");
        }
        this.level = level;
        this.wrapped = checkInternal();
    }

    private JERLightLevel(LightLevel.Relative relative, int level, LightLevel wrapped) {
        this.relative = relative;
        this.level = level;
        this.wrapped = wrapped;
    }

    @NotNull
    public LightLevel getInternal() {
        if (wrapped == null) {
            if (construct == null)
                throw new IllegalArgumentException("LightLevel constructor unavailable");
            try {
                wrapped = construct.newInstance(this.level, this.relative);
            } catch(InstantiationException|IllegalAccessException|java.lang.reflect.InvocationTargetException e) {
                throw new IllegalArgumentException("Could not construct LightLevel");
            }
        }
        return wrapped;
    }

    @Nullable
    private LightLevel checkInternal() {
        return Arrays.stream(FIXED_LEVELS)
                .filter(l -> l.level == this.level && l.relative == this.relative)
                .map(l -> l.wrapped)
                .findFirst()
                .orElse(null);
    }


    @Override
    public String getCommandString() {
        String tokens;
        if (wrapped == LightLevel.any)
            tokens = "any";
        else if (wrapped == LightLevel.bat)
            tokens = "bat";
        else if (wrapped == LightLevel.hostile)
            tokens = "hostile";
        else if (wrapped == LightLevel.blaze)
            tokens = "blaze";
        else
            tokens = (relative == LightLevel.Relative.below ? "below:" : "above:") + level;

        return "<moblightlevel:" + tokens + ">";
    }
}
