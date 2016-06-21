package com.adoregeek.rxdemo.functions.weather;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.adoregeek.rxdemo.R;
import com.bumptech.glide.Glide;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class WeatherActivity extends AppCompatActivity {
    @Inject
    WeatherManager weatherManager;
    @BindView(R.id.tvInfo)
    TextView tvInfo;
    @BindView(R.id.etInput)
    EditText etInput;
    @BindView(R.id.btQuery)
    Button btQuery;
    @BindView(R.id.ivWeatherIcon)
    ImageView ivWeatherIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initRx();
        DaggerWeatherComponent.create().inject(this);
//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//                observable.subscribe(observer);
//                String[] name = new String[]{
//                        "Woody", "David", "Hellen", "Kelly"
//                };
//                Observable.from(name)
//                        .subscribe(new Action1<String>() {
//                            @Override
//                            public void call(String s) {
//                                Log.e("OnNext", "Item=" + s);
//                            }
//                        }, new Action1<Throwable>() {
//                            @Override
//                            public void call(Throwable throwable) {
//
//                            }
//                        }, new Action0() {
//                            @Override
//                            public void call() {
//                                Log.e("onComplete", "Oncomplete！");
//                            }
//                        });
//                Observable.just(1, 2, 3, 4)
//                        .subscribeOn(Schedulers.io())
//                        .observeOn(AndroidSchedulers.mainThread())
//                        .subscribe(new Subscriber<Integer>() {
//                            @Override
//                            public void onCompleted() {
//                                Log.e("onCompleted", "onCompleted");
//                            }
//
//                            @Override
//                            public void onError(Throwable e) {
//
//                            }
//
//                            @Override
//                            public void onNext(Integer integer) {
//                                Log.e("onNext", "Num=" + integer);
//                            }
//                        });
//                test();
//            }
//        });
    }

    private void test() {
        Retrofit.Builder retrofitAdapter = new Retrofit.Builder();
        TESTApiService weatherApiService = retrofitAdapter
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("http://api.openweathermap.org/data/2.5/").build().create(TESTApiService.class);
        Call<WeatherResponse> call = weatherApiService.getWeather("WuXi", "aaacbd36b8f88a65d3c0aa4e8294a8a0");
        call.enqueue(new Callback<WeatherResponse>() {
            @Override
            public void onResponse(Call<WeatherResponse> call, Response<WeatherResponse> response) {
                WeatherResponse weatherResponse = response.body();
                System.out.println("====>>" + weatherResponse.toString());
            }

            @Override
            public void onFailure(Call<WeatherResponse> call, Throwable t) {

            }
        });
    }


    Observer<String> observer;
    Observable observable;
    Subscriber<String> subscriber;

    private void initRx() {
        observer = new Observer<String>() {
            @Override
            public void onCompleted() {
                Log.e("onCompleted", "onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                Log.e("onError", e.toString());
            }

            @Override
            public void onNext(String s) {
                Log.e("onNext", "Item=" + s);
            }
        };

        subscriber = new Subscriber<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {
            }

            @Override
            public void onStart() {
                super.onStart();
            }
        };
        String[] from = new String[]{
                "Hello!", "Woody!", "This is RxAndroid!"
        };
        observable = Observable.from(from);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    @OnClick(R.id.btQuery)
    public void onClick() {
        getAllWeather();
    }

    private void getAllWeather() {
        weatherManager.getWeatherByCityId("1790923")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<WeatherResponse>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onNext(WeatherResponse weatherResponse) {
                        System.out.println("ImageUrl="+String.format(WeatherApiService.BaseImageUrl,weatherResponse.weather.get(0).icon));
                        Glide.with(WeatherActivity.this).load(String.format(WeatherApiService.BaseImageUrl,weatherResponse.weather.get(0).icon)).into(ivWeatherIcon);
                    }
                });
    }

    private void getWeather() {
        weatherManager.getWeatherByCityId("1790923")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<WeatherResponse>() {
                    @Override
                    public void onCompleted() {
                        Log.e("WeatherTag", "onCompleted");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("WeatherTag", "onError");
                    }

                    @Override
                    public void onNext(WeatherResponse weatherResponse) {
                        Log.e("WeatherTag", "Weather=" + weatherResponse.toString());
                    }
                });
    }
}
