package sg.edu.rp.c346.id21001078.ndptsc;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class CustomAdapter extends ArrayAdapter {
    Context parent_context;
    int layout_id;
    ArrayList<Song> songList;

    public CustomAdapter (Context context, int resource,
                          ArrayList<Song> objects) {
        super (context,resource,objects);

        parent_context =context;
        layout_id = resource;
        songList = objects;
    }
    @Override
    public View getView (int position, View convertView,
                         ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater)
                parent_context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(layout_id,parent,false);

        TextView topYear = rowView.findViewById(R.id.Year);
        TextView songTitle = rowView.findViewById(R.id.Title);
        TextView songSinger = rowView.findViewById(R.id.Singer);
        TextView titleYear = rowView.findViewById(R.id.YearR);
        TextView stars = rowView.findViewById(R.id.Ratings);

        Song currentVersion = songList.get(position);

        topYear.setText(String.valueOf(currentVersion.getYear()));
        songTitle.setText(currentVersion.getTitle());
        songSinger.setText(currentVersion.getSingers());
        titleYear.setText(String.valueOf(currentVersion.getYear()));
        String text = (String.valueOf(currentVersion.getStars()));
        stars.setText(text + " ★ ");
        return rowView;
    }
}
