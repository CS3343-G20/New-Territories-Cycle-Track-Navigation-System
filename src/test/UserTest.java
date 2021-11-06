package test;

import SmartNavigationSystem.*;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Scanner;

import static org.junit.Assert.assertEquals;

/**
 * @Auther: Archer
 */
public class UserTest {

    /**
     * test CyclingMode
     */
    @Test
    public void testChooseMode_case1() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, NoSuchFieldException {
        User user=new User();
        Method method = User.class.getMethod("chooseMode", String.class);

        String input = "0\n6\nY\n2 5\nN\n0\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        method.invoke(user,"CyclingMode");
    }

    /**
     * test ClimbingMode
     */
    @Test
    public void testChooseMode_case2() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, NoSuchFieldException {
        User user=new User();
        Method method = User.class.getMethod("chooseMode", String.class);
        method.invoke(user,"ClimbingMode");
    }
}
