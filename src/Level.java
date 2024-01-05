import processing.core.*;

import java.util.Arrays;

public class Level {
	private PApplet app;

	private static PImage ground;
	private static PImage bad;
	private static PImage slip;
	
	public final PImage[] collideLayers	= new PImage[3];
	
	private static PImage details;
	public final PImage[] noCollideLayers = new PImage[1];

	private final int LEVEL_WIDTH;
	private final int LEVEL_HEIGHT;

	private PImage output;

	public Level(PApplet applet) {
		this.app = applet;

		ground   = app.loadImage("../assets/ground.png");
		slip     = app.loadImage("../assets/bad.png");
		bad      = app.loadImage("../assets/bad.png");
		ground.loadPixels();
		slip.loadPixels();
		bad.loadPixels();


		collideLayers[0] = ground;
		collideLayers[1] = slip;
		collideLayers[2] = bad;

		details = app.loadImage("../assets/details.png");
		noCollideLayers[0] = details;
		details.loadPixels();

		LEVEL_WIDTH = collideLayers[0].width;
		LEVEL_HEIGHT = collideLayers[0].height;


		PGraphics temp = app.createGraphics(LEVEL_WIDTH, LEVEL_HEIGHT);
		temp.beginDraw();
		for (PImage image : collideLayers) {
			temp.image(image, 0, 0);
		}
		temp.endDraw();
	
		output = temp.get();
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

	public void draw(float charX, float charY, float CHAR_WIDTH, float CHAR_HEIGHT) { 
		float time = app.millis();
		int drawX = Math.round(-charX + (float) app.width - CHAR_WIDTH / 2);
		int drawY = Math.round(-charY + (float) app.height - CHAR_HEIGHT / 2);

		PImage subimage = output.get(drawX, drawY, Math.min(app.height, (LEVEL_WIDTH - drawX)), Math.min(app.height, (LEVEL_HEIGHT - drawY)));
		app.image(subimage, 0, 0);
		if(app.millis()-time > 17) {
			System.out.println(app.millis()-time);
		}
	}

	public int getIsTransparent(int x, int y) {
		return this.getIsTransparent(x, y, 1, 1);
	}

	public int getIsTransparent(int x, int y, int w, int h) {
		int areaAlpha = 0;

		for (int i = y; i < y + h; i++) {
			for (int j = x; j < x + w; j++) {
				int pixelIndex = i * LEVEL_WIDTH + j;
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
