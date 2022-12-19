package at.htlklu.widerstandsrechner;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<Double> resistors = new ArrayList<>();
    TextView txt_output, txt_input, txt_resistors, txt_colorRing1,
            txt_colorRing2, txt_colorRing3, txt_colorRing4;
    Button btn_add, btn_serial, btn_parallel, btn_reset;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txt_output = findViewById(R.id.txt_output);
        txt_input = findViewById(R.id.txt_input);
        txt_resistors = findViewById(R.id.txt_resistors);
        txt_colorRing1 = findViewById(R.id.txt_colorRing1);
        txt_colorRing2 = findViewById(R.id.txt_colorRing2);
        txt_colorRing3 = findViewById(R.id.txt_colorRing3);
        txt_colorRing4 = findViewById(R.id.txt_colorRing4);
        btn_add = findViewById(R.id.btn_add);
        btn_serial = findViewById(R.id.btn_seriell);
        btn_parallel = findViewById(R.id.btn_parallel);
        btn_reset = findViewById(R.id.btn_reset);

        btn_add_onClickListener();
        btn_reset_onClickListener();
    }

    private void btn_reset_onClickListener() {
        btn_reset.setOnClickListener(view -> {
            resistors.clear();
            txt_resistors.setText("");
            txt_output.setText("");
            txt_input.setText("");
            txt_colorRing1.setBackgroundColor(Color.WHITE);
            txt_colorRing2.setBackgroundColor(Color.WHITE);
            txt_colorRing3.setBackgroundColor(Color.WHITE);
            txt_colorRing4.setBackgroundColor(Color.WHITE);
        });
    }

    @SuppressLint("DefaultLocale")
    public void btn_serial(View view) {
        double result = ResistorUtils.calcSerial(resistors);
        txt_output.setText(String.format("%.1f", result));
        setRingColors(result);
    }

    private void setRingColors(double result) {

        int roundedResult = (int) Math.round(result);

        if (roundedResult < 10) {

            txt_colorRing1.setBackgroundColor(Color.BLACK);
            txt_colorRing3.setBackgroundColor(Color.BLACK);
            setColorOfRing(roundedResult, txt_colorRing2);

        } else if (roundedResult < 100) {

            txt_colorRing3.setBackgroundColor(Color.BLACK);
            int firstDigit = roundedResult / 10;
            setColorOfRing(firstDigit, txt_colorRing1);
            int secondDigit = roundedResult % 10;
            setColorOfRing(secondDigit, txt_colorRing2);

        } else {

            String sRoundResult = String.valueOf(roundedResult);
            try {

                int firstDigit = Integer.parseInt(sRoundResult.substring(0, 1));
                setColorOfRing(firstDigit, txt_colorRing1);
                int secondDigit = Integer.parseInt(sRoundResult.substring(1, 2));
                setColorOfRing(secondDigit, txt_colorRing2);

                int multiply = sRoundResult.length() - 2;
                setColorOfRing(multiply, txt_colorRing3);

            } catch (Exception e) {
                buildAlertDialog_invalidInputs();
            }
        }
    }

    private void setColorOfRing(int value, TextView ring) {
        switch (value) {
            case 0:
                ring.setBackgroundColor(Color.BLACK);
                break;
            case 1:
                ring.setBackgroundColor(Color.rgb(102, 51, 0));
                break;
            case 2:
                ring.setBackgroundColor(Color.RED);
                break;
            case 3:
                ring.setBackgroundColor(Color.rgb(255, 153, 51));
                break;
            case 4:
                ring.setBackgroundColor(Color.YELLOW);
                break;
            case 5:
                ring.setBackgroundColor(Color.GREEN);
                break;
            case 6:
                ring.setBackgroundColor(Color.BLUE);
                break;
            case 7:
                ring.setBackgroundColor(Color.rgb(153, 0, 153));
                break;
            case 8:
                ring.setBackgroundColor(Color.GRAY);
                break;
            case 9:
                ring.setBackgroundColor(Color.WHITE);
                break;
        }
    }

    @SuppressLint("DefaultLocale")
    public void btn_parallel(View view) {
        double result = ResistorUtils.calcParallel(resistors);
        txt_output.setText(String.format("%.1f", result));
        setRingColors(result);
    }

    private void btn_add_onClickListener() {
        btn_add.setOnClickListener(view -> {
            try {
                if (resistors.toString().length() <= 80) {
                    resistors.add(ResistorUtils.parseInputViewToDouble(txt_input));
                    txt_resistors.setText(resistors.toString());
                } else {
                    buildAlertDialog_maxResistorsReached();
                }
            } catch (Exception e) {
                buildAlertDialog_invalidInputs();
            }
        });
    }

    private void buildAlertDialog_maxResistorsReached() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Maximum Entry's");
        builder.setMessage("Maximum amount of entry's reached");
        builder.setCancelable(false);
        builder.setPositiveButton("OK", (dialogInterface, i) -> {
            txt_input.setText("");
            dialogInterface.cancel();
        });
        builder.show();
    }

    private void buildAlertDialog_invalidInputs() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Invalid Input");
        builder.setMessage("Valid input signs are [0123456789.]");
        builder.setCancelable(false);
        builder.setPositiveButton("OK", (dialogInterface, i) -> {
            txt_input.setText("");
            dialogInterface.cancel();
        });
        builder.show();
    }
}