package at.htlklu.widerstandsrechner;

import android.widget.TextView;

import java.util.List;

public class ResistorUtils {

    public static double parseInputViewToDouble(TextView view) {
        return Double.parseDouble(view.getText().toString());
    }

    public static double calcParallel(List<Double> numbers) {
        double result = 0;
        for (Double number : numbers) {
            result += 1 / number;
        }
        return 1 / result;
    }


    public static double calcSerial(List<Double> numbers) {
        double result = 0;
        for (Double number : numbers) {
            result += number;
        }
        return result;
    }

}
