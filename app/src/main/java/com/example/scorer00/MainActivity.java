package com.example.scorer00;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
   public static int overs;
    String TeamNameA, TeamNameB;
    public static String BatFirstTeam;
    public static  String BowlFirstTeam;
    boolean didTossHappen = false;
    String tossWinner;
    String tossLoser;
    int actionID;
    String action = "hi";




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button randomize = findViewById(R.id.Randomize);
        randomize.setVisibility(View.INVISIBLE);
        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.options);
        Button scoreButton = (Button)findViewById(R.id.button2);

        scoreButton.setEnabled(false);


        radioGroup.setEnabled(didTossHappen);

        TextView textView = (TextView) findViewById(R.id.overnum);

        SeekBar seekBar = findViewById(R.id.over);
        seekBar.setMax(20);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                int min = 1;
                if (progress < min) {
                    seekBar.setProgress(min);
                }
                textView.setText(Integer.toString(progress));
                overs = progress;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    public void start(View view) {
//        EditText teamA = (EditText) findViewById(R.id.teamA);
//        TeamNameA = teamA.getText().toString();
//        EditText teamB = (EditText) findViewById(R.id.teamB);
//        TeamNameB = teamB.getText().toString();
//
//        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.options);
//
//        RadioButton selectedRadioButton = (RadioButton) findViewById(radioGroup.getCheckedRadioButtonId());
//        String abc = selectedRadioButton.getText().toString();
//
//        if (abc.equals("Batting")) {
//            action = "Batting";
//            BatFirstTeam=tossWinner;
//        }
//        else {
//            action = "Bowling";
//BatFirstTeam= tossLoser;
//        }
//
//
//        Log.i("Team A name", TeamNameA);
//        Log.i("Team B name", TeamNameB);
//        Log.i("Overs", Integer.toString(overs));
//        Log.i("Toss Winner", tossWinner);
//        Log.i("Action", action);

          Intent intent =new Intent(this, playerSelection.class);
          startActivity(intent);
    }

    public void toss(View view) {


        EditText teamA = (EditText) findViewById(R.id.teamA);
        TeamNameA = teamA.getText().toString();
        EditText teamB = (EditText) findViewById(R.id.teamB);
        TeamNameB = teamB.getText().toString();

        Random random = new Random();
        int num = random.nextInt(20) + 1;
        Log.i("Random Number", Integer.toString(num));
        TextView tosswinner = (TextView) findViewById(R.id.TossWinner);
        boolean didAwin;
        if (num % 2 == 1) {
            didAwin = true;
        } else {
            didAwin = false;
        }
        String defaultToss = "Toss Won By: ";

        if (didAwin) {
            tosswinner.setText(defaultToss + TeamNameA);
            tossWinner = TeamNameA;
            tossLoser = TeamNameB;
        } else {
            tosswinner.setText(defaultToss + TeamNameB);
            tossWinner = TeamNameB;
            tossLoser= TeamNameA;
        }


        didTossHappen = true;

        if (didTossHappen) {
            Button tossButton = (Button) findViewById(R.id.tosser);
            tossButton.setEnabled(false);
        }
        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.options);
        Button scoreButton = (Button)findViewById(R.id.button2);

       radioGroup.setEnabled(didTossHappen);
       scoreButton.setEnabled(didTossHappen);

    }

    public void test(View view){

        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.options);

        RadioButton selectedRadioButton = (RadioButton) findViewById(radioGroup.getCheckedRadioButtonId());
        String abc = selectedRadioButton.getText().toString();

        if (abc.equals("Batting")) {
            action = "Batting";
            BatFirstTeam=tossWinner;
            BowlFirstTeam=tossLoser;
        }
        else {
            action = "Bowling";
            BatFirstTeam= tossLoser;
            BowlFirstTeam= tossWinner;
        }

        Log.i("Bat First Team", BatFirstTeam);
        Log.i("Bowl First Team", BowlFirstTeam);
        Log.i("Team A name", TeamNameA);
        Log.i("Team B name", TeamNameB);
        Log.i("Overs", Integer.toString(overs));
        Log.i("Toss Winner", tossWinner);
        Log.i("Action", action);
        Intent test = new Intent(this, scoring.class);
        startActivity(test);

    }

    public class playerInfo{
        String name;
        int playerID;
        int runsScored;
        int ballsFaced;
        int sixesScored;
        int foursScored;
        int ballsBowled;
        int wickets;
        int catches;
        int dismissals;
        int inningsPlayed;
        int matchesPlayed;
        int dismissed;

        float batAvg= runsScored/dismissed;
        float strikeRate= (runsScored/ballsFaced)*100;
    }

    public static ArrayList<String> plName = new ArrayList<>();
    public static ArrayList<Integer> plRating = new ArrayList<>();

    public void randomize(View view){
        plName.add("Aditya");
        plName.add("Hemanth");
        plName.add("Vikas");
        plName.add("Krithiman");
        plName.add("Ashish");
        plName.add("Sai Manish");
        plName.add("Amartya");
        plName.add("Rohan");
        plName.add("Amit");
        plName.add("Daksh");
        plName.add("Kuber");
        plName.add("Appu");

       // Log.i("Size", String.valueOf(plName.size()));


        plRating.add(5);
        plRating.add(5);
        plRating.add(4);
        plRating.add(4);
        plRating.add(4);
        plRating.add(3);
        plRating.add(2);
        plRating.add(3);
        plRating.add(3);
        plRating.add(2);
        plRating.add(3);
        plRating.add(4);

        ArrayList<String> teamA = new ArrayList<>();
        ArrayList<String> teamB = new ArrayList<>();
        ArrayList<Integer> arrA = new ArrayList<>();
        ArrayList<Integer> arrB = new ArrayList<>();

        for(int i=0; i<12; ++i){
            arrA.add(-2);
            arrB.add(-2);
        }
        Random rand1 = new Random();
        Random rand2 = new Random();
        int aRating=1, bRating=10;


        while (aRating-bRating>-2||aRating-bRating<2) {
aRating=0;
bRating=0;
            int r1=-1, r2=-1;
            for (int i = 0; i < 6; ++i) {
                r1 = rand1.nextInt(plName.size());
                r2 = rand2.nextInt(plName.size());
                if (arrA.contains(r1)) {
                    while (arrA.contains(r1)) {
                        r1 = rand1.nextInt();
                    }
                }

                if (arrA.contains(r2)) {
                    while (arrA.contains(r2)) {
                        r2 = rand2.nextInt();
                    }
                }

                teamA.add(plName.get(r1).toString());
                teamB.add(plName.get(r2).toString());

                aRating += plRating.get(r1);
                bRating += plRating.get(r2);
            }

        }

        for(int i=0; i<6; ++i) {
            Log.i("Team A", teamA.get(i));
        }

        for(int i=0; i<6; ++i) {

            Log.i("Team B", teamB.get(i));
        }
    }

}

