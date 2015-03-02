package fr.paramystick.PyKUHC.fonctions;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import fr.paramystick.PyKUHC.PyKUHC;

public class Boucle implements Runnable {
	public PyKUHC pluginPyKUHC;
	public void run() {
		PyKUHC.setTime(PyKUHC.getTime() - 1);
		
		for (Player player : Bukkit.getOnlinePlayers()) { // pour chaque joueur présent sur le serveur 
			player.setLevel(PyKUHC.getTime());  // j'affiche le temps restant avec leur barre d'expérience
			if (PyKUHC.getTime() == 10 || (PyKUHC.getTime() <= 5 && PyKUHC.getTime() > 0)) {
				player.sendMessage(ChatColor.AQUA + "[ParamYsticK] " + ChatColor.YELLOW + "[Timer]" + ChatColor.GREEN + PyKUHC.getTime() +" Secondes");
			}
			if (PyKUHC.getTime() == 0) {
				player.sendMessage(ChatColor.AQUA + "[ParamYsticK] " + ChatColor.YELLOW + "[Timer]" + ChatColor.GREEN + "L'UHC démarre !!");
				break;
			}
		}
	}
}
