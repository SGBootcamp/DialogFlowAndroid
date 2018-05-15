package com.solidgeargroup.dialogflow.dialogflow;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailsActivity extends AppCompatActivity {

    private Film mFilm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle b = getIntent().getExtras();
        mFilm = (Film) b.getSerializable("film");

        setContentView(R.layout.activity_details);
        TextView titleTextView = this.findViewById(R.id.title);
        TextView descTextView = this.findViewById(R.id.description);
        ImageView imageImageView = this.findViewById(R.id.image);

        titleTextView.setText(mFilm.getTitle());
        descTextView.setText(mFilm.getDescription());
        imageImageView.setImageResource(mFilm.getImage());
    }

}
