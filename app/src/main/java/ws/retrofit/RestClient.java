package ws.retrofit;

import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Response;
import com.squareup.okhttp.logging.HttpLoggingInterceptor;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

public class RestClient {

    private static ApiInterface restClient;

    static {

        setupRestClient();
    }

    private RestClient() {}

    public static ApiInterface get() {

        return restClient;
    }

    public static ApiInterface get(String baseUrl) {

        Retrofit retrofit = getRetrofitClient(baseUrl);
        ApiInterface restClient = retrofit.create(ApiInterface.class);
        return restClient;
    }

    public static ApiInterface getProgressiveClient(String baseUrl, final ProgressListener listener) {

        Retrofit retrofit = getRetrofitClient(baseUrl);

        retrofit.client().interceptors().add(new Interceptor() {

            @Override
            public Response intercept(Chain chain) throws IOException {

                Response originalResponse = chain.proceed(chain.request());

                return originalResponse.newBuilder()
                        .body(new ProgressResponseBody(originalResponse.body(), listener))
                        .build();
            }
        });

        ApiInterface restClient = retrofit.create(ApiInterface.class);
        return restClient;
    }

    public static ApiInterface getProgressiveClient(final ProgressListener listener) {

        return getProgressiveClient(AppUrls.BASE_URL, listener);
    }

    private static void setupRestClient() {

        Retrofit retrofit = getRetrofitClient(AppUrls.BASE_URL);
        restClient = retrofit.create(ApiInterface.class);

    }

    public static Retrofit getRetrofitClient(String url) {

        OkHttpClient client = new OkHttpClient();
        client.setConnectTimeout(5, TimeUnit.MINUTES);

        //client.setReadTimeout(30, TimeUnit.SECONDS);
        //client.setFollowRedirects(true);
        //client.setFollowSslRedirects(true);

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);

        HttpLoggingInterceptor interceptorBody = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        client.interceptors().add(interceptor);
        client.interceptors().add(interceptorBody);

        return new Retrofit.Builder()
                .baseUrl(url)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

    }
}
