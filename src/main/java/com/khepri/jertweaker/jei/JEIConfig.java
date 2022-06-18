package com.khepri.jertweaker.jei;

import com.khepri.jertweaker.JERTweaker;
import com.khepri.jertweaker.state.DungeonState;
import com.khepri.jertweaker.state.MobState;
import com.khepri.jertweaker.state.PlantState;
import com.khepri.jertweaker.state.WorldState;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

@JeiPlugin
public final class JEIConfig implements IModPlugin {

    @Override
    public @NotNull ResourceLocation getPluginUid() { return new ResourceLocation(JERTweaker.MOD_ID); }

    @Override
    public void registerCategories(@NotNull IRecipeCategoryRegistration registration) {
        DungeonState.commit();
        MobState.commit();
        PlantState.commit();
        WorldState.commit();
    }
}
