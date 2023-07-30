package com.example.mynotes;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import com.google.android.material.button.MaterialButton;
import io.realm.Realm;


public class AddNoteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        EditText TitleIn=findViewById(R.id.title);
        EditText DescriptionIn=findViewById(R.id.description);
        MaterialButton SaveBtn=findViewById(R.id.saveBtn);

        Realm.init(getApplicationContext());
        Realm realm = Realm.getDefaultInstance();

        SaveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title=TitleIn.getText().toString();
                String description=DescriptionIn.getText().toString();
                long createdTime=System.currentTimeMillis();

                realm.beginTransaction();
                Note note= realm.createObject(Note.class);
                note.setTitle(title);
                note.setDescription(description);
                note.setCreatedTime(createdTime);
                realm.commitTransaction();
                Toast.makeText(AddNoteActivity.this, "Note Saved", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

    }
}