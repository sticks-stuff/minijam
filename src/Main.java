import processing.core.PApplet;

public class Main extends PApplet {
    private final int START_X       = 500;
    private final int START_Y       = 550;
    private final int SCREEN_SIZE_X = 600;
    private final int SCREEN_SIZE_Y = 600;
    private final int CHAR_WIDTH    = 50;
    private final int CHAR_HEIGHT   = 100;
    
    Character c;
    public void settings(){
        size(SCREEN_SIZE_X,SCREEN_SIZE_Y);
    }
    Level level;

    public void setup(){
        c = new Character(this, START_X, START_Y);
        level = new Level(this);
    }
    public void draw(){
        background(255,255,255);
        level.draw(c.getX(), c.getY(), CHAR_WIDTH, CHAR_HEIGHT);
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
