package test;

import SmartNavigationSystem.ControlPanel;
import SmartNavigationSystem.Login;
import SmartNavigationSystem.Main;
import SmartNavigationSystem.UserControlPanel;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Scanner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 *
 */
public class MainTest {

    @Test
    public void test_case1() throws IOException {
        String input="0";
        ControlPanel cp = UserControlPanel.getInstance();
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        Main.launch(new Scanner(System.in),cp);
    }

}
