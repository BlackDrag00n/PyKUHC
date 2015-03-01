package fr.paramystick.PyKUHC.fonctions;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerExpChangeEvent;
import fr.paramystick.PyKUHC.PyKUHC;

public class Boucle extends PyKUHC {
	public void timerLoop() {
		
		double Dsec = 0; // variable contenant les dixi�mes de secondes
		int timeLastTick = 0; // variable contenant la valeur de time le tick pr�c�dant
		
		while (true) {
			Dsec = Dsec + 0.1; // j'incr�mente la variable Dsec d'un dixi�me tous les ticks
			if (Dsec > 10) {
				Dsec = 0;  // si Dsec est sup�rieur a 10 alors je la remet a 0
			}
			timeLastTick = time; // je d�finie timeLastTick avat que Time ne change de valeur
			if ( time > 0) {
				time = time - (int)Dsec; // je d�cr�ment time de Dsec sans compt� la virgule
			}
			if (time != timeLastTick && time <= 5) { // si time n'est pas �gale a timeLastTick ET que time est inf�rieur ou �gale a 5 alors
				Bukkit.getConsoleSender().sendMessage(ChatColor.AQUA + "[ParamYsticK] " + ChatColor.YELLOW + "[Timer]" + ChatColor.GREEN + time +" Secondes" ); // j'affiche le temps restant � partir de 5 secondes
			}
			for ( Player p : Bukkit.getOnlinePlayers() ) { // pour chaque joueur pr�sentsur le serveur 
				((PlayerExpChangeEvent) p).setAmount(time);  // j'affiche le temps restant avec leur barre d'exp�rience
			}
		}
	}
}
