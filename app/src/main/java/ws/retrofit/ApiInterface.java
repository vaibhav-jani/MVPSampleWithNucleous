package ws.retrofit;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Query;
import ws.entity.SampleResponse;

public interface ApiInterface {

    @GET("jokes/random/10")
    Call<SampleResponse> getSampleEndPointResponse(@Query("firstName") String firstName, @Query("lastName") String lastName);

}