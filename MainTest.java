package test;

import SmartNavigationSystem.ControlPanel;
import SmartNavigationSystem.Main;
import SmartNavigationSystem.UserControlPanel;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 */
public class MainTest {

    @Test
    public void test_case1() throws IOException {
        String input="0";
        ControlPanel cp = UserControlPanel.getInstance();
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        Main.launch(cp,new Scanner(System.in));
    }

}
