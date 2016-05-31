package com.adoregeek.rxdemo.functions.weather;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.adoregeek.rxdemo.R;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        initRx();
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//                observable.subscribe(observer);
                String[] name=new String[]{
                        "Woody","David","Hellen","Kelly"
                };
                Observable.from(name)
                        .subscribe(new Action1<String>() {
                            @Override
                            public void call(String s) {
                                Log.e("OnNext", "Item=" + s);
                            }
                        }, new Action1<Throwable>() {
                            @Override
                            public void call(Throwable throwable) {

                            }
                        }, new Action0() {
                            @Override
                            public void call() {
                                Log.e("onComplete","Oncomplete！");
                            }
                        });
                Observable.just(1,2,3,4)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Subscriber<Integer>() {
                            @Override
                            public void onCompleted() {
                                Log.e("onCompleted","onCompleted");
                            }

                            @Override
                            public void onError(Throwable e) {

                            }

                            @Override
                            public void onNext(Integer integer) {
                                Log.e("onNext","Num="+integer);
                            }
                        });
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

        subscriber= new Subscriber<String>() {
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
        observable  =Observable.from(from);
//        Observable observable=Observable.just("Hello!","woody!","This is RxAndroid!");
//        Observable observable= Observable.create(new Observable.OnSubscribe<String>() {
//            @Override
//            public void call(Subscriber<? super String> subscriber) {
//                subscriber.onNext("Hello!");
//                subscriber.onNext("Woody!");
//                subscriber.onNext("This is RxAndroid!");
//                subscriber.onCompleted();
//            }
//        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
