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

public class PyKUHC extends JavaPlugin {
	private CommandeTimer CommandeTimer;
	public final Logger log = Logger.getLogger("Minecraft"); // On récupére dans une variable "log" la console
	public final PluginDescriptionFile pdfFile = this.getDescription(); // On lis le fichier "plugin.yml"
	public String nomPlugin = pdfFile.getName(); // On crée une variable public qui va permettre d'etre appeler partout (PUBLIC)
	
	public int time = 0; // variable contenant le temps de décompte en secondes
	
	public Boucle Boucle = new Boucle(); //récupération du contenue du fichier Boucle.java
	
	@Override
	public void onDisable() { // Lorsque le plugin est désactiver ou redémarrer
		Bukkit.getConsoleSender().sendMessage(ChatColor.AQUA + "[ParamYsticK] " + ChatColor.RED + this.nomPlugin + " v" + pdfFile.getVersion() + " a ete Desactiver !"); // On affiche dans la console
	}
	
	@Override
	public void onEnable() { // Lorsque le plugin est activer
		getServer().getPluginManager().registerEvents(new PlayerJoin(this), this); // Enregistrer l'evenement PlayerJoin de la classe "PlayerJoin"
		getServer().getPluginManager().registerEvents(new PlayerQuit(this), this); // Enregistrer l'evenement PlayerQuit de la classe "PlayerQuit"
		
		// Récupérer la commande TIMER de la classe "CommandeTimer"
		CommandeTimer = new CommandeTimer(this); // On précise qu'il y a des commandes dans la classe "CommandeTimer"
		getCommand("timer").setExecutor(CommandeTimer); // On précise quelle commande on veut récupérer de la classe "CommandeTimer", ici : /timer
		
		Bukkit.getConsoleSender().sendMessage(ChatColor.AQUA + "[ParamYsticK] " + ChatColor.GREEN + this.nomPlugin + " v" + pdfFile.getVersion() + " a ete Activer !"); // On affiche dans la console
		Boucle.timerLoop(); //j'appelle ma fontion timerLoop() a partie du fichier Boucle.java
	}
}