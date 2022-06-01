package com.example.lab2androidmr;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private ActivityResultLauncher<Intent> mActivityResultLauncher;
    boolean boolimie = false;
    boolean boolnazwisko = false;
    boolean booloceny = false;
    boolean boolAVG = false;
    //Button ButtonResultMessage = findViewById(R.id.ButtonResultMessage);
    float FloatResultAvgValue = 0;

    String AVG = "0";

    void SprawdzGuzik(){
        Button mButton1 = findViewById(R.id.IdButton);


        if(boolimie && boolnazwisko && booloceny){
            mButton1.setVisibility(View.VISIBLE);
        }else{
            mButton1.setVisibility(View.INVISIBLE);
        }
    }

    void sprawdzAVG(){
        Button ButtonResultMessage = findViewById(R.id.ButtonResultMessage);
        TextView resultTextView = findViewById(R.id.IdResultAverageText);

        float FloatResultAvgValue= Float.parseFloat(AVG);
        if(FloatResultAvgValue > 0){
            ButtonResultMessage.setVisibility(View.VISIBLE);
            resultTextView.setVisibility(View.VISIBLE);
        }else{
            ButtonResultMessage.setVisibility(View.INVISIBLE);
            resultTextView.setVisibility(View.VISIBLE);
        }
    }


    public void ResultButtonAvg(String resultAvgValue){
        Button ButtonResultMessage = findViewById(R.id.ButtonResultMessage);
        float FloatResultAvgValue= Float.parseFloat(resultAvgValue);
        if( FloatResultAvgValue > 3.0 ){
            ButtonResultMessage.setText("Super! :)");
        }else{
            ButtonResultMessage.setText("Tym razem mi nie poszło");
        }
        sprawdzAVG();
    }

    public void EndofAplication(){
        Button ButtonResultMessage = findViewById(R.id.ButtonResultMessage);
        if(ButtonResultMessage.getText() == "Super! :)"){
            Toast.makeText(this, "Gratulacje! Otrzymujesz zaliczenie!", Toast.LENGTH_SHORT).show();
            super.onDestroy();
        }else{
            Toast.makeText(this, "Wysyłam podanie o zaliczenie warunkowe", Toast.LENGTH_SHORT).show();
            super.onDestroy();
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button ButtonResultMessage = findViewById(R.id.ButtonResultMessage);
        EditText idInputName = findViewById(R.id.IdInputName);
        EditText idInputSurname = findViewById(R.id.IdInputSurname);
        EditText idInputMarks = findViewById(R.id.IdInputMarks);
        TextView resultTextView = findViewById(R.id.IdResultAverageText);
        Button mButton1 = findViewById(R.id.IdButton);



        mActivityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if(result.getResultCode() == RESULT_OK){
                            Intent resultIntent = result.getData();
                            TextView resultTextView = findViewById(R.id.IdResultAverageText);
                            AVG = resultIntent.getStringExtra(SecondActivity.RESULT_KEY);
                            resultTextView.setText(AVG);

                            //resultTextView.setVisibility(View.VISIBLE);

                            ResultButtonAvg(AVG);

                        }
                    }
                }
        );

        ButtonResultMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EndofAplication();
            }
        });


        if(savedInstanceState != null){

            idInputName.setText(savedInstanceState.getString("IdInputN"));
            idInputSurname.setText(savedInstanceState.getString("IdInputS"));
            idInputMarks.setText(savedInstanceState.getString("IdInputM"));


            resultTextView.setText(savedInstanceState.getString("IdTextAvg"));
            sprawdzAVG();

        }



            idInputName.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    SprawdzGuzik();
                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    SprawdzGuzik();
                }

                @Override
                public void afterTextChanged(Editable editable) {

                    SprawdzGuzik();
                }
            });

            idInputName.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View view, boolean b) {
                    if (!b) {
                        if (idInputName.getText().toString().length() <= 2) {
                            idInputName.setError("Pole wymaga więcej niż 2 litery");
                            Toast err = Toast.makeText(MainActivity.this, "Wprowadź imię", Toast.LENGTH_SHORT);
                            err.show();
                            boolimie = false;
                        } else {
                            idInputName.setError(null);
                            boolimie = true;
                        }
                        SprawdzGuzik();
                    }
                    SprawdzGuzik();
                }
            });


            idInputSurname.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    SprawdzGuzik();
                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    SprawdzGuzik();
                }

                @Override
                public void afterTextChanged(Editable editable) {

                    SprawdzGuzik();
                }
            });

            idInputSurname.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View view, boolean b) {
                    if (!b) {
                        if (idInputSurname.getText().toString().length() <= 2) {
                            idInputSurname.setError("Pole wymaga więcej niż 2 litery");
                            Toast err = Toast.makeText(MainActivity.this, "Wprowadź Nazwisko", Toast.LENGTH_SHORT);
                            err.show();
                            boolnazwisko = false;
                        } else {
                            idInputSurname.setError(null);
                            boolnazwisko = true;
                        }
                    }
                    SprawdzGuzik();
                }
            });

            idInputMarks.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    SprawdzGuzik();
                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    try {
                        int val = Integer.parseInt(idInputMarks.getText().toString());
                        if (val <= 4 && val >= 16) {
                            idInputMarks.setError("Liczba ocen musi miescic sie w przedziale 5-15");
                            Toast err = Toast.makeText(MainActivity.this, "Błędne dane", Toast.LENGTH_SHORT);
                            err.show();
                            booloceny = false;
                        } else {
                            idInputMarks.setError(null);
                            booloceny = true;
                        }
                        SprawdzGuzik();
                    } catch (NumberFormatException e) {
                        idInputMarks.setError("Pole nie może być puste");
                        Toast err = Toast.makeText(MainActivity.this, "Błędne dane", Toast.LENGTH_SHORT);
                        err.show();
                        booloceny = false;
                    }

                }

                @Override
                public void afterTextChanged(Editable editable) {
                    SprawdzGuzik();
                }
            });

            idInputMarks.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View view, boolean b) {
                    if (!b) {
                        try {
                            int val = Integer.parseInt(idInputMarks.getText().toString());
                            if (val <= 4 || val >= 16) {
                                idInputMarks.setError("Liczba ocen musi miescic sie w przedziale 5-15");
                                Toast err = Toast.makeText(MainActivity.this, "Błędne dane", Toast.LENGTH_SHORT);
                                err.show();
                                booloceny = false;
                            } else {
                                idInputMarks.setError(null);
                                booloceny = true;
                                SprawdzGuzik();
                            }
                        } catch (NumberFormatException e) {
                            idInputMarks.setError("Pole nie może być puste");
                            Toast err = Toast.makeText(MainActivity.this, "Błędne dane", Toast.LENGTH_SHORT);
                            err.show();
                            booloceny = false;
                        }
                    }
                    SprawdzGuzik();
                }
            });

            Button startSecondButton = findViewById(R.id.IdButton);
            startSecondButton.setOnClickListener(v -> startSecondActivity());

        }

    private static final String EDIT_BUTTON_VISIBLE = "edit_button_visible";

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {

        EditText tvin= findViewById(R.id.IdInputName);
        EditText tvis= findViewById(R.id.IdInputSurname);
        EditText tvim= findViewById(R.id.IdInputMarks);
        TextView tvravg = findViewById(R.id.IdResultAverageText);
        Button buttonoceny = findViewById(R.id.IdButton);
        //findViewById() zwraca element widoku
        //getText() zwraca CharSequence a nie String
        outState.putInt(EDIT_BUTTON_VISIBLE,buttonoceny.getVisibility());
        outState.putString("IdInputN",tvin.getText().toString());
        outState.putString("IdInputS",tvis.getText().toString());
        outState.putString("IdInputM",tvim.getText().toString());
        outState.putString("IdTextAvg",tvravg.getText().toString());
        outState.putBoolean("Boolname", boolimie);
        outState.putBoolean("Boolsurname", boolnazwisko);
        outState.putBoolean("Boolmark", booloceny);

        outState.putString("average", AVG);
        SprawdzGuzik();
        sprawdzAVG();
        ResultButtonAvg(AVG);

        super.onSaveInstanceState(outState);
    }


    @Override
  protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState){
          super.onRestoreInstanceState(savedInstanceState);
        EditText tvin= findViewById(R.id.IdInputName);
        EditText tvis= findViewById(R.id.IdInputSurname);
        EditText tvim= findViewById(R.id.IdInputMarks);
        TextView tvravg = findViewById(R.id.IdResultAverageText);



            tvin.setText(savedInstanceState.getString("IdInputN"));
            tvis.setText(savedInstanceState.getString("IdInputS"));
            tvim.setText(savedInstanceState.getString("IdInputM"));
            tvravg.setText(savedInstanceState.getString("IdTextAvg"));
            booloceny = savedInstanceState.getBoolean("Boolname");
            boolimie = savedInstanceState.getBoolean("Boolsurname");
            boolnazwisko = savedInstanceState.getBoolean("Boolmark");
            AVG = savedInstanceState.getString("average");
            SprawdzGuzik();
            sprawdzAVG();
            ResultButtonAvg(AVG);

    }

    public static final String NAME_KEY = "com.example.lab2androidmr.textname";
    public static final String SURNAME_KEY = "com.example.lab2androidmr.textsurname";
    public static final String MARKS_KEY = "com.example.lab2androidmr.textmarks";

    private void startSecondActivity(){
        EditText idInputName = findViewById(R.id.IdInputName);
        EditText idInputSurname = findViewById(R.id.IdInputSurname);
        EditText idInputMarks = findViewById(R.id.IdInputMarks);

        Intent intent = new Intent(this, SecondActivity.class);
        intent.putExtra(NAME_KEY, idInputName.getText().toString());
        intent.putExtra(SURNAME_KEY, idInputSurname.getText().toString());
        intent.putExtra(MARKS_KEY, idInputMarks.getText().toString());
        //startActivity(intent);
        mActivityResultLauncher.launch(intent);
    }
}
