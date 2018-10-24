package app.formvalidation;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.mobsandgeeks.saripaar.annotation.ConfirmPassword;
import com.mobsandgeeks.saripaar.annotation.Email;
import com.mobsandgeeks.saripaar.annotation.Length;
import com.mobsandgeeks.saripaar.annotation.Max;
import com.mobsandgeeks.saripaar.annotation.Min;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import com.mobsandgeeks.saripaar.annotation.Or;
import com.mobsandgeeks.saripaar.annotation.Order;
import com.mobsandgeeks.saripaar.annotation.Password;
import com.mobsandgeeks.saripaar.annotation.Pattern;

import app.formvalidation.validation.saripaar.ValidationUtilSaripaar;
import app.formvalidation.validation.listeners.OnValidationCallBack;

public class MainActivitySaripaar extends AppCompatActivity implements OnValidationCallBack {


    @NotEmpty (sequence  = 1)
    @Email( sequence  = 2,messageResId = R.string.valid_email)
    @Order(1)
    private EditText email;

//            (message = "Enter at least 6 characters.")
    @NotEmpty (sequence  = 1)
    @Password( sequence  = 2)
    @Length(min = 4, max = 10 , sequence  = 3)
    @Order(2)
    private EditText password;

    @NotEmpty (sequence  = 1)
    @ConfirmPassword(messageResId = R.string.valid_password ,sequence  = 2)
    @Length(min = 4, max = 10,sequence  = 3)
    @Order(3)
    private EditText confirmPassword;

    @NotEmpty (sequence  = 1)
    @Pattern( regex = "[789][0-9]{9}",messageResId = R.string.valid_phone ,sequence  = 2)
    @Length(min = 8, max = 15,sequence  = 3)
    @Order(4)
    private EditText phone;


    @NotEmpty (sequence  = 1)
    @Min(value = 2 , sequence  = 2)
    @Or
    @Max(value = 10 , sequence  = 3)
    @Order(5)
    private EditText alphnumeric;
    ValidationUtilSaripaar validationUtilSaripaar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);
        confirmPassword = (EditText) findViewById(R.id.conf_password);
        alphnumeric = (EditText) findViewById(R.id.alphnumeric);
        phone = (EditText) findViewById(R.id.phone);

        validationUtilSaripaar =new ValidationUtilSaripaar(this,this);

          email.setOnFocusChangeListener(validationUtilSaripaar);
        password.setOnFocusChangeListener(validationUtilSaripaar);
        confirmPassword.setOnFocusChangeListener(validationUtilSaripaar);
        alphnumeric.setOnFocusChangeListener(validationUtilSaripaar);
        phone.setOnFocusChangeListener(validationUtilSaripaar);

    }


    public void onClick(View view) {
       // validationUtilSaripaar.validateAll();
        validationUtilSaripaar.validateAllFirstError();
    }


    @Override
    public void onValidationSucceeded() {
        Toast.makeText(this, "Form Successfully Validated Activity", Toast.LENGTH_SHORT).show();
    }
}
