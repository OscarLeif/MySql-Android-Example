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

/**
 * Main Activity Class
 * Check activity_main.xml is the layout of this activity
 */
public class MainActivity extends BaseActivity
{
    private String hiduke = "";
    private int price = 0;
    private String errmsg = "";

    public static String TAG = "MySQLDriver";

    //private TextInputLayout usernameWrapper;
    //private TextInputLayout passwordWrapper;

    //Connections and thread variable
    private Connection connection;
    private Thread connectionThread;
    private ConnectionRunner conRunner;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ConnectDatabase();
    }

    /**
     * Connect to our Database, this will automatically start
     * a New Thread and the Runnable methods are inside the Class
     * Connection Runner. This handle the connections and SQL commands
     */

    public void ConnectDatabase()
    {
        conRunner = new ConnectionRunner(this);
        Thread t1 = new Thread(conRunner, "T1");
        t1.start();
    }

    /**
     * Open a new Activity for Loggin an existing user
     * This will clean upo our actual thread
     * @param view
     */

    public void onClickLoggin(View view)
    {
        //Toast.makeText(this, "Click Button", Toast.LENGTH_SHORT);
        //ConnectDatabase();

        Intent intent = new Intent(this, LogginActivity.class);
        startActivity(intent);
    }

    /**
     * Button Event Click
     * @param view
     */
    public void onClickSignIn(View view)
    {
        //if (conRunner.IsConnected())
        {
            Intent intent = new Intent(this, SignUpActivity.class);
            startActivity(intent);
        }
        //else
        {
            //RegisterTask a = new RegisterTask(this, "No connection DB");
            //a.execute();
        }
    }

    public void onClickFacebookLogin(View view)
    {
        ShowToastWithMessage("TODO implement facebook login /ᐠ｡ꞈ｡ᐟ\\");
    }

    /**
     * Just send a simple query to check is DB is online
     * @param conection
     * @return
     */
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
}
