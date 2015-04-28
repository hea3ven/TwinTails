package com.hea3ven.twintails.client.model;

import org.lwjgl.opengl.GL11;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;

@SideOnly(Side.CLIENT)
public class ModelTwinTails extends ModelBiped {

    private IModelCustom model;
    private ResourceLocation modelTexture;

    public ModelTwinTails(String subType) {
        model = AdvancedModelLoader.loadModel(
                new ResourceLocation("twintails:models/twintails_" + subType + ".obj"));
        modelTexture = new ResourceLocation("twintails:textures/models/twintails_" + subType + ".png");
    }

    @Override
    public void render(Entity entity, float p_78088_2_, float p_78088_3_, float p_78088_4_, float headAngleY, float headAngleX, float p_78088_7_) {
        Minecraft.getMinecraft().getTextureManager().bindTexture(modelTexture);

        GL11.glPushMatrix();
        GL11.glRotatef(headAngleY, 0.0F, 1.0F, 0.0F);
        GL11.glRotatef(headAngleX, 1.0F, 0.0F, 0.0F);

        model.renderAll();

        GL11.glPopMatrix();
    }
}
