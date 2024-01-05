import processing.core.*;

public class Level {
	private PApplet app;
	private PImage GROUND;
	private PImage BAD;
	private PImage SLIP;

	public Level(PApplet applet) {
		this.app = applet;
		GROUND   = app.loadImage("../assets/ground.png");
		BAD      = app.loadImage("../assets/bad.png");
		SLIP     = app.loadImage("../assets/bad.png");
	}

}
