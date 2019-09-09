package tp1;

import java.util.*;

public final class PointOperator {
    // TODO appliquer la translation sur le vecteur d'entree.
    public static Double[] translate(Double[] vector, Double[] translateVector) {
        int lengthVector=vector.length;
        Double[] tramslte =new Double[lengthVector];
        for (int i=0;i<lengthVector;i++){
            tramslte[i]=vector[i]+translateVector[i];
        }
        return tramslte;
    }

    // TODO appliquer la rotation sur le vecteur d'entree.
    public static Double[] rotate(Double[] vector, Double[][] rotationMatrix) {
    	Double[] tramslte =new Double[3];
    	for(int i=0;i<3;i++){tramslte[i]=0.0;}
    	for(int i=0;i<3;i++){
    		for(int j=0;j<3;j++){
    			tramslte[i]=tramslte[i]+(vector[j]*rotationMatrix[i][j]);
    		}
    	}
        return tramslte;
    }
        
    // TODO appliquer le facteur de division sur le vecteur d'entree.
    public static Double[] divide(Double[] vector, Double divider) {
    	int lengthVector=vector.length;
    	Double[] tramslte =new Double[lengthVector];
    	for(int i=0;i<lengthVector;i++){
    		tramslte[i]=vector[i]/divider;
    	}
        return tramslte;
    }

    // TODO appliquer le facteur de multiplication sur le vecteur d'entree.
    public static Double[] multiply(Double[] vector, Double multiplier) {
    	int lengthVector=vector.length;
    	Double[] tramslte =new Double[lengthVector];
    	for(int i=0;i<lengthVector;i++){
    		tramslte[i]=vector[i]*multiplier;
    	}
        return tramslte;
    }

    // TODO appliquer le facteur d'addition sur le vecteur d'entree.
    public static Double[] add(Double[] vector, Double adder) {
    	int lengthVector=vector.length;
    	Double[] tramslte =new Double[lengthVector];
    	for(int i=0;i<lengthVector;i++){
    		tramslte[i]=vector[i]+adder;
    	}
        return tramslte;
    }

    // TODO retourne la coordonnee avec les plus grandes valeurs en X et en Y.
    public static Point2d getMaxCoord(Collection<Point2d> coords) {
        Point2d MaxCoord=coords.iterator().next();
        for (Point2d listCoords: coords) {
            if(MaxCoord.X()<listCoords.X())
                MaxCoord=new Point2d(listCoords.X(),MaxCoord.Y());
            if (MaxCoord.Y()<listCoords.Y())
                MaxCoord=new Point2d(MaxCoord.X(),listCoords.Y());
        }
        return MaxCoord;
    }

    // TODO retourne la coordonnee avec les plus petites valeurs en X et en Y.
    public static Point2d getMinCoord(Collection<Point2d> coords) {
       Point2d MinCoord = coords.iterator().next();
       for (Point2d listCoord: coords){
           if(MinCoord.X()>listCoord.X())
               MinCoord=new Point2d(listCoord.X(),MinCoord.Y());
           if (MinCoord.Y()>listCoord.Y())
               MinCoord=new Point2d(MinCoord.X(),listCoord.Y());
       }
        return MinCoord;
    }
}
