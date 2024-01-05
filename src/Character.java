import processing.core.*;
public class Character {
    private final PApplet app;
    private float x;
    private float y;
    private float lastTime = 0;


    private float momentumX = 0;
    private float momentumY = 0;

    private boolean attached = false;
    private float attachedX;
    private float attachedY;
    private float ropeLen = 0;

    private final float yAttachmentOffset = 0;
    private final float xAttachmentOffset = 5;

    private final PImage charImage;

    public Character(PApplet app,int x, int y){
        this.app = app;
        charImage = app.loadImage("../assets/worm.png");
        this.x = x;
        this.y = y;
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
        return new float[]{-i - x - (float) app.width /2 + 25, j - y - (float) app.height /2 + 50};
    }

    public void draw(){
        float deltaTime = (app.millis() - lastTime)/1000;
        lastTime = app.millis();
        if (deltaTime > 1){
            return;
        }
        app.image(charImage, (float) app.width/2,(float) app.height/2);
        app.rect((float) app.width/2,(float) app.height/2,10,20);

        if (attached){
            app.line((float) app.width/2+xAttachmentOffset,(float) app.height/2+yAttachmentOffset,attachedX,attachedY);
            // Mirror for y?
            float angle = PApplet.atan2((float) app.height/2 + yAttachmentOffset - attachedY, (float) app.width/2 + xAttachmentOffset - attachedX);

            momentumX += 20 * PApplet.sin(angle);
            momentumY -= 20 * PApplet.cos(angle);


        }
        x += momentumX * deltaTime;
        y += momentumY * deltaTime;

        attachedX -= momentumX * deltaTime;
        attachedY -= momentumY * deltaTime;


        momentumX *= 0.99;
        momentumY *= 0.99;

    }
}
