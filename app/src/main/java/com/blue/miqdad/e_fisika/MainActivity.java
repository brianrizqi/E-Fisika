package com.blue.miqdad.e_fisika;

import android.content.res.AssetManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.folioreader.Config;
import com.folioreader.FolioReader;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn = (Button) findViewById(R.id.btn);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Config config = new Config();
                config.setShowTts(false);
                FolioReader folioReader = FolioReader.getInstance(getApplicationContext());
                folioReader.setConfig(config, true);
                folioReader.openBook(R.raw.a_room_with_a_view_morrison);
            }
        });

    }
}
