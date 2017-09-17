package mysql.hammergames.mysqlexample;

import android.app.Activity;
import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.concurrent.ExecutionException;
import java.util.regex.Pattern;

/**
 * Created by OscarLeif on 9/14/2017.
 */

public class ConnectionRunner implements Runnable
{
    private Connection mySqlConnection;
    private BaseActivity mainAct;
    private volatile boolean exit = false;

    public ConnectionRunner(BaseActivity activity)
    {
        mainAct = activity;
    }

    @Override
    public void run()
    {
        //First make a connection to the Server
        boolean conecctionAlive = false;

        ConnectDataBase();

        while(!exit)
        {
            //Server is Running...

        }

        mainAct.ShowMessageDialog("The Connection was lost");

        //Server is stopped...

    }

    private void ConnectDataBase()
    {
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            mySqlConnection = DriverManager.getConnection("jdbc:mysql://186.0.93.47:3306/upet", "marco", "marco");
            mainAct.ShowToastWithMessage("Connected to the Database");
        }
        catch(Exception e)
        {
            e.printStackTrace();
            String errmsg = e.getMessage();

            //Lets find checkout the reason of the fail
            //Check if Connections Fails
            //The DB server is not working
            String s = errmsg;

            String regexe = "ETIMEDOUT";
            String errorConecctionRefuse = "ECONNREFUSED";

            if (Pattern.compile("ECONNREFUSED").matcher(errmsg).find()) {
                //MainActivity.this.showToastMessage("Connection refused. Check IP and ports server");
                mainAct.ShowToastWithMessage("Connection refused. Check IP and ports server\"");
            } else if (Pattern.compile("ETIMEDOUT").matcher(errmsg).find())
            {
                //MainActivity.this.showToastMessage("Time Out Connection. Is DB Server online ?");
                mainAct.ShowToastWithMessage(("Time Out Connection. Is DB Server online ?"));
            } else {
                mainAct.ShowToastWithMessage("Something Fails");
            }
        }
    }

    public void RegisterNewUser(String username, String email, String password)
    {
        mainAct.ShowToastWithMessage("Registering new user");
        if(CheckConnection( mySqlConnection))
        {
            String SQLquery = "INSERT INTO user (username, email, password) VALUES (?, ?, ?)";
            try
            {
                final PreparedStatement statemen = mySqlConnection.prepareStatement(SQLquery);
                statemen.setString(1,username);
                statemen.setString(2,username+"@upet.com");
                statemen.setString(3,username);

                statemen.execute();

                mainAct.ShowToastWithMessage("User Registered" + username +" check DB");
                mySqlConnection.close();


            }
            catch(SQLException | NullPointerException e)
            {
                e.printStackTrace();
                String errmsg = e.getMessage();
            }
        }
        else
        {

            exit = true;
        }
    }

    private Boolean CheckConnection(Connection conection)
    {
        boolean isConected = false;
        if(conection!=null)
        {
            String sql;
            sql = "SELECT 1";
            try
            {
                final PreparedStatement statement = conection.prepareStatement(sql);
                isConected = true;
                //Log.d(TAG," DATA BASE CONNECTED");
                //ShowToastWithMessage("Database is connected.");
            }
            catch (SQLException | NullPointerException e)
            {
                mainAct.ShowToastWithMessage("Connection Failed");
            }
        }
        else
        {
            mainAct.ShowToastWithMessage("connection should be created");
            return isConected;
        }
        return isConected;
    }

    public Boolean IsConnected()
    {
        boolean isConnected = false;
        if(mySqlConnection!=null)
        {
            String sql;
            sql = "SELECT 1";
            try
            {
                final PreparedStatement statement = mySqlConnection.prepareStatement(sql);
                isConnected= true;
                //Log.d(TAG," DATA BASE CONNECTED");
                //ShowToastWithMessage("Database is connected.");
            }
            catch (SQLException | NullPointerException e)
            {
                mainAct.ShowToastWithMessage("Connection Failed");
            }
        }
        return isConnected;
    }

    public void stop()
    {
        exit = true;
    }
}
