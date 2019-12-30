package com.example.n550j.acarreos;

import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.n550j.acarreos.Objetos.Usuario;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CrearCuentaActivity extends AppCompatActivity {
    private FirebaseAuth firebaseAuth;
    EditText edtNombre;
    EditText edtApellidos;
    EditText edtCelular;
    EditText edtContrasena;
    EditText edtEmailCrearCuenta;
    Button btnCrearCuenta;

    /*Firebase */

    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_cuenta);
        iniciarElementosGUI();
        getSupportActionBar().setTitle("Crear cuenta");

        /*Activar la persistencia de firebase para cuando no haya internet*/
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
        databaseReference=FirebaseDatabase.getInstance().getReference(); //obteniendo la referencia de la base en firebase   acarreos-38daf

        btnCrearCuenta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             if( validarDatos()){

                 crearUsuario(edtNombre.getText().toString(),edtEmailCrearCuenta.getText().toString(),edtContrasena.getText().toString(),
                         edtApellidos.getText().toString(),edtCelular.getText().toString(),"73Yx2A2DdHNllQkRLunqz0s4jUp1");
                 Snackbar.make(v, "Creamos tu cuenta, buen acarreo", Snackbar.LENGTH_LONG).show();
             };

            }
        });
    }

    public void  crearUsuario(String nombre, String email, String contrasena, String apellido, String telefono, String id_u){
        /*Creamos el objeto usuario*/
        Usuario usuario= new Usuario(databaseReference.push().getKey(),nombre,email,contrasena,apellido,telefono,id_u);
        /*Obtenemos al hijo "Usuarios"de la referencia acarreos-38daf*/
        databaseReference.child("Usuarios").child(usuario.getIdentificador()).setValue(usuario);

    }

    private void createAcount(String email, String password){
        // iniciarCrearCuenta();


        firebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(CrearCuentaActivity.this, "Cuenta creada" ,Toast.LENGTH_SHORT).show();

                    Log.e("------------->",""+firebaseAuth.getCurrentUser().getUid());
                }else {
                    Log.e("FALAL", "FAlla",task.getException());

                    Toast.makeText(CrearCuentaActivity.this, "No se pudo crear la cuenta" ,Toast.LENGTH_SHORT).show();

                }
            }
        });

    }



    public  void iniciarElementosGUI(){
        btnCrearCuenta= (Button) findViewById(R.id.btnCrearCuenta);
        edtNombre= (EditText) findViewById(R.id.edtNombre);
        edtApellidos= (EditText) findViewById(R.id.edtApellido);
        edtCelular= (EditText) findViewById(R.id.edtCelular);
        edtContrasena= (EditText) findViewById(R.id.edtContrasena);
        edtEmailCrearCuenta= (EditText) findViewById(R.id.edtEmailCrearCuenta);

    }
    public boolean validarDatos(){
        Boolean validar=true;

       if(edtNombre.getText().toString().isEmpty()){
           edtNombre.setError("ingrese un nombre valido");
            validar=false;
        }else if(edtApellidos.getText().toString().isEmpty()){
           edtApellidos.setError("ingrese un apellido valido");
            validar=false;
        }else if(edtCelular.getText().toString().isEmpty()){
           edtCelular.setError("ingrese un número de celular valido");
            validar=false;
        }else if(edtEmailCrearCuenta.getText().toString().isEmpty()){
           edtEmailCrearCuenta.setError("ingrese un email valido");
            validar=false;
        }else if(edtContrasena.getText().toString().isEmpty()){
           edtContrasena.setError("ingrese una contraseña valido");
            validar=false;
        }
        return validar;

    }



}
