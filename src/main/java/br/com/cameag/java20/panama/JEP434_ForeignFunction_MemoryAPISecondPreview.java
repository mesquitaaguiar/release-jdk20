package br.com.cameag.java20.panama;

import java.lang.foreign.*;
import java.lang.invoke.MethodHandle;

public class JEP434_ForeignFunction_MemoryAPISecondPreview {
    public static void main(String[] args) {
        Linker linker          = Linker.nativeLinker();
        SymbolLookup stdlib    = linker.defaultLookup();

        MethodHandle radixsort = linker.downcallHandle(
                stdlib.find("radixsort").orElseThrow(),
                FunctionDescriptor.of(ValueLayout.OfLong.JAVA_LONG, ValueLayout.OfAddress.ADDRESS));

        String[] javaStrings = { "mouse", "cat", "dog", "car" };
        try (Arena offHeap = Arena.openConfined()) {
            MemorySegment pointers = offHeap.allocateArray(ValueLayout.ADDRESS, javaStrings.length);
            for (int i = 0; i < javaStrings.length; i++) {
                MemorySegment cString = offHeap.allocateUtf8String(javaStrings[i]);
                pointers.setAtIndex(ValueLayout.ADDRESS, i, cString);
            }
            radixsort.invoke(pointers, javaStrings.length, MemorySegment.NULL, '\0');

            for (int i = 0; i < javaStrings.length; i++) {
                MemorySegment cString = pointers.getAtIndex(ValueLayout.ADDRESS, i);
                javaStrings[i] = cString.getUtf8String(0);
            }
        }catch (Throwable e){}

        System.out.println(javaStrings);
    }
}
