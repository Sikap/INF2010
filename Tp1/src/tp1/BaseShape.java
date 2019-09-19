package tp1;

import java.util.*;
import java.util.stream.Collectors;

public class BaseShape {
    // Vous aller apprendre plus en details le fonctionnement d'un Set lors
    // du cours sur les fonctions de hashage, vous pouvez considerez ceci comme une liste normale.
    private Set<Point2d> coords;

    // TODO Initialiser les points.
    public BaseShape() {
       coords= new HashSet<Point2d>();
    }

    // TODO prendre une liste de points et creer une nouvelle forme.
    public BaseShape(Collection<Point2d> coords) {
        this.coords= new HashSet<Point2d>();
       this.coords.addAll(coords);
    }


    // TODO ajouter ou retirer des coordonnees a la liste de points.
    public void add(Point2d coord) {this.coords.add(coord); }
    public void add(BaseShape shape) {
        this.addAll(shape.coords);
    }
    public void addAll(Collection<Point2d> coords) { this.coords.addAll(coords); }
    public void remove(Point2d coord) { this.coords.remove(coord); }
    public void remove(BaseShape shape) {
        this.removeAll(shape.coords);
    }
    public void removeAll(Collection<Point2d> coords) { this.coords.removeAll(coords); }

    // TODO retourne les coordonnees de la liste.
    public Set<Point2d> getCoords() {
        Set<Point2d> Coords= new HashSet<Point2d>(this.coords);
        return Coords;
    }

    // TODO appliquer la translation sur la forme.
    public BaseShape translate(Point2d point) {
        Iterator<Point2d> itr=this.coords.iterator();
        Set<Point2d> Point= new HashSet<Point2d>() ;
        while (itr.hasNext()) {
            Point.add(itr.next().translate(point));
        }

        return new BaseShape(Point);
    }

    // TODO appliquer la translation sur la liste.
    public Set<Point2d> translateAll(Point2d point) {
        Iterator<Point2d> itr=this.coords.iterator();
        Set<Point2d> Point= new HashSet<Point2d>() ;
        while (itr.hasNext()) {
            Point.add(itr.next().translate(point));
        }
        return Point;
    }

    // TODO appliquer la rotation sur la forme.
    public BaseShape rotate(Double angle) {
        Iterator<Point2d> itr=this.coords.iterator();
        Set<Point2d> Point= new HashSet<Point2d>() ;
        while (itr.hasNext()) {
            Point.add(itr.next().rotate(angle));
        }
        return new BaseShape(Point);
    }

    // TODO appliquer la rotation sur la liste.
    public Set<Point2d> rotateAll(Double angle) {
        Iterator<Point2d> itr=this.coords.iterator();
        Set<Point2d> Point= new HashSet<Point2d>() ;
        while (itr.hasNext()) {
            Point.add(itr.next().rotate(angle));
        }
        return Point;
    }

    // TODO retourner une nouvelle forme.
    public BaseShape clone() {
        return  new BaseShape(this.coords);
    }
}
