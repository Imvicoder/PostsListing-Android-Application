package task.vikas.testapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import task.vikas.testapp.R;
import task.vikas.testapp.adapter.RecyclerViewAdapter;
import task.vikas.testapp.model.AllPostsModel;
import task.vikas.testapp.network.ApiClient;
import task.vikas.testapp.service.APIService;

public class MainActivity extends AppCompatActivity {
LinearLayoutManager layoutManager;
Button btn;
EditText et;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn=(Button) findViewById(R.id.filterbtn);
        et=(EditText)findViewById(R.id.femail);

         final String emid=et.getText().toString().trim();

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,Myposts.class);
                intent.putExtra("pass",emid);
                MainActivity.this.startActivity(intent);
            }
        });

        getAllPosts();
    }
    private void getAllPosts(){
        //here we will implement our retofit library
try{
    APIService service= ApiClient.getClient().create(APIService.class);

    Call<List<AllPostsModel>> call=service.getData();
    call.enqueue(new Callback<List<AllPostsModel>>() {
        @Override
        public void onResponse(Call<List<AllPostsModel>> call, Response<List<AllPostsModel>> response) {
            List<AllPostsModel> rowListItem=response.body(); // all server data will be passed in this list
            System.out.println("list is:->"+rowListItem);
            String time=rowListItem.get(2).getPostedAt();
            String poster=rowListItem.get(2).getEmail();
            String path=rowListItem.get(2).getDp();
            System.out.println("In MyPosts time is:->"+time+" poster is:->"+poster+" pah is:->"+path);
            layoutManager=new LinearLayoutManager(MainActivity.this);
            RecyclerView recyclerView=(RecyclerView) findViewById(R.id.recyclerView);
            recyclerView.setLayoutManager(layoutManager);

            //adding data to recyclerview through Recyclerview adapter

            RecyclerViewAdapter recyclerViewAdapter=new RecyclerViewAdapter(getApplicationContext(),rowListItem);
            recyclerView.setAdapter(recyclerViewAdapter);
        }

        @Override
        public void onFailure(Call<List<AllPostsModel>> call, Throwable t) {
        Log.d("OnFailure",t.toString());
        }
    });

}catch (Exception e){

}
    }


}
