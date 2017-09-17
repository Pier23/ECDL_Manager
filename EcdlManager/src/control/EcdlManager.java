package control;

import java.util.List;

import model.Esame;
import model.SkillCard;


public class EcdlManager {
	
	private EcdlDAO dao;

	public EcdlDAO getDao() {
		if (dao == null) {
			dao = new EcdlDAOSQLServer();	
		}
		return dao;
	}
	
	public void addEsame(Esame es){
		getDao().addEsame(es);
	}
	public void addSkillCard(SkillCard sk){
		getDao().addSkillCard(sk);
	}
	public void delEsame(Esame es){
		getDao().delEsame(es);
	}
	public void delSkillCard(SkillCard sk){
		getDao().delSkillCard(sk);
	}
	public void updateEsame(Esame es){
		getDao().updateEsame(es);
	}
	public void updateSkillCard(SkillCard sk){
		getDao().updateSkillCard(sk);
	}
	
	public List<Esame> getEsame(String skill){
		return getDao().getEsame(skill);
		
	}
	public List<SkillCard> getSkillCard(){
		return getDao().getSkillCard();
	}

}
