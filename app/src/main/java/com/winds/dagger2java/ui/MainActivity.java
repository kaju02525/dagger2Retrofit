package com.winds.dagger2java.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.winds.dagger2java.App;
import com.winds.dagger2java.R;
import com.winds.dagger2java.mvvm.MainActivityViewModel;
import com.winds.dagger2java.utils.PrefUtils;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity {

    private MainActivityViewModel viewModel;
    private TextView tv;

    PrefUtils prefUtils  = App.getInstance().getAppComponent().providePrefUtils();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewModel= ViewModelProviders.of(this).get(MainActivityViewModel.class);
        tv=findViewById(R.id.tv);


       initObservable();
       tv.setText(prefUtils.getUserName());

       // loadData();
    }

    private void initObservable() {
        viewModel.getData().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                prefUtils.setUserName(s);
                tv.append("\n");
                tv.append(s);
            }
        });
    }


    public void btn(View view) {
        viewModel.setData("This is dagger2");
    }
}
