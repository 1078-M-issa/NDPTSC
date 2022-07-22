package sg.edu.rp.c346.id21001078.ndptsc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class ViewList extends AppCompatActivity {

    Button B;
    ListView lv;
    ArrayList<String> al;
    ArrayList<Song> al2;
    ArrayAdapter<String> aa;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        B = findViewById(R.id.bVL);
        lv = findViewById(R.id.VL);
        al = new ArrayList<>();
        al2 = new ArrayList<>();
        aa = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, al);
        lv.setAdapter(aa);
        textView = findViewById(R.id.TV);

        B.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                DBhelp dbh = new DBhelp(ViewList.this);
                al.clear();
                String txt = " ";
                ArrayList<Song> list = dbh.getAllSongs();
                for (int i = 0; i < list.size(); i++ ) {
                    al.add(list.get(i).toStringClass());

                }


                lv.setAdapter(aa);


                aa.notifyDataSetChanged();
                }


        });
        // Seperated [1] = title, [3] = artist, [5] = Year, [7] = rating.
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int
                    position, long identity) {
                Intent i = new Intent(ViewList.this,
                        MainActivity3.class);
                al2.clear();
                DBhelp dbh = new DBhelp(ViewList.this);
                ArrayList<Song> list = dbh.getAllSongs();
                String data = al.get(position);
                String [] sepearate = data.split("'");
                Song object = dbh.getSongDetails(sepearate[1]);
                ArrayList<Song> Ol = new ArrayList<>();
                Ol.add(object);
                i.putExtra("AAA", object);
                startActivity(i);
            }
        });


    }
    @Override
    protected void onResume() {
        super.onResume();

        B.performClick();
    }
}