package me.dragoneisbaer.armorstandsgui;

import me.dragoneisbaer.armorstandsgui.commands.ArmorStandCommand;
import me.dragoneisbaer.armorstandsgui.events.MenuHandler;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.HashMap;

public final class ArmorStandsGUI extends JavaPlugin {

    public HashMap<Player, ArmorStand> armorStands = new HashMap<>();
    public boolean isImmortal = false;


    @Override
    public void onEnable() {
        // Plugin startup logic
        System.out.println(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("StartUp")));
        getCommand("agui").setExecutor(new ArmorStandCommand(this));
        getServer().getPluginManager().registerEvents(new MenuHandler(this), this);
        getConfig().options().copyDefaults();
        saveDefaultConfig();

    }
        public void openMainMenu(Player player){
            //Main Menu
            String main_menu_name = this.getConfig().getString("MainMenuName");
            Inventory main_menu = Bukkit.createInventory(player, 9, ChatColor.translateAlternateColorCodes('&', main_menu_name));
            //Icon "Create"
            ItemStack create = new ItemStack(Material.ARMOR_STAND);
            ItemMeta create_meta = create.getItemMeta();
            create_meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("CreateIconName")));
            ArrayList<String> create_lore = new ArrayList<>();
            create_lore.add(this.getConfig().getString("CreateLore"));
            create_meta.setLore(create_lore);
            create.setItemMeta(create_meta);
            //Icon "Close"
            ItemStack close = new ItemStack(Material.BARRIER);
            ItemMeta close_meta = close.getItemMeta();
            close_meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("CloseIconName")));
            close.setItemMeta(close_meta);
            main_menu.setItem(0, create);
            main_menu.setItem(8, close);
            player.openInventory(main_menu);
        }
        public void openArmorStandCreateMenu(Player player){
        String createmenuname = this.getConfig().getString("CreateMenuName");
        Inventory create_menu = Bukkit.createInventory(player, 9, ChatColor.translateAlternateColorCodes('&', createmenuname));

        //Immortal
        ItemStack immortal = new ItemStack(Material.TOTEM_OF_UNDYING);
        ItemMeta immortal_meta = immortal.getItemMeta();
        immortal_meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("ImmortalIconName")));
        ArrayList<String> immortal_lore = new ArrayList<>();
        immortal_lore.add(this.getConfig().getString("ImmortalLore"));
        immortal_meta.setLore(immortal_lore);
        //Glow
        ItemStack glow = new ItemStack(Material.BEACON);
        ItemMeta glow_meta = glow.getItemMeta();
        glow_meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("GlowIconName")));
        ArrayList<String> glow_lore = new ArrayList<>();
        glow_lore.add(this.getConfig().getString("GlowLore"));
        glow_meta.setLore(glow_lore);
        //Arms
        ItemStack arms = new ItemStack(Material.ARMOR_STAND);
        ItemMeta arms_meta = arms.getItemMeta();
        arms_meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("ArmsIconName")));
        ArrayList<String> arms_lore = new ArrayList<>();
        arms_lore.add(this.getConfig().getString("ArmsLore"));
        arms_meta.setLore(arms_lore);
        //Armor
        ItemStack armor = new ItemStack(Material.DIAMOND_CHESTPLATE);
        ItemMeta armor_meta = armor.getItemMeta();
        armor_meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("ArmorIconName")));
        ArrayList<String> armor_lore = new ArrayList<>();
        armor_lore.add(this.getConfig().getString("ArmorLore"));
        armor_meta.setLore(armor_lore);
        //Armorplate
        ItemStack armorplate = new ItemStack(Material.SMOOTH_STONE_SLAB);
        ItemMeta armorplate_meta = armorplate.getItemMeta();
        armorplate_meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("ArmorplateIconName")));
        ArrayList<String> armorplate_lore = new ArrayList<>();
        armorplate_lore.add(this.getConfig().getString("ArmorplateLore"));
        armorplate_meta.setLore(armorplate_lore);
        //Complete
        ItemStack complete = new ItemStack(Material.GREEN_WOOL);
        ItemMeta complete_meta = complete.getItemMeta();
        complete_meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("CompleteIconName")));
        //Cancel
        ItemStack cancel = new ItemStack(Material.RED_WOOL);
        ItemMeta cancel_meta = cancel.getItemMeta();
        cancel_meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("CancelIconName")));

        //immortal.setItemMeta(immortal_meta);
        glow.setItemMeta(glow_meta);
        arms.setItemMeta(arms_meta);
        armor.setItemMeta(armor_meta);
        armorplate.setItemMeta(armorplate_meta);
        complete.setItemMeta(complete_meta);
        cancel.setItemMeta(cancel_meta);
        create_menu.setItem(0, glow);
        create_menu.setItem(1, arms);
        create_menu.setItem(2, armor);
        create_menu.setItem(3, armorplate);
        //create_menu.setItem(4, immortal);
        create_menu.setItem(7, complete);
        create_menu.setItem(8, cancel);
        player.openInventory(create_menu);
        }
        public void openConfirmMenu(Player player, Material option) {

        String ConfirmMenuName = ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("ConfirmMenuName"));
        Inventory conirm_menu = Bukkit.createInventory(player, 45, ConfirmMenuName);

        ItemStack option_item = new ItemStack(option);
        ItemMeta option_meta = option_item.getItemMeta();
        if (option == Material.ARMOR_STAND) {
            option_meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("GetArms")));
            option_item.setItemMeta(option_meta);
        }else if (option == Material.BEACON) {
            option_meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("IsGlowing")));
            option_item.setItemMeta(option_meta);
        }else if (option == Material.DIAMOND_CHESTPLATE) {
            option_meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("GiveArmor")));
            option_item.setItemMeta(option_meta);
        }else if (option == Material.SMOOTH_STONE_SLAB) {
            option_meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("GiveBasePlate")));
            option_item.setItemMeta(option_meta);
        }
        else if (option == Material.TOTEM_OF_UNDYING) {
            option_meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("Immortal")));
            option_item.setItemMeta(option_meta);
        }
        ItemStack yes = new ItemStack(Material.GREEN_WOOL);
        ItemMeta yes_meta = yes.getItemMeta();
        yes_meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("YesIconName")));
        yes.setItemMeta(yes_meta);

        ItemStack no = new ItemStack(Material.RED_WOOL);
        ItemMeta no_meta = no.getItemMeta();
        no_meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("NoIconName")));
        no.setItemMeta(no_meta);

        conirm_menu.setItem(29, yes);
        conirm_menu.setItem(33, no);
        conirm_menu.setItem(13, option_item);

        player.openInventory(conirm_menu);
        }
        public void openArmorMenu(Player player) {
            String ArmorMenuName = ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("ArmorMenuName"));
            Inventory armorMenu = Bukkit.createInventory(player, 45, ChatColor.translateAlternateColorCodes('&', ArmorMenuName));
            ItemStack head = new ItemStack(Material.DIAMOND_HELMET);
            ItemStack body = new ItemStack(Material.DIAMOND_CHESTPLATE);
            ItemStack legs = new ItemStack(Material.DIAMOND_LEGGINGS);
            ItemStack boots = new ItemStack(Material.DIAMOND_BOOTS);
            ItemStack elytra = new ItemStack(Material.ELYTRA);

            ItemMeta head_meta = head.getItemMeta();
            head_meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("DiamondHelmetIconName")));
            head.setItemMeta(head_meta);

            ItemMeta body_meta = body.getItemMeta();
            body_meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("DiamondChestplateIconName")));
            body.setItemMeta(body_meta);

            ItemMeta legs_meta = legs.getItemMeta();
            legs_meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("DiamondLeggingsIconName")));
            legs.setItemMeta(legs_meta);

            ItemMeta boots_meta = boots.getItemMeta();
            boots_meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("DiamondBootsIconName")));
            boots.setItemMeta(body_meta);


            ItemStack confirm = new ItemStack(Material.GREEN_WOOL);
            ItemMeta confirm_meta = confirm.getItemMeta();
            confirm_meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("DoneIconName")));
            confirm.setItemMeta(confirm_meta);

            ItemMeta elytra_meta = elytra.getItemMeta();
            elytra_meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("ElytraIconName")));
            elytra.setItemMeta(elytra_meta);

            armorMenu.setItem(40, confirm);
            armorMenu.setItem(11, head);
            armorMenu.setItem(12, body);
            armorMenu.setItem(13, elytra);
            armorMenu.setItem(14, legs);
            armorMenu.setItem(15, boots);
            player.openInventory(armorMenu);
        }
    }