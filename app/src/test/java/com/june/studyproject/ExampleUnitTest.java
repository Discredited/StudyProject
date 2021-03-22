package com.june.studyproject;

import org.junit.Test;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        //assertEquals(4, 2 + 2);
        int value = 21;
        int count = 0;
        while (value > 0) {
            if ((value & 1) == 1) {
                count++;
            }
            value = value >> 1;
        }
        System.out.println("当前数字" + value + "有" + count + "个1");
    }
}