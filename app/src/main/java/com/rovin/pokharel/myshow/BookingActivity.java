package com.rovin.pokharel.myshow;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.rovin.pokharel.myshow.model.SeatBooking;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rovin on 11/13/2018.
 */

public class BookingActivity extends AppCompatActivity {

    private ImageView movieBanner;
    private CheckBox a1,a2,a3,a4,b1,b2,b3,b4,c1,c2,c3,c4,d1,d2,d3,d4;
    private Button btnNext;
    private TextView tvTotalPrice;
    private int totalTickets;

    private ArrayList<String> bookedSeat = new ArrayList<>();


    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.booking_seat);


        //firebase
        databaseReference = FirebaseDatabase.getInstance().getReference("seats");

        movieBanner = (ImageView) findViewById(R.id.booking_movie);
        Picasso.with(getApplicationContext()).load(getIntent().getStringExtra(ConstantData.MOVIE_IMAGE)).into(movieBanner);

        a1 = (CheckBox) findViewById(R.id.a1);
        a2 = (CheckBox) findViewById(R.id.a2);
        a3 = (CheckBox) findViewById(R.id.a3);
        a4 = (CheckBox) findViewById(R.id.a4);
        b1 = (CheckBox) findViewById(R.id.b1);
        b2 = (CheckBox) findViewById(R.id.b2);
        b3 = (CheckBox) findViewById(R.id.b3);
        b4 = (CheckBox) findViewById(R.id.b4);
        c1 = (CheckBox) findViewById(R.id.c1);
        c2 = (CheckBox) findViewById(R.id.c2);
        c3 = (CheckBox) findViewById(R.id.c3);
        c4 = (CheckBox) findViewById(R.id.c4);
        d1 = (CheckBox) findViewById(R.id.d1);
        d2 = (CheckBox) findViewById(R.id.d2);
        d3 = (CheckBox) findViewById(R.id.d3);
        d4 = (CheckBox) findViewById(R.id.d4);

        tvTotalPrice = (TextView)findViewById(R.id.booking_total_price);

        databaseReference.child(getIntent().getStringExtra(ConstantData.MOVIE_ID)).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<String> seats = new ArrayList<>();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                    String seatname = snapshot.child("seatName").getValue().toString();
                    seats.add(seatname);
                }

                if (seats.contains("a1")) {
                    a1.setChecked(true);
                    a1.setClickable(false);
                }
                if (seats.contains("a2")) {
                    a2.setChecked(true);
                    a2.setClickable(false);
                }
                if (seats.contains("a3")) {
                    a3.setChecked(true);
                    a3.setClickable(false);
                }
                if (seats.contains("a4")) {
                    a4.setChecked(true);
                    a4.setClickable(false);
                }
                if (seats.contains("b1")) {
                    b1.setChecked(true);
                    b1.setClickable(false);
                }
                if (seats.contains("b2")) {
                    b2.setChecked(true);
                    b2.setClickable(false);
                }
                if (seats.contains("b3")) {
                    b3.setChecked(true);
                    b3.setClickable(false);
                }
                if (seats.contains("b4")) {
                    b4.setChecked(true);
                    b4.setClickable(false);
                }
                if (seats.contains("c1")) {
                    c1.setChecked(true);
                    c1.setClickable(false);
                }
                if (seats.contains("c2")) {
                    c2.setChecked(true);
                    c2.setClickable(false);
                }
                if (seats.contains("c3")) {
                    c3.setChecked(true);
                    c3.setClickable(false);
                }
                if (seats.contains("c4")) {
                    c4.setChecked(true);
                    c4.setClickable(false);
                }
                if (seats.contains("d1")) {
                    d1.setChecked(true);
                    d1.setClickable(false);
                }
                if (seats.contains("d2")) {
                    d2.setChecked(true);
                    d2.setClickable(false);
                }
                if (seats.contains("d3")) {
                    d3.setChecked(true);
                    d3.setClickable(false);
                }
                if (seats.contains("d4")) {
                    d4.setChecked(true);
                    d4.setClickable(false);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        btnNext = (Button) findViewById(R.id.booking_next_btn);
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userEmail = FirebaseAuth.getInstance().getCurrentUser().getEmail().toString();
                if (a1.isClickable() && a1.isChecked()) {
                    bookedSeat.add("a1");
                    String id = FirebaseDatabase.getInstance().getReference("seats").push().getKey();
                    SeatBooking seatBooking = new SeatBooking(id, "a1",userEmail);
                    FirebaseDatabase.getInstance().getReference("seats")
                            .child(getIntent().getStringExtra(ConstantData.MOVIE_ID)).child(id).setValue(seatBooking);
                }
                if (a2.isClickable() &&a2.isChecked()) {
                    bookedSeat.add("a2");
                    String id = FirebaseDatabase.getInstance().getReference("seats").push().getKey();
                    SeatBooking seatBooking = new SeatBooking(id, "a2",userEmail);
                    FirebaseDatabase.getInstance().getReference("seats")
                            .child(getIntent().getStringExtra(ConstantData.MOVIE_ID)).child(id).setValue(seatBooking);
                }
                if (a3.isClickable() &&a3.isChecked()) {
                    bookedSeat.add("a3");
                    String id = FirebaseDatabase.getInstance().getReference("seats").push().getKey();
                    SeatBooking seatBooking = new SeatBooking(id, "a3",userEmail);
                    FirebaseDatabase.getInstance().getReference("seats")
                            .child(getIntent().getStringExtra(ConstantData.MOVIE_ID)).child(id).setValue(seatBooking);
                }
                if (a4.isClickable() &&a4.isChecked()) {
                    bookedSeat.add("a4");
                    String id = FirebaseDatabase.getInstance().getReference("seats").push().getKey();
                    SeatBooking seatBooking = new SeatBooking(id, "a4",userEmail);
                    FirebaseDatabase.getInstance().getReference("seats")
                            .child(getIntent().getStringExtra(ConstantData.MOVIE_ID)).child(id).setValue(seatBooking);
                }
                if (b1.isClickable() &&b1.isChecked()) {
                    bookedSeat.add("b1");
                    String id = FirebaseDatabase.getInstance().getReference("seats").push().getKey();
                    SeatBooking seatBooking = new SeatBooking(id, "b1",userEmail);
                    FirebaseDatabase.getInstance().getReference("seats")
                            .child(getIntent().getStringExtra(ConstantData.MOVIE_ID)).child(id).setValue(seatBooking);
                }
                if (b2.isClickable() &&b2.isChecked()) {
                    bookedSeat.add("b2");
                    String id = FirebaseDatabase.getInstance().getReference("seats").push().getKey();
                    SeatBooking seatBooking = new SeatBooking(id, "b2",userEmail);
                    FirebaseDatabase.getInstance().getReference("seats")
                            .child(getIntent().getStringExtra(ConstantData.MOVIE_ID)).child(id).setValue(seatBooking);
                }
                if (b3.isClickable() &&b3.isChecked()) {
                    bookedSeat.add("b3");
                    String id = FirebaseDatabase.getInstance().getReference("seats").push().getKey();
                    SeatBooking seatBooking = new SeatBooking(id, "b3",userEmail);
                    FirebaseDatabase.getInstance().getReference("seats")
                            .child(getIntent().getStringExtra(ConstantData.MOVIE_ID)).child(id).setValue(seatBooking);
                }
                if (b4.isClickable() &&b4.isChecked()) {
                    bookedSeat.add("b4");
                    String id = FirebaseDatabase.getInstance().getReference("seats").push().getKey();
                    SeatBooking seatBooking = new SeatBooking(id, "b4",userEmail);
                    FirebaseDatabase.getInstance().getReference("seats")
                            .child(getIntent().getStringExtra(ConstantData.MOVIE_ID)).child(id).setValue(seatBooking);
                }
                if (c1.isClickable() &&c1.isChecked()) {
                    bookedSeat.add("c1");
                    String id = FirebaseDatabase.getInstance().getReference("seats").push().getKey();
                    SeatBooking seatBooking = new SeatBooking(id, "c1",userEmail);
                    FirebaseDatabase.getInstance().getReference("seats")
                            .child(getIntent().getStringExtra(ConstantData.MOVIE_ID)).child(id).setValue(seatBooking);
                }
                if (c2.isClickable() &&c2.isChecked()) {
                    bookedSeat.add("c2");
                    String id = FirebaseDatabase.getInstance().getReference("seats").push().getKey();
                    SeatBooking seatBooking = new SeatBooking(id, "c2",userEmail);
                    FirebaseDatabase.getInstance().getReference("seats")
                            .child(getIntent().getStringExtra(ConstantData.MOVIE_ID)).child(id).setValue(seatBooking);
                }
                if (c3.isClickable() &&c3.isChecked()) {
                    bookedSeat.add("c3");
                    String id = FirebaseDatabase.getInstance().getReference("seats").push().getKey();
                    SeatBooking seatBooking = new SeatBooking(id, "c3",userEmail);
                    FirebaseDatabase.getInstance().getReference("seats")
                            .child(getIntent().getStringExtra(ConstantData.MOVIE_ID)).child(id).setValue(seatBooking);
                }
                if (c4.isClickable() &&c4.isChecked()) {
                    bookedSeat.add("c4");
                    String id = FirebaseDatabase.getInstance().getReference("seats").push().getKey();
                    SeatBooking seatBooking = new SeatBooking(id, "c4",userEmail);
                    FirebaseDatabase.getInstance().getReference("seats")
                            .child(getIntent().getStringExtra(ConstantData.MOVIE_ID)).child(id).setValue(seatBooking);
                }
                if (d1.isClickable() &&d1.isChecked()) {
                    bookedSeat.add("d1");
                    String id = FirebaseDatabase.getInstance().getReference("seats").push().getKey();
                    SeatBooking seatBooking = new SeatBooking(id, "d1",userEmail);
                    FirebaseDatabase.getInstance().getReference("seats")
                            .child(getIntent().getStringExtra(ConstantData.MOVIE_ID)).child(id).setValue(seatBooking);
                }
                if (d2.isClickable() &&d2.isChecked()) {
                    bookedSeat.add("d2");
                    String id = FirebaseDatabase.getInstance().getReference("seats").push().getKey();
                    SeatBooking seatBooking = new SeatBooking(id, "d2",userEmail);
                    FirebaseDatabase.getInstance().getReference("seats")
                            .child(getIntent().getStringExtra(ConstantData.MOVIE_ID)).child(id).setValue(seatBooking);
                }
                if (d3.isClickable() &&d3.isChecked()) {
                    bookedSeat.add("d3");
                    String id = FirebaseDatabase.getInstance().getReference("seats").push().getKey();
                    SeatBooking seatBooking = new SeatBooking(id, "d3",userEmail);
                    FirebaseDatabase.getInstance().getReference("seats")
                            .child(getIntent().getStringExtra(ConstantData.MOVIE_ID)).child(id).setValue(seatBooking);
                }
                if (d4.isClickable() &&d4.isChecked()) {
                    bookedSeat.add("d4");
                    String id = FirebaseDatabase.getInstance().getReference("seats").push().getKey();
                    SeatBooking seatBooking = new SeatBooking(id, "d4",userEmail);
                    FirebaseDatabase.getInstance().getReference("seats")
                            .child(getIntent().getStringExtra(ConstantData.MOVIE_ID)).child(id).setValue(seatBooking);

                }


                totalTickets = bookedSeat.size();
//                seatBooking = new SeatBooking(FirebaseAuth.getInstance().getCurrentUser().getEmail().toString(), bookedSeat);
//                databaseReference.child(getIntent().getStringExtra(ConstantData.MOVIE_ID)).child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(bookedSeat);

                Intent intent = new Intent(getApplicationContext(), TicketActivity.class);
                intent.putExtra(ConstantData.MOVIE_NAME, getIntent().getStringExtra(ConstantData.MOVIE_NAME));
                intent.putExtra(ConstantData.MOVIE_DATE, getIntent().getStringExtra(ConstantData.MOVIE_DATE));
                intent.putExtra("totalTickets", totalTickets);
                startActivity(intent);
                finish();

            }
        });


    }
}
