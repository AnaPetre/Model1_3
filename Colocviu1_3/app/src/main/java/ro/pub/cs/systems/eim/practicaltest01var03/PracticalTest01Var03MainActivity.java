package ro.pub.cs.systems.eim.practicaltest01var03;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import static java.lang.Integer.valueOf;

public class PracticalTest01Var03MainActivity extends AppCompatActivity {

    private Button plus;
    private Button minus;
    private EditText first_number;
    private EditText second_number;
    private TextView result;
    private Button navigate_to_secondary_activity;
    int sum = 0;
    int dif = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practical_test01_var03_main);

        plus = (Button) findViewById(R.id.plus);
        plus.setOnClickListener(buttonOnClickListener);
        minus = (Button) findViewById(R.id.minus);
        minus.setOnClickListener(buttonOnClickListener);
        first_number = (EditText) findViewById(R.id.first_number);
        second_number = (EditText) findViewById(R.id.second_number);
        result = (TextView) findViewById(R.id.result);
        navigate_to_secondary_activity = (Button) findViewById(R.id.navigate_to_secondary_activity);

        if (savedInstanceState != null) {
            if (savedInstanceState.containsKey(Constants.FIRST_NUMBER)) {
                first_number.setText(savedInstanceState.getString(Constants.FIRST_NUMBER));
                //Toast.makeText(getApplicationContext(), "First number: " + first_number, Toast.LENGTH_LONG).show();
            } else {
                first_number.setText(String.valueOf(""));
            }
            if (savedInstanceState.containsKey(Constants.SECOND_NUMBER)) {
                second_number.setText(savedInstanceState.getString(Constants.SECOND_NUMBER));
                //Toast.makeText(getApplicationContext(), "Second number: " + second_number, Toast.LENGTH_LONG).show();
            } else {
                second_number.setText(String.valueOf(""));
            }
        } else {
            first_number.setText(String.valueOf(""));
            second_number.setText(String.valueOf(""));
        }
    }

    ButtonOnClickListener buttonOnClickListener = new ButtonOnClickListener();

    private class ButtonOnClickListener implements View.OnClickListener {

        public boolean isStringInt(String str)
        {
            try
            {
                Integer.parseInt(str);
                return true;
            } catch (NumberFormatException ex)
            {
                return false;
            }
        }

        @Override
        public void onClick(View view) {

            String first = first_number.getText().toString();
            String second = second_number.getText().toString();

            if (isStringInt(first) == false) {
                Toast.makeText(getApplicationContext(),"First number is not integer",Toast.LENGTH_SHORT).show();
                first_number.setText("");
                second_number.setText("");
            }

            if (isStringInt(second) == false) {
                Toast.makeText(getApplicationContext(),"Second number is not integer",Toast.LENGTH_SHORT).show();
                first_number.setText("");
                second_number.setText("");
            }


            if (isStringInt(first) && isStringInt(second)){
                if (view.getId() == R.id.plus) {
                    sum = valueOf(first_number.getText().toString()) + valueOf(second_number.getText().toString());
                    result.setText(String.valueOf(sum));
                    first_number.setText("");
                    second_number.setText("");
                }

                if (view.getId() == R.id.minus) {
                    if (valueOf(first_number.getText().toString()) > valueOf(second_number.getText().toString())) {
                        dif = valueOf(first_number.getText().toString()) - valueOf(second_number.getText().toString());
                        result.setText(String.valueOf(dif));
                        first_number.setText("");
                        second_number.setText("");
                    }
                }
            }

        }

    }

    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {

            savedInstanceState.putString(Constants.FIRST_NUMBER, first_number.getText().toString());
            savedInstanceState.putString(Constants.SECOND_NUMBER, second_number.getText().toString());
            super.onSaveInstanceState(savedInstanceState);


    }

    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        if (savedInstanceState.containsKey(Constants.FIRST_NUMBER)) {
            first_number.setText(savedInstanceState.getString(Constants.FIRST_NUMBER));
            //Toast.makeText(getApplicationContext(), "First number: " + first_number.getText().toString(), Toast.LENGTH_LONG).show();

        } else {
            first_number.setText(String.valueOf(""));
        }
        if (savedInstanceState.containsKey(Constants.SECOND_NUMBER)) {
            second_number.setText(savedInstanceState.getString(Constants.SECOND_NUMBER));
           // Toast.makeText(getApplicationContext(), "Second number: " + second_number.getText().toString(), Toast.LENGTH_LONG).show();

        } else {
            second_number.setText(String.valueOf(""));
        }
    }
}