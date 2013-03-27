package com.gesture;

import java.util.Vector;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
//Download by http://www.codefans.net

public class MySurfaceView extends SurfaceView implements Callback, Runnable {
	private Thread mThread;
	private SurfaceHolder surHolder;
	private Canvas mCanvas;
	private Paint mPaint;
	public static Vector<Bitmap> vec_bmp;
	public static Vector<String> vec_string;
	private int colen;
	private boolean isThreadRunning = true;

	public MySurfaceView(Context context, AttributeSet attrs) {
		super(context, attrs);
		surHolder = this.getHolder();
		surHolder.addCallback(this);
		mPaint = new Paint();
		mPaint.setAntiAlias(true);
		mThread = new Thread(this);
		this.setKeepScreenOn(true);
		setFocusable(true);
		vec_string = new Vector<String>();
		vec_bmp = new Vector<Bitmap>();
	}

	public void surfaceCreated(SurfaceHolder holder) {
		colen = this.getWidth()/100;
		mThread.start();
	}

	public void draw() {
		try {
			mCanvas = surHolder.lockCanvas();
			if (mCanvas != null) {
				mCanvas.drawColor(Color.BLACK);
				if (vec_bmp != null && vec_bmp.size() != 0) {
					for (int i = 0; i < vec_bmp.size(); i++) {
						Bitmap bitmap = vec_bmp.elementAt(i);
						mPaint.setStyle(Style.STROKE);
						mCanvas.drawRect((i % colen) * 100  , (i / colen) * 100  , (i % colen) * 100 + 100, (i / colen) * 100 + 100 , mPaint);
						mCanvas.drawBitmap(bitmap, (i % colen) * 100, (i / colen) * 100, mPaint);
						mPaint.setColor(Color.YELLOW);
						mCanvas.drawText(vec_string.elementAt(i), (i % colen) * 100 + 10, (i / colen) * 100 + 100, mPaint);
						mPaint.setColor(Color.WHITE);
					}
				}
				
			}
		} catch (Exception e) {
			
		} finally {
			if(mCanvas!=null)
			   surHolder.unlockCanvasAndPost(mCanvas);
			
		}
	}

	@Override
	public boolean onKeyDown(int key, KeyEvent event) {

		return super.onKeyDown(key, event);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {

		return true;
	}

	public void run() {
		 
		while (isThreadRunning) {
			
			draw();
			try {
				Thread.sleep(100);
			} catch (Exception ex) {
			}
			
		}
		
		
	}

	public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
		 

	}

	public void surfaceDestroyed(SurfaceHolder holder) {
		
			isThreadRunning = false;
	     
	        try
	        {
	            Thread.sleep(300);
	        } catch (InterruptedException e)
	        {
	            e.printStackTrace();
	        }
	 
	}

}
