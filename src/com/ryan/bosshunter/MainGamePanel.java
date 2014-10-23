package com.ryan.bosshunter;

import android.app.Activity;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class MainGamePanel extends SurfaceView implements SurfaceHolder.Callback {

	private static final String TAG = MainGamePanel.class.getSimpleName();
	
	private MainThread thread;
	
	private Entity entity;
	
	private Vector2f touchDownPosition;
	private float friction;
	private float maxMagnitude;
	private float dampening;
	
	public MainGamePanel(Context context) {
		super(context);
		
		getHolder().addCallback(this);
		
		entity = new Entity(BitmapFactory.decodeResource(getResources(), R.drawable.ball), 100, 100);
		friction = 0.98F;
		maxMagnitude = 20.0F;
		dampening = 0.5f;
		
		setFocusable(true);
	}

	public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
		// TODO Auto-generated method stub
		
	}

	public void surfaceCreated(SurfaceHolder holder) {
		thread = new MainThread(getHolder(), this);
		thread.SetRunning(true);
		thread.start();
	}

	public void surfaceDestroyed(SurfaceHolder holder) {
		boolean retry = true;
		while (retry) {
			try {
				thread.SetRunning(false);
				thread.join();
				thread = null;
				retry = false;
				//((Activity)getContext()).finish();
			} catch(InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if (event.getAction() == MotionEvent.ACTION_DOWN) {
			touchDownPosition = new Vector2f(event.getX(), event.getY());
			
			entity.handleActionDown((int)event.getX(), (int)event.getY());
			
			if (event.getY() > getHeight() - 50) {
				thread.SetRunning(false);
				((Activity)getContext()).finish();
			} else {
				Log.d(TAG, "coords == "+ event.getX() +":"+ event.getY());
			}
		}
		
		if (event.getAction() == MotionEvent.ACTION_MOVE) {
			//Log.d(TAG, "ACTION_MOVE");
			//Log.d(TAG, "Moving entity if it is touched: "+ entity.isTouched());
			if (entity.isTouched()) {
				entity.setX(event.getX());
				entity.setY(event.getY());
			}
		}
		
		if (event.getAction() == MotionEvent.ACTION_UP) {
			Log.d(TAG, "ACTION_UP");
			if (entity.isTouched()) {
				Vector2f touchUpPosition = new Vector2f(event.getX(), event.getY());
				Vector2f velocity = Vector2f.subtract(touchDownPosition, touchUpPosition).multiply(dampening);
				
				if ( velocity.getMagnitude() > maxMagnitude ) {
					velocity.normalize().multiply(maxMagnitude);
				}
				
				entity.setTouched(false);
				entity.setVelocity( velocity );
			}
		}		
		
		return true;
	}
	
	public void update(long tickCount) {
		if (entity.velocity.x < 0 && entity.position.x <= entity.getBitmap().getWidth()/2) {
			entity.velocity.x *= -1;
		}
		if (entity.velocity.y < 0 && entity.position.y <= entity.getBitmap().getHeight()/2) {
			entity.velocity.y *= -1;
		}
		
		if (entity.velocity.x > 0 && entity.position.x >= getWidth() - entity.getBitmap().getWidth()/2) {
			entity.velocity.x *= -1;
		}
		if (entity.velocity.y > 0 && entity.position.y >= getHeight() - entity.getBitmap().getHeight()/2) {
			entity.velocity.y *= -1;
		}
		
		if ( entity.velocity.x != 0 && entity.velocity.y != 0) {
			entity.velocity.multiply(friction);
		}
		
		entity.update();
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		canvas.drawColor(Color.BLACK);
		entity.draw(canvas);
	}
	
}