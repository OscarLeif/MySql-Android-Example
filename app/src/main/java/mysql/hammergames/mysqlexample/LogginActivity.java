package mysql.hammergames.mysqlexample;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.ShareCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class LogginActivity extends BaseActivity
{

    private TextInputLayout emailTextInput;
    private TextInputLayout passwordTextInput;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loggin);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


        emailTextInput = (TextInputLayout) findViewById(R.id.userEmailWrapper);
        passwordTextInput = (TextInputLayout) findViewById(R.id.userPasswordWrapper);

        if(emailTextInput ==null  )
        {
            ShowMessageDialog("Warning the Text inputs wasn't find" );
        }
    }

    /**
     * Open The Activity Services
     * In order to do this the App must first
     * Logging the User. Here check if the User and Password are correct
     * @param view
     */
    public void OnClickLogin(View view)
    {
        if(emailTextInput.getEditText().getText().toString().isEmpty() && passwordTextInput.getEditText().getText().toString().isEmpty())
        {
            ShowToastWithMessage("Input values user email and passwords are empty !");
        }
        else
        {
            Intent intent = new Intent(this,ActivityServices.class);
            startActivity(intent);
        }
    }

    /**
     * In a future when this app have a nice backend server
     * it will show a dialog or windows telling the email to the server
     * Then you will recieve a temp email.
     * @param view
     */
    public void OnClickForgotPassword(View view)
    {
        ShowToastWithMessage("In Development =^._.^= ∫");
    }


    @Override
    public void onBackPressed()
    {

        super.onBackPressed();
    }
}
