package com.example.juanjo.punchpower;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.airbnb.lottie.LottieAnimationView;



public class PantallaDatos extends AppCompatActivity {


    Button btnValida,btnAcepta;
    LottieAnimationView lottieAnimationView;
    EditText ednombre;
    static String nombre;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_datos);
        btnValida=(Button)findViewById(R.id.btnValidarNombre);
        btnAcepta=(Button)findViewById(R.id.btnAceptar);
        ednombre=(EditText)findViewById(R.id.editTextNombre);
        lottieAnimationView=(LottieAnimationView)findViewById(R.id.animation_view);
        lottieAnimationView.setAnimation("check_in_.json");


        btnValida.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!ednombre.getText().equals(""))
                lottieAnimationView.playAnimation();

            }
        });

        btnAcepta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              nombre=ednombre.getText().toString();
            }
        });


    }
}
