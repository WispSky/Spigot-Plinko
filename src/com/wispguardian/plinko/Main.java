package com.wispguardian.plinko;

import java.util.ArrayList;

import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

	public static Main instance;
	
	public static ArrayList<Player> players = new ArrayList<Player>();
	
	@Override
	public void onEnable() {
		Main.instance = this;

//		File f = new File(getDataFolder() + "/");
//		if(!f.exists()) f.mkdir();

		getServer().getPluginManager().registerEvents(new EventListener(), this);

		// commands
		this.getCommand("plinko").setExecutor(new PlinkoCommand());
	}

	@Override
	public void onDisable() {

	}
	
	public static void addPlayer(Player player) {
		Main.players.add(player);
	}
	
	public static void removePlayer(Player player) {
		Main.players.remove(player);
	}
	
	public static boolean isPlayerActive(Player player) {
		return Main.players.contains(player);
	}

}
