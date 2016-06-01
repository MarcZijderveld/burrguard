package com.example.sleepyz.burgar;

        import android.annotation.TargetApi;
        import android.app.Activity;
        import android.bluetooth.BluetoothAdapter;
        import android.bluetooth.BluetoothDevice;
        import android.bluetooth.BluetoothGatt;
        import android.bluetooth.BluetoothGattCallback;
        import android.bluetooth.BluetoothGattCharacteristic;
        import android.bluetooth.BluetoothGattService;
        import android.bluetooth.BluetoothManager;
        import android.bluetooth.BluetoothProfile;
        import android.bluetooth.le.BluetoothLeScanner;
        import android.bluetooth.le.ScanCallback;
        import android.bluetooth.le.ScanFilter;
        import android.bluetooth.le.ScanResult;
        import android.bluetooth.le.ScanSettings;
        import android.content.Context;
        import android.content.Intent;
        import android.content.pm.PackageManager;
        import android.os.Build;
        import android.os.Bundle;
        import android.os.Handler;
        import android.support.v7.app.ActionBarActivity;
        import android.util.Log;
        import android.view.View;
        import android.widget.Toast;

        import java.util.ArrayList;
        import java.util.List;

@TargetApi(21)
public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void Login(View v) {
        Intent i = new Intent(this, BluetoothActivity.class);
        startActivity(i);
    }
}