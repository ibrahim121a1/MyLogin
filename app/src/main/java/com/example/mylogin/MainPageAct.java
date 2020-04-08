package com.example.mylogin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class MainPageAct extends AppCompatActivity {

    private EditText objectcnic;
    private EditText objectaddress;
    private Button objectget;
    private FirebaseFirestore objectfirebasefirestore;
    private DocumentReference objectdocumentreference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);
        convertmltojava();
    }

    private void convertmltojava()
    {
        try {

            objectcnic = findViewById(R.id.cnic);
            objectaddress = findViewById(R.id.address);
            objectget = findViewById(R.id.getbtn);

            objectfirebasefirestore=FirebaseFirestore.getInstance();
        }
        catch (Exception e)
        {
            Toast.makeText(this, "convertxmlyojava"+e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    public void addvalues(View v)
    {
        try
        {
            objectfirebasefirestore=FirebaseFirestore.getInstance();

            objectfirebasefirestore.collection("UserDetail").document("MYDetail")
                    .get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                    if (task.getResult().exists())
                    {
                        Toast.makeText(MainPageAct.this, "already created", Toast.LENGTH_SHORT).show();
                    }

                    else
                    {
                        if (!objectcnic.getText().toString().isEmpty()&&!objectaddress.getText().toString().isEmpty())
                        {
                            Map<String,Object> objectMap=new HashMap<>();
                            objectMap.put("Address",objectaddress.getText().toString());
                            objectMap.put("CNIC",objectcnic.getText().toString());

                            objectfirebasefirestore.collection("UserDetail").document("MYDetail")
                                    .set(objectMap).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Toast.makeText(MainPageAct.this, "Uploaded Sucessfuly", Toast.LENGTH_SHORT).show();
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(MainPageAct.this, "Not Uploaded Sucessfully", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }

                        else
                        {
                            Toast.makeText(MainPageAct.this, "Field is empty", Toast.LENGTH_SHORT).show();
                        }
                    }

                }
            });

        }
        catch (Exception e)
        {
            Toast.makeText(this, "Addvalues"+e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}
