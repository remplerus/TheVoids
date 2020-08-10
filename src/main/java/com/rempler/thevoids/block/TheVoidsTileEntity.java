package com.rempler.thevoids.block;

import java.util.HashSet;
import java.util.Set;

import javax.annotation.Nullable;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.NonNullList;
import com.rempler.thevoids.init.ModObjects;
import com.rempler.thevoids.inventory.TheVoidsInventory;

/**
 * @author p455w0rd
 *
 */
public class TheVoidsTileEntity extends TileEntity implements IInventory {

	public static final TileEntityType<TheVoidsTileEntity> TYPE = TileEntityType.Builder.create(TheVoidsTileEntity::new, ModObjects.STONE_WORKBENCH).build(null);
	static {
		TYPE.setRegistryName(ModObjects.STONE_WORKBENCH.getRegistryName());
	}

	public TheVoidsTileEntity() {
		super(TYPE);
	}

	private final String NBT_MATRIX_LIST = "MatrixInv";
	private final String NBT_SLOT_ID = "Slot";
	private final NonNullList<ItemStack> invList = NonNullList.withSize(9, ItemStack.EMPTY);
	private final Set<TheVoidsInventory> invs = new HashSet<>();

	public void onOpen(final TheVoidsInventory inv) {
		invs.add(inv);
	}

	public void onClose(final TheVoidsInventory inv) {
		invs.remove(inv);
	}

	public void updateInvs() {
		for (final TheVoidsInventory inv : invs) {
			inv.changed();
		}
	}

	@Override
	public CompoundNBT getUpdateTag() {
		return write(new CompoundNBT());
	}

	@Override
	@Nullable
	public SUpdateTileEntityPacket getUpdatePacket() {
		return new SUpdateTileEntityPacket(getPos(), 255, getUpdateTag());
	}

	@Override
	public void onDataPacket(final NetworkManager net, final SUpdateTileEntityPacket pkt) {
		read(pkt.getNbtCompound());
	}

	@Override
	public void read(final CompoundNBT nbt) {
		super.read(nbt);
		if (nbt.contains(NBT_MATRIX_LIST)) {
			final ListNBT nbttaglist = nbt.getList(NBT_MATRIX_LIST, 10);
			for (int i = 0; i < nbttaglist.size(); ++i) {
				final CompoundNBT nbttagcompound = nbttaglist.getCompound(i);
				final int j = nbttagcompound.getByte(NBT_SLOT_ID) & 255;
				if (j >= 0 && j < getSizeInventory()) {
					setInventorySlotContents(j, ItemStack.read(nbttagcompound));
				}
			}
		}
		updateInvs();
	}

	@Override
	public CompoundNBT write(final CompoundNBT nbt) {
		super.write(nbt);
		final ListNBT nbttaglist = new ListNBT();
		for (int i = 0; i < getSizeInventory(); ++i) {
			final ItemStack itemstack = getStackInSlot(i);

			if (!itemstack.isEmpty()) {
				final CompoundNBT nbttagcompound = new CompoundNBT();
				nbttagcompound.putByte(NBT_SLOT_ID, (byte) i);
				itemstack.write(nbttagcompound);
				nbttaglist.add(nbttagcompound);
			}
			nbt.put(NBT_MATRIX_LIST, nbttaglist);
		}
		return nbt;
	}

	@Override
	public int getSizeInventory() {
		return invList.size();
	}

	@Override
	public boolean isEmpty() {
		for (final ItemStack stack : invList) {
			if (!stack.isEmpty()) {
				return false;
			}
		}
		return true;
	}

	@Override
	public ItemStack getStackInSlot(final int index) {
		return invList.get(index);
	}

	@Override
	public ItemStack decrStackSize(final int index, final int count) {
		return ItemStackHelper.getAndSplit(invList, index, count);
	}

	@Override
	public ItemStack removeStackFromSlot(final int index) {
		return ItemStackHelper.getAndRemove(invList, index);
	}

	@Override
	public void setInventorySlotContents(final int index, final ItemStack stack) {
		invList.set(index, stack);
	}

	@Override
	public int getInventoryStackLimit() {
		return 64;
	}

	@Override
	public boolean isUsableByPlayer(final PlayerEntity player) {
		return getWorld().getTileEntity(getPos()) == this && player.getDistanceSq(getPos().getX() + .5, getPos().getY() + .5, getPos().getZ() + .5) <= 64;
	}

	@Override
	public void openInventory(final PlayerEntity player) {
	}

	@Override
	public void closeInventory(final PlayerEntity player) {
	}

	@Override
	public boolean isItemValidForSlot(final int index, final ItemStack stack) {
		return true;
	}

	@Override
	public void clear() {
		for (int i = 0; i < getSizeInventory(); i++) {
			setInventorySlotContents(i, ItemStack.EMPTY);
		}
	}

}
