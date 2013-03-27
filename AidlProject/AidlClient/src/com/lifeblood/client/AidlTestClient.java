package com.lifeblood.client;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.lifeblood.ITestService;

public class AidlTestClient extends Activity implements OnClickListener{
    /** Called when the activity is first created. */
	private Button btn = null;
	private Button btn1 = null;
	private Button btn2 = null;
	private Button btn3 = null;
	private Button btn4 = null;
	private TextView text = null;
	private ITestService tService = null;
 
	private ServiceConnection connection = new ServiceConnection(){

		public void onServiceConnected(ComponentName name, IBinder service) {
			// TODO Auto-generated method stub
		 
			tService = ITestService.Stub.asInterface(service);
			System.out.println("Bind Success:"+tService);
		}

		public void onServiceDisconnected(ComponentName name) {
			// TODO Auto-generated method stub
			tService = null;
		}
	};
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        btn = (Button)findViewById(R.id.Button01);
        btn1 = (Button)findViewById(R.id.Button02);
        btn2 = (Button)findViewById(R.id.Button03);
        btn3 = (Button)findViewById(R.id.Button04);
        btn4 = (Button)findViewById(R.id.Button05);
        text = (TextView)findViewById(R.id.TextView01);
        btn.setOnClickListener(this);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
    }
    
	public void onClick(View v) {
		// TODO Auto-generated method stub
		int viewId = v.getId();
		try{
			if (viewId == btn.getId()){
				
				Intent service = new Intent(ITestService.class.getName());
				//°ó¶¨AIDL
				bindService(service, connection, BIND_AUTO_CREATE);
			}else if (viewId == btn1.getId()){
				text.setText("SERVICE£º"+tService.getAccountBalance());
			}else if (viewId == btn2.getId()){
				List<String> names = new ArrayList<String>();
				names.add("Howard ");
				tService.setOwnerNames(names);
			}else if (viewId == btn3.getId()){
				String[] customerList = new String[1];
				tService.getCustomerList("zou", customerList);
				text.setText("SERVICE£º"+customerList[0]);
			}else if (viewId == btn4.getId()){
				tService.showTest();
			}
		}catch(RemoteException e){
			e.printStackTrace();
		}
		
		
	}
	
	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		
		
	}
	
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		
		unbindService(connection);
	}
}