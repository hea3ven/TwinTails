package com.hea3ven.twintails.client.model;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;

public class ModelTwinTails extends ModelBiped {

    private IModelCustom model;

    public ModelTwinTails() {
        model = AdvancedModelLoader.loadModel(new ResourceLocation("twintails:models/twintails_normal.obj"));
    }

    @Override
    public void render(Entity entity, float p_78088_2_, float p_78088_3_, float p_78088_4_, float p_78088_5_, float p_78088_6_, float p_78088_7_) {

        float rotateAngleY = p_78088_5_ / (180F / (float) Math.PI);
        float rotateAngleX = p_78088_6_ / (180F / (float) Math.PI);

        Minecraft.getMinecraft().getTextureManager()
                .bindTexture(
                        new ResourceLocation("twintails:textures/twintails_normal.png"));

        GL11.glPushMatrix();
        if (rotateAngleY != 0.0F)
            GL11.glRotatef(rotateAngleY * (180F / (float) Math.PI), 0.0F, 1.0F, 0.0F);

        if (rotateAngleX != 0.0F)
            GL11.glRotatef(rotateAngleX * (180F / (float) Math.PI), 1.0F, 0.0F, 0.0F);

        GL11.glScalef(-1.0F, -1.0F, 1.0F);

        model.renderAll();

        GL11.glPopMatrix();
    }
}
