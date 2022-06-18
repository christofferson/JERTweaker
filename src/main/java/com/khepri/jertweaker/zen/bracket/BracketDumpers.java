package com.khepri.jertweaker.zen.bracket;

import com.blamejared.crafttweaker.api.annotation.BracketDumper;
import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import com.khepri.jertweaker.zen.component.JERConditional;
import jeresources.api.util.BiomeHelper;
import net.minecraft.world.level.biome.Biome;
import org.openzen.zencode.java.ZenCodeType;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@ZenRegister
@ZenCodeType.Name("mods.jer.bracket.BracketDumpers")
public final class BracketDumpers {

    @ZenCodeType.Method
    @BracketDumper("moblightlevel")
    public static Collection<String> getLightLevelDump() {
        return List.of(
                "<moblightlevel:any>",
                "<moblightlevel:bat>",
                "<moblightlevel:hostile>",
                "<moblightlevel:blaze>",
                "<moblightlevel:above:7>",
                "<moblightlevel:below:8>"
        );
    }

    @ZenCodeType.Method
    @BracketDumper("dropcondition")
    public static Collection<String> getConditionDump() {
        return JERConditional.textMap.entrySet().stream()
                .map(e -> "<dropcondition:" + e.getKey() + (e.getValue().extended() ? ":mod.lang.key" : "") + ">")
                .collect(Collectors.toList());
    }

    @ZenCodeType.Method
    @BracketDumper("biome")
    public static Collection<String> getBiomeDump() {
        return BiomeHelper.getAllBiomes().stream()
                .map(b -> "<biome:" + b.getRegistryName() + ">")
                .toList();
    }

    @ZenCodeType.Method
    @BracketDumper("biomecat")
    public static Collection<String> getBiomeCategoryDump() {
        return Arrays.stream(Biome.BiomeCategory.values())
                .map(c -> "<biomecat:" + c.getName() + ">")
                .toList();
    }
}
