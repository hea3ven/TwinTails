package com.hea3ven.twintails;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class CreativeTabTwinTails extends CreativeTabs {

	private ItemStack stack;

	public CreativeTabTwinTails() {
		super("twintails");
	}

	@Override
	public ItemStack getIconItemStack() {
		return stack;
	}

	@Override
	public Item getTabIconItem() {
		return stack.getItem();
	}

	public void init() {
		stack = new ItemStack(TwinTailsMod.hairBand, 1, 1);
	}

}
