package com.alexjw.mcm.client.gui;

import com.alexjw.mcm.server.helper.LanternHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import org.lwjgl.opengl.GL11;

public class GuiButtonConstruct extends GuiButton {
    protected static RenderItem itemRender = new RenderItem();
    public Minecraft mc = Minecraft.getMinecraft();
    public ItemStack itemStack;
    protected FontRenderer fontRendererObj = mc.fontRenderer;

    public GuiButtonConstruct(int buttonId, ItemStack itemStack, int x, int y) {
        super(buttonId, x, y, 18, 18, itemStack.getDisplayName());
        this.itemStack = itemStack;
        if (itemStack.stackTagCompound == null) {
            itemStack.stackTagCompound = new NBTTagCompound();
        }
        itemStack.stackTagCompound.setString("lantern", LanternHelper.getPlayerLantern(Minecraft.getMinecraft().thePlayer).toString());
    }

    /**
     * Render an ItemStack. Args : stack, x, y, format
     */
    private void drawItemStack(ItemStack stack, int x, int y) {
        GL11.glTranslatef(0.0F, 0.0F, 32.0F);
        this.zLevel = 200.0F;
        itemRender.zLevel = 200.0F;
        FontRenderer font = null;
        if (stack != null) font = stack.getItem().getFontRenderer(stack);
        if (font == null) font = fontRendererObj;
        itemRender.renderItemAndEffectIntoGUI(font, this.mc.getTextureManager(), stack, x, y);
        itemRender.renderItemOverlayIntoGUI(font, this.mc.getTextureManager(), stack, x, y);
        this.zLevel = 0.0F;
        itemRender.zLevel = 0.0F;
    }

    /**
     * Draws this button to the screen.
     */
    public void drawButton(Minecraft mc, int mouseX, int mouseY) {
        if (this.visible) {
            this.drawItemStack(this.itemStack, this.xPosition, this.yPosition);
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        }
    }
}
