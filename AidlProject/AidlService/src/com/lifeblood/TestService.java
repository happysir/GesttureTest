package com.lifeblood;

import java.util.List;

import com.lifeblood.ITestService.Stub;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;

public class TestService extends Service {
	private Context mContext = null;
	
	 
	private ITestService.Stub binder = new Stub(){
		private String name = null;
		public int getAccountBalance() throws RemoteException {
			// TODO Auto-generated method stub
			return 100000;
		}

		public int getCustomerList(String branch, String[] customerList)
				throws RemoteException {
			// TODO Auto-generated method stub
			customerList[0] = name;
			System.out.println("Name:"+branch);
			return 0;
		}

		public void setOwnerNames(List<String> names) throws RemoteException {
			// TODO Auto-generated method stub
			name = names.get(0);
			System.out.println("Size:"+names.size()+"=="+names.get(0));
		}

		public void showTest() throws RemoteException {
			// TODO Auto-generated method stub
			Intent intent = new Intent(mContext, AidlTtest.class);
			intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			startActivity(intent);
		}
	};
	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		mContext = this;
		return binder;		 
	}
}
