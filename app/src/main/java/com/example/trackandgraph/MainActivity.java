package com.example.trackandgraph;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    // Compte le nombre de boutons dans l'activité
    private int nb_btn;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nb_btn = 2;

        //createNewBtnAct("oui oui oui");
    }

    /**
     * Cette méthode sert à créer le bouton d'une nouvelle activité une fois qu'on a validé le form
     * Dans activity_create
     * Le but ici est de générer un nouveau bouton activité avec le nom demandé en dessous du bouton
     * dernier bouton activité et de décaler le bouton de création en dessous de ce nouveau
     * Cependant, la création au run time d'un nouveau bouton et la gestion de l'affichage des différents
     * boutons que nous souhaitons déplacer ne semble pas être faisable de manière raisonnable dans
     * Android Studio.
     * N'ayant que peu de temps pour prendre en main l'outil et ses particularités, nous n'avons pas
     * trouvé de solutions pour remplir les besoins que nous avions
     * De plus, nous étions partis sur des fonctionnalités comme celle-ci car nous nous sommes inspirés
     * de ce que nous sommes capables de faire sur Unity qui permet un développement d'application
     * cross-plateformes et des travaux sur les vues au run time plus facile ainsi qu'un débuggage de
     * ce qui se passe sur les vues au run time natif. Nous perdons cependant par rapport à Android Studio,
     * les fonctions natives présentes sur Android (la flèche de retour en haut à gauche par exemple)
     * ainsi que les contraintes qui sont plus compliqués à mettre sur unity.
     * @param activity_name Le nom de l'activité à créer
     */
    private void createNewBtnAct(String activity_name) {

        // On récupère le layout de l'activité
        ConstraintLayout layout = (ConstraintLayout)findViewById(R.id.constaintLayout);

        // On récupère le bouton d'ajout d'activité et celui de la première activité
        Button addBtn = (Button)findViewById(R.id.btnAddAct);
        Button btn1 = (Button)findViewById(R.id.btnAct1);

        // On créé le nouveau bouton
        Button btn = new Button(this);
        btn.setId(nb_btn+1);
        btn.setText(activity_name);

        btn.setTextSize(20);
        // On récupère les paramètres de layout du bouton d'ajout
        ViewGroup.LayoutParams params = addBtn.getLayoutParams();
        ConstraintLayout.LayoutParams constrParams = new ConstraintLayout.LayoutParams(params);
        ConstraintSet set = new ConstraintSet();
        set.clone(layout);
        set.connect(btn.getId(), ConstraintSet.TOP ,addBtn.getId(), ConstraintSet.BOTTOM, 16);
        set.applyToLayoutParams(btn.getId(), constrParams);

        btn.setLayoutParams(constrParams);

        layout.addView(btn);

        System.out.println("On a appelé la fonction avec le nom " + activity_name);

    }

    @Override
    protected void onStart() {
        super.onStart();

        Intent intent = getIntent();

        boolean createNewBtn = intent.getBooleanExtra(CreateActivity.EXTRA_CREATENEW, false);
        String newBtnName = intent.getStringExtra(CreateActivity.EXTRA_NAME);

        if(createNewBtn){
            createNewBtnAct(newBtnName);

            // On remet à jour les valeurs de ces deux variables
            // Pour éviter de créer des boutons à tous les chargements de l'activité
            //createNewBtn = false;
            nb_btn++;
        }

    }

    /**
     * Cette méthode permet de retourner dans l'activité initiale (écran d'accueil)
     * Quand on clique sur le bouton créer nouvelle activité
     * @param createBtn le bouton créer
     */
    public void switchToCreateActivity(View createBtn){
        startActivity( new Intent(MainActivity.this, CreateActivity.class));
    }
}