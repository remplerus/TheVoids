package com.rempler.thevoids.Workbench.client;

import com.rempler.thevoids.Workbench.StoneWorkbench;
import com.rempler.thevoids.Workbench.gui.renderer.StoneWorkbenchBlockEntityRenderer;
import com.rempler.thevoids.Workbench.gui.screen.StoneWorkbenchScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScreenManager;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(bus= Mod.EventBusSubscriber.Bus.MOD,value = Dist.CLIENT)
public class ClientStuffs {

  public static final Minecraft mc = Minecraft.getInstance();

  @SubscribeEvent
  public static void doClientStuff(final FMLClientSetupEvent event) {
    ScreenManager.registerFactory(StoneWorkbench.Objects.stone_workbench_container, StoneWorkbenchScreen::new);
    ClientRegistry.bindTileEntityRenderer(StoneWorkbench.Objects.stone_workbench_tile, StoneWorkbenchBlockEntityRenderer::new);
  }
}
