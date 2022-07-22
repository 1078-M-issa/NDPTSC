package sg.edu.rp.c346.id21001078.ndptsc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity3 extends AppCompatActivity {

    RadioGroup SRG;
    Button EDit, Destroy, C;
    EditText Edtitle, EdSinger, EdYear;
    TextView TV;
    RadioButton RB, RB2,RB3, RB4, RB5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        RB = findViewById(R.id.radioButton);
        RB2 = findViewById(R.id.radioButton2);
        RB3 = findViewById(R.id.radioButton3);
        RB4 = findViewById(R.id.radioButton4);
        RB5 = findViewById(R.id.radioButton5);

        EDit = findViewById(R.id.Ed);
        Destroy = findViewById(R.id.Des);
        C = findViewById(R.id.Can);
        Edtitle = findViewById(R.id.editTextTextPersonName2);
        EdSinger = findViewById(R.id.editTextTextPersonName3);
        EdYear = findViewById(R.id.editTextNumber);
        SRG = findViewById(R.id.AAAAAAA);
        TV = findViewById(R.id.textView2);

        Intent i = getIntent();
        Song data = (Song) i.getSerializableExtra("AAA");
        Edtitle.setText (data.getTitle());
        EdSinger.setText(data.getSingers());
        EdYear.setText(String.valueOf(data.getYear()));

        EDit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int rating = 0;
                if (RB.isChecked()) {
                    rating = 1;
                } else if (RB2.isChecked()) {
                    rating = 2;
                } else if (RB3.isChecked()) {
                    rating = 3;
                } else if (RB4.isChecked()) {
                    rating = 4;
                } else if (RB5.isChecked()) {
                    rating = 5;
                }

                DBhelp dbh = new DBhelp(MainActivity3.this);
                data.setTitle(Edtitle.getText().toString());
                data.setSingers(EdSinger.getText().toString());
                data.setYear(Integer.valueOf(EdYear.getText().toString()));
                data.setStars(Integer.valueOf(rating));
                dbh.updateSong(data);
                dbh.close();
                finish();
            }
        });

        Destroy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBhelp dbh = new DBhelp(MainActivity3.this);
                dbh.deleteSong(data.getId());
                finish();

            }
        });

        C.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}