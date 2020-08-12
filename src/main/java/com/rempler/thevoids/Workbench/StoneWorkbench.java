package com.rempler.thevoids.Workbench;

import com.rempler.thevoids.TheVoids;
import com.rempler.thevoids.Workbench.gui.block.StoneWorkbenchBlock;
import com.rempler.thevoids.Workbench.gui.container.StoneWorkbenchContainer;
import com.rempler.thevoids.Workbench.gui.tileentity.StoneWorkbenchTileEntity;
import com.rempler.thevoids.Workbench.network.PacketHandler;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.IForgeRegistryEntry;
import net.minecraftforge.registries.ObjectHolder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(TheVoids.MODID)
public class StoneWorkbench {
    // Directly reference a log4j logger.

    public static final String MODID = TheVoids.MODID;

    public static final Logger LOGGER = TheVoids.LOGGER;

    public StoneWorkbench() {
        // Register the setup method for modloading

        //ModLoadingContext.get().registerConfig(ModConfig.Type.SERVER, Configs.SERVER_SPEC);
    }

    public static void setup(final FMLCommonSetupEvent event) {
        PacketHandler.registerMessages(MODID);
    }

    public static void enqueueIMC(final InterModEnqueueEvent event) {
        InterModComms.sendTo("craftingtweaks", "RegisterProvider", () -> {
            CompoundNBT tagCompound = new CompoundNBT();
            tagCompound.putString("ContainerClass", StoneWorkbenchContainer.class.getName());
            tagCompound.putString("AlignToGrid", "left");
            return tagCompound;
        });
    }

    // You can use EventBusSubscriber to automatically subscribe events on the contained class (this is subscribing to the MOD
    // Event bus for receiving Registry Events)
    @Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class RegistryEvents {
        @SubscribeEvent
        public static void block(final RegistryEvent.Register<Block> event) {
            register(new StoneWorkbenchBlock(Block.Properties.from(Blocks.STONE)),"stone_workbench",event.getRegistry());
        }

        @SubscribeEvent
        public static void item(final RegistryEvent.Register<Item> event) {
            // register a new item here
            Item.Properties properties = new Item.Properties().group(ItemGroup.DECORATIONS);
            register(new BlockItem(Objects.stone_workbench,properties),"stone_workbench",event.getRegistry());
        }

        @SubscribeEvent
        public static void container(final RegistryEvent.Register<ContainerType<?>> event){
            register(IForgeContainerType.create((windowId, inv, data) ->
                    new StoneWorkbenchContainer(windowId, inv, inv.player.world, data.readBlockPos())),"stone_workbench_container",event.getRegistry());

        }

        @SubscribeEvent
        public static void tile(final RegistryEvent.Register<TileEntityType<?>> event){
            register(TileEntityType.Builder.create(StoneWorkbenchTileEntity::new,Objects.stone_workbench).build(null),"stone_workbench_tile",event.getRegistry());
        }

        private static <T extends IForgeRegistryEntry<T>> void register(T obj, String name, IForgeRegistry<T> registry) {
            registry.register(obj.setRegistryName(new ResourceLocation(MODID, name)));
        }
    }
    @ObjectHolder(MODID)
    public static class Objects {
        public static final Block stone_workbench = null;
        public static final ContainerType<StoneWorkbenchContainer> stone_workbench_container = null;
        public static final TileEntityType<StoneWorkbenchTileEntity> stone_workbench_tile = null;
    }
}