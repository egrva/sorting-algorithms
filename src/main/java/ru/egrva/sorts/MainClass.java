package ru.egrva.sorts;

import org.reflections.Reflections;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.stream.IntStream;

public class MainClass {

    public static void main(String[] args) throws IOException {

        List<AbstractSort> sorts = getSorts();
        int[] arraySizes = {10, 100, 1000, 10000, 100000};
        int iterationsCount = 100;
        Random r = new Random();

        for (int size : arraySizes) {
            for (ArrayType arrayType : ArrayType.values()) {
                System.out.println("================================");
                System.out.println(String.format("Размер массива: %s, тип массива: %s, кол-во итераций: %d", size, arrayType, iterationsCount));
                Map<String, Long> resultMap = new HashMap<>();
                for (int i = 0; i < iterationsCount; i++) {
                    int[] arr = generateArray(size, arrayType, r);
                    for (AbstractSort sort : sorts) {
                        long timeSort = sort.startSort(arr);
                        resultMap.putIfAbsent(sort.getClass().getSimpleName(), timeSort);
                        resultMap.computeIfPresent(sort.getClass().getSimpleName(), (k, v) -> v + timeSort);
                    }
                }
                resultMap.forEach((key, value) -> System.out.println(
                        String.format("Тип сортировки: %s, среднее время: %f", key, ((double) value / iterationsCount))
                ));
            }
        }
    }

    private static int[] generateArray(int size, ArrayType arrayType, Random random) {
        return switch (arrayType) {
            case ASC -> IntStream.of(random.ints(size, 0, 100000).toArray())
                    .boxed()
                    .sorted()
                    .mapToInt(i -> i)
                    .toArray();
            case DESC -> IntStream.of(random.ints(size, 0, 100000).toArray())
                    .boxed()
                    .sorted(Comparator.reverseOrder())
                    .mapToInt(i -> i)
                    .toArray();
            case RANDOM -> random.ints(size, 0, 100000).toArray();
        };
    }

    private static List<AbstractSort> getSorts() {
        Reflections reflections = new Reflections("ru.egrva.sorts");
        Set<Class<? extends AbstractSort>> classes = reflections.getSubTypesOf(AbstractSort.class);
        List<AbstractSort> sorts = new ArrayList<>();
        try {
            for (Class<? extends AbstractSort> sort : classes) {
                AbstractSort sortr = sort.getConstructor().newInstance();
                sorts.add(sortr);
            }
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }
        return sorts;
    }

}
