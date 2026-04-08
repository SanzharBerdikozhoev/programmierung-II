import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

/**
 * Abstrakte Basisklasse SmsHandy
 * */
public abstract class SmsHandy {
    protected String number;
    protected Provider provider;
    protected List<Message> receivedMessages;
    protected List<Message> sentMessages;

    /**
     * Konstruktor für Objekte der Klasse SmsHandy
     *
     * @param number - die Handynummer
     * @param provider - die Providerinstanz
     * */
    protected SmsHandy(String number, Provider provider) {}

    /**
     * Gibt eine Liste aller empfangenen SMS auf der Konsole aus.
     * */
    protected void listReceived() {
        receivedMessages.forEach(System.out::println);
    }

    /**
     * Gibt eine Liste aller gesendete SMS auf der Konsole aus.
     * */
    protected void listSent() {
        sentMessages.forEach(System.out::println);
    }

    /**
     * Abstrakte Methode zur Prüfung, ob der Versand der SMS noch bezahlt werden kann.
     *
     * @return ist der Versand der SMS noch möglich?
     * */
    public abstract boolean canSendSms();

    /**
     * Abstrakte Methode zum Bezahlen des SMS-Versand.
     * */
    public abstract void payForSms();

    /**
     * Schickt eine SMS über den Provider an den Empfänger.
     *
     * @param to - der Empfänger der SMS
     * @param content - der Inhalt der SMS
     * */
    public void sendSms(String to, String content) {
        if (to.equals("*101#")) {
            return;
        }

        if(!canSendSms()) {
            return;
        }

        Message newMessage = new Message(content, LocalDateTime.now(), number, to);

        if(this.provider.send(newMessage)) {
            payForSms();
            this.sentMessages.add(newMessage);
        }
    }

    /**
     * Schickt eine SMS ohne den Provider an den Empfänger (quasi per Direkverbindung zwischen zwei Handys)
     *
     * @param peer - das empfangende Handy
     * @param content - der Inhalt der SMS
     * */
    public void sendSmsDirect(SmsHandy peer, String content) {
        peer.receiveSms(new Message(content, LocalDateTime.now(), number, peer.getNumber()));
    }

    /**
     * Empfängt eine SMS und speichert diese in den empfangenen SMS
     *
     * @param message - das Message-Objekt, welches an das zweite Handy gesendet werden soll.
     * */
    public void receiveSms(Message message) {
        this.receivedMessages.add(message);
    }

    /**
     * Gibt die Handynummer zurück.
     *
     * @return die Handynummer
     * */
    public String getNumber() {
        return number;
    }

    /**
     * Gibt den aktuellen Provider zurück.
     *
     * @return aktueller Provider des Handys
     * */
    public Provider getProvider() {
        return provider;
    }

    /**
     * Setzt den Provider.
     *
     * @param provider - ProviderInstanz
     * */
    public void setProvider(Provider provider) {
        this.provider = provider;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == this) return true;
        if(obj == null || obj.getClass() != this.getClass()) return false;
        SmsHandy other = (SmsHandy) obj;
        return Objects.equals(this.number, other.number);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
    }
}
