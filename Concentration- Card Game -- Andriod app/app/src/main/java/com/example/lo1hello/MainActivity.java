package com.example.lo1hello;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.gridlayout.widget.GridLayout;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Context myCon;
    public static int numberOfGuesses = 0;
    private Integer defaultImg = R.drawable.ca;
    private static final Integer[] imgs = {R.drawable.ca, R.drawable.ck,R.drawable.cq,
                             R.drawable.da,  R.drawable.dk,  R.drawable.dq,R.drawable.ca, R.drawable.ck,R.drawable.cq,
            R.drawable.da,  R.drawable.dk,  R.drawable.dq,};
    private static ArrayList<Integer> shuffledList;
    private ArrayList<GuessedCards> guesses;
    private ArrayList<GuessedCards> flippedCards;
    private static GridLayout grid;
    private static TextView txtGuess;
    private Activity myActivity;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        myActivity = this;
        setContentView(R.layout.activity_main);
        txtGuess = findViewById(R.id.txtGuesses);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        myToolbar.setTitle("CST Memory App");
        setSupportActionBar(myToolbar);
        grid = findViewById(R.id.gridLayout);
        guesses = new ArrayList<>();
        flippedCards = new ArrayList<>();
        shuffleCards();

/**
 * Flips the cards with a delay and disable setClickable on all the cards until the cards are flipped back
 */
        new Thread(() ->{
            Looper.prepare();
           while (true)
           {


               if(guesses.size() == 2)
               {
                   runOnUiThread(() -> DisableCards());
                   try {
                       checkGuesses();

                       Thread.sleep(200);
                       runOnUiThread(() ->EnableCards());

                   } catch (InterruptedException e) {
                       e.printStackTrace();
                   }
               }
           }

        }).start();

    }

    /**
     * Checks if game over
     */
    private void checkIfGameOver() {

        if(flippedCards.size() == 12)
        {
            runOnUiThread(() ->Toast.makeText(myActivity, "Game Over", Toast.LENGTH_SHORT).show());
            newGame();
        }

    }

    /**
     * Start a new activity when game over
     */
    private void newGame()
    {
        Intent intent = new Intent(this,GameOver.class);
        startActivity(intent);
    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        menu.getItem(0).setOnMenuItemClickListener(item -> {
            flipCards();
            return false;
        });
        return true;
    }

    /**
     * This method suffles all the cards in the arraylist to random position
     */
    public static void shuffleCards()
    {

        numberOfGuesses = 0;
        String displayNumOfGusses = "You've made " +numberOfGuesses + " Gusses";
        txtGuess.setText(displayNumOfGusses);
         shuffledList = new ArrayList<>(Arrays.asList(imgs));
        Collections.shuffle(shuffledList);
    }


    /**
     * This method determines which card was clicked and change the img res according to that (the positions are decided by a Arraylist called suffledList)
     * @param v
     */
    @Override
    public void onClick(View v) {

        TextView txt = findViewById(R.id.txtGuesses);

        ImageView view = (ImageView) v;

        switch(v.getId())
        {
            case R.id.imageView1:
                view.setImageResource(shuffledList.get(0));
                guesses.add(new GuessedCards(view,shuffledList.get(0)));
                break;
            case R.id.imageView2:
                view.setImageResource(shuffledList.get(1));
                guesses.add(new GuessedCards(view,shuffledList.get(1)));
                break;
            case R.id.imageView3:
                view.setImageResource(shuffledList.get(2));
                guesses.add(new GuessedCards(view,shuffledList.get(2)));
                break;
            case R.id.imageView4:
                view.setImageResource(shuffledList.get(3));
                guesses.add(new GuessedCards(view,shuffledList.get(3)));
                break;
            case R.id.imageView5:
                view.setImageResource(shuffledList.get(4));
                guesses.add(new GuessedCards(view,shuffledList.get(4)));
                break;
            case R.id.imageView6:
                view.setImageResource(shuffledList.get(5));
                guesses.add(new GuessedCards(view,shuffledList.get(5)));
                break;
            case R.id.imageView7:
                view.setImageResource(shuffledList.get(6));
                guesses.add(new GuessedCards(view,shuffledList.get(6)));
                break;
            case R.id.imageView8:
                view.setImageResource(shuffledList.get(7));
                guesses.add(new GuessedCards(view,shuffledList.get(7)));
                break;
            case R.id.imageView9:
                view.setImageResource(shuffledList.get(8));
                guesses.add(new GuessedCards(view,shuffledList.get(8)));
                break;
            case R.id.imageView10:
                view.setImageResource(shuffledList.get(9));
                guesses.add(new GuessedCards(view,shuffledList.get(9)));
                break;
            case R.id.imageView11:
                view.setImageResource(shuffledList.get(10));
                guesses.add(new GuessedCards(view,shuffledList.get(10)));
                break;
            case R.id.imageView12:
                view.setImageResource(shuffledList.get(11));
                guesses.add(new GuessedCards(view,shuffledList.get(11)));
                break;
        }



    }

    /**
     * This method resets all the cards on the gridlayout
     */

    public static void flipCards()
    {
        shuffleCards();
        for(int i = 0; i<grid.getChildCount(); i++)
        {
            ImageView img = (ImageView) grid.getChildAt(i);
            img.setImageResource(R.drawable.b);


        }
    }

    //

    /**
     *This method is suppose to stop a user to click a already flipped card
     */
    public void checkIfFilpped()
    {

        for(int i = 0; i<grid.getChildCount(); i++)
        {
            if(grid.getChildAt(i).getTag().equals(R.drawable.b))
            {
                grid.getChildAt(i).setClickable(false);
            }


        }
    }

    /**
     * Disable all the views on a gridlayout
     */
    public void DisableCards()
    {

        for(int i = 0; i<grid.getChildCount(); i++)
        {
            grid.getChildAt(i).setClickable(false);


        }
    }

    /**
     * Enable all the views on a gridlayout
     */
    public void EnableCards()
    {

        for(int i = 0; i<grid.getChildCount(); i++)
        {
            grid.getChildAt(i).setClickable(true);

        }
    }

    /**
     * Check if the two selected card are equal or not.
     * @throws InterruptedException
     */
    public void checkGuesses() throws InterruptedException {
        if(guesses.get(0).getDrawableId() == guesses.get(1).getDrawableId())
        {
//            Toast.makeText(this, "Its a match!", Toast.LENGTH_SHORT).show();

            guesses.get(0).setBlFlipped(true);
//            guesses.get(0).getOriginalView().setClickable(false);
            guesses.get(1).setBlFlipped(true);
//            guesses.get(1).getOriginalView().setClickable(false);

            flippedCards.add(guesses.get(0));
            flippedCards.add(guesses.get(1));
            checkIfGameOver();

            runOnUiThread(() -> Toast.makeText(myActivity, "Its a match", Toast.LENGTH_SHORT).show());


        }
        else
        {
            runOnUiThread(() -> Toast.makeText(myActivity, "Its not a match", Toast.LENGTH_SHORT).show());
            Thread.sleep(1000);
            guesses.get(0).flip();
            guesses.get(1).flip();
        }

        guesses = new ArrayList<>();
        numberOfGuesses++;


        String displayNumOfGusses = "You've made " +numberOfGuesses + " Gusses";
        runOnUiThread(() -> txtGuess.setText(displayNumOfGusses));

    }



}