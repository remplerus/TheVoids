package com.rempler.thevoids;

import com.rempler.thevoids.init.ModObjects;
import com.rempler.thevoids.proxy.ClientProxy;
import com.rempler.thevoids.proxy.CommonProxy;
import com.rempler.thevoids.world.TheVoidsWorldType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.world.WorldType;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(TheVoids.MODID)
public class TheVoids
{
    public static final String CONFIG_FILE = "config/StoneCraftingTable.toml";
    public static final ItemGroup ITEM_GROUP = new ItemGroup(TheVoids.MODID) {
        @Override
        public ItemStack createIcon() {
            return ModObjects.ITEM_BLOCK_STONE_WORKBENCH.asStack();
        }
    };
    public static final Item.Properties BASE_PROPS = new Item.Properties().group(ITEM_GROUP).maxStackSize(64);
    public static final Logger LOGGER = LogManager.getLogger();
    public static final String MODID = "thevoids";
    public static final WorldType THE_VOIDS_TYPE = new TheVoidsWorldType();
    private static final CommonProxy proxy = DistExecutor.safeRunForDist(() -> ClientProxy::new, () -> CommonProxy::new);

    public TheVoids() {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::doClientStuff);

        MinecraftForge.EVENT_BUS.register(this);
    }

    public static CommonProxy getProxy() {
        return proxy;
    }

    private void setup(final FMLCommonSetupEvent event) {
        proxy.init();
    }

    private void doClientStuff(final FMLClientSetupEvent event) {

    }
}
