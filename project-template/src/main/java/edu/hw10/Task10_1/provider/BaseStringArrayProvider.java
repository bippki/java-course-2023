package edu.hw10.Task10_1.provider;

import edu.hw10.Task10_1.api.AbstractRandomProvider;
import edu.hw10.Task10_1.util.RandomArrayStreamer;

import java.util.Iterator;

public class BaseStringArrayProvider extends AbstractRandomProvider<String> {
    protected String[] input = null;

    public BaseStringArrayProvider() {
        super();
    }

    public BaseStringArrayProvider(String[] input) {
        this.input = input;
    }

    @Override
    public Iterator<String> iterator() {
        return RandomArrayStreamer.streamify(input, quantity, max, unique).iterator();
    }

    public void setInput(String[] input) {
        this.input = input;
    }
}
