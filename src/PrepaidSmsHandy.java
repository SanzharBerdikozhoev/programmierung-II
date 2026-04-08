/**
 * Klasse PrepaidSmsHandy.
 * Ein Handy, das über ein beim Provider verwaltetes Guthaben verfügt
 * und dessen SMS-Versand über dieses Guthaben abgerechnet wird.
 * */
public class PrepaidSmsHandy extends SmsHandy {
    public final int COST_PER_SMS = 10;
    public final int STARTING_CREDITS = 1000;

    /**
     * Konstruktor zum Erstellen eines neuen PrepaidHandy
     * */
    public PrepaidSmsHandy(String number, Provider provider) {
        super(number, provider);
        this.deposit(STARTING_CREDITS);
    }

    /**
     * Lädt das Guthaben für das SmsHandy-Objekt auf.
     *
     * @param amount - Menge, um die aufgeladen werden soll.
     * */
    public void deposit(int amount) {
        this.provider.deposit(number, amount);
    }

    /**
     * Zieht Guthaben vom SmsHandy-Objekt ab.
     *
     * @param amount - Menge, die vom Guthaben abgezogen werden soll
     * */
    public void withdraw(int amount) {
        this.provider.deposit(number, -amount);
    }

    /**
     * Prüft, ob das Guthaben noch für das Versenden einer SMS reicht.
     *
     * @return ist das Guthaben noch ausreichend?
     * */
    @Override
    public boolean canSendSms() {
        int balance = this.provider.getCreditForSmsHandy(number);
        return balance >= COST_PER_SMS;
    }

    /**
     * Zieht die Kosten für eine SMS vom Guthaben ab.
     * */
    @Override
    public void payForSms() {
        if (!this.canSendSms()) {
            return;
        }

        this.withdraw(COST_PER_SMS);
    }

    public void updateProvider(Provider newProvider) {
        this.provider = newProvider;
        this.provider.deposit(this.getNumber(), 200);
    }
}
