package model;

import java.util.Date;

public class SkillCard {
	
	private String skillCard;
	private String nome;
	private String cognome;
	private String email;
	private String pwd;
	private Date dataDiNascita;
	
	
	public String getSkillCard() {
		return skillCard;
	}
	public void setSkillCard(String skillCard) {
		this.skillCard = skillCard;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCognome() {
		return cognome;
	}
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public Date getDataDiNascita() {
		return dataDiNascita;
	}
	public void setDataDiNascita(Date dataDiNascita) {
		this.dataDiNascita = dataDiNascita;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(getSkillCard()+"\n");
		sb.append(getNome()+"\n");
		sb.append(getCognome()+"\n");
		sb.append(getEmail()+"\n");
		sb.append(getPwd()+"\n");
		sb.append(getDataDiNascita()+"\n");
		return sb.toString();
	}
	
	


}
