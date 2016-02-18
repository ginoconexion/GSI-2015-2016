package fr.emac.gipsi.gsi.gui;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import fr.emac.gipsi.gsi.element.AbstractTower;
import fr.emac.gipsi.gsi.element.Partie;
import fr.emac.gipsi.gsi.element.TypeTower;
import fr.emac.gipsi.gsi.gui.couleurs.EchantillonCouleur;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;

public class InterfaceSimulation extends JFrame {

	private JPanel contentPane;
	public JPanel Ecran;
	private Partie pp;
	private Screen ecran=new Screen();
	private JTextField txtNbvie;
	private JTextField txtNbargent;
	private JTextField txtNiveau;
	private JPanel panel_1;
	private JTextField txtType;
	private JTextField txtRange;
	private JTextField txtPower;
	private JTextField txtCost;
	private String typeEnCours;

	//	/**
	//	 * Launch the application.
	//	 */
	//	public static void main(String[] args) {
	//		EventQueue.invokeLater(new Runnable() {
	//			public void run() {
	//				try {
	//					InterfaceSimulation frame = new InterfaceSimulation();
	//					frame.setVisible(true);
	//				} catch (Exception e) {
	//					e.printStackTrace();
	//				}
	//			}
	//		});
	//	}

	/**
	 * Create the frame.
	 */
	public InterfaceSimulation(Partie p) {
		this.pp = p;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 761, 551);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		Ecran = new JPanel();
		Ecran.setName("ecran");
		Ecran.setLayout(null);
		//		InitEcran();
		//		Ecran.addPropertyChangeListener(new PropertyChangeListener() {
		//			
		//			@Override
		//			public void propertyChange(PropertyChangeEvent arg0) {
		//				pp.setTowerEnCours((String)arg0.getNewValue());
		//				
		//			}
		//		});
		contentPane.add(Ecran);

		JPanel panel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setVgap(10);
		contentPane.add(panel, BorderLayout.SOUTH);

		txtNiveau = new JTextField();
		txtNiveau.setText("Niveau");
		panel.add(txtNiveau);
		txtNiveau.setColumns(10);

		txtNbvie = new JTextField();
		txtNbvie.setText("NbVie");
		panel.add(txtNbvie);
		txtNbvie.setColumns(10);

		txtNbargent = new JTextField();
		txtNbargent.setText("NbArgent");
		panel.add(txtNbargent);
		txtNbargent.setColumns(10);
		
		panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.NORTH);
		panel_1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		txtPower = new JTextField();
		txtPower.setText("Power");
		panel_1.add(txtPower);
		txtPower.setColumns(10);
		
		txtRange = new JTextField();
		txtRange.setText("Range");
		panel_1.add(txtRange);
		txtRange.setColumns(10);
		
		txtType = new JTextField();
		txtType.setText("Type");
		panel_1.add(txtType);
		txtType.setColumns(10);
		
		txtCost = new JTextField();
		txtCost.setText("Cost");
		panel_1.add(txtCost);
		txtCost.setColumns(10);
		
		JButton btnUpgrade = new JButton("upgrade");
		btnUpgrade.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				/** si une tour est sélectionnée */
				if (pp.getTowerEnCours() != null){
					pp.upgradeTower(pp.getTowerEnCours());
				}
			}
		});
		panel_1.add(btnUpgrade);

		 ButtonGroup group = new ButtonGroup();
		for( TypeTower at : TypeTower.values()){
			JRadioButton rdbtnTowertype = new JRadioButton(at.getName());
			rdbtnTowertype.addChangeListener(new ChangeListener() {

				@Override
				public void stateChanged(ChangeEvent e) {
					if(e.getSource() instanceof JRadioButton){
						JRadioButton radioButton = (JRadioButton) e.getSource();
						if(radioButton.isSelected()){
							typeEnCours=radioButton.getText();
						}
					}
				}
			});
			group.add(rdbtnTowertype);
			panel.add(rdbtnTowertype);
		}

	}

	private void InitEcran() {
		//valeur par d�faut 9*9
		ecran.setColMax(ecran.getColMax());
		ecran.setLigMax(ecran.getLigMax());
		for(int lig=0;lig<ecran.getLigMax();lig++){
			for(int col=0;col<ecran.getColMax();col++){
				updatePanel(lig,col,"AQUA");
			}
		}

	}

	private void updatePanel(int lig, int col,String nomCouleur) {

		int total=lig*ecran.getColMax()+col;
		//		System.out.println("ecran.getRectangles().size() ="+ecran.getRectangles().size()+"  lig*ecran.getColMax()+col="+total+" lig="+lig+" ecran.colmax="+ecran.getColMax()+" col="+col);

		if(ecran.getRectangles().size()>total){
			ecran.updateColorByXY(lig,col,new VisualRectangle(lig,col,nomCouleur));
		}else{
			creationEcran(nomCouleur);
		}

	}

	private void creationEcran(String nomCouleur) {

		for(int lig=0;lig<ecran.getLigMax()+1;lig++){
			for(int col=0;col<ecran.getColMax()+1;col++){
				ecran.getRectangles().add(new VisualRectangle(lig,col,nomCouleur));
				Ecran.add(creation1CaseCouleur(lig,col,nomCouleur));
			}
		}
	}

	private JPanel creation1CaseCouleur(final int lig, final int col,String nomCouleur) {

		int h=(400-ecran.getLigMax())/(ecran.getLigMax()+1);
		int w=(400-ecran.getColMax())/(ecran.getColMax()+1);

		JPanel p = new JPanel();
		p.setName(lig+"_"+col);
		p.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		p.setBounds(col*w, lig*h, w, h);

		//		((FlowLayout)p.getLayout()).setVgap(vgap/2);
		//		((FlowLayout)p.getLayout()).setHgap(hgap/2);
		p.setBackground(EchantillonCouleur.getColorByName(nomCouleur));


		p.addPropertyChangeListener(new PropertyChangeListener() {

			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				((JPanel)evt.getSource()).revalidate();
				((JPanel)evt.getSource()).repaint();
				//				System.out.println("ici2 :" + ((JPanel)evt.getSource()).getName() + " "+((JPanel)evt.getSource()).getBackground());
			}
		});
		p.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getSource() instanceof JPanel){
					if(((JPanel)e.getSource()).getBackground().equals(EchantillonCouleur.getColorByName("Tan"))){
						//on créer une nouvelle tower
						pp.addTower(typeEnCours,lig,col);
						
					}else if(((JPanel)e.getSource()).getBackground().equals(EchantillonCouleur.getColorByName("White"))){
						//on est sur le chemin donc rien
					}else if(((JPanel)e.getSource()).getBackground().equals(EchantillonCouleur.getColorByName("Aqua"))){
						//on est sur le chemin donc rien
					}else{
						//on doit upgrade tower
						AbstractTower at=pp.getInfoTower(lig,col);
						
						if(at!=null){
							pp.setTowerEnCours(at);
							txtCost.setText(Integer.toString(at.getCostUpgrade()));
							txtPower.setText(Integer.toString(at.getPower()));
							txtRange.setText(Integer.toString(at.getRange()));
							txtType.setText(at.getType());
						}
					}
				}
				
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
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
		});

		return p;
	}

	public void changeEcran() {

		//		Ecran.removeAll();
		//		Ecran = new JPanel();
		//		Ecran.setName("ecran");
		//		Ecran.setBounds(5, 5, 494, 450);
		//		Ecran.setLayout(null);

		//	ecran.getRectangles()//.clear();
		//		ecran.setColMax(ec.getColMax());
		//		ecran.setLigMax(ec.getLigMax());
		//			for(int lig=0;lig<ecran.getLigMax()+1;lig++){
		//				for(int col=0;col<ecran.getColMax()+1;col++){
		//					updatePanel(lig,col,ec.getColorByXY(lig, col).getNomCouleur());

		this.txtNbargent.setText(Integer.toString(pp.getArgent()));
		this.txtNbvie.setText(Integer.toString(pp.getNbVie()));
		this.txtNiveau.setText(Integer.toString(pp.getNbVag()));
		ecran.setColMax(pp.getEcran().getColMax());
		ecran.setLigMax(pp.getEcran().getLigMax());
		ecran.setRectangles(new ArrayList<VisualRectangle>());
		//		for(Component p :Ecran.getComponents()){
		//			Ecran.remove(p);
		//		}

		for(int lig=0;lig<pp.getEcran().getLigMax();lig++){
			for(int col=0;col<pp.getEcran().getColMax();col++){
				updatePanel(lig,col,pp.getEcran().getColorByXY(lig, col).getNomCouleur());
			}
		}


		for(Component p :Ecran.getComponents()){
			if(p instanceof JPanel){
				String[] coord = p.getName().split("_");
				((JPanel)p).setBackground(EchantillonCouleur.getColorByName(pp.getEcran().getColorByXY(Integer.parseInt(coord[0]),Integer.parseInt(coord[1])).getNomCouleur()));
			}
		}
		//				}
		//			}


	}
	//
	//	public void refresh(Ecran ec) {
	//		//this.setVisible(false);
	//		changeEcran(ec);
	//		//this.setVisible(true);
	//		for( Component p : this.rootPane.getComponents()){
	//			if( p instanceof JPanel){
	//				if(!(p.getName()==null)){
	//				if (p.getName().equals("ecran")){
	//					p.validate();
	//					p.repaint();
	//				}
	//			}
	//		}
	//	}
	//		this.rootPane.add(Ecran);
	//	}


	public void addListener(){

	}
}
