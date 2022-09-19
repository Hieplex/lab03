package com.example.lab03;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.util.TypedValue;
import android.view.MenuInflater;
import android.view.Menu;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public boolean onCreateOptionsMenu(Menu menu){
        super.onCreateOptionsMenu(menu);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        super.onOptionsItemSelected(item);
                switch(item.getItemId())
                {
                    case R.id.clear:
                        AlertDialog.Builder message = new AlertDialog.Builder(this);
                        message.setTitle(R.string.message_caption);
                        message.setMessage(R.string.message_content);
                        message.setNeutralButton(R.string.close, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                EditText et = (EditText) findViewById(R.id.editText1);
                                et.setText("");
                            }
                        }).show();
                    break;

                    case  R.id.exit:
                      new  AlertDialog.Builder(this)
                        .setTitle(R.string.exit_caption)
                            .setMessage(R.string.exit_content)
                            .setNegativeButton(R.string.yes, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    finish();
                                }
                            })
                              .setPositiveButton(R.string.no, new DialogInterface.OnClickListener() {
                                  @Override
                                  public void onClick(DialogInterface dialogInterface, int i) {
                                      return ;

                                  }
                              }).show();
                    break;


                    case R.id.setting:
                        Intent intent = new Intent(this,OptionActivity.class);
                        final int result=1;
                        someActivityResultLauncher.launch(intent);
                        break;
                    case R.id.Size:
                        AlertDialog.Builder sizemessage = new AlertDialog.Builder(this);
                        sizemessage.setTitle(R.string.size_label);
                        sizemessage.setMessage(R.string.size_shortcut);
                        sizemessage.setNeutralButton(R.string.close, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                EditText et = (EditText) findViewById(R.id.editText1);
                                float textsize =et.getTextSize();
                                int size = (int) Math.round (textsize * 2.625);
                                Log.e("floatmy" , String.valueOf(size) ) ;
                                et.setTextSize(textsize);
                                et.setText(""+textsize);
                            }
                        }).show();
                        break;

                }
                return true;
    }
    ActivityResultLauncher<Intent> someActivityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {

                    if (result.getResultCode() == Activity.RESULT_OK) {
                        Intent data = result.getData();
                        Bundle bundle = data.getExtras();
                        int index1 = bundle.getInt("ForeColor");
                        Log.i("index1", String.valueOf(index1));
                        int index2 = bundle.getInt("BackColor");
                        Log.i("index2", String.valueOf(index2));
                        String colorArray[] = getResources().getStringArray(R.array.color_array);
                        Log.i("colorArray", String.valueOf(colorArray.length));
                       EditText et = (EditText)findViewById(R.id.editText1);
                        et.setTextColor(Color.parseColor(colorArray[index1]));
                        et.setBackgroundColor(Color.parseColor(colorArray[index2]));
                    }
                }
            });
}