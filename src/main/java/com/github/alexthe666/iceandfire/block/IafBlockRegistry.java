package com.github.alexthe666.iceandfire.block;

import com.github.alexthe666.iceandfire.IceAndFire;
import com.github.alexthe666.iceandfire.entity.DragonType;
import com.github.alexthe666.iceandfire.enums.EnumDragonEgg;
import com.github.alexthe666.iceandfire.item.IafItemRegistry;
import com.github.alexthe666.iceandfire.item.IafTabRegistry;
import com.github.alexthe666.iceandfire.misc.IafSoundRegistry;
import net.minecraft.core.Direction;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.StandingAndWallBlockItem;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.Arrays;
import java.util.function.Supplier;

@Mod.EventBusSubscriber(modid = IceAndFire.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class IafBlockRegistry {

    public static DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, IceAndFire.MODID);

    public static final SoundType SOUND_TYPE_GOLD = new SoundType(1.0F, 1.0F, IafSoundRegistry.GOLD_PILE_BREAK, IafSoundRegistry.GOLD_PILE_STEP, IafSoundRegistry.GOLD_PILE_BREAK, IafSoundRegistry.GOLD_PILE_STEP, IafSoundRegistry.GOLD_PILE_STEP);

    public static final RegistryObject<Block> LECTERN = register("lectern", BlockLectern::new);
    public static final RegistryObject<Block> PODIUM_OAK = register("podium_oak", BlockPodium::new);
    public static final RegistryObject<Block> PODIUM_BIRCH = register("podium_birch", BlockPodium::new);
    public static final RegistryObject<Block> PODIUM_SPRUCE = register("podium_spruce", BlockPodium::new);
    public static final RegistryObject<Block> PODIUM_JUNGLE = register("podium_jungle", BlockPodium::new);
    public static final RegistryObject<Block> PODIUM_DARK_OAK = register("podium_dark_oak", BlockPodium::new);
    public static final RegistryObject<Block> PODIUM_ACACIA = register("podium_acacia", BlockPodium::new);
    public static final RegistryObject<Block> FIRE_LILY = register("fire_lily", BlockElementalFlower::new);
    public static final RegistryObject<Block> FROST_LILY = register("frost_lily", BlockElementalFlower::new);
    public static final RegistryObject<Block> LIGHTNING_LILY = register("lightning_lily", BlockElementalFlower::new);
    public static final RegistryObject<Block> GOLD_PILE = register("gold_pile", BlockGoldPile::new);
    public static final RegistryObject<Block> SILVER_PILE = register("silver_pile", BlockGoldPile::new);
    public static final RegistryObject<Block> COPPER_PILE = register("copper_pile", BlockGoldPile::new);
    public static final RegistryObject<Block> SILVER_ORE = register("silver_ore", () -> new DropExperienceBlock(BlockBehaviour.Properties.of().mapColor(MapColor.STONE).strength(3, 3).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> DEEPSLATE_SILVER_ORE = register("deepslate_silver_ore", () -> new DropExperienceBlock(BlockBehaviour.Properties.of().mapColor(MapColor.DEEPSLATE).strength(3,3).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> SILVER_BLOCK = register("silver_block", () -> BlockGeneric.builder(3.0F, 5.0F, SoundType.METAL, MapColor.METAL, null, null, false));
    public static final RegistryObject<Block> SAPPHIRE_ORE = register("sapphire_ore", () -> new DropExperienceBlock(BlockBehaviour.Properties.of().mapColor(MapColor.STONE).strength(4,3).requiresCorrectToolForDrops(), UniformInt.of(3,7)));
    public static final RegistryObject<Block> SAPPHIRE_BLOCK = register("sapphire_block", () -> BlockGeneric.builder(3.0F, 6.0F, SoundType.METAL, MapColor.METAL, null, null, false));
    public static final RegistryObject<Block> RAW_SILVER_BLOCK = register("raw_silver_block", () -> BlockGeneric.builder(3.0F, 5.0F, SoundType.STONE, MapColor.METAL, NoteBlockInstrument.BASEDRUM, null, false));
    public static final RegistryObject<Block> CHARRED_DIRT = register("chared_dirt", () -> BlockReturningState.builder(0.5F, 0.0F, SoundType.GRAVEL, MapColor.DIRT, null, null, false, Blocks.DIRT.defaultBlockState()));
    public static final RegistryObject<Block> CHARRED_GRASS = register("chared_grass", () -> BlockReturningState.builder(0.6F, 0.0F, SoundType.GRAVEL, MapColor.GRASS, null, null, false, Blocks.GRASS_BLOCK.defaultBlockState()));
    public static final RegistryObject<Block> CHARRED_STONE = register("chared_stone", () -> BlockReturningState.builder(1.5F, 10.0F, SoundType.STONE, MapColor.STONE, NoteBlockInstrument.BASEDRUM, null, false, Blocks.STONE.defaultBlockState()));
    public static final RegistryObject<Block> CHARRED_COBBLESTONE = register("chared_cobblestone", () -> BlockReturningState.builder(2F, 10.0F, SoundType.STONE, MapColor.STONE, NoteBlockInstrument.BASEDRUM, null, false, Blocks.COBBLESTONE.defaultBlockState()));
    public static final RegistryObject<Block> CHARRED_GRAVEL = register("chared_gravel", () -> new BlockFallingReturningState(0.6F, 0F, SoundType.GRAVEL, MapColor.DIRT, Blocks.GRAVEL.defaultBlockState()));
    public static final RegistryObject<Block> CHARRED_DIRT_PATH = register(BlockCharedPath.getNameFromType(0), () -> new BlockCharedPath(0));
    public static final RegistryObject<Block> ASH = register("ash", () -> BlockFallingGeneric.builder(0.5F, 0F, SoundType.SAND, MapColor.SAND, NoteBlockInstrument.SNARE));
    public static final RegistryObject<Block> FROZEN_DIRT = register("frozen_dirt", () -> BlockReturningState.builder(0.5F, 0.0F, SoundType.GLASS, true, MapColor.DIRT, null, null, false, Blocks.DIRT.defaultBlockState()));
    public static final RegistryObject<Block> FROZEN_GRASS = register("frozen_grass", () -> BlockReturningState.builder(0.6F, 0.0F, SoundType.GLASS, true, MapColor.GRASS, null, null, false, Blocks.GRASS_BLOCK.defaultBlockState()));
    public static final RegistryObject<Block> FROZEN_STONE = register("frozen_stone", () -> BlockReturningState.builder(1.5F, 1.0F, SoundType.GLASS, true, MapColor.STONE, NoteBlockInstrument.BASEDRUM, null, false, Blocks.STONE.defaultBlockState()));
    public static final RegistryObject<Block> FROZEN_COBBLESTONE = register("frozen_cobblestone", () -> BlockReturningState.builder(2F, 2.0F, SoundType.GLASS, true, MapColor.STONE, NoteBlockInstrument.BASEDRUM, null, false, Blocks.COBBLESTONE.defaultBlockState()));
    public static final RegistryObject<Block> FROZEN_GRAVEL = register("frozen_gravel", () -> new BlockFallingReturningState(0.6F, 0F, SoundType.GLASS, true, MapColor.DIRT, Blocks.GRAVEL.defaultBlockState()));
    public static final RegistryObject<Block> FROZEN_DIRT_PATH = register(BlockCharedPath.getNameFromType(1), () -> new BlockCharedPath(1));
    public static final RegistryObject<Block> FROZEN_SPLINTERS = register("frozen_splinters", () -> BlockGeneric.builder(2.0F, 1.0F, SoundType.GLASS, true, MapColor.WOOD, NoteBlockInstrument.BASS, null, true));
    public static final RegistryObject<Block> DRAGON_ICE = register("dragon_ice", () -> BlockGeneric.builder(0.5F, 0F, SoundType.GLASS, true, MapColor.ICE, null, null, false));
    public static final RegistryObject<Block> DRAGON_ICE_SPIKES = register("dragon_ice_spikes", BlockIceSpikes::new);
    public static final RegistryObject<Block> CRACKLED_DIRT = register("crackled_dirt", () -> BlockReturningState.builder(0.5F, 0.0F, SoundType.GRAVEL, MapColor.DIRT, null, null, false, Blocks.DIRT.defaultBlockState()));
    public static final RegistryObject<Block> CRACKLED_GRASS = register("crackled_grass", () -> BlockReturningState.builder(0.6F, 0.0F, SoundType.GRAVEL, MapColor.GRASS, null, null, false, Blocks.GRASS_BLOCK.defaultBlockState()));
    public static final RegistryObject<Block> CRACKLED_STONE = register("crackled_stone", () -> BlockReturningState.builder(1.5F, 1.0F, SoundType.STONE, MapColor.STONE, NoteBlockInstrument.BASEDRUM, null, false, Blocks.STONE.defaultBlockState()));
    public static final RegistryObject<Block> CRACKLED_COBBLESTONE = register("crackled_cobblestone", () -> BlockReturningState.builder(2F, 2F, SoundType.STONE, MapColor.STONE, NoteBlockInstrument.BASEDRUM, null, false, Blocks.COBBLESTONE.defaultBlockState()));
    public static final RegistryObject<Block> CRACKLED_GRAVEL = register("crackled_gravel", () -> new BlockFallingReturningState(0.6F, 0F, SoundType.GRAVEL, MapColor.DIRT, Blocks.GRAVEL.defaultBlockState()));
    public static final RegistryObject<Block> CRACKLED_DIRT_PATH = register(BlockCharedPath.getNameFromType(2), () -> new BlockCharedPath(2));

    public static final RegistryObject<Block> NEST = register("nest", () -> BlockGeneric.builder(0.5F, 0F, SoundType.GRAVEL, false, MapColor.PLANT, null, PushReaction.DESTROY, false));

    public static final RegistryObject<Block> DRAGON_SCALE_RED = register("dragonscale_red", () -> new BlockDragonScales(EnumDragonEgg.RED));
    public static final RegistryObject<Block> DRAGON_SCALE_GREEN = register("dragonscale_green", () -> new BlockDragonScales(EnumDragonEgg.GREEN));
    public static final RegistryObject<Block> DRAGON_SCALE_BRONZE = register("dragonscale_bronze", () -> new BlockDragonScales(EnumDragonEgg.BRONZE));
    public static final RegistryObject<Block> DRAGON_SCALE_GRAY = register("dragonscale_gray", () -> new BlockDragonScales(EnumDragonEgg.GRAY));
    public static final RegistryObject<Block> DRAGON_SCALE_BLUE = register("dragonscale_blue", () -> new BlockDragonScales(EnumDragonEgg.BLUE));
    public static final RegistryObject<Block> DRAGON_SCALE_WHITE = register("dragonscale_white", () -> new BlockDragonScales(EnumDragonEgg.WHITE));
    public static final RegistryObject<Block> DRAGON_SCALE_SAPPHIRE = register("dragonscale_sapphire", () -> new BlockDragonScales(EnumDragonEgg.SAPPHIRE));
    public static final RegistryObject<Block> DRAGON_SCALE_SILVER = register("dragonscale_silver", () -> new BlockDragonScales(EnumDragonEgg.SILVER));
    public static final RegistryObject<Block> DRAGON_SCALE_ELECTRIC = register("dragonscale_electric", () -> new BlockDragonScales(EnumDragonEgg.ELECTRIC));
    public static final RegistryObject<Block> DRAGON_SCALE_AMYTHEST = register("dragonscale_amythest", () -> new BlockDragonScales(EnumDragonEgg.AMYTHEST));
    public static final RegistryObject<Block> DRAGON_SCALE_COPPER = register("dragonscale_copper", () -> new BlockDragonScales(EnumDragonEgg.COPPER));
    public static final RegistryObject<Block> DRAGON_SCALE_BLACK = register("dragonscale_black", () -> new BlockDragonScales(EnumDragonEgg.BLACK));
    public static final RegistryObject<Block> DRAGON_BONE_BLOCK = register("dragon_bone_block", BlockDragonBone::new);
    public static final RegistryObject<Block> DRAGON_BONE_BLOCK_WALL = register("dragon_bone_wall", () -> new BlockDragonBoneWall(BlockBehaviour.Properties.copy(IafBlockRegistry.DRAGON_BONE_BLOCK.get())));
    public static final RegistryObject<Block> EGG_IN_ICE = register("egginice", BlockEggInIce::new);
    public static final RegistryObject<Block> DRAGONSTEEL_FIRE_BLOCK = register("dragonsteel_fire_block", () -> BlockGeneric.builder(10.0F, 1000.0F, SoundType.METAL, MapColor.METAL, null, null, false));
    public static final RegistryObject<Block> DRAGONSTEEL_ICE_BLOCK = register("dragonsteel_ice_block", () -> BlockGeneric.builder(10.0F, 1000.0F, SoundType.METAL, MapColor.METAL, null, null, false));
    public static final RegistryObject<Block> DRAGONSTEEL_LIGHTNING_BLOCK = register("dragonsteel_lightning_block", () -> BlockGeneric.builder(10.0F, 1000.0F, SoundType.METAL, MapColor.METAL, null, null, false));
    public static final RegistryObject<TorchBlock> BURNT_TORCH = registerWallBlock("burnt_torch", BlockBurntTorch::new);
    public static final RegistryObject<BlockBurntTorchWall> BURNT_TORCH_WALL = registerWallTorch("burnt_torch_wall", BlockBurntTorchWall::new);

    public static void register(IEventBus modBus) {
        BLOCKS.register(modBus);
        Arrays.stream(DragonType.values()).forEach(DragonForge::of);
    }

    public static <T extends Block> RegistryObject<T> register(String name, Supplier<T> block) {
        RegistryObject<T> ret = BLOCKS.register(name, block);
        IafItemRegistry.registerItem(name, () -> new BlockItem(ret.get(), new Item.Properties()), false);
        IafTabRegistry.TAB_BLOCKS_LIST.add(ret);
        return ret;
    }

    public static <T extends TorchBlock> RegistryObject<T> registerWallBlock(String name, Supplier<T> block) {
        RegistryObject<T> ret = BLOCKS.register(name, block);
        IafItemRegistry.registerItem(name, () -> new StandingAndWallBlockItem(ret.get(), ((IWallBlock) ret.get()).wallBlock(), new Item.Properties(), Direction.DOWN), false);
        IafTabRegistry.TAB_BLOCKS_LIST.add(ret);
        return ret;
    }

    public static <T extends WallTorchBlock> RegistryObject<T> registerWallTorch(String name, Supplier<T> block) {
        return BLOCKS.register(name, block);
    }

}
