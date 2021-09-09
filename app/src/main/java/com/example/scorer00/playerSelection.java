package com.example.scorer00;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class playerSelection extends AppCompatActivity {

    int todaysPlayers = 0;
    int i = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_selection);


        ArrayList<String> totalPlayers = new ArrayList<String>(Arrays.asList("Aditya", "Hemanth", "Vikas", "Krithiman", "Sai Manish", "Amartya", "Shreyaj", "Sai Charan", "Daksh", "Chikku", "Kuber"));
        totalPlayers.add("Anto");
        totalPlayers.add("Appu");
        totalPlayers.add("Ashish");
        totalPlayers.add("Amit");
        totalPlayers.add("Rohan");


        ListView listView = findViewById(R.id.allPlayersList);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, totalPlayers);

        listView.setAdapter(arrayAdapter);
        ArrayList<String> selectedPlayers = new ArrayList<String>();

        int[] playerindex = new int[totalPlayers.size()];

        for (int i = 0; i < totalPlayers.size(); ++i) {
            playerindex[i] = -1;
        }

        i = 0;


    }
}