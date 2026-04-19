import model.handy.prepaidSmsHandy.PrepaidSmsHandy;
import model.handy.SmsHandy;
import model.handy.tariffPlanSmsHandy.TariffPlanSmsHandy;
import model.provider.Provider;

public class Main {
    public static void main(String[] args) {
        Provider provider = new Provider();
        SmsHandy smsHandy = new TariffPlanSmsHandy("+49123456789", provider);
        SmsHandy smsHandy2 = new PrepaidSmsHandy("+491345323445", provider);
    }
}