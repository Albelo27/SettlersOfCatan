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

import com.example.util.Building;
import com.example.util.DoNotTouch;
import com.example.util.Hex;
import com.example.util.PlayerData;
import com.example.util.Road;

import java.util.ArrayList;

public class CatanBoardView extends SurfaceView {

    private final DoNotTouch c = new DoNotTouch();
    private final float[] vl = c.vertexList;
    private CatanGameState gs;
    private Canvas canvas;
    private final Hex[] hexVals = new Hex[19];
    private final Paint orePaint = new Paint();
    private final Paint brickPaint = new Paint();
    private final Paint sheepPaint = new Paint();
    private final Paint wheatPaint = new Paint();
    private final Paint woodPaint = new Paint();
    private final Paint hitboxPaint = new Paint();
    private final Paint textPaint = new Paint();
    private final Paint[] playerPaint = new Paint[4];
    private final Path path = new Path();
    public final int radius = 10;
    private ArrayList<Float> xh = new ArrayList<Float>();
    private ArrayList<Float> yh = new ArrayList<Float>();
    boolean drawH = false;

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
        hitboxPaint.setStyle(Paint.Style.STROKE);
        textPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        playerPaint[0] = new Paint();
        playerPaint[1] = new Paint();
        playerPaint[2] = new Paint();
        playerPaint[3] = new Paint();
        for (Paint p : playerPaint) {
            p.setStrokeWidth(5);
            p.setStyle(Paint.Style.FILL);
        }
        hitboxPaint.setColor(Color.BLACK);
        textPaint.setColor(Color.WHITE);
        textPaint.setTextSize(17);
        playerPaint[0].setColor(0xFFc12a43);
        playerPaint[1].setColor(0xFF2a3ec1);
        playerPaint[2].setColor(0xFFc17b2a);
        playerPaint[3].setColor(0xFFe5c8a7);
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

    public void setGameState(CatanGameState gameState) {
        gs = gameState;
    }

    @Override
    protected void onDraw(@NonNull Canvas canvas) {
        this.canvas = canvas;
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
                if (hexVal.getGenNum() > -1) {
                    canvas.drawText(Integer.toString(hexVal.getGenNum()), hexVal.getCenter()[0], hexVal.getCenter()[1], textPaint);
                }
            }
        }
        if (gs != null) {
           for (int a = 0; a < gs.data.length; a++) {
               for (Building b : gs.data[a].buildings) {
                   if (b.getName().equals("settlement")) {
                       canvas.drawCircle(b.getX(), b.getY(), radius, playerPaint[a]);
                   } else {
                       canvas.drawRect(b.getX()-radius, b.getY()-radius, b.getX()+radius, b.getY()+radius, playerPaint[a]);
                   }
               }
               for (Road r : gs.data[a].roads) {
                    canvas.drawLine(r.x, r.y, r.z, r.q, playerPaint[a]);
               }
           }
            if (drawH) {
                for (int x = 0; x < xh.size(); x++) {
                        canvas.drawRect(xh.get(x) - (radius*2), yh.get(x) - (radius *2), xh.get(x) + (radius*2), yh.get(x) + (radius *2), hitboxPaint);
                }
            }
        }
//        for (int a = 0; a < c.vertexList.length; a += 2) {
//            canvas.drawRect(vl[a] - (radius*2), vl[a+1] - (radius *2), vl[a] + (radius*2), vl[a+1] + (radius *2), hitboxPaint);
//        }
        drawH = false;
    }

    private void traceHex(float[] cords) {
        path.reset();
        path.moveTo(cords[0], cords[1]);
        for (int a = 2; a < 12; a+=2) {
            path.lineTo(cords[a], cords[a+1]);
        }
    }
    public void setHitBoxVals(ArrayList<Float> hitboxx, ArrayList<Float> hitboxy) {
        xh = hitboxx;
        yh = hitboxy;
    }

    public void setHDraw(boolean in) {
        drawH = in;
    }

    public Canvas getCanvas() {
        return canvas;
    }
}//end of class