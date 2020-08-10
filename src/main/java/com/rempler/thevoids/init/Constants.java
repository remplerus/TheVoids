package com.rempler.thevoids.init;

import com.rempler.thevoids.TheVoids;
import net.minecraft.item.Item.Properties;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class Constants {

	public static final String CONFIG_FILE = "config/StoneCraftingTable.toml";
	public static final ItemGroup ITEM_GROUP = new ItemGroup(TheVoids.MODID) {
		@Override
		public ItemStack createIcon() {
			return ModObjects.ITEM_BLOCK_STONE_WORKBENCH.asStack();
		}
	};
	public static final Properties BASE_PROPS = new Properties().group(ITEM_GROUP).maxStackSize(64);

}
