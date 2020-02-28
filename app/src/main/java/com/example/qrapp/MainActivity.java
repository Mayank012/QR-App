package com.example.qrapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.zxing.WriterException;

import androidmads.library.qrgenearator.QRGContents;
import androidmads.library.qrgenearator.QRGEncoder;

public class MainActivity extends AppCompatActivity {

    EditText grValue;
    Button generateBtn, scanBtn;
    ImageView qrImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        grValue = findViewById(R.id.qrInput);
        generateBtn = findViewById(R.id.generateBtn);
        qrImage = findViewById(R.id.qrPlaceHolder);
        scanBtn = findViewById(R.id.scanBtn);

        generateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String data = grValue.getText().toString();
                if (data.isEmpty()) {
                    grValue.setError("Value Required.");
                } else {
                    QRGEncoder qrgEncoder = new QRGEncoder(data, null, QRGContents.Type.TEXT, 500);
                    try {
                        Bitmap qrBits = qrgEncoder.encodeAsBitmap();
                        qrImage.setImageBitmap(qrBits);
                    } catch (WriterException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        scanBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Scanner.class));
            }
        });
    }
}
