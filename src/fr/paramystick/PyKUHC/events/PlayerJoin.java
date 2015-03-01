package fr.paramystick.PyKUHC.events;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import fr.paramystick.PyKUHC.PyKUHC;

public class PlayerJoin implements Listener {
	private PyKUHC joueur; // On crée la variable qui va etre utiliser dans cette classe seulement (PRIVATE)
	
	public PlayerJoin(PyKUHC joueur) { // On crée le constructor
		this.joueur = joueur;
	}
	
	@EventHandler
	public void playerJoin(PlayerJoinEvent evenement) { // Si l'event est un JOIN serveur
		evenement.setJoinMessage(ChatColor.AQUA + "[" + joueur.nomPlugin + "] " + ChatColor.GOLD + evenement.getPlayer().getName() + ChatColor.GREEN + " a rejoint la session !"); // On envoie un message a tout le monde pour prevenir qu'un joueur a rejoint le serveur
		evenement.getPlayer().sendMessage(ChatColor.AQUA + "[" + joueur.nomPlugin + "] " + ChatColor.GREEN + "Bienvenu, Former les equipes et preparez-vous au combat !"); // On envoie un message au joueur qui rejoint
	}
}