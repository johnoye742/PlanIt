package com.johnoye742.planit;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.widget.Toast;

public class DeleteService extends Service {
    public DeleteService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        PlanDatabase pd = new PlanDatabase(getApplicationContext());
        pd.delete(intent.getStringExtra("title"), intent.getStringExtra("descr"));
        Toast.makeText(getApplicationContext(), "Task has been deleted", Toast.LENGTH_SHORT).show();
        return super.onStartCommand(intent, flags, startId);


    }
}