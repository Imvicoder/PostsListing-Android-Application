package task.vikas.testapp.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import task.vikas.testapp.R;
import task.vikas.testapp.adapter.MyPostsRecyclerViewAdapter;
import task.vikas.testapp.model.MyPostsModel;
import task.vikas.testapp.network.ApiClient;
import task.vikas.testapp.service.APIService;

public class Myposts extends AppCompatActivity {
    String emailid;
    LinearLayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myposts);
        Bundle bundle = getIntent().getExtras();

        emailid = bundle.getString("pass");
        System.out.println("email is:->"+emailid);

        getMyPosts();
    }


    private void getMyPosts(){
        //here we will implement our retofit library
        try{
            APIService service= ApiClient.getClient().create(APIService.class);
            System.out.println("email id:->"+emailid);
            Call<List<MyPostsModel>> call=service.getMyPosts(emailid);
            call.enqueue(new Callback<List<MyPostsModel>>() {
                @Override
                public void onResponse(Call<List<MyPostsModel>> call, Response<List<MyPostsModel>> response) {
                    List<MyPostsModel> rowListItem=response.body(); // all server data will be passed in this list
                    System.out.println("list is:->"+rowListItem);
                    String time=rowListItem.get(2).getPostedAt();
                    String poster=rowListItem.get(2).getEmail();
                    String path=rowListItem.get(2).getDp();
                    System.out.println("time is:->"+time+" poster is:->"+poster+" pah is:->"+path);
                    layoutManager=new LinearLayoutManager(Myposts.this);
                    layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                    RecyclerView recyclerView=(RecyclerView) findViewById(R.id.mypostsrecyclerView);
                    recyclerView.setLayoutManager(layoutManager);

                    //adding data to recyclerview through Recyclerview adapter

                    MyPostsRecyclerViewAdapter myPostsrecyclerViewAdapter=new MyPostsRecyclerViewAdapter(getApplicationContext(),rowListItem);
                    recyclerView.setAdapter(myPostsrecyclerViewAdapter);
                }

                @Override
                public void onFailure(Call<List<MyPostsModel>> call, Throwable t) {
                    Log.d("OnFailure",t.toString());
                }
            });

        }catch (Exception e){

        }
    }

}
