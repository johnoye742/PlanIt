package com.johnoye742.planit;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

import java.util.ArrayList;
import java.util.Random;

public class PlanListAdapter extends BaseAdapter {
   private final ArrayList<PlanDataModel> al;
   
   PlanDatabase dbm;
   Context c;
   int[] colors = new int[] {Color.BLACK, Color.rgb(10, 200, 1), Color.BLUE, Color.YELLOW};

    public PlanListAdapter(Context c, ArrayList<PlanDataModel> al) {
       this.al = al;
    this.c = c;


    }
    @Override
    public int getCount() {
        return this.al.size();
    }

    @Override
    public Object getItem(int position) {

        return this.al.get(position);
    }

    @Override
    public long getItemId(int position) {

        return position;
    }
public PlanDataModel c(int i) {

        return (PlanDataModel) getItem(i);
}
    @SuppressLint({"NonConstantResourceId", "ViewHolder"})
    @Override
    public View getView(int position, View v, ViewGroup parent) {
        if(v == null) {
            LayoutInflater li = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = li.inflate(R.layout.a1, parent, false);
            TextView tsk = v.findViewById(R.id.task);
            tsk.setText(al.get(position).plan);
            tsk.setTextColor(colors[new Random().nextInt(colors.length)]);
            ImageView imv = v.findViewById(R.id.imv2);
            dbm = new PlanDatabase(c);
            PopupMenu.OnMenuItemClickListener mi = (item) -> {
                switch (item.getItemId()) {
                    case R.id.delete:
                        AlertDialog.Builder ab2 = new AlertDialog.Builder(c);
                        ab2.setTitle("Delete?");
                        ab2.setMessage("Are you really sure you want to delete task : " +al.get(position).plan);
                        ab2.setPositiveButton("Delete", (a, b) -> dbm.delete(al.get(position).plan, al.get(position).description));
                        ab2.setNegativeButton("Cancel", (a, b) -> {
                         ab2.create().show();
                        });

                     break;
                    case R.id.details:
                        AlertDialog.Builder ab = new AlertDialog.Builder(c);
                        ab.setTitle("Task Details");
                        String s1 = "Task Name : " + al.get(position).plan + "\n\nTask Description : " + al.get(position).description + "\n\nTime of task : " + al.get(position).time;
                        ab.setMessage(s1);
                        ab.setPositiveButton("OK", (a, b) -> {

                        });
                        ab.create().show();
                     break;
                    case R.id.edit:
                        Intent intent = new Intent(c, EditTask.class);
                        intent.setAction("com.johnoye742.planit.edit");
                        intent.putExtra("title", al.get(position).plan);
                        intent.putExtra("descr", al.get(position).description);
                        intent.putExtra("time", al.get(position).time);
                        c.startActivity(intent);
                        break;
                }
                return false;
            };
            imv.setOnClickListener((v1) -> {
                PopupMenu pm = new PopupMenu(c, imv);
                pm.inflate(R.menu.menu_list);
                pm.setOnMenuItemClickListener(mi);
                pm.show();
            });
            View finalV = v;
            v.setOnLongClickListener((v2) -> {
                PopupMenu pm = new PopupMenu(c, finalV);
                pm.inflate(R.menu.menu_list);
                pm.setOnMenuItemClickListener(mi);
                pm.show();
                return false;
            });
        }
        return v;
}

}

