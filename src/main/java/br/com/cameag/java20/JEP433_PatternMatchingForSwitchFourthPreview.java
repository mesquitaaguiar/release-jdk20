package br.com.cameag.java20;

public class JEP433_PatternMatchingForSwitchFourthPreview {

    public static void main(String[] args) {

        String var1 = "Carlos";

        switch (var1) {
            case "Carlos", "Mesquita", "Aguiar" -> System.out.println("Olá Carlos Mesquita Aguiar");
            default                             -> System.out.println("POr favor, identificar-se.");
        }

        Object obj = 100000000L;
        String formatted = switch (obj) {
            case Integer i -> String.format("int %d", i);
            case Long l    -> String.format("long %d", l);
            case Double d  -> String.format("double %f", d);
            case String s  -> String.format("String %s", s);
            default        -> obj.toString();
        };

        System.out.println(formatted);

        class Shape {}
        class Rectangle extends Shape {}
        class Triangle  extends Shape { int calculateArea() { return 10; } }

        Triangle triangulo = new Triangle();
        switch (triangulo) {
            case null -> { break; }
            case Triangle tri when tri.calculateArea() == 10 -> System.out.println("Triangulo bem definido");
            default -> System.out.println("Trinagulo fora da especificação");
        }
    }
}
