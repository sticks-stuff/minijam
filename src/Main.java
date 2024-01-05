import processing.core.PApplet;

public class Main extends PApplet {
    public void settings(){
        size(200,200);
    }
    Character c;

    public void setup(){
        c = new Character(this,100,100);
    }
    public void draw(){
        c.draw();
    }

    public static void main(String[] args) {
        PApplet.main("Main");
    }
}
