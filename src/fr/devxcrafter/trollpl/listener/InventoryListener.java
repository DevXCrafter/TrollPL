package fr.devxcrafter.trollpl.listener;

import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.util.Vector;

import fr.devxcrafter.trollpl.Main;
import fr.devxcrafter.trollpl.commands.Commands;

public class InventoryListener implements Listener {
	
	public static HashMap<Player, Player> control = new HashMap<>();
	public static HashMap<Player, Player> isControl = new HashMap<>();
	
	public static ArrayList<Player> nomove = new ArrayList<>();
	
	
	@EventHandler
	public void onClick(InventoryClickEvent e) {
		Player p = (Player) e.getWhoClicked();
		
		if(e.getInventory().getName().equalsIgnoreCase("§cAdminGUI | §9Troll")) {
			e.setCancelled(true);
			if(e.getSlot() == 0) {
				Player target = Commands.troll.get(p);
				control.put(p, target);
				isControl.put(target, p);
				p.closeInventory();
			}
			
			if(e.getSlot() == 1) {
				p.setHealthScale(Integer.MAX_VALUE);
				p.closeInventory();
			}
			
			if(e.getSlot() == 2) {
				nomove.add(p);
				p.closeInventory();
			}
			
			if(e.getSlot() == 3) {
				Player target = Commands.troll.get(p);
				Vector v = p.getLocation().getDirection().multiply(2D);
				target.setVelocity(v);
				p.closeInventory();
			}
			
			if(e.getSlot() == 4) {
				p.closeInventory();
			}
		}
	}
	
	@EventHandler
	public void onJoin(PlayerJoinEvent e){
		Player p =e.getPlayer();
		p.setHealthScale(20);
	}
	
	public static HashMap<Player, Location> move = new HashMap<>();
	
	@EventHandler
	public void onMove(PlayerMoveEvent e) {
		Player p = e.getPlayer();
		
		if(control.containsKey(p)) {
			Player target = control.get(p);
			target.teleport(p);
		}
		
		if(!move.containsKey(p)) {
			move.put(p, p.getLocation());
			Bukkit.getScheduler().runTaskLater(Main.getInstance(), new Runnable() {
				
				@Override
				public void run() {
					p.teleport(move.get(p));
					move.remove(p);
				}
			}, 20*2);
		}
		
		if(nomove.contains(p)) {
			if(e.getFrom().getX() != e.getTo().getX() || e.getFrom().getZ() != e.getTo().getZ()) {
				e.setCancelled(true);
			}
		}
	}
	
	
	

}
