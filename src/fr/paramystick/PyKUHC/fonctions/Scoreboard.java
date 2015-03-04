

package fr.paramystick.PyKUHC.fonctions;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.*;

import fr.paramystick.PyKUHC.PyKUHC;

public class Scoreboard implements Runnable {
	
	ScoreboardManager manager = Bukkit.getScoreboardManager();
	org.bukkit.scoreboard.Scoreboard affichage = manager.getNewScoreboard();
	Objective objective = affichage.registerNewObjective("ParamYsticK UHC", "dummy");
	Score nbJoueur = null;
	Score nbEquipe = null;
	Score blank1 = null;
	Score blank2 = null;
	int joueurParEquipe = 2;
	
	public static int affLoop;
	
	int maxJoueur = Bukkit.getMaxPlayers();
	
	@SuppressWarnings("unused")
	private PyKUHC pluginPyKUHC;
	
	boolean equipe = false;
	public Scoreboard(PyKUHC pluginPyKUHC) {
		this.pluginPyKUHC = pluginPyKUHC;
	}
	public void run() {
		objective.setDisplaySlot(DisplaySlot.SIDEBAR);
		
		for (int i = 0;i <=  maxJoueur;i++) {
			affichage.resetScores(ChatColor.GREEN + "Joueur : " + ChatColor.GOLD + i + "/" + maxJoueur);
			affichage.resetScores(ChatColor.GREEN + "Equipe : " + ChatColor.GOLD + i/joueurParEquipe + "/" + maxJoueur/joueurParEquipe);
		}
		
		
		int i = 0;
		int k = 0;
		
		blank1 = objective.getScore("");
		blank1.setScore(-1);
		blank2 = objective.getScore("");
		blank2.setScore(-3);
		
		for (Player player : Bukkit.getOnlinePlayers()) {
			i = i + 1;
		}
		if (equipe) {
			if (i % joueurParEquipe == 0){
				k = i / joueurParEquipe;
			}
			else {
				k = (i / joueurParEquipe) + 1;
			}
		}
		nbJoueur = objective.getScore(ChatColor.GREEN + "Joueur : " + ChatColor.GOLD + i + "/" + maxJoueur);
		nbJoueur.setScore(-2);
		nbEquipe = objective.getScore(ChatColor.GREEN + "Equipe : " + ChatColor.GOLD + k + "/" + maxJoueur/2);
		nbEquipe.setScore(-4);
		for(Player online : Bukkit.getOnlinePlayers()){
			online.setScoreboard(affichage);
		}
	}
	
}
