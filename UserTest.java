package test;

import SmartNavigationSystem.JsonOperation;
import SmartNavigationSystem.User;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Scanner;

/**
 */
public class UserTest {

    /**
     * test CyclingMode
     */
    @Test
    public void testChooseMode_case1() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, NoSuchFieldException {
        User user=new User();

        String input = "1\n6\nY\n2\nN\n3\nY\n2 5\n0\n1";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        user.chooseMode(new Scanner(System.in));
    }

    /**
     * test ClimbingMode
     */
    @Test
    public void testChooseMode_case2() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, NoSuchFieldException {
        User user=new User();
        String input = "2\n1\n1\n1\nN";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        user.chooseMode(new Scanner(System.in));
    }
}
