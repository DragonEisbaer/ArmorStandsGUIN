package me.dragoneisbaer.armorstandsgui.events;

import me.dragoneisbaer.armorstandsgui.ArmorStandsGUI;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class MenuHandler implements Listener {

    private final ArmorStandsGUI plugin;

    public MenuHandler(ArmorStandsGUI plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onClick(InventoryClickEvent e) {

        final String ArmorMenuName = ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("ArmorMenuName"));
        final String ConfirmMenuName = ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("ConfirmMenuName"));
        final String MainMenuName = ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("MainMenuName"));
        final String CreateMenuName = ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("CreateMenuName"));

        Player player = (Player) e.getWhoClicked();

        if (e.getView().getTitle().equalsIgnoreCase(ChatColor.translateAlternateColorCodes('&', MainMenuName))) {
            e.setCancelled(true);
            if (e.getCurrentItem() == null) {
                return;
            }
            switch (e.getCurrentItem().getType()) {
                case ARMOR_STAND:
                    if (e.getCurrentItem().hasItemMeta()) {
                        if (plugin.getConfig().getBoolean("ShowCloseOpenMenuMessage")) {
                            player.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("OpenCreateMessage")));
                        }
                        //Open the armor stand menu
                        plugin.openArmorStandCreateMenu(player);
                    }
                    break;
                case BARRIER:
                    if (e.getCurrentItem().hasItemMeta()) {
                        if (plugin.getConfig().getBoolean("ShowCloseOpenMenuMessage")) {
                            player.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("ClosingMainMenu")));
                        }
                        player.closeInventory();
                    }
                    break;
            }
        } else if (e.getView().getTitle().equalsIgnoreCase(ChatColor.translateAlternateColorCodes('&', CreateMenuName))) {
            e.setCancelled(true);
            if (e.getCurrentItem() == null) {
                return;
            }
            //Create ArmorStand
            if (!plugin.armorStands.containsKey(player)) {
                ArmorStand stand = (ArmorStand) player.getWorld().spawnEntity(player.getLocation(), EntityType.ARMOR_STAND);
                stand.setVisible(false);
                stand.setInvulnerable(true);
                plugin.armorStands.put(player, stand);
            }
            switch (e.getCurrentItem().getType()) {
                case ARMOR_STAND:
                    if (e.getCurrentItem().hasItemMeta()) {
                        plugin.openConfirmMenu(player, Material.ARMOR_STAND);
                    }
                    break;
                case BEACON:
                    if (e.getCurrentItem().hasItemMeta()) {
                        plugin.openConfirmMenu(player, Material.BEACON);
                    }
                    break;
                case SMOOTH_STONE_SLAB:
                    if (e.getCurrentItem().hasItemMeta()) {
                        plugin.openConfirmMenu(player, Material.SMOOTH_STONE_SLAB);
                    }
                    break;
                case DIAMOND_CHESTPLATE:
                    if (e.getCurrentItem().hasItemMeta()) {
                        plugin.openArmorMenu(player);
                    }
                    break;
                case TOTEM_OF_UNDYING:
                    if (e.getCurrentItem().hasItemMeta()) {
                        plugin.openConfirmMenu(player, Material.TOTEM_OF_UNDYING);
                    }
                    break;
                case RED_WOOL:
                    if (e.getCurrentItem().hasItemMeta()) {
                        if (plugin.getConfig().getBoolean("ShowCloseOpenMenuMessage")) {
                            player.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("ClosingCreateMenu")));
                            if (plugin.armorStands.containsKey(player)) {
                                ArmorStand stand = plugin.armorStands.get(player);
                                stand.remove();
                                plugin.armorStands.remove(player);
                            }
                        }
                        player.closeInventory();
                    }
                    break;
                case GREEN_WOOL:
                    if (e.getCurrentItem().hasItemMeta()) {
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("ArmorStandCreated")));
                        if (plugin.armorStands.containsKey(player)) {
                            ArmorStand stand = plugin.armorStands.get(player);
                            stand.setVisible(true);
                            stand.setInvulnerable(plugin.isImmortal);
                            plugin.armorStands.remove(player);
                        }
                        player.closeInventory();
                    }
                    break;
            }
        } else if (e.getView().getTitle().equalsIgnoreCase(ChatColor.translateAlternateColorCodes('&', ConfirmMenuName))) {
            e.setCancelled(true);
            if (e.getCurrentItem() == null) {
                return;
            }
            if (e.getView().getTopInventory().contains(Material.ARMOR_STAND)) {
                switch (e.getCurrentItem().getType()) {
                    case GREEN_WOOL:
                        if (e.getCurrentItem().hasItemMeta()) {
                            if (plugin.armorStands.containsKey(player)) {
                                ArmorStand stand = plugin.armorStands.get(player);
                                stand.setArms(true);
                            }
                            player.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("OptionConfirmed")));
                            plugin.openArmorStandCreateMenu(player);
                        }
                        break;
                    case RED_WOOL:
                        if (e.getCurrentItem().hasItemMeta()) {
                            if (plugin.armorStands.containsKey(player)) {
                                ArmorStand stand = plugin.armorStands.get(player);
                                stand.setArms(false);
                            }
                            player.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("OptionCanceld")));
                            plugin.openArmorStandCreateMenu(player);
                        }
                }
            }else if (e.getView().getTopInventory().contains(Material.BEACON)) {
                switch (e.getCurrentItem().getType()) {
                    case GREEN_WOOL:
                        if (e.getCurrentItem().hasItemMeta()) {
                            if (plugin.armorStands.containsKey(player)) {
                                ArmorStand stand = plugin.armorStands.get(player);
                                stand.setGlowing(true);
                            }
                            player.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("OptionConfirmed")));
                            plugin.openArmorStandCreateMenu(player);
                        }
                        break;
                    case RED_WOOL:
                        if (e.getCurrentItem().hasItemMeta()) {
                            if (plugin.armorStands.containsKey(player)) {
                                ArmorStand stand = plugin.armorStands.get(player);
                                stand.setGlowing(true);
                            }
                            player.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("OptionCanceld")));
                            plugin.openArmorStandCreateMenu(player);
                        }
                }
            }else if (e.getView().getTopInventory().contains(Material.SMOOTH_STONE_SLAB)) {
                switch (e.getCurrentItem().getType()) {
                    case GREEN_WOOL:
                        if (e.getCurrentItem().hasItemMeta()) {
                            if (plugin.armorStands.containsKey(player)) {
                                ArmorStand stand = plugin.armorStands.get(player);
                                stand.setBasePlate(true);
                            }
                            player.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("OptionConfirmed")));
                            plugin.openArmorStandCreateMenu(player);
                        }
                        break;
                    case RED_WOOL:
                        if (e.getCurrentItem().hasItemMeta()) {
                            if (plugin.armorStands.containsKey(player)) {
                                ArmorStand stand = plugin.armorStands.get(player);
                                stand.setBasePlate(false);
                            }
                            player.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("OptionCanceld")));
                            plugin.openArmorStandCreateMenu(player);
                        }
                        break;
                }
            }else if (e.getView().getTopInventory().contains(Material.TOTEM_OF_UNDYING)) {
                switch (e.getCurrentItem().getType()) {
                    case GREEN_WOOL:
                        if (e.getCurrentItem().hasItemMeta()) {
                            if (plugin.armorStands.containsKey(player)) {
                                plugin.isImmortal = true;
                            }
                            player.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("OptionConfirmed")));
                            plugin.openArmorStandCreateMenu(player);
                        }
                        break;
                    case RED_WOOL:
                        if (e.getCurrentItem().hasItemMeta()) {
                            if (plugin.armorStands.containsKey(player)) {
                                plugin.isImmortal = false;
                            }
                            player.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("OptionCanceld")));
                            plugin.openArmorStandCreateMenu(player);
                        }
                        break;
                }
            }
        } else if (e.getView().getTitle().equalsIgnoreCase(ChatColor.translateAlternateColorCodes('&', ArmorMenuName))) {
            e.setCancelled(true);
            if (e.getCurrentItem() == null) {
                return;
            }
            ItemStack head = new ItemStack(Material.DIAMOND_HELMET);
            ItemStack body = new ItemStack(Material.DIAMOND_CHESTPLATE);
            ItemStack legs = new ItemStack(Material.DIAMOND_LEGGINGS);
            ItemStack boots = new ItemStack(Material.DIAMOND_BOOTS);
            ItemStack elytra = new ItemStack(Material.ELYTRA);
            elytra.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 10);
            head.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 10);
            body.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 10);
            legs.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 10);
            boots.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 10);
            ArmorStand stand = plugin.armorStands.get(player);
            switch (e.getCurrentItem().getType()) {
                case DIAMOND_HELMET:
                    if (e.getCurrentItem().hasItemMeta()) {
                        if (stand.getHelmet().getType() == Material.DIAMOND_HELMET) {
                            stand.setHelmet(null);
                            player.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("HelmetRemoved")));
                        } else {
                            stand.setHelmet(head);
                            player.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("HelmetAdded")));
                        }
                    }
                    break;
                case DIAMOND_CHESTPLATE:
                    if (e.getCurrentItem().hasItemMeta()) {
                        if (stand.getChestplate().getType() == Material.DIAMOND_CHESTPLATE) {
                            stand.setChestplate(null);
                            player.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("ChestplateRemoved")));
                        } else {
                            stand.setChestplate(body);
                            player.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("ChestplateAdded")));
                        }
                    }
                    break;
                case DIAMOND_LEGGINGS:
                    if (e.getCurrentItem().hasItemMeta()) {
                        if (stand.getLeggings().getType() == Material.DIAMOND_LEGGINGS) {
                            stand.setLeggings(null);
                            player.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("LeggingsRemoved")));
                        } else {
                            stand.setLeggings(legs);
                            player.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("LeggingsAdded")));
                        }
                    }
                    break;
                case DIAMOND_BOOTS:
                    if (e.getCurrentItem().hasItemMeta()) {
                        if (stand.getBoots().getType() == Material.DIAMOND_BOOTS) {
                            stand.setBoots(null);
                            player.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("BootsRemoved")));
                        }else {
                            stand.setBoots(boots);
                            player.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("BootsAdded")));
                        }
                    }
                    break;
                case ELYTRA:
                    if (e.getCurrentItem().hasItemMeta()) {
                        if (stand.getChestplate().getType() == Material.ELYTRA) {
                            stand.setChestplate(null);
                            player.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("ElytraRemoved")));
                        }else {
                            stand.setChestplate(elytra);
                            player.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("ElytraAdded")));
                        }
                    }
                case GREEN_WOOL:
                    if (e.getCurrentItem().hasItemMeta()) {
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("OptionConfirmed")));
                        plugin.openArmorStandCreateMenu(player);
                    }
                    break;
            }
        }
    }
}