package baac.po.utthapon.baacrestaurant;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by BAAC on 19/10/2015.
 */
public class UserTABLE {

    private MyOpenHelper objMyOpenHelper;

    private SQLiteDatabase writeSqLiteDatabase ,readSqLiteDatabase;




    public UserTABLE(Context context) {

        objMyOpenHelper = new MyOpenHelper(context);
        writeSqLiteDatabase = objMyOpenHelper.getWritableDatabase();
        readSqLiteDatabase = objMyOpenHelper.getWritableDatabase();



    }   //alt insert for crate  constructor
}//main class
