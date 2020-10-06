package com.alexjw.mcm.server.helper;

import com.alexjw.mcm.server.items.constructs.EnumLanterns;
import com.alexjw.mcm.server.modifiers.AbilityManager;
import com.fiskmods.heroes.util.SHHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;

import java.util.Random;

public class LanternHelper {
    public static int getColorFromLantern(EnumLanterns enumLanterns) {
        switch (enumLanterns) {
            case Willpower:
                return 4764952;
            case NOTSET:
                return 2134124;
            default:
                return 16777215;
        }
    }

    public static EnumChatFormatting getChatColorFromLantern(EnumLanterns enumLanterns) {
        switch (enumLanterns) {
            case Willpower:
                return EnumChatFormatting.GREEN;
            case NOTSET:
                return EnumChatFormatting.DARK_RED;
            default:
                return EnumChatFormatting.WHITE;
        }
    }

    public static EnumLanterns getLanternFromString(String color) {
        return EnumLanterns.valueOf(color);
    }

    public static void consumePower(ItemStack itemStack, EntityPlayer entityPlayer, int power) {
        if (!entityPlayer.worldObj.isRemote) {
            int toConsume = power;
            while (toConsume > 0) {
                if ((itemStack.getMaxDamage() - itemStack.getItemDamage()) >= 1) {
                    itemStack.damageItem(1, entityPlayer);
                }
                toConsume--;
            }
        }
    }


    public static EnumLanterns getPlayerLantern(EntityPlayer entityPlayer) {
        EnumLanterns enumLanterns = EnumLanterns.NOTSET;
        if (SHHelper.getHero(entityPlayer) != null) {
            if (SHHelper.getHero(entityPlayer).getAbilities() != null) {
                if (SHHelper.getHero(entityPlayer).getAbilities().contains(AbilityManager.WILLPOWER)) {
                    if (SHHelper.getHero(entityPlayer).getAbilities().contains(AbilityManager.WILLPOWER)) {
                        enumLanterns = EnumLanterns.Willpower;
                    }
                }
            }
        }
        return enumLanterns;
    }


    public static void constructUpdate(ItemStack itemStack, World world, Entity entity, int p_77663_4_, boolean p_77663_5_) {
        if (getLantern(itemStack) == EnumLanterns.NOTSET) {
            if (entity instanceof EntityPlayer) {
                if (itemStack.stackTagCompound == null) {
                    itemStack.stackTagCompound = new NBTTagCompound();
                }
                itemStack.stackTagCompound.setString("lantern", LanternHelper.getPlayerLantern((EntityPlayer) entity).toString());
            }
        }
        if(world.isRemote) {
            Random random = new Random();
            if (random.nextInt(1000) == 20) {
                itemStack.attemptDamageItem(1, random);

                if (itemStack.getItem().getDamage(itemStack) > itemStack.getItem().getMaxDamage(itemStack)) {
                    itemStack.stackSize--;
                }
            }
        }
    }


    public static EnumLanterns getLantern(ItemStack itemStack) {
        if (itemStack.stackTagCompound != null) {
            if (itemStack.stackTagCompound.hasKey("lantern")) {
                return LanternHelper.getLanternFromString(itemStack.stackTagCompound.getString("lantern"));
            } else {
                return EnumLanterns.NOTSET;
            }
        } else {
            return EnumLanterns.NOTSET;
        }
    }
}
