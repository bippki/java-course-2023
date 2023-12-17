package hw11;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.implementation.FixedValue;
import org.junit.jupiter.api.Test;
import static net.bytebuddy.matcher.ElementMatchers.named;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Test11_1 {
    @Test
    void Printer() throws InstantiationException, IllegalAccessException {
        String toString = new ByteBuddy()
            .subclass(Object.class)
            .name("example.Type")
            .method(named("toString")).intercept(FixedValue.value("Hello ByteBuddy!"))
            .make()
            .load(getClass().getClassLoader())
            .getLoaded()
            .newInstance()
            .toString();


        assertEquals(
            toString.toString(), "Hello ByteBuddy!");
    }
}
