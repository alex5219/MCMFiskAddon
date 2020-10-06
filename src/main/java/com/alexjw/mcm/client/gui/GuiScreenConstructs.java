package com.alexjw.mcm.client.gui;

import com.alexjw.mcm.MCM;
import com.alexjw.mcm.server.items.ModItems;
import com.alexjw.mcm.server.network.MessageConstructItem;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiLabel;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatAllowedCharacters;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import java.util.List;

@SideOnly(Side.CLIENT)
public class GuiScreenConstructs extends GuiScreen {
    private final String title;
    private String name = "";

    public GuiScreenConstructs() {
        this.title = "Constructs";
    }

    @Override
    public void initGui() {
        buttonList.clear();
        int buttonID = 0;
        int constructCount = 0;
        int widthIncrease = 0;
        int heightDecrease = 0;
        for (Item item : ModItems.constructs) {
            buttonList.add(new GuiButtonConstruct(buttonID, new ItemStack(item),
                    width / 2 - 81 + widthIncrease, height / 2 - 16 + heightDecrease));
            buttonID++;

            widthIncrease = (widthIncrease + 18);

            constructCount++;
            if (constructCount == 9) {
                widthIncrease = 0;
                constructCount = 0;
                heightDecrease = (heightDecrease + 20);
            }
        }
    }

    @Override
    protected void actionPerformed(GuiButton button) {
        super.actionPerformed(button);

        if (button instanceof GuiButtonConstruct) {
            GuiButtonConstruct guiButtonConstruct = (GuiButtonConstruct) button;
            MCM.packetHandler.sendToServer(new MessageConstructItem(guiButtonConstruct.itemStack));
        }
    }


    @Override
    protected void keyTyped(char character, int key) {
        super.keyTyped(character, key);
        Keyboard.enableRepeatEvents(true);

        if (ChatAllowedCharacters.isAllowedCharacter(character) && fontRendererObj.getStringWidth(name + character + "_") < 170) {
            name += character;
        }

        if (name.length() > 0 && Keyboard.isKeyDown(Keyboard.KEY_BACK)) {
            if (isCtrlKeyDown()) {
                name = name.substring(0, name.contains(" ") ? name.lastIndexOf(" ") : 0);
            } else {
                name = name.substring(0, name.length() - 1);
            }
        }
    }

    @Override
    public boolean doesGuiPauseGame() {
        return false;
    }

    protected void renderToolTip(ItemStack itemIn, int x, int y) {
        List list = itemIn.getTooltip(this.mc.thePlayer, this.mc.gameSettings.advancedItemTooltips);

        for (int k = 0; k < list.size(); ++k) {
            if (k == 0) {
                list.set(k, "" + itemIn.getRarity().rarityColor + list.get(k));
            } else {
                list.set(k, "" + EnumChatFormatting.GRAY + list.get(k));
            }
        }

        FontRenderer font = itemIn.getItem().getFontRenderer(itemIn);
        drawHoveringText(list, x, y, (font == null ? fontRendererObj : font));
    }

