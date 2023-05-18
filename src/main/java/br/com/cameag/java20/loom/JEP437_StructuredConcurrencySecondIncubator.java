package br.com.cameag.java20.loom;

import jdk.incubator.concurrent.StructuredTaskScope;

import java.time.Duration;
import java.util.concurrent.Future;

public class JEP437_StructuredConcurrencySecondIncubator {

    public static String findUsuario(String codigo){
        try{Thread.sleep(Duration.ofSeconds(2));}catch (Exception e){}
        return switch (codigo){
            case String s when s.equals("345678") -> "Carlos";
            default -> "Não encontrado";
        };
    }
    public static String findCurso(String codigo){
        try{Thread.sleep(Duration.ofSeconds(3));}catch (Exception e){}
        return switch (codigo){
            case String s when s.equals("345678") -> "Java 20";
            default -> "Não encontrado";
        };
    }
    public static void main(String[] args) {
        try (var scope = new StructuredTaskScope.ShutdownOnFailure()) {
            Future<String> user  = scope.fork(() -> findUsuario("345678"));
            Future<String> course = scope.fork(() -> findCurso("345678"));
            scope.join().throwIfFailed();
            System.out.printf("Matricula 345678 e o %s do curso %s\n",user.resultNow(),course.resultNow());
        }catch (Exception e){}
    }
}
