package brainiacs.insertsort;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    EditText editTextInput;
    TextView editTextOutput;
    TextView helpTextTop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editTextInput = (EditText) findViewById(R.id.editTextInput);
        editTextOutput = (TextView) findViewById(R.id.editTextOutput);
        helpTextTop = (TextView) findViewById(R.id.helpTextTop);
    }

    public void buttonOnClick(View view) {
        String string = editTextInput.getText().toString().trim();

        if (string.length() < 1)
        {
            Toast.makeText(this, "Can't be empty", Toast.LENGTH_LONG).show();
            return;
        }

        String[] strings = string.split(" ");  //test

        int[] ints = new int[strings.length];
        for (int i = 0; i < strings.length; i++) {
            ints[i] = Integer.parseInt(strings[i]);
        }

        String sortedInts = insertionSort(ints);
        String sorted = "";
        //  for(int i:sortedInts){
        // sorted += i + " ";
        // }
        if (sortedInts.equalsIgnoreCase("wrong entry")) {
            Toast.makeText(this,"wrong entry",Toast.LENGTH_LONG).show();
        } else {
            editTextOutput.setText(sortedInts);
        }


    }

    public void restartButtonOnClick(View view) {
        editTextInput.setText("");
        editTextOutput.setText("");
    }

    public void helpButtonOnClick(View view) {
        if(helpTextTop.getVisibility() == View.VISIBLE)
        {
            helpTextTop.setVisibility(View.GONE);
        } else
        {
            helpTextTop.setVisibility(View.VISIBLE);
        }
    }


    public static String insertionSort(int[] list) {
        boolean checkNumbers = checkData(list);
        if (list != null && list.length > 1 && list.length < 9 && checkNumbers == true) {
            int i, j, key, temp;
            String sortStr = "";
            for (int m : list) {
                sortStr += m;
            }
            for (i = 1; i < list.length; i++) {

                key = list[i];
                j = i - 1;

                while (j >= 0 && key < list[j]) {

                    temp = list[j];
                    list[j] = list[j + 1];
                    list[j + 1] = temp;
                    j--;
                }
                sortStr += "\n";

                for (int r : list) {
                    sortStr += r;
                }

            }

            return sortStr;

        } else {
            System.out.println("wrong entry");
            return "wrong entry";
        }

    }

    private static boolean checkData(int[] list) {
        boolean isValid = true;
        for (int n : list) {
            if (n > 9 || n < 0) {
                isValid = false;
                break;
            }
        }
        return isValid;
    }
}
