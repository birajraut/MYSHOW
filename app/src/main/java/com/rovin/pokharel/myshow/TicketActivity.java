package com.rovin.pokharel.myshow;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.rovin.pokharel.myshow.model.SendEmail;

public class TicketActivity extends AppCompatActivity {

    private TextView movieName;
    private TextView userName;
    private TextView showTime;
    private TextView totalPrice;
    private Button printTicket;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket);

        movieName = (TextView) findViewById(R.id.ticket_movie_name);
        userName = (TextView) findViewById(R.id.ticket_user_name);
        showTime = (TextView) findViewById(R.id.ticket_show_time);
        totalPrice = (TextView) findViewById(R.id.ticket_price);
        printTicket = (Button) findViewById(R.id.ticket_print);

        movieName.setText(getIntent().getStringExtra(ConstantData.MOVIE_NAME));
        showTime.setText(getIntent().getStringExtra(ConstantData.MOVIE_DATE));
        int price = getIntent().getIntExtra("totalTickets", 0) * 350;
        totalPrice.setText(String.valueOf(price));

        final String uid = FirebaseAuth.getInstance().getCurrentUser().getUid().toString();
        FirebaseDatabase.getInstance().getReference("users").child(uid).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                /*for (final DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    String name = snapshot.child("userName").getValue().toString();
                    userName.setText(name);
                }*/
                userName.setText(dataSnapshot.child("userName").getValue().toString());

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        printTicket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendEmail();
            }
        });
    }

    private void sendEmail(){
        final String email = FirebaseAuth.getInstance().getCurrentUser().getEmail().toString();
        final String movie = getIntent().getStringExtra(ConstantData.MOVIE_NAME);
        final String date = getIntent().getStringExtra(ConstantData.MOVIE_DATE);
        Toast.makeText(getApplicationContext(), "Confirmation email has been sent, check your email!!!", Toast.LENGTH_LONG).show();
        SendEmail sendEmail = new SendEmail(this, email, movie, date);
        sendEmail.execute();
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(getApplicationContext(), Dashboard.class));
        finish();
    }
}
