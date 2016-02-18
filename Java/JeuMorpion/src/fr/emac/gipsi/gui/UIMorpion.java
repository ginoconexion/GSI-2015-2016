package fr.emac.gipsi.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import fr.emac.gipsi.gsi.element.IJeuMorpion;
import fr.emac.gipsi.gsi.element.PartieMorpion;

import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class UIMorpion extends JFrame {

	private JPanel contentPane;
	private JTextField txtJoueurEncours;
	private IJeuMorpion jeu;
	
	private JButton button11;
	private JButton button12;
	private JButton button13;
	
	private JButton button21;
	private JButton button22;
	private JButton button23;
	
	private JButton button31;
	private JButton button32;
	private JButton button33;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PartieMorpion game = new PartieMorpion();
					UIMorpion frame = new UIMorpion(game);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public UIMorpion(IJeuMorpion game) {
		this.jeu = game;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 570, 368);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		panel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
			}
		});
		panel.setBackground(Color.CYAN);
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JButton btnNouvellePartie = new JButton("Nouvelle partie");
		btnNouvellePartie.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				jeu.init();
				button11.setText("");
				button12.setText("");
				button13.setText("");
				button21.setText("");
				button22.setText("");
				button23.setText("");
				button31.setText("");
				button32.setText("");
				button33.setText("");
			}
		});
		btnNouvellePartie.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnNouvellePartie.setBounds(378, 11, 143, 23);
		panel.add(btnNouvellePartie);
		
		txtJoueurEncours = new JTextField();
		txtJoueurEncours.setText("joueurEncours");
		txtJoueurEncours.setBounds(377, 45, 112, 20);
		panel.add(txtJoueurEncours);
		txtJoueurEncours.setColumns(10);
		
		button11 = new JButton("11");
		button11.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				joue(1, 1);
			}
		});
		button11.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		button11.setBounds(10, 11, 80, 80);
		panel.add(button11);
		
		button12 = new JButton("12");
		button12.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				joue(1, 2);
			}
		});
		button12.setBounds(94, 11, 80, 80);
		panel.add(button12);
		
		button13 = new JButton("13");
		button13.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				joue(1, 3);
			}
		});
		button13.setBounds(179, 11, 80, 80);
		panel.add(button13);
		
		button21 = new JButton("21");
		button21.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				joue(2, 1);
			}
		});
		button21.setBounds(10, 102, 80, 80);
		panel.add(button21);
		
		button22 = new JButton("22");
		button22.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				joue(2, 2);
			}
		});
		button22.setBounds(94, 102, 80, 80);
		panel.add(button22);
		
		button23 = new JButton("23");
		button23.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				joue(2, 3);
			}
		});
		button23.setBounds(179, 102, 80, 80);
		panel.add(button23);
		
		button31 = new JButton("31");
		button31.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				joue(3, 1);
			}
		});
		button31.setBounds(10, 190, 80, 80);
		panel.add(button31);
		
		button32 = new JButton("32");
		button32.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				joue(3, 2);
			}
		});
		button32.setBounds(94, 190, 80, 80);
		panel.add(button32);
		
		button33 = new JButton("33");
		button33.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				joue(3, 3);
			}
		});
		button33.setBounds(179, 190, 80, 80);
		panel.add(button33);
	}
	
	private void joue(int lig, int col){
		if (!jeu.isSymbole(lig, col)){
			jeu.setSymbole(lig, col, jeu.getJoueurEnCours());
			getButton(lig,col).setText(jeu.getSymbole(lig, col));
			jeu.joueurSuivant();
			txtJoueurEncours.setText(jeu.getJoueurEnCours());
		}
	}
	private JButton getButton(int lig, int col){
		if (lig == 1 && col == 1)
			return button11;
		else if (lig == 1 && col == 2)
			return button12;
		else if (lig == 1 && col == 3)
			return button13;
		
		else if (lig == 2 && col == 1)
			return button21;
		else if (lig == 2 && col == 2)
			return button22;
		else if (lig == 2 && col == 3)
			return button23;
		
		else if (lig == 3 && col == 1)
			return button31;
		else if (lig == 3 && col == 2)
			return button32;
		else
			return button33;
	}
	
}
