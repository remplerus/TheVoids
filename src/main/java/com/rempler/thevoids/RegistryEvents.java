package com.rempler.thevoids;

import com.rempler.thevoids.world.TheVoidsBiome;
import com.rempler.thevoids.world.TheVoidsBiomes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.apache.logging.log4j.Logger;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class RegistryEvents {
    public static final Logger LOGGER = TheVoids.LOGGER;
    public static final String MODID = TheVoids.MODID;

    @SubscribeEvent
    public static void registerBiomes(final RegistryEvent.Register<Biome> event) {
        event.getRegistry().registerAll(
                TheVoidsBiomes.the_void = new TheVoidsBiome()
        );
        TheVoidsBiomes.registerBiomes();
    }

    public static ResourceLocation location(String name){
        return new ResourceLocation(MODID, name);
    }
}
