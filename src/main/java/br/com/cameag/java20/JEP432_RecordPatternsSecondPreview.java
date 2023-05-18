package br.com.cameag.java20;

public class JEP432_RecordPatternsSecondPreview {


    public static void main(String[] args) {

        record Point(int x, int y) {}
        Point p = new Point(40, 50);
        enum Color { RED, GREEN, BLUE }
        record ColoredPoint(Point p, Color c) {}
        record Rectangle(ColoredPoint upperLeft, ColoredPoint lowerRight) {}

        if (p instanceof Point(int x, int y)) {
            System.out.println(x + ", " + y);
        } else {
            System.out.println("Não é um Point");
        }

        Rectangle[] r = {
                            new Rectangle(new ColoredPoint(p,Color.BLUE),new ColoredPoint(p,Color.RED)),
                            new Rectangle(new ColoredPoint(new Point(50,100),Color.GREEN),new ColoredPoint(p,Color.BLUE))
                        };
        for (Rectangle(ColoredPoint(Point(var x1, var y1), Color c), ColoredPoint lr): r) {
            System.out.println("Point "+ x1 +" "+ y1 +" - Cor "+ c +" --- ColoredPoint "+ lr);
        }

    }
}
