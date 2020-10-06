package com.alexjw.mcm.server.items;

import com.fiskmods.heroes.common.book.widget.IItemListEntry;
import com.fiskmods.heroes.common.hero.Hero;
import com.fiskmods.heroes.common.hero.HeroIteration;
import com.fiskmods.heroes.common.item.ItemUntextured;
import com.fiskmods.heroes.util.FiskServerUtils;
import com.fiskmods.heroes.util.SHFormatHelper;
import com.fiskmods.heroes.util.SHHelper;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;

import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.function.Predicate;

public class ItemWillpowerRing extends ItemUntextured implements IItemListEntry {
    private static final Predicate<Hero> PREDICATE = (t) -> t.getEquipmentStacks().stream().anyMatch((t1) -> t1.getItem() == ModItems.willpowerRing);

    public ItemWillpowerRing() {
        this.setMaxDamage(1024);
        this.setMaxStackSize(1);
        this.setHasSubtypes(true);
    }

    public static void setContainedArmor(ItemStack itemstack, ItemStack... armor) {
        NBTTagList itemsList = new NBTTagList();

        for (int i = 0; i < armor.length; ++i) {
            if (armor[i] != null) {
                NBTTagCompound itemTag = new NBTTagCompound();
                itemTag.setByte("Slot", (byte) i);
                itemsList.appendTag(armor[i].writeToNBT(itemTag));
            }
        }

        if (!itemstack.hasTagCompound()) {
            itemstack.setTagCompound(new NBTTagCompound());
        }

        itemstack.getTagCompound().setTag("Items", itemsList);
    }

    public static ItemStack[] getArmorFromNBT(ItemStack itemstack) {
        if (itemstack.hasTagCompound() && itemstack.getTagCompound().hasKey("Items")) {
            NBTTagList nbtItems = itemstack.getTagCompound().getTagList("Items", 10);
            ItemStack[] items = new ItemStack[4];

            for (int i = 0; i < nbtItems.tagCount(); ++i) {
                NBTTagCompound item = nbtItems.getCompoundTagAt(i);
                byte slot = item.getByte("Slot");
                if (slot >= 0 && slot < items.length) {
                    items[slot] = ItemStack.loadItemStackFromNBT(item);
                }
            }

            return items;
        } else {
            return null;
        }
    }

    public static HeroIteration getContainedHero(ItemStack itemstack) {
        if (itemstack.hasTagCompound()) {
            if (itemstack.getTagCompound().hasKey("Items", 9) && !itemstack.getTagCompound().getBoolean("Dispensed")) {
                return SHHelper.getHeroIterFromArmor(FiskServerUtils.nonNull(Objects.requireNonNull(getArmorFromNBT(itemstack))));
            }

            if (itemstack.getTagCompound().hasKey("Suit", 8)) {
                return HeroIteration.lookup(itemstack.getTagCompound().getString("Suit"));
            }
        }

        return null;
    }

    public static ItemStack create(Item item, HeroIteration iter) {
        ItemStack itemstack = new ItemStack(item);
        itemstack.setTagCompound(new NBTTagCompound());
        itemstack.getTagCompound().setString("Suit", iter.toString());
        return itemstack;
    }

    public static ItemStack create(HeroIteration iter) {
        return create(ModItems.willpowerRing, iter);
    }

    public void addInformation(ItemStack itemstack, EntityPlayer player, List list, boolean advanced) {
        HeroIteration iter = getContainedHero(itemstack);
        if (iter != null) {
            list.add(SHFormatHelper.formatHero(iter));
        }
        list.add("" + EnumChatFormatting.GREEN + (this.getMaxDamage(itemstack) - this.getDamage(itemstack)) + "/" + EnumChatFormatting.GREEN + this.getMaxDamage(itemstack));
    }

    public void onUpdate(ItemStack stack, World world, Entity entity, int f, boolean b) {
        if (!world.isRemote) {
            if(stack.getItemDamage() == stack.getMaxDamage()) {
                Random random = new Random();
                if (random.nextInt(100) == 20) {
                    stack.attemptDamageItem(-(random.nextInt(3) + 1), random);
                }
            }
        }
    }

    public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer player) {
        if (itemstack.hasTagCompound()) {
            if (itemstack.getTagCompound().hasKey("Suit", 8)) {
                HeroIteration iter = HeroIteration.lookup(itemstack.getTagCompound().getString("Suit"));
                if (iter != null) {
                    setContainedArmor(itemstack, iter.createArmorStacks());
                }
                itemstack.getTagCompound().removeTag("Suit");
            }

            if (itemstack.getTagCompound().getBoolean("Dispensed")) {
                itemstack.getTagCompound().removeTag("Items");
            }

            if (itemstack.getTagCompound().hasKey("Dispensed")) {
                itemstack.getTagCompound().removeTag("Dispensed");
            }
        }

        ItemStack[] equipment = SHHelper.getEquipment(player);
        HeroIteration iter = SHHelper.getHeroIter(equipment);
        if (itemstack.hasTagCompound() && itemstack.getTagCompound().hasKey("Items", 9)) {
            ItemStack[] armorFromNBT = getArmorFromNBT(itemstack);
            int i;
            if (iter != null && PREDICATE.test(iter.hero)) {
                setContainedArmor(itemstack, equipment);
                if (armorFromNBT != null) {
                    for (i = 0; i < 4; ++i) {
                        player.setCurrentItemOrArmor(4 - i, armorFromNBT[i]);
                    }
                }
            } else {
                if (armorFromNBT != null) {
                    for (i = 0; i < 4; ++i) {
                        this.swapArmor(player, armorFromNBT[i], 3 - i);
                    }
                }

                itemstack.getTagCompound().removeTag("Items");
            }
        } else if (iter != null && PREDICATE.test(iter.hero)) {
            setContainedArmor(itemstack, equipment);

            for (int i = 1; i <= 4; ++i) {
                player.setCurrentItemOrArmor(i, null);
            }
        }
        if (itemstack.hasTagCompound() && itemstack.getTagCompound().hasNoTags()) {
            itemstack.setTagCompound(null);
        }

        return itemstack;
    }

    public void swapArmor(EntityPlayer player, ItemStack itemstack, int slot) {
        if (itemstack != null) {
            ItemStack armor = player.getCurrentArmor(slot);
            if (armor != null && !player.inventory.addItemStackToInventory(armor)) {
                player.entityDropItem(armor, 0.0F);
            }

            player.setCurrentItemOrArmor(slot + 1, itemstack);
        }

    }

    public void getSubItems(Item item, CreativeTabs tab, List list) {
        list.add(new ItemStack(item));
        Hero.REGISTRY.getValues().stream().filter(PREDICATE.and((t) -> !t.isHidden())).sorted().forEach((t) -> {
            if (tab == null) {
                t.getIterations().stream().sorted().forEach((t1) -> {
                    list.add(create(t1));
                });
            } else {
                list.add(create(t.getDefault()));
            }
        });
    }

    public void getListItems(Item item, CreativeTabs tab, List list) {
        super.getSubItems(item, tab, list);
    }

    public String getPageLink(ItemStack itemstack) {
        return itemstack.getUnlocalizedName();
    }

    public void onCreated(ItemStack itemStack, World world, EntityPlayer entityPlayer) {
        itemStack.damageItem(1, entityPlayer);
    }

    public boolean showDurabilityBar(ItemStack stack)
    {
        return true;
    }
}
