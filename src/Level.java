import processing.core.*;

public class Level {
	private PApplet app;

	private static PImage ground;
	private static PImage bad;
	private static PImage slip;
	
	public final PImage[] collideLayers	= new PImage[3];
	
	private static PImage details;
	public final PImage[] noCollideLayers = new PImage[1];

	public Level(PApplet applet) {
		this.app = applet;

		ground   = app.loadImage("../assets/ground.png");
		slip     = app.loadImage("../assets/bad.png");
		bad      = app.loadImage("../assets/bad.png");
//		ground.loadPixels();
//		slip.loadPixels();
//		bad.loadPixels();


		collideLayers[0] = ground;
		collideLayers[1] = slip;
		collideLayers[2] = bad;

		details = app.loadImage("../assets/details.png");
		noCollideLayers[0] = details;
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

	public void draw(float charX, float charY, int CHAR_WIDTH, int CHAR_HEIGHT) { 
		float drawX = -charX + (float) app.width / 2 - (float) CHAR_WIDTH / 2;
		float drawY = -charY + (float) app.height / 2 - (float) CHAR_HEIGHT / 2;
		app.loadPixels();
		for (int i = 0; i < app.pixels.length; i++){
		}

		for (PImage image : collideLayers) {
			app.image(image, drawX, drawY);
		}
		for (PImage image : noCollideLayers) {
			app.image(image, drawX, drawY);
		}
	}

	public int getIsTransparent(int x, int y) {
		return this.getIsTransparent(x, y, 1, 1);
	}

	public int getIsTransparent(int x, int y, int w, int h) {
		int imageWidth = collideLayers[0].width;
		int areaAlpha = 0;

		for (int i = y; i < y + h; i++) {
			for (int j = x; j < x + w; j++) {
				int pixelIndex = i * imageWidth + j;
				if (pixelIndex < collideLayers[0].pixels.length) {
					int pixelAlpha = 0;
					for (PImage image : collideLayers) {
						int alpha = (image.pixels[pixelIndex] >> 24) & 0xFF; // 32 bit int to store color, AAAARRRRGGGGBBBB
						pixelAlpha += alpha;
					}
					pixelAlpha = Math.max(0, Math.min(255, pixelAlpha));
					areaAlpha += pixelAlpha;
				}
			}
		}
		double result = ((double)areaAlpha / ((w * h) * 255)) * 100;
		return (int) result;
	}

}
