package com.ryan.bosshunter;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.util.Log;

public class Entity {
	
	private static final String TAG = Entity.class.getSimpleName();
	
	private Bitmap bitmap;
	Vector2f position;
	Vector2f velocity;
	
	private boolean touched;
	
	public Entity(Bitmap bitmap, int x, int y) {
		this.bitmap = bitmap;
		this.position = new Vector2f(x, y);
		this.velocity = new Vector2f(0, 0);
	}
	
	public Bitmap getBitmap() {
		return bitmap;
	}	
	public void setBitmap(Bitmap bitmap) {
		this.bitmap = bitmap;
	}
	public void setVelocity(Vector2f velocity) {
		this.velocity = velocity;
	}
	public void setVelocity(float x, float y) {
		velocity.x = x;
		velocity.y = y;
	}
	public int getX() {
		return (int)position.x;
	}
	public void setX(float x) {
		position.x = x;
	}
	public int getY() {
		return (int)position.y;
	}
	public void setY(float y) {
		position.y = y;
	}
	
	public boolean isTouched() {
		return touched;
	}
	public void setTouched(boolean touched) {
		this.touched = touched;
	}
	
	public void update() {
		position.add(velocity);
	}
	
	public void draw(Canvas canvas) {
		canvas.drawBitmap(bitmap, position.x - bitmap.getWidth()/2, position.y - bitmap.getHeight()/2, null);
	}
	
	public void handleActionDown(int eventX, int eventY) {
		int half_w = bitmap.getWidth();
		int half_h = bitmap.getHeight();
		
		//Log.d(TAG, "Entity coords: "+ (position.x-half_w) +":"+ (position.y-half_h) +" -- "+ (position.x+half_w) +":"+ (position.y+half_h));
		
		if (eventX > position.x - half_w && eventX < position.x + half_w && eventY > position.y - half_h && eventY < position.y + half_h) {
			//Log.d(TAG, "Entity is touched!!!");
			setTouched(true);
		} else {
			//Log.d(TAG, "Entity is NOT touched!!!");
			setTouched(false);
		}
	}
}