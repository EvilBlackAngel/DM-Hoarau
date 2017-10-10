
public class BalleS implements Runnable {
      private Balle balle;
      private Panneau panneau = new Panneau();
 
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