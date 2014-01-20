package fuga.hoge.encipher;

import fuga.hoge.cipherutil.CipherUtil;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity
                    implements OnClickListener{

    //メンバ変数
    EditText editText;
    TextView cipherText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button encipherButton = (Button) findViewById(R.id.encipherButton);
        encipherButton.setOnClickListener(this);

        editText = (EditText) findViewById(R.id.editText);
        editText.setText("");

        cipherText = (TextView) findViewById(R.id.cipherText);
        cipherText.setText("Cipher text will be shown here.");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    public void onClick(View v) {
        Log.d("hoge", "fuga");
        String plainText = editText.getText().toString();

        editText.setText("");

        try {
            cipherText.setText(CipherUtil.encipher(plainText));
        } catch (Exception e) {
            // TODO 自動生成された catch ブロック
            e.printStackTrace();
        }
    }
}
