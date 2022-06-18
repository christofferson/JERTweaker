package com.khepri.jertweaker.zen.component;

import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import com.blamejared.crafttweaker.api.item.IItemStack;
import jeresources.api.conditionals.Conditional;
import jeresources.api.drop.LootDrop;
import org.jetbrains.annotations.NotNull;
import org.openzen.zencode.java.ZenCodeType;

@ZenRegister
@ZenCodeType.Name("mods.jer.component.LootDrop")
public final class JERLootDrop {
    private final LootDrop wrapped;
    private JERLootDrop(LootDrop drop) { wrapped = drop; }
    public LootDrop getInternal() { return wrapped; }

    @ZenCodeType.Method
    public static JERLootDrop from(@NotNull IItemStack stack) {
        return from(stack, stack.getAmount());
    }

    @ZenCodeType.Method
    public static JERLootDrop from(@NotNull IItemStack stack, float chance) {
        return from(stack, chance, 0);
    }

    @ZenCodeType.Method
    public static JERLootDrop from(@NotNull IItemStack stack, float chance, int fortuneLevel) {
        return from(stack, (int)Math.floor(chance), (int)Math.ceil(chance), chance, fortuneLevel, null);
    }

    @ZenCodeType.Method
    public static JERLootDrop from(@NotNull IItemStack stack, int minDrop, int maxDrop, float chance, int fortuneLevel,
                                   JERConditional[] conditionals) {
        if (conditionals != null) {
            final Conditional[] internalConditions = new Conditional[conditionals.length];
            for (int i = 0; i < conditionals.length; ++i)
                internalConditions[i] = conditionals[i].getInternal();

            return new JERLootDrop(new LootDrop(
                    stack.getInternal(), minDrop, maxDrop, chance, fortuneLevel, internalConditions
            ));
        } else
            return new JERLootDrop(new LootDrop(stack.getInternal(), minDrop, maxDrop, chance, fortuneLevel));
    }
}
