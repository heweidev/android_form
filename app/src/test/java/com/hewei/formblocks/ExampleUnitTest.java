package com.hewei.formblocks;

import com.hewei.formblocks.blocks.SpinnerLine;
import com.hewei.formblocks.blocks.factory.ItemsSet;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

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


    public class User {
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        private String name;
    }

    @Test
    public void testApacheCommon() {

    }

    @Test
    public void testItemSet() {
        assert (ItemsSet.ID_TYPE_ITEMS != null);
        for (SpinnerLine.SpinnerItem item : ItemsSet.ID_TYPE_ITEMS) {
            System.out.println(item.getId() + ": " + item.getDesc());
        }
    }
}