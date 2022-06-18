package com.khepri.jertweaker.zen;

import com.blamejared.crafttweaker.api.CraftTweakerAPI;
import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import com.khepri.jertweaker.zen.component.PropertyWrapper;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.state.properties.*;
import org.jetbrains.annotations.NotNull;
import org.openzen.zencode.java.ZenCodeType;

import java.util.Arrays;
import java.util.Locale;

@ZenRegister
@ZenCodeType.Name("mods.jer.util.PropertyHelper")
public class PropertyHelper {

    @ZenCodeType.Method
    public static PropertyWrapper blockStateProperty(@NotNull String name) {
        name = name.toUpperCase(Locale.ENGLISH);
        try {
            final java.lang.reflect.Field field = BlockStateProperties.class.getField(name);
            final Object value = field.get(null);
            if (value instanceof Property<?> property)
                return new PropertyWrapper(property);
            CraftTweakerAPI.LOGGER.warn("{} is not a Property instance", name);
            throw new IllegalArgumentException(name + " is not a Property instance");
        } catch(NoSuchFieldException|IllegalAccessException e) {
            CraftTweakerAPI.LOGGER.warn("The property {} could not be found in BlockStateProperties",
                    name);
            throw new IllegalArgumentException("The property " + name + "could not be found in BlockStateProperties");
        }
    }

    @ZenCodeType.Method
    public static PropertyWrapper booleanProperty(@NotNull String name) {
        return new PropertyWrapper(BooleanProperty.create(name));
    }

    @ZenCodeType.Method
    public static PropertyWrapper integerProperty(@NotNull String name, int min, int max) {
        return new PropertyWrapper(IntegerProperty.create(name, min, max));
    }

    @ZenCodeType.Method
    public static PropertyWrapper directionProperty(@NotNull String name) {
        return new PropertyWrapper(DirectionProperty.create(name));
    }

    @ZenCodeType.Method
    public static PropertyWrapper directionProperty(@NotNull String name, @NotNull String[] directions) {
        final Direction[] allowed = Arrays.stream(directions)
                .map(d -> d.toLowerCase(Locale.ENGLISH))
                .distinct()
                .map(PropertyHelper::directionFromString)
                .toList().toArray(new Direction[0]);
        return new PropertyWrapper(DirectionProperty.create(name, allowed));
    }

    private static Direction directionFromString(@NotNull String name) {
        Direction output;
        switch(name) {
            case "up" -> output = Direction.UP;
            case "down" -> output = Direction.DOWN;
            case "north" -> output = Direction.NORTH;
            case "south" -> output = Direction.SOUTH;
            case "east" -> output = Direction.EAST;
            case "west" -> output = Direction.WEST;
            default -> throw new IllegalArgumentException(name + " is not a valid direction");
        }
        return output;
    }
}
