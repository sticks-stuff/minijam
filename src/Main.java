import processing.core.PApplet;

public class Main extends PApplet {

    Character c;
    public void settings(){
        size(200,200);
    }

    public void setup(){
        c = new Character(this,100,100);
    }
    public void draw(){
        background(255,255,255);
        c.draw();
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
