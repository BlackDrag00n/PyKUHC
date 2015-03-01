package fr.paramystick.PyKUHC.commandes;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.paramystick.PyKUHC.PyKUHC;

public class CommandeTimer implements CommandExecutor {
	private PyKUHC pluginPyKUHC; // On crée la variable qui va etre utiliser dans cette classe seulement (PRIVATE)
	
	public CommandeTimer(PyKUHC pluginPyKUHC) { // On crée le constructor
		this.pluginPyKUHC = pluginPyKUHC;
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		
		Player player = (Player) sender;
		if (commandLabel.equalsIgnoreCase("timer")) {
			if (args.length == 1) {
				try {
					pluginPyKUHC.time = Integer.parseInt(args[0]);
					
					if (pluginPyKUHC.time > 0) {
						player.sendMessage("timer of "+pluginPyKUHC.time+" activated");
					}
					
					return true;
				}
				catch(NumberFormatException e) {
					player.sendMessage(ChatColor.AQUA + "[" + pluginPyKUHC.nomPlugin + "] " + ChatColor.GOLD + args[0] + ChatColor.DARK_RED + " n'est pas un nombre valide !");
				}
			}
		}
		return false;
	}
}
