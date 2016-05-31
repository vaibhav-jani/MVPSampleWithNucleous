package ws;

import android.content.Context;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;
import rx.Observable;
import rx.Subscriber;
import utils.Logger;
import utils.NetworkUtils;
import ws.entity.BaseRequest;
import ws.entity.BaseResponse;
import ws.entity.SampleRequest;
import ws.entity.SampleResponse;
import ws.retrofit.RestClient;

/**
 * Created by vaibhav on 30/5/16.
 */
public class AppWs {

    public static void getSampleEndPointResponse(final SampleRequest request, final Context context,
                                    final WsListener listener) {

        String firstName = request.getFirstName();
        String lastName = request.getLastName();

        Call<SampleResponse> call = RestClient.get().getSampleEndPointResponse(firstName, lastName);

        try {

            call.enqueue(new Callback<SampleResponse>() {

                @Override
                public void onResponse(Response<SampleResponse> response, Retrofit retrofit) {

                    SampleResponse baseResponse = response.body();

                    if (baseResponse != null) {

                        if (listener != null) {

                            listener.onResponseSuccess(baseResponse);
                        }

                    } else {

                        if (listener != null) {


                            listener.notifyResponseFailed(null, request);
                        }
                    }
                }

                @Override
                public void onFailure(Throwable t) {

                    retrofitError(t, listener, context);

                    listener.notifyResponseFailed(null, request);
                }

            });

        } catch (Exception e) {

            Logger.e(e);

            listener.notifyResponseFailed(null, request);
        }
    }

    public static void retrofitError(Throwable t, WsListener listener, Context context) {

        if (listener != null) {

            Logger.e(t);

            listener.notifyResponseFailed(null, null);
            NetworkUtils.checkInternetConnection(context);
        }
    }

    public static Observable<SampleResponse> getSampleEndPointResponse(final SampleRequest request) {

        Observable<SampleResponse> observable = Observable.create(new Observable.OnSubscribe<SampleResponse>() {

            @Override
            public void call(final Subscriber<? super SampleResponse> subscriber) {

                String firstName = request.getFirstName();

                String lastName = request.getLastName();

                Call<SampleResponse> call = RestClient.get().getSampleEndPointResponse(firstName, lastName);

                try {

                    Response<SampleResponse> execute = call.execute();

                    SampleResponse baseResponse = execute.body();

                    if(baseResponse != null) {

                        subscriber.onNext(baseResponse);

                    } else {

                        subscriber.onError(new NullPointerException("Retrofit response is null"));
                    }

                } catch (Exception e) {

                    subscriber.onError(e);

                    Logger.e(e);
                }

            }
        });

        return observable;
    }


    public interface WsListener {

        void onResponseSuccess(BaseResponse baseResponse);

        void notifyResponseFailed(String message, BaseRequest request);
    }
}
