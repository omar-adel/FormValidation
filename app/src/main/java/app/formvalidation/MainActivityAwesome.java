package app.formvalidation;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.model.IsEmptyRule;
import com.basgeekball.awesomevalidation.model.IsEqualRule;
import com.basgeekball.awesomevalidation.model.LengthRule;
import com.basgeekball.awesomevalidation.model.NumericRangeRule;
import com.basgeekball.awesomevalidation.model.PatternRule;
import com.basgeekball.awesomevalidation.model.Rule;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;
import com.google.common.collect.Range;

import java.util.ArrayList;

import app.formvalidation.validation.awesome.ValidationUtilAwesome;
import app.formvalidation.validation.listeners.OnValidationCallBack;

/**
 * Created by Net22 on 11/19/2017.
 */

public class MainActivityAwesome extends AppCompatActivity implements OnValidationCallBack {


    private EditText email;
    private EditText password;
    private EditText confirmPassword;
    private EditText phone;
    private EditText alphnumeric;
    ValidationUtilAwesome validationUtilAwesome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);
        confirmPassword = (EditText) findViewById(R.id.conf_password);
        alphnumeric = (EditText) findViewById(R.id.alphnumeric);
        phone = (EditText) findViewById(R.id.phone);
        validationUtilAwesome = new ValidationUtilAwesome(this, this);
        addValidationForEditText();

    }


    public void onClick(View view) {
       // validationUtilAwesome.validateAll();
       validationUtilAwesome.validateAllFirstError();
    }


    @Override
    public void onValidationSucceeded() {
        Toast.makeText(this, "Form Successfully Validated Activity", Toast.LENGTH_SHORT).show();
    }


    private void addValidationForEditText() {

        ArrayList<Rule> rules = new ArrayList<>();
        rules.add(new IsEmptyRule(getString(R.string.empty_email)).build());
        rules.add(new PatternRule(Patterns.EMAIL_ADDRESS, getString(R.string.valid_email)).build());
        validationUtilAwesome.getAwesomeValidation().addValidation(email, rules);


        rules = new ArrayList<>();
        rules.add(new IsEmptyRule(getString(R.string.empty_password)).build());
        rules.add(new LengthRule(4, 10, getString(R.string.valid_password_length)).build());
        validationUtilAwesome.getAwesomeValidation().addValidation(password, rules);

        rules = new ArrayList<>();
        rules.add(new IsEmptyRule(getString(R.string.empty_confirm_password)).build());
        rules.add(new IsEqualRule(getString(R.string.valid_confirm_password)).build());
        validationUtilAwesome.getAwesomeValidation().addValidation(confirmPassword, password, rules);

        rules = new ArrayList<>();
        rules.add(new IsEmptyRule(getString(R.string.empty_phone)).build());
        rules.add(new LengthRule(8, 15, getString(R.string.valid_phone_length)).build());
        rules.add(new PatternRule(RegexTemplate.TELEPHONE, getString(R.string.valid_phone)).build());
        validationUtilAwesome.getAwesomeValidation().addValidation(phone, rules);

        rules = new ArrayList<>();
        rules.add(new IsEmptyRule(getString(R.string.empty_number)).build());
        rules.add(new NumericRangeRule(Range.closed(2, 10), getString(R.string.valid_number_range)).build());
        validationUtilAwesome.getAwesomeValidation().addValidation(alphnumeric, rules);

        validationUtilAwesome.addOnFocusChangeListeners();


    }


}
