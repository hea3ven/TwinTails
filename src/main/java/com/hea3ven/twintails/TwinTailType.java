package com.hea3ven.twintails;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.potion.Potion;
import net.minecraft.util.IIcon;

import com.hea3ven.twintails.client.model.ModelTwinTails;

public class TwinTailType {

    private String name;
    private ModelTwinTails model;
    private IIcon icon;
    private int ordinal;
    private Potion[] potions;

    public TwinTailType(int ordinal, String name, Potion[] potions) {
        this.ordinal = ordinal;
        this.name = name;
        this.potions = potions;
        model = new ModelTwinTails(name);
    }

    public int getOrdinal() {
        return ordinal;
    }

    public String getName() {
        return name;
    }

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

}
