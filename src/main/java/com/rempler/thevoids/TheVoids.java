package com.rempler.thevoids;

import com.rempler.thevoids.world.TheVoidsChunkGenerator;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(TheVoids.MODID)
public class TheVoids
{
    public static final Logger LOGGER = LogManager.getLogger();
    public static final String MODID = "thevoids";

    public TheVoids() {
        IEventBus iEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        iEventBus.addListener(this::setup);
        iEventBus.addListener(this::doClientStuff);

        MinecraftForge.EVENT_BUS.register(this);
    }

    private void setup(final FMLCommonSetupEvent event) {
        event.enqueueWork(TheVoidsChunkGenerator::init);
    }

    private void doClientStuff(final FMLClientSetupEvent event) {

    }
}
