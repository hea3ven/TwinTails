package com.hea3ven.twintails;

import com.hea3ven.twintails.client.model.ModelTwinTails;

import net.minecraft.potion.Potion;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class TwinTailType {
    private String name;
    private ModelTwinTails model;
    private int ordinal;
    private Potion[] potions;
    private Object recipeIngredient;
	private int colorOrdinal;

    public TwinTailType(int ordinal, String name, Potion[] potions, Object recipeIngredient, int colorOrdinal) {
        this.ordinal = ordinal;
        this.name = name;
        this.potions = potions;
        this.recipeIngredient = recipeIngredient;
        this.colorOrdinal = colorOrdinal;
    }

	@SideOnly(Side.CLIENT)
	public void setModel(ModelTwinTails model) {
		this.model = model;
	}

    public int getOrdinal() {
        return ordinal;
    }

    public String getName() {
        return name;
    }

    @SideOnly(Side.CLIENT)
    public ModelTwinTails getModel() {
        return model;
    }

    public Potion[] getPotions() {
        return potions;
    }

    public Object getRecipeIngredient() {
        return recipeIngredient;
    }

	public int getColorOrdinal() {
		return colorOrdinal;
	}
}
