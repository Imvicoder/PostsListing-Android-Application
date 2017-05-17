package task.vikas.testapp.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Descent-Vikas on 11-02-2017.
 */

public class ApiClient {
    public static final String Base_Url="http://connectindia.esy.es/task/";
    private static Retrofit retrofit=null;

    public static Retrofit getClient(){
        if(retrofit==null){
            retrofit=new Retrofit.Builder().baseUrl(Base_Url)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;

    }
}
