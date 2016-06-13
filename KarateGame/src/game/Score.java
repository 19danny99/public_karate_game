package game;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.JOptionPane;

public class Score
{

	private ArrayList<Player> players;

	public Score()
	{
		players = new ArrayList<Player>();
	}

	public String getScore(Player player)
	{
		return "" + player.getscore();

	}

	public boolean playerHinzu(Player p){
		return players.add(p);
	}
	public boolean playerLoeschen(Player p){
		return players.remove(p);
	}
	public void liesBenutzerAusDatei()
	{
		BufferedReader br;
		try
		{
			// Achtung die Datei muss sich im richtigen Verzeichnis befinden
			br = new BufferedReader(new FileReader("scores.csv"));
			String line = br.readLine();
			while (line != null)
			{
				// split die Komponenten
				String[] sa = line.split(";");
				// erzeugt ein Benutzerobjekt
				Player player = new Player(sa[0],sa[1]);
				// add user to the collection
				playerHinzu(player);
				line = br.readLine();
			} // while
				// schlie�t die Datei
			br.close();
		} catch (FileNotFoundException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Schreibt alle Benutzer aus der ArrayList<Benutzer> in die Datei.
	 * 
	 * @throws IOException
	 */
	public void schreibeAlleBenutzerInDatei() throws IOException
	{
		BufferedWriter bw = new BufferedWriter(new FileWriter("scores.csv"));
		for (Player p : players)
		{
			bw.write(p.getName() + ";" + p.getscore());
		}
		bw.close();
	}
	
	public void sortPlayers(){
		Collections.sort(players);
	}

	/**
	 * Save changes to the file
	 */
	// public void p{
	// try{
	// BufferedWriter bw = new BufferedWriter(new FileWriter(fileAndPatch));
	// for(BankAccount b: bankAccounts){
	// bw.write(b.getAccountnumber() + ";" + b.getBalance() + ";" +
	// b.isAccountLocked() + ";" + b.getCustomer().getLastname() + "");
	// }
	// }
	// }
}
