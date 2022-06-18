package com.khepri.jertweaker.zen.component;

import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import com.blamejared.crafttweaker.api.bracket.CommandStringDisplayable;
import jeresources.api.conditionals.Conditional;
import jeresources.api.conditionals.ExtendedConditional;
import net.minecraft.client.resources.language.I18n;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.openzen.zencode.java.ZenCodeType;

import java.util.Map;

@ZenRegister
@ZenCodeType.Name("mods.jer.component.Conditional")
public final class JERConditional implements CommandStringDisplayable {
    public static final JERConditional magmaCream = new JERConditional(Conditional.magmaCream);
    public static final JERConditional slimeBall = new JERConditional(Conditional.slimeBall);
    public static final JERConditional rareDrop = new JERConditional(Conditional.rareDrop);
    public static final JERConditional silkTouch = new JERConditional(Conditional.silkTouch);
    public static final JERConditional equipmentDrop = new JERConditional(Conditional.equipmentDrop);
    public static final JERConditional affectedByLooting = new JERConditional(Conditional.affectedByLooting);
    public static final JERConditional affectedByFortune = new JERConditional(Conditional.affectedByFortune);
    public static final JERConditional powered = new JERConditional(Conditional.powered);
    public static final JERConditional burning = new JERConditional(Conditional.burning);
    public static final JERConditional notBurning = new JERConditional(Conditional.notBurning);
    public static final JERConditional wet = new JERConditional(Conditional.wet);
    public static final JERConditional notWet = new JERConditional(Conditional.notWet);
    public static final JERConditional hasPotion = new JERConditional(Conditional.hasPotion); // Extended
    public static final JERConditional hasNoPotion = new JERConditional(Conditional.hasNoPotion); // Extended
    public static final JERConditional beyond = new JERConditional(Conditional.beyond); // Extended
    public static final JERConditional nearer = new JERConditional(Conditional.nearer); // Extended
    public static final JERConditional raining = new JERConditional(Conditional.raining);
    public static final JERConditional dry = new JERConditional(Conditional.dry);
    public static final JERConditional thundering = new JERConditional(Conditional.thundering);
    public static final JERConditional notThundering = new JERConditional(Conditional.notThundering);
    public static final JERConditional moonPhase = new JERConditional(Conditional.moonPhase); // Extended
    public static final JERConditional notMoonPhase = new JERConditional(Conditional.notMoonPhase); // Extended
    public static final JERConditional pastTime = new JERConditional(Conditional.pastTime); // Extended
    public static final JERConditional beforeTime = new JERConditional(Conditional.beforeTime); // Extended
    public static final JERConditional pastWorldTime = new JERConditional(Conditional.pastWorldTime); // Extended
    public static final JERConditional beforeWorldTime = new JERConditional(Conditional.beforeWorldTime); // Extended
    public static final JERConditional pastWorldDifficulty = new JERConditional(Conditional.pastWorldDifficulty); // Extended
    public static final JERConditional beforeWorldDifficulty = new JERConditional(Conditional.beforeWorldDifficulty); // Extended
    public static final JERConditional gameDifficulty = new JERConditional(Conditional.gameDifficulty); // Extended
    public static final JERConditional notGameDifficulty = new JERConditional(Conditional.notGameDifficulty); // Extended
    public static final JERConditional inDimension = new JERConditional(Conditional.inDimension); // Extended
    public static final JERConditional notInDimension = new JERConditional(Conditional.notInDimension); // Extended
    public static final JERConditional inBiome = new JERConditional(Conditional.inBiome); // Extended
    public static final JERConditional notInBiome = new JERConditional(Conditional.notInBiome); // Extended
    public static final JERConditional onBlock = new JERConditional(Conditional.onBlock); // Extended
    public static final JERConditional notOnBlock = new JERConditional(Conditional.notOnBlock); // Extended
    public static final JERConditional below = new JERConditional(Conditional.below); // Extended
    public static final JERConditional above = new JERConditional(Conditional.above); // Extended
    public static final JERConditional playerOnline = new JERConditional(Conditional.playerOnline); // Extended
    public static final JERConditional playerOffline = new JERConditional(Conditional.playerOffline); // Extended
    public static final JERConditional playerKill = new JERConditional(Conditional.playerKill);
    public static final JERConditional notPlayerKill = new JERConditional(Conditional.notPlayerKill);
    public static final JERConditional aboveLooting = new JERConditional(Conditional.aboveLooting); // Extended
    public static final JERConditional belowLooting = new JERConditional(Conditional.belowLooting); // Extended
    public static final JERConditional killedBy = new JERConditional(Conditional.killedBy); // Extended
    public static final JERConditional notKilledBy = new JERConditional(Conditional.notKilledBy); // Extended

    public record ConditionalPair(JERConditional conditional, boolean extended){}
    public static final Map<String, ConditionalPair> textMap;

