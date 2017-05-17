package task.vikas.testapp.service;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import task.vikas.testapp.model.AllPostsModel;
import task.vikas.testapp.model.MyPostsModel;

/**
 * Created by Descent-Vikas on 11-02-2017.
 */

public interface APIService {
    @GET("fetchposts")
    Call<List<AllPostsModel>> getData();
    @FormUrlEncoded
    @POST("fetchpostsbyid")
    Call<List<MyPostsModel>> getMyPosts(@Field("email") String emailid);
    //@Field("email") denotes key
    //String emailid denotes value
}
