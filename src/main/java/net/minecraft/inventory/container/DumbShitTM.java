package net.minecraft.inventory.container;

import net.minecraft.item.ItemStack;

/**
 * This class is needed so I can invoke this for any container from {@link shadows.fastbench.gui.ContainerFastBench#handleShiftCraft}
 */
public class DumbShitTM {

	public static boolean mergeItemStack(Container container, ItemStack stack, int start, int end) {
		return container.mergeItemStack(stack, start, end, true);
	}

}
