package com.example.settlersofcatan;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceView;

import androidx.annotation.NonNull;

import com.example.util.DoNotTouch;
import com.example.util.Hex;

public class CatanBoardView extends SurfaceView {

    private final DoNotTouch c = new DoNotTouch();
    private final Hex[] hexVals = new Hex[19];
    private final Paint orePaint = new Paint();
    private final Paint brickPaint = new Paint();
    private final Paint sheepPaint = new Paint();
    private final Paint wheatPaint = new Paint();
    private final Paint woodPaint = new Paint();
    private final Path path = new Path();

    public CatanBoardView(Context c, AttributeSet attr) {
        //surface view ctr
        super(c, attr);
        //make sure it will draw
        setWillNotDraw(false);
        wheatPaint.setStyle(Paint.Style.FILL);
        woodPaint.setStyle(Paint.Style.FILL);
        orePaint.setStyle(Paint.Style.FILL);
        sheepPaint.setStyle(Paint.Style.FILL);
        brickPaint.setStyle(Paint.Style.FILL);
        wheatPaint.setColor(0xffd8cd14);
        woodPaint.setColor(0xff124408);
        orePaint.setColor(0xff353e45);
        sheepPaint.setColor(0xff65d642);
        brickPaint.setColor(0xff851a0f);
        setBackgroundColor(Color.WHITE);
    }

    public void setHexVals(Hex[] hexIn) {
        System.arraycopy(hexIn, 0, hexVals, 0, hexVals.length);
    }

    @Override
    protected void onDraw(@NonNull Canvas canvas) {
        for (Hex hexVal : hexVals) {
            if (hexVal != null) {
                traceHex(hexVal.getCorners());
                switch (hexVal.getResource()) {
                    case 0:
                        canvas.drawPath(path, orePaint);
                        break;
                    case 1:
                        canvas.drawPath(path, wheatPaint);
                        break;
                    case 2:
                        canvas.drawPath(path, brickPaint);
                        break;
                    case 3:
                        canvas.drawPath(path, sheepPaint);
                        break;
                    case 4:
                        canvas.drawPath(path, woodPaint);
                        break;
                }
            }
        }
    }

    private void traceHex(float[] cords) {
        path.reset();
        path.moveTo(cords[0], cords[1]);
        for (int a = 2; a < 12; a+=2) {
            path.lineTo(cords[a], cords[a+1]);
        }
    }


}//end of class