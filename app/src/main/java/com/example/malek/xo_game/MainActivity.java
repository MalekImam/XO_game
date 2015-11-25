package com.example.malek.xo_game;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    int m_Array_XO[][],m_PlayerTurn,m_GameEnd,m_winer,m_NumberRound,m_PlayerXscore,m_PlayerOscore,m_NumberOfClicks,m_Array_clickedBtn[];
    Random m_RandomBegin;
    Button m_Clicked;
    TextView m_ScoreText;
    String m_PWinner;
    //First Player = X
    //Second Player = O

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        m_Array_XO=new int [3][3];
        m_Array_clickedBtn = new int[9];
        //Arrays.fill(m_Array_XO,-1);

        for(int i=0;i<3;i++)
            for(int j=0;j<3;j++)
                m_Array_XO[i][j]=-1;

        for(int i=0;i<9;i++)
            m_Array_clickedBtn[i]=0;

        m_RandomBegin= new Random();
        m_PlayerTurn= m_RandomBegin.nextInt(2);
        m_GameEnd=0;
        m_NumberRound=0;
        m_PlayerXscore=0;
        m_PlayerOscore=0;
        m_NumberOfClicks=0;
    }

    public void btn1_click(View view) {
        play(0,0);
    }

    public void btn2_click(View view) {
        play(0,1);
    }

    public void btn3_click(View view) {
        play(0,2);
    }

    public void btn4_click(View view) {
        play(1,0);
    }

    public void btn5_click(View view) {
        play(1,1);
    }

    public void btn6_click(View view) {
        play(1,2);
    }

    public void btn7_click(View view) {
        play(2,0);
    }

    public void btn8_click(View view) { play(2,1); }

    public void btn9_click(View view) { play(2,2); }

    private void play(int m_Row, int m_Column) {

        if(m_GameEnd==1)
            return;
        if(m_Array_XO[m_Row][m_Column]== -1)
        {
            if(m_Row==0 && m_Column==0)//////////////////////////////////////////////////////////
            {
                if(m_Array_clickedBtn[0]==0)
                {
                    m_Array_clickedBtn[0]=1;
                    m_NumberOfClicks++;
                }
                m_Clicked = (Button)findViewById(R.id.mBtn1);
            }
            else if(m_Row==0 && m_Column==1)
            {
                m_Clicked = (Button)findViewById(R.id.mBtn2);

                if(m_Array_clickedBtn[1]==0)
                {
                    m_Array_clickedBtn[1]=1;
                    m_NumberOfClicks++;
                }
            }
            else if(m_Row==0 && m_Column==2)
            {
                m_Clicked = (Button)findViewById(R.id.mBtn3);

                if(m_Array_clickedBtn[2]==0)
                {
                    m_Array_clickedBtn[2]=1;
                    m_NumberOfClicks++;
                }
            }
            else if(m_Row==1 && m_Column==0)//////////////////////////////////////////////////////////
            {
                m_Clicked = (Button)findViewById(R.id.mBtn4);

                if(m_Array_clickedBtn[3]==0)
                {
                    m_Array_clickedBtn[3]=1;
                    m_NumberOfClicks++;
                }
            }
            else if(m_Row==1 && m_Column==1)
            {
                m_Clicked = (Button)findViewById(R.id.mBtn5);

                if(m_Array_clickedBtn[4]==0)
                {
                    m_Array_clickedBtn[4]=1;
                    m_NumberOfClicks++;
                }
            }
            else if(m_Row==1 && m_Column==2)
            {
                m_Clicked = (Button)findViewById(R.id.mBtn6);

                if(m_Array_clickedBtn[5]==0)
                {
                    m_Array_clickedBtn[5]=1;
                    m_NumberOfClicks++;
                }
            }
            else if(m_Row==2 && m_Column==0)//////////////////////////////////////////////////////////
            {
                m_Clicked = (Button)findViewById(R.id.mBtn7);

                if(m_Array_clickedBtn[6]==0)
                {
                    m_Array_clickedBtn[6]=1;
                    m_NumberOfClicks++;
                }
            }
            else if(m_Row==2 && m_Column==1)
            {
                m_Clicked = (Button)findViewById(R.id.mBtn8);

                if(m_Array_clickedBtn[7]==0)
                {
                    m_Array_clickedBtn[7]=1;
                    m_NumberOfClicks++;
                }
            }
            else if(m_Row==2 && m_Column==2)
            {
                m_Clicked = (Button)findViewById(R.id.mBtn9);

                if(m_Array_clickedBtn[8]==0)
                {
                    m_Array_clickedBtn[8]=1;
                    m_NumberOfClicks++;
                }
            }

            if(m_PlayerTurn==0)
            {
                m_Clicked.setText("O");
                m_Clicked.setTextColor(Color.BLUE);
                m_Array_XO[m_Row][m_Column]=0;
            }
            else
            {
                m_Clicked.setText("X");
                m_Clicked.setTextColor(Color.RED);
                m_Array_XO[m_Row][m_Column]=1;
            }
            m_Clicked.setEnabled(false);
            m_PlayerTurn^=1;
        }
        //print_mya();


        if(isEnd())
        {
            m_NumberRound++;
            if(m_winer==1)//Player X
            {
                //m_PWinner="Player 1 Is The Winner";
                m_PWinner="Player 1 Win This Round";
                m_PlayerXscore++;
                m_ScoreText = (TextView)findViewById(R.id.m_XScore);
                m_ScoreText.setText(m_PlayerXscore + "");
                if(m_PlayerXscore<5)
                    RoundFinished();
                Toast.makeText(getApplicationContext(),m_PWinner,Toast.LENGTH_SHORT).show();
            }
            else//Player O
            {
                //m_PWinner="Player 2 Is The Winner";
                m_PWinner="Player 2 Win This Round";
                m_PlayerOscore++;
                m_ScoreText = (TextView)findViewById(R.id.m_OScore);
                m_ScoreText.setText(m_PlayerOscore+"");
                if(m_PlayerOscore<5)
                    RoundFinished();
                Toast.makeText(getApplicationContext(),m_PWinner,Toast.LENGTH_SHORT).show();
            }

            if(m_PlayerOscore==5 || m_PlayerXscore==5)
            {
                m_GameEnd=1;
                if(m_PlayerOscore==5)
                    m_PWinner="Player 2 Is The Winner";
                else
                    m_PWinner="Player 1 Is The Winner";
                Toast.makeText(getApplicationContext(),m_PWinner, Toast.LENGTH_LONG).show();
            }
            Log.v("MyTag", m_PWinner + "");

        }
        else if(m_NumberOfClicks==9)
        {
            m_PWinner="No Winner In This Round ( Tie )";
            RoundFinished();
            Toast.makeText(getApplicationContext(),m_PWinner,Toast.LENGTH_SHORT).show();
        }

    }

    private void print_mya() {
        for(int i=0;i<3;i++)
            for(int j=0;j<3;j++)
                Log.v("MyTag",m_Array_XO[i][j]+"");
    }

    private boolean isEnd() {

        int m_isend;
        for(int i=0;i<3;i++)
        {
            m_isend=1;
            for(int j=1;j<3;j++)
            {
                if (m_Array_XO[i][j] != m_Array_XO[i][j - 1] || m_Array_XO[i][j-1]==-1 ) {
                    m_isend = 0;
                    break;
                }
            }
           if(m_isend==1)
           {
               m_winer=m_Array_XO[i][0];
              return true;
           }
        }

        for(int i=0;i<3;i++)
        {
            m_isend=1;
            for(int j=1;j<3;j++)
            {
                if (m_Array_XO[j][i] != m_Array_XO[j-1][i] || m_Array_XO[j-1][i]==-1 ) {
                    m_isend = 0;
                    break;
                }
            }
            if(m_isend==1)
            {
                m_winer=m_Array_XO[0][i];
                return true;
            }
        }
        m_isend=0;

        if(m_Array_XO[0][0] == m_Array_XO[1][1]  && m_Array_XO[1][1] == m_Array_XO[2][2] && m_Array_XO[1][1]!=-1 ) //  Diagonal 1
        {
            m_winer=m_Array_XO[1][1];
            m_isend=1;
        }

        if(m_Array_XO[0][2] == m_Array_XO[1][1]  && m_Array_XO[1][1] == m_Array_XO[2][0]  && m_Array_XO[1][1]!=-1)  //  Diagonal 2
        {
            m_winer=m_Array_XO[1][1];
            m_isend=1;
        }

        if(m_isend==1)
            return  true;

        return false;
    }

    public void reset_game(View view) {

        ClearButton();
        ClearScore();
        ClearArray();

        m_PlayerOscore=0;
        m_PlayerXscore=0;
        m_NumberRound=0;
        m_GameEnd=0;
        m_NumberOfClicks=0;
    }

    private void ClearScore() {
        m_ScoreText = (TextView)findViewById(R.id.m_OScore);
        m_ScoreText.setText("0");
        m_ScoreText = (TextView)findViewById(R.id.m_XScore);
        m_ScoreText.setText("0");
    }

    private  void ClearButton() {
        m_Clicked = (Button)findViewById(R.id.mBtn1);
        ClearAll();
        m_Clicked = (Button)findViewById(R.id.mBtn2);
        ClearAll();
        m_Clicked = (Button)findViewById(R.id.mBtn3);
        ClearAll();
        m_Clicked = (Button)findViewById(R.id.mBtn4);
        ClearAll();
        m_Clicked = (Button)findViewById(R.id.mBtn5);
        ClearAll();
        m_Clicked = (Button)findViewById(R.id.mBtn6);
        ClearAll();
        m_Clicked = (Button)findViewById(R.id.mBtn7);
        ClearAll();
        m_Clicked = (Button)findViewById(R.id.mBtn8);
        ClearAll();
        m_Clicked = (Button)findViewById(R.id.mBtn9);
        ClearAll();
    }

    private void ClearAll() {
        m_Clicked.setEnabled(true);
        m_Clicked.setText("");
    }

    private void ClearArray() {
        for(int i=0;i<3;i++)
            for(int j=0;j<3;j++)
                m_Array_XO[i][j]=-1;

        m_NumberOfClicks=0;
        for(int i=0;i<9;i++)
            m_Array_clickedBtn[i]=0;
    }

    private void RoundFinished() {
        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which){
                    case DialogInterface.BUTTON_POSITIVE:
                        //Leave button clicked
                        Intent intent = new Intent(Intent.ACTION_MAIN);
                        intent.addCategory(Intent.CATEGORY_HOME);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                        break;

                    case DialogInterface.BUTTON_NEGATIVE:
                        //Continue button clicked
                        ClearButton();
                        ClearArray();
                        break;
                }
            }
        };

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Round Findished......").setPositiveButton("Leave", dialogClickListener)
                .setNegativeButton("Continue", dialogClickListener).show();
    }

}

