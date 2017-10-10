package balle;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.util.ArrayList;
import javax.swing.*;
import java.util.Random;
import java.util.concurrent.ExecutorService;

 
public class BalleMov extends JFrame {
   private JButton Lancer = new JButton("Start");
   private JButton Quitter = new JButton("Quitter");
   private JButton Stop = new JButton("Stop");
   private JButton Moins = new JButton("-");
   private JButton Plus = new JButton("+");
   private JPanel boutons = new JPanel();
   private Panneau panneau = new Panneau();
 
   public BalleMov() {
      super("Balles en mouvements");
      panneau.setBackground(Color.WHITE);
      add(panneau);
      add(boutons, BorderLayout.SOUTH);
      boutons.add(Lancer);
      boutons.add(Quitter);
      boutons.add(Moins);
      boutons.add(Plus);

      /* Lors du clic sur le bouton debut */
		Lancer.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				String texte;
				texte=Lancer.getText();
				if(texte.compareTo("Start")==0)
				{
					Lancer.setText("Stop ");
					ajoutBalle();
				}
				else if(texte.compareTo("Stop ")==0)
				{
					Lancer.setText("Start");
					pauseBalle();;
				}
			}
		});
		/* Lors du clic sur le bouton fin */
		Stop.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				String texte;
				texte=Lancer.getText();
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
      
      Moins.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent e) {
        	retireBalle();
          }
       });

 
      setSize(500, 500);
      setLocationRelativeTo(null);
      setDefaultCloseOperation(EXIT_ON_CLOSE);
      setVisible(true);
   }
 
   private void ajoutBalle() {
      Balle balle = new Balle();      
      panneau.ajout(balle);
      new Thread(new BalleS(balle)).start();
   }
   	
   private void pauseBalle() {  
	   panneau.pause();

	   }
   private void retireBalle() {  
	   panneau.retire();

	   }

 
   
   
   private class BalleS implements Runnable {
      private Balle balle;
 
      public BalleS(Balle balle) {
          this.balle = balle;
      }
 
      public void run() {
         try {
            while(true){
               balle.deplace(panneau.getBounds());
               panneau.repaint();
               Thread.sleep(10);
            }
         }
         catch (InterruptedException ex) { }
      }
   }
 
   private class Panneau extends JPanel {

      private ArrayList<Balle> balles = new ArrayList<Balle>();
	  
	  private void pause() {
		    balles.clear();
		    }
		
	
      public void ajout(Balle balle) {
         balles.add(balle);
 
      }
      public void retire(){
    	  balles.remove(0);
      }
 
 
      @Override
      protected void paintComponent(Graphics g) {
    	 int R = (int)(Math.random()*256);
    	 int G = (int)(Math.random()*256);
    	 int B = (int)(Math.random()*256);
    	 Random rand = new Random();
    	 Color randomColor = new Color(R,G,B);
         super.paintComponent(g);
         Graphics2D surface = (Graphics2D) g;
         g.setColor(randomColor);
         for (Balle balle : balles) surface.fill(balle.getForme());
 
      }
   }
 
   private class Balle {
      private double x, y, dx=5, dy=5;
 
 
      public void deplace(Rectangle2D zone) {
         x+=dx;
         y+=dy;
         if (x < zone.getMinX()) { x = zone.getMinX();  dx = -dx; }
         if (x+15 >= zone.getMaxX()) { x = zone.getMaxX() - 15;  dx = -dx; }
         if (y < zone.getMinY()) { y = zone.getMinY();  dy = -dy; }
         if (y+15 >= zone.getMaxY()) { y = zone.getMaxY() - 15;  dy = -dy; }
      }
 
      public Ellipse2D getForme() {
         return new Ellipse2D.Double(x, y, 30, 30);
      }
   }
 
   public static void main(String[] args) { new BalleMov(); }
}
