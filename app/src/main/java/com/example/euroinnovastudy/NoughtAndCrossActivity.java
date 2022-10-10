package com.example.euroinnovastudy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.euroinnovastudy.customviews.NoughtsAndCrosses;

public class NoughtAndCrossActivity extends AppCompatActivity {

    private NoughtsAndCrosses board;

    private Button next;

    private TextView selectedCell;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nought_and_cross);

        /*board = findViewById(R.id.board);
        next = findViewById(R.id.next);

        selectedCell = findViewById(R.id.selectedCell);*/
        /*board.setOnCellSelectedListener(new NoughtsAndCrosses.OnCellSelectedListener() {
            @Override
            public void onCellSelected(int row, int column) {
                board.alternateChess();
            }
        });*/
    }

    public void onNext(View view) {
        Intent intent = new Intent(this, ActionbarActivity.class);
        startActivity(intent);
    }
}