package fr.paramystivk.PyKUHC.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Counter {

	public void onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		
		Player player = (Player) sender;
		if (commandLabel.equalsIgnoreCase("timer")) {
			if (args.length == 1) {
				int time = 0;
				try {
					time = Integer.parseInt(args[1]);
				}
				catch(NumberFormatException e) {
					player.sendMessage(ChatColor.DARK_RED + args[1] + " is not a valid variable");
				}
				if (time > 0) {
					player.sendMessage("timer of "+time+" activated");
				}
			}
			else {
				player.sendMessage(ChatColor.DARK_RED + "usage : /timer <second(s)>");
			}
		}
	}
}
