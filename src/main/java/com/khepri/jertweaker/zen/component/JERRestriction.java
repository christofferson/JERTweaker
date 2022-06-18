package com.khepri.jertweaker.zen.component;

import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import jeresources.api.restrictions.BiomeRestriction;
import jeresources.api.restrictions.DimensionRestriction;
import jeresources.api.restrictions.Restriction;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import org.openzen.zencode.java.ZenCodeType;

import java.util.List;

@ZenRegister
@ZenCodeType.Name("mods.jer.component.WorldRestriction")
public final class JERRestriction {
    private final Restriction wrapped;
    private JERRestriction(Restriction restriction) { wrapped = restriction; }
    public Restriction getInternal() { return wrapped; }

    @ZenCodeType.Method
    public static JERRestriction dimension(String name) {
        return biomesInDimension(name, null);
    }

    @ZenCodeType.Method
    public static JERRestriction notDimension(String name) {
        return biomesNotDimension(name, null);
    }

    @ZenCodeType.Method
    public static JERRestriction biomes(IBiomeList biomes) {
        return biomesInDimension(null, biomes);
    }

    @ZenCodeType.Method
    public static JERRestriction biomesInDimension(String dimension, IBiomeList biomes) {
        return makeRestriction(Restriction.Type.WHITELIST, dimension, biomes);
    }

    @ZenCodeType.Method
    public static JERRestriction biomesNotDimension(String dimension, IBiomeList biomes) {
        return makeRestriction(Restriction.Type.BLACKLIST, dimension, biomes);
    }

    private static JERRestriction makeRestriction(Restriction.Type dimensionType, String dimension,
                                                  IBiomeList biomes) {
        Restriction restriction;
        if (dimension == null) {
            restriction = biomes == null
                    ? Restriction.NONE
                    : new Restriction(makeBiome(biomes));
        } else {
            restriction = biomes == null
                    ? new Restriction(makeDimension(dimensionType, dimension))
                    : new Restriction(makeBiome(biomes), makeDimension(dimensionType, dimension));
        }
        return new JERRestriction(restriction);
    }

    @SuppressWarnings("unchecked")
    private static DimensionRestriction makeDimension(Restriction.Type type, String name) {
        final ResourceLocation location = new ResourceLocation(name);
        final String key = (Registry.DIMENSION_REGISTRY.location() + ":" + location).intern();
        ResourceKey<?> value = ResourceKey.VALUES.get(key);
        if (value == null)
            throw new IllegalArgumentException("No dimension registered with the name " + name);

        return new DimensionRestriction(type, (ResourceKey<Level>) value);
    }

    private static BiomeRestriction makeBiome(IBiomeList biomes) {
        final List<Biome> allBiomes = List.copyOf(biomes.getBiomes());
        final int size = allBiomes.size();
        if (size == 0)
            return BiomeRestriction.NO_RESTRICTION;
        final Biome first = allBiomes.get(0);
        final Biome[] extra = new Biome[size - 1];
        for (int i = 0; i < extra.length; ++i)
            extra[i] = allBiomes.get(i + 1);
        return new BiomeRestriction(first, extra);
    }
}
