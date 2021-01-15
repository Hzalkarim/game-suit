package kuliah.esqsot.mobileprogram.gamesuit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.ImageView;

public class RuleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rule);

        ImageView t = (ImageView)findViewById(R.id.img_tutor);
        t.setImageResource(R.drawable.img_tutorial2);
    }

    public void onClickBack(View v){
        ImageView t = (ImageView)findViewById(R.id.img_tutor);
        t.setImageResource(R.drawable.rounder);
        finish();
    }
}