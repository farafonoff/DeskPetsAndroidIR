package com.github.farafonoff.deskpetsir;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.text.TextUtils;

import com.github.farafonoff.deskpetsir.control.Action;
import com.github.farafonoff.deskpetsir.ircomm.ConsumerIRComm;
import com.github.farafonoff.deskpetsir.ircomm.IIRComm;
import com.github.farafonoff.deskpetsir.ircomm.SamsungIRComm;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class TransmissionService extends Service {
    public TransmissionService() {
    }

    Action state;

    IIRComm comm;

    ScheduledExecutorService ses = Executors.newSingleThreadScheduledExecutor();

    void initComm() {
        if (comm==null) {
            comm = new ConsumerIRComm(this);
        }
    }

    void sendIr(String lircCommand) {
        String[] numbers = lircCommand.split("[ \n]+");
        int[] command = new int[numbers.length];
        for(int i=0;i<command.length;++i) {
            command[i] = Integer.valueOf(numbers[i]);
        }
        comm.sendIRCode(38000, command);
    }

    @Override
    public void onCreate() {
        initComm();
        ses.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                if (state!=null) {
                    sendIr(state.command);
                }
            }
        }, 1000, 500, TimeUnit.MILLISECONDS);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        super.onStartCommand(intent, flags, startId);
        if ("IRSEND".equals(intent.getAction())) {
            Action action = (Action)intent.getParcelableExtra("action");
            state = action;
            sendIr(action.command);
        }
        return START_REDELIVER_INTENT;
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
