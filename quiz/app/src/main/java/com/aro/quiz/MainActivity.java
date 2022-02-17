package com.aro.quiz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.aro.quiz.databinding.ActivityMainBinding;
import com.aro.quiz.model.Question;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.snackbar.Snackbar;
import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    private int currentQuestionIndex = 0;

    private ArrayList<Integer> questionOrderList;

    private int currentScore = 0;

    private final int numberOfQuestions = 20;

    private int correctChain = -1;

    private int correctAnswersCounter = 0;

    private int highScore = 0;

    private String highScoreName = "Default";

    private static final String MESSAGE_ID = "stored_high_score_info_aro_quiz";


    private final Question[] questionBank = new Question[]{
            //create question objects

            //USA
            new Question(R.string.question_amendments, false),
            //new Question(R.string.question_constitution, true),
            //new Question(R.string.question_declaration, true),
            //new Question(R.string.question_independence_rights, true),
            //new Question(R.string.question_religion, true),
            new Question(R.string.question_government, false),
            new Question(R.string.question_government_feds, false),
            new Question(R.string.question_government_senators, true),

            //Movies
            new Question(R.string.matrix_food_true, true),
            new Question(R.string.lord_rings_false, false),
            new Question(R.string.frozen_setting_true, true),
            new Question(R.string.winter_soldier_false, false),
            new Question(R.string.james_bond_true, true),
            new Question(R.string.fight_club_true, true),
            new Question(R.string.joker_false, false),
            new Question(R.string.kirk_halloween_true, true),
            new Question(R.string.mighty_ducks_false, false),
            new Question(R.string.lion_king_true, true),
            new Question(R.string.independence_day_true, true),
            new Question(R.string.private_ryan_false, false),
            new Question(R.string.haley_joel_false, false),
            new Question(R.string.shrek_true, true),
            new Question(R.string.mission_true, true),
            new Question(R.string.handle_the_truth_true, true),
            new Question(R.string.hoth_false, false),



            //History
            new Question(R.string.pilgrims_true, true),
            new Question(R.string.flag_stripes_false, false),
            new Question(R.string.us_olympics_true, true),
            new Question(R.string.declaration_false, false),
            new Question(R.string.mount_rushmore_true, true),
            new Question(R.string.white_house_first_false, false),
            new Question(R.string.statue_liberty_true, true),
            new Question(R.string.stars_true, true),
            new Question(R.string.maine_true, true),
            new Question(R.string.yale_false, false),
            new Question(R.string.titanic_true, true),
            new Question(R.string.england_oldest_false, false),
            new Question(R.string.london_fire_true, true),
            new Question(R.string.leif_true, true),
            new Question(R.string.industrial_rev_false, false),
            new Question(R.string.bologna_true, true),
            new Question(R.string.pompeii_false, false),
            new Question(R.string.rome_false, false),
            new Question(R.string.wheel_true, true),
            new Question(R.string.water_moon_true, true),
            new Question(R.string.achilles_true, true),
            new Question(R.string.rev_war_true, true),





            //General
            new Question(R.string.mm_false, false),
            new Question(R.string.greek_myth_first_woman_true, true),
            new Question(R.string.winnie_pooh_false, false),
            new Question(R.string.knox_true, true),
            new Question(R.string.dst_false, false),
            new Question(R.string.howler_monkey_false, false),
            new Question(R.string.potato_head_true, true),
            new Question(R.string.aglet_true, true),
            new Question(R.string.shakespeare_false, false),
            new Question(R.string.talc_true, true),
            new Question(R.string.thatcher_true, true),
            new Question(R.string.ribs_false, false),
            new Question(R.string.india_ocean_false, false),
            new Question(R.string.mercury_false, false),
            new Question(R.string.baby_knees_true, true),
            new Question(R.string.octopus_heart_true, true),
            new Question(R.string.giraffe_true, true),
            new Question(R.string.classical_music_plants_true, true),
            new Question(R.string.first_state_false, false),
            new Question(R.string.ph_water_false, false),
            new Question(R.string.bishop_false, false),
            new Question(R.string.scissors_true, true),
            new Question(R.string.caesar_salad_true, true),
            new Question(R.string.nfl_super_bowl_false, false),
            new Question(R.string.state_borders_false, false),
            new Question(R.string.bagels_true, true),
            new Question(R.string.hydrogen_true, true),
            new Question(R.string.sake_false, false),
            new Question(R.string.chickpeas_true, true),
            new Question(R.string.chocolate_false, false),
            new Question(R.string.strawberry_true, true),
            new Question(R.string.mercury_true, true),
            new Question(R.string.love_apple_true, true),
            new Question(R.string.elton_joel_false, false),
            new Question(R.string.japan_vending_true, true),
            new Question(R.string.sirius_true, true),
            new Question(R.string.carrots_false, false),
            new Question(R.string.patronus_false, false),
            new Question(R.string.dali_false, false),
            new Question(R.string.home_plate_true, true),
            new Question(R.string.blueberry_true, true),
            new Question(R.string.coffee_true, true),
            new Question(R.string.moon_true, true),
            new Question(R.string.bones_false, false),
            new Question(R.string.potassium_true, true),
            new Question(R.string.ant_false, false),
            new Question(R.string.soccer_football_true, true),


            //Books
            new Question(R.string.don_quixote_true, true),
            new Question(R.string.beowulf_true, true),
            new Question(R.string.twain_false, false),
            new Question(R.string.dorian_gray_true, true),
            new Question(R.string.scrooge_false, false),



            //Video Games
            new Question(R.string.pong_false, false),
            new Question(R.string.snes_true, true),
            new Question(R.string.nintendo_true, true),
            new Question(R.string.wow_false, false),
            new Question(R.string.us_airforce_ps3_true, true),
            new Question(R.string.jump_man_true, true),
            new Question(R.string.vr_false, false),
            new Question(R.string.yoshi_false, false),
            new Question(R.string.rupees_true, true),
            new Question(R.string.aerosmith_true, true),
            new Question(R.string.rainbowroad_false, false),


    // add more or different questions like this. Also add the strings to the string.xml
            /* if we are making questions with more than one answer we will change the parameter than question takes in from
               a bool to an int and then save the int of the correct answer here. If we were to do this we would also need to have
               a list of strings for the answers associated with each question.
             */

    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        AdView mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);



        SharedPreferences sharedPreferences = getSharedPreferences(MESSAGE_ID, MODE_PRIVATE);
        highScore = sharedPreferences.getInt("high_score", 0);
        highScoreName = sharedPreferences.getString("high_score_name", "Default");

        //randomize the order of the questions
        questionOrderList = new ArrayList<>();
        setQuestionOrderList();

        //set the views with starting point data for new game
        binding.questionTextview.setText(questionBank[questionOrderList.get(currentQuestionIndex)].getAnswerResId());
        binding.scoreText.setText(String.valueOf(currentScore));
        // Question: 1 / 20
        binding.questionNumberText.setText(getString(R.string.question_counter_view_string)
                + (currentQuestionIndex + 1) + " / " + numberOfQuestions);


        binding.trueButton.setOnClickListener(this::trueButton);
        binding.falseButton.setOnClickListener(this::falseButton);

    }

    private void showEndScreen(){

        //instantiate the layout and get the views
        BottomSheetDialog gameOverDialog = new BottomSheetDialog(this);
        gameOverDialog.setContentView(R.layout.endscreen_dialog);
        TextView currentScoreText = gameOverDialog.findViewById(R.id.current_score_endscreen_text);
        TextView highScoreText = gameOverDialog.findViewById(R.id.highscore_text_endscreen);
        TextView numberOfQuestionCorrectText = gameOverDialog.findViewById(R.id.current_questions_right_text_endscreen);
        TextView newHighScoreHeaderText = gameOverDialog.findViewById(R.id.new_high_score_header_endscreen);
        EditText newHighScoreUserNameText = gameOverDialog.findViewById(R.id.new_high_score_edit_text_endscreen);
        Button newHighScoreButton = gameOverDialog.findViewById(R.id.new_high_score_button);

        //set the text for this game
        assert currentScoreText != null;
        // Your Score: 800
        currentScoreText.setText(getString(R.string.your_score_dialog_view_string) + currentScore);
        assert numberOfQuestionCorrectText != null;
        // Correct Answers: 9/20
        numberOfQuestionCorrectText.setText(getString(R.string.correct_answers_dialog_string)
                + correctAnswersCounter +  "/" + numberOfQuestions);


        SharedPreferences sharedPreferences = getSharedPreferences(MESSAGE_ID, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        //if there is a new high score
        assert highScoreText != null;
        if(currentScore >= highScore){

            //toggle the views to new high score mode
            highScoreText.setVisibility(View.GONE);
            assert newHighScoreHeaderText != null;
            newHighScoreHeaderText.setVisibility(View.VISIBLE);
            assert newHighScoreUserNameText != null;
            newHighScoreUserNameText.setVisibility(View.VISIBLE);
            assert newHighScoreButton != null;
            newHighScoreButton.setVisibility(View.VISIBLE);

            //when the user presses enter get their input and save the new high score
            newHighScoreButton.setOnClickListener(view -> {
                //save new high score and set the views

                highScoreName = newHighScoreUserNameText.getText().toString().trim();
                highScore = currentScore;

                editor.putInt("high_score", highScore);
                editor.putString("high_score_name", highScoreName);
                editor.apply();

                //set the high score text and toggle new high score views back off
                highScoreText.setVisibility(View.VISIBLE);
                highScoreText.setText(getString(R.string.high_score_view_string) + highScore + getString(R.string.by) + highScoreName );
                newHighScoreHeaderText.setVisibility(View.GONE);
                newHighScoreUserNameText.setVisibility(View.GONE);
                newHighScoreButton.setVisibility(View.GONE);

            });
        }
        // no new high score
        else{
            //set the high score text using the high score that was obtained on create
            highScoreText.setText(getString(R.string.high_score_view_string) + highScore + getString(R.string.by) + highScoreName );
        }

        gameOverDialog.show();


        //when the dialog is closed reset the quiz

        gameOverDialog.setOnCancelListener(dialogInterface -> resetQuiz());

        gameOverDialog.setOnDismissListener(dialogInterface -> resetQuiz());

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR);
    }

    @Override
    protected void onStop() {
        super.onStop();
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR);
    }

    @Override
    protected void onPause() {
        super.onPause();
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR);
    }

    private void resetQuiz(){

        //set variables to starting point
        currentQuestionIndex = 0;
        currentScore = 0;
        correctAnswersCounter = 0;
        correctChain = -1;
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR);


        questionOrderList.clear();
        setQuestionOrderList();




        //set the views back to starting point
        binding.questionTextview.setText(questionBank[questionOrderList.get(currentQuestionIndex)].getAnswerResId());
        binding.scoreText.setText(String.valueOf(currentScore));
        binding.questionNumberText.setText(getString(R.string.question_counter_view_string) + (currentQuestionIndex + 1) + " / " + numberOfQuestions);

        //turn the buttons on
        binding.trueButton.setEnabled(true);
        binding.falseButton.setEnabled(true);

    }

    private void setQuestionOrderList(){

        //add all the questions to a list and then shuffle them
        for (int i = 0; i < questionBank.length; i++) {

            questionOrderList.add(i);

        }
        Collections.shuffle(questionOrderList);

        //note that the length of the quiz is set by numberOfQuestions.
        // the questionOrderList is longer than that but we will only use the first X (=numberOfQuestions)

    }


    private void falseButton(View view) {
        //to use an int instead we would simply pass the int we have associated with the button rather than true/false
        checkAnswer(false);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LOCKED);
    }
    private void trueButton(View view) {
        //to use an int instead we would simply pass the int we have associated with the button rather than true/false
        checkAnswer(true);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LOCKED);
    }



    private void nextQuestion(){
        if((currentQuestionIndex + 1) < numberOfQuestions){
            currentQuestionIndex++;
            updateQuestion();
        }
        else{
            showEndScreen();
        }
    }


    //if we change our app to take multiple choice answers we need to pass an int to this function rather than boolean
    private void checkAnswer(boolean userAnswer){
        //this would also need to change to reflect multiple choice
        boolean correctAnswer = questionBank[questionOrderList.get(currentQuestionIndex)].isAnswerTrue();


        //turn off the buttons after the question is answered
        binding.trueButton.setEnabled(false);
        binding.falseButton.setEnabled(false);

        //this lets us get the string id for the message we want to display based on the user answer.
        // It should stay the same even if we update to an int answer
        int messageId;
        if(correctAnswer == userAnswer ){
            messageId = R.string.correct_answer;
            correctChain = correctChain + 1;
            float multiplier = 1f + correctChain/10f;
            int pointsToAdd = (int) (100 * multiplier);
            currentScore = currentScore + pointsToAdd;
            correctAnswersCounter = correctAnswersCounter + 1;
        }
        else{
            messageId = R.string.wrong_answer;
            correctChain = -1;
            currentScore = currentScore - 10;
            if(currentScore < 0){ currentScore = 0;}
        }
        binding.scoreText.setText(String.valueOf(currentScore));
        Snackbar.make(binding.falseButton, messageId, Snackbar.LENGTH_SHORT).show();

        Handler mHandler = new Handler();

        //run the nextQuestion method after 1300 ms
        mHandler.postDelayed(this::nextQuestion, 1300);
    }

    private void updateQuestion() {

        binding.questionTextview.setText(questionBank[questionOrderList.get(currentQuestionIndex)].getAnswerResId());

        //turn on the buttons
        binding.trueButton.setEnabled(true);
        binding.falseButton.setEnabled(true);

        //update the question counter text
        binding.questionNumberText.setText(getString(R.string.question_counter_view_string) + (currentQuestionIndex + 1) + " / " + numberOfQuestions);
    }
}