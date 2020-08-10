package com.rempler.thevoids.api;

import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import com.rempler.thevoids.init.Constants;

/**
 * @author p455w0rd
 *
 */
public class BaseBlockItem extends BlockItem implements IItemStackProvider {

	ItemStack stack;

	public BaseBlockItem(final Block block) {
		super(block, Constants.BASE_PROPS);
		setRegistryName(block.getRegistryName());
		stack = new ItemStack(this);
	}

	@Override
	public ItemStack asStack() {
		return stack;
	}

}
