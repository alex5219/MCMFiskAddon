package com.alexjw.mcm.server.registry;

import com.fiskmods.heroes.FiskHeroes;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.oredict.OreDictionary;

import java.util.function.Function;

public class BlockRegistry {
    public BlockRegistry() {
    }

    public static BlockRegistry.RegistryBuilder builder(String name) {
        return new BlockRegistry.RegistryBuilder(name);
    }

    public static class RegistryBuilder {
        private final String name;
        private String oreName;
        private String tileName;
        private Function<Block, ItemBlock> item;
        private Class<? extends ItemBlock> itemClass;
        private Class<? extends TileEntity> tileClass;

        public RegistryBuilder(String name) {
            this.name = name;
        }

        public BlockRegistry.RegistryBuilder ore(String oreName) {
            this.oreName = oreName;
            return this;
        }

        public BlockRegistry.RegistryBuilder item(Function<Block, ItemBlock> item) {
            this.item = item;
            return this;
        }

        public BlockRegistry.RegistryBuilder item(Class<? extends ItemBlock> itemClass) {
            this.itemClass = itemClass;
            return this;
        }

        public BlockRegistry.RegistryBuilder tile(Class<? extends TileEntity> tileClass) {
            this.tileClass = tileClass;
            return this;
        }

        public BlockRegistry.RegistryBuilder tile(Class<? extends TileEntity> tileClass, String tileName) {
            this.tileClass = tileClass;
            this.tileName = tileName;
            return this;
        }

        public <T extends Block> T register(T block) {
            block.setBlockName(this.name);
            block.setBlockTextureName("mcm:" + this.name);
            block.setCreativeTab(FiskHeroes.TAB_ITEMS);
            if (this.itemClass != null) {
                GameRegistry.registerBlock(block, this.itemClass, this.name);
            } else if (this.item != null) {
                GameRegistry.registerBlock(block, null, this.name);
                Item.itemRegistry.addObject(Block.getIdFromBlock(block), this.name, this.item.apply(block).setUnlocalizedName(this.name));
            } else {
                GameRegistry.registerBlock(block, this.name);
            }

            if (this.tileClass != null) {
                GameRegistry.registerTileEntity(this.tileClass, this.tileName != null ? this.tileName : this.name);
            }

            if (this.oreName != null) {
                OreDictionary.registerOre(this.oreName, block);
            }

            return block;
        }
    }
}