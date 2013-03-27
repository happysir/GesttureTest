package com.gesture;

import java.io.File;
import java.util.List;
import java.util.Set;

import android.app.Activity;
import android.gesture.Gesture;
import android.gesture.GestureLibraries;
import android.gesture.GestureLibrary;
import android.gesture.GestureOverlayView;
import android.gesture.GestureOverlayView.OnGestureListener;
import android.gesture.Prediction;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;



public class MainActivity extends Activity {
	private GestureOverlayView gestView;
	private Gesture mGesture =null; 
	private GestureLibrary mGestureLib;
	private TextView mText;
	private EditText mEdit;
	private Button mCancel,mOK;
	private String path;
	private File file;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		
		setContentView(R.layout.main);
		mText = (TextView) findViewById(R.id.text);
		mEdit = (EditText) findViewById(R.id.gname);
		mCancel = (Button) findViewById(R.id.cancel);
		mOK = (Button) findViewById(R.id.ok);
		mOK.setEnabled(false);
		gestView = (GestureOverlayView) findViewById(R.id.mygestureview);
		gestView.setGestureStrokeType(GestureOverlayView.GESTURE_STROKE_TYPE_MULTIPLE);
		
		gestView.setFadeEnabled(true);
		gestView.setKeepScreenOn(true);
		path = new File(Environment.getExternalStorageDirectory(), "gestures").getAbsolutePath();
		file = new File(path);
		mGestureLib = GestureLibraries.fromFile(path); 
		gestView.addOnGestureListener(new OnGestureListener() {  
					
					 
					public void onGestureStarted(GestureOverlayView overlay, MotionEvent event) {

						mText.setText("请用一笔完成一个手势,");
					} 
				
					 
					public void onGestureEnded(GestureOverlayView overlay, MotionEvent event) {
						mGesture = overlay.getGesture();
						if (mGesture.getStrokesCount() == 1) {
							if (event.getAction() == MotionEvent.ACTION_UP) { 
								
								
								mText.setText("请输入名字，再点确定保存手势！");

								mOK.setEnabled(true);
								
							}
							
						}else
						{
							mText.setText("请只用一笔完成手势！");
							mOK.setEnabled(false);
							Log.v("jmq","111111");
						}
					}


					@Override
					public void onGesture(GestureOverlayView overlay,
							MotionEvent event) {
						
					}

					@Override
					public void onGestureCancelled(GestureOverlayView overlay,
							MotionEvent event) {
						 	
					} 
				
				});
		 
		
		mOK.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if (mEdit.getText().toString().equals("")) {
					mText.setText("请输入手势名称");
				} else {
					mText.setText("保存手势:"+mEdit.getText().toString());
					addMyGesture(mEdit.getText().toString(), mGesture);	
					
					mGesture = null;
					gestView.setFadeOffset(10);
					gestView.clear(true); 
					gestView.setFadeOffset(600000);
					mOK.setEnabled(false);
				} 				
			}
		});
		
		mCancel.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
 	
				gestView.setFadeOffset(10);
				gestView.clear(true); 
				gestView.setFadeOffset(600000);  
				mOK.setEnabled(false);
			}
		});
				
		if (!mGestureLib.load()) {
			 mText.setText("手势最多保存9个");
		} else {
			Set<String> set = mGestureLib.getGestureEntries(); 
			Object object[] = set.toArray();
			loadAllGesture(set, object);
		}	
	
	}

	public void addMyGesture(String name, Gesture gesture) { 
		try {
			if (findGesture(gesture)) {
				mText.setText("存在相同手势");
			} else {			 
				if (Environment.getExternalStorageState() != null) { 
					if (!file.exists()) { 
						mGestureLib.addGesture(name, gesture);
						if (mGestureLib.save()) { 
							gestView.clear(true); 
						 
							mText.setText(" ");
							mEdit.setText("");
							gestureToImage(gesture,name);
						} else {
							mText.setText(" ");
						}
					} else { 
						if (!mGestureLib.load()) { 
							mText.setText(" ");
						} else { 
							Set<String> set = mGestureLib.getGestureEntries(); 
							Object object[] = set.toArray();
							boolean isHavedGesture = false;
							for (int i = 0; i < object.length; i++) { 
								if (((String) object[i]).equals(name)) { 
									isHavedGesture = true;
								}
							}
							if (isHavedGesture) { 
							  mGestureLib.removeEntry(name);
							  mGestureLib.addGesture(name, gesture);
							} else {
								mGestureLib.addGesture(name, gesture);
							}
							if (mGestureLib.save()) {
								gestureToImage(gesture,name);
								gestView.clear(true);  
								
								mText.setText("保存成功");
								mEdit.setText("");
							} else {
								mText.setText("保存失败");
							}
							if (!mGestureLib.load()) {
								 
							} else {							
									
							}	
							if (object.length > 9) {
								for (int i = 0; i < object.length; i++) { 
									mGestureLib.removeEntry((String) object[i]);
								}
								mGestureLib.save();
								if (MySurfaceView.vec_bmp != null) {
									MySurfaceView.vec_bmp.removeAllElements(); 
								}
								mText.setText(" ");
								mEdit.setText("");
							}
							object = null;
							set = null;
						}
					}
				} else {
					mText.setText(" ");
				}
			}
		} catch (Exception e) {
			mText.setText(" ");
		}
	}

	public void loadAllGesture(Set<String> set, Object object[]) {  
		if (mGestureLib.load()) { 
			set = mGestureLib.getGestureEntries(); 
			object = set.toArray();
			for (int i = 0; i < object.length; i++) {
			 
				gestureToImage(mGestureLib.getGestures((String) object[i]).get(0),(String) object[i]);
			 
				
			}
		}
	}

	public void gestureToImage(Gesture ges,String name) { 
	 
		if (MySurfaceView.vec_bmp != null) {
			MySurfaceView.vec_bmp.addElement(ges.toBitmap(100, 100, 12, Color.GREEN));
			MySurfaceView.vec_string.addElement(name);
		}
	}

	public boolean findGesture(Gesture gesture) {
//			boolean tem =false;
		try {			 
			if (Environment.getExternalStorageState() != null) { 
				if (!file.exists()) { 
 
				} else { 
					if (!mGestureLib.load()) { 
//						mText.setText("加载失败！");
					} else { 
						List<Prediction> predictions = mGestureLib.recognize(gesture);
						 
						if (!predictions.isEmpty()) {
							Prediction prediction = predictions.get(0);
						 
							if (prediction.score >= 5) {
								
								Toast.makeText(this, "存在相似的手势：name =" + prediction.name,
									     Toast.LENGTH_LONG).show();
								
								return true;
							}
						}
					}
				}
			} else {
				mText.setText(" ");
			}
		} catch (Exception e) {
			e.printStackTrace();
			mText.setText(" ");
			
		}
		
		return false;
	}
}


