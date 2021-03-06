package baac.po.utthapon.baacrestaurant;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by BAAC on 19/10/2015.
 */
public class UserTABLE {

    private MyOpenHelper objMyOpenHelper;
    private SQLiteDatabase writeSqLiteDatabase ,readSqLiteDatabase;

    public static final String User_TABLE = "userTable";
    public static final String COLUMM_ID_USER = "_id";
    public static final String COLUMN_USER = "User";
    public static final String COLUMN_PASSWORD = "password";
    public static final String COLUMN_NAME = "Name";





    public UserTABLE(Context context) {

        objMyOpenHelper = new MyOpenHelper(context);
        writeSqLiteDatabase = objMyOpenHelper.getWritableDatabase();
        readSqLiteDatabase = objMyOpenHelper.getWritableDatabase();

    }   //alt insert for crate  constructor

    public  String[] searchUser(String strUser) {

        try {
            String[] strresult = null;
            Cursor objCursor = readSqLiteDatabase.query(User_TABLE,new String[]{COLUMM_ID_USER,COLUMN_USER,COLUMN_PASSWORD,COLUMN_NAME},
                    COLUMN_USER+"=?",
                    new String[]{String.valueOf(strUser)},null,null,null,null);
            if (objCursor != null) {
                if (objCursor.moveToFirst()) {

                    strresult = new String[objCursor.getColumnCount()];
                    strresult[0] = objCursor.getString(0);
                    strresult[1] = objCursor.getString(1);
                    strresult[2] = objCursor.getString(2);
                    strresult[3] = objCursor.getString(3);

                }//if
            }//if
            objCursor.close();
            return strresult;



        } catch (Exception e) {
            return null;
        }
        //return new String[0];
    }

    public long addNewUser(String strUser, String strPassword, String strName) {

        ContentValues objContentValues = new ContentValues();
        objContentValues.put(COLUMN_USER,strUser);
        objContentValues.put(COLUMN_PASSWORD,strPassword);
        objContentValues.put(COLUMN_NAME,strName);
        return writeSqLiteDatabase.insert(User_TABLE,null,objContentValues);
    }


}//main class
