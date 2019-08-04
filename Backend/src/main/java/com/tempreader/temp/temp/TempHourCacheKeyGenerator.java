package com.tempreader.temp.temp;

import org.springframework.cache.interceptor.KeyGenerator;

import java.lang.reflect.Method;

public class TempHourCacheKeyGenerator implements KeyGenerator {
    @Override
    public Object generate(Object o, Method method, Object... objects) {
        System.out.println("o = " + o);
        System.out.println("method = " + method);
        System.out.println("objects = " + objects);
        System.out.println("objects = " + objects.toString());
        return objects;
    }
}
