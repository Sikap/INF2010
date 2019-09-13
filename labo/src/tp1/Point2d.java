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
        super(new Double[] {vector[0],vector[1]});
    }

    public Double X() { return vector[X];}
    public Double Y() { return vector[Y];}

    // TODO prendre un vecteur de donnees et appliquer la translation.
    @Override
    public Point2d translate(Double[] translateVector) {
        Double xTranslation=this.X()+translateVector[X];
        Double yTranslation=this.Y()+translateVector[Y];
        return new Point2d(xTranslation,yTranslation);
    }

    // TODO prendre un point et appliquer la translation.
    public Point2d translate(Point2d translateVector) {
        Double xTranslation=this.X()+translateVector.X();
        Double yTranslation=this.Y()+translateVector.Y();
        return new Point2d(xTranslation,yTranslation);
    }

    // TODO prendre un vecteur de donnees et appliquer la translation.
    @Override
    public Point2d rotate(Double[][] rotationMatrix) {
        Double xRotate=rotationMatrix[X][X]*this.X()-rotationMatrix[X][Y]*this.Y();
        Double yRotate=rotationMatrix[Y][X]*this.X()+rotationMatrix[Y][Y]*this.Y();
        return new Point2d(xRotate,yRotate);
    }

    // TODO prendre un angle de rotation, creer une matrice et appliquer la rotation.
    public Point2d rotate(Double angle) {
        Double xRotate=this.X()*Math.cos(angle)-this.Y()*Math.sin(angle);
        Double yRotate=this.X()*Math.sin(angle)+this.Y()*Math.cos(angle);
        return new Point2d(xRotate,yRotate);
    }

    // TODO prendre un facteur de division et l'appliquer.
    @Override
    public Point2d divide(Double divider) {
        Double xDiv=X()/divider;
        Double yDiv=Y()/divider;
        return new Point2d(xDiv,yDiv);
    }

    // TODO prendre un facteur de multiplication et l'appliquer.
    @Override
    public Point2d multiply(Double multiplier) {
        Double xMul=X()*multiplier;
        Double yMul=Y()*multiplier;
        return new Point2d(xMul,yMul);
    }

    // TODO prendre un facteur d'addition et l'appliquer.
    @Override
    public Point2d add(Double adder) {
        Double xAdd=X()+adder;
        Double yAdd=Y()+adder;
        return new Point2d(xAdd,yAdd);
    }

    // TODO creer un nouveau point.
    @Override
    public Point2d clone() {
        return new Point2d(this.vector);
    }
}
