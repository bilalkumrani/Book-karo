package com.example.barberbookingapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskExecutors;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class Forget_password extends AppCompatActivity {
    private EditText input_mobile;
    private String mobile_no;
    FirebaseAuth fAuth;
    AwesomeValidation awesomeValidation;
    String verificationId;
    PhoneAuthProvider.ForceResendingToken Token;
    ProgressBar progressBar;
    TextView state;
    EditText codeEnter;
    Button send_otp;
    boolean verificationInProgress = false;
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks = new
            PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

        ////////////////////////////////////////////////////////
                ///////////////////////////
        @Override
        public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
            super.onCodeSent(s, forceResendingToken);
            verificationId = s;
            Token = forceResendingToken;
            progressBar.setVisibility(View.GONE);
            state.setVisibility(View.GONE);
            codeEnter.setVisibility(View.VISIBLE);

            Toast.makeText(
                    getApplicationContext(),
                    "SUCCESS",
                    Toast.LENGTH_SHORT
            ).show();
            send_otp.setText("Verify");
            verificationInProgress = true;


        }



                @Override
                public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {

                    String code = phoneAuthCredential.getSmsCode();
                    if(code!=null)
                    {
                        codeEnter.setText(code);
                        verifyAuth(code);

                    }

                }

                @Override
                public void onVerificationFailed(@NonNull FirebaseException e) {

                    Toast.makeText(
                            getApplicationContext(),
                            "Failed",
                            Toast.LENGTH_SHORT
                    ).show();

                }
            };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);
        progressBar = findViewById(R.id.progress);
        codeEnter = findViewById(R.id.codeEnter);
        state = findViewById(R.id.state);
        send_otp = findViewById(R.id.send_otp_btn);
    }


    public void send_otp(View view)
    {
        if(!verificationInProgress)
        {
            awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);
            input_mobile = (EditText)findViewById(R.id.mobile_no_field);
            awesomeValidation.addValidation(this, R.id.mobile_no_field, "^[+]?[0-9]{10,13}$", R.string.invalid);
            if (awesomeValidation.validate())
            {
                mobile_no = input_mobile.getText().toString().trim();

                ////////////MOBILE NO AVAILABLE HERE

                state = findViewById(R.id.state);
                state.setText("Sending OTP");
                PhoneAuthProvider.getInstance().verifyPhoneNumber(
                        mobile_no,        // Phone number to verify
                        60,                 // Timeout duration
                        TimeUnit.SECONDS,   // Unit of timeout
                        TaskExecutors.MAIN_THREAD,               // Activity (for callback binding)
                        mCallbacks);        // OnVerificationStateChangedCallbacks

            }
            else
            {
                input_mobile.setError("Invalid");
            }
        }
        else
        {
            codeEnter =(EditText) findViewById(R.id.codeEnter);
            String userOtp = codeEnter.getText().toString().trim();
            if(!userOtp.isEmpty() && userOtp.length() == 6)
            {

               verifyAuth(userOtp);
            }
            else
            {
                codeEnter.setError("Invalid");
            }
        }
    }

    private void verifyAuth(String code)
    {
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationId,code);
        signInWithPhoneAuthCredential(credential);

    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {

        fAuth = FirebaseAuth.getInstance();
        fAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            Toast.makeText(getApplicationContext(),"Operation Successful", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            // Sign in failed, display a message and update the UI

                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                                // The verification code entered was invalid
                                Toast.makeText(getApplicationContext(),"Operation Failed", Toast.LENGTH_SHORT).show();

                            }
                        }
                    }
                });
    }

}