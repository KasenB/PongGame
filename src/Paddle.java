import java.awt.*;

public class Paddle {
    public String name;
    public int xPos;
    public int yPos;
    public int dy = 5; // speed in the y direction
    public int height = 100;
    public int width = 75;
    public boolean isAlive;
    public Rectangle rec;
    public boolean upIsPressed;
    public boolean downIsPressed;
    public boolean leftIsPressed;
    public boolean rightIsPressed;

    public Paddle(String paramName, int paramXPos, int paramYPos){
        name = paramName;
        xPos = paramXPos;
        yPos = paramYPos;
        rec = new Rectangle(xPos, yPos, width, height);
    }

    public void move(){

        if (upIsPressed == true) {
            yPos = yPos - dy;
        } else if (downIsPressed == true){
            yPos = yPos + dy;
        }


        // Because we now have control over the character, we don't need wrap.
        // || = or
        if (yPos < 0){
            yPos=0;
        }
        if (yPos > 700-height){
            yPos=700-height;
        }
        if (xPos > 1000-width){
            xPos = 1000-width;
        }
        if (xPos < 0) {
            xPos = 0;
        }
        rec = new Rectangle(xPos, yPos, width, height);
    }
}
