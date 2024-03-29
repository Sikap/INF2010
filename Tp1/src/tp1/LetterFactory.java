package tp1;

public final class LetterFactory {
    final static Double maxHeight = 200.0;
    final static Double maxWidth = maxHeight / 2;
    final static Double halfMaxHeight = maxHeight / 2;
    final static Double halfMaxWidth = maxWidth / 2;
    final static Double stripeThickness = maxHeight / 10;

    // TODO
    public static BaseShape create_H()
    {
        Double degrees90 = Math.toRadians(90);
        Double degrees0 = Math.toRadians(0);
        Double spacing = stripeThickness * 2;
        BaseShape mainStripe = new Rectangle(stripeThickness, maxHeight);
        BaseShape middlStripe = new Rectangle(stripeThickness,spacing*2);
        BaseShape leftStripe = mainStripe.rotate(degrees0).translate(new Point2d(-spacing, 0.0));
        BaseShape middleStripe=middlStripe.rotate(degrees90).translate(new Point2d(spacing, maxWidth));
        BaseShape rightStripe = mainStripe.rotate(degrees0).translate(new Point2d(spacing, 0.0));
        leftStripe.add(middleStripe);
        leftStripe.add(rightStripe);
        return leftStripe;

    }

    // TODO
    public static BaseShape create_e() {
        Double degrees90 = Math.toRadians(90);
        Double hSpacing = stripeThickness * 4;
        Double wSpacing =stripeThickness * 2;
        BaseShape mainStripe = new Ellipse(wSpacing,hSpacing);
        BaseShape centreStripe= new Ellipse(wSpacing,hSpacing*5/6);
        BaseShape bare = new Rectangle(stripeThickness,hSpacing);
        BaseShape morsoSurper= bare.translate(new Point2d(30.0,0.0));
        mainStripe.removeAll(centreStripe.getCoords());
        mainStripe.removeAll(morsoSurper.getCoords());
        BaseShape Baremilieu =bare.rotate(degrees90).translate(new Point2d(wSpacing,-10.0));
        mainStripe.add(Baremilieu);
        BaseShape e= mainStripe.translate(new Point2d(0.0,120.0));
        return e;
    }

    // TODO
    public static BaseShape create_l()
    {
        BaseShape mainStripe = new Rectangle(stripeThickness, maxHeight);
        return mainStripe;
    }

    // TODO
    public static BaseShape create_o() {
        BaseShape mainStripe = new Ellipse(halfMaxHeight/2,halfMaxHeight);
        BaseShape centreStripe= new Ellipse(halfMaxHeight/3,halfMaxHeight*2/3);
        mainStripe.removeAll(centreStripe.getCoords());
        BaseShape deplacerStripe=mainStripe.translate(new Point2d(0.0,100.0));
        return deplacerStripe;
    }

    // On vous donne la lettre W comme exemple.
    public static BaseShape create_W() {
        Double degrees15 = Math.toRadians(8);
        Double spacing = stripeThickness * 2+30;
        BaseShape mainStripe = new Rectangle(stripeThickness, maxHeight);
        BaseShape leftStripe = mainStripe.rotate(-degrees15).translate(new Point2d(-spacing, 0.0));
        BaseShape middleLeftStripe = mainStripe.rotate(degrees15).translate(new Point2d(-spacing / 10, 0.0));
        BaseShape middleRightStripe = mainStripe.rotate(-degrees15).translate(new Point2d(spacing / 10, 0.0));
        BaseShape rightStripe = mainStripe.rotate(degrees15).translate(new Point2d(spacing, 0.0));
        leftStripe.add(middleLeftStripe);
        leftStripe.add(middleRightStripe);
        leftStripe.add(rightStripe);
        //BaseShape a= leftStripe.translate(new Point2d(0.0,-100.0));
        return leftStripe;
    }

    // TODO
    public static BaseShape create_r() {
        Double degrees15 = Math.toRadians(25);
        Double spacing = stripeThickness * 2+30;
        BaseShape mainStripe = new Rectangle(stripeThickness, maxHeight);
        BaseShape leftStripe = new Rectangle(stripeThickness, (maxHeight)*2/3);
        BaseShape Circle = new Circle(halfMaxHeight/2);
        BaseShape centreStripe= new Circle((halfMaxHeight/2)*2/3);
        Circle.removeAll(centreStripe.getCoords());
        BaseShape cercleTranslate=Circle.translate(new Point2d(50.0, 45.0));
        mainStripe.add(cercleTranslate);
        BaseShape lefStripeTranslate= leftStripe.rotate(-degrees15).translate(new Point2d(spacing / 10, 80.0));
        mainStripe.add(lefStripeTranslate);
        BaseShape R= mainStripe.translate(new Point2d( -35.0, 0.0));
        return R;
    }

    // TODO
    public static BaseShape create_d() {

        Double degrees0 = Math.toRadians(0);

        BaseShape mainStripe = new Rectangle(stripeThickness, maxHeight);
        BaseShape Circle = new Circle(halfMaxHeight/2);
        BaseShape centreStripe= new Circle((halfMaxHeight/2)*2/3);
        Circle.removeAll(centreStripe.getCoords());
        //retourne dans un autre BaseShape les list de point
        BaseShape cercleTranslate=Circle.translate(new Point2d(-40.0, 140.0));
        mainStripe.add(cercleTranslate);
        BaseShape translateStripe=mainStripe.translate(new Point2d(40.0,0.0));
        return translateStripe;
    }
}
