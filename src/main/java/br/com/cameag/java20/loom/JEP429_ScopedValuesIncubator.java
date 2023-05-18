package br.com.cameag.java20.loom;


import jdk.incubator.concurrent.ScopedValue;
import jdk.incubator.concurrent.StructuredTaskScope;

import java.time.Duration;
import java.util.concurrent.Future;

public class JEP429_ScopedValuesIncubator {

    final static ScopedValue<String> MAIN_SCOPE = ScopedValue.newInstance();
    public static void main(String[] args) {
        System.out.println("Iniciando...");

        ScopedValue.where(MAIN_SCOPE,"345678")
                        .run(()->{
                            new Worker().execute();
                        });

        ScopedValue.where(MAIN_SCOPE,"045670")
                .run(()->{
                    new Worker().execute();
                });

        System.out.println("Terminando...");
    }
}

class Worker{

    public void execute()  {
        System.out.printf("Matricula %s\n", JEP429_ScopedValuesIncubator.MAIN_SCOPE.get());
    }

}
