package com.animania.manual.components;

import net.minecraft.world.level.Level;

import java.util.Iterator;
import java.util.List;

import com.animania.Animania;
import com.animania.manual.gui.GuiManual;

import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.init.SoundEvents;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.text.translation.I18n;
import net.minecraftforge.common.crafting.IShapedRecipe;

public class CraftingComponent implements IManualComponent
{
	private List<Recipe> recipes;
	private GuiManual manual;
	private int x;
	private int y;

	private int absoluteX;
	private int absoluteY;

	private int objectWidth;
	private int objectHeight;

	private Minecraft mc;

	private int recipeIndex = 0;
	private ItemStack[][] items;
	private int[] ingredientIndex = new int[9];
	private Recipe currentRecipe;

	private static final ResourceLocation MATRIX_TEXTURE = new ResourceLocation(Animania.MODID, "textures/gui/crafting_matrix.png");
	private static final ResourceLocation BUTTONS = new ResourceLocation(Animania.MODID, "textures/gui/recipe_buttons.png");

	public static int ITEM_TIMER = 0;

	public CraftingComponent(int x, int y, List<Recipe> recipes)
	{
		this.manual = GuiManual.INSTANCE;
		this.absoluteX = x + GuiManual.START_OFFSET_X;
		this.absoluteY = y + GuiManual.START_OFFSET_Y;

		this.x = x;
		this.y = y;

		this.recipes = recipes;
		this.mc = Minecraft.getInstance();

		this.objectHeight = 54;
		this.objectWidth = 104;
	}

	@Override
	public void init()
	{
		this.currentRecipe = this.recipes.get(this.recipeIndex);
		this.items = this.getSortedIngredients(this.currentRecipe);
		this.ingredientIndex = new int[9];
	}

	@Override
	public void draw(int mouseX, int mouseY, float partialTicks)
	{
		this.mc.textureManager.bind(MATRIX_TEXTURE);
		int border = (GuiManual.MANUAL_MAX_X - this.objectWidth) / 2;

		int getX() = absoluteX + manual.guiLeft + border;
		int getY() = absoluteY + manual.guiTop;

		GlStateManager.pushMatrix();
		GlStateManager.color(1, 1, 1);
		GlStateManager.enableBlend();
		this.manual.drawModalRectWithCustomSizedTexture(this.getX(), this.getY(), 0, 0, this.objectWidth, this.objectHeight, this.objectWidth, this.objectHeight);
		GlStateManager.popMatrix();

		for (int i = 0; i < 9; i++)
		{
			int offsetX = 1 + 18 * (i % 3);
			int offsetY = 1 + 18 * (i / 3);
			if (this.items[i].length > 0)
			{
				GlStateManager.pushMatrix();
				RenderHelper.enableGUIStandardItemLighting();
				this.manual.drawItemStack(this.items[i][this.ingredientIndex[i]], this.getX() + offsetX, this.getY() + offsetY, null);
				GlStateManager.popMatrix();
			}
		}

		GlStateManager.pushMatrix();
		RenderHelper.enableGUIStandardItemLighting();
		this.manual.drawItemStack(this.currentRecipe.getRecipeOutput(), this.getX() + 83, this.getY() + 19, null);
		GlStateManager.popMatrix();

		if (this.recipes.size() > 1)
		{
			this.mc.renderEngine.bindTexture(BUTTONS);
			this.manual.drawModalRectWithCustomSizedTexture(this.getX() + 56, this.getY() + 42, this.isHovering(mouseX, mouseY, this.getX() + 56, this.getY() + 42, 9, 11) ? 18 : 0, 0, 9, 11, 36, 11);
			this.manual.drawModalRectWithCustomSizedTexture(this.getX() + 67, this.getY() + 42, this.isHovering(mouseX, mouseY, this.getX() + 67, this.getY() + 42, 9, 11) ? 27 : 9, 0, 9, 11, 36, 11);
		}

		if (!(this.currentRecipe instanceof IShapedRecipe))
		{
			this.mc.fontRenderer.drawString(I18n.translateToLocal("manual.crafting.shapeless"), this.getX() + 57, this.getY() + 2, 0);
		}

		GlStateManager.disableLighting();
	}

	@Override
	public void update()
	{
		this.updateIngredientIndices();
	}

