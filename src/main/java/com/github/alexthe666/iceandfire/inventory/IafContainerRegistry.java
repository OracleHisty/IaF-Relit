package com.github.alexthe666.iceandfire.inventory;

import com.github.alexthe666.iceandfire.IceAndFire;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class IafContainerRegistry {
    public static final DeferredRegister<MenuType<?>> CONTAINERS = DeferredRegister.create(ForgeRegistries.MENU_TYPES, IceAndFire.MODID);

    public static final RegistryObject<MenuType<ContainerLectern>> IAF_LECTERN_CONTAINER = register("iaf_lectern", ContainerLectern::new);
    public static final RegistryObject<MenuType<ContainerPodium>> PODIUM_CONTAINER = register("podium", ContainerPodium::new);
    public static final RegistryObject<MenuType<ContainerDragon>> DRAGON_CONTAINER = register("dragon", ContainerDragon::new);
    public static final RegistryObject<MenuType<ContainerDragonForge>> DRAGON_FORGE_CONTAINER = register("dragon_forge", ContainerDragonForge::new);

    public static <C extends AbstractContainerMenu> RegistryObject<MenuType<C>> register(String name, MenuType.MenuSupplier<C> type) {
        return CONTAINERS.register(name, () -> new MenuType<C>(type, FeatureFlags.VANILLA_SET));
    }
}
