package mysql.hammergames.mysqlexample;
//Java SQL imports

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// Android librarires
import android.app.ActivityManager;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.os.PatternMatcher;
import android.support.design.widget.TextInputLayout;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import static android.R.id.input;

public class MainActivity extends AppCompatActivity implements Runnable {
    private String hiduke = "";
    private int price = 0;
    private String errmsg = "";

    public static String TAG = "MySQLDriver";

    private TextInputLayout usernameWrapper;
    private TextInputLayout passwordWrapper;


    private Thread connectionThread;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        usernameWrapper = (TextInputLayout) findViewById(R.id.usernameWrapper);
        passwordWrapper = (TextInputLayout) findViewById(R.id.passwordWrapper);

        //Thread thread = new Thread(this);
        //thread.start();
    }

    public void ConnectDatabase() {
        showToastMessage("You touch the button");
        if (connectionThread.isAlive()) {
            return;
        }
        connectionThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    //Your code goes here
                    Connection con = null;
                    int count = 0;
                    try {
                        Class.forName("com.mysql.jdbc.Driver");
                        con = DriverManager.getConnection("jdbc:mysql://186.0.93.47:3306/upet", "marco", "marco");
                        MainActivity.this.showToastMessage("connection successful");
                    } catch (Exception e) {
                        e.printStackTrace();
                        errmsg = errmsg + e.getMessage();

                        //Lets find checkout the reason of the fail
                        //Check if Connections Fails
                        //The DB server is not working
                        String s = errmsg;

                        String regexe = "ETIMEDOUT";
                        String errorConecctionRefuse = "ECONNREFUSED";

                        if (Pattern.compile("ECONNREFUSED").matcher(errmsg).find()) {
                            //MainActivity.this.showToastMessage("Connection refused. Check IP and ports server");
                            ShowErrorDialog("Connection refused. Check IP and ports server\"");
                        } else if (Pattern.compile("ETIMEDOUT").matcher(errmsg).find()) {
                            //MainActivity.this.showToastMessage("Time Out Connection. Is DB Server online ?");
                            ShowErrorDialog("Time Out Connection. Is DB Server online ?");
                        } else {
                            MainActivity.this.showToastMessage("Something Fails");
                        }
                    }

                } catch (Exception e) {
                    Log.e(TAG, e.getMessage());
                }
            }
        });
        connectionThread.start();



    }

    public void showToastMessage(final String message) {
        this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
            }
        });
    }


    public void onClickLoggin(View view) {
        Toast.makeText(this, "Click Button", Toast.LENGTH_SHORT);
        ConnectDatabase();
    }

    public void ShowErrorDialog(final String errorMessage) {
        this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setMessage(errorMessage)
                        .setCancelable(false)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                //do things
                            }
                        });
                AlertDialog alert = builder.create();
                alert.show();
            }
        });
    }


    public void run() {
        System.out.println("Select Records Example by using the Prepared Statement!");
        Connection con = null;
        int count = 0;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://186.0.93.47:3306/upet", "marco", "marco");
            //("jdbc:mysql://10.0.2.2:3306/stock","root","root");

            /*try{
                String sql;
                //	  sql
                //	  = "SELECT title,year_made FROM movies WHERE year_made >= ? AND year_made <= ?";
                sql
                        = "SELECT hiduke,jikan,code,price FROM table_stock";
                PreparedStatement prest = con.prepareStatement(sql);
                //prest.setInt(1,1980);
                //prest.setInt(2,2004);
                ResultSet rs = prest.executeQuery();
                while (rs.next()){
                    hiduke = rs.getString(1);
                    price = rs.getInt(4);
                    count++;
                    System.out.println(hiduke + "\t" + "- " + price);
                }
                System.out.println("Number of records: " + count);
                prest.close();
                con.close();
            }
            catch (SQLException s){
                System.out.println("SQL statement is not executed!");
                errmsg=errmsg+s.getMessage();

            }*/
            Log.d("TAG MYSQL", "The Conecction should we work");
            showToastMessage("Connection Working");
        } catch (Exception e) {
            e.printStackTrace();
            errmsg = errmsg + e.getMessage();
        }

        handler.sendEmptyMessage(0);

    }

    //TODO not mandatory to use. Clean
    private Handler handler = new Handler()
    {
        public void handleMessage(Message msg)
        {
            //TextView textView = (TextView) findViewById(R.id.textView0);
            //textView.setText("hiduke="+hiduke+" price="+price+" "+errmsg);
        }
    };

    /*
        Signs up a user
        Requires Name, las name, email, password, retype password
     */
    private void SignUp(String name,String email, String password, String retypePassword)
    {
        //First make a connection to the data base
       //Or check that the thread conecction is alive who works better !!!
        
    }
}
