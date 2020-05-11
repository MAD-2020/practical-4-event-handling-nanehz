package sg.edu.np.WhackAMole;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    //initialization
    private Button leftbutton;
    private Button middlebutton;
    private Button rightbutton;
    private int point = 0;
    private TextView score;
    private static final String TAG = "Whack-A-Mole";



    /* Hint
        - The function setNewMole() uses the Random class to generate a random value ranged from 0 to 2.
        - The function doCheck() takes in button selected and computes a hit or miss and adjust the score accordingly.
        - The function doCheck() also decides if the user qualifies for the advance level and triggers for a dialog box to ask for user to decide.
        - The function nextLevelQuery() builds the dialog box and shows. It also triggers the nextLevel() if user selects Yes or return to normal state if user select No.
        - The function nextLevel() launches the new advanced page.
        - Feel free to modify the function to suit your program.
    */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.v(TAG, "Finished Pre-Initialisation!");

        leftbutton = (Button) findViewById(R.id.leftbutton);
        middlebutton = (Button) findViewById(R.id.middlebutton);
        rightbutton = (Button) findViewById(R.id.rightbutton);
        score = findViewById(R.id.score);



    }

    @Override
    protected void onStart(){
        super.onStart();
        setNewMole();
        Log.v(TAG, "Starting GUI!");
    }
    @Override
    protected void onPause(){
        super.onPause();
        Log.v(TAG, "Paused Whack-A-Mole!");
    }

    @Override
    protected void onStop(){
        super.onStop();
        Log.v(TAG, "Stopped Whack-A-Mole!");
        finish();
    }
    public void setScore()
    {

        String Scores = String.valueOf(point);
        score.setText(Scores);

    }

    public boolean checkMole(String s)
    {
        if (s == "*")
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    public void onClickButton(View v)
    {
        Button button = (Button) v;

        if (button == rightbutton)
        {
            Log.d("#Whack-A-Mole","Right Button Clicked");
        }
        else if (button == leftbutton)
        {
            Log.d("#Whack-A-Mole","Left button Clicked");
        }
        else
        {
            Log.d("#Whack-A-Mole","Middle button Clicked");
        }

        if (checkMole(button.getText().toString()))
        {
            point +=1;
        }
        else
        {
            point-=1;
        }
        setNewMole();
        setScore();
        if (point % 10 == 0){
            nextLevelQuery();

        }

    }
    private void doCheck(Button checkButton) {

        /* Checks for hit or miss and if user qualify for advanced page.
            Triggers nextLevelQuery().
         */
    }

    private void nextLevelQuery(){
        /*
        Builds dialog box here.
        Log.v(TAG, "User accepts!");
        Log.v(TAG, "User decline!");
        Log.v(TAG, "Advance option given to user!");
        belongs here*/
//        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//
//        builder.setTitle("Warning! Insane Whack-A-Mole incoming!");
//        builder.setMessage("Would you like to proceed to the advance mode?");
//        builder.setCancelable(true);
//        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener(){
//            public void onClick(DialogInterface dialog, int id){
//                Log.v(TAG,"User accepts!");
//
//            }
//        });
//        builder.setNegativeButton("No",null);
//
//        AlertDialog alert = builder.create();
//        alert.show();
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Warning! Insane Whack-A-Mole incoming!");
        builder.setMessage("Would you like to proceed to the advance mode?");
        builder.setCancelable(true);
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener(){
            public void onClick(DialogInterface dialog, int id){
                Log.v(TAG,"User accepts!");
                nextLevel();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener(){
            public void onClick(DialogInterface dialog, int id){
		    Log.v(TAG,"User declines");
            }
        });

        AlertDialog alert = builder.create();
        alert.show();



    }

    private void nextLevel(){
        Intent advancePage = new Intent(this,Main2Activity.class);
        String Scores = String.valueOf(point);
        advancePage.putExtra("score",Scores);
        startActivity(advancePage);

        /* Launch advanced page */
    }

    private void setNewMole() {
        Random ran = new Random();
        int randomLocation = ran.nextInt(3);

        Button[] ButtonArray = {leftbutton,middlebutton,rightbutton};
        Button mole = ButtonArray[randomLocation];

        for ( Button b : ButtonArray)
        {
            if (b== mole)
            {
                b.setText("*");
            }
            else
            {
                b.setText("O");
            }
        }
    }
}