package edu.hw10.Task10_1.provider;

import edu.hw10.Task10_1.util.RandomArrayStreamer;
import edu.hw10.Task10_1.api.AbstractRandomProvider;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class IntegerProvider extends AbstractRandomProvider<Integer> {
    List<Integer> numbers;
    Iterator<Integer> numbersIterator;

    @Override
    public Iterator<Integer> iterator() {
        if (unique) {
            if (max == Integer.MAX_VALUE) {
                max = min + quantity;
            }
            IntStream stream = IntStream.range((int) min, (int) max);
            if (quantity != 0) {
                stream = stream.limit(quantity);
            }
            numbers = stream.boxed().collect(Collectors.toList());
            Collections.shuffle(numbers);

            numbersIterator = RandomArrayStreamer.streamify(numbers, quantity, max, unique).iterator();
        } else {
            IntStream stream = new Random().ints(0, (int) (max - min));
            if (quantity != 0) {
                stream = stream.limit(quantity);
            }
            numbersIterator = stream.map(x -> x + (int) min).iterator();
        }

        return new Iterator<>() {
            @Override
            public boolean hasNext() {
                return numbersIterator.hasNext();
            }

            @Override
            public Integer next() {
                return numbersIterator.next();
            }
        };
    }
}
