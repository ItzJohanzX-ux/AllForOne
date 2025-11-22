/*
 * AdvancedAnticheat – MIT License
 * Copyright (c) 2025 Johan
 */

package johan.anticheat.command;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import java.util.*;

public class AnticheatCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player p)) return true;
        if(!p.hasPermission("anticheat.staff")) return true;
        Inventory gui=Bukkit.createInventory(null,27,"§cAdvancedAC §8- §fStaff");
        gui.setItem(10,createItem("§eFlight checks","Toggle flight alerts"));
        p.openInventory(gui); return true;
    }
    private ItemStack createItem(String name,String lore){
        ItemStack item=new ItemStack(org.bukkit.Material.PAPER);
        ItemMeta meta=item.getItemMeta(); meta.setDisplayName(name); meta.setLore(List.of("§7"+lore)); item.setItemMeta(meta); return item;
    }
}

