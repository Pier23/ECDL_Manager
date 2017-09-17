package control;

import java.util.List;

import model.Esame;
import model.SkillCard;

public interface EcdlDAO {
	
	public void addEsame(Esame es);
	public void addSkillCard(SkillCard sk);
	public void delEsame(Esame es);
	public void delSkillCard(SkillCard sk);
	public void updateEsame(Esame es);
	public void updateSkillCard(SkillCard sk);
	
	public List<Esame> getEsame(String skill);
	public List<SkillCard> getSkillCard();

}
