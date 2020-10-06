package com.alexjw.mcm.client.gui;

import com.alexjw.mcm.server.helper.LanternHelper;
import com.fiskmods.heroes.client.SHRenderHooks;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import org.lwjgl.opengl.GL11;

public class GuiButtonConstruct extends GuiButton {
    public ItemStack itemStack;
    private boolean isActive = true;
    protected static RenderItem renderItem = RenderItem.getInstance();

    public GuiButtonConstruct(int buttonId, ItemStack itemStack, int x, int y) {
        super(buttonId, x, y, 18, 18, itemStack.getDisplayName());
        this.itemStack = itemStack;
    }

    /**
     * Draws this button to the screen.
     */
    public void drawButton(Minecraft mc, int mouseX, int mouseY) {
        if (this.visible) {
            if(isActive) {
                if (itemStack != null) {
                    if (itemStack.stackTagCompound == null) {
                        itemStack.stackTagCompound = new NBTTagCompound();
                    }
                    itemStack.stackTagCompound.setString("lantern", LanternHelper.getPlayerLantern(Minecraft.getMinecraft().thePlayer).toString());
                    FontRenderer font = itemStack.getItem().getFontRenderer(itemStack);
                    if (font == null) {
                        font = mc.fontRenderer;
                    }
                    renderItem.renderItemIntoGUI(font, mc.getTextureManager(), itemStack, this.xPosition, this.yPosition);
                }
            }
        }
    }

    public void setActive(boolean isActive) {
        this.isActive = isActive;
    }

    public boolean isActive() {
        return isActive;
    }
}
