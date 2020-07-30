package com.rempler.thevoids.world;

import com.rempler.thevoids.RegistryEvents;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.DefaultBiomeFeatures;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.carver.WorldCarver;
import net.minecraft.world.gen.feature.BlockStateFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.feature.ProbabilityConfig;
import net.minecraft.world.gen.feature.structure.MineshaftConfig;
import net.minecraft.world.gen.feature.structure.MineshaftStructure;
import net.minecraft.world.gen.placement.ChanceConfig;
import net.minecraft.world.gen.placement.CountRangeConfig;
import net.minecraft.world.gen.placement.IPlacementConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;

public class TheVoidsBiome extends Biome {
    private static final BlockState WATER = Blocks.WATER.getDefaultState();
    private static final BlockState GRAVEL = Blocks.GRAVEL.getDefaultState();
    private static final BlockState GRANITE = Blocks.GRANITE.getDefaultState();
    private static final BlockState DIORITE = Blocks.DIORITE.getDefaultState();
    private static final BlockState ANDESITE = Blocks.ANDESITE.getDefaultState();
    private static final BlockState CLAY = Blocks.CLAY.getDefaultState();

    public TheVoidsBiome() {
        super(new Biome.Builder()
                .surfaceBuilder(SurfaceBuilder.DEFAULT, SurfaceBuilder.STONE_STONE_GRAVEL_CONFIG)
                .precipitation(RainType.RAIN)
                .category(Category.PLAINS)
                .downfall(0.5f)
                .depth(0.4f)
                .scale(0.5f)
                .temperature(0.6f)
                .waterColor(0x3F76E4)
                .waterFogColor(0x050533)
                .parent(null)
            );

        //stones
        DefaultBiomeFeatures.addOres(this);
        this.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Feature.ORE.withConfiguration(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, CLAY, 33)).withPlacement(Placement.COUNT_RANGE.configure(new CountRangeConfig(10, 0, 0, 256))));
        this.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Feature.ORE.withConfiguration(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, GRAVEL, 33)).withPlacement(Placement.COUNT_RANGE.configure(new CountRangeConfig(8, 0, 0, 256))));
        this.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Feature.ORE.withConfiguration(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, GRANITE, 33)).withPlacement(Placement.COUNT_RANGE.configure(new CountRangeConfig(10, 0, 0, 80))));
        this.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Feature.ORE.withConfiguration(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, DIORITE, 33)).withPlacement(Placement.COUNT_RANGE.configure(new CountRangeConfig(10, 0, 0, 80))));
        this.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Feature.ORE.withConfiguration(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, ANDESITE, 33)).withPlacement(Placement.COUNT_RANGE.configure(new CountRangeConfig(10, 0, 0, 80))));

        //caves
        DefaultBiomeFeatures.addOceanCarvers(this);
        DefaultBiomeFeatures.addMonsterRooms(this);
        this.addFeature(GenerationStage.Decoration.UNDERGROUND_STRUCTURES, Feature.MINESHAFT.withConfiguration(new MineshaftConfig((double)0.004F, MineshaftStructure.Type.NORMAL)).withPlacement(Placement.NOPE.configure(IPlacementConfig.NO_PLACEMENT_CONFIG)));
        this.addCarver(GenerationStage.Carving.AIR, Biome.createCarver(WorldCarver.CANYON, new ProbabilityConfig(0.02F)));
        this.addFeature(GenerationStage.Decoration.LOCAL_MODIFICATIONS, Feature.LAKE.withConfiguration(new BlockStateFeatureConfig(WATER)).withPlacement(Placement.WATER_LAKE.configure(new ChanceConfig(8))));

        //Monsters & Creatures
        this.addSpawn(EntityClassification.CREATURE, new Biome.SpawnListEntry(EntityType.SHEEP, 12, 3, 3));
        this.addSpawn(EntityClassification.CREATURE, new Biome.SpawnListEntry(EntityType.PIG, 10, 4, 6));
        this.addSpawn(EntityClassification.CREATURE, new Biome.SpawnListEntry(EntityType.CHICKEN, 10, 4, 5));
        this.addSpawn(EntityClassification.CREATURE, new Biome.SpawnListEntry(EntityType.COW, 8, 4, 4));
        this.addSpawn(EntityClassification.CREATURE, new Biome.SpawnListEntry(EntityType.HORSE, 5, 2, 2));
        this.addSpawn(EntityClassification.CREATURE, new Biome.SpawnListEntry(EntityType.DONKEY, 1, 1, 2));
        this.addSpawn(EntityClassification.AMBIENT, new Biome.SpawnListEntry(EntityType.BAT, 10, 3, 5));
        this.addSpawn(EntityClassification.MONSTER, new Biome.SpawnListEntry(EntityType.SPIDER, 100, 2, 2));
        this.addSpawn(EntityClassification.MONSTER, new Biome.SpawnListEntry(EntityType.ZOMBIE, 95, 2, 3));
        this.addSpawn(EntityClassification.MONSTER, new Biome.SpawnListEntry(EntityType.ZOMBIE_VILLAGER, 5, 1, 1));
        this.addSpawn(EntityClassification.MONSTER, new Biome.SpawnListEntry(EntityType.SKELETON, 100, 2, 2));
        this.addSpawn(EntityClassification.MONSTER, new Biome.SpawnListEntry(EntityType.CREEPER, 100, 4, 5));
        this.addSpawn(EntityClassification.MONSTER, new Biome.SpawnListEntry(EntityType.SLIME, 100, 2, 2));
        this.addSpawn(EntityClassification.MONSTER, new Biome.SpawnListEntry(EntityType.ENDERMAN, 10, 1, 2));
        this.addSpawn(EntityClassification.MONSTER, new Biome.SpawnListEntry(EntityType.WITCH, 5, 1, 3));

        this.setRegistryName(RegistryEvents.location("the-voids-biome"));
    }
}
