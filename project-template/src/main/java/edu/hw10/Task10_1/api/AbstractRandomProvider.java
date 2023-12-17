package edu.hw10.Task10_1.api;

import java.security.SecureRandom;

public abstract class AbstractRandomProvider<T> extends AbstractProvider<T> {
    protected boolean unique = false;
    static protected SecureRandom randomGenerator = new SecureRandom();

    @Override
    public Provider<T> setUnique(boolean unique) {
        this.unique = unique;
        return this;
    }

}
