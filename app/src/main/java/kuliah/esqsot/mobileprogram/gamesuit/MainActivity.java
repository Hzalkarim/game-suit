package kuliah.esqsot.mobileprogram.gamesuit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.nio.InvalidMarkException;

public class MainActivity extends AppCompatActivity {

    public Boolean isPlaying;
    public int skor = 0;

    public int timeLimit = 45;
    public int roundTime = 3;

    public String pilPlayer;
    public String pilLawan;
    public String instruksi;

    public GameSuitController gameSuitController = new GameSuitController();
    public Handler handler = new Handler(Looper.getMainLooper());
    final public Runnable r = new Runnable() {
        @Override
        public void run() {
            ImageView player = (ImageView) findViewById(R.id.img_pilihanPemain);
            player.setImageResource(R.drawable.rounder);
            if (gameSuitController.testDuel(pilPlayer, pilLawan, instruksi)){
                addScore();
            }
            generatePilLawan();
            generateInstruction();
            setPilihanEnabled(true);
            roundTimer.start();
        }
    };

    public CountDownTimer roundTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        isPlaying = true;

        ImageView pilPlayer = (ImageView) findViewById(R.id.img_pilihanPemain);

        final TextView waktuText = (TextView) findViewById(R.id.text_waktu);

        generatePilLawan();
        generateInstruction();
        pilPlayer.setImageResource(R.drawable.rounder);

        new CountDownTimer(timeLimit * 1000, 1000) {

            public void onTick(long millisUntilFinished) {
                waktuText.setText("" + millisUntilFinished / 1000);
            }

            public void onFinish() {
                waktuText.setText("TO!");
                isPlaying = false;
            }
        }.start();

        roundTimer = new CountDownTimer(roundTime * 1000, 100) {
            @Override
            public void onTick(long millisUntilFinished) {
                ProgressBar waktu = (ProgressBar) findViewById(R.id.progress_roundTimer);
                waktu.setProgress((int) (100 - (millisUntilFinished  / (roundTime * 10))));
                if (!isPlaying) cancel();
            }

            @Override
            public void onFinish() {
                ProgressBar waktu = (ProgressBar) findViewById(R.id.progress_roundTimer);
                waktu.setProgress(100);

                generatePilLawan();
                generateInstruction();

                if (!isPlaying) {
                    cancel();
                    return;
                }

                start();
            }
        };


        roundTimer.start();
    }

    public void onClickGunting(View v){
        if (!isPlaying) return;
        pilPlayer = "Gunting";
        ImageView pilPlayer = (ImageView) findViewById(R.id.img_pilihanPemain);
        pilPlayer.setImageResource(R.drawable.ic_gunting);
        setPilihanEnabled(false);
        roundTimer.cancel();
        handler.postDelayed(r, 500);
    }

    public void onClickBatu(View v){
        if (!isPlaying) return;
        pilPlayer = "Batu";
        ImageView pilPlayer = (ImageView) findViewById(R.id.img_pilihanPemain);
        pilPlayer.setImageResource(R.drawable.ic_batu);
        setPilihanEnabled(false);
        roundTimer.cancel();
        handler.postDelayed(r, 500);
    }

    public void onClickKertas(View v){
        if (!isPlaying) return;
        pilPlayer = "Kertas";
        ImageView pilPlayer = (ImageView) findViewById(R.id.img_pilihanPemain);
        pilPlayer.setImageResource(R.drawable.ic_kertas);
        setPilihanEnabled(false);
        roundTimer.cancel();
        handler.postDelayed(r, 500);
    }

    public void onClickRestart(View v){
        Intent intent = getIntent();
        finish();
        startActivity(intent);
    }

    public void onClickToHome(View v){
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }

    public void onClickExit(View v){
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        startActivity(intent);
    }

    public void addScore(){
        TextView skor = (TextView) findViewById(R.id.text_skor);
        String skorBaru = (++this.skor) + "";
        skor.setText(skorBaru);
    }

    public void generatePilLawan(){
        ImageView lawanImg = (ImageView) findViewById(R.id.img_pilihanLawan);
        pilLawan = gameSuitController.generateLawan();
        switch (pilLawan){
            case "Gunting":
                lawanImg.setImageResource(R.drawable.ic_gunting);
                break;
            case "Batu":
                lawanImg.setImageResource(R.drawable.ic_batu);
                break;
            case "Kertas":
                lawanImg.setImageResource(R.drawable.ic_kertas);
                break;
        }
    }

    public void generateInstruction(){
        instruksi = gameSuitController.generateInstruction();
        TextView instruksiText = (TextView) findViewById(R.id.text_instruksi);
        instruksiText.setText(instruksi);
    }

    public void setPilihanEnabled(Boolean enabled){
        Button guntingBtn = (Button) findViewById(R.id.btn_gunting);
        Button batuBtn = (Button) findViewById(R.id.btn_batu);
        Button kertasBtn = (Button) findViewById(R.id.btn_kertas);

        guntingBtn.setEnabled(enabled);
        batuBtn.setEnabled(enabled);
        kertasBtn.setEnabled(enabled);
    }

}