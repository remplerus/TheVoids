package com.rempler.thevoids.world;

import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.provider.SingleBiomeProvider;
import net.minecraft.world.biome.provider.SingleBiomeProviderSettings;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.OverworldChunkGenerator;
import net.minecraft.world.gen.OverworldGenSettings;

public class TheVoidsWorldType extends WorldType {
    public TheVoidsWorldType() {
        super("the-voids-type");
    }

    @Override
    public ChunkGenerator<?> createChunkGenerator(World world){
        if (world.getDimension().getType() == DimensionType.OVERWORLD) {
            OverworldGenSettings settings = new OverworldGenSettings();
            SingleBiomeProviderSettings single = new SingleBiomeProviderSettings();
            single.setBiome(TheVoidsBiomes.the_void);
            return new OverworldChunkGenerator(world, new SingleBiomeProvider(single), settings);
        }
        else {
            return super.createChunkGenerator(world);
        }
    }
}
