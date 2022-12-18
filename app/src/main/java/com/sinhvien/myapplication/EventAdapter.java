package com.sinhvien.myapplication;

        import android.annotation.SuppressLint;
        import android.content.Context;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.ArrayAdapter;
        import android.widget.ImageView;
        import android.widget.TextView;

        import androidx.annotation.NonNull;
        import androidx.annotation.Nullable;

        import com.sinhvien.myapplication.schemas.Event;

        import java.util.List;

public class EventAdapter extends ArrayAdapter<Event> {
    private static Context context;

    public EventAdapter (Context context, int resource, List<Event> events) {
        super(context, resource, events);
        this.context = context;
    }

    @SuppressLint("ResourceType")
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Event event = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.event_cell, parent, false);
        }

        TextView title = (TextView) convertView.findViewById(R.id.event_title_text_view);
        TextView body = (TextView) convertView.findViewById(R.id.event_title_text_view);
        ImageView image = (ImageView) convertView.findViewById(R.id.event_image_view);

        title.setText(event.getTitle());
        body.setText(event.getBody());
        image.setImageResource(R.drawable.merry_christmas_event);

        // Bug
        // image.setImageURI(String.valueOf(event.getImageUrl()));
        // context -> MainActivity
//        int resourceID = context.getResources().getIdentifier(tour.getImage(), "drawable",context.getPackageName());
//        image.setImageResource(resourceID);

        return convertView;
    }
}
