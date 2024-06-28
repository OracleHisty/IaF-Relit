package com.github.alexthe666.iceandfire.block;

import com.github.alexthe666.iceandfire.entity.DragonType;
import net.minecraftforge.registries.RegistryObject;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public record DragonForge(RegistryObject<BlockDragonforgeBricks> bricks, RegistryObject<BlockDragonforgeInput> input, RegistryObject<BlockDragonforgeCore> core, RegistryObject<BlockDragonforgeCore> coreDisabled) {
    private static final Map<DragonType, DragonForge> FORGE_VARIANTS = new HashMap<>();

    public static void of(DragonType type) {
        var bricks = IafBlockRegistry.register("dragonforge_%s_brick".formatted(type.getSerializedName()), () -> new BlockDragonforgeBricks(type));
        var input = IafBlockRegistry.register("dragonforge_%s_input".formatted(type.getSerializedName()), () -> new BlockDragonforgeInput(type));
        var core = IafBlockRegistry.register("dragonforge_%s_core".formatted(type.getSerializedName()), () -> new BlockDragonforgeCore(type, true));
        var coreDisabled = IafBlockRegistry.register("dragonforge_%s_core_disabled".formatted(type.getSerializedName()), () -> new BlockDragonforgeCore(type, false));

        var dragonForge = new DragonForge(bricks, input, core, coreDisabled);
        FORGE_VARIANTS.put(type, dragonForge);
    }

    public static Collection<DragonForge> forges() {
        return FORGE_VARIANTS.values();
    }

    public static DragonForge getForge(DragonType type) {
        return FORGE_VARIANTS.get(type);
    }

    public static BlockDragonforgeCore getCore(DragonType type, boolean active) {
        var dragonForge = getForge(type);

        return (active ? dragonForge.core() : dragonForge.coreDisabled()).get();
    }
}
