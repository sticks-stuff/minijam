import processing.core.*;

public class Level {
	private PApplet app;
	private static PImage ground;
	private static PImage bad;
	private static PImage slip;

	private final int OFFSET_X = 1920;
	private final int OFFSET_Y = 600;


	public Level(PApplet applet) {
		this.app = applet;
		ground   = app.loadImage("../assets/ground.png");
		bad      = app.loadImage("../assets/bad.png");
		slip     = app.loadImage("../assets/bad.png");
	}

	public PImage getGround() {
		return ground;
	}
	
	public PImage getBad() {
		return bad;
	}

	public PImage getSlip() {
		return slip;
	}

	public void draw(int x, int y) {
		app.image(ground, x - OFFSET_X, y - OFFSET_Y);
		app.image(slip, x - OFFSET_X, y - OFFSET_Y);
		app.image(bad, x - OFFSET_X, y - OFFSET_Y);
	}

}
