/*package com.example.grocerystore;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class CheckoutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);
    }
}*/

package com.example.grocerystore;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class CheckoutActivity extends AppCompatActivity {

    EditText name, e_mail, phone_no, mode_payment, addr;
    Button submitButton;
    String userID;




    FirebaseDatabase rootNode;
    DatabaseReference reference;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);

        name = findViewById(R.id.editTextTextPersonName);
        e_mail = findViewById(R.id.editTextTextEmailAddress);
        phone_no = findViewById(R.id.editTextPhone2);
        addr = findViewById(R.id.editTextTextPostalAddress);
        mode_payment = findViewById(R.id.editTextTextPersonName2);
        submitButton =  findViewById(R.id.button);

        Intent intent = getIntent();
        String bd = intent.getExtras().getString("userID");

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rootNode = FirebaseDatabase.getInstance();
                reference = rootNode.getReference("Customers");


                String NAME = name.getText().toString();
                String EMAIL = e_mail.getText().toString();
                String PHONE = phone_no.getText().toString();
                String ADDR = addr.getText().toString();
                String MODEPAYMENT = mode_payment.getText().toString();

                UserHelperCustomer hc = new UserHelperCustomer(NAME, EMAIL, PHONE, ADDR, MODEPAYMENT);



                /*FirebaseDatabase.getInstance().getReference("Customers").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(bd).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(CheckoutActivity.this, "Thank you for using GrocersStore!!", Toast.LENGTH_LONG).show();
                    }
                });*/

                reference.child(bd).setValue(hc);

                gotostartpage();
            }

            //startActivity(new Intent(MainActivity.this, FirstActivity.class));

        });
    }

    public void gotostartpage()
    {
        Toast.makeText(this, "Checkout Successful", Toast.LENGTH_LONG).show();
        Intent intent2 = new Intent(this, FirstActivity.class).putExtra("userID", userID);
        startActivity(intent2);
    }

}



/*
submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rootNode = FirebaseDatabase.getInstance();

                String NAME = name.getText().toString();
                String EMAIL = e_mail.getText().toString();
                String PHONE = phone_no.getText().toString();
                String ADDR = addr.getText().toString();

                if(NAME.isEmpty()) {
                    name.setError("username is required");
                    name.requestFocus();
                    return;
                }

                if(EMAIL.isEmpty()) {
                    e_mail.setError("username is required");
                    e_mail.requestFocus();
                    return;
                }

                if(PHONE.isEmpty()) {
                    phone_no.setError("username is required");
                    phone_no.requestFocus();
                    return;
                }

                if(ADDR.isEmpty()) {
                    addr.setError("username is required");
                    addr.requestFocus();
                    return;
                }

               // Log.d("aa","qq");

                submitButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Log.d("aa","qq");
                        rootNode = FirebaseDatabase.getInstance();
                        reference = rootNode.getReference("CustomerDetails");

                        UserHelperCustomer hc = new UserHelperCustomer(NAME, EMAIL, PHONE, ADDR);

                        reference.child(PHONE).setValue(hc);

                        //reference.setValue("First data");
                    }
                });


            }
        });
 */


