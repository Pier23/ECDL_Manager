package view;



import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;
import java.sql.SQLException;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import org.zefiro.business.Articolo;
import org.zefiro.business.GestoreCarrello;
import org.zefiro.business.ModelloListaArticoli;
import org.zefiro.integration.ConnessioneDB;

public class CarrelloView extends JFrame{
	ModelloListaArticoli modListaArt = new ModelloListaArticoli();
	ConnessioneDB conn;

	//COMPONENTI GRAFICI
	
	//etichette
	private JLabel selezione = new JLabel("Selezionare l'articolo da aggiungere al carrello");
	private JLabel artContenuti = new JLabel("Articoli contenuti nel carrello");
	private JLabel impScontato = new JLabel("Importo Scontato");
	
	//bottoni
	private JButton addCarrello;
	private JButton importoTot;
	
	//campi txt
	private JTextField scontoTxt;
	private JTextField importoTxt;
	private JTextField impScontatoTxt;
	
	//check box e list
	private JCheckBox applSconto;
	
	private JList listaArticoli;
	private JScrollPane scorriArticoli;
	
	//Combo Box
	private DefaultComboBoxModel modelloCombo = new DefaultComboBoxModel();
	private JComboBox comboArticoli;
	
	//pannelli
	private JPanel pnlNord;
	private JPanel pnlCentro;
	private JPanel pnlSud;
	
	//METODI
	public CarrelloView() {
		setTitle("Carrello della Spesa");
		setSize(300, 400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		init();
	}
	
	private void init(){		
		setLayout(new BorderLayout());
		add(getPnlNord(),BorderLayout.NORTH);
		add(getPnlCentro(),BorderLayout.CENTER);
		add(getPnlSud(),BorderLayout.SOUTH);
	}
	
	
	//GETTER
	public JLabel getSelezione() {
		selezione.setHorizontalAlignment(JLabel.CENTER);
		return selezione;
	}

	public JLabel getArtContenuti() {
		artContenuti.setHorizontalAlignment(JLabel.CENTER);
		return artContenuti;
	}

	public JLabel getImpScontato() {
		impScontato.setHorizontalAlignment(JLabel.CENTER);
		return impScontato;
	}

	public JButton getAddCarrello() {
		if (addCarrello == null) {
			addCarrello = new JButton("Aggiungi al carrello");
			addCarrello.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					modListaArt.addElemento((Articolo)getComboArticoli().getSelectedItem());
					importoTxt.setText("");
					scontoTxt.setText("");
					impScontatoTxt.setText("");
				}
			});
		}
		return addCarrello;
	}

	public JButton getImportoTot() {
		if (importoTot == null) {
			importoTot = new JButton("Calcola Importo");
			importoTot.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					GestoreCarrello gestCarr = new GestoreCarrello();
					importoTxt.setText(String.valueOf(gestCarr.getImportoTotale(modListaArt.getElementi())));
					if(applSconto.isSelected() && !scontoTxt.getText().equals("")){
						impScontatoTxt.setText(String.valueOf
								(gestCarr.getImportoScontato(gestCarr.getImportoTotale(modListaArt.getElementi()),Double.parseDouble(scontoTxt.getText()))));
					}
				}
			});
		}
		return importoTot;
	}

	public JTextField getScontoTxt() {
		if (scontoTxt == null) {
			scontoTxt = new JTextField();
			scontoTxt.setHorizontalAlignment(JTextField.CENTER);
		}
		return scontoTxt;
	}

	public JTextField getImportoTxt() {
		if (importoTxt == null) {
			importoTxt = new JTextField();
			importoTxt.setEditable(false);
			importoTxt.setHorizontalAlignment(JTextField.CENTER);
		}
		return importoTxt;
	}

	public JTextField getImpScontatoTxt() {
		if (impScontatoTxt == null) {
			impScontatoTxt = new JTextField();
			impScontatoTxt.setEditable(false);
			impScontatoTxt.setHorizontalAlignment(JTextField.CENTER);
		}
		return impScontatoTxt;
	}

	public JCheckBox getApplSconto() {
		if (applSconto == null) {
			applSconto = new JCheckBox("Applica Sconto");
			applSconto.setHorizontalAlignment(JCheckBox.CENTER);
		}
		return applSconto;
	}

	public JList getListaArticoli() {
		if (listaArticoli == null) {
			listaArticoli = new JList(modListaArt);
		}
		return listaArticoli;
	}

	public JScrollPane getScorriArticoli() {
		if (scorriArticoli == null) {
			scorriArticoli = new JScrollPane(getListaArticoli());
			
		}
		return scorriArticoli;
	}

	public DefaultComboBoxModel getModelloCombo() {
		return modelloCombo;
	}

	public JComboBox getComboArticoli() {
		try{
		if (comboArticoli == null) {
			conn = new ConnessioneDB();
			for (Articolo articolo : conn.getAll()) {
				modelloCombo.addElement(articolo);
			}
			comboArticoli = new JComboBox(modelloCombo);
		}
		}
		catch(SQLException e){
			System.out.println("Errore SQL...");
		}
		return comboArticoli;
	}

	public JPanel getPnlNord() {
		if (pnlNord == null) {
			pnlNord  = new JPanel(new GridLayout(0,1));	
			
			pnlNord.add(getSelezione());
			pnlNord.add(getComboArticoli());
			pnlNord.add(getAddCarrello());
			pnlNord.add(getArtContenuti());
		}
		return pnlNord;
	}

	public JPanel getPnlCentro() {
		if (pnlCentro == null) {
			pnlCentro = new JPanel(new GridLayout(0,1));
			
			pnlCentro.add(getScorriArticoli());
		}
		return pnlCentro;
	}

	public JPanel getPnlSud() {
		if (pnlSud == null) {
			pnlSud = new JPanel(new GridLayout(3,2));
			
			pnlSud.add(getImportoTot());
			pnlSud.add(getImportoTxt());
			pnlSud.add(getApplSconto());
			pnlSud.add(getScontoTxt());
			pnlSud.add(getImpScontato());
			pnlSud.add(getImpScontatoTxt());
		}

		return pnlSud;
	}

	public static void main(String[] args) {
		CarrelloView carrello = new CarrelloView();
		carrello.setVisible(true);
	}

}
