package MK_nurtaj.Notes_Handeler;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private Button createNewBtn, exitBtn;
    private ListView lvNotes;
    private ArrayList<Note> Note;
    private CustomEventAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        createNewBtn = findViewById(R.id.createNewBtn);
        exitBtn = findViewById(R.id.exitBtn);

        lvNotes = findViewById(R.id.lvNotes);

        createNewBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, NoteGenerate_Activity.class);
                startActivity(intent);
            }
        });

        exitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        loadData();

    }

    private void loadData() {
        Note = new ArrayList<>();
        KeyValueDB db = new KeyValueDB(this);
        Cursor rows = db.execute("SELECT * FROM key_value_pairs");
        if (rows.getCount() == 0) {
            return;
        }

        while (rows.moveToNext()) {
            String key = rows.getString(0);
            String noteData = rows.getString(1);
            String[] fieldValues = noteData.split("-::-");

            String topicName = fieldValues[0];
            String course_id = fieldValues[1];
            String dateTime = fieldValues[2];
            String description = fieldValues[3];

            Note e = new Note(key, topicName, course_id, dateTime, description);
            Note.add(e);
        }
        db.close();
        adapter = new CustomEventAdapter(this, Note);
        lvNotes.setAdapter(adapter);

        lvNotes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, final View view, int position, long id) {
                System.out.println(position);

                Intent i = new Intent(MainActivity.this, NoteGenerate_Activity.class);
                i.putExtra("NoteKey", Note.get(position).key);
                startActivity(i);
            }
        });
    }

    @Override
    public void onRestart() {
        super.onRestart();
        System.out.println("@MainActivity.onRestart");
        loadData();
    }
}