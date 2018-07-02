package io.github.dltech21.zxingcamera;

import android.app.Activity;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.yanzhenjie.permission.Action;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.Permission;

import java.util.List;

import io.github.dltech21.ocr.IDCardEnum;
import io.github.dltech21.ocr.IdentityInfo;
import io.github.dltech21.ocr.OcrCameraActivity;
import io.github.dltech21.ocr.OcrConfig;

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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == 1001) {
            String filepath = data.getStringExtra(OcrConfig.OCR_PHOTO_PATH);
            IdentityInfo identityInfo = (IdentityInfo) data.getSerializableExtra(OcrConfig.OCR_IDENTITYINFO);
            StringBuffer localStringBuffer = new StringBuffer();
            localStringBuffer.append("姓名：").append(identityInfo.getName()).append("\n");
            localStringBuffer.append("身份号码：").append(identityInfo.getCertid()).append("\n");
            localStringBuffer.append("性别：").append(identityInfo.getSex()).append("\n");
            localStringBuffer.append("民族：").append(identityInfo.getFork()).append("\n");
            localStringBuffer.append("出生：").append(identityInfo.getBirthday()).append("\n");
            localStringBuffer.append("住址：").append(identityInfo.getAddress()).append("\n");
            localStringBuffer.append("签发机关：").append(identityInfo.getIssue_authority()).append("\n");
            localStringBuffer.append("有效期限：").append(identityInfo.getVaild_priod()).append("\n");
            localStringBuffer.append(identityInfo.getType() == IDCardEnum.FaceEmblem ? "人像面" : "国徽面").append("\n");
            ((TextView) findViewById(R.id.idresult)).setText(localStringBuffer.toString());
            ((ImageView) findViewById(R.id.idimgview)).setImageBitmap(BitmapFactory.decodeFile(filepath));
        }
    }
}
