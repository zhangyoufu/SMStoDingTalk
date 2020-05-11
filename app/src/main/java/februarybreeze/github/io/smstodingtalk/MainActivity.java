package februarybreeze.github.io.smstodingtalk;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private Preferences preference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        preference = new Preferences(this);
    }

    public void setDingTalkToken(View view) {
        EditText tokenText = (EditText) findViewById(R.id.tokenText);
        String token = tokenText.getText().toString();
        preference.setDingTalkToken(token);
    }
}
