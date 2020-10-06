package com.alexjw.mcm.server.items.constructs;

import com.alexjw.mcm.MCM;
import com.alexjw.mcm.server.helper.LanternHelper;
import com.alexjw.mcm.server.items.ModItems;
import com.alexjw.mcm.server.modifiers.AbilityManager;
import com.fiskmods.heroes.util.SHHelper;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IIcon;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import net.minecraftforge.common.util.EnumHelper;

import java.util.List;
import java.util.Random;

public class ItemConstructWeapon extends ItemSword implements IConstruct {
    private int damage, maxDurability;

    public ItemConstructWeapon(String name, int harvestLevel, int maxDurability, float efficiency, float damage) {
        super(EnumHelper.addToolMaterial(name, harvestLevel, maxDurability, efficiency, damage, 0));
        this.setUnlocalizedName("construct_" + name);
        this.setTextureName(MCM.MODID + ":construct_" + name);
        this.setMaxDamage(maxDurability);
        this.setMaxStackSize(1);
        this.damage = (int) damage;
        this.maxDurability = maxDurability;
        this.setCreativeTab(null);
        ModItems.constructs.add(this);
    }

    /**
     * Calculate the cost for a weapon construct.
     *
     * These values should change between tools & weapons
     *
     * @return The overall cost after the math
     */
    public int getCost() {
        return (int) ((this.damage * 2.75) + (this.maxDurability * 0.75));
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
                + "Durability: " + EnumChatFormatting.WHITE + this.maxDurability);
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
}
