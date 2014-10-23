package com.ryan.bosshunter;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public class Level {
	
	private Bitmap bitmap;
	private int tileSize;
	private int[][] map;
	
	void Level(int tileSize, Bitmap bitmap) {
		this.tileSize = tileSize;
		loadBitmap(bitmap);
	}
	
	void loadBitmap(Bitmap bitmap) {
		this.bitmap = bitmap;
		
		int height = bitmap.getHeight();
		int width = bitmap.getWidth();
		
		map = new int[width][height];
		
		for ( int x = 0; x < height; x++ ) {
			for ( int y = 0; y < width; y++ ) {
				map[x][y] = bitmap.getPixel(x, y);
			}
		}
	}
	
	public void render(Canvas canvas) {
		int height = bitmap.getHeight();
		int width = bitmap.getWidth();
		
		for ( int x = 0; x < height; x++ ) {
			for ( int y = 0; y < width; y++ ) {
				// draw map
			}
		}
	}
}