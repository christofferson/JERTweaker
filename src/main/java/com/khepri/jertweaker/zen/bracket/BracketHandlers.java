package com.khepri.jertweaker.zen.bracket;

import com.blamejared.crafttweaker.api.CraftTweakerAPI;
import com.blamejared.crafttweaker.api.annotation.BracketResolver;
import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import com.blamejared.crafttweaker.platform.Services;
import com.khepri.jertweaker.zen.component.SingleBiome;
import com.khepri.jertweaker.zen.component.BiomeCategory;
import com.khepri.jertweaker.zen.component.JERConditional;
import com.khepri.jertweaker.zen.component.JERLightLevel;
import net.minecraft.resources.ResourceLocation;
import org.openzen.zencode.java.ZenCodeType;

import java.util.Locale;

@ZenRegister
@ZenCodeType.Name("mods.jer.bracket.BracketHandlers")
public final class BracketHandlers {

    @ZenCodeType.Method
    @BracketResolver("moblightlevel")
    public static JERLightLevel getLightLevel(final String tokens) {
        if (!tokens.toLowerCase(Locale.ENGLISH).equals(tokens))
            CraftTweakerAPI.LOGGER.warn(
                    "MobLightLevel BEP <moblightlevel:{}> does not seem to be lower-cased!", tokens);

        switch(tokens) {
            case "any":
                return JERLightLevel.any;
            case "bat":
                return JERLightLevel.bat;
            case "hostile":
                return JERLightLevel.hostile;
            case "blaze":
                return JERLightLevel.blaze;
        }

        if (!tokens.matches("(?:above|below):-?\\d+"))
            throw new IllegalArgumentException("Could not get light level from <moblightlevel:"
                    + tokens +">! Syntax is <moblightlevel:fixedlevel> or <moblightlevel:reltype:value>");

        final String[] split = tokens.split(":");
        return new JERLightLevel(split[0], Integer.parseInt(split[1]));
    }

    @ZenCodeType.Method
    @BracketResolver("dropcondition")
    public static JERConditional getCondition(final String tokens) {
        final String[] split = tokens.split(":");
        if (split.length == 1)
            return JERConditional.standard(split[0]);
        else if (split.length == 2)
            return JERConditional.extended(split[0], split[1]);
        else
            throw new IllegalArgumentException("<dropcondition:" + tokens + "> is not a valid condition");
    }

    @ZenCodeType.Method
    @BracketResolver("biome")
    public static SingleBiome getBiome(final String tokens) {
        return new SingleBiome(Services.REGISTRY.biomes()
                .getOptional(new ResourceLocation(tokens))
                .orElseThrow(() -> new IllegalArgumentException(
                        "Could not get biome with name: <biome:" + tokens + ">")));
    }

    @ZenCodeType.Method
    @BracketResolver("biomecat")
    public static BiomeCategory getBiomeCategory(final String tokens) {
        net.minecraft.world.level.biome.Biome.BiomeCategory category = net.minecraft.world.level.biome.Biome.BiomeCategory.byName(tokens);
        if (category == null)
            throw new IllegalArgumentException("Could not get biome category with name: <biomecat:" + tokens + ">");
        return new BiomeCategory(category);
    }
}
