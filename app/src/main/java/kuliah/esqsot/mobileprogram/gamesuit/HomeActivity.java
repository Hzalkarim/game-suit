package kuliah.esqsot.mobileprogram.gamesuit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }

    public void onClickStart(View v){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void onClickRule(View v){
        Intent intent = new Intent(this, RuleActivity.class);
        startActivity(intent);
    }
}