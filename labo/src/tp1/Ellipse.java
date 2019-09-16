package tp1;

import java.util.HashSet;
import java.util.Set;

public class Ellipse extends BaseShape {
    // TODO creer une ellipse avec une largeur et une longueur.
    public Ellipse(Double widthRadius, Double heightRadius) {
        this(init(widthRadius, heightRadius));
    }

    static private Set<Point2d> init(Double widthRadius, Double heightRadius) {
        Set<Point2d> coords = new HashSet<Point2d>();
        for(double i=-widthRadius;i<widthRadius;i++)
            for (double j=-heightRadius;j<heightRadius;j++){
                if((Math.pow(i,2.0)/Math.pow(widthRadius,2.0))+(Math.pow(j,2.0)/Math.pow(heightRadius,2.0))<=1)
                    coords.add(new Point2d(i,j));
            }
        return coords;
    }

    private Ellipse(Set<Point2d> coords) {
        super(coords);
    }

    // TODO appliquer la translation sur la forme.
    @Override
    public Ellipse translate(Point2d point) {
        return new Ellipse(translateAll(point));
    }

    // TODO appliquer la rotation sur la forme.
    @Override
    public Ellipse rotate(Double angle) {
        return new Ellipse(rotateAll(angle));
    }

    // TODO retourner une nouvelle forme.
    @Override
    public Ellipse clone() { return new Ellipse(getCoords()); }
}
