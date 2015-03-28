package fr.paramystick.PyKUHC.commandes;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.paramystick.PyKUHC.PyKUHC;
import fr.paramystick.PyKUHC.fonctions.Scoreboard;
import fr.paramystick.PyKUHC.fonctions.Lancement;

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
					Lancement.initialiseTeam();
					return true;
				}
				else if (args[0].equalsIgnoreCase("desactive")) {
					Scoreboard.equipe = false;
					player.sendMessage(ChatColor.AQUA + "[" + pluginPyKUHC.nomPlugin + "] " + ChatColor.YELLOW + "[Equipe] " + ChatColor.GREEN + "equipe inactive");
					Lancement.suppressTeam();
					return true;
				}
				else if (args[0].equalsIgnoreCase("set")) {
					try {
						Scoreboard.joueurParEquipe = (Integer.parseInt(args[1]));// je définie la variable time lorsque l'argument est un nombre
						player.sendMessage(ChatColor.AQUA + "[" + pluginPyKUHC.nomPlugin + "] " + ChatColor.YELLOW + "[Equipe] " + ChatColor.GREEN + "Les equipes conteront maintenant " + ChatColor.GOLD + args[1] + ChatColor.GREEN + " joueurs dans leurs rangs");
						return true;
					}
					catch(NumberFormatException e) {
						player.sendMessage(ChatColor.AQUA + "[" + pluginPyKUHC.nomPlugin + "] " + ChatColor.YELLOW + "[Equipe] " + ChatColor.GOLD + args[1] + ChatColor.DARK_RED + " n'est pas une valeur valide !"); // message d'érreur lorsque l'argument n'est pas correct
					}
				}
				else if ((args[0].equalsIgnoreCase("join")) || (args[0].equalsIgnoreCase("quit")) || (args[0].equalsIgnoreCase("list"))) {
					if (Scoreboard.equipe == true) {
						if (args[0].equalsIgnoreCase("join")) {
							if (args[1] != null ) {
								boolean acheve = false ;
								boolean TeamTrouve = false;
								int TeamNb = 0;
								for (int k = 0; k < 15 ; k++) {
									if (Lancement.TeamDispo[k] != null) {
										if (args[1].equalsIgnoreCase(Lancement.TeamDispo[k])) {
											TeamNb = k;
											TeamTrouve = true;
											break;
										}
									}
								}
								
								if (TeamTrouve == false ) {
									player.sendMessage(ChatColor.AQUA + "[" + pluginPyKUHC.nomPlugin + "] " + ChatColor.YELLOW + "[Equipe] "+ ChatColor.DARK_RED +"L'équipe "+ ChatColor.GOLD + args[1] + ChatColor.DARK_RED + " n'as pas été trouvé");
									player.sendMessage(ChatColor.AQUA + "[" + pluginPyKUHC.nomPlugin + "] " + ChatColor.YELLOW + "[Equipe] "+ ChatColor.DARK_RED +"Executé "+ ChatColor.GOLD + "/equipe list" + ChatColor.DARK_RED + " pour avoir une liste des équipe disponible");
									return true;
								}
								
								int PlayerInTeam = Lancement.NbJoueurInEquipe[TeamNb];
								if (PlayerInTeam < Scoreboard.joueurParEquipe) {
									for (int k = 0 ; k <15 ; k++) {
										if (Lancement.TeamDispo[k] != null) {
											boolean Hp = Lancement.LsTeam[k].hasPlayer(player);
											if (Hp == true) {
												Lancement.LsTeam[k].removePlayer(player);
												break;
											}	
										}
									}
									Lancement.LsTeam[TeamNb].addPlayer(player);
									player.sendMessage(ChatColor.AQUA + "[" + pluginPyKUHC.nomPlugin + "] " + ChatColor.YELLOW + "[Equipe] " + ChatColor.GREEN + "Vous avez été intégré a l'équipe " + ChatColor.GOLD + Lancement.TeamDispo[TeamNb]);
									Lancement.NbJoueurInEquipe[TeamNb] += 1;
									acheve = true;
								}
								else {
									player.sendMessage(ChatColor.AQUA + "[" + pluginPyKUHC.nomPlugin + "] " + ChatColor.YELLOW + "[Equipe] "+ ChatColor.DARK_RED +"L'équipe "+ ChatColor.GOLD + args[1] + ChatColor.DARK_RED + " est déjà complète");
								}
								
								return acheve;
							}
						}
						else if (args[0].equalsIgnoreCase("quit")) {
							boolean TeamTrouvé = false;
							for (int k = 0 ; k <15 ; k++) {
								if (Lancement.TeamDispo[k] != null) {
									boolean Hp = Lancement.LsTeam[k].hasPlayer(player);
									if (Hp == true) {
										Lancement.LsTeam[k].removePlayer(player);
										TeamTrouvé = true;
										break;
									}	
								}
							}
							if (TeamTrouvé == false) {
								player.sendMessage(ChatColor.AQUA + "[" + pluginPyKUHC.nomPlugin + "] " + ChatColor.YELLOW + "[Equipe] "+ ChatColor.DARK_RED + "Vous ne faite partie d'aucune équipe");
							}
							return true;
						}
						else if (args[0].equalsIgnoreCase("list")) {
							player.sendMessage(ChatColor.AQUA + "[" + pluginPyKUHC.nomPlugin + "] " + ChatColor.YELLOW + "[Equipe] " + ChatColor.GREEN + "liste des équipes active :" );
							for (int k = 0 ; k < 15; k++) {
								if (Lancement.TeamDispo[k] != null ) {
									player.sendMessage(ChatColor.GREEN + " - " + Lancement.TeamDispo[k] + "          " + ChatColor.GOLD + Lancement.NbJoueurInEquipe[k] + "/" + Scoreboard.joueurParEquipe);
								}
							}
							return true;
						}
					}
					else {
						player.sendMessage(ChatColor.AQUA + "[" + pluginPyKUHC.nomPlugin + "] " + ChatColor.YELLOW + "[Equipe] " + ChatColor.RED + "Les equipes sont inactive");
						return true;
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
