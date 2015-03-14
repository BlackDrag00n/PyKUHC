package fr.paramystick.PyKUHC.commandes;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.paramystick.PyKUHC.PyKUHC;
import fr.paramystick.PyKUHC.fonctions.Scoreboard;

public class CommandeTeam implements CommandExecutor{
	public PyKUHC pluginPyKUHC; // On crée la variable qui va etre utiliser dans cette classe seulement (PRIVATE)
	
	public CommandeTeam(PyKUHC pluginPyKUHC) { // On crée le constructor
		this.pluginPyKUHC = pluginPyKUHC;
	}
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		Player player = (Player) sender;
		if (commandLabel.equalsIgnoreCase("equipe")) {
			if (args.length >= 1) {
				if (args[0].equalsIgnoreCase("active")) {
					Scoreboard.equipe = true;
					player.sendMessage(ChatColor.AQUA + "[" + pluginPyKUHC.nomPlugin + "] " + ChatColor.YELLOW + "[Equipe] " + ChatColor.GREEN + "equipe active");
					return true;
				}
				else if (args[0].equalsIgnoreCase("desactive")) {
					Scoreboard.equipe = false;
					player.sendMessage(ChatColor.AQUA + "[" + pluginPyKUHC.nomPlugin + "] " + ChatColor.YELLOW + "[Equipe] " + ChatColor.GREEN + "equipe inactive");
					return true;
				}
				else if (args[0].equalsIgnoreCase("set")) {
					try {
						Scoreboard.joueurParEquipe = (Integer.parseInt(args[1]));// je définie la variable time lorsque l'argument est un nombre
						player.sendMessage(ChatColor.AQUA + "[" + pluginPyKUHC.nomPlugin + "] " + ChatColor.YELLOW + "[Equipe] " + ChatColor.GREEN + "Les equipes conteront maintenant " + ChatColor.GOLD + args[1] + ChatColor.GREEN + " joueurs dans leurs rangs");
						return true;
					}
					catch(NumberFormatException e) {
						player.sendMessage(ChatColor.AQUA + "[" + pluginPyKUHC.nomPlugin + "] " + ChatColor.GOLD + args[1] + ChatColor.DARK_RED + " n'est pas une valeur valide !"); // message d'érreur lorsque l'argument n'est pas correct
					}
				}
				else {
					return false ;
				}
			}
		}
		return false;
	}
}
