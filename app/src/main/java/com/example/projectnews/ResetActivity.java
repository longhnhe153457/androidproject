package com.example.projectnews;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.projectnews.dao.DBHelper;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ResetActivity extends AppCompatActivity {
    EditText  reset_pass, rereset_pass;
    TextView username;
    Button reset;
    DBHelper DB;
    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset);
        username=(TextView) findViewById(R.id.username_reset);
        reset_pass=(EditText) findViewById(R.id.reset_pass);
        rereset_pass=(EditText) findViewById(R.id.reret_pass);
        reset=(Button) findViewById(R.id.btnconfirm);
        DB = new DBHelper(this);
        Intent intent = getIntent();
        username.setText(intent.getStringExtra("username"));

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = username.getText().toString().trim();
                String pass = reset_pass.getText().toString();
                String repass = rereset_pass.getText().toString();
                if (user.equals("") || pass.equals("") || repass.equals(""))
                    Toast.makeText(ResetActivity.this, "Vui lòng điền đủ thông tin", Toast.LENGTH_SHORT).show();
                else {

                    Boolean checkuser = DB.updatepass(user, pass);
                    if (checkuser == true && isValidPassword(pass)) {
                        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                        startActivity(intent);
                        Toast.makeText(ResetActivity.this, "Reset pass thành công", Toast.LENGTH_SHORT).show();
                    }
                    else if(!isValidPassword(pass)){
                        Toast.makeText(ResetActivity.this, "Mật khẩu phải chứa tối thiểu 8 ký tự, ít nhất 1 bảng chữ cái, 1 số và 1 ký tự đặc biệt", Toast.LENGTH_SHORT).show();
                    }

                    else {
                        Toast.makeText(ResetActivity.this, "Tài khoản không tồn tại", Toast.LENGTH_SHORT).show();
                    }
                }
            }


        });
    }
    public static boolean isValidPassword(final String password) {

        Pattern pattern;
        Matcher matcher;
        final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{4,}$";
        pattern = Pattern.compile(PASSWORD_PATTERN);
        matcher = pattern.matcher(password);

        return matcher.matches();

    }
}