    protected void drawHoveringText(List textLines, int x, int y, FontRenderer font) {
        if (!textLines.isEmpty()) {
            GL11.glDisable(GL12.GL_RESCALE_NORMAL);
            RenderHelper.disableStandardItemLighting();
            GL11.glDisable(GL11.GL_LIGHTING);
            GL11.glDisable(GL11.GL_DEPTH_TEST);
            int k = 0;

            for (Object textLine : textLines) {
                String s = (String) textLine;
                int l = font.getStringWidth(s);

                if (l > k) {
                    k = l;
                }
            }

            int j2 = x + 12;
            int k2 = y - 12;
            int i1 = 8;

            if (textLines.size() > 1) {
                i1 += 2 + (textLines.size() - 1) * 10;
            }

            if (j2 + k > this.width) {
                j2 -= 28 + k;
            }

            if (k2 + i1 + 6 > this.height) {
                k2 = this.height - i1 - 6;
            }

            this.zLevel = 300.0F;
            itemRender.zLevel = 300.0F;
            int j1 = -267386864;
            this.drawGradientRect(j2 - 3, k2 - 4, j2 + k + 3, k2 - 3, j1, j1);
            this.drawGradientRect(j2 - 3, k2 + i1 + 3, j2 + k + 3, k2 + i1 + 4, j1, j1);
            this.drawGradientRect(j2 - 3, k2 - 3, j2 + k + 3, k2 + i1 + 3, j1, j1);
            this.drawGradientRect(j2 - 4, k2 - 3, j2 - 3, k2 + i1 + 3, j1, j1);
            this.drawGradientRect(j2 + k + 3, k2 - 3, j2 + k + 4, k2 + i1 + 3, j1, j1);
            int k1 = 1347420415;
            int l1 = (k1 & 16711422) >> 1 | k1 & -16777216;
            this.drawGradientRect(j2 - 3, k2 - 3 + 1, j2 - 3 + 1, k2 + i1 + 3 - 1, k1, l1);
            this.drawGradientRect(j2 + k + 2, k2 - 3 + 1, j2 + k + 3, k2 + i1 + 3 - 1, k1, l1);
            this.drawGradientRect(j2 - 3, k2 - 3, j2 + k + 3, k2 - 3 + 1, k1, k1);
            this.drawGradientRect(j2 - 3, k2 + i1 + 2, j2 + k + 3, k2 + i1 + 3, l1, l1);

            for (int i2 = 0; i2 < textLines.size(); ++i2) {
                String s1 = (String) textLines.get(i2);
                font.drawStringWithShadow(s1, j2, k2, -1);

                if (i2 == 0) {
                    k2 += 2;
                }

                k2 += 10;
            }

            this.zLevel = 0.0F;
            itemRender.zLevel = 0.0F;
            GL11.glEnable(GL11.GL_LIGHTING);
            GL11.glEnable(GL11.GL_DEPTH_TEST);
            RenderHelper.enableStandardItemLighting();
            GL11.glEnable(GL12.GL_RESCALE_NORMAL);
        }
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        drawDefaultBackground();
        int x = width / 2 - 45;
        int y = height / 2 - 30;
        int additionalWidth = 40;
        drawCenteredString(fontRendererObj, title, width / 2, y - 15, 16777215);

        GL11.glColor4f(1, 1, 1, 1);
        mc.renderEngine.bindTexture(new ResourceLocation("textures/gui/container/creative_inventory/tab_item_search.png"));
        drawTexturedModalRect(x, y, 80, 4, 90, 12);
        drawTexturedModalRect(x - additionalWidth, y, 80, 4, 89, 12);
        drawTexturedModalRect(x + additionalWidth, y, 81, 4, 89, 12);
        drawString(fontRendererObj, name + (mc.thePlayer.ticksExisted % 20 >= 10 ? "" : "_"), x + 2 - additionalWidth, y + 2, 0xffffff);

        int k;

        for (Object guiButton : this.buttonList) {
            if (guiButton instanceof GuiButtonConstruct) {
                GuiButtonConstruct guiButtonConstruct = (GuiButtonConstruct) guiButton;
                if (guiButtonConstruct.itemStack.getUnlocalizedName().contains(this.name)) {
                    if (mouseX >= guiButtonConstruct.xPosition && mouseY >= guiButtonConstruct.yPosition && mouseX < guiButtonConstruct.xPosition + guiButtonConstruct.width && mouseY < guiButtonConstruct.yPosition + guiButtonConstruct.height) {
                        this.renderToolTip(guiButtonConstruct.itemStack, mouseX, mouseY + 28);
                    }
                    guiButtonConstruct.drawButton(this.mc, mouseX, mouseY);
                }
            }
        }
        super.drawScreen(mouseX, mouseY, partialTicks);
    }
}