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
        return null;
    }

    // TODO
    public static BaseShape create_l()
    {
        BaseShape mainStripe = new Rectangle(stripeThickness, maxHeight);
        return mainStripe;
    }

    // TODO
    public static BaseShape create_o() {
        BaseShape mainStripe = new Circle(halfMaxHeight);
        BaseShape centreStripe= new Circle(halfMaxHeight*2/3);
        mainStripe.removeAll(centreStripe.getCoords());
        return mainStripe;
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
        return leftStripe;
    }

    // TODO
    public static BaseShape create_r() {
        return null;
    }

    // TODO
    public static BaseShape create_d() {
        return null;
    }
}
