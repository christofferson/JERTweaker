package com.khepri.jertweaker.zen.component;

import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import net.minecraft.world.level.block.state.properties.Property;
import org.openzen.zencode.java.ZenCodeType;

@ZenRegister
@ZenCodeType.Name("mods.jer.component.Property")
public class PropertyWrapper {
    private final Property<?> wrapped;
    public PropertyWrapper(Property<?> property) { wrapped = property; }
    public Property<?> getInternal() { return wrapped; }
}
