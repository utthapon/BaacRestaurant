package baac.po.utthapon.baacrestaurant;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import java.util.logging.Handler;
import java.util.logging.LogRecord;

public class SplassScreen extends AppCompatActivity {

    //explicit
    private ImageView monkeyImageView;
    private AnimationDrawable objAnimationDrawable;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splass_screen);

    //show animate

        monkeyImageView = (ImageView) findViewById(R.id.imvmonkey);
        monkeyImageView.setBackgroundResource(R.anim.monkey);

        objAnimationDrawable = (AnimationDrawable) monkeyImageView.getBackground();
        objAnimationDrawable.start();



    //auto trade
        android.os.Handler objHandler = new android.os.Handler();
        objHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplassScreen.this,MainActivity.class));
            }
        }, 8000);

        //sound effect
        MediaPlayer introPlayer = MediaPlayer.create(getBaseContext(),R.raw.intro_start_horse);
        introPlayer.start();








        

    }//on create
}//main class
