package com.github.farafonoff.deskpetsir.ircomm;

import android.content.Context;
import android.hardware.ConsumerIrManager;

/**
 * Created by Artem_Farafonov on 12/22/2015.
 */
public class ConsumerIRComm implements IIRComm {
    ConsumerIrManager manager;
    public ConsumerIRComm(Context ctx) {
        manager = (ConsumerIrManager) ctx.getSystemService(Context.CONSUMER_IR_SERVICE);
    }

    @Override
    public void sendIRCode(int frequency, int[] command) {
        manager.transmit(frequency, command);
    }
}
