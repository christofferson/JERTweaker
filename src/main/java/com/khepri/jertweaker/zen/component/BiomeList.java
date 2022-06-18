package com.khepri.jertweaker.zen.component;

import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import net.minecraft.world.level.biome.Biome;
import org.openzen.zencode.java.ZenCodeType;

import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

@ZenRegister
@ZenCodeType.Name("mods.jer.component.BiomeList")
public final class BiomeList implements IBiomeList {
    private final Collection<Biome> biomes;
    BiomeList(Collection<Biome> biomes) {
        this.biomes = biomes;
    }

    @Override
    public Collection<Biome> getBiomes() { return List.copyOf(biomes); }

    @Override
    public IBiomeList merge(IBiomeList other) {
        return new BiomeList(Stream.concat(biomes.stream(), other.getBiomes().stream()).toList());
    }
}
