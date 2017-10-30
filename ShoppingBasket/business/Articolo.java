package business;

public class Articolo {
	private String codArticolo;
	private String descrizione;
	private int id;
	private String nome;
	private float prezzo;
	
	//GETTERS AND SETTERS

	public String getCodArticolo() {
		return codArticolo;
	}
	public void setCodArticolo(String codArticolo) {
		this.codArticolo = codArticolo;
	}
	public String getDescrizione() {
		return descrizione;
	}
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public float getPrezzo() {
		return prezzo;
	}
	public void setPrezzo(float prezzo) {
		this.prezzo = prezzo;
	}
	
	@Override
	public String toString() {
		StringBuilder articolo = new StringBuilder();
		articolo.append(getCodArticolo()+" ");
		articolo.append(getNome()+" ");
		articolo.append(getDescrizione()+" ");
		articolo.append(getPrezzo()+" ");
		return articolo.toString();
	}
}
