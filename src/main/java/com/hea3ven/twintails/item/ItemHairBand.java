package com.hea3ven.twintails.item;

import com.hea3ven.twintails.client.model.ModelTwinTails;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class ItemHairBand extends ItemArmor {

    public static final ArmorMaterial hairBandArmorMaterial = ArmorMaterial.CLOTH;
    private ModelTwinTails model = new ModelTwinTails();

    public ItemHairBand() {
        super(hairBandArmorMaterial, 0, 0);
        setUnlocalizedName("hairBand");
        setCreativeTab(CreativeTabs.tabCombat);
        iconString = "minecraft:apple";
    }

    @Override
    public void onArmorTick(World world, EntityPlayer player, ItemStack itemStack) {
        if (!world.isRemote) {
            PotionEffect effect = player
                    .getActivePotionEffect(Potion.moveSpeed);
            if (effect == null || effect.getDuration() < 10)
                player.addPotionEffect(new PotionEffect(Potion.moveSpeed.id,
                        80, 2));
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public ModelBiped getArmorModel(EntityLivingBase entityLiving, ItemStack itemStack, int armorSlot) {
        return model;
    }
}
