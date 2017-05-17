package task.vikas.testapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;

import java.util.List;

import task.vikas.testapp.R;
import task.vikas.testapp.model.AllPostsModel;
import task.vikas.testapp.viewholder.RecyclerViewHolder;

/**
 * Created by Descent-Vikas on 11-02-2017.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewHolder> {
   private List<AllPostsModel> itemist;
    private Context context;
    public  RecyclerViewAdapter(Context context, List<AllPostsModel> itemist){
        this.itemist=itemist;
        this.context = context;
    }
    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_row_xml ,null);
        RecyclerViewHolder recyclerViewHolder=new RecyclerViewHolder(view);
        return recyclerViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {
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
