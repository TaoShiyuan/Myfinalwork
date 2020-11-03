package com.example.myfinalwork.ui.login;

import android.app.Activity;

import androidx.appcompat.app.AlertDialog;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myfinalwork.DBHelper;
import com.example.myfinalwork.MainActivity;
import com.example.myfinalwork.R;
import com.example.myfinalwork.jitashe.Jitashe;
import com.example.myfinalwork.ui.login.LoginViewModel;
import com.example.myfinalwork.ui.login.LoginViewModelFactory;

public class LoginActivity extends AppCompatActivity {

    public static String xuehao;
    AlertDialog dialog;
    private LoginViewModel loginViewModel;
    private DBHelper dbHelper;
    EditText usernameEditText;
    EditText passwordEditText;
int flag=0;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
//连接数据库
        dbHelper = new DBHelper(this, "MyFinalWork.db", null, 1);


        loginViewModel = ViewModelProviders.of(this, new LoginViewModelFactory())
                .get(LoginViewModel.class);
        usernameEditText = findViewById(R.id.username);
        passwordEditText = findViewById(R.id.password);
        final Button loginButton = findViewById(R.id.login);
        final ProgressBar loadingProgressBar = findViewById(R.id.loading);

        loginViewModel.getLoginFormState().observe(this, new Observer<LoginFormState>() {
            @Override
            public void onChanged(@Nullable LoginFormState loginFormState) {
                if (loginFormState == null) {
                    return;
                }
                loginButton.setEnabled(loginFormState.isDataValid());
                if (loginFormState.getUsernameError() != null) {
                    usernameEditText.setError(getString(loginFormState.getUsernameError()));
                }
                if (loginFormState.getPasswordError() != null) {
                    passwordEditText.setError(getString(loginFormState.getPasswordError()));
                }
            }
        });

        loginViewModel.getLoginResult().observe(this, new Observer<LoginResult>() {
            @Override
            public void onChanged(@Nullable LoginResult loginResult) {
                if (loginResult == null) {
                    return;
                }
                loadingProgressBar.setVisibility(View.GONE);
                if (loginResult.getError() != null) {
                    showLoginFailed(loginResult.getError());
                }
                if (loginResult.getSuccess() != null) {
                    updateUiWithUser(loginResult.getSuccess());
                }
                setResult(Activity.RESULT_OK);

                //Complete and destroy login activity once successful
                finish();
            }
        });

        TextWatcher afterTextChangedListener = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // ignore
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // ignore
            }

            @Override
            public void afterTextChanged(Editable s) {
                loginViewModel.loginDataChanged(usernameEditText.getText().toString(),
                        passwordEditText.getText().toString());
            }
        };
        usernameEditText.addTextChangedListener(afterTextChangedListener);
        passwordEditText.addTextChangedListener(afterTextChangedListener);
        passwordEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {

            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    loginViewModel.login(usernameEditText.getText().toString(),
                            passwordEditText.getText().toString());
                }
                return false;
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadingProgressBar.setVisibility(View.VISIBLE);
                loginViewModel.login(usernameEditText.getText().toString(),
                        passwordEditText.getText().toString());
            }
        });
    }

    private void updateUiWithUser(LoggedInUserView model) {
        usernameEditText = findViewById(R.id.username);
        passwordEditText = findViewById(R.id.password);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        Cursor cursor = db.query("LOGIN", null, null, null, null, null, null);
        String number, password;

        if (cursor.moveToFirst()) {
            do {
                number = cursor.getString(cursor.getColumnIndex("number"));
                password = cursor.getString(cursor.getColumnIndex("password"));
                if (usernameEditText.getText().toString().equals(number)) {
                    if (passwordEditText.getText().toString().equals(password)) {
                        xuehao = number;
                        Intent intent = new Intent(this, MainActivity.class);
                        startActivity(intent);
                        String welcome = getString(R.string.welcome) + model.getDisplayName();
                        // TODO : initiate successful logged in experience
                        Toast.makeText(getApplicationContext(), "welcome" + usernameEditText.getText().toString(), Toast.LENGTH_LONG).show();
                        //连接数据库
                        dbHelper = new DBHelper(this, "MyFinalWork.db", null, 1);
                        SQLiteDatabase db1 = dbHelper.getWritableDatabase();
                        //将登陆用户信息加入INFORMATION中
                        ContentValues values1 = new ContentValues();
                        values1.put("number", number);
                        db1.insert("INFORMATION", null, values1);
                        //创建用户个人的社团表
//                        db1.execSQL("create table INFORMATION_SHETUAN("+
//                                "id integer primary key autoincrement,"+
//                                "shetuan text NOT NULL UNIQUE)");
                        db1.close();
                        flag=1;
                    } else {
                        Intent intent = new Intent(this, LoginActivity.class);
                        startActivity(intent);
                        Toast.makeText(getApplicationContext(), "密码错误或用户不存在，请重新输入", Toast.LENGTH_LONG).show();
                    }
                }
                Log.d("SQLiteTest", "number is:" + number);
                Log.d("SQLiteTest", "password is:" + password);
            } while (cursor.moveToNext());
        }
        if(flag==0){
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            Toast.makeText(getApplicationContext(), "用户不存在，请重新输入", Toast.LENGTH_LONG).show();
        }

    }

    private void showLoginFailed(@StringRes Integer errorString) {
        Toast.makeText(getApplicationContext(), errorString, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (dialog != null) {
            dialog.dismiss();
        }
    }
}