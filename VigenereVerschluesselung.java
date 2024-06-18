/*
public class VigenereVerschluesselung implements Kryptomodul {
    String key;

    // Methode zum Verschlüsseln eines Klartextes mit einem Schlüssel
    public String verschluesseln(String klartext, String schluessel) {
        StringBuilder verschluesselt = new StringBuilder();
        schluessel = schluessel.toUpperCase();
        klartext = klartext.toUpperCase();

        for (int i = 0, j = 0; i < klartext.length(); i++) {
            char buchstabe = klartext.charAt(i);
            if (buchstabe < 'A' || buchstabe > 'Z') {
                // Wenn der Buchstabe kein Alphabetbuchstabe ist, unverändert hinzufügen
                verschluesselt.append(buchstabe);
                continue;
            }
            char verschluesselterBuchstabe = (char) ((buchstabe + schluessel.charAt(j) - 2 * 'A') % 26 + 'A');
            verschluesselt.append(verschluesselterBuchstabe);
            j = ++j % schluessel.length();
        }
        return verschluesselt.toString();
    }

    // Methode zum Entschlüsseln eines verschlüsselten Textes mit einem Schlüssel
    public String entschluesseln(String verschluesselterText, String schluessel) {
        StringBuilder entschluesselt = new StringBuilder();
        schluessel = schluessel.toUpperCase();
        verschluesselterText = verschluesselterText.toUpperCase();

        for (int i = 0, j = 0; i < verschluesselterText.length(); i++) {
            char buchstabe = verschluesselterText.charAt(i);
            if (buchstabe < 'A' || buchstabe > 'Z') {
                // Wenn der Buchstabe kein Alphabetbuchstabe ist, unverändert hinzufügen
                entschluesselt.append(buchstabe);
                continue;
            }
            char entschluesselterBuchstabe = (char) ((buchstabe - schluessel.charAt(j) + 26) % 26 + 'A');
            entschluesselt.append(entschluesselterBuchstabe);
            j = ++j % schluessel.length();
        }
        return entschluesselt.toString();
    }

    public void loadKey()
    {
        key = 
    }
    }
}
*/