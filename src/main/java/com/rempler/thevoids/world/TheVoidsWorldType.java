package com.rempler.thevoids.world;

import net.minecraft.client.gui.screen.BiomeGeneratorTypeScreens;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.provider.OverworldBiomeProvider;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.DimensionSettings;

import javax.annotation.Nonnull;

public class TheVoidsWorldType extends BiomeGeneratorTypeScreens {
    public static final BiomeGeneratorTypeScreens INSTANCE = new TheVoidsWorldType();

    protected TheVoidsWorldType() {
        super("the-voids");
    }

    @Nonnull
    @Override
    protected ChunkGenerator func_241869_a(@Nonnull Registry<Biome> biomeRegistry, @Nonnull Registry<DimensionSettings> dimensionSettingsRegistry, long seed) {
        return new TheVoidsChunkGenerator(new OverworldBiomeProvider(seed, false, false, biomeRegistry), seed,
                () -> dimensionSettingsRegistry.getValueForKey(DimensionSettings.field_242734_c));
    }
}
