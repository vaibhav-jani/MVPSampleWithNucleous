package com.example.vaibhav.mvpsample;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.functions.Func2;
import rx.schedulers.Schedulers;
import utils.Notify;

public class RxJavaTestActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_test);

        just();

        arrayObservable();

        threads();

        viewObservable();
    }

    private void viewObservable() {

        Observable<View> clickEventObservable = Observable.create(new Observable.OnSubscribe<View>() {
            @Override
            public void call(final Subscriber<? super View> subscriber) {

                View view = findViewById(R.id.btnStart);

                assert view != null;
                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if (subscriber.isUnsubscribed()) {

                            return;
                        }

                        subscriber.onNext(v);
                    }
                });
            }
        });

        Observable<View> skip = clickEventObservable.skip(4);

        // You can then apply all sorts of operation here
        Subscription subscription = skip.subscribe(new Observer<View>() {

            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(View view) {

                Notify.toast("Clicked more than 4 time", RxJavaTestActivity.this);
            }
        });

        // Unsubscribe when you're done with it
        //subscription.unsubscribe();
    }

    private void arrayObservable() {

        Observable<Integer> myArrayObservable
                = Observable.from(new Integer[]{1, 2, 3, 4, 5, 6});

        Observable<String> myArrayObservableAfterMap = myArrayObservable.map(

                new Func1<Integer, String>() {

                    @Override
                    public String call(Integer integer) {

                        return String.valueOf(integer * integer);
                    }
                });

        Observer<Integer> arrayObserver = new Observer<Integer>() {

            @Override
            public void onCompleted() {

                Log.d("Rx:", "Completed ");
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Integer integer) {

                Log.d("Rx:", "Integer : " + integer);
            }
        };

        Observer<String> anotherObserver = new Observer<String>() {

            @Override
            public void onCompleted() {

                Log.d("Rx:", "Completed ");
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {

                Log.d("Rx:", "String : " + s);
            }
        };

        myArrayObservable.subscribe(arrayObserver);
        myArrayObservableAfterMap.subscribe(anotherObserver);
    }

    private void just() {

        Observable<String> myObservable = Observable.just("Hello");

        Observer<String> myObserver = new Observer<String>() {

            @Override
            public void onCompleted() {
                // Called when the observable has no more data to emit
            }

            @Override
            public void onError(Throwable e) {
                // Called when the observable encounters an error
            }

            @Override
            public void onNext(String s) {

                Log.d("Rx:", s);
            }
        };

        Subscription mySubscription = myObservable.subscribe(myObserver);
        Subscription mySubscription2 = myObservable.subscribe(new Action1<String>() {
            @Override
            public void call(String s) {

                Log.d("Rx:", "Second observer : " + s);
            }
        });
    }

    private void threads() {

        Observable<String> fetchFromGoogle = Observable.create(

                new Observable.OnSubscribe<String>() {

                    @Override
                    public void call(Subscriber<? super String> subscriber) {

                        try {

                            String data = fetchData("http://www.google.com");
                            subscriber.onNext(data); // Emit the contents of the URL
                            subscriber.onCompleted(); // Nothing more to emit
                        } catch (Exception e) {
                            subscriber.onError(e); // In case there are network errors
                        }
                    }
                });

        fetchFromGoogle = fetchFromGoogle.subscribeOn(Schedulers.newThread());
        fetchFromGoogle = fetchFromGoogle.observeOn(AndroidSchedulers.mainThread());

        Observer<String> myObserver = new Observer<String>() {

            @Override
            public void onCompleted() {
                // Called when the observable has no more data to emit
            }

            @Override
            public void onError(Throwable e) {
                // Called when the observable encounters an error
            }

            @Override
            public void onNext(String s) {

                Log.d("Rx:", s);
            }
        };


        //Subscribing single end point
        Subscription mySubscription = fetchFromGoogle.subscribe(myObserver);

        Observable<String> fetchFromYahoo = Observable.create(

                new Observable.OnSubscribe<String>() {

                    @Override
                    public void call(Subscriber<? super String> subscriber) {

                        try {

                            String data = fetchData("http://www.yahoo.com");
                            subscriber.onNext(data); // Emit the contents of the URL
                            subscriber.onCompleted(); // Nothing more to emit
                        } catch (Exception e) {
                            subscriber.onError(e); // In case there are network errors
                        }
                    }
                });

        fetchFromYahoo = fetchFromYahoo.subscribeOn(Schedulers.newThread());
        fetchFromYahoo = fetchFromYahoo.observeOn(AndroidSchedulers.mainThread());

        // Fetch from both simultaneously
        Observable<String> zipped
                = Observable.zip(fetchFromGoogle, fetchFromYahoo, new Func2<String, String, String>() {
            @Override
            public String call(String google, String yahoo) {
                // Do something with the results of both threads
                return "Google : \n" + google + "\nYahoo : \n" + yahoo;
            }
        });

        //Subscribing both
        Subscription subscribe = zipped.subscribe(myObserver);
    }

    private String fetchData(String urlString) {

        try {

            HttpURLConnection httpURLConnection;

            URL url = new URL(urlString);

            httpURLConnection = (HttpURLConnection) url.openConnection();

            StringBuilder builder = new StringBuilder();
            builder.append(httpURLConnection.getResponseCode())
                    .append(" ")
                    .append(httpURLConnection.getResponseMessage())
                    .append("\n");

            int contentLength = httpURLConnection.getContentLength();

            builder.append("\nContent Length: " + contentLength + "\n");
            //builder.append("\nContent : " + "\n");

            InputStream inputStream = httpURLConnection.getInputStream();

            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;

            while ((line = reader.readLine()) != null) {

                //builder.append(line + "\n");
            }


            builder.append("\nHeaders : " + "\n");

            Map<String, List<String>> map = httpURLConnection.getHeaderFields();

            for (Map.Entry<String, List<String>> entry : map.entrySet()) {

                if (entry.getKey() == null) {

                    continue;
                }

                builder.append(entry.getKey()).append(": ");

                List<String> headerValues = entry.getValue();

                Iterator<String> it = headerValues.iterator();

                if (it.hasNext()) {

                    builder.append(it.next());

                    while (it.hasNext()) {

                        builder.append(", ").append(it.next());
                    }
                }

                builder.append("\n");
            }

            return builder.toString();

        } catch (Exception e) {

            e.printStackTrace();
        }

        return "";
    }
}
