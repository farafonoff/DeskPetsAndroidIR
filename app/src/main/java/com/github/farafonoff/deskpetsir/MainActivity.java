package com.github.farafonoff.deskpetsir;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.github.farafonoff.deskpetsir.control.Action;
import com.github.farafonoff.deskpetsir.control.ControlAdapter;

public class MainActivity extends AppCompatActivity implements ControlAdapter.ActionListener {

    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.buttonPanel);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        recyclerView.setAdapter(new ControlAdapter(this));
    }

    @Override
    public void onAction(Action act) {
        Log.i("BUTTON", act.name);
        Intent it = new Intent(this, TransmissionService.class);
        it.setAction("IRSEND");
        it.putExtra("action", act);
        startService(it);
    }
}
