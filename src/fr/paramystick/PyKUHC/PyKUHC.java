package fr.paramystick.PyKUHC;

import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

import fr.paramystick.PyKUHC.events.PlayerJoin;
import fr.paramystick.PyKUHC.events.PlayerQuit;

public class PyKUHC extends JavaPlugin {
	public final Logger log = Logger.getLogger("Minecraft"); // On récupére dans une variable "log" la console
	public final PluginDescriptionFile pdfFile = this.getDescription(); // On lis le fichier "plugin.yml"
	public String nomPlugin = pdfFile.getName(); // On crée une variable public qui va permettre d'etre appeler partout (PUBLIC)
	
	@Override
	public void onDisable() { // Lorsque le plugin est désactiver ou redémarrer
		Bukkit.getConsoleSender().sendMessage(ChatColor.AQUA + "[ParamYsticK] " + ChatColor.RED + this.nomPlugin + " v" + pdfFile.getVersion() + " a ete Desactiver !"); // On affiche dans la console
	}
	
	@Override
	public void onEnable() { // Lorsque le plugin est activer
		getServer().getPluginManager().registerEvents(new PlayerJoin(this), this);
		getServer().getPluginManager().registerEvents(new PlayerQuit(this), this);
		
		Bukkit.getConsoleSender().sendMessage(ChatColor.AQUA + "[ParamYsticK] " + ChatColor.GREEN + this.nomPlugin + " v" + pdfFile.getVersion() + " a ete Activer !");	// On affiche dans la console
	}
}
