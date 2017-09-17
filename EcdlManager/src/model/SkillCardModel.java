package model;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import control.EcdlManager;
import model.SkillCard;;

public class SkillCardModel extends AbstractTableModel {
	
	List<SkillCard> Skill;
	EcdlManager ecldMan = new EcdlManager();
	
	public SkillCardModel() {
		aggiornaTabella();
	}

	public void aggiornaTabella() {
		setSkill(ecldMan.getSkillCard());
		fireTableDataChanged();
	}
	
	public SkillCard getSingleRow(int riga){
		return getSkills().get(riga);
	}

	@Override
	public int getRowCount() {
		return Skill.size();
	}

	@Override
	public int getColumnCount() {
		return 6;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		SkillCard sk = getSkills().get(rowIndex);
		
		switch (columnIndex) {
		case 0:
			return sk.getSkillCard();
		case 1:
			return sk.getNome();
		case 2:
			return sk.getCognome();
		case 3:
			return sk.getEmail();
		case 4:
			return sk.getPwd();
		case 5:
			return sk.getDataDiNascita();
		default:
			return null;
		}
	}
	
	@Override
	public String getColumnName(int column) {
		switch (column) {
		case 0:
			return "Skill Card";
		case 1:
			return "Name";
		case 2:
			return "Surname";
		case 3:
			return "Email";
		case 4:
			return "Password";
		case 5:
			return "Date of Birth";

		default:
			return null;
		}
	}

	public List<SkillCard> getSkills() {
		if (Skill == null) {
			Skill = new ArrayList<SkillCard>();	
		}
		return Skill;
	}

	public void setSkill(List<SkillCard> skill) {
		Skill = skill;
	}
	
	

}
