package com.example.trackandgraph;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class CreateActivity extends AppCompatActivity {

    public static final String EXTRA_NAME = "com.example.trackandgraph.extra.NAME";
    public static final String EXTRA_CREATENEW = "com.example.trackandgraph.extra.CREATENEW";

    private EditText name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);


        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.spinner_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);

        Spinner spinner2 = (Spinner) findViewById(R.id.spinner2);
        spinner2.setAdapter(adapter);

        name = findViewById(R.id.editActivityName);
    }

    /**
     * Cette méthode permet de retourner dans l'activité initiale (écran d'accueil)
     * Quand on clique sur le bouton annuler
     * @param cancelBtn le bouton annuler
     */
    public void switchBackToMainCancel(View cancelBtn){
        startActivity( new Intent(CreateActivity.this, MainActivity.class));
    }

    /**
     * Cette méthode permet de retourner dans l'activité initiale (écran d'accueil)
     * Quand on clique sur le bouton valider
     * @param validateBtn le bouton valider
     */
    public void switchBackToMainValidate(View validateBtn){
        String activityName = name.getText().toString();

        Intent intent = new Intent(CreateActivity.this, MainActivity.class);

        intent.putExtra(EXTRA_NAME, activityName);
        intent.putExtra(EXTRA_CREATENEW, true);

        startActivity(intent );
    }


}