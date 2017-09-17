package view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import control.EcdlDAO;
import control.EcdlDAOSQLServer;
import control.EcdlManager;
import model.Esame;
import model.EsameModel;
import model.SkillCard;

public class EsamiView extends JFrame {
	private JLabel utente = new JLabel("User");
	private JLabel data = new JLabel("Date");
	private JLabel modulo = new JLabel("Module");
	private JLabel punteggio = new JLabel("Score");
	private JLabel esito = new JLabel("Result");
	private JLabel skillCard = new JLabel("N° Skill Card");
	private JTextField txtUtente,txtData,txtPunteggio,txtSkill;
	private JComboBox<String> txtEsito,txtModulo;
	private JButton salva,aggiorna;
	private JTable tabellaEsami;
	private JScrollPane scrollTblEsami;
	private JPanel pnlNord,pnlCentro;
	private JPopupMenu menu;
	private JMenuItem modifica,elimina;
	
	//COMPONENTI LOGICI TODO
	EcdlManager manager = new EcdlManager();
	EsameModel modTabEsami;
	GestioneEventi listner;
	GestionePopUp gestPop = new GestionePopUp();
	//METODI
	public EsamiView(String skill) {
		setTitle("Esami Sostenuti");
		setSize(400,500);
		setLocation(200, 400);
		init(skill);	
		}
	
	private void init(String skill) {
		setLayout(new BorderLayout());
		add(getPnlNord(),BorderLayout.NORTH);
		add(getPnlCentro(skill),BorderLayout.CENTER);
	}
	
	private void pulisciCampi(){
		getTxtData().setText("");
		getTxtModulo();
		getTxtPunteggio().setText("");
	}
	
