package com.example.tugas5;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.tugas5.entity.DatabaseClient;
import com.example.tugas5.entity.Player;
import com.example.tugas5.entity.PlayerDAO;

import io.reactivex.Completable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.schedulers.Schedulers;

public class AddDataViewModel extends AndroidViewModel {
    private PlayerDAO playerDAO;

    public AddDataViewModel(@NonNull Application application) {
        super(application);

        playerDAO = DatabaseClient.getInstance(application).getAppDatabase().playerDAO();
    }

    public void addPlayer(final String name, final int age, final int number, final String position){
        Completable.fromAction(new Action() {
            @Override
            public void run() throws Exception {
                Player player = new Player();
                player.name = name;
                player.age = age;
                player.number = number;
                player.position = position;
                playerDAO.insertData(player);
            }
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe();
    }

    public void updatePlayer(final int uid, final String name, final int age, final int number, final String position){
        Completable.fromAction(new Action() {
            @Override
            public void run() throws Exception {
                playerDAO.updateData(name, age, number,position, uid);
            }
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe();
    }
}
