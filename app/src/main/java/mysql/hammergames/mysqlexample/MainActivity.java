package mysql.hammergames.mysqlexample;
//Java SQL imports

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

// Android librarires
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.util.Log;
import android.view.View;

import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

public class MainActivity extends BaseActivity
{
    private String hiduke = "";
    private int price = 0;
    private String errmsg = "";

    public static String TAG = "MySQLDriver";

    private TextInputLayout usernameWrapper;
    private TextInputLayout passwordWrapper;


    //Connections and thread variable
    private Connection connection;
    private Thread connectionThread;
    private ConnectionRunner conRunner;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //usernameWrapper = (TextInputLayout) findViewById(R.id.usernameWrapper);
        //passwordWrapper = (TextInputLayout) findViewById(R.id.passwordWrapper);

        //Thread thread = new Thread(this);
        //thread.start();
        ConnectDatabase();
    }

    public void ConnectDatabase()
    {
        ShowToastWithMessage("You touch the button");

        conRunner = new ConnectionRunner(this);
        Thread t1 = new Thread(conRunner, "T1");
        t1.start();
    }

    public void onClickLoggin(View view)
    {
        //Toast.makeText(this, "Click Button", Toast.LENGTH_SHORT);
        //ConnectDatabase();

        Intent intent = new Intent(this, LogginActivity.class);
        startActivity(intent);
    }

    public void onClickSignIn(View view)
    {
        ///SignUpActivity("","","","");
        if (conRunner.IsConnected())
        {
            Intent intent = new Intent(this, SignUpActivity.class);
            startActivity(intent);
        }
        else
        {
            ShowMessageDialog("Not Connected to the server");
        }

    }

    private Boolean CheckConnection(Connection conection)
    {
        boolean isConected = false;
        if (conection != null)
        {
            String sql;
            sql = "SELECT 1";
            try
            {
                final PreparedStatement statement = conection.prepareStatement(sql);
                isConected = true;
                Log.d(TAG, " DATA BASE CONNECTED");
                ShowToastWithMessage("Database is connected.");
            } catch (SQLException | NullPointerException e)
            {

            }
        } else
        {
            return isConected;
        }
        return isConected;
    }

    /*
        Signs up a user
        Requires Name, las name, email, password, retype password
     */
    private void SignUp(String name, String email, String password, String retypePassword)
    {
        if (conRunner != null)
        {
            conRunner.RegisterNewUser(usernameWrapper.getEditText().getText().toString(), "", "");
        } else
        {
            ShowToastWithMessage("Connect First");
        }
    }
}
