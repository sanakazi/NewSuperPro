package com.superapp.activity.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.superapp.R;
import com.superapp.activity.base.BaseAppCompatActivity;
import com.superapp.activity.base.ErrorType;
import com.superapp.webservice.Interactor;
import com.superapp.webservice.InteractorImpl;
import com.superapp.webservice.ResponsePacket;

import org.json.JSONObject;

public class ActivityChangePassword extends BaseAppCompatActivity {

    Toolbar toolbar;
    EditText newpassword,confirmpassword;
    Button submit;
    String username;
    TextInputLayout textinputpass,textinputconfirmpass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        initView();
        setUpAsAction();
    }

    private void initView()
    {
        toolbar=findViewById(R.id.toolbar);
        newpassword=findViewById(R.id.password);
        confirmpassword=findViewById(R.id.confirmpass);
        submit=findViewById(R.id.submit);
        textinputconfirmpass=findViewById(R.id.textinputconfirmpass);
        textinputpass=findViewById(R.id.textinputpass);
        Intent intent=getIntent();
        if(intent!=null)
        {
            username=intent.getStringExtra("username");
        }
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(getResources().getString(R.string.changepassword));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));
    }

    private void setUpAsAction()
    {
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                onBackPressed();
            }
        });
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                if(validateFields())
                {
                    doUpdatePassword();
                }
            }
        });
    }

    public void doUpdatePassword() {
        JSONObject object = new JSONObject();
        try {
            object.put("username", username);
            object.put("password", newpassword.getText().toString().trim());
            object.put("rePassword", confirmpassword.getText().toString().trim());

        } catch (Exception e) {
            e.printStackTrace();
        }
        new InteractorImpl(ActivityChangePassword.this, ActivityChangePassword.this, Interactor.RequestCode_UpdatePassword, Interactor.Tag_UpdatePassword).
                makeJsonPostRequest(Interactor.Method_UpdatePassword, object, true);
    }

    private boolean validateFields()
    {
        if(TextUtils.isEmpty(newpassword.getText().toString()))
        {
            textinputpass.setError(getResources().getString(R.string.newpassword));
            textinputpass.requestFocus();
            return false;
        }
        else
        {
            textinputpass.setError(null);
        }
        if(TextUtils.isEmpty(confirmpassword.getText().toString()))
        {
            textinputconfirmpass.setError(getResources().getString(R.string.confirmpassword));
            textinputconfirmpass.requestFocus();
            return false;
        }
        else
        {
            textinputconfirmpass.setError(null);
        }

        if(!newpassword.getText().toString().equals(confirmpassword.getText().toString()))
        {
            textinputconfirmpass.setError(getResources().getString(R.string.noMatchPassword));
            textinputconfirmpass.requestFocus();
            return false;
        }
        else
        {
            textinputconfirmpass.setError(null);
        }
        return true;
    }
    @Override
    public void onError(int requestCode, ErrorType errorType) {
        super.onError(requestCode, errorType);
    }


    @Override
    public void onSuccess(int requestCode, ResponsePacket responsePacket) {
        super.onSuccess(requestCode, responsePacket);
        if (responsePacket.getErrorCode() == 0) {
          if (Interactor.RequestCode_UpdatePassword == requestCode) {
                makeToast(responsePacket.getMessage());
                Intent intent = new Intent(ActivityChangePassword.this, ActivityLogin.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
            }
        } else {
            if (responsePacket.getErrorCode() != 410)
                makeToast(responsePacket.getMessage());
        }
    }
}
