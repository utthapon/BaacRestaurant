package baac.po.utthapon.baacrestaurant;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    //explicit
    private UserTABLE objUserTABLE;

    private  FoodTable objFoodTABLE;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // create connect database
        createAndConnected();

        testerAdd();






    } // main method



    private void testerAdd() {
        objUserTABLE.addNewUser("testuser", "password", "ทดสอบชื่อไทย");
        objFoodTABLE.addNewFood("ชื่ออาหาร", "testsource", "123");

    }

    private void createAndConnected() {

        objUserTABLE = new UserTABLE(this);
        objFoodTABLE = new FoodTable(this);

    }
}   //main class
