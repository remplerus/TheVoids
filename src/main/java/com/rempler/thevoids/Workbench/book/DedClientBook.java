package shadows.fastbench.book;

import java.util.Collections;
import java.util.List;

import net.minecraft.client.gui.recipebook.RecipeList;
import net.minecraft.client.util.ClientRecipeBook;
import net.minecraft.client.util.RecipeBookCategories;
import net.minecraft.inventory.container.RecipeBookContainer;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.RecipeBook;
import net.minecraft.util.ResourceLocation;

public class DedClientBook extends ClientRecipeBook {

	public DedClientBook() {
		super(null);
	}

	@Override
	public void copyFrom(RecipeBook that) {
	}

	@Override
	public boolean isFilteringCraftable() {
		return false;
	}

	@Override
	public boolean isGuiOpen() {
		return false;
	}

	@Override
	public void lock(IRecipe<?> recipe) {
	}

	@Override
	public void markNew(IRecipe<?> recipe) {
	}

	@Override
	public void markSeen(IRecipe<?> recipe) {
	}

	@Override
	public boolean isNew(IRecipe<?> recipe) {
		return false;
	}

	@Override
	public boolean isUnlocked(IRecipe<?> recipe) {
		return true;
	}

	@Override
	public void setFilteringCraftable(boolean shouldFilter) {
	}

	@Override
	public void setGuiOpen(boolean open) {
	}

	@Override
	public void unlock(IRecipe<?> recipe) {
	}

	@Override
	public void setBlastFurnaceGuiOpen(boolean p_216755_1_) {
	}

	@Override
	public void setBlastFurnaceFilteringCraftable(boolean p_216756_1_) {
	}

	@Override
	public void setSmokerGuiOpen(boolean p_216757_1_) {
	}

	@Override
	public boolean isBlastFurnaceGuiOpen() {
		return false;
	}

	@Override
	public boolean isSmokerGuiOpen() {
		return false;
	}

	@Override
	public void setSmokerFilteringCraftable(boolean p_216760_1_) {
	}

	@Override
	public boolean isBlastFurnaceFilteringCraftable() {
		return false;
	}

	@Override
	public boolean isSmokerFilteringCraftable() {
		return false;
	}

	@Override
	public List<RecipeList> getRecipes() {
		return Collections.emptyList();
	}

	@Override
	public List<RecipeList> getRecipes(RecipeBookCategories p_202891_1_) {
		return Collections.emptyList();
	}

	@Override
	public boolean isFilteringCraftable(RecipeBookContainer<?> p_203432_1_) {
		return false;
	}

	@Override
	public boolean isFurnaceFilteringCraftable() {
		return false;
	}

	@Override
	public boolean isFurnaceGuiOpen() {
		return false;
	}

	@Override
	protected void lock(ResourceLocation p_209119_1_) {
	}

	@Override
	protected void markNew(ResourceLocation p_209120_1_) {
	}

	@Override
	public void rebuildTable() {
	}

	@Override
	public void setFurnaceFilteringCraftable(boolean p_202882_1_) {
	}

	@Override
	public void setFurnaceGuiOpen(boolean p_202881_1_) {
	}

	@Override
	protected void unlock(ResourceLocation p_209118_1_) {
	}

	@Override
	public boolean func_226144_b_(ResourceLocation p_226144_1_) {
		return false;
	}

}
