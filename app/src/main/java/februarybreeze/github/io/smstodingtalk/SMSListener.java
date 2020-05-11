package februarybreeze.github.io.smstodingtalk;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Telephony;
import android.telephony.SmsMessage;

import java.util.Objects;

public class SMSListener extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if (Objects.equals(intent.getAction(), Telephony.Sms.Intents.SMS_RECEIVED_ACTION)) {
            Bundle bundle = intent.getExtras();
            if (bundle == null) {
                return;
            }

            SmsMessage[] messages = Telephony.Sms.Intents.getMessagesFromIntent(intent);
            StringBuilder body = new StringBuilder();
            String senderNumber = "";

            for (SmsMessage message : messages) {
                senderNumber = message.getDisplayOriginatingAddress();
                body.append(message.getDisplayMessageBody());
            }

            String text = body.toString() + "\nFrom: " + senderNumber;
            startSmsService(context, text);
        }
    }

    private void startSmsService(Context context, String message) {
        Intent serviceIntent = new Intent(context, DingTalkService.class);
        serviceIntent.putExtra(Constant.Message, message);
        context.startService(serviceIntent);
    }
}
