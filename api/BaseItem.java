package com.rempler.thevoids.api;

import com.rempler.thevoids.TheVoids;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import com.rempler.thevoids.init.Constants;

/**
 * @author p455w0rd
 *
 */
public class BaseItem extends Item implements IItemStackProvider {

	ItemStack stack;

	public BaseItem(final String name) {
		super(Constants.BASE_PROPS);
		setRegistryName(TheVoids.MODID + ":" + name);
		stack = new ItemStack(this);
	}

	@Override
	public ItemStack asStack() {
		return stack;
	}

}
