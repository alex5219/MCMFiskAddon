package com.alexjw.mcm.server.items;

import com.alexjw.mcm.server.items.constructs.ItemConstructWeapon;
import com.fiskmods.heroes.FiskHeroes;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Locale;

public class ModItems {
    public static ArrayList<Item> constructs = new ArrayList<>();
    public static ItemConstructWeapon BASEBALL_BAT = new ItemConstructWeapon("baseball_bat", 0, 24, 0f, 1.5f);
    public static ItemConstructWeapon BATTLE_AXE = new ItemConstructWeapon("battle_axe", 0, 18, 0f, 6.0f);
    public static ItemConstructWeapon BATTLE_SIGN = new ItemConstructWeapon("battle_sign", 0, 18, 0f, 2.0f);
    public static ItemConstructWeapon BATTON = new ItemConstructWeapon("batton", 0, 18, 0f, 2.0f);
    public static ItemConstructWeapon BOW = new ItemConstructWeapon("bow", 0, 18, 0f, 2.0f);
    public static ItemConstructWeapon CLEAVER = new ItemConstructWeapon("cleaver", 0, 18, 0f, 2.0f);
    public static ItemConstructWeapon CROWBAR = new ItemConstructWeapon("crowbar", 0, 18, 0f, 2.0f);
    public static ItemConstructWeapon CUTLASS = new ItemConstructWeapon("cutlass", 0, 18, 0f, 2.0f);
    public static ItemConstructWeapon DAGGER = new ItemConstructWeapon("dagger", 0, 18, 0f, 2.0f);
    public static ItemConstructWeapon EXCAVATOR = new ItemConstructWeapon("excavator", 0, 18, 0f, 2.0f);
    public static ItemConstructWeapon FRYING_PAN = new ItemConstructWeapon("frying_pan", 0, 18, 0f, 2.0f);
    public static ItemConstructWeapon GLAIVE = new ItemConstructWeapon("glaive", 0, 18, 0f, 2.0f);
    public static ItemConstructWeapon HALBERT = new ItemConstructWeapon("halbert", 0, 18, 0f, 2.0f);
    public static ItemConstructWeapon HAMMER = new ItemConstructWeapon("hammer", 0, 18, 0f, 2.0f);
    public static ItemConstructWeapon HATCHET = new ItemConstructWeapon("hatchet", 0, 18, 0f, 2.0f);
    public static ItemConstructWeapon KAI = new ItemConstructWeapon("kai", 0, 18, 0f, 2.0f);
    public static ItemConstructWeapon KATANA = new ItemConstructWeapon("katana", 0, 20, 0f, 4.75f);
    public static ItemConstructWeapon KATAR = new ItemConstructWeapon("katar", 0, 20, 0f, 4.75f);
    public static ItemConstructWeapon KUKRI = new ItemConstructWeapon("kukri", 0, 20, 0f, 4.75f);
    public static ItemConstructWeapon LONGSWORD = new ItemConstructWeapon("longsword", 0, 20, 0f, 4.75f);
    public static ItemConstructWeapon MACE = new ItemConstructWeapon("mace", 0, 20, 0f, 4.75f);
    public static ItemConstructWeapon MATTOCK = new ItemConstructWeapon("mattock", 0, 20, 0f, 4.75f);
    public static ItemConstructWeapon MEAT_CLEAVER = new ItemConstructWeapon("meat_cleaver", 0, 20, 0f, 4.75f);
    public static ItemConstructWeapon PICKAXE = new ItemConstructWeapon("pickaxe", 0, 20, 0f, 4.75f);
    public static ItemConstructWeapon PIPE_WRENCH = new ItemConstructWeapon("pipe_wrench", 0, 20, 0f, 4.75f);
    public static ItemConstructWeapon SABER = new ItemConstructWeapon("saber", 0, 20, 0f, 4.75f);
    public static ItemConstructWeapon SCYTHE = new ItemConstructWeapon("scythe", 0, 20, 0f, 4.75f);
    public static ItemConstructWeapon SHOVEL = new ItemConstructWeapon("shovel", 0, 20, 0f, 4.75f);
    public static ItemConstructWeapon SWORD = new ItemConstructWeapon("sword", 0, 18, 0f, 4.5f);
    public static ItemConstructWeapon TRIDENT = new ItemConstructWeapon("trident", 0, 20, 0f, 4.75f);
    public static ItemConstructWeapon WAR_HAMMER = new ItemConstructWeapon("war_hammer", 0, 20, 0f, 4.75f);
    public static ItemConstructWeapon WRENCH = new ItemConstructWeapon("wrench", 0, 20, 0f, 4.75f);

    public static Item willpowerRing;

    public static void register() {
        Field[] var0 = ModItems.class.getFields();

        for (Field field : var0) {
            if (field.getType() == ItemConstructWeapon.class) {
                try {
                    GameRegistry.registerItem((ItemConstructWeapon) field.get(null), field.getName().toLowerCase(Locale.ROOT));
                } catch (Exception var5) {
                    var5.printStackTrace();
                }
            }
        }

        willpowerRing = registerItem("willpower_ring", new ItemWillpowerRing());
    }

    public static Item registerItem(String unlocalizedName, Item item) {
        item.setCreativeTab(FiskHeroes.TAB_ITEMS);
        return registerItemNoTab(unlocalizedName, item);
    }

    public static Item registerItemNoTab(String unlocalizedName, Item item) {
        item.setUnlocalizedName(unlocalizedName);
        item.setTextureName("mcm:" + unlocalizedName);
        GameRegistry.registerItem(item, unlocalizedName);
        return item;
    }
}
