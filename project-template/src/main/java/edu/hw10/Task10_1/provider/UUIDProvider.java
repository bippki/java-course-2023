package edu.hw10.Task10_1.provider;

import edu.hw10.Task10_1.api.AbstractRandomProvider;

import java.util.Iterator;
import java.util.UUID;

public class UUIDProvider extends AbstractRandomProvider<UUID> {

    @Override
    public Iterator<UUID> iterator() {
        return new Iterator<>() {

            @Override
            public boolean hasNext() {
                return true;
            }

            @Override
            public UUID next() {
                if (unique) {
                    return UUID.randomUUID();
                } else {
                    long mostSigBits = randomGenerator.nextLong();
                    long leastSigBits = randomGenerator.nextLong();
                    return new UUID(mostSigBits, leastSigBits);
                }
            }
        };
    }
}
