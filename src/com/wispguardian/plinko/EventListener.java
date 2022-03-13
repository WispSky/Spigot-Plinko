package com.wispguardian.plinko;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

public class EventListener implements Listener {

	@EventHandler
	public void onPlace(BlockPlaceEvent event) {
		Player player = event.getPlayer();
		if(Main.isPlayerActive(player)) {
			Block placed = event.getBlock();
			Block against = event.getBlockAgainst();
			
			new PlinkoBlock(placed, against);
		}
	}

}
