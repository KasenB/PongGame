import java.awt.*;

public class Ball {
    public String name;
    public int xPos;
    public int yPos;
    public int dx = (int)(Math.random()*5+6);
    public int dy = (int)(Math.random()*3+2); // speed in the y direction
    public int height = 50;
    public int width = 55;
    public boolean isAlive = true;
    public int rightPoints = 0;
    public int leftPoints = 0;
    boolean leftHasWon = false;
    public Rectangle rec;
    public Image pic;
    public int timer;
    public boolean timerRunning = true;

    public Ball(int paramXPos, int paramYPos){
        xPos = paramXPos;
        yPos = paramYPos;
        rec = new Rectangle(xPos, yPos, width, height);
    }

    public void move(){
        xPos = xPos + dx;
        yPos = yPos + dy;

        if (yPos < 0 || yPos + height > 700)  {
            dy = -dy;
        } // top & bottom

        if (xPos + width > 1000 )  {
            // if ball is not hit by right, left gets a point
            leftPoints ++;
            System.out.println("Left has " + leftPoints + " points, Right has " + rightPoints + " points.");
            yPos = 320;
            xPos = 472;
            timerRunning = true;


            dx = -dx;
        } // right

        if (xPos < 0) {
            // if ball is not hit by left, give a point to right
            rightPoints ++;
            System.out.println("Left has " + leftPoints + " points, Right has " + rightPoints + " points.");
            yPos = 320;
            xPos = 473;
            timerRunning = true;

        } // right


        rec = new Rectangle(xPos, yPos, width, height);
    }
}
