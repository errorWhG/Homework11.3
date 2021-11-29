    package com.example.homework13;

    import androidx.appcompat.app.AppCompatActivity;

    import android.content.Intent;
    import android.os.Bundle;
    import android.text.Editable;
    import android.text.TextWatcher;
    import android.util.Log;
    import android.widget.Button;
    import android.widget.Toast;

    import com.google.android.material.textfield.TextInputEditText;

    public class MainActivity extends AppCompatActivity {

        private TextInputEditText username, password;
        private Button go;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            username = findViewById(R.id.f1);
            password = findViewById(R.id.f2);
            go = findViewById(R.id.btn_go);
            initListeners();
        }

        private void initListeners() {
            go.setOnClickListener(v -> {
                Toast.makeText(MainActivity.this, "GO!", Toast.LENGTH_SHORT).show();
            });

            username.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                    Log.e("ololo", "besoreTextChange");
                }

                @Override
                public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                    if (charSequence.length() >= 6) {
                        go.setEnabled(true);
                    }
                }

                @Override
                public void afterTextChanged(Editable editable) {
                    if (password.getText().length() >= 6 && editable.length() != 0) {
                        go.setEnabled(true);
                    } else {
                        go.setEnabled(false);
                    }
                }
            });
            password.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable editable) {
                    if (editable.length() >= 6 && username.getText().length() != 0) {
                        go.setEnabled(true);
                    } else {
                        go.setEnabled(false);
                    }
                }
            });

            go.setOnClickListener(v ->{
                Intent intent = new Intent(MainActivity.this,SecondActivity2.class);
                startActivity(intent);
            });
        }
    }