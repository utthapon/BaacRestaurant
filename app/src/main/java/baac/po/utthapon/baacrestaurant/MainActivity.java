package baac.po.utthapon.baacrestaurant;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    //explicit
    private UserTABLE objUserTABLE;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // create connect database
        createAndConnected();

        


    } // main method

    private void createAndConnected() {

        objUserTABLE = new UserTABLE(this);


    }
}   //main class
