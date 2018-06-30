package io.github.dltech21.zxingcamera;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.yanzhenjie.permission.Action;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.Permission;

import java.util.List;

import io.github.dltech21.ocr.IDCardEnum;
import io.github.dltech21.ocr.OcrCameraActivity;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AndPermission.with(this)
                .permission(new String[]{Permission.WRITE_EXTERNAL_STORAGE, Permission.READ_EXTERNAL_STORAGE, Permission.CAMERA})
                .onGranted(new Action() {
                    @Override
                    public void onAction(List<String> permissions) {
                    }
                })
                .start();

        findViewById(R.id.bt1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, CameraActivity.class));
            }
        });

        findViewById(R.id.bt2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OcrCameraActivity.open(MainActivity.this, IDCardEnum.FaceEmblem, 1001);
            }
        });

        findViewById(R.id.bt3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OcrCameraActivity.open(MainActivity.this, IDCardEnum.NationalEmblem, 1001);
            }
        });
    }
}
