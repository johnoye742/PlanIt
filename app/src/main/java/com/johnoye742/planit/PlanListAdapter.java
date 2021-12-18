package com.johnoye742.planit;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

import java.util.ArrayList;

public class PlanListAdapter extends BaseAdapter {
   private final ArrayList<PlanDataModel> al;
   
   PlanDatabase dbm;
   Context c;


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
    @SuppressLint("NonConstantResourceId")
    @Override
    public View getView(int position, View v, ViewGroup parent) {

        return v;
}

}
class Vars {
    TextView task;

    ImageView imv2;
    Typeface tf;
    Vars() {

    }

}
