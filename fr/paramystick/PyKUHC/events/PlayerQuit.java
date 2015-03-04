package fr.paramystick.PyKUHC.events;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import fr.paramystick.PyKUHC.PyKUHC;

public class PlayerQuit implements Listener {
	private PyKUHC pluginPyKUHC; // On crée la variable qui va etre utiliser dans cette classe seulement (PRIVATE)
	
	public PlayerQuit(PyKUHC pluginPyKUHC) { // On crée le constructor
		this.pluginPyKUHC = pluginPyKUHC;
	}
	
	@EventHandler
	public void playerQuit(PlayerQuitEvent evenement) {
		evenement.setQuitMessage(ChatColor.AQUA + "[" + pluginPyKUHC.nomPlugin + "] " + ChatColor.GOLD + evenement.getPlayer().getName() + ChatColor.GREEN + " a fuis la session !"); // On envoie un message a tout le monde pour prevenir qu'un joueur a quitter le serveur
	}
}
