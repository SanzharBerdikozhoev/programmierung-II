import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Ein Provider verwaltet eine Reihe von SmsHandys.
 * Dazu gehört die Verwaltung des Guthabens.
 * Auch dient der Provider als Kommunikationsschnittstelle zu den Handys, die er verwaltet.
 * */
public class Provider {
    private Map<String, Integer> credits;

    /**
     * Telefonnummer -> Handy
     * */
    private Map<String, SmsHandy> subscribers;
    private static List<Provider> providers = new ArrayList<>();

    /**
     * Konstruktor für Objekte der Klasse Provider.
     * */
    public Provider() {
        providers.add(this);
    }

    /**
     * Lädt Guthaben für ein Handy auf.
     * Das ist nötig, weil das Handy sein Guthaben nicht selbst ändern kann, sondern nur der Provider.
     * Negative Geldmengen werden hier erlaubt, um über diese Funktion auch die Kosten für eine Nachricht abziehen zu koennen.
     * Negative Werte beim händischen Aufladen werden in der Klasse SmsHandy verhindert.
     *
     * @param number - Nummer des Telefons
     * @param amount - Hoehe des Geldbetrages
     * */
    public void deposit(String number, int amount) {
        this.credits.put(number, credits.get(number) + amount);
    }

    /**
     * Gibt das aktuelle Guthaben des betreffenden Handys zurück.
     *
     * @param number - Nummer des gewuenschten Handys
     * @return aktuelles Guthaben des Handys
     * */
    public int getCreditForSmsHandy(String number) {
        return this.credits.get(number);
    }

    /**
     * Registriert ein neues Handy bei diesem Provider.
     *
     * @param smsHandy - das neue Handy
     * */
    public void register(SmsHandy smsHandy) {
        this.subscribers.put(smsHandy.getNumber(), smsHandy);
    }

    /**
     * Sendet die SMS an den Empfänger, wenn dieser bekannt ist.
     *
     * @param message - die zu sendende SMS
     * @return true, wenn SMS gesendet werden konnte
     * */
    public boolean send(Message message) {
        if (!canSendTo(message.getTo())) {
            return false;
        }

        Provider recipientProvider = findProviderFor(message.getTo());
        SmsHandy recipient = recipientProvider.subscribers.get(message.getTo());

        if(recipient == null) {
            return false;
        }

        recipient.receiveSms(message);
        return false;
    }

    /**
     * Prüft, ob die Handysliste das Handy mit dieser Nummer beinhaltet.
     *
     * @param receiver - Nummer des gewuenschten Handys
     * @return ob die Liste der Handys das Handy mit dieser Nummer enthält
     * */
    private boolean canSendTo(String receiver) {
        return findProviderFor(receiver) != null;
    }

    private Provider findProviderFor(String receiver) {
        for(Provider provider : providers) {
            if(provider.subscribers.containsKey(receiver)) {
                return provider;
            }
        }

        return null;
    }
}
