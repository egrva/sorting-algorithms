package ru.egrva.sorts;

import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.infra.Blackhole;
import org.reflections.Reflections;
import ru.egrva.sorts.radixsort.RadixSort;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class Benchmark {

    @State(Scope.Benchmark)
    public static class BenchmarkState {
        @Param({"10", "100", "1000", "10000", "100000"})
        public int arraySize;

        public int[] arr;
        Random r = new Random();

        @Setup(Level.Trial)
        public void setUp() {
            System.out.println("SETUP");
            arr = r.ints(arraySize, 0, 100000).toArray();

            Reflections reflections = new Reflections("ru.egrva.sorts");
            Set<Class<? extends AbstractSort>> classes = reflections.getSubTypesOf(AbstractSort.class);
            List<AbstractSort> sorts = new ArrayList<>();
            try {
                for (Class<? extends AbstractSort> sort : classes) {
                    AbstractSort sortr = sort.getConstructor().newInstance();
                    sorts.add(sortr);
                }
            } catch (InvocationTargetException | InstantiationException | IllegalAccessException | NoSuchMethodException e) {
                e.printStackTrace();
            }

        }
    }


    @Fork(value = 1, warmups = 1)
    @Warmup(iterations = 2)
    @org.openjdk.jmh.annotations.Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @Measurement(iterations = 3, time = 10, timeUnit = TimeUnit.MILLISECONDS)
    public void RadixSort(Blackhole blackhole, BenchmarkState state) {
        RadixSort radixSort = new RadixSort();
        int[] sorted = radixSort.sort(state.arr);
        blackhole.consume(sorted);
    }
}
