package org.dasultra.api.builder;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.*;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.potion.PotionEffect;

import javax.annotation.Nonnull;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.UUID;

public class ItemBuilder implements Cloneable {

    ItemStack item;
    ItemMeta meta;

    public ItemBuilder(@Nonnull Material material) {
        this.item = new ItemStack(material);
        this.meta = item.getItemMeta();
    }

    public ItemBuilder(TypePotion type) {
        if (type == TypePotion.POTION) {
            item = new ItemStack(Material.POTION);
            meta = item.getItemMeta();
        }
        if (type == TypePotion.SPLASH) {
            item = new ItemStack(Material.SPLASH_POTION);
            meta = item.getItemMeta();
        }
        if (type == TypePotion.LINGERING) {
            item = new ItemStack(Material.LINGERING_POTION);
            meta = item.getItemMeta();
        }
    }

    public ItemBuilder(@Nonnull ItemStack item) {
        this.item = item;
        this.meta = item.getItemMeta();
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public ItemBuilder setDataContainer(NamespacedKey key) {
        meta.getPersistentDataContainer().set(key, PersistentDataType.BYTE, (byte) 1);
        return this;
    }

    public ItemBuilder addPotion(PotionEffect effect) {
        if (meta instanceof PotionMeta p) {
            p.addCustomEffect(effect, true);
        }
        return this;
    }

    public ItemBuilder addPotion(PotionEffect... effect) {
        if (meta instanceof PotionMeta p) {
            for (PotionEffect f : effect) {
                p.addCustomEffect(f, true);
            }
        }
        return this;
    }

    public ItemBuilder setColor(Color color) {
        if (meta instanceof PotionMeta p) {
            p.setColor(color);
        }
        return this;
    }

    public ItemBuilder addEnchantment(Enchantment enchantment, int level) {
        meta.addEnchant(enchantment, level, true);
        return this;
    }

    public ItemBuilder setOwningSkull(String name) {
        return setOwningSkull(Bukkit.getOfflinePlayer(name).getUniqueId());
    }

    public ItemBuilder setOwningSkull(UUID uuid) {
        if (meta instanceof SkullMeta skull) {
            skull.setOwningPlayer(Bukkit.getOfflinePlayer(uuid));
        }
        return this;
    }

    public ItemBuilder colorLeather(Color color) {
        if (meta instanceof LeatherArmorMeta m) {
            m.setColor(color);
        }
        return this;
    }

    public ItemBuilder getItemFlags(ItemFlag... flags) {
        meta.addItemFlags(flags);
        return this;
    }

    public ItemBuilder setAmount(int amount) {
        item.setAmount(amount);
        return this;
    }

    public ItemBuilder removeOneAmount() {
        if (item.getAmount() == 1) {
            return setAmount(0);
        }
        return setAmount(item.getAmount() - 1);
    }

    public ItemBuilder setUnbreakable(boolean breakable) {
        meta.setUnbreakable(breakable);
        return this;
    }

    public ItemBuilder setCustomModelData(int data) {
        meta.setCustomModelData(data);
        return this;
    }

    public ItemBuilder setLore(String... lore) {
        return setLore(Arrays.asList(lore));
    }

    public ItemBuilder setLore(List<String> lore) {
        meta.setLore(lore);
        return this;
    }

    public ItemBuilder romanIndulges(int amount) {
        boolean give = new Random().nextBoolean();
        return give ? setAmount(amount) : setAmount(0);
    }

    public ItemBuilder romanIndulges(int amount, boolean random) {
        boolean give = new Random().nextBoolean();
        if (random) {
            var i = new Random().nextInt(amount) + 1;
            return give ? setAmount(i) : setAmount(0);
        }
        return give ? setAmount(amount) : setAmount(0);
    }

    public ItemBuilder romanIndulges() {
        boolean give = new Random().nextBoolean();
        return give ? setAmount(1) : setAmount(0);
    }

    public ItemBuilder randomAmount(int max, boolean zero) {
        int r;
        if (!zero) {
            r = new Random().nextInt(max) + 1;
        } else {
            r = new Random().nextInt(max);
        }
        return setAmount(r);
    }

    public ItemBuilder randomAmount(int max) {
        return randomAmount(max, false);
    }

    public ItemBuilder chanceOfThousand(int chance) {
        var a = new Random().nextInt(1000) + 1;
        if (a <= chance) {
            setAmount(1);
        } else {
            setAmount(0);
        }
        return this;
    }

    public ItemBuilder setDamage(int i) {
        if (meta instanceof Damageable d) {
            d.setDamage(i);
        }
        return this;
    }

    public ItemBuilder setDamage(int i, boolean random) {
        if (random) {
            var c = new Random().nextInt(i);
            setDamage(c);
        } else {
            setDamage(i);
        }
        return this;
    }

    public ItemBuilder randomEnchantment(int chance, Enchantment[] enchantments) {

        var random = new Random().nextInt(100) + 1;
        var current = new Random().nextInt(enchantments.length);

        if (random < chance) {
            addEnchantment(enchantments[current], new Random().nextInt(enchantments[current].getMaxLevel()) + 1);
        }

        return this;
    }

    public ItemBuilder setDisplayName(String name) {
        meta.setDisplayName(name);
        return this;
    }

    public ItemStack build() {
        item.setItemMeta(meta);
        return item;
    }

    public enum TypePotion {

        POTION,
        SPLASH,
        LINGERING

    }

}
