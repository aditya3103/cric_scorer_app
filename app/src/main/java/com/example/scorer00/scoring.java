package com.example.scorer00;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class scoring extends AppCompatActivity {



    String scoreString = "Score: ";
    String oversString = "Overs: ";


    boolean firstInningscompleted = false;

    int timelineMaxSize = (MainActivity.overs * 6 + 30);
    int j = 0;

    String timelineString = "";
    boolean oversFinished = false;

    int target = 1000;

    int runs = 0;
    int wickets = 0;
    int overnum = 0;
    int ballsnum = 0;
    float rrr;

    int[] timeline = new int[MainActivity.overs * 6 + 30];

    public void xyz() {

        for (int i = 0; i < timelineMaxSize; ++i) {
            timeline[i] = -5;
        }
    }


    boolean didGameFinish = false;

    public void getTime(){
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat mdformat = new SimpleDateFormat("HH:mm:ss");
        String strDate =  mdformat.format(calendar.getTime()).toString();
      //  strDate=strDate.substring(0,strDate.length()-3);
        String hours= strDate.substring(0,2);
        String minutes= strDate.substring(3,5);
        Log.i("HH:mm", hours+":"+minutes);
        int hoursInt = Integer.parseInt(hours);
        int minutesInt = Integer.parseInt(minutes);

        if(hoursInt>12)
            hoursInt-=12;
        hoursInt=7-hoursInt-1;

        minutesInt=60-minutesInt;

        String timeLeft = "Time left: "+ String.valueOf(hoursInt)+":"+String.valueOf(minutesInt);


        TextView textView = (TextView)findViewById(R.id.timeText);
        textView.setText(timeLeft);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scoring);


        TextView batFirstTeamName = (TextView) findViewById(R.id.FieldingTeam);

        if (firstInningscompleted)
            batFirstTeamName.setText(MainActivity.BowlFirstTeam);
        else
            batFirstTeamName.setText(MainActivity.BatFirstTeam);

        TextView equation = (TextView) findViewById(R.id.equation);
        equation.setVisibility(View.INVISIBLE);




        Handler handler = new Handler();
        Runnable run = new Runnable() {
            @Override
            public void run() {
                getTime();
               // Log.i("Runnable", "Working");
                handler.postDelayed(this, 10000);
            }
        };

        handler.post(run);
    }

    public void scorer(View view) {
        if (didGameFinish == false&& j<=timelineMaxSize-10) {
            Log.i("RRR", String.valueOf(rrr));
            TextView score = (TextView) findViewById(R.id.BatFirstScore);
            TextView overs = (TextView) findViewById(R.id.BatFirstOvers);
            TextView balls = (TextView) findViewById(R.id.BatFirstBalls);

            Button button = (Button) view;
            int tag = Integer.parseInt(button.getTag().toString());


            if (overnum != MainActivity.overs) {
                switch (tag) {
                    case 0:
                        ++ballsnum;
                        timeline[j] = 0;
                        break;
                    case 1:
                        ++runs;
                        ++ballsnum;
                        timeline[j] = 1;
                        break;
                    case 2:
                        runs += 2;
                        ++ballsnum;
                        timeline[j] = 2;
                        break;
                    case 3:
                        runs += 3;
                        ++ballsnum;
                        timeline[j] = 3;
                        break;
                    case 4:
                        runs += 4;
                        ++ballsnum;
                        timeline[j] = 4;
                        break;
                    case 6:
                        runs += 6;
                        ++ballsnum;
                        timeline[j] = 6;
                        break;
                    case 7:
                        ++wickets;
                        ++ballsnum;
                        timeline[j] = 7;
                        break;
                    case 8:
                        ++runs;
                        timeline[j] = 8;
                        break;
                    case 9:
                        ++runs;
                        timeline[j] = 9;
                        break;

                    default:
                        Log.i("Info", "Entered Default in switch");

                }

                switch (timeline[j]) {
                    case 0:
                        timelineString += " . ";
                        break;
                    case 1:
                    case 2:
                    case 3:
                    case 4:
                    case 6:
                        timelineString += " " + String.valueOf(timeline[j]) + " ";
                        break;
                    case 7:
                        timelineString += " OUT ";
                        break;
                    case 8:
                        timelineString += " WIDE ";
                        break;
                    case 9:
                        timelineString += " NB ";
                        break;
                }


                if (ballsnum == 6) {
                    ++overnum;
                    ballsnum = 0;
                    timelineString += " | ";
                }
            } else {
                oversFinished = true;
                Toast.makeText(this, "Overs Finished", Toast.LENGTH_SHORT).show();
            }

            score.setText(scoreString + runs + "/" + wickets);
            overs.setText(oversString + overnum + ".");
            balls.setText(String.valueOf(ballsnum));

            TextView timeline = (TextView) findViewById(R.id.timeline);
            timeline.setText(timelineString);
if(firstInningscompleted){
            TextView equation = (TextView) findViewById(R.id.equation);
    rrr= (float)(((target-runs)*6.0)/((MainActivity.overs - (overnum + 1)) * 6 + (6 - ballsnum)));
    String rrrStr = String.format("%.2f", rrr);

            if (target - runs > 0 && ((MainActivity.overs - (overnum + 1)) * 6 + (6 - ballsnum)) > 0) {
                equation.setText(String.valueOf(target - runs) + " Runs required in " + ((MainActivity.overs - (overnum + 1)) * 6 + (6 - ballsnum)) + " Balls\n"+"Required Run Rate: "+rrrStr);
            } else {

                if (target - runs > 1 && ((MainActivity.overs - (overnum + 1)) * 6 + (6 - ballsnum)) == 0) {
                    equation.setText(MainActivity.BatFirstTeam + " Won the game");
                }
                if (target - runs <= 0 && ((MainActivity.overs - (overnum + 1)) * 6 + (6 - ballsnum)) >= 0) {
                    equation.setText(MainActivity.BowlFirstTeam + " Won the game !");
                }

                if (target - runs == 1 && ((MainActivity.overs - (overnum + 1)) * 6 + (6 - ballsnum)) == 0)
                    equation.setText(" Game Tied !");

                Button newMatch = (Button) findViewById(R.id.button3);
                newMatch.setEnabled(true);
                didGameFinish = true;

            }
        }

            ++j;
        } else {
            if(j>=timelineMaxSize-10)
                Toast.makeText(this, "Bowl Properly", Toast.LENGTH_SHORT).show();
            if(didGameFinish)
            Toast.makeText(this, "Game has finished", Toast.LENGTH_SHORT).show();
        }
    }

    public void nextInnings(View view) {
        if (firstInningscompleted ==false) {
            firstInningscompleted = true;
            target = runs + 1;
            runs = 0;
            wickets = 0;
            overnum = 0;
            ballsnum = 0;
            timelineString = "DNS";
            j = 0;
            xyz();
            rrr= (float)(((target-runs)/((MainActivity.overs - (overnum + 1)) * 6 + (6 - ballsnum)))*6.0);


            TextView timeline = (TextView) findViewById(R.id.timeline);
            timeline.setText(timelineString);
            timelineString = "";


            TextView equation = (TextView) findViewById(R.id.equation);
            equation.setVisibility(View.VISIBLE);

            TextView score = (TextView) findViewById(R.id.BatFirstScore);
            TextView overs = (TextView) findViewById(R.id.BatFirstOvers);
            TextView balls = (TextView) findViewById(R.id.BatFirstBalls);

            score.setText(scoreString + runs + "/" + wickets);
            overs.setText(oversString + overnum + ".");
            balls.setText(String.valueOf(ballsnum));
            rrr= (float)(((target-runs)*6.0)/((MainActivity.overs - (overnum + 1)) * 6 + (6 - ballsnum)));
            String rrrStr = String.format("%.2f", rrr);

            equation.setText((target - runs) + " Runs required in " + ((MainActivity.overs - (overnum + 1)) * 6 + (6 - ballsnum)) + " Balls\n"+"Required Run Rate: "+String.valueOf(rrrStr));
            // Button nextInningsButton = (Button) findViewById(R.id.button3);
            //  nextInningsButton.setEnabled(false);

            TextView batFirstTeamName = (TextView) findViewById(R.id.FieldingTeam);
            batFirstTeamName.setText(MainActivity.BowlFirstTeam);

            Button button = (Button) findViewById(R.id.button3);
            button.setText("New\nmatch");
            button.setEnabled(false);
            button.setBackgroundColor(Color.parseColor("#FFFF00"));
            //firstInningscompleted=true;
        } else {
            Intent i = new Intent(this, MainActivity.class);
            didGameFinish=false;
            firstInningscompleted=false;
            startActivity(i);

        }


    }

    public void undo(View view) {
        if (j > 0) {
            if(didGameFinish)
                didGameFinish=false;
            if (ballsnum == 0) {
                timelineString = timelineString.substring(0, timelineString.length() - 3);
            }

            switch (timeline[--j]) {
                case 0:
                case 1:
                case 2:
                case 3:
                case 4:
                case 6:
                    timelineString = timelineString.substring(0, timelineString.length() - 3);
                    runs -= timeline[j];
                    if (ballsnum == 0) {
                        ballsnum = 5;
                        --overnum;
                    } else {
                        --ballsnum;
                    }
                    break;
                case 7:
                    timelineString = timelineString.substring(0, timelineString.length() - 5);
                    --wickets;
                    if (ballsnum == 0) {
                        ballsnum = 5;
                        --overnum;
                    } else {
                        --ballsnum;
                    }
                    break;
                case 8:
                    timelineString = timelineString.substring(0, timelineString.length() - 6);
                    --runs;
                    break;
                case 9:
                    timelineString = timelineString.substring(0, timelineString.length() - 4);
                    --runs;
                    break;
            }

            TextView timeline = (TextView) findViewById(R.id.timeline);
            timeline.setText(timelineString);

            TextView score = (TextView) findViewById(R.id.BatFirstScore);
            TextView overs = (TextView) findViewById(R.id.BatFirstOvers);
            TextView balls = (TextView) findViewById(R.id.BatFirstBalls);

            score.setText(scoreString + runs + "/" + wickets);
            overs.setText(oversString + overnum + ".");
            balls.setText(String.valueOf(ballsnum));

            TextView equation = (TextView) findViewById(R.id.equation);
            rrr= (float)(((target-runs)*6.0)/((MainActivity.overs - (overnum + 1)) * 6 + (6 - ballsnum)));
            String rrrStr = String.format("%.2f", rrr);

            equation.setText((target - runs) + " Runs required in " + ((MainActivity.overs - (overnum + 1)) * 6 + (6 - ballsnum)) + " Balls\n"+"Required Run Rate: "+String.valueOf(rrrStr));


        }
        else {
            Toast.makeText(this, "Consult a Doctor", Toast.LENGTH_SHORT).show();
        }
    }


//    public void getCurrentTime(View view) {
//        Calendar calendar = Calendar.getInstance();
//        SimpleDateFormat mdformat = new SimpleDateFormat("HH:mm:ss");
//        String strDate = "Current Time : " + mdformat.format(calendar.getTime());
//    }



    }


