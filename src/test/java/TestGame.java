import gui.game.Robot;
import org.junit.Assert;
import org.junit.jupiter.api.Test;


public class TestGame {
    @Test
    public void oneTest() {
        Robot robot = new Robot();
        robot.setM_PositionX(0);
        robot.setM_PositionY(0);
        robot.setM_Direction(Math.PI / 4);
        for (int i = 0; i < 1000000; i++) {
            robot.onModelUpdateEvent(150, 150, 300, 300);
        }
        Assert.assertTrue(Math.sqrt((robot.getM_PositionX() - 150) * (robot.getM_PositionX() - 150) +
                                              (robot.getM_PositionY() - 150) * (robot.getM_PositionY() - 150)) < 1);
    }
}
