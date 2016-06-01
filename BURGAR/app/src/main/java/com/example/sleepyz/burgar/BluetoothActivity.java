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
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.support.v7.app.AppCompatActivity;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import nl.dobots.bluenet.ble.base.callbacks.IStatusCallback;
import nl.dobots.bluenet.ble.extended.BleDeviceFilter;
import nl.dobots.bluenet.ble.extended.BleExt;
import nl.dobots.bluenet.ble.extended.callbacks.IBleDeviceCallback;
import nl.dobots.bluenet.ble.extended.structs.BleDevice;
import nl.dobots.bluenet.ble.extended.structs.BleDeviceList;

@TargetApi(21)
public class BluetoothActivity extends AppCompatActivity {
//    private BluetoothAdapter mBluetoothAdapter;
//    private int REQUEST_ENABLE_BT = 1;
//    private Handler mHandler;
//    private static final long SCAN_PERIOD = 10000;
//    private BluetoothLeScanner mLEScanner;
//    private ScanSettings settings;
//    private List<ScanFilter> filters;
//    private BluetoothGatt mGatt;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_bluetooth);
//        mHandler = new Handler();
//        if (!getPackageManager().hasSystemFeature(PackageManager.FEATURE_BLUETOOTH_LE)) {
//            Toast.makeText(this, "BLE Not Supported",
//                    Toast.LENGTH_SHORT).show();
//            finish();
//        }
//
//        final BluetoothManager bluetoothManager =
//                (BluetoothManager) getSystemService(Context.BLUETOOTH_SERVICE);
//        mBluetoothAdapter = bluetoothManager.getAdapter();
//
//    }
//
//    @Override
//    protected void onResume() {
//        super.onResume();
//        if (mBluetoothAdapter == null || !mBluetoothAdapter.isEnabled()) {
//            Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
//            startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
//        } else {
//            if (Build.VERSION.SDK_INT >= 21) {
//                mLEScanner = mBluetoothAdapter.getBluetoothLeScanner();
//                settings = new ScanSettings.Builder()
//                        .setScanMode(ScanSettings.SCAN_MODE_LOW_LATENCY)
//                        .build();
//                filters = new ArrayList<ScanFilter>();
//            }
//            scanLeDevice(true);
//        }
//    }
//
//    @Override
//    protected void onPause() {
//        super.onPause();
//        if (mBluetoothAdapter != null && mBluetoothAdapter.isEnabled()) {
//            scanLeDevice(false);
//        }
//    }
//
//    @Override
//    protected void onDestroy() {
//        if (mGatt == null) {
//            return;
//        }
//        mGatt.close();
//        mGatt = null;
//        super.onDestroy();
//    }
//
//    public void openHome(View v) {
//        Intent i = new Intent(this, HomeActivity.class);
//        startActivity(i);
//    }
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        if (requestCode == REQUEST_ENABLE_BT) {
//            if (resultCode == Activity.RESULT_CANCELED) {
//                //Bluetooth not enabled.
//                finish();
//                return;
//            }
//        }
//        super.onActivityResult(requestCode, resultCode, data);
//    }
//
//    private void scanLeDevice(final boolean enable) {
//        if (enable) {
//            mHandler.postDelayed(new Runnable() {
//                @Override
//                public void run() {
//                    if (Build.VERSION.SDK_INT < 21) {
//                        mBluetoothAdapter.stopLeScan(mLeScanCallback);
//                    } else {
//                        mLEScanner.stopScan(mScanCallback);
//
//                    }
//                }
//            }, SCAN_PERIOD);
//            if (Build.VERSION.SDK_INT < 21) {
//                mBluetoothAdapter.startLeScan(mLeScanCallback);
//            } else {
//                mLEScanner.startScan(filters, settings, mScanCallback);
//            }
//        } else {
//            if (Build.VERSION.SDK_INT < 21) {
//                mBluetoothAdapter.stopLeScan(mLeScanCallback);
//            } else {
//                mLEScanner.stopScan(mScanCallback);
//            }
//        }
//    }
//
//
//    private ScanCallback mScanCallback = new ScanCallback() {
//        @Override
//        public void onScanResult(int callbackType, ScanResult result) {
//            Log.i("callbackType", String.valueOf(callbackType));
//            Log.i("result", result.toString());
//            BluetoothDevice btDevice = result.getDevice();
//            connectToDevice(btDevice);
//        }
//
//        @Override
//        public void onBatchScanResults(List<ScanResult> results) {
//            for (ScanResult sr : results) {
//                Log.i("ScanResult - Results", sr.toString());
//            }
//        }
//
//        @Override
//        public void onScanFailed(int errorCode) {
//            Log.e("Scan Failed", "Error Code: " + errorCode);
//        }
//    };
//
//    private BluetoothAdapter.LeScanCallback mLeScanCallback =
//            new BluetoothAdapter.LeScanCallback() {
//                @Override
//                public void onLeScan(final BluetoothDevice device, int rssi,
//                                     byte[] scanRecord) {
//                    runOnUiThread(new Runnable() {
//                        @Override
//                        public void run() {
//                            Log.i("onLeScan", device.toString());
//                            connectToDevice(device);
//                        }
//                    });
//                }
//            };
//
//    public void connectToDevice(BluetoothDevice device) {
//        if (mGatt == null) {
//            mGatt = device.connectGatt(this, false, gattCallback);
//            scanLeDevice(false);// will stop after first device detection
//        }
//    }
//
//    private final BluetoothGattCallback gattCallback = new BluetoothGattCallback() {
//        @Override
//        public void onConnectionStateChange(BluetoothGatt gatt, int status, int newState) {
//            Log.i("onConnectionStateChange", "Status: " + status);
//            switch (newState) {
//                case BluetoothProfile.STATE_CONNECTED:
//                    Log.i("gattCallback", "STATE_CONNECTED");
//                    gatt.discoverServices();
//                    break;
//                case BluetoothProfile.STATE_DISCONNECTED:
//                    Log.e("gattCallback", "STATE_DISCONNECTED");
//                    break;
//                default:
//                    Log.e("gattCallback", "STATE_OTHER");
//            }
//
//        }
//
//        @Override
//        public void onServicesDiscovered(BluetoothGatt gatt, int status) {
//            List<BluetoothGattService> services = gatt.getServices();
//            Log.i("onServicesDiscovered", services.toString());
//            gatt.readCharacteristic(services.get(1).getCharacteristics().get
//                    (0));
//        }
//
//        @Override
//        public void onCharacteristicRead(BluetoothGatt gatt,
//                                         BluetoothGattCharacteristic
//                                                 characteristic, int status) {
//            Log.i("onCharacteristicRead", characteristic.toString());
//            gatt.disconnect();
//        }
//    };

    private static final String TAG = MainActivity.class.getCanonicalName();

    private BleExt _ble;

    private Button _btnScan;
    private ListView _lvScanList;

    private boolean _scanning = false;
    private BleDeviceList _bleDeviceList;
    private String _address;

    private static final int GUI_UPDATE_INTERVAL = 500;
    private long _lastUpdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initUI();

        // create access point to the library and initialize the Bluetooth adapter.
        _ble = new BleExt();
        _ble.init(this, new IStatusCallback() {
            @Override
            public void onSuccess() {
                // on success is called whenever bluetooth is enabled
                Log.i(TAG, "BLE enabled");
                onBleEnabled();
            }

            @Override
            public void onError(int error) {
                // on error is (also) called whenever bluetooth is disabled
                Log.e(TAG, "Error: " + error);
                onBleDisabled();
            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // finish has to be called on the library to release the objects if the library
        // is not used anymore
        _ble.destroy();
    }

    private void initUI() {
        setContentView(R.layout.activity_bluetooth);

        _btnScan = (Button) findViewById(R.id.buttonScan);
        _btnScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // using the scan filter, we can tell the library to return only specific device
                // types. we are currently distinguish between Crownstones, DoBeacons, iBeacons,
                // and FridgeBeacons
//                BleDeviceFilter selectedItem = (BleDeviceFilter) _spFilter.getSelectedItem();
//                _ble.setScanFilter(selectedItem);

                if (!_scanning) {
                    startScan();
                } else {
                    stopScan();
                }
            }
        });
        _btnScan.setEnabled(false);

        // create a spinner element with the device filter options

        // create an empty list to assign to the list view. this will be updated whenever a
        // device is scanned
        _bleDeviceList = new BleDeviceList();
        DeviceListAdapter adapter = new DeviceListAdapter(this, _bleDeviceList);

        _lvScanList = (ListView) findViewById(R.id.listViewDevices);
        _lvScanList.setAdapter(adapter);
        _lvScanList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // stop scanning for devices. We can't scan and connect to a device at the same time.
                if (_scanning) {
                    stopScan();
                }

                BleDevice device = _bleDeviceList.get(position);
                _address = device.getAddress();

                // start the control activity to switch the device
                Intent intent = new Intent(BluetoothActivity.this, WhitelistActivity.class);
                intent.putExtra("address", _address);
                startActivity(intent);
                Log.d("ble","connected met bluetooth apparaat" + _address);
            }
        });
    }

    private void stopScan() {
        ProgressBar spinner = (ProgressBar)findViewById(R.id.progressBar);
        spinner.setVisibility(View.INVISIBLE);

        _btnScan.setText(getString(R.string.scan_bluetooth_devices));
        // stop scanning for devices
        _ble.stopScan(new IStatusCallback() {
            @Override
            public void onSuccess() {
                _scanning = false;
            }

            @Override
            public void onError(int error) {
                // nada
            }
        });
    }

    private void startScan() {
        ProgressBar spinner = (ProgressBar)findViewById(R.id.progressBar);
        spinner.setVisibility(View.VISIBLE);
        _btnScan.setText(getString(R.string.stop_scan_bluetooth_devices));
        // start scanning for devices. the scan will run at the highest frequency until stopScan
        // is called again. results are coming in as fast as possible. If you're concerned about
        // battery consumption, use the example with the BleScanService instead.
        _scanning = _ble.startScan(new IBleDeviceCallback() {
            @Override
            public void onDeviceScanned(BleDevice device) {
                // called whenever a device was scanned. the library keeps track of the scanned devices
                // and updates average rssi and distance measurements. the device received here as a
                // parameter already has the updated values.

                // but for this example we are only interested in the list of scanned devices, so
                // we ignore the parameter and get the updated device list, sorted by rssi from the
                // library
                if (System.currentTimeMillis() > _lastUpdate + GUI_UPDATE_INTERVAL) {
                    Log.i(TAG, "update");
                    _bleDeviceList = _ble.getDeviceMap().getRssiSortedList();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            // the closest device is the first device in the list (because we asked for the
                            // rssi sorted list)

                            // update the list view
                            DeviceListAdapter adapter = ((DeviceListAdapter) _lvScanList.getAdapter());
                            adapter.updateList(_bleDeviceList);
                            adapter.notifyDataSetChanged();
                        }
                    });
                    _lastUpdate = System.currentTimeMillis();
                }
            }

            @Override
            public void onError(int error) {
                Log.e(TAG, "Scan error: " + error);
            }
        });
    }

    private void onBleEnabled() {
        _btnScan.setEnabled(true);
    }

    private void onBleDisabled() {
        _btnScan.setEnabled(false);
    }
}