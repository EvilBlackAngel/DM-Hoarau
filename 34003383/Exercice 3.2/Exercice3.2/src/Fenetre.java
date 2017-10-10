import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;


public class Fenetre extends JFrame {
	private JButton Start = new JButton("Start");
	private JButton Stop = new JButton("Stop");
	private JButton Plus = new JButton("+");
	private JButton Moins = new JButton("-");
	private JButton Quitter = new JButton("Quitter");
	private JPanel boutons = new JPanel();
	private Panneau panneau = new Panneau();



	
	public Fenetre() {
		super("balles en mouvements");
		panneau.setBackground(Color.WHITE); //modifie la couleur du fond 
		add(panneau); //ajoute le fond 
		add(boutons, BorderLayout.SOUTH);
		boutons.add(Start);
		boutons.add(Moins);
		boutons.add(Quitter);
		boutons.add(Plus);
		

	      /* Lors du clic sur le bouton Start */
			Start.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					String texte;
					texte=Start.getText();
					if(texte.compareTo("Start")==0)
					{
						Start.setText("Stop ");
						ajoutBalle();
					}
					else if(texte.compareTo("Stop ")==0)
					{
						Start.setText("Start");
						pauseBalle();;
					}
				}
			});
			/* Lors du clic sur le bouton Stop */
			Stop.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					String texte;
					texte=Start.getText();
					if(texte.compareTo("Start")==0)
					{
						ajoutBalle();

					}
				}
			});
	      Quitter.addActionListener(new ActionListener() {
	          public void actionPerformed(ActionEvent e) {
	             System.exit(0);
	          }
	       });
	      
	      Plus.addActionListener(new ActionListener() {
	          public void actionPerformed(ActionEvent e) {
	             ajoutBalle();
	          }
	       });
		
		setSize(500, 500);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);

		}
	    void ajoutBalle() {
		      Balle balle = new Balle();      
		      panneau.ajout(balle);
		      new Thread(new BalleS(balle)).start();
		   }
		   	
		  void pauseBalle() {  
			   panneau.pause();

			   }
}
