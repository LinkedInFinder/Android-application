package uk.ac.lancs.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

/**
 * This class extends SQLiteOpenHelper.
 * The main function of this class is used to connect the required database.
 * @author Miao Cai
 * @since 7/5/2019
 */
public class MySQLiteOpenHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String TABLE_NAME = "test";
    private ArrayList<String> array;

    /**
     * The construction of the class.
     * @param context current context.
     * @param name the name of database.
     */
    public MySQLiteOpenHelper(Context context, String name){
        super(context, name, null, DATABASE_VERSION);
    }

    /**
     * This method is used for query operation.
     * @param db the required database.
     * @param name the name of table.
     * @param rawQuerySql the query sentence.
     * @return the list which contains a string of results.
     */
    public ArrayList<String> queryInfo(SQLiteDatabase db, String name, String rawQuerySql){
        array = new ArrayList<>();
        Cursor cursor = null;
        cursor = db.rawQuery(rawQuerySql,new String[]{name});
        while (cursor.moveToNext()){
            for (int i= 0;i< cursor.getColumnCount();i++) {
                array.add(cursor.getString(i));
            }
        }
        cursor.close();
        return array;
    }

    /**
     * Super method.
     * @param db the required database.
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        // Do nothing.
    }

    /**
     * Super method.
     * When the database is upgraded, we need to drop the old one.
     * @param db the required database
     * @param oldVersion the previous one.
     * @param newVersion the current one.
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if(newVersion > oldVersion){
            String sql = "DROP TABLE IF EXISTS " + TABLE_NAME;
            db.execSQL(sql);
            onCreate(db);
        }
    }
}
