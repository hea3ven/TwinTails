package com.hea3ven.twintails;

import com.hea3ven.twintails.client.model.ModelTwinTails;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.potion.Potion;
import net.minecraft.util.IIcon;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class TwinTailType {

    private String name;
    private ModelTwinTails model;
    private IIcon icon;
    private int ordinal;
    private Potion[] potions;
    private Object recipeIngredient;

    public TwinTailType(int ordinal, String name, Potion[] potions, Object recipeIngredient) {
        this.ordinal = ordinal;
        this.name = name;
        this.potions = potions;
        this.recipeIngredient = recipeIngredient;
        if (FMLCommonHandler.instance().getEffectiveSide() == Side.CLIENT)
        	model = new ModelTwinTails(name);
    }

    public int getOrdinal() {
        return ordinal;
    }

    public String getName() {
        return name;
    }

    @SideOnly(Side.CLIENT)
    public ModelBiped getModel() {
        return model;
    }

    public void registerIcon(IIconRegister iconRegister) {
        icon = iconRegister.registerIcon("twintails:hairband_" + name);
    }

    public IIcon getIcon() {
        return icon;
    }

    public Potion[] getPotions() {
        return potions;
    }

    public Object getRecipeIngredient() {
        return recipeIngredient;
    }

}
