package com.hea3ven.twintails;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;

import com.hea3ven.twintails.client.model.ModelTwinTails;

public class TwinTailType {

    private String name;
    private ModelTwinTails model;
    private IIcon icon;
    private int ordinal;

    public TwinTailType(int ordinal, String name) {
        this.ordinal = ordinal;
        this.name = name;
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
        icon = iconRegister.registerIcon("twintails:twintail_" + name);
    }

    public IIcon getIcon() {
        return icon;
    }

}
