package fr.paramystick.PyKUHC.commandes;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.paramystick.PyKUHC.fonctions.Boucle;
import fr.paramystick.PyKUHC.PyKUHC;

public class CommandeTimer implements CommandExecutor {
	public PyKUHC pluginPyKUHC; // On crée la variable qui va etre utiliser dans cette classe seulement (PRIVATE)
	
	public CommandeTimer(PyKUHC pluginPyKUHC) { // On crée le constructor
		this.pluginPyKUHC = pluginPyKUHC;
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		
		Player player = (Player) sender;
		if (commandLabel.equalsIgnoreCase("timer")) {
			if (args.length == 1) {
				try {
					Boucle.setTime(Integer.parseInt(args[0]));// je définie la variable time lorsque l'argument est un nombre
					player.sendMessage(ChatColor.AQUA + "[" + pluginPyKUHC.nomPlugin + "] " + ChatColor.YELLOW + "[Timer] " + ChatColor.GREEN + "Timer initialisé à " + ChatColor.GOLD + args[0] + ChatColor.GREEN + " secondes");
					return true;
				}
				catch(NumberFormatException e) {
					if (args[0].equalsIgnoreCase("stop")) {
						pluginPyKUHC.getServer().getScheduler().cancelTask(Boucle.loop); // je stop la boucle timer lorsque l'argument vos "stop"
						player.sendMessage(ChatColor.AQUA + "[" + pluginPyKUHC.nomPlugin + "] " + ChatColor.YELLOW + "[Timer] "+ ChatColor.GREEN + "Timer stopé");
						Boucle.setLoopActive(false);
						return true;
					}
					else if ( args[0].equalsIgnoreCase("start")) {
						Boucle.loop = pluginPyKUHC.getServer().getScheduler().scheduleSyncRepeatingTask(pluginPyKUHC,new Boucle(pluginPyKUHC), 25, 25); // je démarre  la boucle timer lorsque l'argument vos "start"
						player.sendMessage(ChatColor.AQUA + "[" + pluginPyKUHC.nomPlugin + "] " + ChatColor.YELLOW + "[Timer] "+ ChatColor.GREEN + "Timer démarré");
						Boucle.setLoopActive(true);
						return true;
					}
					else {
						player.sendMessage(ChatColor.AQUA + "[" + pluginPyKUHC.nomPlugin + "] " + ChatColor.GOLD + args[0] + ChatColor.DARK_RED + " n'est pas une valeur valide !"); // message d'érreur lorsque l'argument n'est pas correct
					}
				}
			}
		}
		return false;
	}
}
