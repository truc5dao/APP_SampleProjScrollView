package com.cuatrucdao.k22411csampleproject;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.view.View;

import android.content.Intent;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.cuatrucdao.connectors.EmployeeConnector;
import com.cuatrucdao.models.Employee;

public class LoginActivity extends AppCompatActivity {

    EditText edtUserName;
    EditText edtPassword;
    CheckBox chkSaveLogin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);

        addViews();

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void addViews() {
        edtUserName = findViewById(R.id.edtUserName);
        edtPassword = findViewById(R.id.edtPassword);
        chkSaveLogin = findViewById(R.id.chkSaveLoginInfor);
    }

    public void do_login(View view) {
        String usr = edtUserName.getText().toString();
        String pwd = edtPassword.getText().toString();
        EmployeeConnector ec = new EmployeeConnector();
        Employee emp = ec.login(usr, pwd);
        if (emp!=null)
        {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
        else
        {
            Toast.makeText(this, "Login failed - please check your account again!",
                    Toast.LENGTH_LONG).show();
        }
    }

    public void do_exit(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
        Resources res = getResources();
        //title
        builder.setTitle(res.getText(R.string.confirm_exit_title));
        builder.setMessage(res.getText(R.string.confirm_exit_message));
        //symbol
        builder.setIcon(android.R.drawable.ic_dialog_alert);
        //implement YES interaction
        builder.setPositiveButton(res.getText(R.string.confirm_exit_yes), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
//                System.exit(0);
                finish();
            }
        });

        builder.setNegativeButton(res.getText(R.string.confirm_exit_no), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
    }

//    private long lastBackPressedTime = 0;
//    private static final long BACK_PRESS_THRESHOLD = 500;
//    @Override
//    public void onBackPressed() {
//        super.onBackPressed();
//        long currentTime = System.currentTimeMillis();
//        if (currentTime - lastBackPressedTime <= BACK_PRESS_THRESHOLD) {
//            do_exit(null);
//        } else {
//            lastBackPressedTime = currentTime;
//            Toast.makeText(this, "Click once more to exit", Toast.LENGTH_SHORT).show();
//        }
//    }

    public void saveLoginInformation()
    {
        SharedPreferences preferences = getSharedPreferences("LOGIN_INFORMATION", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        String usr = edtUserName.getText().toString();
        String pwd = edtPassword.getText().toString();
        boolean isSave = chkSaveLogin.isChecked();
        editor.putString("USERNAME", usr);
        editor.putString("PASSWORD", pwd);
        editor.putBoolean("SAVED", isSave);
        editor.commit();
    }

    @Override
    protected void onPause()
    {
        super.onPause();
        saveLoginInformation();
    }

    ///  khi doc ra, khogn can doi tuong editor
    public void restoreLoginInformation()
    {
        SharedPreferences preferences = getSharedPreferences("LOGIN_INFORMATION", MODE_PRIVATE);
        String usr = preferences.getString("USERNAME", "");
        String pwd = preferences.getString("PASSWORD", "");
        boolean isSave = preferences.getBoolean("", true);

        if (isSave)
        {
            edtUserName.setText(usr);
            edtPassword.setText(pwd);
            chkSaveLogin.setChecked(isSave);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        restoreLoginInformation();
    }
}