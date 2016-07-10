package thailand.cns.pheng.phengebookshop;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

/**
 * Created by User on 10/7/2559.
 */
public class MyAdapter extends BaseAdapter{

    // Explicit
    public Context context;
    private String[] bookStrings, priceStrings, iconStrings;

    public MyAdapter(Context context,
                     String[] bookStrings,
                     String[] priceStrings,
                     String[] iconStrings) {
        this.context = context;
        this.bookStrings = bookStrings;
        this.priceStrings = priceStrings;
        this.iconStrings = iconStrings;
    }

    @Override
    public int getCount() {
        return bookStrings.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE); // เปิด service เลย์เอาท์เสมือน
        View view1 = layoutInflater.inflate(R.layout.my_listview, parent, false);

        // Bind Widget
        TextView bookTextView = (TextView) view1.findViewById(R.id.textView8);
        TextView priceTextView = (TextView) view1.findViewById(R.id.textView9);
        ImageView iconImageView = (ImageView) view1.findViewById(R.id.imageView2);

        bookTextView.setText(bookStrings[position]);
        priceTextView.setText(priceStrings[position]);
        Picasso.with(context).load(iconStrings[position]).resize(150,180).into(iconImageView);

        return view1;
    }
}   // Main Class
