

package fr.paramystick.PyKUHC.fonctions;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.*;

import fr.paramystick.PyKUHC.fonctions.Boucle;
import fr.paramystick.PyKUHC.PyKUHC;

public class Scoreboard implements Runnable {
	
	ScoreboardManager manager = Bukkit.getScoreboardManager();
	org.bukkit.scoreboard.Scoreboard affichage = manager.getNewScoreboard();
	Objective objective = affichage.registerNewObjective("ParamYsticK UHC", "dummy");
	Score nbJoueur = null;
	Score nbEquipe = null;
	Score timer = null;
	Score blank1 = null;
	Score blank2 = null;
	Score blank3 = null;
	String entry1;
	String entry2;
	String entry3;
	int firstTime = 1;
	int joueurParEquipe = 2;
	
	public static int affLoop;
	
	int maxJoueur = Bukkit.getMaxPlayers();
	
	@SuppressWarnings("unused")
	private PyKUHC pluginPyKUHC;
	
	boolean equipe = false;
	public Scoreboard(PyKUHC pluginPyKUHC) {
		this.pluginPyKUHC = pluginPyKUHC;
	}
	@SuppressWarnings("unused")
	public void run() {
		if (firstTime == 0) {
			entry1 = nbJoueur.getEntry();
			entry2 = nbEquipe.getEntry();
			entry3 = timer.getEntry();
			affichage.resetScores(entry1);
			affichage.resetScores(entry2);
			affichage.resetScores(entry3);
		}
		objective.setDisplaySlot(DisplaySlot.SIDEBAR);
		
		int i = 0;
		int k = 0;
		
		blank1 = objective.getScore("");
		blank1.setScore(-1);
		blank2 = objective.getScore("");
		blank2.setScore(-3);
		blank3 = objective.getScore("");
		blank3.setScore(-5);
		
		for (@SuppressWarnings("unused") Player player : Bukkit.getOnlinePlayers()) {
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
		
		if (Boucle.getLoopActive()) {
			timer = objective.getScore(ChatColor.GREEN + "Temps avant lancement : "+ ChatColor.GOLD + (Boucle.getTime()+1) + ChatColor.GREEN + " secondes");
		}
		else {
			timer = objective.getScore(ChatColor.GRAY + "en attente");
		}
		timer.setScore(-6);
		
		for(Player online : Bukkit.getOnlinePlayers()){
			online.setScoreboard(affichage);
		}
		firstTime = 0;
		
	}
	
}
