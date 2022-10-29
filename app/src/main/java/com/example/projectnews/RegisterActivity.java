package com.example.projectnews;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.projectnews.dao.DBHelper;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class RegisterActivity  extends AppCompatActivity {
    EditText username, password, repassword,email;
    Button signup, signin;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        username = (EditText) findViewById(R.id.dktaikhoan);
        email =(EditText) findViewById(R.id.inputEmail);
        password = (EditText) findViewById(R.id.dkmatkhau);
        repassword = (EditText) findViewById(R.id.redkmatkhau);
        signup = (Button) findViewById(R.id.dkdangky);
        signin = (Button) findViewById(R.id.relogin);
        DB = new DBHelper(this);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = username.getText().toString().trim();
                String pass = password.getText().toString();
                String repass = repassword.getText().toString();
                String email1 = email.getText().toString().trim();

                if(user.equals("")||pass.equals("")||repass.equals(""))
                    Toast.makeText(RegisterActivity.this, "Vui lòng điền đủ thông tin", Toast.LENGTH_SHORT).show();
                else{
                    if(pass.equals(repass)){
                        Boolean checkuser = DB.checkusername(user);
                        if(checkuser==false && email1.contains("@") && isValidPassword(pass)){
                            Boolean insert = DB.insertData(user, pass,email1);
                            if(insert==true){
                                Toast.makeText(RegisterActivity.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                                intent.putExtra("username",user);
                                startActivity(intent);
                            }else{
                                Toast.makeText(RegisterActivity.this, "Đăng nhập thất bại", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else if( !email1.contains("@")){
                            Toast.makeText(RegisterActivity.this, "Email chưa đúng định dạng", Toast.LENGTH_SHORT).show();
                        }
                        else if(!isValidPassword(pass)){
                            Toast.makeText(RegisterActivity.this, "Mật khẩu phải chứa tối thiểu 8 ký tự, ít nhất 1 bảng chữ cái, 1 số và 1 ký tự đặc biệt", Toast.LENGTH_SHORT).show();
                        }

                        else{
                            Toast.makeText(RegisterActivity.this, "Tài khoản đã tồn tại", Toast.LENGTH_SHORT).show();
                        }
                    }

                    else{
                        Toast.makeText(RegisterActivity.this, "Mật khẩu xác nhận không chính xác", Toast.LENGTH_SHORT).show();
                    }
                } }
        });
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
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
