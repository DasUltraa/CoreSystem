package org.dasultra.api.builder;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.dasultra.api.builder.ItemBuilder;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class InventoryBuilder implements Cloneable {

    private final Inventory inventory;



    public InventoryBuilder(InventoryHolder holder, InventoryType type) {
        inventory = Bukkit.createInventory(holder, type);
    }

    public InventoryBuilder(InventoryHolder holder, @Nonnull int size, @Nonnull String title) {
        inventory = Bukkit.createInventory(holder, size, title);
    }

    public InventoryBuilder(Inventory inventory) {
        this.inventory = inventory;
    }

    public InventoryBuilder(InventoryType type) {
        inventory = Bukkit.createInventory(null, type);
    }

    public InventoryBuilder(@Nonnull int size, @Nonnull String title) {
        inventory = Bukkit.createInventory(null, size, title);
    }

    public InventoryBuilder setItem(@Nonnull int i, @Nonnull ItemStack item) {
        inventory.setItem(i, item);
        return this;
    }

    public InventoryBuilder addItem(@Nonnull ItemStack... item) {
        inventory.addItem(item);
        return this;
    }

    public Inventory build() {
        return inventory;
    }

    public InventoryBuilder clear() {
        inventory.clear();
        return this;
    }

    public InventoryBuilder fillInventory(@Nonnull int startPos, @Nonnull List<ItemStack> items) {
        if (items.size() > inventory.getSize()) {
            return this;
        }
        for (int i = 0; i < items.size(); i++) {
            setItem(startPos + i, new ItemStack(items.get(i)));
        }
        return this;
    }

    public InventoryBuilder fillInventory(@Nonnull int startPos, @Nonnull ArrayList<ItemStack> items) {
        if (items.size() > inventory.getSize()) {
            return this;
        }
        for (int i = 0; i < items.size(); i++) {
            setItem(startPos + i, new ItemStack(items.get(i)));
        }
        return this;
    }

    public InventoryBuilder fillInventory(@Nonnull int startPos, @Nonnull Material[] items) {
        if (items.length > inventory.getSize()) {
            return this;
        }
        for (int i = 0; i < items.length; i++) {
            setItem(startPos + i, new ItemStack(items[i]));
        }
        return this;
    }

    public InventoryBuilder fillInventory(@Nonnull int startPos, @Nonnull ItemStack[] items) {
        if (items.length > inventory.getSize()) {
            return this;
        }
        for (int i = 0; i < items.length; i++) {
            setItem(startPos + i, new ItemStack(items[i]));
        }
        return this;
    }

    public InventoryBuilder fillInventory(@Nonnull ArrayList<ItemStack> item) {
        for (int i = 0; i < item.size(); i++) {
            setItem(i, new ItemStack(item.get(i)));
        }
        return this;
    }

    public InventoryBuilder fillInventory(@Nonnull LinkedList<ItemStack> item) {
        for (int i = 0; i < item.size(); i++) {
            setItem(i, new ItemStack(item.get(i)));
        }
        return this;
    }


    public InventoryBuilder fillInventory(@Nonnull ItemStack item) {
        for (int i = 0; i < inventory.getSize(); i++) {
            setItem(i, new ItemStack(item));
        }
        return this;
    }



    @Override
    public InventoryBuilder clone() {
        try {
            return (InventoryBuilder) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }

    public static class RandomInventory implements Cloneable {

        Inventory inv;

        public RandomInventory(@Nonnull Inventory inv) {
            this.inv = inv;
        }

        public void setItemRandom(ItemStack item) {
            Random random = new Random();
            int current = random.nextInt(inv.getSize());
            inv.setItem(current, new ItemStack(item));
        }

        @Override
        public RandomInventory clone() {
            try {
                return (RandomInventory) super.clone();
            } catch (CloneNotSupportedException e) {
                throw new AssertionError();
            }
        }
    }

}

