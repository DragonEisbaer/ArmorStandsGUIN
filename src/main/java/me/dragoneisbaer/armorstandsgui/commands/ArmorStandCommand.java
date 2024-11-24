package me.dragoneisbaer.armorstandsgui.commands;

import me.dragoneisbaer.armorstandsgui.ArmorStandsGUI;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


public class ArmorStandCommand implements CommandExecutor {

    private final ArmorStandsGUI plugin;

    public ArmorStandCommand(ArmorStandsGUI plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (player.hasPermission("armorstandgui.gui")) {
                plugin.openMainMenu(player);
            }
        }else {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("NotAPlayer")));
        }
        return true;
    }
}