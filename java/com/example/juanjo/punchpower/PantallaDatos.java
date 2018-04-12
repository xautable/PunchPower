package com.example.juanjo.punchpower;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.airbnb.lottie.LottieAnimationView;



public class PantallaDatos extends AppCompatActivity {


    Button btnValida,btnAcepta;
    LottieAnimationView lottieAnimationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_datos);
        btnValida=(Button)findViewById(R.id.btnValidarNombre);
        btnAcepta=(Button)findViewById(R.id.btnAceptar);
        lottieAnimationView=(LottieAnimationView)findViewById(R.id.animation_view);
        lottieAnimationView.setAnimation("check_in_.json");

        btnValida.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lottieAnimationView.playAnimation();
            }
        });
    }
}
