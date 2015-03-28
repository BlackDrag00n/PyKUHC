

package fr.paramystick.PyKUHC.fonctions;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.*;

import fr.paramystick.PyKUHC.fonctions.Boucle;
import fr.paramystick.PyKUHC.PyKUHC;

public class Scoreboard implements Runnable {

	@SuppressWarnings("unused")
	private PyKUHC pluginPyKUHC;
	
	public Scoreboard(PyKUHC pluginPyKUHC) {
		this.pluginPyKUHC = pluginPyKUHC;
	}
	
	// Création d'un scoreboard
	public static ScoreboardManager manager = Bukkit.getScoreboardManager(); // Création d'un scoreboard
	public static org.bukkit.scoreboard.Scoreboard affichage = manager.getNewScoreboard(); // Création d'un scoreboard
	Objective objective = affichage.registerNewObjective("ParamYsticK UHC", "dummy"); // Nom et type de l'objectif
	
	// Initialisation des variables (création) - NE PAS MODIFIER -
	Score nbJoueur = null;
	Score nbEquipe = null;
	Score timer = null;
	Score blank1 = null;
	Score blank2 = null;
	Score blank3 = null;
	String entry1;
	String entry2;
	String entry3;
	boolean firstTime = true;
	public static int affLoop;
	
	// Variable configurable
	public static boolean equipe = false;
	public static int joueurParEquipe = 2;
	
	
	// Boucle qui affichera ce que contient le scoreboard
	@SuppressWarnings("unused")
	public void run() {
		
		// Si c'est la premiere fois on intialise le SCOREBOARD et les espaces
		if (firstTime) {
			objective.setDisplaySlot(DisplaySlot.SIDEBAR);
			
			blank1 = objective.getScore("");
			blank1.setScore(-1);
			blank2 = objective.getScore(" ");
			blank2.setScore(-4);
		// Sinon on met a jour les informations
		} else {
			entry1 = nbJoueur.getEntry();
			entry2 = nbEquipe.getEntry();
			entry3 = timer.getEntry();
			affichage.resetScores(entry1);
			affichage.resetScores(entry2);
			affichage.resetScores(entry3);
		}
		
		//On compte le nombre de joueur en ligne / Max joueur ET on affiche le resultat
		int i = 0;
		for (Player player : Bukkit.getOnlinePlayers()) {
			i = i + 1;
		}
		int maxJoueur = Bukkit.getMaxPlayers();
		nbJoueur = objective.getScore(ChatColor.GREEN + "Joueur: " + ChatColor.GOLD + i + "/" + maxJoueur);
		nbJoueur.setScore(-2);
		
		// On compte le nombre d'équipe faisable selon le nombre de joueur en ligne / (Max joueur/ joueur par equipe) ET on affiche SI les équipes sont activé SINON j'affiche une ligne blanche
		int k = 0;
		if (equipe) {
			if (i % joueurParEquipe == 0){
				k = i / joueurParEquipe;
			}
			else {
				k = (i / joueurParEquipe) + 1;
			}
			nbEquipe = objective.getScore(ChatColor.GREEN + "Equipe: " + ChatColor.GOLD + k + "/" + maxJoueur/joueurParEquipe);
		}
		else {
			nbEquipe = objective.getScore(" ");
		}
		nbEquipe.setScore(-3);
		
		// On affiche le temps restant lors de la lancement de la commande /timer start
		if (Boucle.getLoopActive()) {
			timer = objective.getScore(ChatColor.GREEN + "Début dans: "+ ChatColor.GOLD + (Boucle.getTime()+1) + ChatColor.GREEN + "s");
		}
		else {
			timer = objective.getScore(ChatColor.GRAY + "Préparation ...");
		}
		timer.setScore(-5);
		
		// On affiche le scoreboard a tous les joueurs en ligne actuellement		
		for(Player online : Bukkit.getOnlinePlayers()){
			online.setScoreboard(affichage);
		}
		
		// On modifie cette variable pour prévenu que ce n'est plus la premiere fois que la boucle a été démarrer
		firstTime = false;
	}
}
