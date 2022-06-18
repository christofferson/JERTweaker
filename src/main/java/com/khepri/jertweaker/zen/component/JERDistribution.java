package com.khepri.jertweaker.zen.component;

import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import jeresources.api.distributions.DistributionBase;
import jeresources.api.distributions.DistributionCustom;
import jeresources.api.distributions.DistributionHelpers;
import org.jetbrains.annotations.NotNull;
import org.openzen.zencode.java.ZenCodeType;

@ZenRegister
@ZenCodeType.Name("mods.jer.component.WorldDistribution")
public final class JERDistribution {
    private final float[] values;
    private DistributionCustom wrapper;
    private JERDistribution(float[] values) { this.values = values; }

    @NotNull
    public DistributionBase getInternal() {
        if (wrapper == null)
            wrapper = new DistributionCustom(values);
        return wrapper;
    }

    @ZenCodeType.Method
    public static JERDistribution square(int minY, int maxY, float chance) {
        return new JERDistribution(DistributionHelpers.getSquareDistribution(
                checkBounds(minY), checkBounds(maxY), chance));
    }

    @ZenCodeType.Method
    public static JERDistribution square(int start, int minY, int maxY, int end, float chance) {
        return new JERDistribution(DistributionHelpers.getRoundedSquareDistribution(
                checkBounds(start), checkBounds(minY), checkBounds(maxY), checkBounds(end), chance));
    }

    @ZenCodeType.Method
    public static JERDistribution square(int veinCount, int veinSize, int minY, int maxY) {
        return square(minY - veinSize / 2, minY, maxY, maxY + veinSize / 2,
                DistributionHelpers.calculateChance(veinCount, veinSize, minY, maxY));
    }

    @ZenCodeType.Method
    public static JERDistribution triangle(int midY, int range, float chance) {
        return new JERDistribution(DistributionHelpers.getTriangularDistribution(midY, range, chance));
    }

    @ZenCodeType.Method
    public static JERDistribution triangle(int veinCount, int veinSize, int midY, int range) {
        return new JERDistribution(DistributionHelpers.getTriangularDistribution(midY, range,
                DistributionHelpers.calculateChance(veinCount, veinSize, midY - range, midY + range)));
    }

    @ZenCodeType.Method
    public static JERDistribution triangle(int minY, int maxY, int plateau, float chance) {
        final int base = Math.abs(maxY - minY);
        if (plateau < 1)
            plateau = 1;
        if (plateau >= base)
            return square(minY, maxY, chance);
        final int leg1 = (int)Math.ceil((base - plateau + 1) / 2d),
                leg2 = (int)Math.floor((base + plateau - 1) / 2d);
        return new JERDistribution(DistributionHelpers.getTriangularDistribution(minY, leg1, leg2, chance));
    }

    @ZenCodeType.Method
    public static JERDistribution triangle(int veinCount, int veinSize, int minY, int maxY, int plateau) {
        return triangle(minY, maxY, plateau, DistributionHelpers.calculateChance(veinCount, veinSize, minY, maxY));
    }


    @ZenCodeType.Operator(ZenCodeType.OperatorType.OR)
    public JERDistribution combine(JERDistribution other) {
        final float[] combined = new float[values.length];
        for (int i = 0; i < values.length; ++i)
            combined[i] = values[i] + other.values[i];
        return new JERDistribution(combined);
    }

    private static int checkBounds(int value) { return Math.max(Math.min(value, 255), -64); }
}
