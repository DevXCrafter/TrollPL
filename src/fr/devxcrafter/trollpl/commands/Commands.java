package fr.devxcrafter.trollpl.commands;
import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Commands implements CommandExecutor {

	public static HashMap<Player, Player> troll = new HashMap<>();
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		
		
		Player p = (Player) sender;
		
		if(cmd.getName().equalsIgnoreCase("troll")) {
			if(args.length == 0) {
				p.sendMessage("§b[§6TROLL§b] §7Please enter the victim's name !");
			
			}
			
			if(args.length ==  1) {
				Player target = Bukkit.getPlayer(args[0]);
				if(target == null) {
					
					return true;
				}
				
				Inventory inv = p.getServer().createInventory(null, InventoryType.HOPPER, "§cAdminGUI | §9Troll");
				
				@SuppressWarnings("deprecation")
				ItemStack control = createItem(Material.getMaterial(420), "§6Player Control");
				ItemStack crash = createItem(Material.TNT, "§eCrash Player");
				ItemStack freeze = createItem(Material.ICE, "§aFreeze Player");
				ItemStack exit = createItem(Material.BARRIER, "§cExit");
				ItemStack catapult = createItem(Material.FEATHER, "§dCatapult Player");
				
				inv.setItem(0, control);
				inv.setItem(1, crash);
				inv.setItem(2, freeze);
				inv.setItem(3, catapult);
				inv.setItem(4, exit);
				
				p.openInventory(inv);
				troll.put(p, target);
			}
		}
		
		
		return false;
	}
	
	private static ItemStack createItem(Material mat, String name) {
		ItemStack item = new ItemStack(mat);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(name);
		item.setItemMeta(meta);
		return item;
	}

}
