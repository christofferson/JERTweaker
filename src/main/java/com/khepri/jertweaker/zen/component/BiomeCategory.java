package com.khepri.jertweaker.zen.component;

import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import com.blamejared.crafttweaker.api.bracket.CommandStringDisplayable;
import jeresources.api.util.BiomeHelper;
import net.minecraft.world.level.biome.Biome;
import org.openzen.zencode.java.ZenCodeType;

import java.util.Collection;
import java.util.stream.Stream;

@ZenRegister
@ZenCodeType.Name("mods.jer.component.BiomeCategory")
public final class BiomeCategory implements IBiomeList, CommandStringDisplayable {
    private final Biome.BiomeCategory wrapped;
    public BiomeCategory(Biome.BiomeCategory category) { wrapped = category; }

    @Override
    public Collection<Biome> getBiomes() { return BiomeHelper.getBiomes(wrapped); }

    @Override
    public IBiomeList merge(IBiomeList other) {
        return new BiomeList(Stream.concat(getBiomes().stream(), other.getBiomes().stream()).toList());
    }

    @Override
    public String getCommandString() { return "<biomecat:" + wrapped.getName() + ">"; }
}
