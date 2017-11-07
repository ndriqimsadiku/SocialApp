package yourpost_package;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hp.socialapp.R;
import com.github.curioustechizen.ago.RelativeTimeTextView;
import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by hp on 11/4/2017.
 */

public class PostsAdapter extends BaseAdapter {
    ArrayList<Posts> posts;
    LayoutInflater layoutInflater;

    public PostsAdapter(LayoutInflater layoutInflater) {
        this.layoutInflater = layoutInflater;
    }

    @Override
    public int getCount() {
        try {
            return posts.size();
        } catch (NullPointerException e) {
            return 1;
        }
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if (view == null) {
            view = layoutInflater.inflate(R.layout.neews_feed_list, viewGroup, false);
            holder = new ViewHolder(view);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        try {
            String myDate = posts.get(i).getCreatedDate();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date;
            date = sdf.parse(myDate);
            long millis = date.getTime();
            holder.username.setText(posts.get(i).getUsername());
            holder.timeTextView.setReferenceTime(millis);
            holder.pershkrimi.setText(posts.get(i).getPershkrimi());
            Picasso.with(view.getContext())
                    .load(posts.get(i).getPhotoUrl())
                    .into(holder.imageView);
        }
        catch (ParseException e) {
            e.printStackTrace();
        }
        catch (NullPointerException e) {
            e.printStackTrace();
        }
        return view;
    }

    public class ViewHolder {
        TextView username, pershkrimi;
        RelativeTimeTextView timeTextView;
        ImageView imageView;

        public ViewHolder(View view) {
            username = view.findViewById(R.id.name_textview);
            pershkrimi = view.findViewById(R.id.pershkrimi_textview);
            timeTextView = view.findViewById(R.id.date_textview);
            imageView = view.findViewById(R.id.posted_image);
        }
    }

    public void setPostsInfos(ArrayList<Posts> posts) {
        this.posts = posts;
        notifyDataSetChanged();
    }
}
