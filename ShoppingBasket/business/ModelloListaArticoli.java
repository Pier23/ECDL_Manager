package business;

import java.util.ArrayList;

import javax.swing.AbstractListModel;

public class ModelloListaArticoli extends AbstractListModel{
	
	ArrayList<Articolo> elementi;
	
	//METODI
	@Override
	public int getSize() {
		return getElementi().size();
	}

	@Override
	public Object getElementAt(int index) {
		return elementi.get(index);
	}
	
	public void addElemento(Articolo nuovoElemento){
		getElementi().add(nuovoElemento);
		fireContentsChanged(this, 0, getElementi().size()-1);
	}
	
	public void clearAll(){
		getElementi().clear();
		
		fireContentsChanged(this, 0, getElementi().size()-1);
	}
	
	//GETTERS AND SETTERS
	public ArrayList<Articolo> getElementi() {
		if (elementi == null) {
			elementi = new ArrayList<Articolo>();
		}
		return elementi;
	}

	public void setElementi(ArrayList<Articolo> elementi) {
		this.elementi = elementi;
	}	
	
	

}
