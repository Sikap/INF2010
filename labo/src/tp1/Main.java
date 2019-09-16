package tp1;

import org.omg.CORBA.SystemException;
import tests.Tester;

import java.awt.*;
import java.io.File;

public class Main {

    public static void main(String[] args) throws Exception {
        System.out.println("Bienvenue au premier labo de INF2010!");
        if (Tester.runner("All", Tester::start, 20.0) >= 20.0) {
            LetterPlacer letterPlacer = new LetterPlacer();
            letterPlacer.placeNext('H');
            letterPlacer.placeNext('e');
            letterPlacer.placeNext('l');
            letterPlacer.placeNext('l');
            letterPlacer.placeNext('o');
            letterPlacer.placeNextln('W');
            letterPlacer.placeNext('o');
            letterPlacer.placeNext('r');
            letterPlacer.placeNext('l');
            letterPlacer.placeNext('d');
            File imgFile = letterPlacer.saveImage("image", false);
            Desktop dt = Desktop.getDesktop();
            dt.open(imgFile);
            System.out.println("Bravo!");
        }
        else {
            System.out.println("Hello World!");
        }
        try{LetterPlacer letterPlacer = new LetterPlacer();
            letterPlacer.placeNext('H');
            letterPlacer.placeNext('W');
            letterPlacer.placeNextln('W');
            letterPlacer.placeNext('l');
            File imgFile = letterPlacer.saveImage("w", false);
            Desktop dt = Desktop.getDesktop();
            dt.open(imgFile);
            System.out.println("Bravo!");
        }catch (SystemException e){

            System.out.println("Erreur");
        }
    }
}