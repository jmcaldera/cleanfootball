package com.jmcaldera.cleanfootball.competitions;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.jmcaldera.cleanfootball.R;

/**
 * Se mostrara la lista de competiciones segun la respuesta de
 * http://api.football-data.org/v1/competitions
 * Cada competicion tiene un id y currentMatchday. Estos seran
 * pasados a la actividad de Competicion para solicitar el
 * calendario y la tabla de posiciones
 */
public class CompetitionsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_competitions);
    }
}
