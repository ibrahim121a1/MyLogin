package com.example.mylogin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SigninAct extends AppCompatActivity {

    private EditText EmailEt;
    private EditText passwordEt;
    private Button objectsignin_btn;
    private FirebaseAuth objectfirebaseauth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
        convertxmltojava();
        objectfirebaseauth=FirebaseAuth.getInstance();

    }

    private void convertxmltojava()
    {
        EmailEt=findViewById(R.id.Signin_ET);
        passwordEt=findViewById(R.id.Signin_password_ET);
        objectsignin_btn=findViewById(R.id.Signin_btn);
    }

    private void signinuser()
    {
        try
        {
            if (!EmailEt.getText().toString().isEmpty()&&!passwordEt.getText().toString().isEmpty())
            {
                if(objectfirebaseauth!=null)
                {
                    if (objectfirebaseauth.getCurrentUser()!=null)
                    {
                        objectfirebaseauth.signOut();

                        Toast.makeText(this, "Signout Sucessfully", Toast.LENGTH_SHORT).show();
                    }

                    else {
                        objectsignin_btn.setEnabled(false);

                        objectfirebaseauth.signInWithEmailAndPassword(EmailEt.getText().toString(),
                                passwordEt.getText().toString()).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                            @Override
                            public void onSuccess(AuthResult authResult) {

                                objectsignin_btn.setEnabled(true);
                                Toast.makeText(SigninAct.this, "SignIn Sucessfully", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(SigninAct.this,MainPageAct.class));
                                finish();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(SigninAct.this, "Not Sign in", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                }
            }

            else if (EmailEt.getText().toString().isEmpty())
            {
                Toast.makeText(this, "You have not entered your Email", Toast.LENGTH_SHORT).show();
                EmailEt.requestFocus();
            }

            else if (passwordEt.getText().toString().isEmpty())
            {
                Toast.makeText(this, "Enter your Password", Toast.LENGTH_SHORT).show();

                passwordEt.requestFocus();
            }
        }
        catch(Exception e)
        {
            Toast.makeText(this, "Signinuser"+e.getMessage(), Toast.LENGTH_SHORT).show();
        }

    }


}
