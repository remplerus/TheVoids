package com.rempler.thevoids.Workbench.gui.itemhandler;

import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.items.ItemStackHandler;

public class StoneWorkbenchItemHandler extends ItemStackHandler {
    public StoneWorkbenchItemHandler(int size){
        super(size);
    }

    public NonNullList<ItemStack> getContents(){
        return stacks;
    }

    public boolean isEmpty(){
        return getContents().stream().allMatch(ItemStack::isEmpty);
    }
}
