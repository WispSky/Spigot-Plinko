package com.wispguardian.plinko;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.md_5.bungee.api.ChatColor;

public class PlinkoCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

		if(sender instanceof Player) {
			Player p = (Player)sender;
			if(!Main.isPlayerActive(p)) {
				Main.addPlayer(p);
				p.sendMessage(ChatColor.AQUA + "" + ChatColor.BOLD + 
						"Plinko mode activated!");
			}else {
				Main.removePlayer(p);
				p.sendMessage(ChatColor.AQUA + "" + ChatColor.BOLD + 
						"Plinko mode disabled!");
			}
		}

		return true;
	}

}
