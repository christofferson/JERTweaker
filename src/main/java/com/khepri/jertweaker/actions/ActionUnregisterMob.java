package com.khepri.jertweaker.actions;

import com.blamejared.crafttweaker.api.action.base.IUndoableAction;
import com.blamejared.crafttweaker.api.zencode.IScriptLoadSource;
import com.blamejared.crafttweaker.platform.Services;
import com.khepri.jertweaker.state.MobState;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public final class ActionUnregisterMob implements IUndoableAction {

    private final EntityType<? extends LivingEntity> entityType;

    public ActionUnregisterMob(@NotNull EntityType<? extends LivingEntity> entityType) { this.entityType = entityType; }

    @Override
    public void apply() { MobState.removeEntity(entityType); }

    @Override
    public void undo() { MobState.undoRemoveEntity(entityType); }

    @Override
    public String describe() { return "Unregistering mob " + Objects.requireNonNull(entityType.getRegistryName()); }

    @Override
    public String describeUndo() {
        return "Undo unregistering of mob " + Objects.requireNonNull(entityType.getRegistryName());
    }

    @Override
    public boolean shouldApplyOn(IScriptLoadSource source) {
        return Services.DISTRIBUTION.getDistributionType().isClient();
    }
}
