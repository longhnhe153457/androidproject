package com.example.projectnews;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class StartGame extends AppCompatActivity {
    /*
    Dev icons originally made by Konpa (under MIT License)
    Link for download: https://devicons.github.io/devicon/
    License: https://github.com/devicons/devicon/blob/master/LICENSE
     */

    // Declare a TextView for showing Timer
    TextView tvTimer;
    // A TextView for showing Result
    TextView tvResult;
    // An ImageView for showing an image in question
    TextView ivShowImage;
    // Instantiate a HashMap to store technology names and corresponding image resource ids
    HashMap<String, String> map = new HashMap<>();
    // An ArrayList for storing technology names only
    ArrayList<String> techList = new ArrayList<>();
    int index;
    Button btn1, btn2, btn3, btn4;
    TextView tvPoints;
    // An integer variable to store points
    int points;
    // A CountDownTimer object reference
    CountDownTimer countDownTimer;
    // And a long integer to store the time limit for each question to be used
    // with the CountDownTimer.
    long millisUntilFinished;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_game);
        // Set the content view with a layout file.
        tvTimer = findViewById(R.id.tvTimer);
        // Get the handles for the Views
        tvResult = findViewById(R.id.tvResult);
        ivShowImage = findViewById(R.id.ivShowImage);
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        btn4 = findViewById(R.id.btn4);
        tvPoints = findViewById(R.id.tvPoints);
        // Initialize index with 0
        index = 0;
        // Populate techList with all the technology names
        techList.add("Con ốc sên");
        techList.add(" Con ruồi, mâm cơm nào cũng có nó, tức là ăn giỗ cả làng");
        techList.add("Cái bàn");
        techList.add("Là cái bóng");
        techList.add("Đập muỗi");
        techList.add("Bàn cờ tướng");
        techList.add("Bàn chân");
        techList.add("Con dao");
        techList.add("Hột gạo");
        techList.add("Gà ác");
        techList.add("Mặt trời");
        techList.add("Mặt trăng");
        techList.add("Mặt trăng");
        techList.add("Mặt trận");
        techList.add("Mặt nạ");
        techList.add("Mặt biển (Mặt sông)");
        // Put all the technology names with technology image resource ids in map.
        map.put(techList.get(0), "Mồm bò mà không phải mồm bò. Đố là con gì");
        map.put(techList.get(1), "Vừa bằng hạt đỗ, ăn giỗ cả làng. Là con gì?");
        map.put(techList.get(2), "Tôi có 4 cái chân, 1 cái lưng nhưng không có cơ thể. Tôi là ai");
        map.put(techList.get(3), "Nắng lửa mưa dầu tôi đâu bỏ bạn. Tối lửa tắt đèn sao bạn lại bỏ tôi. Đó là cái gì");
        map.put(techList.get(4), "Vì tao tao phải đánh tao, vì tao tao phải đánh mày. Hỏi đang làm gì?");
        map.put(techList.get(5),"Bàn gì xe ngựa sớm chiều giơ ra");
        map.put(techList.get(6), "Bàn gì mà lại bước gần bước xa");
        map.put(techList.get(7), "Con gì có mũi có lưỡi hẳn hoi. Có sống không chết người đời cầm luôn?");
        map.put(techList.get(8), "Hột để sống: Một tên. Hột nấu lên: tên khác. Trong nhà nông các bác. Đều có mặt cả hai?");
        map.put(techList.get(9), "Da thịt như than. Áo choàng như tuyết. Giúp người trị bệnh. Mà tên chẳng hiền");
        map.put(techList.get(10),"Mặt gì tròn trịa trên cao\n" +
                "\n" +
                "Toả ra những ánh nắng đào đẹp thay?");
        map.put(techList.get(11), "Mặt gì mát dịu đêm nay,\n" +
                "\n" +
                "Cây đa, chú cuội, đứng đây rõ ràng?");
        map.put(techList.get(12), "Mặt gì bằng phặng thênh thang,\n" +
                "\n" +
                "Người đi muôn lối dọc ngang phố phường?");
        map.put(techList.get(13), "Mặt gì làm bãi chiến trường,\n" +
                "\n" +
                "Làm cho đổ máu, tan xương, cháy nhà?");
        map.put(techList.get(14), "Mặt gì để doạ người ta,\n" +
                "\n" +
                "Đeo vào trẻ sợ như ma hiện hồn?");
        map.put(techList.get(15), "Mặt gì xoa động luôn luôn,\n" +
                "\n" +
                "Thuyền bè qua lại bán buôn hàng ngày?");
        // Shuffle techList so that each time we open the app it starts with
        // a random question
        Collections.shuffle(techList);
        // Initialize millisUntilFinished. Set 10 thousand milliseconds or
        // 10 seconds time limit for each question (or as per your need).
        millisUntilFinished = 10000;
        // Initialize points to 0
        points = 0;
        // Call startGame() method that is responsible for starting the quiz.
        startGame();
    }

    private void startGame() {
        // Initialize millisUntilFinished with 10 seconds.
        millisUntilFinished = 10000;
        // Set the TextView for Timer.
        tvTimer.setText("" + (millisUntilFinished / 1000) + "s");
        // Set the TextView for points.
        tvPoints.setText(points + " / " + techList.size());
        // To generate a question, call generateQuestions() method and pass
        // index as parameter.
        generateQuestions(index);
        // Instantiate the countDownTimer object.
        // Android CountDownTimer class is used to schedule a countdown
        // until a time in the future defined by the value you specify,
        // with regular notifications on intervals along the way.
        // In the constructor for CountDownTimer you need to specify two things:
        // 1. The number of millis in the future from the call to
        // start() method until the countdown is done and onFinish() method is called.
        // And,
        // 2. The interval along the way to receive onTick() callbacks.
        // You also have to override onTick() and onFinish() methods.
        countDownTimer = new CountDownTimer(millisUntilFinished, 1000) {
            // In our case, onTick() callback method is fired on regular intervals of
            // 1000 milliseconds or 1 second and onFinish() callback method is fired
            // when the timer finishes.
            @Override
            public void onTick(long millisUntilFinished) {
                // Update tvTimer every 1 second to show the number of seconds remaining.
                tvTimer.setText("" + (millisUntilFinished / 1000) + "s");
            }

            @Override
            public void onFinish() {
                // Increment index by 1 so that the next question can be presented
                // automatically when the user is unable to select his/her answer.
                index++;
                // When timer is finished check if all questions are being asked.
                if (index > techList.size() - 1){
                    // If true, hide the ImageView and Buttons.
                    ivShowImage.setVisibility(View.GONE);
                    btn1.setVisibility(View.GONE);
                    btn2.setVisibility(View.GONE);
                    btn3.setVisibility(View.GONE);
                    btn4.setVisibility(View.GONE);
                    // Go to GameOver screen with points using an Intent
                    Intent intent = new Intent(StartGame.this, GameOver.class);
                    intent.putExtra("points", points);
                    startActivity(intent);
                    finish();
                } else {
                    countDownTimer = null;
                    startGame();
                }
            }
        }.start(); // Call start() method to start the timer.
    }

    private void generateQuestions(int index) {
        // Clone techList to a new ArrayList called techListTemp.
        ArrayList<String> techListTemp = (ArrayList<String>) techList.clone();
        // Get the correct answer for the current question from techList using index.
        String correctAnswer = techList.get(index);

        techListTemp.remove(correctAnswer);
        Collections.shuffle(techListTemp);
        ArrayList<String> newList = new ArrayList<>();
        // Get first three elements from techListTemp and add into newList.
        newList.add(techListTemp.get(0));
        newList.add(techListTemp.get(1));
        newList.add(techListTemp.get(2));
        // Also add the correct answer into newList
        newList.add(correctAnswer);
        // Shuffle newList so that the correct answer can be placed in one of the four
        // buttons, randomly.
        Collections.shuffle(newList);
        // Once you shuffle newList, set all four Button's text with the elements
        // from newList.
        btn1.setText(newList.get(0));
        btn2.setText(newList.get(1));
        btn3.setText(newList.get(2));
        btn4.setText(newList.get(3));
        // Also, set the ImageView with current image from map
        ivShowImage.setText(map.get(techList.get(index)));
    }

    public void nextQuestion(View view) {
        btn1.setBackgroundColor(Color.parseColor("#2196f3"));
        btn2.setBackgroundColor(Color.parseColor("#2196f3"));
        btn3.setBackgroundColor(Color.parseColor("#2196f3"));
        btn4.setBackgroundColor(Color.parseColor("#2196f3"));
        // Enable the buttons
        btn1.setEnabled(true);
        btn2.setEnabled(true);
        btn3.setEnabled(true);
        btn4.setEnabled(true);
        // Cancel the countDownTimer
        countDownTimer.cancel();
        index++;
        if (index > techList.size() - 1){
            // If true, hide the ImageView and Buttons.
            ivShowImage.setVisibility(View.GONE);
            btn1.setVisibility(View.GONE);
            btn2.setVisibility(View.GONE);
            btn3.setVisibility(View.GONE);
            btn4.setVisibility(View.GONE);
            // Go to GameOver screen with points
            Intent intent = new Intent(StartGame.this, GameOver.class);
            intent.putExtra("points", points);
            startActivity(intent);
            // Finish StartGame Activity
            finish();
        } else {
            countDownTimer = null;
            startGame();
        }
    }

    public void answerSelected(View view) {
        view.setBackgroundColor(Color.parseColor("#17243e"));
        // Disable all four Buttons
        btn1.setEnabled(false);
        btn2.setEnabled(false);
        btn3.setEnabled(false);
        btn4.setEnabled(false);
        // The user has selected an answer, so, cancel the countDownTimer
        countDownTimer.cancel();
        // Get clicked button's text for user answer
        String answer = ((Button) view).getText().toString().trim();
        String correctAnswer = techList.get(index);
        if(answer.equals(correctAnswer)){
            points++;
            tvPoints.setText(points + " / " + techList.size());
            tvResult.setText("Correct");
        } else {
            tvResult.setText("Wrong");
        }
    }
}
