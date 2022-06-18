package com.khepri.jertweaker.zen.component;

import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import com.blamejared.crafttweaker.api.bracket.CommandStringDisplayable;
import net.minecraft.world.level.biome.Biome;
import org.openzen.zencode.java.ZenCodeType;

import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

@ZenRegister
@ZenCodeType.Name("mods.jer.component.Biome")
public final class SingleBiome implements IBiomeList, CommandStringDisplayable {
    private final net.minecraft.world.level.biome.Biome wrapped;
    public SingleBiome(Biome biome) { wrapped = biome; }

    @Override
    public Collection<net.minecraft.world.level.biome.Biome> getBiomes() { return List.of(wrapped); }

    @Override
    public IBiomeList merge(IBiomeList other) {
        return new BiomeList(Stream.concat(Stream.of(wrapped), other.getBiomes().stream()).toList());
    }

    @Override
    public String getCommandString() { return "<biome:" + wrapped.getRegistryName() + ">"; }
}
