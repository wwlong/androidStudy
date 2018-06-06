package com.example.dg.androiddualcamdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.hardware.Camera;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;

import java.io.IOException;

public class MainActivity extends AppCompatActivity{

    private String TAG = "dual camera test";
    private Camera camera0;
    private int cameraId0 = 0;
    SurfaceView surfaceView0;
    SurfaceHolder surfaceHolder0;
    CameraPreview cameraPreview0;
    //camera1
    private Camera camera1;
    private int cameraId1 = 1;
    SurfaceView surfaceView1;
    SurfaceHolder surfaceHolder1;
    CameraPreview cameraPreview1;
    private int count = 0;
    private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = (Button)findViewById(R.id.button);
        cameraTest();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("button", "click");
                try {
                    getFrameFromPreview(cameraPreview0, null);
                    getFrameFromPreview(cameraPreview1, null);
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        });
    }

    private int cameraTest()
    {
        /* Initializing camera preview from 0*/
        surfaceView0 = (SurfaceView) findViewById(R.id.surfaceview0);
        surfaceHolder0 = surfaceView0.getHolder();
        cameraPreview0 = new CameraPreview(getApplicationContext(),camera0,surfaceHolder0,cameraId0);
        surfaceHolder0.addCallback(cameraPreview0);
        surfaceHolder0.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
        //surfaceView0.setVisibility(View.INVISIBLE);

        /* Initializing camera preview from 1*/
        surfaceView1 = (SurfaceView) findViewById(R.id.surfaceview1);
        surfaceHolder1 = surfaceView1.getHolder();
        cameraPreview1 = new CameraPreview(getApplicationContext(),camera1,surfaceHolder1,cameraId1);
        surfaceHolder1.addCallback(cameraPreview1);
        surfaceHolder1.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
        //surfaceView0.setVisibility(View.INVISIBLE);
        return 0;
    }
    private void getFrameFromPreview(CameraPreview cameraPreview, final String savingName) throws IOException {
        cameraPreview.camera.setPreviewCallback(new Camera.PreviewCallback(){

            @Override
            public void onPreviewFrame(byte[] data, Camera camera) {
                Log.d("onPreviewFrame", "camera data"+count);
                count ++;
            }
        });
    }
}
