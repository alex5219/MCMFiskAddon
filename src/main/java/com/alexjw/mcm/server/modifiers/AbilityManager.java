package com.alexjw.mcm.server.modifiers;

import com.fiskmods.heroes.common.hero.modifier.Ability;

import java.lang.reflect.Field;
import java.util.Locale;

public class AbilityManager {
    public static Ability CONSTRUCT = new AbilityConstruct(5);
    public static Ability WILLPOWER = new Ability(4);

    public static void register() {
        Field[] var0 = AbilityManager.class.getFields();

        for (Field field : var0) {
            if (field.getType() == Ability.class) {
                try {
                    Ability.register("mcm:" + field.getName().toLowerCase(Locale.ROOT), (Ability) field.get(null));
                } catch (Exception var5) {
                    var5.printStackTrace();
                }
            }
        }
    }
}
