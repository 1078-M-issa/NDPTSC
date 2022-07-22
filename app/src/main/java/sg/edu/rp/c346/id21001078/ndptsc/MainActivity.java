package sg.edu.rp.c346.id21001078.ndptsc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RadioGroup RG;
    Button BA, BE;
    EditText EST, ESD, ESS;
    ArrayList<Song> al;
    ArrayAdapter<Song> aa;
    TextView TV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BA = findViewById(R.id.BN1);
        BE = findViewById(R.id.BN2);
        EST = findViewById(R.id.ET1);
        ESD = findViewById(R.id.ET2);
        ESS = findViewById(R.id.ET3);
        RG = findViewById(R.id.SRRGN);
        TV = findViewById(R.id.textView7);

        al = new ArrayList<>();
        aa = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, al);

        BA.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {



                al.clear();
                String Title = EST.getText().toString().trim();
                int Year = Integer.parseInt(ESD.getText().toString());
                String Singer = ESS.getText().toString().trim();
                int rating = 0;
                int radio = RG.getCheckedRadioButtonId();
                if (radio == 2131230732) {
                    rating = 1;
                } else if (radio == 2131230733) {
                    rating = 2;
                } else if (radio == 2131230734) {
                    rating = 3;
                } else if (radio == 2131230735) {
                    rating = 4;
                } else if (radio == 2131230736) {
                    rating = 5;
                }
                DBhelp dbh = new DBhelp(MainActivity.this);
                long id = dbh.insertSong(Title, Singer, Year, rating);

                if (id != -1) {
                    al.clear();
                    al.addAll(dbh.getAllSongs());
                    aa.notifyDataSetChanged();
                    Toast.makeText(MainActivity.this, "Insert successful",
                            Toast.LENGTH_SHORT).show();

                }
                EST.setText("");
                ESD.setText("");
                ESS.setText("");
                RG.clearCheck();



            }
        });

        BE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(MainActivity.this, ViewList.class);
                startActivity(i);
            }
        });

    }


}


