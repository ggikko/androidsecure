package ggikko.me.secureapplication;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MainActivity extends AppCompatActivity {

    private final String TAG = "ggikko";
    private final String FILE_NAME = "myfile.dat";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /** 1. using internal storage : MODE_PRIVATE file */

        File myFile = new File(getFilesDir(), "myfile.dat");

        /** 1 - 1 write text in internal storage */
        try {
            OutputStream outputStream = openFileOutput(FILE_NAME, Context.MODE_PRIVATE);
            outputStream.write("hello ggikko! nice to meet you.".getBytes());
            outputStream.close();

            String content = "";
            InputStream inputStream = openFileInput(FILE_NAME);
            byte[] input = new byte[inputStream.available()];
            while(inputStream.read(input) != -1){
                content += new String(input);
            }
            Log.e(TAG, "content : " + content);
        } catch (FileNotFoundException e) {
            Log.e(TAG, "file.not.found");
        } catch (IOException e) {
            Log.e(TAG, "io.exception");
        }

        /** cacheing data */
        File cachefile = new File(getCacheDir(), "myfile.dat");


        // Initialize MessageDigest to use SHA-256
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            Log.e(TAG, "algorithm.not.found");
        }

        // Convert the string to a hash
        byte[] sha256Hash = md.digest("Hello World".getBytes());
    }
}
