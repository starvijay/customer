package com.example.satmunia.myapp;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.view.View.OnClickListener;
import android.widget.Toast;

/**
 * Created by SatMunia on 01-05-2018.
 */

public class IntermediateView extends Activity {
    // Declare Variables
    RadioButton radEmail;
    RadioButton radMobile;
    String email;
    String mobile;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.intermediate);

        Intent i = getIntent();
        email = i.getStringExtra("Email");
        // Get the results of population
        mobile = i.getStringExtra("Mobile");
        radEmail = (RadioButton) findViewById(R.id.radioEmail);
        radMobile = (RadioButton) findViewById(R.id.radioMobile);

        radEmail.setText(email);
        radMobile.setText(mobile);
        porcessListenerOnButton(i);


    }

    public void porcessListenerOnButton(final Intent i) {

        final RadioGroup radioCommuGroup = (RadioGroup) findViewById(R.id.radioCommunication);
        Button btnProces = (Button) findViewById(R.id.butProcess);

        btnProces.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {

                // get selected radio button from radioGrou
                int selectedId = radioCommuGroup.getCheckedRadioButtonId();

                // find the radiobutton by returned id
                Button radioCButton = (RadioButton) findViewById(selectedId);

                Log.e("radio", String.valueOf(radioCButton.getText()));
                Toast.makeText(IntermediateView.this,
                        radioCButton.getText(), Toast.LENGTH_SHORT).show();

                if(R.id.radioEmail == radioCButton.getId()){

                    Intent intent=new Intent(Intent.ACTION_SEND);
                String[] recipients={(String) radioCButton.getText()};
                intent.putExtra(Intent.EXTRA_EMAIL, recipients);
                intent.putExtra(Intent.EXTRA_SUBJECT,"Subject text here...");
                intent.putExtra(Intent.EXTRA_TEXT,"Body of the content here...");
                intent.setType("text/html");
                intent.setPackage("com.google.android.gm");
                IntermediateView.this.startActivity(Intent.createChooser(intent, "Send mail"));

                } else  if(R.id.radioMobile == radioCButton.getId()){
                    Intent smsIntent = new Intent(Intent.ACTION_VIEW);
                smsIntent.setData(Uri.parse("smsto:"));
                smsIntent.setType("vnd.android-dir/mms-sms");
                smsIntent.putExtra("address"  , new String ((String) radioCButton.getText()));
                smsIntent.putExtra("sms_body"  , "Test sathish ");
                IntermediateView.this.startActivity(smsIntent);

                }else if(R.id.radioPage == radioCButton.getId()){
                Intent intent = new Intent(IntermediateView.this, SingleItemView.class);
                intent.putExtra("Name",(i.getStringExtra("Name")));
                intent.putExtra("Email",(i.getStringExtra("Email")));
                intent.putExtra("Mobile",(i.getStringExtra("Mobile")));
                IntermediateView.this.startActivity(intent);
                }
            }

        });
    }
}