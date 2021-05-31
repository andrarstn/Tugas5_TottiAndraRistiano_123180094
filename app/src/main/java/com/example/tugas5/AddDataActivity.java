package com.example.tugas5;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.tugas5.databinding.ActivityAddDataBinding;
import com.example.tugas5.entity.Player;

public class AddDataActivity extends AppCompatActivity {
    private static String KEY_IS_EDIT = "key_is_edit";
    private static String KEY_DATA = "key_data";

    private ActivityAddDataBinding binding;
    private AddDataViewModel addDataViewModel;

    private boolean isEdit = false;
    private int mUid = 0;
    private String playerPosition;

    public static void startActivity(Context context, boolean isEdit, Player player) {
        Intent intent = new Intent(new Intent(context, AddDataActivity.class));
        intent.putExtra(KEY_IS_EDIT, isEdit);
        intent.putExtra(KEY_DATA, player);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddDataBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        addDataViewModel = ViewModelProviders.of(this).get(AddDataViewModel.class);

        addListenerButton();
        loadData();
        initAction();
    }

    private void addListenerButton(){
        RadioGroup radioGroup = binding.rgPosition;
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i){
                    case R.id.position_pg:
                        playerPosition = "PG";
                        break;
                    case R.id.position_sg:
                        playerPosition = "SG";
                        break;
                    case R.id.position_pf:
                        playerPosition = "PF";
                        break;
                    case R.id.position_sf:
                        playerPosition = "SF";
                        break;
                    case R.id.position_c:
                        playerPosition = "C";
                        break;
                }
            }
        });
    }

    private void initAction() {
        binding.btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = binding.etName.getText().toString();
                String age = binding.etAge.getText().toString();
                String number = binding.etNumber.getText().toString();

                if (name.isEmpty() || age.isEmpty() || number.isEmpty()) {
                    Toast.makeText(AddDataActivity.this, "Semua harus diisi",
                            Toast.LENGTH_SHORT).show();
                } else {
                    if (isEdit) {
                        addDataViewModel.updatePlayer(mUid, name, Integer.parseInt(age), Integer.parseInt(number), playerPosition);
                    } else {
                        addDataViewModel.addPlayer(name, Integer.parseInt(age), Integer.parseInt(number), playerPosition);
                    }
                    finish();
                }
            }
        });
    }

    private void loadData() {
        isEdit = getIntent().getBooleanExtra(KEY_IS_EDIT, false);
        if (isEdit) {
            Player player = getIntent().getParcelableExtra(KEY_DATA);
            if (player != null) {
                mUid = player.uid;
                String name = player.name;
                int age = player.age;
                int number = player.number;
                String position = player.position;

                binding.etName.setText(name);
                binding.etAge.setText(String.valueOf(age));
                binding.etNumber.setText(String.valueOf(number));

                if (position.equals("PG")){
                    binding.positionPg.setChecked(true);
                }else if (position.equals("SG")){
                    binding.positionSg.setChecked(true);
                }else if (position.equals("PF")){
                    binding.positionPf.setChecked(true);
                }else if (position.equals("SF")){
                    binding.positionSf.setChecked(true);
                }else {
                    binding.positionC.setChecked(true);
                }
            }
        }
    }
}
