package fr.paramystick.PyKUHC.fonctions;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.scoreboard.Team;

import fr.paramystick.PyKUHC.fonctions.Scoreboard;

public class Lancement {
	
	public static Team LsTeam[] = new Team[15];
	public static String TeamDispo[] = new String[15];
	public static int NbJoueurInEquipe[] = new int[15];

	public static void initialiseTeam() {
		ChatColor LsColor[] = new ChatColor[] {ChatColor.DARK_RED,ChatColor.RED,ChatColor.GOLD,ChatColor.YELLOW,ChatColor.DARK_GREEN,ChatColor.DARK_AQUA,ChatColor.GREEN,ChatColor.AQUA,
											   ChatColor.BLUE,ChatColor.DARK_BLUE,ChatColor.DARK_PURPLE,ChatColor.LIGHT_PURPLE,ChatColor.BLACK,ChatColor.DARK_GRAY,ChatColor.GRAY,ChatColor.WHITE};
		String LsTeamName[] = new String[] {"Rouge_Foncé","Rouge","Or","Jaune","Vert_Foncé","Aqua Foncé","Vert","Aqua","Bleu","Bleu_Foncé","Violet_Foncé","Violet_Clair","Noir","Gris_Foncé","Gris","Blanc"};
	
		int maxJoueur = Bukkit.getMaxPlayers();
	
		int JpE = Scoreboard.joueurParEquipe;
		int NbEquipeMax;
		NbEquipeMax = maxJoueur/JpE;
	
		for (int i=0;i < NbEquipeMax;i++) {
			LsTeam[i] = Scoreboard.affichage.registerNewTeam(LsTeamName[i]);
			LsTeam[i].setDisplayName(LsTeamName[i]);
			LsTeam[i].setPrefix(LsColor[i] + "");
			TeamDispo[i] = LsTeamName[i];
			NbJoueurInEquipe[i] = 0;
		}
	}
	public static void suppressTeam() {
		for (int k = 0; k < 15; k++) {
			if (Lancement.TeamDispo[k] != null) {
				LsTeam[k].unregister();
			}
			NbJoueurInEquipe[k] = -1;
		}
 	}

}
