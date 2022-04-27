package MK_nurtaj.Notes_Handeler;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class NoteGenerate_Activity extends AppCompatActivity {

    private EditText ntName, crsID, ntDateTime, ntDescription;

    private Button cancelBtn, saveBtn;
    private String existingKey = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_generate);

        ntName = findViewById(R.id.ntName);
        crsID = findViewById(R.id.crsID);
        ntDateTime = findViewById(R.id.ntDateTime);
        ntDescription = findViewById(R.id.ntDescription);

        cancelBtn = findViewById(R.id.cancelBtn);
        saveBtn = findViewById(R.id.saveBtn);

        Intent i = getIntent();
        existingKey = i.getStringExtra("NoteKey");
        if (existingKey != null && !existingKey.isEmpty()) {
            initializeFormWithExistingData(existingKey);
        }

        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveData();
            }
        });
    }

    private void initializeFormWithExistingData(String NoteKey) {

        String value = Util.getInstance().getValueByKey(this, NoteKey);
        System.out.println("Key: " + NoteKey + "; Value: " + value);

        if (value != null) {
            String[] fieldValues = value.split("-::-");
            String name = fieldValues[0];
            String place = fieldValues[1];
            String dateTime = fieldValues[2];
            String description = fieldValues[3];

            ntName.setText(name);
            crsID.setText(place);
            ntDateTime.setText(dateTime);
            ntDescription.setText(description);
        }
    }

    private void saveData() {
        String topic_name = ntName.getText().toString();
        String courseID = crsID.getText().toString();
        String datetime = ntDateTime.getText().toString();
        String description = ntDescription.getText().toString();

        System.out.println("NOTES Name" + topic_name);
        System.out.println("NOTES Place" + courseID);
        System.out.println("NOTES Datetime" + datetime);
        System.out.println("NOTES Description" + description);

        String key = topic_name + "-::-" + datetime;
        String value = topic_name + "-::-" + courseID + "-::-" + datetime + "-::-" + description;

        if (existingKey != null) {
            key = existingKey;
        }

        Util.getInstance().setKeyValue(this, key, value);
        System.out.println("Data Saved Success");

        Toast.makeText(this, "Note Saved Successfully", Toast.LENGTH_SHORT).show();
        finish();
    }
}