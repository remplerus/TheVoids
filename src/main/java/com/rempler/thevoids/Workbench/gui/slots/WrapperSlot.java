package com.rempler.thevoids.Workbench.gui.slots;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;

import javax.annotation.Nonnull;

/**
 * Used to wrap the slots inside Modules/Subcontainers
 */
  public class WrapperSlot extends Slot {

    public final Slot parent;

    public WrapperSlot(Slot slot) {
      super(slot.inventory, slot.getSlotIndex(), slot.xPos, slot.yPos);
      this.parent = slot;
    }

    @Override
    public void onSlotChange(ItemStack p_75220_1_, ItemStack p_75220_2_) {
      parent.onSlotChange(p_75220_1_, p_75220_2_);
    }

    @Override
    public void onSlotChanged() {
      parent.onSlotChanged();
    }

    @Override
    public boolean isItemValid(ItemStack stack) {
      return parent.isItemValid(stack);
    }

    @Override
    public boolean canTakeStack(PlayerEntity playerIn) {
      return parent.canTakeStack(playerIn);
    }

    @Override
    public void putStack(@Nonnull ItemStack stack) {
      parent.putStack(stack);
    }

    @Override
    @Nonnull
    public ItemStack onTake(PlayerEntity playerIn, ItemStack stack) {
      parent.onTake(playerIn, stack);
      return stack;
    }

  @Override
  public ItemStack decrStackSize(int p_75209_1_) {
    return parent.decrStackSize(p_75209_1_);
  }

  @Override
    @Nonnull
    public ItemStack getStack() {
      return parent.getStack();
    }

    @Override
    public boolean getHasStack() {
      return parent.getHasStack();
    }

    @Override
    public int getSlotStackLimit() {
      return parent.getSlotStackLimit();
    }

    @Override
    public int getItemStackLimit(ItemStack stack) {
      return parent.getItemStackLimit(stack);
    }

    //clientonly
    @Override
    public boolean isEnabled() {
      return parent.isEnabled();
    }

  @Override
  public int getSlotIndex() {
    return super.getSlotIndex();
  }

 // @Override
//  protected void onCrafting(ItemStack p_75208_1_) {
 //   parent.onCrafting(p_75208_1_);
  //}

  /*   //clientonly
    @Override
    public String getSlotTexture() {
      return parent.getSlotTexture();
    }

    @Nonnull
    @Override
    public ItemStack decrStackSize(int amount) {
      return parent.decrStackSize(amount);
    }

    @Nonnull
    @Override
    public ResourceLocation getBackgroundLocation() {
      return parent.getBackgroundLocation();
    }

    @Override
    public void setBackgroundName(@Nonnull String name) {
      parent.setBackgroundName(name);
    }

    @Nullable
    @Override
    public TextureAtlasSprite getBackgroundSprite() {
      return parent.getBackgroundSprite();
    }

    @Override
    public void setBackgroundLocation(@Nonnull ResourceLocation texture) {
      parent.setBackgroundLocation(texture);
    }*/

  }

