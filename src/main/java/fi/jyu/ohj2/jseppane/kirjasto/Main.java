package fi.jyu.ohj2.jseppane.kirjasto;

import javafx.application.Application;
import model.Kirja;
import model.Kirjasto;
import model.Lainaus;

public class Main {

    public static void main(String[] args) {

        /// testaaKirjasto();
        Application.launch(App.class, args);

    }

    /**
     * Testimetodi, jonka tarkoituksena on testata Kirjasto-luokan perustoiminnallisuuksia.
     * Metodi suorittaa kirjasto-olion elinkaaren keskeiset operaatiot ja tulostaa niiden tulokset konsoliin.
     *
     */
    private static void testaaKirjasto() {

        System.out.println("Aloitetaan testi");
        Kirjasto kirjasto = Kirjasto.getInstance();

        System.out.println("Alussa kirjoja: " + kirjasto.getKirjat().size());

        /// Luodaan kirja-olio
        Kirja kirja1 = new Kirja("Testi", "Matti Meikäläinen", "1234567890123", false, 0, null);

        /// Lisätään kirja-olio kirjastoon
        kirjasto.lisaaKirja(kirja1);
        System.out.println("Lisättiin kirja.");
        System.out.println("Kirjoja nyt: " + kirjasto.getKirjat().size());

        /// Tulostetaan lista kirjoista
        System.out.println("\nKirjat listassa:");
        for (Kirja kirja : kirjasto.getKirjat()) {

            System.out.println(kirja);
        }

        /// Lainataan kirja
        kirjasto.lainaaKirja(kirja1, "Jakke Jääkäri");
        System.out.println("\nLainattiin kirja onnistuneesti:");
        System.out.println(kirja1);

        /// Lainataan uudestaan
        kirjasto.lainaaKirja(kirja1, "Toinen käyttäjä");

        /// Palautetaan
        kirjasto.palautaKirja(kirja1);
        System.out.println("\nPalautettiin kirja onnistuneesti:");
        System.out.println(kirja1);

        /// Palautetaan uudestaan
        kirjasto.palautaKirja(kirja1);

        /// Printataan kirjan lainaushistoria
        System.out.println("\nLainaushistoria:");
        for (Lainaus lainaus : kirja1.getLainausHistoria()) {

            System.out.println(lainaus.getLainaaja() + " lainasi " + lainaus.getLainattu() + " ja palautti " + lainaus.getPalautettu());
        }

        /// Poistetaan kirja
        kirjasto.poistaKirja(kirja1);
        System.out.println("\nPoistettiin kirja onnistuneesti.");
        System.out.println("Kirjoja nyt: " + kirjasto.getKirjat().size());

        System.out.println("Lopetetaan testi");
    }
}
