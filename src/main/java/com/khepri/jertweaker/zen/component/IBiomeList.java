package com.khepri.jertweaker.zen.component;

import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import jeresources.api.util.BiomeHelper;
import net.minecraft.world.level.biome.Biome;
import org.openzen.zencode.java.ZenCodeType;

import java.util.Collection;

@ZenRegister
@ZenCodeType.Name("mods.jer.component.IBiomeList")
public interface IBiomeList {
    Collection<Biome> getBiomes();

    @ZenCodeType.Operator(ZenCodeType.OperatorType.OR)
    IBiomeList merge(IBiomeList other);

    @ZenCodeType.Operator(ZenCodeType.OperatorType.INVERT)
    default IBiomeList invert() {
        final Collection<Biome> currentBiomes = getBiomes();
        return new BiomeList(
            BiomeHelper.getAllBiomes().stream()
                    .filter(b -> !currentBiomes.contains(b))
                    .toList()
        );
    }
}
