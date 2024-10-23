package com.example.zachowaniestanu;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

public class MainActivity extends AppCompatActivity {
    private static final String KEY_COUNT = "liczba_k";
    private static final String STATE_CHECKBOX = "check";
    private static final String STATE_SWITCH = "tryb";
    private static final String STATE_INPUT = "tekst";

    private TextView tekst, liczba, stan_check;
    private EditText name;
    private Button btn, btn2;
    private Switch tryb;
    private CheckBox stan;
    private ConstraintLayout layout;
    private int liczba_k = 0;
    private boolean stan_checkbox, stan_switch;
    private String name_input;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn = findViewById(R.id.button);
        btn2 = findViewById(R.id.button2);
        liczba = findViewById(R.id.textView);
        stan_check = findViewById(R.id.textView2);
        stan = findViewById(R.id.checkBox);
        tryb = findViewById(R.id.switch1);
        layout = findViewById(R.id.main);
        name = findViewById(R.id.editTextText);
        tekst = findViewById(R.id.tekst_input);

        if (savedInstanceState != null) {
            name_input = savedInstanceState.getString(STATE_INPUT);
            liczba_k = savedInstanceState.getInt(KEY_COUNT);
            stan_checkbox = savedInstanceState.getBoolean(STATE_CHECKBOX);
            stan_switch = savedInstanceState.getBoolean(STATE_SWITCH);
        }

        updateUI();
        btn2.setOnClickListener(v -> {
            name_input = name.getText().toString().trim();
            tekst.setText(name_input);
        });

        tryb.setOnCheckedChangeListener((buttonView, isChecked) -> toggleDarkMode(isChecked));
        stan.setOnCheckedChangeListener((buttonView, isChecked) -> updateCheckState(isChecked));
        btn.setOnClickListener(v -> updateCount());
    }

    private void updateUI() {
        tekst.setText(name_input);
        liczba.setText("liczba: " + liczba_k);
        stan.setChecked(stan_checkbox);
        stan_check.setText(stan_checkbox ? "Check box zaznaczony" : "Check box odzaznaczony");
        toggleDarkMode(stan_switch);
    }

    private void toggleDarkMode(boolean isChecked) {
        stan_switch = isChecked;
        layout.setBackgroundColor(isChecked ? Color.BLACK : Color.WHITE);
        tryb.setText("Tryb ciemny");
        Toast.makeText(this, "Zmieniono tryb", Toast.LENGTH_SHORT).show();
    }

    private void updateCheckState(boolean isChecked) {
        stan_checkbox = isChecked;
        stan_check.setText(isChecked ? "Check box zaznaczony" : "Check box odzaznaczony");
    }

    private void updateCount() {
        liczba_k++;
        liczba.setText("liczba: " + liczba_k);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(KEY_COUNT, liczba_k);
        outState.putBoolean(STATE_CHECKBOX, stan_checkbox);
        outState.putBoolean(STATE_SWITCH, stan_switch);
        outState.putString(STATE_INPUT, name_input);
    }
}
