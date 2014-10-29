package com.hea3ven.twintails.item;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class ItemHairBand extends ItemArmor {

    public static final ArmorMaterial hairBandArmorMaterial = ArmorMaterial.CLOTH;

    public ItemHairBand() {
        super(hairBandArmorMaterial, 0, 0);
        setUnlocalizedName("hairBand");
        setCreativeTab(CreativeTabs.tabCombat);
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

}
