package com.rempler.thevoids.world;

import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;
import net.minecraftforge.common.BiomeManager;

public class TheVoidsBiomes {
    public static Biome the_void;

    public static void registerBiomes(){
        registerBiome(the_void, Type.VOID, Type.PLAINS, Type.MOUNTAIN, Type.RIVER, Type.OCEAN);
    }

    public static void registerBiome(Biome biome, Type... types){
        BiomeDictionary.addTypes(biome, types);
        BiomeManager.addSpawnBiome(biome);
    }
}
