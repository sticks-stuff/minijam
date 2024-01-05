import processing.core.PApplet;

public class Main extends PApplet {
    private final int START_X       = 400;
    private final int START_Y       = 450;
    private final int SCREEN_SIZE_X = 600;
    private final int SCREEN_SIZE_Y = 600;
    private final int CHAR_WIDTH    = 50;
    private final int CHAR_HEIGHT   = 100;

    private float scale;
    
    Character c;
    Level level;

    public void settings(){
        size(SCREEN_SIZE_X,SCREEN_SIZE_Y);
        scale = (float) width / SCREEN_SIZE_X;
    }
    
    public void setup(){
        level = new Level(this);
        c = new Character(this, level, START_X, START_Y, CHAR_WIDTH, CHAR_HEIGHT);
        surface.setResizable(true);
    }

    public void surfaceResized() {
        scale = (float) width / SCREEN_SIZE_X;
    }

    public void draw(){
        background(255,255,255);
        level.draw(c.getX(), c.getY(), CHAR_WIDTH, CHAR_HEIGHT, scale);
        c.draw(scale);
        println(frameRate);
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