	private Date convertiData(){
		DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
		Date datainserita = new Date();
		java.sql.Date datasql = null;
		String data = getTxtData().getText();
		try {
			datainserita = df.parse(data);
			datasql = new java.sql.Date(datainserita.getTime());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return datasql;
	}
	
	private boolean esito(){
		Boolean esito = true;
		if(getTxtEsito().getSelectedItem().toString().equals("Pass")){
			esito = true;
		}
		else
			esito = false;
		return esito;
	}

	
	public GestioneEventi getListner() {
		if (listner == null) {
			listner = new GestioneEventi();
		}
		return listner;
	}

	public EsameModel getModTabEsami(String skill) {
		if (modTabEsami == null) {
			modTabEsami = new EsameModel(skill);	
		}
		return modTabEsami;
	}

	public JTextField getTxtUtente() {
		if (txtUtente == null) {
			txtUtente = new JTextField();
			txtUtente.setEnabled(false);
		}
		return txtUtente;
	}

	public JTextField getTxtData() {
		if (txtData == null) {
			txtData = new JTextField();
		}
		return txtData;
	}

	public JComboBox<String> getTxtModulo() {
		if (txtModulo == null) {
			String moduli[] = {"Word", "Excel", "Power Point", "Access"};
			txtModulo = new JComboBox<String>(new DefaultComboBoxModel<String>(moduli));
		}
		return txtModulo;
	}

	public JTextField getTxtPunteggio() {
		if (txtPunteggio == null) {
			txtPunteggio = new JTextField();
		}
		return txtPunteggio;
	}

	public JComboBox<String> getTxtEsito() {
		if (txtEsito == null) {
			String esito[] = {"Pass","Fail"};
			txtEsito = new JComboBox<String>(new DefaultComboBoxModel<String>(esito));
		}
		return txtEsito;
	}

	public JTextField getTxtSkill() {
		if (txtSkill == null) {
			txtSkill = new JTextField();
			txtSkill.setEnabled(false);
		}
		return txtSkill;
	}

	public JButton getSalva() {
		if (salva == null) {
			salva = new JButton("Save");	
			salva.addActionListener(getListner());
		}
		return salva;
	}

	public JButton getAggiorna() {
		if (aggiorna == null) {
			aggiorna = new JButton("Update");
		}
		return aggiorna;
	}

	public JTable getTabellaEsami(String skill) {
		if(tabellaEsami == null){
			tabellaEsami = new JTable(getModTabEsami(skill));
		}
		return tabellaEsami;
	}

	public JScrollPane getScrollTblEsami(String skills) {
		if (scrollTblEsami == null) {
			scrollTblEsami = new JScrollPane(getTabellaEsami(skills));
		}
		return scrollTblEsami;
	}

	public JPanel getPnlNord() {
		if (pnlNord == null) {
			pnlNord = new JPanel(new GridLayout(0,2));
			
			pnlNord.add(skillCard);
			pnlNord.add(getTxtSkill());
			pnlNord.add(utente);
			pnlNord.add(getTxtUtente());
			pnlNord.add(data);
			pnlNord.add(getTxtData());
			pnlNord.add(modulo);
			pnlNord.add(getTxtModulo());
			pnlNord.add(esito);
			pnlNord.add(getTxtEsito());
			pnlNord.add(punteggio);
			pnlNord.add(getTxtPunteggio());
			pnlNord.add(getSalva());
			pnlNord.add(getAggiorna());

		}
		return pnlNord;
	}

	public JPanel getPnlCentro(String skill) {
		if(pnlCentro == null){
			pnlCentro = new JPanel(new GridLayout(0,1));
			
			pnlCentro.add(getScrollTblEsami(skill));
		}
		return pnlCentro;
	}

	public JPopupMenu getMenu() {
		if (menu == null) {
			menu = new JPopupMenu();
			menu.add(getModifica());
//			menu.add(getElimina());
		}
		return menu;
	}

	public JMenuItem getModifica() {
		if (modifica == null) {
			modifica = new JMenuItem("Modifica");
			modifica.addMouseListener(gestPop);
		}

		return modifica;
	}

	public JMenuItem getElimina() {
		return elimina;
	}
	
class GestioneEventi implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == salva){
		salvaClick();
		if(e.getSource() == aggiorna ){
//		aggiornaClick(getTxtSkill().getText());
		getMenu().show(true);
		}
	}
	
}

	private void salvaClick() {
		Esame e = new Esame();
		
		e.setSkillsCard(getTxtSkill().getText());
		e.setDate(convertiData());
		e.setModulo(getTxtModulo().getSelectedItem().toString());
		e.setPunteggio(Integer.parseInt(getTxtPunteggio().getText()));
//		Boolean esito = true;
//		if(getTxtEsito().getSelectedItem().toString().equals("Superato")){
//			esito = true;
//		}
//		else
//			esito = false;
		e.setEsito(esito());
		
		manager.addEsame(e);
		getModTabEsami(getTxtSkill().getText()).aggiornaTabella(getTxtSkill().getText());
		pulisciCampi();
		}
	private void aggiornaClick(String skill){
		Esame e = getModTabEsami(skill).getSingleRow(getTabellaEsami(skill).getSelectedRow());
		
//		e.setDate(getTxtData());
		e.setModulo(getTxtModulo().getSelectedItem().toString());
		e.setEsito(esito());
		e.setPunteggio(Integer.parseInt(getTxtPunteggio().getText()));
	}
	}

	class GestionePopUp implements MouseListener{

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if(e.isPopupTrigger()){
			getMenu().show(e.getComponent(), e.getX(), e.getY());
		}
//		if(e.getSource() == getItemElimina()){
//			gestisciElimina();
//		}
		if(e.getSource() == getModifica()){
			gestisciModifica(getTxtSkill().getText());
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub	
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	private void gestisciModifica(String skill) {
		if(getTabellaEsami(skill).getSelectedRow() != -1){
			Esame es = getModTabEsami(skill).getSingleRow(getTabellaEsami(skill).getSelectedRow());
			
			getTxtData().setText(String.valueOf(es.getDate()));
//			getTxtEsito()
//			getTxtModulo().setText(es.getModulo());
			getTxtPunteggio().setText(String.valueOf(es.getPunteggio()));
			aggiorna.setEnabled(true);
			salva.setEnabled(false);
			tabellaEsami.setEnabled(false);
		}
		else
			JOptionPane.showMessageDialog(tabellaEsami, "Devi selezionare un record", "Modifica Tabella",JOptionPane.WARNING_MESSAGE);
		}
	}

//public static void main(String[] args) {
//	EsamiView ev = new EsamiView("ADV031320");
//	ev.setVisible(true);
//}
}