package com.wispguardian.plinko;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;

public class PlinkoBlock {

	private Block block;
	private Material mat;
	private boolean axisX;
	
	public PlinkoBlock(Block block, Block against) {
		this.block = block;
		this.mat = block.getType();
		
		if(Math.abs(against.getLocation().getBlockX() - block.getLocation().getBlockX()) == 0) {
			axisX = true;
		}else axisX = false;
		
		nextStep();
	}
	
	private void nextStep() {
		if(isInCorner()) return;
		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Main.instance, new Runnable(){
			@Override
			public void run(){
				if(!isColliding()) moveDown();
				else moveRandom();
				nextStep();
			}
		}, 1*10L);
	}
	
	// move block methods
	private void moveDown() {
		block.setType(Material.AIR);
		block = getUnder();
		block.setType(mat);
	}
	
	private void moveLeft() {
		block.setType(Material.AIR);
		block = getLeft();
		block.setType(mat);
	}
	
	private void moveRight() {
		block.setType(Material.AIR);
		block = getRight();
		block.setType(mat);
	}
	
	private void moveRandom() {
		int rand = (int)Math.floor(Math.random()*2);
		if(rand == 0) moveLeft();
		else moveRight();
	}
	
	// Utility methods
	private boolean isColliding() {
		return getUnder().getType() != Material.AIR;
	}
	
	private boolean isInCorner() {
		return isColliding() && getLeft().getType() != Material.AIR && getRight().getType() != Material.AIR;
	}
	
	private Block getUnder() {
		Location newLoc = block.getLocation();
		newLoc.add(0, -1, 0);
		return newLoc.getBlock();
	}
	
	private Block getLeft() {
		Location newLoc = block.getLocation();
		if(axisX) newLoc.add(-1, 0, 0);
		else newLoc.add(0, 0, -1);
		return newLoc.getBlock();
	}
	
	private Block getRight() {
		Location newLoc = block.getLocation();
		if(axisX) newLoc.add(1, 0, 0);
		else newLoc.add(0, 0, 1);
		return newLoc.getBlock();
	}
	
}
