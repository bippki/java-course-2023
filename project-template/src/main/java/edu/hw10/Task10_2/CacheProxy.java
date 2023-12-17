package edu.hw10.Task10_2;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class CacheProxy implements InvocationHandler {
    private final Object target;
    private final Map<String, Object> cache;
    private final String cacheDirectory;

    private CacheProxy(Object target, String cacheDirectory) {
        this.target = target;
        this.cache = new HashMap<>();
        this.cacheDirectory = cacheDirectory;
    }

    @SuppressWarnings("unchecked")
    public static <T> T create(Object target, Class<?> interfaceType, String cacheDirectory) {
        return (T) Proxy.newProxyInstance(
                target.getClass().getClassLoader(),
                new Class<?>[]{interfaceType},
                new CacheProxy(target, cacheDirectory)
        );
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Cache cacheAnnotation = method.getAnnotation(Cache.class);
        if (cacheAnnotation != null) {
            String key = method.getName() + Arrays.toString(args);
            if (cache.containsKey(key)) {
                return cache.get(key);
            } else {
                Object result = method.invoke(target, args);
                cache.put(key, result);

                if (cacheAnnotation.persist()) {
                    persistToDisk(key, result);
                }

                return result;
            }
        } else {
            return method.invoke(target, args);
        }
    }

    private void persistToDisk(String key, Object result) {
        try {
            File cacheDirectory = new File(this.cacheDirectory);

            if (!cacheDirectory.exists()) {
                cacheDirectory.mkdirs();
            }

            String fileName = key.replaceAll("[^a-zA-Z0-9.-]", "_");
            File cacheFile = new File(cacheDirectory, fileName + ".ser");

            try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(cacheFile))) {
                outputStream.writeObject(result);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void clearCache() {
        cache.clear();

        File cacheDirectory = new File(this.cacheDirectory);

        if (cacheDirectory.exists() && cacheDirectory.isDirectory()) {
            File[] cacheFiles = cacheDirectory.listFiles();

            if (cacheFiles != null) {
                for (File file : cacheFiles) {
                    file.delete();
                }
            }
        }
    }
}
