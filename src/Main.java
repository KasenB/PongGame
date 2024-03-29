import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.tools.Tool;

public class Main implements Runnable, KeyListener {

    final int WIDTH = 1000;
    final int HEIGHT = 700;

    public JFrame frame;
    public Canvas canvas;
    public JPanel panel;

    public BufferStrategy bufferStrategy;

    public Paddle rightPaddle;
    public Paddle leftPaddle;

    /***
     * Step 1 for arrays: declare
     */
    //public Ball[] balls;
    public Ball ball;
    public boolean ballAndRightPaddle = false;
    public boolean ballAndLeftPaddle = false;
    public Image paddlePic;
    public Image tablePic;
    public Image ballPic;
    public Image boltPic;

    public boolean pointOn = true;

    public static void main(String[] args) {
        Main ex = new Main();   //creates a new instance of the game
        new Thread(ex).start();                 //creates a threads & starts up the code in the run( ) method
    }
    public Main() { // BasicGameApp constructor
        // step 2 for keyboard: add KeyListener to the canvas
        setUpGraphics();
        canvas.addKeyListener(this);

        //variable and objects
        //create (construct) the objects needed for the game
        // **** STEP 2: CONTSTRUCT ASTRONAUT AND ITS IMAGE
        rightPaddle = new Paddle("Right Paddle", 936, 305);
        paddlePic = Toolkit.getDefaultToolkit().getImage("paddle.png");
        leftPaddle = new Paddle("Right Paddle", 2, 305);
        ballPic = Toolkit.getDefaultToolkit().getImage("pingpongball2 copy.png");
        ball = new Ball("Ball", 473, 320);

        /***
         * Step 2 for arrays: construct
         */
        //balls = new Ball[2];

        /***
         * Step 3 for arrays: fill using for loop
         */
        //for (int x = 0; x < 2; x++) {
//            balls[x] = new Ball(473, 320);
//            balls[x].pic = Toolkit.getDefaultToolkit().getImage("star.png");


        tablePic = Toolkit.getDefaultToolkit().getImage("pingpongtable2.jpeg");

    } // end BasicGameApp constructor



    public void run() {
        //for the moment we will loop things forever.
        while (true) {
            moveThings();  //move all the game objects
            collisions();
            render();  // paint the graphics
            pause(10); // sleep for 10 ms
        }
    }

    public void moveThings() {
        //call the move() code for each object
        rightPaddle.move();
        leftPaddle.move();
        if (ball.timerRunning) {
            System.out.println("timer: " + ball.timerRunning);
            ball.dx = 0;
            ball.dy = 0;
            // increase timer
            ball.timer++;
        } else {
            ball.move();

        }
        if (ball.timer > 75) {
            ball.timerRunning = false;
            ball.timer = 0;
            ball.dx = (int) (Math.random() * 5 + 6);
            ball.dy = (int) (Math.random() * 3 + 2);

        }

    }

    public void collisions(){
        if (ball.rec.intersects(rightPaddle.rec) && ballAndRightPaddle == false) {
            ballAndRightPaddle = true;
            ball.dx = -ball.dx - 2 ;
            ball.dy = ball.dy + 1;
        }
        if ( !ball.rec.intersects(rightPaddle.rec)) {
            ballAndRightPaddle = false;
        }

        if (ball.rec.intersects(leftPaddle.rec) && ballAndLeftPaddle == false) {
            ballAndLeftPaddle = true;
            ball.dx = -ball.dx + 2;
            ball.dy = ball.dy + 1;
        }
        if ( !ball.rec.intersects(leftPaddle.rec)) {
            ballAndLeftPaddle = false;
        }
    }

    private void render() {
        Graphics2D g = (Graphics2D) bufferStrategy.getDrawGraphics();
        g.clearRect(0, 0, WIDTH, HEIGHT);

        g.drawImage(tablePic, 0, 0,WIDTH,HEIGHT,null);
        g.drawImage(paddlePic, rightPaddle.xPos, rightPaddle.yPos,rightPaddle.width, rightPaddle.height, null);
        g.drawImage(paddlePic, leftPaddle.xPos, leftPaddle.yPos,leftPaddle.width, leftPaddle.height, null);
        g.drawImage(ballPic, ball.xPos, ball.yPos, ball.width, ball.height, null);

        g.dispose();
        bufferStrategy.show();
    }
    public void pause(int time ) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
        }
    }
    private void setUpGraphics() {
        frame = new JFrame("Application Template");   //Create the program window or frame.  Names it.

        panel = (JPanel) frame.getContentPane();  //sets up a JPanel which is what goes in the frame
        panel.setPreferredSize(new Dimension(WIDTH, HEIGHT));  //sizes the JPanel
        panel.setLayout(null);   //set the layout

        // creates a canvas which is a blank rectangular area of the screen onto which the application can draw
        // and trap input events (Mouse and Keyboard events)
        canvas = new Canvas();
        canvas.setBounds(0, 0, WIDTH, HEIGHT);
        canvas.setIgnoreRepaint(true);

        panel.add(canvas);  // adds the canvas to the panel.

        // frame operations
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  //makes the frame close and exit nicely
        frame.pack();  //adjusts the frame and its contents so the sizes are at their default or larger
        frame.setResizable(false);   //makes it so the frame cannot be resized
        frame.setVisible(true);      //IMPORTANT!!!  if the frame is not set to visible it will not appear on the screen!

        // sets up things so the screen displays images nicely.
        canvas.createBufferStrategy(2);
        bufferStrategy = canvas.getBufferStrategy();
        canvas.requestFocus();
        System.out.println("DONE graphic setup");
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        char key = e.getKeyChar();
        int keyCode = e.getKeyCode();
        //System.out.println("Key pressed: " + key + ", Key code: " + keyCode);
        if (keyCode == 38) {
//            oliver.dy = -2;
            rightPaddle.upIsPressed = true;
        } // right paddle - up
        if (keyCode == 40) {
//            oliver.dy = 2;
            rightPaddle.downIsPressed = true;
        } // right paddle - down
        if (keyCode == 87) {
//            oliver.dy = 2;
            leftPaddle.upIsPressed = true;
        } // left paddle - up
        if (keyCode == 83) {
//            oliver.dy = 2;
            leftPaddle.downIsPressed = true;
        } // left paddle - down
    }

    @Override
    public void keyReleased(KeyEvent e) {
        char key = e.getKeyChar();
        int keyCode = e.getKeyCode();
        //System.out.println("Key pressed: " + key + ", Key code: " + keyCode);
        if (keyCode == 38) {
//            oliver.dy = 0;
            rightPaddle.upIsPressed = false;
        } // up
        if (keyCode == 40) {
//            oliver.dy = 0;
            rightPaddle.downIsPressed = false;
        } // down
        if (keyCode == 87) {
//            oliver.dy = 2;
            leftPaddle.upIsPressed = false;
        } // left paddle - up
        if (keyCode == 83) {
//            oliver.dy = 2;
            leftPaddle.downIsPressed = false;
        } // left paddle - down
    }
}

