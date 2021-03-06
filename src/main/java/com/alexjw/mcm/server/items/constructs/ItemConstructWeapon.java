package com.alexjw.mcm.server.items.constructs;

import com.alexjw.mcm.MCM;
import com.alexjw.mcm.server.helper.LanternHelper;
import com.alexjw.mcm.server.items.ModItems;
import com.google.common.collect.Multimap;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;
import net.minecraftforge.common.util.EnumHelper;

import java.util.List;

public class ItemConstructWeapon extends ItemSword implements IConstruct {
    private float damage, reach;

    public ItemConstructWeapon(String name, int maxDurability, float damage, float reach) {
        super(EnumHelper.addToolMaterial(name, 0, maxDurability, 0f, damage, 0));
        this.damage = damage;
        this.reach = reach;
        this.setMaxDamage(maxDurability);
        this.setUnlocalizedName("construct_" + name);
        this.setTextureName(MCM.MODID + ":construct_" + name);
        this.setMaxDamage(maxDurability);
        this.setMaxStackSize(1);
        this.setCreativeTab(null);
        ModItems.constructs.add(this);
    }

    public int getCost() {
        return (int) (this.getMaxDamage() * 0.75);
    }

    // Everything from here on is the same for every construct.
    public void onUpdate(ItemStack itemStack, World world, Entity entity, int p_77663_4_, boolean p_77663_5_) {
        LanternHelper.constructUpdate(itemStack, world, entity, p_77663_4_, p_77663_5_);
    }

    @SideOnly(Side.CLIENT)
    public int getColorFromItemStack(ItemStack itemStack, int p_82790_2_) {
        return LanternHelper.getColorFromLantern(LanternHelper.getLantern(itemStack));
    }

    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack itemStack, EntityPlayer entityPlayer, List list, boolean p_77624_4_) {
        list.add(LanternHelper.getChatColorFromLantern(LanternHelper.getLantern(itemStack))
                + "Durability: " + EnumChatFormatting.WHITE + this.getMaxDamage());
        list.add(LanternHelper.getChatColorFromLantern(LanternHelper.getLantern(itemStack))
                + "Cost: " + EnumChatFormatting.WHITE + getCost());
        list.add(LanternHelper.getChatColorFromLantern(LanternHelper.getLantern(itemStack))
                + "" + LanternHelper.getLantern(itemStack));
    }

    @SideOnly(Side.CLIENT)
    public String getItemStackDisplayName(ItemStack itemStack) {
        return LanternHelper.getChatColorFromLantern(LanternHelper.getLantern(itemStack)) +
                "" + LanternHelper.getLantern(itemStack) + " " + super.getItemStackDisplayName(itemStack);
    }

    @Override
    public Multimap getAttributeModifiers(ItemStack stack) {
        Multimap map = super.getAttributeModifiers(stack);

        return map;
    }
}
