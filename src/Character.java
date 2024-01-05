import processing.core.*;
public class Character {
    private PApplet app;
    private float x;
    private float y;
    public Character(PApplet app,int x, int y){
        this.app = app;
        this.x = x;
        this.y = y;
    }

    public void draw(){
        app.rect(x,y,10,20);
    }
}
