package edu.hw10.Task10_1.provider;

import edu.hw10.Task10_1.api.AbstractRandomProvider;
import edu.hw10.Task10_1.util.RandomArrayStreamer;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

public class LongProvider extends AbstractRandomProvider<Long> {
    List<Long> numbers;
    Iterator<Long> numbersIterator;

    @Override
    public Iterator<Long> iterator() {
        if (unique) {
            if (max == Integer.MAX_VALUE) {
                max = min + quantity;
            }
            LongStream stream = LongStream.range(min, max);
            if (quantity != 0) {
                stream = stream.limit(quantity);
            }
            numbers = stream.boxed().collect(Collectors.toList());
            Collections.shuffle(numbers);

            numbersIterator = RandomArrayStreamer.streamify(numbers, quantity, max, unique).iterator();
        } else {
            LongStream stream = new Random().longs(min, max);
            if (quantity != 0) {
                stream = stream.limit(quantity);
            }
            numbersIterator = stream.iterator();
        }

        return new Iterator<>() {
            @Override
            public boolean hasNext() {
                return numbersIterator.hasNext();
            }

            @Override
            public Long next() {
                return numbersIterator.next();
            }
        };
    }
}
