import java.awt.*;

public class Ball {
    public String name;
    public int xPos;
    public int yPos;
    public int dy = 5; // speed in the y direction
    public int height = 100;
    public int width = 75;
    public boolean isAlive;
    public Rectangle rec;

    public Ball(String paramName, int paramXPos, int paramYPos){
        name = paramName;
        xPos = paramXPos;
        yPos = paramYPos;
        rec = new Rectangle(xPos, yPos, width, height);
    }
}