    static {
        textMap = Map.ofEntries(
                Map.entry("magmaCream", new ConditionalPair(magmaCream, false)),
                Map.entry("slimeBall", new ConditionalPair(slimeBall, false)),
                Map.entry("rareDrop", new ConditionalPair(rareDrop, false)),
                Map.entry("silkTouch", new ConditionalPair(silkTouch, false)),
                Map.entry("equipmentDrop", new ConditionalPair(equipmentDrop, false)),
                Map.entry("affectedByLooting", new ConditionalPair(affectedByLooting, false)),
                Map.entry("affectedByFortune", new ConditionalPair(affectedByFortune, false)),
                Map.entry("powered", new ConditionalPair(powered, false)),
                Map.entry("burning", new ConditionalPair(burning, false)),
                Map.entry("notBurning", new ConditionalPair(notBurning, false)),
                Map.entry("wet", new ConditionalPair(wet, false)),
                Map.entry("notWet", new ConditionalPair(notWet, false)),
                Map.entry("hasPotion", new ConditionalPair(hasPotion, true)),
                Map.entry("hasNoPotion", new ConditionalPair(hasNoPotion, true)),
                Map.entry("beyond", new ConditionalPair(beyond, true)),
                Map.entry("nearer", new ConditionalPair(nearer, true)),
                Map.entry("raining", new ConditionalPair(raining, false)),
                Map.entry("dry", new ConditionalPair(dry, false)),
                Map.entry("thundering", new ConditionalPair(thundering, false)),
                Map.entry("notThundering", new ConditionalPair(notThundering, false)),
                Map.entry("moonPhase", new ConditionalPair(moonPhase, true)),
                Map.entry("notMoonPhase", new ConditionalPair(notMoonPhase, true)),
                Map.entry("pastTime", new ConditionalPair(pastTime, true)),
                Map.entry("beforeTime", new ConditionalPair(beforeTime, true)),
                Map.entry("pastWorldTime", new ConditionalPair(pastWorldTime, true)),
                Map.entry("beforeWorldTime", new ConditionalPair(beforeWorldTime, true)),
                Map.entry("pastWorldDifficulty", new ConditionalPair(pastWorldDifficulty, true)),
                Map.entry("beforeWorldDifficulty", new ConditionalPair(beforeWorldDifficulty, true)),
                Map.entry("gameDifficulty", new ConditionalPair(gameDifficulty, true)),
                Map.entry("notGameDifficulty", new ConditionalPair(notGameDifficulty, true)),
                Map.entry("inDimension", new ConditionalPair(inDimension, true)),
                Map.entry("notInDimension", new ConditionalPair(notInDimension, true)),
                Map.entry("inBiome", new ConditionalPair(inBiome, true)),
                Map.entry("notInBiome", new ConditionalPair(notInBiome, true)),
                Map.entry("onBlock", new ConditionalPair(onBlock, true)),
                Map.entry("notOnBlock", new ConditionalPair(notOnBlock, true)),
                Map.entry("below", new ConditionalPair(below, true)),
                Map.entry("above", new ConditionalPair(above, true)),
                Map.entry("playerOnline", new ConditionalPair(playerOnline, true)),
                Map.entry("playerOffline", new ConditionalPair(playerOffline, true)),
                Map.entry("playerKill", new ConditionalPair(playerKill, false)),
                Map.entry("notPlayerKill", new ConditionalPair(notPlayerKill, false)),
                Map.entry("aboveLooting", new ConditionalPair(aboveLooting, true)),
                Map.entry("belowLooting", new ConditionalPair(belowLooting, true)),
                Map.entry("killedBy", new ConditionalPair(killedBy, true)),
                Map.entry("notKilledBy", new ConditionalPair(notKilledBy, true))
        );
    }

    private final Conditional source, wrapped;
    private final String param;
    private JERConditional(@NotNull Conditional wrapped) {
        this(wrapped, null, wrapped);
    }
    private JERConditional(@NotNull Conditional source, @Nullable String param, @NotNull Conditional wrapped) {
        this.source = source;
        this.wrapped = wrapped;
        this.param = param;
    }
    public Conditional getInternal() { return wrapped; }

    @NotNull
    public static JERConditional standard(String conditional) {
        ConditionalPair pair = textMap.get(conditional);
        if (pair == null)
            throw new IllegalArgumentException("No conditional with the name\"" + conditional + "\"");
        return pair.conditional;
    }

    @NotNull
    public static JERConditional extended(String conditional, String key) {
        ConditionalPair pair = textMap.get(conditional);
        if (pair == null)
            throw new IllegalArgumentException("No conditional with the name\"" + conditional + "\"");
        if (!pair.extended)
            throw new IllegalArgumentException("\"" + conditional + "\" is not an extended conditional");
        return new JERConditional(
                pair.conditional.source, key,
                new ExtendedConditional(pair.conditional.source, I18n.get(key))
        );
    }

    @Override
    public String getCommandString() {
        StringBuilder builder = new StringBuilder("<dropcondition:");
        boolean extended = false;
        for (Map.Entry<String, ConditionalPair> entry : textMap.entrySet()) {
            if (entry.getValue().conditional.source == this.source) {
                builder.append(entry.getKey());
                extended = entry.getValue().extended;
                break;
            }
        }
        if (param != null) {
            builder.append(':');
            builder.append(param);
        } else if (extended) {
            builder.append(':');
            builder.append("mod.lang.key");
        }
        builder.append('>');
        return builder.toString();
    }
}
