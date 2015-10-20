package baac.po.utthapon.baacrestaurant;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.os.StrictMode;
import android.preference.DialogPreference;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {

    //explicit
    private UserTABLE objUserTABLE;

    private  FoodTable objFoodTABLE;

    private EditText userEditText, passwordEditText;

    private  String userString, passwordString;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //bind widget
        bindWiidget();



        // create connect database
        createAndConnected();

        //testerAdd();

        //delete all sqlite
        deleteAllSQLITE();

        //synchronize SQLITE
        synJSONtoSQLite();






    } // main method

    private  void bindWiidget() {
        userEditText = (EditText) findViewById(R.id.editText);


        passwordEditText = (EditText) findViewById(R.id.editText2);
    }

    public void clickLogin(View view) {
        userString = userEditText.getText().toString().trim();
        passwordString = passwordEditText.getText().toString().trim();
        if (userString.equals("")  || passwordString.equals("")) {
            //have space
            errorDailog("have space","please fill all every blank");
        } else {
            //no space
            checkUser();

        }

    }

    private void checkUser() {
        try {

            String[] strMyresult = objUserTABLE.searchUser(userString);
            if (passwordString.equals(strMyresult[2])) {
                Toast.makeText(MainActivity.this,"welcom" +strMyresult[3],Toast.LENGTH_LONG).show();

            } else {
                errorDailog("password false ", "please try again Password False");
            }
        } catch (Exception e) {
            errorDailog("No This User","no"+ userString + "on my database");
        }

    }

    private void errorDailog(String strTitle, String strMessage) {
        AlertDialog.Builder objBuilder = new AlertDialog.Builder(this);
        objBuilder.setIcon(R.drawable.danger);
        objBuilder.setTitle(strTitle);
        objBuilder.setMessage(strMessage);
        objBuilder.setCancelable(false);
        objBuilder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }

        });
        objBuilder.show();

    }

    private void synJSONtoSQLite() {
        //0  change policy
        StrictMode.ThreadPolicy myPolicy = new StrictMode.ThreadPolicy.Builder().permitAll().build();// policy 777
        StrictMode.setThreadPolicy(myPolicy);
        HttpPost objHttpPost ;

        int intTime = 0;
        while (intTime<=1) {
            InputStream objInputStream = null;
            String strJSON = null;

            String strUserURL = "http://swiftcodingthai.com/baac/php_get_data_master.php";
            String strFoodURL= "http://swiftcodingthai.com/baac/php_get_food.php";

            //1 create inputStream
            try {
                HttpClient objHttpClient = new DefaultHttpClient();
                switch (intTime) {
                    case 0:
                        objHttpPost = new HttpPost(strUserURL);
                        break;
                    case 1:
                        objHttpPost = new HttpPost(strFoodURL);
                        break;
                    default:
                        objHttpPost = new HttpPost(strUserURL);
                }//switch

                HttpResponse objHttpResponse = objHttpClient.execute(objHttpPost);
                HttpEntity objHttpEntity = objHttpResponse.getEntity();
                objInputStream = objHttpEntity.getContent();



            } catch (Exception e) {
                Log.d("baac", "Inputstream ==>" + e.toString());
            }

            //2 create json string
            try {
                BufferedReader objBufferedReader = new BufferedReader(new InputStreamReader(objInputStream, "UTF-8"));
                StringBuilder objStringBuilder = new StringBuilder();
                String strLine = null;
                while ((strLine = objBufferedReader.readLine()) != null   ) {
                    objStringBuilder.append(strLine);

                }//while
                objInputStream.close();
                strJSON = objStringBuilder.toString();


            } catch (Exception e) {
                Log.d("baac", "strJSON ==>" + e.toString());
            }


            //3 updateSQL
            try {
                JSONArray objJsonArray = new JSONArray(strJSON);

                for (int i = 0; i < objJsonArray.length(); i++) {
                    JSONObject object = objJsonArray.getJSONObject(i);
                    switch (intTime) {
                        case 0:
                            //for usertable
                            String strUser = object.getString("User");
                            String strPassword = object.getString("Password");
                            String strName = object.getString("Name");
                            objUserTABLE.addNewUser(strUser, strPassword, strName);

                            break;
                        case 1:
                            //for foodtable
                            String strFood = object.getString("Food");
                            String strSource = object.getString("Source");
                            String strPrice = object.getString("Price");
                            objFoodTABLE.addNewFood(strFood, strSource, strPrice);
                            break;
                    }//switch
                }

            } catch (Exception e) {
                Log.d("baac", "Update ==> " + e.toString());
            }

            intTime += 1;
        }
    }//  synchronize SQLITE


    private void deleteAllSQLITE() {

        SQLiteDatabase objSQLiteDatabase = openOrCreateDatabase("BAAC.db",MODE_PRIVATE,null);//create database
        objSQLiteDatabase.delete("userTABLE", null, null);
        objSQLiteDatabase.delete("foodTABLE", null, null);

    }


    private void testerAdd() {
        objUserTABLE.addNewUser("testuser", "password", "ทดสอบชื่อไทย");
        objFoodTABLE.addNewFood("ชื่ออาหาร", "testsource", "123");

    }

    private void createAndConnected() {

        objUserTABLE = new UserTABLE(this);
        objFoodTABLE = new FoodTable(this);

    }
}   //main class
