package tp1;

import java.util.*;
import java.util.stream.Collectors;

public class BaseShape {
    // Vous aller apprendre plus en details le fonctionnement d'un Set lors
    // du cours sur les fonctions de hashage, vous pouvez considerez ceci comme une liste normale.
    private Set<Point2d> coords;

    // TODO Initialiser les points.
    public BaseShape() {
        Set<Point2d> coords= new HashSet<Point2d>();
    }

    // TODO prendre une liste de points et creer une nouvelle forme.
    public BaseShape(Collection<Point2d> coords) {
    //this.coords=coords;
    }

    // TODO ajouter ou retirer des coordonnees a la liste de points.
    public void add(Point2d coord) {
        this.coords.add(coord);
    }
    public void add(BaseShape shape) {
        this.add(shape);
    }
    public void addAll(Collection<Point2d> coords) {
        this.addAll(coords);
    }
    public void remove(Point2d coord) {
      this.coords.remove(coord);
    }
    public void remove(BaseShape shape) {
        this.remove(shape);
    }
    public void removeAll(Collection<Point2d> coords) {
        this.removeAll(coords);
    }

    // TODO retourne les coordonnees de la liste.
    public Set<Point2d> getCoords() {
        return this.coords;
    }

    // TODO appliquer la translation sur la forme.
    public BaseShape translate(Point2d point) {
    //    this.coords= new Point2d(this.coords.X()+point.X(),this.coords.Y()+point.Y());
        return this;
    }

    // TODO appliquer la translation sur la liste.
    public Set<Point2d> translateAll(Point2d point) {
    //    this.coords= new Point2d(this.coords.X()+point.X(),this.coords.Y()+point.Y());
        return this.coords;
    }

    // TODO appliquer la rotation sur la forme.
    public BaseShape rotate(Double angle) {
    /*    Double XRotation=Math.cos(angle)*this.X()+sin(angle)*this.Y();
        Double YRotation=Math.sin(angle)*this.X()+cos(angle)*this.Y();
        this.coords.vecteur[0]=XRotation;
        this.coords.vecteur[1]=YRotationl;*/
        return this;
    }

    // TODO appliquer la rotation sur la liste.
    public Set<Point2d> rotateAll(Double angle) {
        /*Double XRotation=Math.cos(angle)*+sin(angle)*;
        Double YRotation=Math.sin(angle)*+cos(angle)*;
        this.coords.vecteur[0]=XRotation;
        this.coords.vecteur[1]=YRotationl;*/
        return this.coords;
    }

    // TODO retourner une nouvelle forme.
    public BaseShape clone() {
        return  new BaseShape(this.coords);
    }
}
