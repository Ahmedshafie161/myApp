package com.example.ui.main;

import static android.content.ContentValues.TAG;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.data.MyDatabase;
import com.example.data.PostsClient;
import com.example.pojo.PostModel;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostViewModel extends ViewModel {
    MutableLiveData<List<PostModel>> postsMutableLiveData = new MutableLiveData<>();
    MutableLiveData<String> posts = new MutableLiveData<>();

    // Retrofit Recieve Data
    public void getPosts() {
        //Rx java observable
        Observable observable =  PostsClient.getINSTANCE().getPosts()
                // make upstream (getting data) in io cashed thread
                .subscribeOn(Schedulers.io())
                //make downstream in ui thread
                .observeOn(AndroidSchedulers.mainThread());
        //RxJava expressions ,when recieve data assign it to LiveData
               observable.subscribe(o -> postsMutableLiveData.setValue((List<PostModel>) o),e -> Log.d(TAG, "getPosts: "+e));
        /*
        Observer<List<PostModel>> observer = new Observer<List<PostModel>>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull List<PostModel> postModels) {
                postsMutableLiveData.setValue(postModels);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.d(TAG, "onError: "+e);
            }

            @Override
            public void onComplete() {

            }
        };
        observable.subscribe(observer);*/

        //enqueue callback
       /* PostsClient.getINSTANCE().getPosts().enqueue(new Callback<List<PostModel>>() {
            @Override
            public void onResponse(Call<List<PostModel>> call, Response<List<PostModel>> response) {
                postsMutableLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<PostModel>> call, Throwable t) {
                posts.setValue("errr");
            }
        });
    }*/
    }

    //DataBase write Data
    public void addToDatabase(PostModel postModel){
        MyDatabase myDatabase= new MyDatabase();
        myDatabase.addFromActivity(postModel );

    }

}

