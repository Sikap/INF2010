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
        Point2d point = new Point2d(widthRadius,heightRadius);
        Ge
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
