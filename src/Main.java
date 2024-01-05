import processing.core.PApplet;

public class Main extends PApplet {

    Character c;
    public void settings(){
        size(200,200);
    }
    Level level;

    public void setup(){
        c = new Character(this,100,100);
        level = new Level(this);
    }
    public void draw(){
        background(255,255,255);
        c.draw();
        level.draw(0, 0);
    }

    public void mousePressed(){
        c.attachTo(mouseX,mouseY);
    }

    public void mouseReleased(){
        c.detach();
    }

    public static void main(String[] args) {
        PApplet.main("Main");
    }
}
