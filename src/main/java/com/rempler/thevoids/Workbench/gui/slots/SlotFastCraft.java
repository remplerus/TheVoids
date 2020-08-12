
package com.rempler.thevoids.Workbench.gui.slots;

import com.rempler.thevoids.Workbench.gui.container.StoneWorkbenchContainer;
import com.rempler.thevoids.Workbench.gui.inventory.StoneWorkbenchCraftingInventory;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.CraftingResultSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.fml.hooks.BasicEventHooks;


/**
 * SlotCraftingSucks from FastWorkbench adapted for the Crafting Station container (no change in functionality)
 * See: https://github.com/Shadows-of-Fire/FastWorkbench/blob/master/src/main/java/shadows/fastbench/gui/SlotCraftingSucks.java
 * <p>
 * Basically it makes crafting less laggy
 */
public class SlotFastCraft extends CraftingResultSlot {

  private final PlayerEntity player = Minecraft.getInstance().player;
  private int amountCrafted;
  private final StoneWorkbenchContainer container;
  protected StoneWorkbenchCraftingInventory craftingInventoryPersistant;

  public SlotFastCraft(StoneWorkbenchContainer container, StoneWorkbenchCraftingInventory craftingInventoryPersistant, IInventory resultInventory, int slotIndex, int xPosition, int yPosition, PlayerEntity player) {
    super(player, craftingInventoryPersistant, resultInventory, slotIndex, xPosition, yPosition);
    this.container = container;
    this.craftingInventoryPersistant = craftingInventoryPersistant;
  }

  @Override
  public ItemStack decrStackSize(int amount) {
    if (this.getHasStack()) {
      this.amountCrafted += Math.min(amount, getStack().getCount());
    }
    return getStack();
  }

  /**
   * the itemStack passed in is the output - ie, iron ingots, and pickaxes, not ore and wood.
   */
  @Override
  protected void onCrafting(ItemStack stack) {
    if (this.amountCrafted > 0) {
      stack.onCrafting(this.player.world, this.player, this.amountCrafted);
      BasicEventHooks.firePlayerCraftingEvent(this.player, stack, craftingInventoryPersistant);
    }

    this.amountCrafted = 0;
  }

  @Override
  public ItemStack onTake(PlayerEntity thePlayer, ItemStack craftingResult) {
    this.onCrafting(craftingResult);
    net.minecraftforge.common.ForgeHooks.setCraftingPlayer(thePlayer);
    /* CHANGE BEGINS HERE */
    NonNullList<ItemStack> nonnulllist = container.getRemainingItems();
    /* END OF CHANGE */
    net.minecraftforge.common.ForgeHooks.setCraftingPlayer(null);

    // note: craftMatrixPersistent and this.field_75239_a are the same object!
    craftingInventoryPersistant.setDoNotCallUpdates(true);

    for (int i = 0; i < nonnulllist.size(); ++i) {
      ItemStack stackInSlot = this.craftingInventoryPersistant.getStackInSlot(i);
      ItemStack stack1 = nonnulllist.get(i);

      if (!stackInSlot.isEmpty()) {
        this.craftingInventoryPersistant.decrStackSize(i, 1);
        stackInSlot = this.craftingInventoryPersistant.getStackInSlot(i);
      }

      if (!stack1.isEmpty()) {
        if (stackInSlot.isEmpty()) {
          this.craftingInventoryPersistant.setInventorySlotContents(i, stack1);
        } else if (ItemStack.areItemsEqual(stackInSlot, stack1) && ItemStack.areItemStackTagsEqual(stackInSlot, stack1)) {
          stack1.grow(stackInSlot.getCount());
          this.craftingInventoryPersistant.setInventorySlotContents(i, stack1);
        } else if (!this.player.inventory.addItemStackToInventory(stack1)) {
          this.player.dropItem(stack1, false);
        }
      }
    }

    craftingInventoryPersistant.setDoNotCallUpdates(false);
    container.onCraftMatrixChanged(craftingInventoryPersistant);

    return craftingResult;
  }
}
