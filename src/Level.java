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

	public void draw(float charX, float charY) {
		float time = app.millis();
		app.loadPixels();
		int index = 0;
		Arrays.fill(app.pixels, 0);
		app.updatePixels();
		app.loadPixels();

		for (int i = (int) (charX - app.width/2); i < (int) (charX + app.width/2); i++){
			for (int j = (int) (charY - app.height/2); j < (int) (charY + app.height/2); j++){
				int offset = ground.width*i;
				int arrayIndex = offset+ j + i;
				int value = app.color(255,255,255);
				if (!(arrayIndex > ground.pixels.length || arrayIndex < 0)){
					value = Math.max(ground.pixels[arrayIndex],0);
				}

//				if (slip.pixels[offset+j+i] > 0){
//					value = slip.pixels[offset+j+i];
//				}
//				if (bad.pixels[offset+j+i] > 0){
//					value = bad.pixels[offset+j+i];
//				}
//				if (details.pixels[offset+j+i] > 0){
//					value = details.pixels[offset+j+i];
//				}

				app.pixels[index] = value;
				index += 1;
			}
		}
		app.updatePixels();
//		System.out.println(app.millis()-time);

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
