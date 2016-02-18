package fr.emac.gipsi.gsi.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import fr.emac.gipsi.gsi.element.Grille;

public class UIDemineur {

	private JFrame frame;
	private JTextField textFieldLignes;
	private JTextField textFieldColonnes;
	private JTextField textFieldBombes;
	private Grille grille;
	
	private JPanel panel_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIDemineur window = new UIDemineur();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public UIDemineur() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 789, 538);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.CYAN);
		panel.setForeground(Color.WHITE);
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		textFieldLignes = new JTextField();
		textFieldLignes.setBounds(90, 11, 86, 20);
		panel.add(textFieldLignes);
		textFieldLignes.setColumns(10);
		
		textFieldColonnes = new JTextField();
		textFieldColonnes.setColumns(10);
		textFieldColonnes.setBounds(306, 11, 53, 20);
		panel.add(textFieldColonnes);
		
		textFieldBombes = new JTextField();
		textFieldBombes.setColumns(10);
		textFieldBombes.setBounds(493, 11, 47, 20);
		panel.add(textFieldBombes);
		
		JLabel lblNewLabel = new JLabel("Nb de lignes");
		lblNewLabel.setBounds(21, 14, 64, 14);
		panel.add(lblNewLabel);
		
		JLabel lblNbDeColonnes = new JLabel("Nb de colonnes");
		lblNbDeColonnes.setBounds(186, 14, 86, 14);
		panel.add(lblNbDeColonnes);
		
		JLabel lblNbDeBombes = new JLabel("Nb de bombes");
		lblNbDeBombes.setBounds(415, 14, 86, 14);
		panel.add(lblNbDeBombes);
		
		JButton btnNew = new JButton("New");
		btnNew.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				panel.setVisible(false);
				grille = new Grille(Integer.parseInt(textFieldLignes.getText()), Integer.parseInt(textFieldColonnes.getText()), Integer.parseInt(textFieldColonnes.getText()));
				initialiserGrilleGraphique();
				panel.setVisible(true);
			}
		});
		btnNew.setBounds(580, 10, 53, 23);
		panel.add(btnNew);
		
		panel_1 = new JPanel();
		panel_1.setBounds(21, 42, 720, 446);
		panel.add(panel_1); 
		panel_1.setLayout(null);
		
		
	}
	
	public void initialiserGrilleGraphique(){
		
		for (int lig = 0; lig < grille.getLigne(); lig++) {
			for (int col = 0; col < grille.getColonne(); col++) {
				
				final JButton button = new JButton("");
				final int ligf = lig;
				final int colf = col;
				button.addMouseListener(new MouseAdapter() { 	
					@Override
					public void mouseClicked(MouseEvent arg0) {
						if (grille.getCase(ligf, colf).isBombe()){
							button.setText("X");
						}
						else {
							System.out.println(grille.getCase(ligf, colf).getNbBombeAlentour());
							button.setText(Integer.toString(grille.getCase(ligf, colf).getNbBombeAlentour()));
						}
						/*
						if (!grille.isDefaite()){
							grille.decouvrir1Case(ligf, colf);
							if (grille.getCase(ligf, colf).isBombe()){
								button.setText("X");
							}
							else {
								System.out.println(grille.getCase(ligf, colf).getNbBombeAlentour());
								button.setText("1");;
								grille.isVictory();
							}
						}*/
					}
				});
				button.setBounds(50 * col,50 * lig, 50, 50);
				panel_1.add(button);
			}
		}
	}
}
