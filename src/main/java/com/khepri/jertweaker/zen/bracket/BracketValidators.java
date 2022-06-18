package com.khepri.jertweaker.zen.bracket;

import com.blamejared.crafttweaker.api.annotation.BracketValidator;
import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import com.blamejared.crafttweaker.platform.Services;
import com.khepri.jertweaker.zen.component.JERConditional;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import org.openzen.zencode.java.ZenCodeType;

@ZenRegister
@ZenCodeType.Name("mods.jer.bracket.BracketValidators")
public final class BracketValidators {

    @ZenCodeType.Method
    @BracketValidator("moblightlevel")
    public static boolean validateLightLevel(final String tokens) {
        return switch (tokens) {
            case "any", "bat", "hostile", "blaze" -> true;
            default -> tokens.matches("(?:above|below):-?\\d+");
        };
    }

    @ZenCodeType.Method
    @BracketValidator("dropcondition")
    public static boolean validateCondition(final String tokens) {
        final String[] split = tokens.split(":");
        JERConditional.ConditionalPair pair = JERConditional.textMap.get(split[0]);
        if (pair == null) return false;
        return split.length == (pair.extended() ? 2 : 1);
    }

    @ZenCodeType.Method
    @BracketValidator("biome")
    public static boolean validateBiome(final String tokens) {
        return Services.REGISTRY.biomes().containsKey(new ResourceLocation(tokens));
    }

    @ZenCodeType.Method
    @BracketValidator("biomecat")
    public static boolean validateBiomeCategory(final String tokens) {
        return Biome.BiomeCategory.byName(tokens) != null;
    }
}
