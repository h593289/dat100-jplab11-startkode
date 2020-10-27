package no.hvl.dat100.jplab11.oppgave3;

import no.hvl.dat100.jplab11.common.TODO;
import no.hvl.dat100.jplab11.oppgave1.*;
import no.hvl.dat100.jplab11.oppgave2.Tekst;

public class Blogg {

	private Innlegg[] innleggtabell;
	private int nesteledig;

	public Blogg() {
		
		this(20);
	}

	public Blogg(int lengde) {
		
		innleggtabell = new Innlegg[lengde];
		nesteledig = 0;
	}

	public int getAntall() {

		return nesteledig;
	}
	
	public Innlegg[] getSamling() {
		
		return innleggtabell;
	}
	
	public int finnInnlegg(Innlegg innlegg) {

		int id = -1;
		for (int i = 0; i < nesteledig && id == -1; i++)
			if (innlegg.erLik(innleggtabell[i]))
				id = innleggtabell[i].getId();
		
		return id;
	}

	public boolean finnes(Innlegg innlegg) {

		if (finnInnlegg(innlegg) == -1)
			return false;
		
		return true;
	}

	public boolean ledigPlass() {

		return nesteledig < innleggtabell.length;
	}
	
	public boolean leggTil(Innlegg innlegg) {
		
		if (ledigPlass()) {
			innleggtabell[nesteledig] = innlegg;
			nesteledig++;
			return true;
		}
		
		return false;
	}
	
	public String toString() {

		String out = nesteledig + "\n";
		for (int i = 0; i < nesteledig; i++)
			out += innleggtabell[i].toString();
		
		return out;
	}

	// valgfrie oppgaver nedenfor
	
	public void utvid() {

		Innlegg[] nyTabell = new Innlegg[innleggtabell.length * 2];
		
		for (int i = 0; i < nesteledig; i++)
			nyTabell[i] = innleggtabell[i];
		
		innleggtabell = nyTabell;
	}
	
	public boolean leggTilUtvid(Innlegg innlegg) {

		if (!finnes(innlegg)) {
			
			if (!ledigPlass())
				utvid();
			
			leggTil(innlegg);
			
			return true;
		}
		
		return false;
	}
	
	public boolean slett(Innlegg innlegg) {

		int targetIndex = -1;
		for (int i = 0; i < nesteledig && targetIndex == -1; i++)
			if (innlegg.getId() == innleggtabell[i].getId())
				targetIndex = i;
		if (targetIndex != -1) {
			
			nesteledig--;
			innleggtabell[targetIndex] = innleggtabell[nesteledig];
			
			return true;
		}
		
		return false;
	}
	
	public int[] search(String keyword) {

		int[] ids = new int[nesteledig];
		int idCount = 0;
		
		for (int i = 0; i < nesteledig; i++) {
			if (((Tekst)innleggtabell[i]).getTekst().contains(keyword)) {
				ids[i] = innleggtabell[i].getId();
				idCount++;
			}
			else
				ids[i] = -1;
		}
		
		int[] targetIds = new int[idCount];
		int inputtedIds = 0;
		
		for (int i = 0; i < ids.length; i++)
			if (ids[i] != -1) {
				targetIds[inputtedIds] = ids[i];
				inputtedIds++;
			}
		
		return targetIds;
	}
}