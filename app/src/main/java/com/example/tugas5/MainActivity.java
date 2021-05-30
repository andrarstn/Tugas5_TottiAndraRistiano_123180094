package com.example.tugas5;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.view.View;

import com.example.tugas5.databinding.ActivityMainBinding;
import com.example.tugas5.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements PlayerAdapter.PlayerAdapterCallback {
    private ActivityMainBinding binding;
    private PlayerAdapter playerAdapter;
    private MainViewModel mainViewModel;

    private List<Player> players = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initAdapter();
        observeData();

        initAction();
    }

    private void initAction() {
        binding.btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddDataActivity.startActivity(MainActivity.this, false,
                        null);
            }
        });

        binding.btnDeleteAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainViewModel.deleteAllData();
            }
        });
    }

    private void initAdapter(){
        playerAdapter = new PlayerAdapter(this, players, this);
        binding.rvPlayers.setLayoutManager(new LinearLayoutManager(this));
        binding.rvPlayers.setItemAnimator(new DefaultItemAnimator());
        binding.rvPlayers.setAdapter(playerAdapter);
    }

    private void observeData(){
        mainViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        mainViewModel.getPlayers().observe(this,
                new Observer<List<Player>>() {
                    @Override
                    public void onChanged(List<Player> players) {
                        if (players.isEmpty()){
                            binding.btnDeleteAll.setVisibility(View.GONE);
                        }else{
                            binding.btnDeleteAll.setVisibility(View.VISIBLE);
                        }

                        playerAdapter.addData(players);
                    }
                }
        );
    }

    @Override
    public void onEdit(Player player) {
        AddDataActivity.startActivity(this,true,player);
    }

    @Override
    public void onDelete(Player player) {
        int uid = player.uid;
        mainViewModel.deleteSingleData(uid);
    }
}