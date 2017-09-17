package mysql.hammergames.mysqlexample;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.util.Log;

/**
 * Created by OscarLeif on 9/17/2017.
 */

public class RegisterTask extends AsyncTask<Void, Integer, String>
{
    String TAG = getClass().getSimpleName();

    AlertDialog.Builder builder;

    Context context;

    String dialogMessage;

    public RegisterTask(Context context, String dialogMessage)
    {
        this.context = context;
        this.dialogMessage = dialogMessage;
    }

    protected void onPreExecute()
    {
        super.onPreExecute();
        Log.d(TAG + " PreExceute", "On pre Exceute......");
        builder = new AlertDialog.Builder(context);
    }

    protected String doInBackground(Void... arg0)
    {
        Log.d(TAG + " DoINBackGround", "On doInBackground...");

        for (int i = 0; i < 10; i++)
        {
            Integer in = new Integer(i);
            publishProgress(i);
        }
        return "You are at PostExecute";
    }

    protected void onProgressUpdate(Integer... a)
    {
        super.onProgressUpdate(a);
        Log.d(TAG + " onProgressUpdate", "You are in progress update ... " + a[0]);
    }

    protected void onPostExecute(String result)
    {
        super.onPostExecute(result);
        builder.setMessage(dialogMessage).setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener()
                {
                    public void onClick(DialogInterface dialog, int id)
                    {
                        //do things
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
        //AlertDialog alert = builder.create();
        //alert.show();
        Log.d(TAG + " onPostExecute", "" + result);
    }
}
