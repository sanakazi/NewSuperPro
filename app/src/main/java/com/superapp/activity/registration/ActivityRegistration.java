package com.superapp.activity.registration;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.text.Html;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v7.widget.Toolbar;

import com.superapp.R;
import com.superapp.activity.base.BaseAppCompatActivity;
import com.superapp.activity.base.ErrorType;
import com.superapp.utils.PrefSetup;
import com.superapp.utils.Utilities;
import com.superapp.webservice.Interactor;
import com.superapp.webservice.InteractorImpl;
import com.superapp.webservice.ResponsePacket;

import org.json.JSONObject;

public class ActivityRegistration extends BaseAppCompatActivity {
    TextView tandc;
    Toolbar toolbar;
    EditText name,companyname,mobileno,emailid,password,confirm_password,salescode;
    ImageView owner,client,coworker;
    boolean flag=true;
    TextInputLayout textinputname,textinputcompanyname,textinputmono,textinputemail,textinputpassword,textinputconfirmpassword;
    Button register;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_register);
        initView();
        setUpAsAction();
    }


    private void initView()
    {
        tandc=findViewById(R.id.tandc);
        toolbar=findViewById(R.id.toolbar);
        name=findViewById(R.id.name);
        companyname=findViewById(R.id.companyname);
        mobileno=findViewById(R.id.mono);
        emailid=findViewById(R.id.emailid);
        password=findViewById(R.id.password);
        confirm_password=findViewById(R.id.confirmpass);
        salescode=findViewById(R.id.salescode);
        register=findViewById(R.id.register);
        owner=findViewById(R.id.owner);
        client=findViewById(R.id.client);
        coworker=findViewById(R.id.coworker);
        textinputname=findViewById(R.id.textinputname);
        textinputcompanyname=findViewById(R.id.textinputcompanyname);
        textinputmono=findViewById(R.id.textinputmono);
        textinputemail=findViewById(R.id.textinputemailid);
        textinputpassword=findViewById(R.id.textinputpassword);
        textinputconfirmpassword=findViewById(R.id.textinputconfirmpassword);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(getResources().getString(R.string.registration));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));
        String redString = getResources().getString(R.string.tandc);
        tandc.setText(Html.fromHtml(redString));
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
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                if (validateFields()) {
                    doRegister();
                }
            }
        });
        owner.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent)
            {
                if(motionEvent.getAction()==MotionEvent.ACTION_DOWN)
                {
                    if(flag)
                    {
                        owner.setImageResource(R.mipmap.owner_registeration_selection);
                        client.setImageResource(R.mipmap.project_owner);
                        coworker.setImageResource(R.mipmap.co_worker);
                        PrefSetup.getInstance().setREGISTER_TYPE("d");
                        flag=false;
                    }
                    else
                    {
                        owner.setImageResource(R.mipmap.owner_registeration);
                        flag=true;
                    }
                }
                return false;
            }
        });
        client.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent)
            {
                if(motionEvent.getAction()==MotionEvent.ACTION_DOWN)
                {
                    if(flag)
                    {
                        client.setImageResource(R.mipmap.project_owner_selection);
                        owner.setImageResource(R.mipmap.owner_registeration);
                        coworker.setImageResource(R.mipmap.co_worker);
                        PrefSetup.getInstance().setREGISTER_TYPE("c");
                        flag=false;
                    }
                    else
                    {
                        client.setImageResource(R.mipmap.project_owner);
                        flag=true;
                    }
                }
                return false;
            }
        });
        coworker.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent)
            {
                if(motionEvent.getAction()==MotionEvent.ACTION_DOWN)
                {
                    if(flag)
                    {
                        coworker.setImageResource(R.mipmap.co_worker_selection);
                        owner.setImageResource(R.mipmap.owner_registeration);
                        client.setImageResource(R.mipmap.project_owner);
                        PrefSetup.getInstance().setREGISTER_TYPE("w");
                        flag=false;
                    }
                    else
                    {
                        coworker.setImageResource(R.mipmap.co_worker);
                        flag=true;
                    }
                }
                return false;
            }
        });

       /* owner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                    if(flag)
                    {
                        owner.setImageResource(R.mipmap.owner_registeration_selection);
                        client.setImageResource(R.mipmap.project_owner);
                        coworker.setImageResource(R.mipmap.co_worker);
                        PrefSetup.getInstance().setREGISTER_TYPE("d");
                        flag=false;
                    }
                    else
                    {
                        owner.setImageResource(R.mipmap.owner_registeration);
                        flag=true;
                    }
            }
        });*/
      /*  client.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                if(flag)
                {
                    client.setImageResource(R.mipmap.project_owner_selection);
                    owner.setImageResource(R.mipmap.owner_registeration);
                    coworker.setImageResource(R.mipmap.co_worker);
                    PrefSetup.getInstance().setREGISTER_TYPE("c");
                    flag=false;
                }
                else
                {
                    client.setImageResource(R.mipmap.project_owner);
                    flag=true;
                }
            }
        });
        coworker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                if(flag)
                {
                    coworker.setImageResource(R.mipmap.co_worker_selection);
                    owner.setImageResource(R.mipmap.owner_registeration);
                    client.setImageResource(R.mipmap.project_owner);
                    PrefSetup.getInstance().setREGISTER_TYPE("w");
                    flag=false;
                }
                else
                {
                    coworker.setImageResource(R.mipmap.co_worker);
                    flag=true;
                }
            }
        });*/
    }
    private boolean validateFields() {

        if (TextUtils.isEmpty(name.getText().toString())) {
            textinputname.setError(getString(R.string.requiredName));
            textinputname.requestFocus();
            return false;
        } else {
            textinputname.setError(null);
        }

        if (TextUtils.isEmpty(companyname.getText().toString())) {
            textinputcompanyname.setError(getString(R.string.company_name));
            textinputcompanyname.requestFocus();
            return false;
        } else {
            textinputcompanyname.setError(null);
        }

        if (mobileno.getText().toString().length() != 10) {
            textinputmono.setError(getString(R.string.invalidMobileNo));
            textinputmono.requestFocus();
            return false;
        } else {
            textinputmono.setError(null);
        }
//        if (TextUtils.isEmpty(et_user_id.getText().toString())) {
//            et_user_id.setError("user id required");
//            et_user_id.requestFocus();
//            return false;
//        } else {
//            et_user_id.setError(null);
//        }

        if (!emailid.getText().toString().trim().matches(emailPattern)) {
            textinputemail.setError(getString(R.string.emailid));
            textinputemail.requestFocus();
            return false;
        } else {
            textinputemail.setError(null);
        }
        if (TextUtils.isEmpty(password.getText().toString().trim())) {
            textinputpassword.setError(getString(R.string.requiredPassword));
            textinputpassword.requestFocus();
            return false;
        } else {
            textinputpassword.setError(null);
        }
        if (!confirm_password.getText().toString().trim().equals(password.getText().toString().trim())) {
            textinputconfirmpassword.setError(getString(R.string.noMatchPassword));
            textinputconfirmpassword.requestFocus();
            return false;
        } else {
            textinputconfirmpassword.setError(null);
        }
        return true;
    }

    private void doRegister()
    {

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("name", name.getText().toString().trim());

            // put this parameter for all the persons
            if (PrefSetup.getInstance().getREGISTER_TYPE().equalsIgnoreCase("d")) {
                jsonObject.put("companyName", companyname.getText().toString().trim());
            }
            jsonObject.put("username", emailid.getText().toString().trim());
            jsonObject.put("contactNo", mobileno.getText().toString().trim());
            jsonObject.put("password", password.getText().toString().trim());

           /* if (PrefSetup.getInstance().getREGISTER_TYPE().equalsIgnoreCase("w")) {
                jsonObject.put("isSuperSearch", checkbox_enrollForSuperAppSearch.isChecked() ? "t" : "f");
                PrefSetup.getInstance().setSUPER_SERACH_CHECK(checkbox_enrollForSuperAppSearch.isChecked() ? "t" : "f");
            }*/
            jsonObject.put("registerType", PrefSetup.getInstance().getREGISTER_TYPE());

            jsonObject.put("deviceToken", "TOKEN");
            jsonObject.put("deviceType", "ANDROID");
            new InteractorImpl(ActivityRegistration.this, ActivityRegistration.this, Interactor.RequestCode_Register, Interactor.Tag_Register)
                    .makeJsonPostRequest(Interactor.Method_Register, jsonObject,true);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    public void onError(int requestCode, ErrorType errorType) {
        super.onError(requestCode, errorType);
        if (requestCode == Interactor.RequestCode_Register) {

        }
    }
    @Override
    public void onSuccess(int requestCode, ResponsePacket responsePacket) {
        super.onSuccess(requestCode, responsePacket);
        if (Interactor.RequestCode_Register == requestCode) {
            if (responsePacket.getErrorCode() == 0 && responsePacket.getUserDetail() != null) {

               /* Intent myIntent = new Intent(this, ActivityOtpVerification.class);
                myIntent.putExtra("userId", responsePacket.getUserDetail().getId());
                myIntent.putExtra("mobileNumber", responsePacket.getUserDetail().getContactNo());
                myIntent.putExtra("OTP", responsePacket.getUserDetail().getOTP());
                PrefSetup.getInstance().setUSER_OTP(responsePacket.getUserDetail().getOTP());
                startActivity(myIntent);*/

            } else {
                // TODO do task on error response.
                makeToast(responsePacket.getMessage());
            }
        }
    }


 /*   @Override
    protected void onResume() {
        super.onResume();
    }

    private EditText et_name, et_email, et_mobile_no, et_password, et_rePassword, et_user_id, et_company_name;
    private TextView tv_register, tv_tile8_register;
    private ImageView iv_backButtonRegister;

    private LinearLayout ll_checkBox_enrollForSuperAppSearch;
    Button bt_done_popup;
    CheckBox checkbox_custom_popup, checkbox_enrollForSuperAppSearch;

    public void initView() {
        et_name = (EditText) findViewById(R.id.et_name_register);
        et_email = (EditText) findViewById(R.id.et_eMail_register);
        et_mobile_no = (EditText) findViewById(R.id.et_mobile_no_register);

        setTouchNClickSrc(R.id.iv_back);
        setTouchNClick(R.id.tv_register_register);
        et_password = (EditText) findViewById(R.id.et_password_register);
        et_rePassword = (EditText) findViewById(R.id.et_re_password_register);
        et_company_name = (EditText) findViewById(R.id.et_company_name_register);
        tv_register = (TextView) findViewById(R.id.tv_Title);
        tv_tile8_register = (TextView) findViewById(R.id.tv_tile8_register);

        ll_checkBox_enrollForSuperAppSearch = (LinearLayout) findViewById(R.id.ll_checkBox_enrollForSuperAppSearch);
        checkbox_enrollForSuperAppSearch = (CheckBox) findViewById(R.id.checkbox_enrollForSuperAppSearch);
        ll_checkBox_enrollForSuperAppSearch.setVisibility(View.GONE);

        iv_backButtonRegister = (ImageView) findViewById(R.id.iv_back);
        tv_register.setText("REGISTRATION");

        if (PrefSetup.getInstance().getREGISTER_TYPE().equalsIgnoreCase("d")) {


        } else if (PrefSetup.getInstance().getREGISTER_TYPE().equalsIgnoreCase("c")) {
            et_company_name.setVisibility(View.GONE);
            tv_tile8_register.setVisibility(View.VISIBLE);

        } else if (PrefSetup.getInstance().getREGISTER_TYPE().equalsIgnoreCase("w")) {
            et_company_name.setVisibility(View.GONE);
//            ll_checkBox_enrollForSuperAppSearch.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case R.id.iv_back:
                onBackPressed();
                break;
            case R.id.tv_register_register:
                if (validateFields()) {
                    showStatusPopup(this);
                }
                break;
        }
    }

    @Override
    public void onBackPressed() {
//        super.onBackPressed();
        openLoginScreen();
    }

    private void openLoginScreen() {
        Intent intent = new Intent(ActivityRegistration.this, ActivityMainClient.class);
        finish();
        startActivity(intent);
    }

    private boolean validateFields() {

        if (TextUtils.isEmpty(et_name.getText().toString())) {
           // et_name.setError(getString(R.string.requiredName));
            et_name.requestFocus();
            return false;
        } else {
            et_name.setError(null);
        }

        if (PrefSetup.getInstance().getREGISTER_TYPE().equalsIgnoreCase("d")) {
            if (TextUtils.isEmpty(et_company_name.getText().toString())) {
             //   et_company_name.setError(getString(R.string.requiredCompanyName));
                et_company_name.requestFocus();
                return false;
            } else {
                et_company_name.setError(null);
            }
        }

        if (!PrefSetup.getInstance().getREGISTER_TYPE().equalsIgnoreCase("w")) {
            if (TextUtils.isEmpty(et_email.getText().toString())) {
           //     et_email.setError(getString(R.string.requiredEmail));
                et_email.requestFocus();
                return false;
            } else {
                et_email.setError(null);
            }

            if (!Utilities.getInstance().isValidEmail(et_email.getText().toString())) {
              //  et_email.setError(getString(R.string.invalidEmail));
                et_email.requestFocus();
                return false;
            } else {
                et_email.setError(null);
            }
        }

        if (TextUtils.isEmpty(et_mobile_no.getText().toString())) {
         //   et_mobile_no.setError(getString(R.string.requiredMobileNo));
            et_mobile_no.requestFocus();
            return false;
        } else {
            et_mobile_no.setError(null);
        }

        if (et_mobile_no.getText().toString().length() < 10) {
          //  et_mobile_no.setError(getString(R.string.invalidMobileNo));
            et_mobile_no.requestFocus();
            return false;
        } else {
            et_mobile_no.setError(null);
        }
//        if (TextUtils.isEmpty(et_user_id.getText().toString())) {
//            et_user_id.setError("user id required");
//            et_user_id.requestFocus();
//            return false;
//        } else {
//            et_user_id.setError(null);
//        }
        if (TextUtils.isEmpty(et_password.getText().toString().trim())) {
         //   et_password.setError(getString(R.string.requiredPassword));
            et_password.requestFocus();
            return false;
        } else {
            et_password.setError(null);
        }
        if (TextUtils.isEmpty(et_rePassword.getText().toString().trim())) {
           // et_rePassword.setError(getString(R.string.requiredRePassword));
            et_rePassword.requestFocus();
            return false;
        } else {
            et_rePassword.setError(null);
        }
        if (!et_rePassword.getText().toString().trim().equalsIgnoreCase(et_password.getText().toString().trim())) {
          //  et_rePassword.setError(getString(R.string.noMatchPassword));
            et_rePassword.requestFocus();
            return false;
        } else {
            et_rePassword.setError(null);
        }
        return true;
    }

    private void showStatusPopup(final Context context) {

        final Dialog tncDialog = new Dialog(context);
        tncDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.popup_termsandconditions, null, false);
        tncDialog.setContentView(view);

        final Window window = tncDialog.getWindow();
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        window.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        tncDialog.show();

        final CheckBox checkbox_custom_popup = (CheckBox) view.findViewById(R.id.checkbox_custom_popup);
        Button bt_accept_popup = (Button) view.findViewById(R.id.bt_accept_popup);
        ImageView iv_close = (ImageView) view.findViewById(R.id.iv_close);
        iv_close.setOnClickListener(v -> tncDialog.dismiss());
        bt_accept_popup.setOnClickListener(v -> {
            if (checkbox_custom_popup.isChecked()) {
                tncDialog.dismiss();
                doRegister();

//                Intent intent = new Intent(ActivityRegistration.this, ActivityLogin.class);
//                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                startActivity(intent);
//                finish();
            } else {
                Toast.makeText(ActivityRegistration.this, "Accept terms and conditions to proceed.", Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void doRegister() {

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("name", et_name.getText().toString().trim());

            if (PrefSetup.getInstance().getREGISTER_TYPE().equalsIgnoreCase("d")) {
                jsonObject.put("companyName", et_company_name.getText().toString().trim());
            }
            jsonObject.put("username", et_email.getText().toString().trim());
            jsonObject.put("contactNo", et_mobile_no.getText().toString().trim());
            jsonObject.put("password", et_password.getText().toString().trim());

            if (PrefSetup.getInstance().getREGISTER_TYPE().equalsIgnoreCase("w")) {
                jsonObject.put("isSuperSearch", checkbox_enrollForSuperAppSearch.isChecked() ? "t" : "f");
                PrefSetup.getInstance().setSUPER_SERACH_CHECK(checkbox_enrollForSuperAppSearch.isChecked() ? "t" : "f");
            }
            jsonObject.put("registerType", PrefSetup.getInstance().getREGISTER_TYPE());

            jsonObject.put("deviceToken", "TOKEN");
            jsonObject.put("deviceType", "ANDROID");
            new InteractorImpl(ActivityRegistration.this, ActivityRegistration.this, Interactor.RequestCode_Register, Interactor.Tag_Register)
                    .makeJsonPostRequest(Interactor.Method_Register, jsonObject *//* new JSONObject(new Gson().toJson(beanRegistration))*//*, true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onError(int requestCode, ErrorType errorType) {
        super.onError(requestCode, errorType);
        if (requestCode == Interactor.RequestCode_Register) {

        }
    }

    @Override
    public void onSuccess(int requestCode, ResponsePacket responsePacket) {
        super.onSuccess(requestCode, responsePacket);
        if (Interactor.RequestCode_Register == requestCode) {
            if (responsePacket.getErrorCode() == 0 && responsePacket.getUserDetail() != null) {



                Intent myIntent = new Intent(this, ActivityOtpVerification.class);
                myIntent.putExtra("userId", responsePacket.getUserDetail().getId());
                myIntent.putExtra("mobileNumber", responsePacket.getUserDetail().getContactNo());
                myIntent.putExtra("OTP", responsePacket.getUserDetail().getOTP());
                PrefSetup.getInstance().setUSER_OTP(responsePacket.getUserDetail().getOTP());
                startActivity(myIntent);

            } else {
                // TODO do task on error response.
                makeToast(responsePacket.getMessage());
            }
        }
    }*/

}


