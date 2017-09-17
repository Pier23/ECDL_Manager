package model;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import control.EcdlManager;

public class EsameModel extends AbstractTableModel {
	
	List<Esame> esami;
	EcdlManager ecdlMan = new EcdlManager();
	
	public EsameModel(String skills) {
		aggiornaTabella(skills);
	}
	
	public Esame getSingleRow(int riga){
		return getEsami().get(riga);
	}

	public void aggiornaTabella(String skills) {
		setEsami(ecdlMan.getEsame(skills));
		fireTableDataChanged();
	}

	@Override
	public int getRowCount() {
		return getEsami().size();
	}

	@Override
	public int getColumnCount() {
		return 6;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Esame es = getEsami().get(rowIndex);
		
		switch (columnIndex) {
		case 0:
			return es.getIdEsame();
		case 1:
			return es.getDate();
		case 2:
			return es.getModulo();
		case 3:
			return es.getPunteggio();
		case 4:
			return es.isEsito();
		case 5:
			return es.getSkillsCard();
		default:
			return null;
		}
	}
	
	@Override
	public String getColumnName(int column) {
		switch (column) {
		case 0:
			return "ID Esame";
		case 1:
			return "Date";
		case 2:
			return "Module";
		case 3:
			return "Score";
		case 4:
			return "Result";
		case 5:
			return "Skill Card";

		default:
			return null;
		}
	}
	
	//GETTERS AND SETTERS
	public List<Esame> getEsami() {
		if (esami == null) {
			esami = new ArrayList<Esame>();	
		}
		return esami;
	}

	public void setEsami(List<Esame> esami) {
		this.esami = esami;
	}
	
}
