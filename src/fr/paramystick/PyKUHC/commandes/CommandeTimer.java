package fr.paramystick.PyKUHC.commandes;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.paramystick.PyKUHC.fonctions.Boucle;
import fr.paramystick.PyKUHC.PyKUHC;

public class CommandeTimer implements CommandExecutor {
	public PyKUHC pluginPyKUHC; // On cr�e la variable qui va etre utiliser dans cette classe seulement (PRIVATE)
	
	public CommandeTimer(PyKUHC pluginPyKUHC) { // On cr�e le constructor
		this.pluginPyKUHC = pluginPyKUHC;
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		
		Player player = (Player) sender;
		if (commandLabel.equalsIgnoreCase("timer")) {
			if (args.length == 1) {
				try {
					PyKUHC.setTime(Integer.parseInt(args[0])+1);// je d�finie la variable time lorsque l'argument est un nombre
					player.sendMessage(ChatColor.AQUA + "[" + pluginPyKUHC.nomPlugin + "] " + ChatColor.YELLOW + "[Timer]"+ ChatColor.GREEN + "Timer initialis� a " + ChatColor.GOLD + args[0]);
					return true;
				}
				catch(NumberFormatException e) {
					if (args[0].equalsIgnoreCase("stop")) {
						pluginPyKUHC.getServer().getScheduler().cancelTask(PyKUHC.loop); // je stop la boucle timer lorsque l'argument vos "stop"
						player.sendMessage(ChatColor.AQUA + "[" + pluginPyKUHC.nomPlugin + "] " + ChatColor.YELLOW + "[Timer]"+ ChatColor.GREEN + "Timer stop�");
						return true;
					}
					else if ( args[0].equalsIgnoreCase("start")) {
						PyKUHC.loop = pluginPyKUHC.getServer().getScheduler().scheduleSyncRepeatingTask(pluginPyKUHC,new Boucle(), 25, 25); // je d�marre  la boucle timer lorsque l'argument vos "start"
						player.sendMessage(ChatColor.AQUA + "[" + pluginPyKUHC.nomPlugin + "] " + ChatColor.YELLOW + "[Timer]"+ ChatColor.GREEN + "Timer d�marr�");
						return true;
					}
					else {
						player.sendMessage(ChatColor.AQUA + "[" + pluginPyKUHC.nomPlugin + "] " + ChatColor.GOLD + args[0] + ChatColor.DARK_RED + " n'est pas une valeur valide !"); // message d'�rreur lorsque l'argument n'est pas correct
					}
				}
			}
		}
		return false;
	}
}
