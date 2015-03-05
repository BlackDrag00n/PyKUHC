package fr.paramystick.PyKUHC;

import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

import fr.paramystick.PyKUHC.commandes.CommandeTimer;
import fr.paramystick.PyKUHC.events.PlayerJoin;
import fr.paramystick.PyKUHC.events.PlayerQuit;
import fr.paramystick.PyKUHC.fonctions.Boucle;
import fr.paramystick.PyKUHC.fonctions.Scoreboard;

public class PyKUHC extends JavaPlugin {
	
	private CommandeTimer CommandeTimer;
	
	public final Logger log = Logger.getLogger("Minecraft"); // On r�cup�re dans une variable "log" la console
	public final PluginDescriptionFile pdfFile = this.getDescription(); // On lis le fichier "plugin.yml"
	public String nomPlugin = pdfFile.getName(); // On cr�e une variable public qui va permettre d'etre appeler partout (PUBLIC)
	
	
	@Override
	public void onDisable() { // Lorsque le plugin est d�sactiver ou red�marrer
		Bukkit.getConsoleSender().sendMessage(ChatColor.AQUA + "[ParamYsticK] " + ChatColor.RED + this.nomPlugin + " v" + pdfFile.getVersion() + " a ete Desactiver !"); // On affiche dans la console
		getServer().getScheduler().cancelTask(Boucle.loop); // je coupe la boucle timer lorque le plugin est d�sactiv�
		getServer().getScheduler().cancelTask(Scoreboard.affLoop);
	}
	
	@Override
	public void onEnable() { // Lorsque le plugin est activer
		getServer().getPluginManager().registerEvents(new PlayerJoin(this), this); // Enregistrer l'evenement PlayerJoin de la classe "PlayerJoin"
		getServer().getPluginManager().registerEvents(new PlayerQuit(this), this); // Enregistrer l'evenement PlayerQuit de la classe "PlayerQuit"
		
		CommandeTimer = new CommandeTimer(this); // On pr�cise qu'il y a des commandes dans la classe "CommandeTimer"
		getCommand("timer").setExecutor(CommandeTimer); // On pr�cise quelle commande on veut r�cup�rer de la classe "CommandeTimer", ici : /timer
		
		Scoreboard.affLoop = this.getServer().getScheduler().scheduleSyncRepeatingTask(this,new Scoreboard(this), 0, 20); // On affiche le Scoreboard du plugin
		
		Bukkit.getConsoleSender().sendMessage(ChatColor.AQUA + "[ParamYsticK] " + ChatColor.GREEN + this.nomPlugin + " v" + pdfFile.getVersion() + " a ete Activer !"); // On affiche dans la console
	}
}



/*
--------------- RESTE A FAIRE : ---------------
// Cr�ation des �quipes possibles (Ne Pas Toucher a l'ORDRE)
 * dark_red
 * red
 * gold
 * yellow
 * dark_green
 * dark_aqua
 * green
 * aqua
 * blue
 * dark_blue
 * dark_purple
 * light_purple
 * black
 * dark_gray
 * gray
 * white

// Commande Pour rejoindre, ou quitter une team + v�rification si la team n'a pas d�j� le nombre requis ! (1v1, 2V2, etc ...)
 * Lorsque qu'une team est full, on rajoute +1 a la variable "k" pour dire qu'une team compl�te est former
 * Impossibilit� de rejoindre une team full
 * Les personnes qui sont seul dans leur team sont kick�, si deux team ont 1 personnes ont fusionne les teams si pas possible on kick

// Type de TP al�atoire au d�marrage de la game, en cercle, en carrer, ...

// Faire apparaitre un wordborder

// SETWORLD SPAWN 0 200 0

// Faire apparaitre une structure au coordonn�e 0 200 0 (l'id�ale serait de le faire a partir d'un fichier Schematic

// Gestion du timer : Compte le temps de jeu ou compte a rebour a partir d'un temps donn�e exemple : 1h, 1h30, etc ...

// Distance entre le joueur et le centre de la carte

// Resoudre le bug d'affichage du scoreboard
 * source : https://www.youtube.com/watch?v=33C_JG-YnJs
 * source : https://www.youtube.com/watch?v=qFh_KP9T1ec
 * source : https://www.youtube.com/watch?v=ROcTZz4OUeo

// ...
-----------------------------------------------
*/