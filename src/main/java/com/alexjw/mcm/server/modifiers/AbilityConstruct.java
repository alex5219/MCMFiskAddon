package com.alexjw.mcm.server.modifiers;

import com.alexjw.mcm.client.gui.GuiScreenConstructs;
import com.fiskmods.heroes.common.hero.Hero;
import com.fiskmods.heroes.common.hero.modifier.Ability;
import com.fiskmods.heroes.util.SHHelper;
import cpw.mods.fml.common.gameevent.TickEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;

public class AbilityConstruct extends Ability {
    public static final String KEY_CONSTRUCT = "CONSTRUCT";

    public AbilityConstruct(int tier) {
        super(tier);
    }

    public void onUpdate(EntityLivingBase entity, Hero hero, TickEvent.Phase phase, boolean enabled) {
        if (phase == TickEvent.Phase.END && enabled && hero.isKeyPressed(entity, KEY_CONSTRUCT) && SHHelper.getHero(entity).getAbilities().contains(this)) {
            if (entity instanceof EntityPlayer) {
                Minecraft.getMinecraft().displayGuiScreen(new GuiScreenConstructs());
            }
            // Open a menu
        }

    }
}