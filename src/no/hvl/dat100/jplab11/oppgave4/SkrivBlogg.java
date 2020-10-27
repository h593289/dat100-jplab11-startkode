package no.hvl.dat100.jplab11.oppgave4;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

import no.hvl.dat100.jplab11.common.TODO;
import no.hvl.dat100.jplab11.oppgave3.*;

public class SkrivBlogg {

	public static boolean skriv(Blogg samling, String mappe, String filnavn) {

		try {
			File textfile = new File(mappe + filnavn);
			PrintWriter writer = new PrintWriter(textfile);
			writer.write(samling.toString());
			writer.close();
			return true;
		}
		catch (FileNotFoundException fnfe) {
			return false;
		}
	}
}
