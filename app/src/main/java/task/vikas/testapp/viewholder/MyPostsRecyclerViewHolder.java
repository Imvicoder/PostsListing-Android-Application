package task.vikas.testapp.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import task.vikas.testapp.R;

/**
 * Created by Descent-Vikas on 11-02-2017.
 */

public class MyPostsRecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    public ImageView dp;
    public TextView post;
    public TextView posted_by;
    public TextView posted_at;
    public TextView likes;

    public MyPostsRecyclerViewHolder(View itemView) {
        super(itemView);
        itemView.setOnClickListener(this);
        //binding views to layout
        dp = (ImageView) itemView.findViewById(R.id.mypostsdp);
        post = (TextView) itemView.findViewById(R.id.mypostspost);
        posted_by = (TextView) itemView.findViewById(R.id.mypostsposter);
        posted_at = (TextView) itemView.findViewById(R.id.mypostsposttime);
        likes = (TextView) itemView.findViewById(R.id.mypostslikes);
    }
    @Override
    public void onClick(View v) {

    }
}
