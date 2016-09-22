package brainiacs.insertsort;

import android.content.DialogInterface;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
    }

    public void buttonOnClick(View view) {
        //remove whitespace before and after input
        String string = editTextInput.getText().toString().trim();

        if (string.length() < 1) {
            Toast.makeText(this, "Error: The text box is empty. Please add input.", Toast.LENGTH_LONG).show();
            return;
        }

        // http://stackoverflow.com/a/15099212
        // match a digit only if there is not a non-whitespace character before it,
        // and there is not a non-whitespace character after it
        String pattern = "((?<!\\S)\\d+(?!\\S))";
        Pattern regex = Pattern.compile(pattern);

        Matcher m = regex.matcher(string);

        ArrayList<Integer> integers = new ArrayList<Integer>();

        while(m.find()) {
            //if number is more than 1 digit. Catches leading zeroes.
            if(m.group().length() > 1) {
                Toast.makeText(this, "Error: Please input numbers with one digit only.", Toast.LENGTH_LONG).show();
                return;
            }

            int num = Integer.parseInt(m.group());

            // Determine if the number is a number from 0 to 9, inclusive
            if(num >= 0 && num <= 9) {
                integers.add(num);
            } else {
                Toast.makeText(this, "Error: Please input numbers from 0-9 only.", Toast.LENGTH_LONG).show();
                return;
            }
        }

        // Convert ArrayList<Integer> to int[]
        int ints[] = new int[integers.size()];

        for(int i = 0; i < ints.length; i++) {
            ints[i] = integers.get(i);
        }

        String sortedInts = insertionSort(ints);
        String sorted = "";
        //  for(int i:sortedInts){
        // sorted += i + " ";
        // }
        if (sortedInts.startsWith("Error:")) {
            Toast.makeText(this,sortedInts,Toast.LENGTH_LONG).show();
        } else {
            editTextOutput.setText(sortedInts);
        }
    }

    public void restartButtonOnClick(View view) {
        editTextInput.setText("");
        editTextOutput.setText("");
    }

    public void helpButtonOnClick(View view) {
        Resources res = getResources();

        new AlertDialog.Builder(this)
            .setMessage(res.getString(R.string.help_msg))
            .setPositiveButton(res.getString(R.string.ok).toUpperCase(), null)
            .setTitle(res.getString(R.string.help))
            .show();
    }

    public void quitButtonOnClick(View view) {
        Resources res = getResources();

        new AlertDialog.Builder(this)
            .setMessage(String.format(res.getString(R.string.exit_prompt), res.getString(R.string.app_name)))
            .setPositiveButton(res.getString(R.string.yes), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                }
            })
            .setNegativeButton(res.getString(R.string.no), null)
            .show();
    }

    //Please output string starting with "Error:" to mean there is an error
    public static String insertionSort(int[] list) {
        boolean checkNumbers = checkData(list);
        boolean notNull = list != null;
        boolean largeEnough = list.length > 1;
        boolean smallEnough = list.length < 9;
        if (notNull && largeEnough && smallEnough && checkNumbers) {
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
        } else if (!notNull) {
            return "Error: The list is empty. The minimum size is 2.";
        } else if (!largeEnough)
        {
            return "Error: The list is too small. The minimum size is 2.";
        } else if(!smallEnough)
        {
            return "Error: The list is too large. The maximum size is 8.";
        } else if(!checkNumbers)
        {
            return "Error: A numbers is out of range. Please input numbers 0-9 only.";
        } else
        {
            // Should not print in sub-module
            // System.out.println("wrong entry");
            return "Error: Sort Failed.";
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
