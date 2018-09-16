package com.hewei.formblocks;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void testReflect() throws IllegalAccessException, InstantiationException {
        Object[] array = new Object[10];
        print(array.getClass());

        print(array.getClass().getComponentType());
        //print(array.getClass().newInstance());
    }

    private void print(Object o) {
        System.out.println(o.toString());
    }
}