package com.rempler.thevoids.inventory;

import com.rempler.thevoids.block.TheVoidsTileEntity;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.CraftingInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.ItemStack;

/**
 * @author Lothrazar via https://github.com/PrinceOfAmber/Cyclic/blob/develop/src/main/java/com/lothrazar/cyclicmagic/block/workbench/InventoryWorkbench.java
 *
 */
public class TheVoidsInventory extends CraftingInventory {

	private final TheVoidsTileEntity tile;
	private final Container container;

	public TheVoidsInventory(final Container eventHandlerIn, final TheVoidsTileEntity tileEntity) {
		super(eventHandlerIn, 3, 3);
		tile = tileEntity;
		container = eventHandlerIn;
	}

	@Override
	public int getSizeInventory() {
		return 9;
	}

	@Override
	public ItemStack getStackInSlot(final int index) {
		return tile.getStackInSlot(index);
	}

	@Override
	public ItemStack decrStackSize(final int index, final int count) {
		final ItemStack stack = tile.decrStackSize(index, count);
		if (stack != ItemStack.EMPTY) {
			tile.updateInvs();
		}
		return stack;
	}

	@Override
	public void setInventorySlotContents(final int index, final ItemStack stack) {
		tile.setInventorySlotContents(index, stack);
		tile.updateInvs();
	}

	@Override
	public void markDirty() {
		tile.markDirty();
		final BlockState state = tile.getWorld().getBlockState(tile.getPos());
		tile.getWorld().notifyBlockUpdate(tile.getPos(), state, state, 3);
	}

	@Override
	public void openInventory(final PlayerEntity player) {
		super.openInventory(player);
		tile.onOpen(this);
	}

	@Override
	public void closeInventory(final PlayerEntity player) {
		super.closeInventory(player);
		tile.onClose(this);
	}

	public void changed() {
		container.onCraftMatrixChanged(this);
	}
}