package hw11;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.matcher.ElementMatchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class Test11_2 {
    @Test
    void testSum() throws Exception {
        DynamicType.Unloaded<ArithmeticUtils> dynamicType = new ByteBuddy()
            .subclass(ArithmeticUtils.class)
            .method(ElementMatchers.named("sum"))
            .intercept(MethodDelegation.to(Wrap.class))
            .make();

        Class<? extends ArithmeticUtils> arithmeticUtilsClass = dynamicType.load(
            Test11_2.class.getClassLoader()
        ).getLoaded();

        ArithmeticUtils arithmeticUtils = arithmeticUtilsClass.newInstance();
        Assertions.assertEquals(8, arithmeticUtils.sum(4, 2));
    }

    public  class ArithmeticUtils {
        public int sum(int a, int b) {
            return a + b;
        }
    }

    public  class Wrap {
        public static int sum(int a, int b) {
            return a * b;
        }
    }
}
