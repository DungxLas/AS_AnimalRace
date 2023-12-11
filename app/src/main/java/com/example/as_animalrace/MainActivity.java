package com.example.as_animalrace;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.SeekBar;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    CheckBox cbAnimal1, cbAnimal2, cbAnimal3;
    SeekBar animal1, animal2, animal3;
    ImageButton play;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cbAnimal1 = (CheckBox) findViewById(R.id.animal1);
        cbAnimal2 = (CheckBox) findViewById(R.id.animal2);
        cbAnimal3 = (CheckBox) findViewById(R.id.animal3);

        CompoundButton.OnCheckedChangeListener checker = (buttonView, isChecked) -> {
            if (isChecked) {
                if (buttonView.getId() != cbAnimal1.getId() && cbAnimal1.isChecked()) {
                    cbAnimal1.setChecked(false);
                }
                if (buttonView.getId() != cbAnimal2.getId() && cbAnimal2.isChecked()) {
                    cbAnimal2.setChecked(false);
                }
                if (buttonView.getId() != cbAnimal3.getId() && cbAnimal3.isChecked()) {
                    cbAnimal3.setChecked(false);
                }
            }
        };
        cbAnimal1.setOnCheckedChangeListener(checker);
        cbAnimal2.setOnCheckedChangeListener(checker);
        cbAnimal3.setOnCheckedChangeListener(checker);

        animal1 = (SeekBar) findViewById(R.id.seekBarAnimal1);
        animal2 = (SeekBar) findViewById(R.id.seekBarAnimal2);
        animal3 = (SeekBar) findViewById(R.id.seekBarAnimal3);
        play = (ImageButton) findViewById(R.id.imageButton);
        play.setOnClickListener(v -> {
            play.setVisibility(View.GONE);
            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    runOnUiThread(() -> {
                        if (animal1.getProgress() < animal1.getMax() &&
                                animal2.getProgress() < animal2.getMax() &&
                                animal3.getProgress() < animal3.getMax()) {

                            animal1.setProgress(animal1.getProgress() + new Random().nextInt(10));
                            animal2.setProgress(animal2.getProgress() + new Random().nextInt(10));
                            animal3.setProgress(animal3.getProgress() + new Random().nextInt(10));
                        } else {
                            timer.cancel();
                        }
                    });
                }
            }, 0, 1000);
        });
    }
}