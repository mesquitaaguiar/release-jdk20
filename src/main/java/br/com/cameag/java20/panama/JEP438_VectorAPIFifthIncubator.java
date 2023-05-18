package br.com.cameag.java20.panama;

import jdk.incubator.vector.FloatVector;
import jdk.incubator.vector.VectorSpecies;

public class JEP438_VectorAPIFifthIncubator {
    public static final VectorSpecies<Float> SPECIES = FloatVector.SPECIES_PREFERRED;
    public static void main(String[] args) {

        float[] a = {5.0f,12.5f,20.8f,35.5f,50.0f};
        float[] b = {100.0f,120.5f,200.8f,350.5f,500.0f};
        float[] c = {7000.0f,12000.5f,20000.8f,35000.5f,50000.0f};


        for (int i = 0; i < a.length; i += SPECIES.length()) {
            // VectorMask<Float>  m;
            var m = SPECIES.indexInRange(i, a.length);
            // FloatVector va, vb, vc;
            var va = FloatVector.fromArray(SPECIES, a, i, m);
            var vb = FloatVector.fromArray(SPECIES, b, i, m);
            var vc = va.mul(va)
                    .add(vb.mul(vb))
                    .neg();
            vc.intoArray(c, i, m);

        }

    }
}
