package com.johnoye742.planit;

public class PlanDataModel {
   public final String description;
    public final String plan;
    public final String time;

    public PlanDataModel(String plan, String description, String time) {
        this.plan = plan;
        this.description = description;
        this.time = time;
    }
}
