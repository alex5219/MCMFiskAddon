package com.alexjw.mcm.client.render.item;

import com.alexjw.mcm.client.model.ModelWillRing;
import com.alexjw.mcm.server.items.ItemWillpowerRing;
import com.alexjw.mcm.server.items.ModItems;
import com.fiskmods.heroes.common.hero.HeroIteration;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;
import org.lwjgl.opengl.GL11;

public class RenderItemWillRing implements IItemRenderer {
    private static final Minecraft mc = Minecraft.getMinecraft();
    private static ResourceLocation texture = new ResourceLocation("mcm", "textures/models/will_ring.png");
    private static ModelWillRing model = new ModelWillRing();

    public static void render() {
        mc.getTextureManager().bindTexture(texture);
        model.render();
    }

    public boolean handleRenderType(ItemStack item, ItemRenderType type) {
        return true;
    }

    public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper) {
        return type == ItemRenderType.INVENTORY || type == ItemRenderType.ENTITY;
    }

    public void renderItem(ItemRenderType type, ItemStack itemstack, Object... data) {
        HeroIteration iter = ItemWillpowerRing.getContainedHero(itemstack);
        GL11.glPushMatrix();
        float scale;
        if (type == ItemRenderType.EQUIPPED_FIRST_PERSON) {
            GL11.glTranslatef(0.7F, 0.4F, 0.2F);
            GL11.glRotatef(-5.0F, 1.0F, 0.0F, 0.0F);
            GL11.glRotatef(210.0F, 0.0F, 0.0F, 1.0F);
            GL11.glRotatef(90.0F, 0.0F, 1.0F, 0.0F);
            GL11.glRotatef(70.0F * MathHelper.sin(Math.max(mc.thePlayer.rotationPitch / 90.0F, 0.0F)), -1.0F, 1.0F, 1.0F);
            scale = 0.75F;
            GL11.glScalef(scale, scale, scale);
            render();
        } else if (type == ItemRenderType.EQUIPPED) {
            GL11.glRotatef(15.0F, 1.0F, 0.0F, 0.0F);
            GL11.glRotatef(-79.0F, 0.0F, 0.0F, 1.0F);
            GL11.glRotatef(-105.0F, 1.0F, 0.0F, 0.0F);
            GL11.glTranslatef(0.2125F, -0.185F, 0.325F);
            scale = 0.4F;
            GL11.glScalef(scale, scale, scale);
            render();
        } else if (type == ItemRenderType.INVENTORY) {
            GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
            GL11.glRotatef(40.0F, 1.0F, 0.0F, 0.0F);
            GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
            GL11.glTranslatef(0.0F, 0.4F, 0.0F);
            scale = 2.75F;
            GL11.glScalef(scale, -scale, -scale);
            render();
        } else if (type == ItemRenderType.ENTITY) {
            GL11.glRotatef(-90.0F, 0.0F, 1.0F, 0.0F);
            GL11.glRotatef(90.0F, 1.0F, 0.0F, 0.0F);
            GL11.glRotatef(180.0F, 1.0F, 0.0F, 0.0F);
            GL11.glTranslatef(0.0F, 0.2F, 0.0F);
            scale = 1.0F;
            GL11.glScalef(scale, -scale, -scale);
            render();
        }
        if (iter == null) {
            GL11.glRotatef(-90.0F, 1.0F, 0.0F, 0.0F);
            GL11.glTranslatef(0.0F, 0.0F, -0.0625F);
            ItemStack item = new ItemStack(ModItems.willpowerLogo);

            if (type == ItemRenderType.INVENTORY) {
                scale = 0.01171875F;
                GL11.glScalef(-scale, -scale, scale);
                RenderItem.getInstance().renderItemIntoGUI(mc.fontRenderer, mc.getTextureManager(), item, -8, -8);
            } else {
                IIcon icon = item.getIconIndex();
                ResourceLocation location = mc.getTextureManager().getResourceLocation(item.getItemSpriteNumber());
                mc.getTextureManager().bindTexture(location);
                if (icon == null) {
                    icon = ((TextureMap) mc.getTextureManager().getTexture(location)).getAtlasSprite("missingno");
                }

                scale = 0.1875F;
                GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
                GL11.glScalef(scale, scale, scale);
                GL11.glTranslatef(-0.5F, -0.5F, 0.0F);
                ItemRenderer.renderItemIn2D(Tessellator.instance, icon.getMaxU(), icon.getMinV(), icon.getMinU(), icon.getMaxV(), icon.getIconWidth(), icon.getIconHeight(), 0.0625F);
            }
        }

        if (iter != null) {
            GL11.glRotatef(-90.0F, 1.0F, 0.0F, 0.0F);
            GL11.glTranslatef(0.0F, 0.0F, -0.0625F);
            ItemStack item = null;
            if (iter.hero.hasPieceOfSet(1)) {
                item = iter.createChestplate();
            } else {
                item = iter.createArmor(iter.hero.getFirstPieceOfSet());
            }

            if (item != null) {
                if (type == ItemRenderType.INVENTORY) {
                    scale = 0.01171875F;
                    GL11.glScalef(-scale, -scale, scale);
                    RenderItem.getInstance().renderItemIntoGUI(mc.fontRenderer, mc.getTextureManager(), item, -8, -8);
                } else {
                    IIcon icon = item.getIconIndex();
                    ResourceLocation location = mc.getTextureManager().getResourceLocation(item.getItemSpriteNumber());
                    mc.getTextureManager().bindTexture(location);
                    if (icon == null) {
                        icon = ((TextureMap) mc.getTextureManager().getTexture(location)).getAtlasSprite("missingno");
                    }

                    scale = 0.1875F;
                    GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
                    GL11.glScalef(scale, scale, scale);
                    GL11.glTranslatef(-0.5F, -0.5F, 0.0F);
                    ItemRenderer.renderItemIn2D(Tessellator.instance, icon.getMaxU(), icon.getMinV(), icon.getMinU(), icon.getMaxV(), icon.getIconWidth(), icon.getIconHeight(), 0.0625F);
                }
            }
        }

        GL11.glPopMatrix();
    }
}
