package com.rempler.thevoids.init;

import com.rempler.thevoids.block.TheVoidsBlock;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import com.rempler.thevoids.api.BaseBlockItem;
import com.rempler.thevoids.api.BaseItem;

/**
 * @author p455w0rd
 *
 */
public class ModObjects {

	public static final TheVoidsBlock STONE_WORKBENCH = new TheVoidsBlock();
	public static final BaseItem STONE_CRAFTING_PLATE = new BaseItem("stone_crafting_plate");
	public static final BaseItem STONE_STICK = new BaseItem("stone_stick");
	public static final BaseBlockItem ITEM_BLOCK_STONE_WORKBENCH = new BaseBlockItem(STONE_WORKBENCH) {
		@Override
		public int getBurnTime(final ItemStack itemStack) {
			return 200;
		}
	};

	private static final Item[] ITEMS = new Item[] { //@formatter:off
			STONE_CRAFTING_PLATE,
			STONE_STICK,
			ITEM_BLOCK_STONE_WORKBENCH//@formatter:on
	};

	public static Item[] getItems() {
		return ITEMS;
	}

}
