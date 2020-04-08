package com.example.mylogin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignUpAct extends AppCompatActivity {

    private EditText EmailET;
    private EditText passwordET;

    private Button objectsignin_btn;

    FirebaseAuth objectfirebaseauth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        convertxmltojava();
        objectfirebaseauth=FirebaseAuth.getInstance();
        objectsignin_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createAccount();
            }
        });
    }

    private void convertxmltojava()
    {
        EmailET=findViewById(R.id.Signin_ET);
        passwordET=findViewById(R.id.Signin_password_ET);
        objectsignin_btn=findViewById(R.id.Signup_btn);
    }

    private void createAccount()
    {
        try
        {
            if (!EmailET.getText().toString().isEmpty()&&!passwordET.getText().toString().isEmpty())
            {
                if (objectfirebaseauth!=null)
                {
                    objectsignin_btn.setEnabled(false);
                    objectfirebaseauth.createUserWithEmailAndPassword(EmailET.getText().toString()
                            ,passwordET.getText().toString()).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                        @Override
                        public void onSuccess(AuthResult authResult) {
                            objectsignin_btn.setEnabled(false);
                            if (authResult.getUser()!=null)
                            {

                                Toast.makeText(SignUpAct.this, "Signup Sucessfully", Toast.LENGTH_SHORT).show();

                                objectsignin_btn.setEnabled(true);


                            }
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(SignUpAct.this, "Fail To SignUp", Toast.LENGTH_SHORT).show();
                            objectsignin_btn.setEnabled(true);
                        }
                    });


                }
            }

            else if(EmailET.getText().toString().isEmpty())
            {
                Toast.makeText(this, "Please Enter your Email ", Toast.LENGTH_SHORT).show();
                EmailET.requestFocus();
            }

            else if (passwordET.getText().toString().isEmpty())
            {
                Toast.makeText(this, "Please Enter your Password", Toast.LENGTH_SHORT).show();

                passwordET.requestFocus();
            }
        }
        catch (Exception e)
        {

            Toast.makeText(this, "createAccount"+e.getMessage(), Toast.LENGTH_SHORT).show();

        }
    }
}
