package trap1.khatoresha.korbapp;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    DrawView view;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button slow, fast;
        view = findViewById(R.id.drawView);
        slow = findViewById(R.id.leftButton);
        fast = findViewById(R.id.rightButton);
        MediaPlayer music = MediaPlayer.create(MainActivity.this,R.raw.sandcanyon);
        music.start();
//        slow.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View v){
//                System.out.println("left");
//                view.slower(2);
//            }
//        });
//        fast.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View v){
//                System.out.println("right");
//                view.faster(2);
//            }
//        });
    }

    public void slower(View view) {
        this.view.slower(2);
        System.out.println("left dX:" + this.view.getdX());
        System.out.println("left dY:" + this.view.getdY());
    }

    public void faster(View view) {
        this.view.faster(2);
        System.out.println("\nright dX:" + this.view.getdX());
        System.out.println("\nright dY:" + this.view.getdY());
    }


}
