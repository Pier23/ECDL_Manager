package model;

import java.util.Date;

public class Esame {
	
	private int idEsame;
	private Date date;
	private String modulo;
	private int punteggio;
	private boolean esito;	
	private String skillsCard;
	
	public int getIdEsame() {
		return idEsame;
	}
	public void setIdEsame(int idEsame) {
		this.idEsame = idEsame;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getModulo() {
		return modulo;
	}
	public void setModulo(String modulo) {
		this.modulo = modulo;
	}
	public int getPunteggio() {
		return punteggio;
	}
	public void setPunteggio(int punteggio) {
		this.punteggio = punteggio;
	}
	public boolean isEsito() {
		return esito;
	}
	public void setEsito(boolean esito) {
		this.esito = esito;
	}
	public String getSkillsCard() {
		return skillsCard;
	}
	public void setSkillsCard(String skillsCard) {
		this.skillsCard = skillsCard;
	}
}
