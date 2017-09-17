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
import java.util.GregorianCalendar;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

import control.EcdlManager;
import model.Esame;
import model.SkillCard;
import model.SkillCardModel;

public class SkillCardView extends JFrame {
	
	private JLabel skillCard = new JLabel("Skill Card");
	private JLabel nome = new JLabel("Name");
	private JLabel cognome = new JLabel("Surname");
	private JLabel email = new JLabel("Email");
	private JLabel pwd = new JLabel("Pwd");
	private JLabel dataDiNascita = new JLabel("Date of Birth");
	private JTextField txtSkillCard,txtNome,txtCognome,txtEmail,txtPwd,txtDataDiNascita;
	private JTable tabellaSkillCards;
	private JScrollPane scrollTblSkills;
	private JButton salva,aggiorna;
	private JPanel pnlNord,pnlCentro;
	private JPopupMenu menu;
	private JMenuItem itemModifica,itemElimina,itemEsami;
	
	//COMPONENTI LOGICI
	EcdlManager manager;
	SkillCardModel modTabSkill;
	GestioneEventi listner;
	GestionePopup listnerPopup;
	
	//METODI
	public SkillCardView() {
		setTitle("Skill Card");
		setSize(400,500);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		init();
	}

	private void init() {
		setLayout(new BorderLayout());
		add(getPnlNord(),BorderLayout.NORTH);
		add(getPnlCentro(),BorderLayout.CENTER);
	}
	
	private void pulisciCampi(){
		getTxtNome().setText("");
		getTxtCognome().setText("");
		getTxtEmail().setText("");
		getTxtPwd().setText("");
		getTxtDataDiNascita().setText("");
		getTxtSkillCard().setText("");
	}
	
	private Date convertiData(){
		DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
		Date datainserita = new Date();
		java.sql.Date datasql = null;
		String data = getTxtDataDiNascita().getText();
		try {
			datainserita = df.parse(data);
			datasql = new java.sql.Date(datainserita.getTime());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return datasql;
	}

	//getter
	
	public GestioneEventi getListner() {
		if (listner == null) {
			listner = new GestioneEventi();	
		}
		return listner;
	}

	public JTextField getTxtSkillCard() {
		if (txtSkillCard == null) {
			txtSkillCard = new JTextField() ;
		}
		return txtSkillCard;
	}

	public GestionePopup getListnerPopup() {
		if (listnerPopup == null) {
			listnerPopup = new GestionePopup();
		}
		return listnerPopup;
	}

	public JTextField getTxtNome() {
		if (txtNome == null) {
			txtNome = new JTextField() ;
		}
		return txtNome;
	}

	public JTextField getTxtCognome() {
		if (txtCognome == null) {
			txtCognome = new JTextField() ;
		}
		return txtCognome;
	}

	public JTextField getTxtEmail() {
		if (txtEmail == null) {
			txtEmail = new JTextField() ;
		}
		return txtEmail;
	}

	public JTextField getTxtPwd() {
		if (txtPwd == null) {
			txtPwd = new JTextField() ;
		}
		return txtPwd;
	}

	public JTextField getTxtDataDiNascita() {
		if (txtDataDiNascita == null) {
			txtDataDiNascita = new JTextField() ;
		}
		return txtDataDiNascita;
	}

	public JTable getTabellaSkillCards() {
		if (tabellaSkillCards == null) {
			tabellaSkillCards = new JTable(getModTabSkill());
			tabellaSkillCards.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			tabellaSkillCards.addMouseListener(getListnerPopup());
		}
		return tabellaSkillCards;
	}

	public JScrollPane getScrollTblSkills() {
		if (scrollTblSkills == null) {
			scrollTblSkills = new JScrollPane(getTabellaSkillCards()) ;
		}
		return scrollTblSkills;
	}

	public JButton getSalva() {
		if (salva == null) {
			salva = new JButton("Save") ;
			
			salva.addActionListener(getListner());
		}
		return salva;
	}

	public JButton getAggiorna() {
		if (aggiorna == null) {
			aggiorna = new JButton("Update");
			aggiorna.setEnabled(false);
			aggiorna.addActionListener(getListner());
		}
		return aggiorna;
	}

	public JPanel getPnlNord() {
		if (pnlNord == null) {
			pnlNord = new JPanel(new GridLayout(0,2));
		
			pnlNord.add(skillCard);
			pnlNord.add(getTxtSkillCard());
			pnlNord.add(nome);
			pnlNord.add(getTxtNome());
			pnlNord.add(cognome);
			pnlNord.add(getTxtCognome());
			pnlNord.add(email);
			pnlNord.add(getTxtEmail());
			pnlNord.add(pwd);
			pnlNord.add(getTxtPwd());
			pnlNord.add(dataDiNascita);
			pnlNord.add(getTxtDataDiNascita());
			pnlNord.add(getSalva());
			pnlNord.add(getAggiorna());
		}
		return pnlNord;
	}

	public JPanel getPnlCentro() {
		if (pnlCentro == null) {
			pnlCentro = new JPanel(new GridLayout(1,1));
			
			pnlCentro.add(getScrollTblSkills());
		}
		return pnlCentro;
	}

	public JPopupMenu getMenu() {
		if (menu == null) {
			menu = new JPopupMenu();
			menu.add(getItemModifica());
			menu.add(getItemElimina());
			menu.add(getItemEsami());
		}
		return menu;
	}

	public JMenuItem getItemModifica() {
		if (itemModifica == null) {
			itemModifica = new JMenuItem("Update");
			itemModifica.addMouseListener(getListnerPopup());
		}
	return itemModifica;
	}

	public JMenuItem getItemElimina() {
		if (itemElimina == null) {
			itemElimina = new JMenuItem("Delete");
			itemElimina.addMouseListener(getListnerPopup());
		}
		return itemElimina;
	}

	public JMenuItem getItemEsami() {
		if (itemEsami == null) {
			itemEsami= new JMenuItem("Examinations");
			itemEsami.addMouseListener(getListnerPopup());
		}
		return itemEsami;
	}

	public EcdlManager getManager() {
		if (manager == null) {
			manager = new EcdlManager();
		}
		return manager;
	}

	public SkillCardModel getModTabSkill() {
		if (modTabSkill == null) {
			modTabSkill = new SkillCardModel();	
		}
		return modTabSkill;
	}

	
	class GestioneEventi implements ActionListener{
	
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == salva)
				salvaClick();
			else if(e.getSource() == aggiorna);
				aggiornaClick();
			
		}

