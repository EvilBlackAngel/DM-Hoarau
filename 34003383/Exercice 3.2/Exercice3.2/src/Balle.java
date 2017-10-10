import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;



public class Balle {
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
         return new Ellipse2D.Double(x, y, 40, 40);
      }
   }
 
