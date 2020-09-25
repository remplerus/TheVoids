package com.rempler.thevoids.world;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.rempler.thevoids.TheVoids;
import net.minecraft.block.BlockState;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.Blockreader;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.biome.provider.BiomeProvider;
import net.minecraft.world.chunk.IChunk;
import net.minecraft.world.gen.*;
import net.minecraft.world.gen.feature.structure.StructureManager;

import javax.annotation.Nonnull;
import java.util.function.Supplier;

public class TheVoidsChunkGenerator extends ChunkGenerator {

    public static final Codec<TheVoidsChunkGenerator> CODEC = RecordCodecBuilder.create(
            (instance) -> instance.group(
                    BiomeProvider.CODEC.fieldOf("biome_source").forGetter((gen) -> gen.biomeProvider),
                    Codec.LONG.fieldOf("seed").stable().forGetter((gen) -> gen.seed),
                    DimensionSettings.field_236098_b_.fieldOf("settings").forGetter((gen) -> gen.settings)
            ).apply(instance, instance.stable(TheVoidsChunkGenerator::new))
    );

    public static void init() {
        Registry.register(Registry.CHUNK_GENERATOR_CODEC, prefix("thevoids"), TheVoidsChunkGenerator.CODEC);
    }

    private final long seed;
    private final Supplier<DimensionSettings> settings;

    public TheVoidsChunkGenerator(BiomeProvider provider, long seed, Supplier<DimensionSettings> settings) {
        super(provider, provider, settings.get().getStructures(), seed);
        this.seed = seed;
        this.settings = settings;
    }

    @Nonnull
    @Override
    protected Codec<? extends ChunkGenerator> func_230347_a_() {
        return CODEC;
    }

    @Nonnull
    @Override
    public ChunkGenerator func_230349_a_(long newSeed) {
        return new TheVoidsChunkGenerator(this.biomeProvider.getBiomeProvider(newSeed), newSeed, settings);
    }

    @Override
    public void generateSurface(@Nonnull WorldGenRegion p_225551_1_, @Nonnull IChunk p_225551_2_) {

    }

    @Override
    public void func_230352_b_(@Nonnull IWorld p_230352_1_, @Nonnull StructureManager p_230352_2_, @Nonnull IChunk p_230352_3_) {

    }

    @Override
    public int getHeight(int x, int z, @Nonnull Heightmap.Type heightmapType) {
        return 0;
    }

    @Nonnull
    @Override
    public IBlockReader func_230348_a_(int p_230348_1_, int p_230348_2_) {
        return new Blockreader(new BlockState[0]);
    }

    public static ResourceLocation prefix(String path) {
        return new ResourceLocation(TheVoids.MODID, path);
    }
}