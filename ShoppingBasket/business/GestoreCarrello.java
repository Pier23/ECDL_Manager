package business;

import java.util.ArrayList;

public class GestoreCarrello {
	
	public double getImportoTotale(ArrayList<Articolo> listaArticoli){
		
		double importoTot = 0;
		
		for (Articolo articolo : listaArticoli) {
			
			importoTot += articolo.getPrezzo();
		}
		return importoTot;
	}
		
	public double getImportoScontato(double importo, double sconto){
		
		double importoScontato = 0;
		
		importoScontato = importo - (importo * sconto/100);
		
		return importoScontato;
	}
	
	
	
	

}
