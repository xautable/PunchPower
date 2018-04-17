package com.example.juanjo.punchpower;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;



public class PantallaDatos extends AppCompatActivity {


    Button btnValida,btnAcepta;
    LottieAnimationView lottieAnimationView;
    EditText ednombre;
    static String nombre;
    int puntos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_datos);
        btnValida=(Button)findViewById(R.id.btnValidarNombre);
        btnAcepta=(Button)findViewById(R.id.btnAceptar);
        ednombre=(EditText)findViewById(R.id.editTextNombre);
        lottieAnimationView=(LottieAnimationView)findViewById(R.id.animation_view);
        lottieAnimationView.setAnimation("check_in_.json");
        final BaseDeDatos jugador=new BaseDeDatos(this,"Record",null,1);
        puntos=PantallaJuego.puntuacion;

        btnValida.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!ednombre.getText().equals("")) {
                    nombre = ednombre.getText().toString();
                    lottieAnimationView.playAnimation();
                    SQLiteDatabase bd = jugador.getReadableDatabase();
                    Cursor cursor = bd.rawQuery("SELECT * FROM Record",null);
                    Toast.makeText(PantallaDatos.this,"aaa"+cursor,Toast.LENGTH_LONG).show();

                }
            }
        });

        btnAcepta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SQLiteDatabase db=jugador.getWritableDatabase();
                db.execSQL("INSERT INTO Record(Puntos,Nombre) VALUES("+puntos+",'"+nombre+"')");
                db.close();
                ednombre.setText("");
                ControlEscenas.pantallaActual=0;
            }
        });


    }
}
