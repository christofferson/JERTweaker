package com.khepri.jertweaker.actions;

import com.blamejared.crafttweaker.api.CraftTweakerAPI;
import com.blamejared.crafttweaker.api.action.base.IUndoableAction;
import com.blamejared.crafttweaker.api.zencode.IScriptLoadSource;
import com.blamejared.crafttweaker.platform.Services;
import com.khepri.jertweaker.state.PlantState;
import com.khepri.jertweaker.zen.component.JERPlantDrop;
import jeresources.api.drop.PlantDrop;
import jeresources.entry.PlantEntry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraftforge.common.IPlantable;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public class ActionRegisterPlant implements IUndoableAction {
    private final PlantEntry entry;

    public ActionRegisterPlant(@NotNull Block plant, @Nullable BlockState soil, @Nullable Property<?> ageProperty,
                               JERPlantDrop[] drops) {
        if (plant instanceof IPlantable plantable) {
            this.entry = new PlantEntry(new ItemStack(plant), plantable, convertDrops(drops));
            this.entry.setSoil(soil);
            this.entry.setAgeProperty(ageProperty);
        } else {
            final ResourceLocation name = Objects.requireNonNull(plant.getRegistryName());
            CraftTweakerAPI.LOGGER.error("{} is not a plantable block", name);
            throw new IllegalArgumentException(name + " is not a plantable block");
        }
    }

    public ActionRegisterPlant(@NotNull BlockState plantState, @Nullable BlockState soil,
                               @Nullable Property<?> ageProperty, JERPlantDrop[] drops) {
        final Block plant = plantState.getBlock();
        if (plant instanceof IPlantable plantable) {
            this.entry = new PlantEntry(new ItemStack(plant), plantable, convertDrops(drops));
            this.entry.setPlantState(plantState);
            this.entry.setSoil(soil);
            this.entry.setAgeProperty(ageProperty);
        } else {
            final ResourceLocation name = Objects.requireNonNull(plant.getRegistryName());
            CraftTweakerAPI.LOGGER.error("{} is not a plantable block", name);
            throw new IllegalArgumentException(name + " is not a plantable block");
        }
    }

    @Override
    public void apply() { PlantState.addPlant(entry); }

    @Override
    public void undo() { PlantState.undoAddPlant(entry); }

    @Override
    public String describe() { return "Registering plant " + entry.getPlantItemStack().getItem().getRegistryName(); }

    @Override
    public String describeUndo() {
        return "Undo registering plant " + entry.getPlantItemStack().getItem().getRegistryName();
    }

    @Override
    public boolean shouldApplyOn(IScriptLoadSource source) {
        return Services.DISTRIBUTION.getDistributionType().isClient();
    }

    @NotNull
    private static PlantDrop[] convertDrops(@NotNull JERPlantDrop[] drops) {
        PlantDrop[] converted = new PlantDrop[drops.length];
        for (int i = 0; i < drops.length; ++i)
            converted[i] = drops[i].getInternal();
        return converted;
    }
}
