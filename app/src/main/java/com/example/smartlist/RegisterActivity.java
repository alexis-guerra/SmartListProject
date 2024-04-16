package com.example.smartlist;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.smartlist.Activity.MainActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;      
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {

    Button btn_register;
    EditText correo, contraseña, password;
    FirebaseFirestore mFirestore;
    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register2);

        mFirestore=FirebaseFirestore.getInstance();
        mAuth=FirebaseAuth.getInstance();

        correo = findViewById(R.id.correo);
        contraseña= findViewById(R.id.contraseña);
        password=findViewById(R.id.password);
        btn_register=findViewById(R.id.btn_registro);

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String correoUser= correo.getText().toString().trim();
                String contraseñaUser= contraseña.getText().toString().trim();
                String passwordUser=password.getText().toString().trim();

                if(correoUser.isEmpty() && contraseñaUser.isEmpty() && passwordUser.isEmpty()){
                    Toast.makeText(RegisterActivity.this,"complete los datos",Toast.LENGTH_SHORT).show();
                }else {
                    registerUser(correoUser,contraseñaUser,passwordUser);
                }
            }
        });
    }

    private void registerUser(String correoUser, String contraseñaUser, String passwordUser) {
    mAuth.createUserWithEmailAndPassword(correoUser,contraseñaUser).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
        @Override
        public void onComplete(@NonNull Task<AuthResult> task) {
            String id= mAuth.getCurrentUser().getUid();
            Map<String, Object> map =new HashMap<>();
            map.put("id",id);
            map.put("correo",correoUser);
            map.put("contraseña",contraseñaUser);
            map.put("password",passwordUser);

            mFirestore.collection("user").document(id).set(map).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void unused) {
                finish();
                startActivity(new Intent(RegisterActivity.this, MainActivity.class));
                Toast.makeText(RegisterActivity.this,"Usuario registrado con exito",Toast.LENGTH_SHORT).show();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(RegisterActivity.this,"Error al guardar", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }).addOnFailureListener(new OnFailureListener() {
        @Override
        public void onFailure(@NonNull Exception e) {
        Toast.makeText(RegisterActivity.this, "Error al registrarse", Toast.LENGTH_SHORT).show();
        }
    });
    }
}