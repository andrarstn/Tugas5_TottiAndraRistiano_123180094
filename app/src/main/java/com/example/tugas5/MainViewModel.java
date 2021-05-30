package com.example.tugas5;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.tugas5.entity.DatabaseClient;
import com.example.tugas5.entity.Player;
import com.example.tugas5.entity.PlayerDAO;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Action;
import io.reactivex.schedulers.Schedulers;

public class MainViewModel extends AndroidViewModel {
    private LiveData<List<Player>> players;
    private PlayerDAO playerDAO;

    public MainViewModel(@NonNull Application application) {
        super(application);
        playerDAO = DatabaseClient.getInstance(application)
                .getAppDatabase().playerDAO();
        players = playerDAO.getAll();
    }

    public LiveData<List<Player>> getPlayers(){
        return players;
    }

    public void deleteAllData() {
        Completable.fromAction(new Action() {
            @Override
            public void run() throws Exception {
                playerDAO.deleteAllData();
            }
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe();
    }

    public void deleteSingleData(final int uid) {
        Completable.fromAction(new Action() {
            @Override
            public void run() throws Exception {
                playerDAO.deleteSingleData(uid);
            }
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe();
    }
}
