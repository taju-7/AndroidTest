package com.tajulab.screen2camera;

import android.app.Activity;
import android.os.Bundle;
import android.hardware.*;
import android.hardware.Camera;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;
import android.view.*;

public class MainActivity extends Activity {
    SurfaceView sv;
    SurfaceHolder sh;
    Button bt;
    Camera cm;

    static private final int PREVIEW_WIDTH = 640;
    static private final int PREVIEW_HEIGHT = 480;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String s = "カメラスタート";

        Toast.makeText(MainActivity.this, s, Toast.LENGTH_LONG).show();

        SurfaceView mySurfaceView = (SurfaceView)findViewById(R.id.surface_view);
        SurfaceHolder holder = mySurfaceView.getHolder();
        holder.addCallback(mSurfaceListener);
        holder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
    }


    private SurfaceHolder.Callback mSurfaceListener = new SurfaceHolder.Callback() {
        public void surfaceCreated(SurfaceHolder holder){
            cm = Camera.open();
            try {
                cm.setPreviewDisplay(holder);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

            Camera.Parameters parameters = cm.getParameters();
            parameters.setPreviewSize(width, height);
            cm.setParameters(parameters);
            cm.setDisplayOrientation(90);
            cm.startPreview();
            System.out.println(parameters);
        }

        public void surfaceDestroyed(SurfaceHolder holder){
            cm.release();
            cm = null;
        }
    };

}
