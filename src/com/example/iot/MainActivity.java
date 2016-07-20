package com.example.iot;

import com.example.iot.UdpServer;
import android.support.v7.app.ActionBarActivity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;

public class MainActivity extends ActionBarActivity {

	private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        System.out.println(isOnline());
        System.out.println(getMobileIP());
        new Thread(new UdpServer()).start();
    }

    

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    

    public static String getMobileIP() {
    	  try {
    	    for (Enumeration<NetworkInterface> en = NetworkInterface
    	    .getNetworkInterfaces(); en.hasMoreElements();) {
    	       NetworkInterface intf = (NetworkInterface) en.nextElement();
    	       for (Enumeration<InetAddress> enumIpAddr = intf
    	          .getInetAddresses(); enumIpAddr.hasMoreElements();) {
    	          InetAddress inetAddress = enumIpAddr.nextElement();
    	          if(!inetAddress.isLoopbackAddress() && inetAddress instanceof Inet4Address) {
    	             String ipaddress = inetAddress .getHostAddress().toString();
    	             return ipaddress;
    	          }
    	       }
    	    }
    	  } catch (SocketException ex) {
    	     Log.e(TAG, "Exception in Get IP Address: " + ex.toString());
    	  }
    	  return null;
    }
    
    public boolean isOnline() {
        ConnectivityManager cm =
            (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }
    
    
    

}


