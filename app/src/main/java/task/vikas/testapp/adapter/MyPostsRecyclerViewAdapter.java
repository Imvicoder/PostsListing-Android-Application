package task.vikas.testapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;

import java.util.List;

import task.vikas.testapp.R;
import task.vikas.testapp.model.MyPostsModel;
import task.vikas.testapp.viewholder.MyPostsRecyclerViewHolder;

/**
 * Created by Descent-Vikas on 11-02-2017.
 */

public class MyPostsRecyclerViewAdapter extends RecyclerView.Adapter<MyPostsRecyclerViewHolder> {
    private List<MyPostsModel> itemist;
    private Context context;
    public  MyPostsRecyclerViewAdapter(Context context, List<MyPostsModel> itemist){
        this.itemist=itemist;
        this.context = context;
    }
    @Override
    public MyPostsRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_row_xml ,null);
        MyPostsRecyclerViewHolder myPostsrecyclerViewHolder=new MyPostsRecyclerViewHolder(view);
        return myPostsrecyclerViewHolder;
    }

    @Override
    public void onBindViewHolder(MyPostsRecyclerViewHolder holder, int position) {
        Picasso.with(context).load(itemist.get(position).getDp()).resize(120,160).into(holder.dp);
        //holder.dp.
        holder.post.setText(itemist.get(position).getPost());
        holder.posted_by.setText(itemist.get(position).getEmail());
        holder.posted_at.setText(itemist.get(position).getPostedAt());
        holder.likes.setText(itemist.get(position).getLikes() );
    }

    @Override
    public int getItemCount() {
        return itemist.size();
    }
}
