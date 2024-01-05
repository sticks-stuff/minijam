import processing.core.*;
public class Character {
    private final PApplet app;
    private final Level level;
    private final int width;
    private final int height;

    private float x;
    private float y;
    private float lastTime = 0;


    private PVector velocity;

    private boolean attached = false;
    private float attachedX;
    private float attachedY;
    private float ropeLen = 0;

    private final float yAttachmentOffset = 0;
    private final float xAttachmentOffset = 5;


    private final float MAX_SPEED = 5;
    private final float ACCELERATION = 0.2f;
    private final float GRAVITY = 0.5f;
    private final int TRANSPARENCY_THRESHOLD = 50;

    private final PImage charImage;

    public Character(PApplet app, Level level, int x, int y, int width, int height){
        this.app = app;
        this.level = level;
        charImage = app.loadImage("../assets/worm.png");
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.velocity = new PVector(0,0);
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public boolean attachTo(float x, float y){
        attached = true;
        attachedX = x;
        attachedY = y;
        ropeLen = ropeLength();
        return attached;
    }

    public void detach(){
        this.attached = false;
    }

    public float ropeLength(){
        return (float) Math.sqrt(Math.pow((y+yAttachmentOffset - attachedY),2) + Math.pow((x+xAttachmentOffset - attachedX),2));
    }

    private boolean isGrounded(){
        return y >= 500;
    }

    private float[] coordinatesToWorld(float i, float j){
        return new float[]{-i - x - (float) app.width/2 + 25, j - y - (float) app.height/2 + 50};
    }

    public void draw(){
        float deltaTime = (app.millis() - lastTime)/1000;
        lastTime = app.millis();
        if (deltaTime > 1){
            return;
        }
        app.image(charImage, (float) app.width/2,(float) app.height/2);

        update();
    }
    
    public void update() {
        // Apply gravity
        //velocityY += GRAVITY;

        // Predict next position
        float nextX = x + velocity.x;
        float nextY = y + velocity.y;

        // Check for collision


        float transparency = level.getIsTransparent((int) x, (int) y, 1, 1);
        System.out.println(transparency);
        if (transparency < TRANSPARENCY_THRESHOLD) {
            // No collision, update position
            x = nextX;
            y = nextY;
        } else {
            // Collision, stop movement in that direction
            if (level.getIsTransparent((int)nextX, (int)y, width-10, height-10) > TRANSPARENCY_THRESHOLD) {
                // Horizontal movement is safe
                x = nextX;
            } else {
                app.println("Collision X");
                velocity.x = 0;
            }
            if (level.getIsTransparent((int)x, (int)nextY, width-10, height-10) > TRANSPARENCY_THRESHOLD) {
                // Vertical movement is safe
                y = nextY;
            } else {
                app.println("Collision Y");
                velocity.y = 0;
            }
        }

        if (app.keyPressed) {
            if (app.key == 'w' || app.key == 'W') {
                velocity.y = Math.max(velocity.y - ACCELERATION, -MAX_SPEED);
            } else if (app.key == 's' || app.key == 'S') {
                velocity.y = Math.min(velocity.y + ACCELERATION, MAX_SPEED);
            } else if (app.key == 'a' || app.key == 'A') {
                velocity.x = Math.max(velocity.x - ACCELERATION, -MAX_SPEED);
            } else if (app.key == 'd' || app.key == 'D') {
                velocity.x = Math.min(velocity.x + ACCELERATION, MAX_SPEED);
            }
        } else {
            // Slow down when no keys are pressed
            velocity.x *= 0.9;
            velocity.y *= 0.9;
        }
    }
}
