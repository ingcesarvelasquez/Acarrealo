package com.example.n550j.acarreos;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private  static final String TAG ="MainActivity";
    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener authStateListener;

    private Button btnCreateCount;
    private  Button btnSignin;

    private EditText edtEmail;
    private EditText edtPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        //getSupportActionBar().setTitle("Acarrealo");


        setContentView(R.layout.activity_main);
        btnCreateCount  =(Button)findViewById(R.id.btnCreateCount);
        btnSignin       = (Button) findViewById(R.id.btnSignIn);

        edtEmail        = (EditText) findViewById(R.id.edtEmail);
        edtPassword     = (EditText) findViewById(R.id.edtPassword);

        inicialize();
        btnCreateCount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iniciarCrearCuenta();
              //  createAcount(edtEmail.getText().toString(), edtPassword.getText().toString());

            }
        });

        btnSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn(edtEmail.getText().toString(), edtPassword.getText().toString());
            }
        });
    }
    private   void inicialize(){
        firebaseAuth =FirebaseAuth.getInstance();
        authStateListener=new FirebaseAuth.AuthStateListener(){


            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser firebaseUser  =firebaseAuth.getCurrentUser();
                if(firebaseUser!=null){
                    Log.w(TAG, "onAuthStateChanged-signed_in "+ firebaseUser.getUid());
                    Log.w(TAG, "onAuthStateChanged-signed_in "+ firebaseUser.getEmail());

                }else {
                    Log.w(TAG, "onAuthStateChanged-signed_out ");
                }

            }
        };
    }
    private void signIn(String email, String password){

        firebaseAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(MainActivity.this, "Autenticado" ,Toast.LENGTH_SHORT).show();
                    iniciarActivity();
                }else {
                    Toast.makeText(MainActivity.this, "No autenticado" ,Toast.LENGTH_SHORT).show();

                }

            }
        });


    }
    public void iniciarActivity() {
        Intent intent = new Intent(this, ElegirServicioActivity.class);
        //     EditText editText = (EditText) findViewById(R.id.edit_message);
        //  String message = editText.getText().toString();
        intent.putExtra("EXTRA_MESSAGE", "DD");
        startActivity(intent);
    }
    public void iniciarCrearCuenta() {
        Intent intent = new Intent(this, CrearCuentaActivity.class);
        //     EditText editText = (EditText) findViewById(R.id.edit_message);
        //  String message = editText.getText().toString();
        intent.putExtra("EXTRA_MESSAGE", "DD");
        startActivity(intent);
    }
    private void createAcount(String email, String password){
       // iniciarCrearCuenta();
       firebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(MainActivity.this, "Cuenta creada" ,Toast.LENGTH_SHORT).show();


                    Log.e("------------->",""+firebaseAuth.getCurrentUser().getUid());
                }else {
                    Log.e("FALAL", "FAlla",task.getException());

                    Toast.makeText(MainActivity.this, "No se pudo crear la cuenta" ,Toast.LENGTH_SHORT).show();

                }
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        firebaseAuth.addAuthStateListener(authStateListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        firebaseAuth.removeAuthStateListener(authStateListener);
    }
}
