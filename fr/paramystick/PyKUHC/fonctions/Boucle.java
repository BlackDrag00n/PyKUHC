package fr.paramystick.PyKUHC.fonctions;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import fr.paramystick.PyKUHC.PyKUHC;

public class Boucle implements Runnable {
	private PyKUHC pluginPyKUHC; // On cr�e la variable qui va etre utiliser dans cette classe seulement (PRIVATE)
	
	public Boucle(PyKUHC pluginPyKUHC) { // On cr�e le constructor
		this.pluginPyKUHC = pluginPyKUHC;
	}

	private static int time = 0; // variable contenant le temps de d�compte en secondes
	public static int loop = 0; // variable contenant la boucle timer
	private static boolean loopActive = false; // variable pour savoir si la boucle est active ou non
	
	public static int getTime() { // me sert a r�cup�rer la variable time dans d'autre fichier , j'ai besoin de mettre cette variable en static , me demande pas pourquoi j'ai pas compris
		
		return Boucle.time;
	}

	public static void setTime(int time) {
		Boucle.time = time;
	}

	public static boolean getLoopActive() {
		return loopActive;
	}

	public static void setLoopActive(boolean loopActive) {
		Boucle.loopActive = loopActive;
	}
	
	public void run() {
		for (Player player : Bukkit.getOnlinePlayers()) { // pour chaque joueur pr�sent sur le serveur 
			
			player.setLevel(Boucle.getTime());  // j'affiche le temps restant avec leur barre d'exp�rience
			
			if (Boucle.getTime() == 10 || (Boucle.getTime() <= 5 && Boucle.getTime() > 0)) {
				player.sendMessage(ChatColor.AQUA + "[" + pluginPyKUHC.nomPlugin + "] " + ChatColor.YELLOW + "[Timer] " + ChatColor.GREEN + Boucle.getTime() +" Secondes");
				player.playSound(player.getLocation(), Sound.SUCCESSFUL_HIT, 10, 1);
			}
			if (Boucle.getTime() == 0) {
				player.sendMessage(ChatColor.AQUA + "[" + pluginPyKUHC.nomPlugin + "] " + ChatColor.YELLOW + "[Timer] " + ChatColor.GREEN + "L'UHC d�marre !!");
				player.playSound(player.getLocation(), Sound.EXPLODE, 10, 1);
				Bukkit.getServer().getScheduler().cancelTask(Boucle.loop);
				setLoopActive(false);
				break;
			}
		}
		
		Boucle.setTime(Boucle.getTime() - 1);
	}

	
}
