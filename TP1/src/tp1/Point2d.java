package tp1;

public class Point2d extends AbstractPoint {
    private final Integer X = 0;
    private final Integer Y = 1;

    // TODO creer un point en 2d avec 2 donnees
    public Point2d(Double x, Double y) {
        super(new Double[] {x,y});
    }

    // TODO creer un point a partir d'un vecteur de donnees
    public Point2d(Double[] vector) {
        super(vector);
    }

    public Double X() { return vector[X];}
    public Double Y() { return vector[Y];}

    // TODO prendre un vecteur de donnees et appliquer la translation.
    //a veriffier plus tart si return un new point est une erreur
    @Override
    public Point2d translate(Double[] translateVector) {
        vector[X]=this.X()+translateVector[X];
        vector[Y]=this.Y()+translateVector[Y];
        return this;
    }

    // TODO prendre un point et appliquer la translation.
    //a veriffier plus tart si return un new point est une erreur
    public Point2d translate(Point2d translateVector) {
        vector[X]=this.X()+translateVector.X();
        vector[Y]=this.Y()+translateVector.Y();
        return this;
    }

    // TODO prendre un vecteur de donnees et appliquer la translation.
    @Override
    public Point2d rotate(Double[][] rotationMatrix) {
        Double xTranslation=rotationMatrix[0][0]*this.X()-rotationMatrix[0][1]*this.Y();
        Double yTranslation=rotationMatrix[1][0]*this.X()+rotationMatrix[1][1]*this.Y();
        vector[X]=xTranslation;
        vector[Y]=yTranslation;
        return this;
    }

    // TODO prendre un angle de rotation, creer une matrice et appliquer la rotation.
    public Point2d rotate(Double angle) {
        Double xTranslation=Math.cos(angle)*this.X()-Math.sin(angle)*this.Y();
        Double yTranslation=Math.sin(angle)*this.X()+Math.cos(angle)*this.Y();
        vector[X]=xTranslation;
        vector[Y]=yTranslation;
        return this;
    }

    // TODO prendre un facteur de division et l'appliquer.
    @Override
    public Point2d divide(Double divider) {
        vector[X]=X()/divider;
        vector[Y]=Y()/divider;
        return this;
    }

    // TODO prendre un facteur de multiplication et l'appliquer.
    @Override
    public Point2d multiply(Double multiplier) {
        vector[X]=X()*multiplier;
        vector[Y]=Y()*multiplier;
        return this;
    }

    // TODO prendre un facteur d'addition et l'appliquer.
    @Override
    public Point2d add(Double adder) {
        vector[X]=X()+adder;
        vector[Y]=Y()+adder;
        return this;
    }

    // TODO creer un nouveau point.
    @Override
    public Point2d clone() {
        return new Point2d(this.vector);
    }
}
