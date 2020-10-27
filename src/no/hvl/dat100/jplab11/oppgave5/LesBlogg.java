package no.hvl.dat100.jplab11.oppgave5;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.util.Scanner;

import no.hvl.dat100.jplab11.common.TODO;
import no.hvl.dat100.jplab11.oppgave1.*;
import no.hvl.dat100.jplab11.oppgave2.*;
import no.hvl.dat100.jplab11.oppgave3.*;

import javax.swing.JOptionPane;

public class LesBlogg {

	private static String TEKST = "TEKST";
	private static String BILDE = "BILDE";

	public static Blogg les(String mappe, String filnavn) {

		try {
			File file = new File(mappe + filnavn);
			BufferedReader reader = new BufferedReader(new FileReader(file));
			
			int entries = Integer.parseInt(reader.readLine());
			Blogg blogg = new Blogg(entries);
			
			for (int i = 0; i < entries; i++) {
				String format = reader.readLine();
				if (format.contains(TEKST)) {
					
					int id = Integer.parseInt(reader.readLine());
					String bruker = reader.readLine();
					String dato = reader.readLine();
					int likes = Integer.parseInt(reader.readLine());
					String tekst = reader.readLine();
					
					Tekst innlegg = new Tekst(id, bruker, dato, likes, tekst);
					blogg.leggTil(innlegg);
				}
				else if (format.contains(BILDE)) {
					
					int id = Integer.parseInt(reader.readLine());
					String bruker = reader.readLine();
					String dato = reader.readLine();
					int likes = Integer.parseInt(reader.readLine());
					String tekst = reader.readLine();
					String url = reader.readLine();
					
					Bilde innlegg = new Bilde(id, bruker, dato, likes, tekst, url);
					blogg.leggTil(innlegg);
				}
			}
			
			return blogg;
		}
		catch (FileNotFoundException fnfe) {
			return new Blogg();
		}
		catch (IOException ioe) {
			return new Blogg();
		}
	}
}
