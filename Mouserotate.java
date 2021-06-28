
package mouserotate;

import java.awt.AWTException;
import java.awt.Robot;

/**
 *
 * @author logesh-pt4110
 */
public class Mouserotate {

   
    public static void main(String[] args) throws AWTException, InterruptedException {
    int min=0; 
    Robot robot = new Robot();
    while(true){
    if(min==100) break;
    robot.mouseMove(500, 550);
    Thread.sleep(60000);
    robot.mouseMove(-250, -250);
    Thread.sleep(60000);
    min++;
    }
    
    }
    
}
