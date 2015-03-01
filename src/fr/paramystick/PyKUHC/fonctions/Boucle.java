package fr.paramystick.PyKUHC.fonctions;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerExpChangeEvent;
import fr.paramystick.PyKUHC.PyKUHC;

public class Boucle extends PyKUHC {
	public void timerLoop() {
		
		double Dsec = 0; // variable contenant les dixièmes de secondes
		int timeLastTick = 0; // variable contenant la valeur de time le tick précédant
		
		while (true) {
			Dsec = Dsec + 0.1; // j'incrémente la variable Dsec d'un dixième tous les ticks
			if (Dsec > 10) {
				Dsec = 0;  // si Dsec est supérieur a 10 alors je la remet a 0
			}
			timeLastTick = time; // je définie timeLastTick avat que Time ne change de valeur
			if ( time > 0) {
				time = time - (int)Dsec; // je décrément time de Dsec sans compté la virgule
			}
			if (time != timeLastTick && time <= 5) { // si time n'est pas égale a timeLastTick ET que time est inférieur ou égale a 5 alors
				Bukkit.getConsoleSender().sendMessage(ChatColor.AQUA + "[ParamYsticK] " + ChatColor.YELLOW + "[Timer]" + ChatColor.GREEN + time +" Secondes" ); // j'affiche le temps restant à partir de 5 secondes
			}
			for ( Player p : Bukkit.getOnlinePlayers() ) { // pour chaque joueur présentsur le serveur 
				((PlayerExpChangeEvent) p).setAmount(time);  // j'affiche le temps restant avec leur barre d'expérience
			}
		}
	}
}
