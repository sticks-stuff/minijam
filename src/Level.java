import processing.core.*;

public class Level {
	private PApplet app;
	private static PImage ground;
	private static PImage bad;
	private static PImage slip;
	public final PImage[] layers = new PImage[3];

	private final int OFFSET_X = 1920;
	private final int OFFSET_Y = 600;


	public Level(PApplet applet) {
		this.app = applet;
		ground   = app.loadImage("../assets/ground.png");
		slip     = app.loadImage("../assets/bad.png");
		bad      = app.loadImage("../assets/bad.png");

		ground.loadPixels();
		layers[0] = ground;
		slip.loadPixels();
		layers[1] = slip;
		bad.loadPixels();
		layers[2] = bad;
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

	public void draw(float x, float y) {
		app.image(ground, x - OFFSET_X, y - OFFSET_Y);
		app.image(slip, x - OFFSET_X, y - OFFSET_Y);
		app.image(bad, x - OFFSET_X, y - OFFSET_Y);
	}

	public int getIsTransparent(int x, int y) {
		return this.getIsTransparent(x, y, 1, 1);
	}

	public int getIsTransparent(int x, int y, int w, int h) {
		int imageWidth = layers[0].width;
		int areaAlpha = 0;

		for (int i = y; i < y + h; i++) {
			for (int j = x; j < x + w; j++) {
				int pixelIndex = i * imageWidth + j;
				if (pixelIndex < layers[0].pixels.length) {
					int pixelAlpha = 0;
					for (PImage image : layers) {
						int alpha = (image.pixels[pixelIndex] >> 24) & 0xFF; // 32 bit int to store color, AAAARRRRGGGGBBBB
						pixelAlpha += alpha;
					}
					pixelAlpha = Math.max(0, Math.min(255, pixelAlpha));
					areaAlpha += pixelAlpha;
				}
			}
		}
		Double result = ((double)areaAlpha / ((w * h) * 255)) * 100;
		return result.intValue();
	}

}
