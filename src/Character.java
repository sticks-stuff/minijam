import processing.core.*;
public class Character {
    private PApplet app;
    private float x;
    private float y;

    private boolean attached = false;
    private float attachedX;
    private float attachedY;

    public Character(PApplet app,int x, int y){
        this.app = app;
        this.x = x;
        this.y = y;
    }

    public boolean attachTo(float x, float y){
        attached = true;
        attachedX = x;
        attachedY = y;
        return attached;
    }

    public void detach(){
        this.attached = false;
    }

    public void draw(){
        app.rect(x,y,10,20);
        if (attached){
            float yAttachmentOffset = 0;
            float xAttachmentOffset = 5;
            app.line(x+ xAttachmentOffset,y+ yAttachmentOffset,attachedX,attachedY);
        }


    }
}
