package tp1;

import java.util.HashSet;
import java.util.Set;

public class Rectangle extends BaseShape {
    // TODO creer un rectangle avec une largeur et une longueur.
    public Rectangle(Double width, Double height) {
       Point2d point = new Point2d(width,height);
        Set coords= new HashSet<Point2d>();
        coords.add(point);
       new Rectangle(coords);

    }

    // TODO creer un rectangle avec un point contenant la largeur et longueur.
    public Rectangle(Point2d dimensions) {
         Set coords= new HashSet<Point2d>();
          coords.add(dimensions);
        new Rectangle(coords);



    }

    private Rectangle(Set<Point2d> coords) {
        super(coords);
    }

    // TODO appliquer la translation sur la forme.
    @Override
    public Rectangle translate(Point2d point) {
        return new Rectangle(translateAll(point));
    }

    // TODO appliquer la rotation sur la forme.
    @Override
    public Rectangle rotate(Double angle) { return new Rectangle(rotateAll(angle)); }

    // TODO retourner une nouvelle forme.
    @Override
    public Rectangle clone() {return  new Rectangle(getCoords()); }
}
