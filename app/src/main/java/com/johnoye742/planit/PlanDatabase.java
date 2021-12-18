package com.johnoye742.planit;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class PlanDatabase extends SQLiteOpenHelper {

    public SQLiteDatabase sld;
   public static PlanDatabase pd;
    public PlanDatabase(Context c) {
        super(c, "planIt", null, 1);
        sld = getWritableDatabase();
    }
 public static PlanDatabase getInstance(Context c) {
  if(pd == null) {
    pd = new PlanDatabase(c);
  }
  return pd;
 }

    @Override
    public void onCreate(SQLiteDatabase db) {
      db.execSQL("CREATE TABLE plans ( id INTEGER PRIMARY KEY AUTOINCREMENT, task TEXT, description TEXT, dat TEXT )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    db.execSQL("DROP TABLE IF EXISTS plans");
    onCreate(db);
    }
    public long add(String plan, String description, String date) {
		SQLiteDatabase sld = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("task", plan);
        cv.put("description", description);
        cv.put("dat", date);
        return sld.insert("plans", null, cv);
    }

    public void edit(String oldPlan, String oldDescription, String newPlan, String newDescription) {
        ContentValues cv = new ContentValues();
        cv.put("task", newPlan);
        cv.put("description", newDescription);
        sld.update("plans", cv,
                "tasks LIKE ? AND description LIKE ? AND", new String[]{oldPlan, oldDescription});
    }
    public void delete(String plan, String description) {
        sld.delete("plans", "tasks LIKE ? AND description LIKE ? AND", new String[]{plan, description});
    }
}
