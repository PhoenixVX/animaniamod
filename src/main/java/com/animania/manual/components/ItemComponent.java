package com.animania.manual.components;

import com.animania.manual.gui.GuiManual;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.level.item.ItemStack;

public class ItemComponent implements IManualComponent
{
	private ItemStack[] stacks;

	private GuiManual manual;
	private int x;
	private int y;

	private int absoluteX;
	private int absoluteY;

	private int objectWidth;
	private int objectHeight;

	private Minecraft mc;
	
	private static final int ITEM_OFFSET = 3;

	public ItemComponent(int x, int y, ItemStack[] stacks)
	{
		this.stacks = stacks;
		this.manual = GuiManual.INSTANCE;
		this.absoluteX = x + GuiManual.START_OFFSET_X;
		this.absoluteY = y + GuiManual.START_OFFSET_Y;

		this.x = x;
		this.y = y;

		this.mc = Minecraft.getInstance();

		this.objectHeight = 16;
		this.objectWidth = 0;
		for (int i = 0; i < stacks.length; i++)
		{
			objectWidth += 16;
			if (i != (stacks.length - 1))
				objectWidth += ITEM_OFFSET;
		}
	}

	@Override
	public void init()
	{

	}

	@Override
	public void draw(int mouseX, int mouseY, float partialTicks)
	{
		int border = (GuiManual.MANUAL_MAX_X - objectWidth) / 2;
		for (int i = 0; i < stacks.length; i++)
		{
			ItemStack stack = stacks[i];
			int getX() = absoluteX + manual.guiLeft + border + (i * (16 + ITEM_OFFSET));
			int getY() = absoluteY + manual.guiTop;
			GlStateManager.pushMatrix();
			RenderHelper.enableGUIStandardItemLighting();
			manual.drawItemStack(stack, getX(), getY(), null);
			GlStateManager.disableLighting();
			GlStateManager.popMatrix();

		}
		
		

	}

	@Override
	public void drawLater(int mouseX, int mouseY, float partialTicks)
	{
		int border = (GuiManual.MANUAL_MAX_X - objectWidth) / 2;
		for (int i = 0; i < stacks.length; i++)
		{
			ItemStack stack = stacks[i];
			int getX() = absoluteX + manual.guiLeft + border + (i * (16 + ITEM_OFFSET));
			int getY() = absoluteY + manual.guiTop;

			if (mouseX > getX() && mouseX < getX() + 16 && mouseY > getY() && mouseY < getY() + 16)
			{
				GlStateManager.pushMatrix();
				manual.renderToolTip(stack, mouseX, mouseY);
				GlStateManager.disableLighting();
				GlStateManager.popMatrix();
			}
		}
	}
	
	private boolean isHovering(int mouseX, int mouseY)
	{
		return mouseX > absoluteX + manual.guiLeft && mouseX < absoluteX + manual.guiLeft + objectWidth && mouseY > absoluteY + manual.guiTop && mouseY < absoluteY + manual.guiTop + objectHeight;
	}

	@Override
	public void onLeftClick()
	{
	}

	@Override
	public void onRightClick()
	{
	}

	@Override
	public int getObjectWidth()
	{
		return objectWidth;
	}

	@Override
	public int getObjectHeight()
	{
		return objectHeight;
	}

	@Override
	public int getX()
	{
		return x;
	}

	@Override
	public int getY()
	{
		return y;
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
}
