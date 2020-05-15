package sg.edu.np.WhackAMole;

import android.content.Intent;
import android.nfc.Tag;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class Main2Activity extends AppCompatActivity {

    //top layer buttons
    private Button t_leftbutton;
    private Button t_middlebutton;
    private Button t_rightbutton;

    //middle layer buttons
    private Button m_leftbutton;
    private Button m_middlebutton;
    private Button m_rightbutton;

    //bottom layer buttons
    private Button b_leftbutton;
    private Button b_middlebutton;
    private Button b_rightbutton;

    //countdown timer
    CountDownTimer myCountDown;

    //score
    private int points;
    private TextView score;

    private Toast mToastToShow;

    private static final String TAG = "Whack-A-Mole";

    /* Hint
        - The function setNewMole() uses the Random class to generate a random value ranged from 0 to 8.
        - The function doCheck() takes in button selected and computes a hit or miss and adjust the score accordingly.
        - The functions readTimer() and placeMoleTimer() are to inform the user X seconds before starting and loading new mole.
        - Feel free to modify the function to suit your program.

    */

    private void readyTimer(){
        final Toast[] mToast = {(null)};

        myCountDown = new CountDownTimer(10000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                Log.v(TAG, "Ready CountDown! " + millisUntilFinished/ 1000);
                if (mToast[0] != null) mToast[0].cancel();
                mToast[0] = Toast.makeText(getApplicationContext(), "Get Ready in " + millisUntilFinished / 1000 + " seconds", Toast.LENGTH_SHORT);
                mToast[0].show();
            }
            @Override
            public void onFinish() {
                Log.v(TAG,"GO!");
                if (mToast[0]!= null) mToast[0].cancel();
                mToast[0] = Toast.makeText(getApplicationContext(), "GO!", Toast.LENGTH_SHORT);
                mToast[0].show();
                placeMoleTimer();
            }

        };
        myCountDown.start();



        /*  HINT:
            The "Get Ready" Timer.
            Log.v(TAG, "Ready CountDown!" + millisUntilFinished/ 1000);
            Toast message -"Get Ready In X seconds"
            Log.v(TAG, "Ready CountDown Complete!");
            Toast message - "GO!"
            belongs here.
            This timer countdown from 10 seconds to 0 seconds and stops after "GO!" is shown.
         */
    }
    private void placeMoleTimer(){
        myCountDown = new CountDownTimer(1000,1000) {
            @Override
            public void onTick(long l) {
                Log.v(TAG, "Ready CountDown! " + l/ 1000);
            }

            @Override
            public void onFinish() {

                setNewMole();
                myCountDown.start();

            }
        };
        myCountDown.start();

        /* HINT:
           Creates new mole location each second.
           Log.v(TAG, "New Mole Location!");
           setNewMole();
           belongs here.
           This is an infinite countdown timer.
         */
    }
    private static final int[] BUTTON_IDS = {
            //top
            R.id.t_leftbutton,R.id.t_middlebutton,R.id.t_rightbutton,
            //middle
            R.id.m_leftbutton,R.id.m_middlebutton,R.id.m_rightbutton,
            //bottom
            R.id.b_leftbutton,R.id.b_middlebutton,R.id.b_rightbutton
        /* HINT:
            Stores the 9 buttons IDs here for those who wishes to use array to create all 9 buttons.
            You may use if you wish to change or remove to suit your codes.*/
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        /*Hint:
            This starts the countdown timers one at a time and prepares the user.
            This also prepares the existing score brought over.
            It also prepares the button listeners to each button.
            You may wish to use the for loop to populate all 9 buttons with listeners.
         */

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        //Log.v(TAG, "Current User Score: " + String.valueOf(advancedScore));

        score = findViewById(R.id.score);
        Intent receiving_score = getIntent();
        String point = receiving_score.getStringExtra("score");
        score.setText(String.valueOf(point));

        points = Integer.parseInt(String.valueOf(point));
        //score.setText(points);


        for(final int id : BUTTON_IDS){
            Log.v(TAG,"abc");
            readyTimer();

            /*  HINT:
            This creates a for loop to populate all 9 buttons with listeners.
            You may use if you wish to remove or change to suit your codes.
            */
        }
    }
    @Override
    protected void onStart(){
        super.onStart();
    }
    private void doCheck(Button checkButton)
    {
//
        if (checkButton.getText().toString() == "*")
        {
            Log.v(TAG, "Hit, score added!");
            points +=1;
        }
        else if (checkButton.getText().toString() != "*"){
            Log.v(TAG, "Missed, point deducted!");
            points -=1;
        }
            /* Hint:
            Checks for hit or miss
            belongs here.
        */
    }



    public void setNewMole()
    {
        /* Hint:
            Clears the previous mole location and gets a new random location of the next mole location.
            Sets the new location of the mole.
         */
        Random ran = new Random();
        int randomLocation = ran.nextInt(9);

        int mole = BUTTON_IDS[randomLocation];

        Button b = (Button)findViewById(mole);
        for (final int id : BUTTON_IDS){
            if ( id == mole){
                Button m = (Button)findViewById(id);
                m.setText("*");
            }
            else{
                Button o = (Button)findViewById(id);
                o.setText("O");
            }
        }
    }

    public void onClickButton(View view) {
        Button b = (Button) view;
        doCheck(b);
        score.setText(String.valueOf(points));
    }
}

