import java.time.LocalDateTime;
import java.util.Objects;


/**
 * Klasse Message. Eine Nachricht, die über SMSHandys verschickt werden kann.
 * */
public class Message {
    /**
     * Inhalt der Nachricht
     * */
    private String content;

    /**
     * Empfänger
     * */
    private LocalDateTime date;

    /**
     * Absender
     * */
    private String from;

    /**
     * Datum
     * */
    private String to;

    /**
     * Konstruktor ohne Parameter
     * */
    public Message() {}

    /**
     * Konstruktor mit Parametern
     *
     * @param content - Inhalt der Nachricht
     * @param to - Empfaenger
     * @param from - Absender
     * @param date - Datum
     */
    public Message(String content, LocalDateTime date, String from, String to) {
        this.content = content;
        this.date = date;
        this.from = from;
        this.to = to;
    }

    /**
     * Gibt die vollstaendige Nachricht als String zurück.
     *
     * @return formatierter String, mit allen Daten
     * */
    @Override
    public String toString() {
        return this.content;
    }

    /**
     * Gibt den Inhalt der Nachricht zurück.
     *
     * @return aktueller Inhalt der SMS
     * */
    public String getContent() {
        return content;
    }

    /**
     * Setzt den Inhalt der Nachricht.
     *
     * @param content - neuer Inhalt fuer die SMS
     * */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * Gibt das Erstellungsdatum der Nachricht zurück.
     *
     * @return Erstellungsdatum der SMS
     * */
    public LocalDateTime getDate() {
        return date;
    }

    /**
     * Setzt das Erstellungsdatum der SMS.
     *
     * @param date - Neues Datum fuer die SMS
     * */
    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    /**
     * Gibt den Absender der Nachricht zurück.
     *
     * @return aktueller Absender der SMS
     * */
    public String getFrom() {
        return from;
    }

    /**
     * Setzt den Absender der Nachricht.
     *
     * @param from - neuer Absender fuer die SMS
     * */
    public void setFrom(String from) {
        this.from = from;
    }

    /**
     * Gibt den Empfänger zurück
     *
     * @return aktueller Empfaenger fuer die SMS
     */
    public String getTo() {
        return to;
    }

    /**
     * Setzt den Empfaenger der Nachricht.
     *
     * @param to - neuer Empfaenger fuer die SMS
     * */
    public void setTo(String to) {
        this.to = to;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == this) return true;
        if(obj == null || obj.getClass() != this.getClass()) return false;
        Message other = (Message) obj;
        return Objects.equals(content, other.content) &&
                Objects.equals(date, other.date) &&
                Objects.equals(from, other.from) &&
                Objects.equals(to, other.to);
    }

    @Override
    public int hashCode() {
        return Objects.hash(content, date, from, to);
    }
}
