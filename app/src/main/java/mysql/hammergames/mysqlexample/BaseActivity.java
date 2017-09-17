package mysql.hammergames.mysqlexample;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

/**
 * Created by OscarLeif on 9/14/2017.
 */

public class BaseActivity extends AppCompatActivity
{
    @Override
    public void onConfigurationChanged(Configuration newConfig)
    {
        super.onConfigurationChanged(newConfig);

    }

    // For some reason this method stop working
    // Instead of using this RegisterTask now have this
    public void ShowMessageDialog(final String errorMessage)
    {
        this.runOnUiThread(new Runnable()
        {
            @Override
            public void run()
            {
                //Note AlertDialog Needs Activity Context not Application Context.
                AlertDialog.Builder builder = new AlertDialog.Builder(getApplicationContext());
                builder.setMessage(errorMessage).setCancelable(false)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener()
                        {
                            public void onClick(DialogInterface dialog, int id)
                            {
                                //do things
                            }
                        });
                AlertDialog alert = builder.create();
                alert.show();
            }
        });
    }

    public void ShowToastWithMessage(final String message)
    {
        this.runOnUiThread(new Runnable()
        {
            @Override
            public void run()
            {
                Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void ShowToastWithMessage(final String message, int LengtTime)
    {
        this.runOnUiThread(new Runnable()
        {
            @Override
            public void run()
            {
                Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
            }
        });
    }
}
