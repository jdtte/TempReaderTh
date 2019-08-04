package com.tempreader.temp.temp;

import org.junit.Test;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.cache.interceptor.SimpleKey;

import java.lang.reflect.Method;

import static org.junit.Assert.*;

public class TempHourCacheKeyGeneratorTest {

    @Test
    public void generateSimpleInt() {
        //TempHourCacheKeyGenerator test = new TempHourCacheKeyGenerator();
        //test.generate(new Object(),generateSimpleInt(),1);
        //SimpleKey sk = new SimpleKey(1);
        SimpleKey sk = new SimpleKey(2);
        SimpleKey sk2 = new SimpleKey(2);
        System.out.println(sk.equals(sk2));

    }
}