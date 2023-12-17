package edu.hw11;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.dynamic.loading.ClassLoadingStrategy;
import net.bytebuddy.dynamic.scaffold.InstrumentedType;
import net.bytebuddy.implementation.Implementation;
import net.bytebuddy.implementation.bytecode.ByteCodeAppender;
import net.bytebuddy.jar.asm.Label;
import net.bytebuddy.jar.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class Task11_3 {

    public static void main(String[] args) {
        DynamicType.Unloaded<?> dynamicType = new ByteBuddy()
            .subclass(Object.class)
            .name("FibonacciClass")
            .defineMethod("fib", long.class, Opcodes.ACC_PUBLIC | Opcodes.ACC_STATIC)
            .withParameter(int.class, "n")
            .intercept(new CustomByteCodeAppender())
            .make();

        Class<?> dynamicTypeLoaded = dynamicType.load(Task11_3.class.getClassLoader(), ClassLoadingStrategy.Default.WRAPPER)
            .getLoaded();

        try {
            Object instance = dynamicTypeLoaded.getDeclaredConstructor().newInstance();
            long result = (long) dynamicTypeLoaded.getMethod("fib", int.class).invoke(instance, 10);
            System.out.println("Result: " + result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static class CustomByteCodeAppender implements ByteCodeAppender, Implementation {

        @Override
        public Size apply(MethodVisitor mv, Implementation.Context ctx, MethodDescription instrumentedMethod) {
            mv.visitCode();
            Label elseLabel = new Label();
            Label endLabel = new Label();

            // Обычные переменные для хранения параметров и промежуточных результатов
            int paramN = 1;
            int var1 = 2;
            int var2 = 3;

            // Используем обычную переменную для сохранения значения параметра
            mv.visitVarInsn(Opcodes.ILOAD, paramN);
            mv.visitVarInsn(Opcodes.ISTORE, var1);

            mv.visitVarInsn(Opcodes.ILOAD, var1);
            mv.visitJumpInsn(Opcodes.IFLE, elseLabel);
            mv.visitVarInsn(Opcodes.ILOAD, var1);
            mv.visitInsn(Opcodes.ICONST_1);
            mv.visitJumpInsn(Opcodes.IF_ICMPLT, elseLabel);
            mv.visitVarInsn(Opcodes.ILOAD, var1);
            mv.visitInsn(Opcodes.ICONST_1);
            mv.visitInsn(Opcodes.ISUB);
            mv.visitVarInsn(Opcodes.ISTORE, var2); // Используем обычную переменную для n-1
            mv.visitMethodInsn(Opcodes.INVOKESTATIC, "FibonacciClass", "fib", "(I)J", false);

            mv.visitVarInsn(Opcodes.ILOAD, var1);
            mv.visitInsn(Opcodes.ICONST_2);
            mv.visitInsn(Opcodes.ISUB);
            mv.visitVarInsn(Opcodes.ISTORE, var2); // Используем обычную переменную для n-2
            mv.visitMethodInsn(Opcodes.INVOKESTATIC, "FibonacciClass", "fib", "(I)J", false);
            mv.visitVarInsn(Opcodes.ILOAD, var2);
            mv.visitVarInsn(Opcodes.ILOAD, var1);
            mv.visitInsn(Opcodes.LADD);
            mv.visitJumpInsn(Opcodes.GOTO, endLabel);

            mv.visitLabel(elseLabel);
            mv.visitVarInsn(Opcodes.ILOAD, var1);
            mv.visitInsn(Opcodes.I2L);
            mv.visitLabel(endLabel);
            mv.visitInsn(Opcodes.LRETURN);

            mv.visitMaxs(0, 0);
            mv.visitEnd();

            return new Size(4, instrumentedMethod.getStackSize());
        }


        @Override
        public ByteCodeAppender appender(Target target) {
            return this;
        }

        @Override
        public InstrumentedType prepare(InstrumentedType instrumentedType) {
            return instrumentedType;
        }
    }
}