	@Override
	public void drawLater(int mouseX, int mouseY, float partialTicks)
	{
		int border = (GuiManual.MANUAL_MAX_X - this.objectWidth) / 2;
		int getX() = absoluteX + manual.guiLeft + border;
		int getY() = absoluteY + manual.guiTop;

		GlStateManager.pushMatrix();

		for (int i = 0; i < 9; i++)
		{
			int offsetX = 1 + 18 * (i % 3);
			int offsetY = 1 + 18 * (i / 3);
			if (this.isHovering(mouseX, mouseY, this.getX() + offsetX, this.getY() + offsetY, 16, 16) && this.items[i].length > 0)
			{
				this.manual.renderToolTip(this.items[i][this.ingredientIndex[i]], mouseX, mouseY);
			}
		}

		if (this.isHovering(mouseX, mouseY, this.getX() + 83, this.getY() + 19, 16, 16))
			this.manual.renderToolTip(this.currentRecipe.getRecipeOutput(), mouseX, mouseY);

		GlStateManager.disableLighting();
		GlStateManager.popMatrix();

	}

	public boolean isHovering(int mouseX, int mouseY, int x, int y, int dx, int dy)
	{
		return mouseX > x && mouseX < x + dx && mouseY > y && mouseY < y + dy;
	}

	private void updateIngredientIndices()
	{
		if (!GuiScreen.isShiftKeyDown() && ITEM_TIMER == 0)
		{
			for (int i = 0; i < this.items.length; i++)
			{
				ItemStack[] ings = this.items[i];
				if (ings.length > 1)
				{
					int currentIndex = this.ingredientIndex[i];

					if (currentIndex == ings.length - 1)
						currentIndex = 0;
					else
						currentIndex++;

					this.ingredientIndex[i] = currentIndex;
				}
			}
		}
	}

	@Override
	public void onLeftClick(int mouseX, int mouseY)
	{
		if (this.recipes.size() > 1)
		{
			int border = (GuiManual.MANUAL_MAX_X - this.objectWidth) / 2;

			int getX() = absoluteX + manual.guiLeft + border;
			int getY() = absoluteY + manual.guiTop;

			if (this.isHovering(mouseX, mouseY, this.getX() + 56, this.getY() + 42, 9, 11))
			{
				if (this.recipeIndex == 0)
					this.recipeIndex = this.recipes.size() - 1;
				else
					this.recipeIndex--;

				this.mc.getSoundHandler().playSound(PositionedSoundRecord.getMasterRecord(SoundEvents.UI_BUTTON_CLICK, 1.0F));
				this.init();
				return;
			}

			if (this.isHovering(mouseX, mouseY, this.getX() + 67, this.getY() + 42, 9, 11))
			{
				if (this.recipeIndex == this.recipes.size() - 1)
					this.recipeIndex = 0;
				else
					this.recipeIndex++;
				this.init();
				this.mc.getSoundHandler().playSound(PositionedSoundRecord.getMasterRecord(SoundEvents.UI_BUTTON_CLICK, 1.0F));
			}
		}
	}

	@Override
	public void onRightClick(int mouseX, int mouseY)
	{
	}

	@Override
	public int getObjectWidth()
	{
		return this.objectWidth;
	}

	@Override
	public int getObjectHeight()
	{
		return this.objectHeight;
	}

	@Override
	public int getX()
	{
		return this.x;
	}

	@Override
	public int getY()
	{
		return this.y;
	}

	@Override
	public IManualComponent setX(int x)
	{
		this.x = x;
		this.absoluteX = x + GuiManual.START_OFFSET_X;
		return this;
	}

	@Override
	public IManualComponent setY(int y)
	{
		this.y = y;
		this.absoluteY = y + GuiManual.START_OFFSET_Y;
		return this;
	}

	@Override
	public void onLeftClick()
	{
	}

	@Override
	public void onRightClick()
	{
	}

	private ItemStack[][] getSortedIngredients(IRecipe recipe)
	{
		ItemStack[][] sortedIngredients = new ItemStack[9][0];

		int craftingWidth = 3;
		int craftingHeight = 3;
		int recipeWidth = recipe instanceof IShapedRecipe ? ((IShapedRecipe) recipe).getRecipeWidth() : craftingWidth;
		int l = 0;
		Iterator<Ingredient> iterator = recipe.getIngredients().iterator();

		loop: for (int i1 = 0; i1 < craftingHeight; ++i1)
		{
			for (int j1 = 0; j1 < recipeWidth; ++j1)
			{
				if (!iterator.hasNext())
				{
					break loop;
				}

				Ingredient ingredient = iterator.next();

				if (ingredient != Ingredient.EMPTY)
				{
					sortedIngredients[l] = ingredient.getMatchingStacks();
				}

				++l;
			}

			if (recipeWidth < craftingWidth)
			{
				l += craftingWidth - recipeWidth;
			}
		}

		return sortedIngredients;
	}
}
