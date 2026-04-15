package fi.jyu.ohj2.jseppane.kirjasto;

import model.Kirja;
import model.Kirjasto;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class KirjastoTest {

    /**
     * Testaa toimiiko kirjan lainaus
     *
     */
    @Test
    void lainaaKirjaTesti() {

        Kirjasto kirjasto = Kirjasto.getInstance();

        Kirja kirja = new Kirja("Testikirja", "Hackerman", "1234567890123", false, 0, null);

        kirjasto.lisaaKirja(kirja);
        kirjasto.lainaaKirja(kirja, "Matti");

        assertTrue(kirja.isLainassa());
        assertEquals("Matti", kirja.getNykyinenLainaaja());
        assertEquals(1, kirja.getLainausMaara());

        // cleanup
        kirjasto.poistaKirja(kirja);
    }

    /**
     * Testaa toimiiko kirjan palautus
     *
     */
    @Test
    void palautaKirjaTesti() {

        Kirjasto kirjasto = Kirjasto.getInstance();

        Kirja kirja = new Kirja("Testikirja", "Hackerman", "1234567890123", false, 0, null);

        kirjasto.lisaaKirja(kirja);
        kirjasto.lainaaKirja(kirja, "Matti");
        kirjasto.palautaKirja(kirja);

        assertFalse(kirja.isLainassa());
        assertNull(kirja.getNykyinenLainaaja());

        kirjasto.poistaKirja(kirja);
    }

    /**
     * Testaa virhetilanteen kirjan lisäämisen yhteydessä
     *
     */
    @Test
    void lisaaKirjaTesti() {

        Kirjasto kirjasto = Kirjasto.getInstance();

        assertThrows(IllegalArgumentException.class, () -> {kirjasto.lisaaKirja(null);});

    }
}