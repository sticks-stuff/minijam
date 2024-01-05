import processing.core.*;
public class Character {
    private final PApplet app;
    private float x;
    private float y;

    private float momentumX = 0;
    private float momentumY = 0;

    private boolean attached = false;
    private float attachedX;
    private float attachedY;
    private float ropeLen = 0;

    private final float yAttachmentOffset = 0;
    private final float xAttachmentOffset = 5;

    public Character(PApplet app,int x, int y){
        this.app = app;
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

    public void draw(){
        app.rect(x,y,10,20);
        if (isGrounded()){
            momentumY = 0;
        }

        if (attached){
            if (!isGrounded()){
                momentumY += 0.1;
            }
            app.line(x+xAttachmentOffset,y+yAttachmentOffset,attachedX,attachedY);
            // Mirror for y?
            float angle = PApplet.atan2( y+yAttachmentOffset - attachedY ,  x+xAttachmentOffset - attachedX)*180/app.PI;
            if (angle >= 0){
                angle -= 90;
            }
            momentumX += 0.1*angle;

            //momentumY += ropeLen-ropeLength();

            y += (ropeLen-ropeLength());
            System.out.println(momentumY);
        }
        else{
            y += momentumY;
        }
        x += momentumX;


        momentumX *= 0.95;
        momentumY *= 0.95;

    }
}
