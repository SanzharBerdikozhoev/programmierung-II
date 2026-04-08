/**
 * Klasse TariffPlanSmsHandy.
 * Ein Vertragshandy, das über eine bestimmte Menge an Frei-SMS verfügt.
 * In einer späteren Version könnten diese nach einer bestimmten Zeit wieder zurückgesetzt werden.
 * Dies wird vorerst noch nicht berücksichtigt.
 * */
public class TariffPlanSmsHandy extends SmsHandy {
    private int remainingFreeSms = 100;

    /**
     * Konstruktor zum Erstellen eines neuen TariffPlanHandy
     * */
    public TariffPlanSmsHandy(String number, Provider provider) {
        super(number, provider);
    }

    /**
     * Liefert Anzahl der verbliebenen Frei-SMS.
     *
     * @return Anzahl der Frei-SMS
     * */
    private int getRemainingFreeSms() {
        return this.remainingFreeSms;
    }

    /**
     * Prüft, ob Frei-SMS noch zum Senden ausreichen.
     *
     * @return noch Frei-SMS vorhanden?
     * */
    @Override
    public boolean canSendSms() {
        return this.getRemainingFreeSms() > 0;
    }

    /**
     * Reduziert die Frei-SMS.
     */
    @Override
    public void payForSms() {
        this.remainingFreeSms--;
    }

    public void updateProvider(Provider newProvider) {
        this.provider = newProvider;
        this.remainingFreeSms += 20;
    }
}
