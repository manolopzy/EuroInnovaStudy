package com.example.euroinnovastudy.customviews;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

/**
 * En este control personalizado, vamos a implementar
 * un control de tres en rayas desde cero.
 * En vez de extender de algun control específico existente
 * deberíamos extender de *View* del cual la gran
 * mayoría de componentes visuales de Android extienden
 *
 * Tenemos que sobrescribir dos métodos de eventos de View,
 * 1 *onDraw* para dibujar el control en la patalla
 * 2 *onMeasure* para calcular las dimensiones del control,
 * el cual se ejecuta automáticamente cuando se necesita
 * recalcular el tamaño de un control
 *
 * Comment/uncomment with line comment	Control+/	Command+/
 * Comment/uncomment with block comment	Control+Shift+/	Command+Shift+/
 */
public class NoughtsAndCrosses extends View {

    private static final int EMPTY = 0;
    private static final int NOUGHT = 1;
    private static final int CROSS = 2;

    //This is a 3x3 chess board,
    private int[][] board = new int[3][3];

    private int noughtColor = Color.RED;
    private int crossColor = Color.BLUE;
    private int activeChess = NOUGHT;

    /**
     * 反鋸齒（英語：anti-aliasing，簡稱AA），也译为抗锯齿或反走样、
     * 消除混叠、抗图像折叠失真等
     * Noun. antialiasing (uncountable) (computer graphics) any technique
     * that reduces the appearance of jagged edges in digital images
     * caused by high-contrast borders between pixels.
     *
     * Creación de diferentes pinceles
     */
    private Paint boardPainter = new Paint();
    private Paint crossPainter = new Paint();
    private Paint noughtPainter = new Paint();

    /*private OnCellSelectedListener listener;
    public void setOnCellSelectedListener(OnCellSelectedListener listener){
        this.listener = listener;
    }*/
    public NoughtsAndCrosses(Context context) {
        super(context);
        initialization();
    }

    public NoughtsAndCrosses(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initialization();
    }

    public NoughtsAndCrosses(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initialization();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public NoughtsAndCrosses(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initialization();
    }

    private void initialization(){
        for (int i = 0; i < board.length; i++) {//board.length returns the length of the rows in the array
            for (int j = 0; j < board[0].length; j++) {//board[0].length returns the length of the columns in the array
                board[i][j] = EMPTY;
            }
        }

        boardPainter.setColor(Color.BLACK);
        boardPainter.setStyle(Paint.Style.STROKE);
        boardPainter.setStrokeWidth(5f);

        crossPainter.setStyle(Paint.Style.STROKE);
        crossPainter.setColor(crossColor);
        crossPainter.setStrokeWidth(10f);

        noughtPainter.setStyle(Paint.Style.STROKE);
        crossPainter.setColor(noughtColor);
        noughtPainter.setStrokeWidth(10);


    }

    public void setCell(int row, int column, int value){
        board[row][column] = value;
    }

    public int getCell(int row, int column){
        return board[row][column];
    }
    public void alternateChess(){
        if(activeChess == NOUGHT){
            activeChess = CROSS;
        }
        else{
            activeChess = NOUGHT;
        }
    }

    /**
     * MeasureSpec modes
     * AT_MOST specified size as maximum
     * EXACTLY exactly the same as the specified size
     * UNSPECIFIED implies that the parent element has not imposed any restrictions on its size
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = calculateWidth(widthMeasureSpec);
        int height = calculateHeight(heightMeasureSpec);
        if(width < height){
            height = width;
        }
        else{
            width = height;
        }
        setMeasuredDimension(width, height);
    }

    private int calculateHeight(int heightMeasureSpec) {
        int defaultHeight = 100;
        int mode = MeasureSpec.getMode(heightMeasureSpec);
        int size = MeasureSpec.getSize(heightMeasureSpec);
        if(mode == MeasureSpec.AT_MOST)
            defaultHeight = size;
        else if(mode == MeasureSpec.EXACTLY){
            defaultHeight = size;
        }
        return defaultHeight;
    }

    private int calculateWidth(int widthMeasureSpec) {
        int defaultWidth = 100;
        int mode = MeasureSpec.getMode(widthMeasureSpec);
        int size = MeasureSpec.getSize(widthMeasureSpec);
        if(mode == MeasureSpec.AT_MOST)
            defaultWidth = size;
        else if(mode == MeasureSpec.EXACTLY){
            defaultWidth = size;
        }
        return defaultWidth;
    }

    /**
     * Android Screen coordinate system:
     * The origin (0,0) is the most top-left position
     * from left to right is the positive x-axis
     * from top to down is the positive y-axis
     * @param canvas
     */
    @Override
    protected void onDraw(Canvas canvas) {
        //Obtenemos las dimensiones del control
        float height = getMeasuredHeight();
        float width = getMeasuredWidth();
        //Dibujamos el tablero
        //float startX, float startY, float stopX, float stopY, @NonNull Paint paint
        //the two vertical lines
        canvas.drawLine(width / 3, 0, width / 3, height, boardPainter);
        canvas.drawLine(2 * width / 3, 0, 2 * width / 3, height, boardPainter);
        //the two horizontal lines
        canvas.drawLine(0, height / 3, width, height / 3, boardPainter);
        canvas.drawLine(0, 2 * height / 3, width, 2 * height / 3, boardPainter);
        //the outside rectangular of the board
        canvas.drawRect(0, 0, width, height, boardPainter);

        //Dibujamos los Os y Xs
        for (int row = 0; row < board.length; row++) {//board.length returns the length of the row in the array
            for (int column = 0; column < board[0].length; column++) {//board[0].length returns the length of the columns in the array
                if(board[row][column] == NOUGHT){
                    canvas.drawCircle(column * width / 3 + width / 6, row * height / 3 + height / 6, width / 6 * 0.8f, noughtPainter);
                }
                else if(board[row][column] == CROSS){
                    canvas.drawLine(
                            column * width / 3 + width / 3 * 0.1f,
                            row * height / 3 + height / 3 * 0.1f,
                            column * width / 3 + width / 3 * 0.9f,
                            row * height / 3 + height / 3 * 0.9f,
                            crossPainter);

                    canvas.drawLine(
                            column * width / 3 + width / 3 * 0.1f,
                            row * height / 3 + height / 3 * 0.9f,
                            column * width / 3 + width / 3 * 0.9f,
                            row * height / 3 + height / 3 * 0.1f,
                            crossPainter);
                }
            }
        }

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float height = getMeasuredHeight();
        float width = getMeasuredWidth();
        //Calculation of the row and column that the user has touched
        int column = (int)(event.getX() / (width / 3));
        int row = (int)(event.getY() / (height / 3));
        if(row < board.length && column < board[0].length){
            board[row][column] = activeChess;
            alternateChess();
            //refresh the view
            this.invalidate();
        }
        return super.onTouchEvent(event);
    }

}
