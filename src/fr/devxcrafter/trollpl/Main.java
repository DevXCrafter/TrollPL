package fr.devxcrafter.trollpl;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import fr.devxcrafter.trollpl.commands.Commands;
import fr.devxcrafter.trollpl.listener.InventoryListener;

public class Main extends JavaPlugin {
	
	public static Main instance;
	
	@Override
	public void onEnable() {
		
		
		Bukkit.getPluginManager().registerEvents(new InventoryListener(), this);
		getCommand("troll").setExecutor(new Commands());
	}

	public static Main getInstance() {
		return instance;
	}
	
	

}