		private void aggiornaClick() {
			SkillCard sk = getModTabSkill().getSingleRow(getTabellaSkillCards().getSelectedRow());
			
			sk.setSkillCard(getTxtSkillCard().getText());
			sk.setNome(getTxtNome().getText());
			sk.setCognome(getTxtCognome().getText());
			sk.setEmail(getTxtEmail().getText());
			sk.setPwd(getTxtPwd().getText());
			sk.setDataDiNascita(convertiData());
			System.out.println(sk.toString());
			
			getManager().updateSkillCard(sk);
			getModTabSkill().aggiornaTabella();
			
			tabellaSkillCards.setEnabled(true);
			salva.setEnabled(true);
			aggiorna.setEnabled(false);
			pulisciCampi();
		}

		private void salvaClick() {
			SkillCard sk = new SkillCard();
			
			sk.setSkillCard(getTxtSkillCard().getText());
			sk.setNome(getTxtNome().getText());
			sk.setCognome(getTxtCognome().getText());
			sk.setEmail(getTxtEmail().getText());
			sk.setPwd(getTxtPwd().getText());
			sk.setDataDiNascita(convertiData());
			
			getManager().addSkillCard(sk);
			getModTabSkill().aggiornaTabella();
			pulisciCampi();
		}
		
	}
	class GestionePopup implements MouseListener{


	@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent e) {
			if(e.isPopupTrigger()){
				getMenu().show(e.getComponent(), e.getX(), e.getY());
			}
			if(e.getSource() == getItemElimina()){
				gestisciElimina();
			}
			if(e.getSource() == getItemModifica()){
				gestisciModifica();
			}
			if(e.getSource() == getItemEsami()){
				gestisciEsami();
			}
			
		}

		private void gestisciModifica() {
			if(getTabellaSkillCards().getSelectedRow() != -1){
				SkillCard sk = getModTabSkill().getSingleRow(getTabellaSkillCards().getSelectedRow());
				
				getTxtSkillCard().setText(sk.getSkillCard());
				getTxtNome().setText(sk.getNome());
				getTxtCognome().setText(sk.getCognome());
				getTxtEmail().setText(sk.getEmail());
				getTxtPwd().setText(sk.getPwd());
				getTxtDataDiNascita().setText(String.valueOf(sk.getDataDiNascita()));
				aggiorna.setEnabled(true);
				salva.setEnabled(false);
				tabellaSkillCards.setEnabled(false);
				txtSkillCard.setEnabled(false);
			}
			else
				JOptionPane.showMessageDialog(tabellaSkillCards, "Devi selezionare un record", "Modifica Tabella",JOptionPane.WARNING_MESSAGE);
		}

		private void gestisciElimina() {
			if(getTabellaSkillCards().getSelectedRow() != -1){
				SkillCard sk = getModTabSkill().getSingleRow(getTabellaSkillCards().getSelectedRow());
				getTxtSkillCard().setText(sk.getSkillCard());
				getManager().delSkillCard(sk);
				getModTabSkill().aggiornaTabella();
			}
			
		}

		private void gestisciEsami() {
			SkillCard sk = getModTabSkill().getSingleRow(getTabellaSkillCards().getSelectedRow());
			EsamiView ev = new EsamiView(sk.getSkillCard());
			ev.getTxtSkill().setText(sk.getSkillCard());
			ev.getTxtUtente().setText(sk.getNome() + " " + sk.getCognome());
			ev.setVisible(true);
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
	
	}
	public static void main(String[] args) {
		SkillCardView skw = new SkillCardView();
		skw.setVisible(true);
}
}

