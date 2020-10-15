package com.alexjw.mcm.server.items;

import com.alexjw.mcm.server.items.constructs.IConstruct;
import com.alexjw.mcm.server.items.constructs.ItemConstruct;
import com.alexjw.mcm.server.items.constructs.ItemConstructWeapon;
import com.fiskmods.heroes.FiskHeroes;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Locale;

public class ModItems {
    public static ArrayList<Item> constructs = new ArrayList<>();
    public static ItemConstructWeapon BASEBALL_BAT = new ItemConstructWeapon("baseball_bat", 20, 8, 1f);
    public static ItemConstructWeapon BATTLE_AXE = new ItemConstructWeapon("battle_axe", 20, 8, 4f);
    public static ItemConstructWeapon BATTLE_SIGN = new ItemConstructWeapon("battle_sign", 20, 8, 4f);
    public static ItemConstructWeapon BATTON = new ItemConstructWeapon("batton", 20, 8, 4f);
    public static ItemConstruct BOW = new ItemConstruct("bow", 20);
    public static ItemConstructWeapon CLEAVER = new ItemConstructWeapon("cleaver", 20, 8, 4f);
    public static ItemConstructWeapon CROWBAR = new ItemConstructWeapon("crowbar", 20, 8, 4f);
    public static ItemConstructWeapon CUTLASS = new ItemConstructWeapon("cutlass", 20, 8, 4f);
    public static ItemConstructWeapon DAGGER = new ItemConstructWeapon("dagger", 20, 8, 4f);
    public static ItemConstructWeapon EXCAVATOR = new ItemConstructWeapon("excavator", 20, 8, 4f);
    public static ItemConstructWeapon FRYING_PAN = new ItemConstructWeapon("frying_pan", 20, 8, 4f);
    public static ItemConstructWeapon GLAIVE = new ItemConstructWeapon("glaive", 20, 8, 4f);
    public static ItemConstructWeapon HALBERT = new ItemConstructWeapon("halbert", 20, 8, 4f);
    public static ItemConstructWeapon HAMMER = new ItemConstructWeapon("hammer", 20, 8, 4f);
    public static ItemConstructWeapon HATCHET = new ItemConstructWeapon("hatchet", 20, 8, 4f);
    public static ItemConstructWeapon KAI = new ItemConstructWeapon("kai", 20, 8, 4f);
    public static ItemConstructWeapon KATANA = new ItemConstructWeapon("katana", 20, 8, 4f);
    public static ItemConstructWeapon KATAR = new ItemConstructWeapon("katar", 20, 8, 4f);
    public static ItemConstructWeapon KUKRI = new ItemConstructWeapon("kukri", 20, 8, 4f);
    public static ItemConstructWeapon LONGSWORD = new ItemConstructWeapon("longsword", 20, 8, 4f);
    public static ItemConstructWeapon MACE = new ItemConstructWeapon("mace", 20, 8, 4f);
    public static ItemConstructWeapon MATTOCK = new ItemConstructWeapon("mattock", 20, 8, 4f);
    public static ItemConstructWeapon MEAT_CLEAVER = new ItemConstructWeapon("meat_cleaver", 20, 8, 4f);
    public static ItemConstructWeapon PICKAXE = new ItemConstructWeapon("pickaxe", 20, 8, 4f);
    public static ItemConstructWeapon PIPE_WRENCH = new ItemConstructWeapon("pipe_wrench", 20, 8, 4f);
    public static ItemConstructWeapon SABER = new ItemConstructWeapon("saber", 20, 8, 4f);
    public static ItemConstructWeapon SCYTHE = new ItemConstructWeapon("scythe", 20, 8, 4f);
    public static ItemConstructWeapon SHOVEL = new ItemConstructWeapon("shovel", 20, 8, 4f);
    public static ItemConstructWeapon SWORD = new ItemConstructWeapon("sword", 20, 8, 4f);
    public static ItemConstructWeapon TRIDENT = new ItemConstructWeapon("trident", 20, 8, 4f);
    public static ItemConstructWeapon WAR_HAMMER = new ItemConstructWeapon("war_hammer", 20, 8, 4f);
    public static ItemConstructWeapon WRENCH = new ItemConstructWeapon("wrench", 20, 8, 4f);

    public static Item willpowerRing;
    public static Item willpowerLogo;

    public static void register() {
        for(Item item:constructs) {
            GameRegistry.registerItem(item, item.getUnlocalizedName().toLowerCase(Locale.ROOT));
        }

        willpowerLogo = registerItem("willpower_logo", new Item());
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
