package baac.po.utthapon.baacrestaurant;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by BAAC on 19/10/2015.
 */
public class MyOpenHelper extends SQLiteOpenHelper{
    // explicit ประกาศตัวแปร

    private static final String DATABASE_NAME = "BAAC.db";
    private static final int DATABASE_VERSION = 1;
    private static final String CREATE_USER_TABLE = "create table userTable(_id integer primary key,User text ,Password text,name text );";//sqllite ต้องมี _id เท่านั้น และบอก data type
    private static final String CREATE_FOOD_TABLE = "create table foodTable(_id integer primary key,Food text,Source text,Price text);";


    public MyOpenHelper(Context context) {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);

    }// สร้าง constructor ค่าเริ่มต้นเร่มทำงาน

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_USER_TABLE);
        sqLiteDatabase.execSQL(CREATE_FOOD_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}   // Main Class คลาส หลัก
