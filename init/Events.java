package com.rempler.thevoids.init;

import static net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus.MOD;

import com.rempler.thevoids.TheVoids;
import com.rempler.thevoids.block.TheVoidsTileEntity;
import com.rempler.thevoids.container.TheVoidsContainer;
import net.minecraft.block.Block;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

/**
 * @author p455w0rd
 *
 */
@EventBusSubscriber(bus = MOD, modid = TheVoids.MODID)
public class Events {

	@SubscribeEvent
	public static void onBlockRegistryReady(final RegistryEvent.Register<Block> event) {
		event.getRegistry().register(ModObjects.STONE_WORKBENCH);
	}

	@SubscribeEvent
	public static void onItemRegistryReady(final RegistryEvent.Register<Item> event) {
		event.getRegistry().registerAll(ModObjects.getItems());
	}

	@SubscribeEvent
	public static void onTileRegistryReady(final RegistryEvent.Register<TileEntityType<?>> event) {
		event.getRegistry().register(TheVoidsTileEntity.TYPE);
	}

	@SubscribeEvent
	public static void onContainerRegistryReady(final RegistryEvent.Register<ContainerType<?>> event) {
		event.getRegistry().register(TheVoidsContainer.TYPE);
	}

}
