package mysql.hammergames.mysqlexample;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.sql.Connection;

public class SignUpActivity extends BaseActivity
{

    private TextInputLayout userNameWrapper ;
    private TextInputLayout userLastNameWrapper ;
    private TextInputLayout userEmailWrapper;
    private TextInputLayout userPasswordWrapper ;

    private Thread thread;
    private Connection conecction;
    private ConnectionRunner conectionRunner;



    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        userNameWrapper = (TextInputLayout) findViewById(R.id.usernameWrapper);
        userLastNameWrapper = (TextInputLayout) findViewById(R.id.userLastNameWrapper);
        userEmailWrapper = (TextInputLayout) findViewById(R.id.userEmailWrapper);
        userPasswordWrapper = (TextInputLayout) findViewById(R.id.userPasswordWrapper);

        //conectionRunner = new ConnectionRunner(this);
        conectionRunner = new ConnectionRunner(this);
        thread = new Thread(conectionRunner,"T2");
        thread.start();
    }

    private void SignUpNewUser()
    {


        String name,lastName,Email,password;
        name = userNameWrapper.getEditText().getText().toString();
        lastName = userLastNameWrapper.getEditText().getText().toString();
        Email = userEmailWrapper.getEditText().getText().toString();
        password = userPasswordWrapper.getEditText().getText().toString();

        conectionRunner.RegisterNewUser(name,"","");

    }

    public void onClickSignUp(View v)
    {
        SignUpNewUser();
        OnBackPressEmu();
    }

    //This should close the activity if all the UI elements are empty.
    //If not show a Warning Dialog if OK exit if not Stay Here

    @Override
    public void onBackPressed()
    {
        String name,lastName,Email,password;
        name = userNameWrapper.getEditText().getText().toString();
        lastName = userLastNameWrapper.getEditText().getText().toString();
        Email = userEmailWrapper.getEditText().getText().toString();
        password = userPasswordWrapper.getEditText().getText().toString();

        if(name.isEmpty() && lastName.isEmpty() && Email.isEmpty() && password.isEmpty() )
        {
            super.onBackPressed();
        }
        else
        {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);

            builder.setTitle("Confirm");
            builder.setMessage("Are you sure you want to quit?");

            builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {

                public void onClick(DialogInterface dialog, int which) {
                    // Do nothing but close the dialog

                    //dialog.dismiss();
                    SignUpActivity.this.OnBackPressEmu();
                }
            });

            builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {

                    // Do nothing
                    dialog.dismiss();
                }
            });

            AlertDialog alert = builder.create();
            alert.show();
        }
    }

    public void OnBackPressEmu()
    {
        super.onBackPressed();
    }
}
