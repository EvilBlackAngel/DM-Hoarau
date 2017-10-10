import java.awt.*;
import java.util.*;
import java.util.concurrent.*;

import javax.swing.*;



public class Panneau extends JPanel {
	private ArrayList<Balle> balles = new ArrayList<Balle>();
	private ExecutorService exécuteur;

	
	 public void pause() {
	    //balles.clear();
		if (exécuteur != null) {
			exécuteur.shutdownNow();
			exécuteur = null;
		    balles.clear();
		    }
	    }
	
	 public void ajout(Balle balle) {
        balles.add(balle);
     	}
	
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
	