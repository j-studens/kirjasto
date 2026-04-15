package controller;

import fi.jyu.ohj2.jseppane.kirjasto.Paneelit;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import model.Kirja;
import model.Kirjasto;

import java.time.LocalDate;


public class LainausController {

    /**
     * Controller-luokan FXML- ja tavalliset attribuutit
     */
    @FXML private TextField syotaLainaaja;
    @FXML private ListView<String> kirjaTieto;
    @FXML private ListView<String> lainausPaiva;
    @FXML private ListView<String> palautusPaiva;
    private Kirjasto kirjasto;
    private Kirja kirja;

    /**
     * Alustaa lainaamisnäkymän käyttöliittymään.
     * Hakee Kirjasto-instanssin sekä valitun kirjan Nakymat-luokasta, minkä jälkeen näytää kirjan tiedot,
     * asettaa lainauspäiväksi nykyhetkisen päivän ja osoittaa palautuspäivän (Oletuksena 1 kuukausi nykyhetkestä)
     *
     */
    @FXML
    public void initialize() {

        kirjasto = Kirjasto.getInstance();
        kirja = Paneelit.getValittuKirja();

        /// Keskeyttää metodin, jos kirja ei ole valittu
        if (kirja == null){

            return;
        }

        /// Näyttää kirjan tiedot, kuten nimi, tekijä ja ISBN-tunniste
        kirjaTieto.setItems(FXCollections.observableArrayList(kirja.getNimi() + " - " + kirja.getTekija() + " | ISBN: " + kirja.getIsbn()));

        /// Määrittää lainaus- ja palautuspäivän
        LocalDate nyt = LocalDate.now();
        LocalDate palautus = nyt.plusMonths(1);
        lainausPaiva.setItems(FXCollections.observableArrayList("Lainauspäivä: " + nyt));
        palautusPaiva.setItems(FXCollections.observableArrayList("Palautuspäivä: " + palautus));
    }


    /**
     * Lainaa valitun kirjan käyttäjän antamalle lainaajalle.
     * Metodi varmistaa sen, että kaikki syöttökentät ovat täytetty ja ettei kirja ole jo lainassa,
     * minkä jälkeen kirja lainataa, lainaustiedot päivitetään tietomalliin ja
     * käyttäjä siirretään takaisin varsinaiseen kirjasto näkymään
     *
     */
    @FXML
    private void lainaaKirja() {

        /// Tarkistaa sen, että kirja on valittu
        if (kirja == null){

            return;
        }

        String lainaaja = syotaLainaaja.getText();

        /// Tarkistaa sen, että nimi on annettu
        if (lainaaja == null || lainaaja.trim().isEmpty()) {

            return;
        }

        /// Tarkistaa ettei kirja ole jo lainassa
        if (kirja.isLainassa()) {

            return;
        }

        /// Varsinainen lainaustapahtuma
        kirjasto.lainaaKirja(kirja, lainaaja);

        Paneelit.vaihdaPaneeli("kirjastoPaneeli.fxml");
    }

    /**
     * Metodi, joka yksinkertaisesti siirtää käyttäjän takaisin päänäkymään.
     */
    @FXML
    private void peruuta() {

        Paneelit.vaihdaPaneeli("kirjastoPaneeli.fxml");
    }
}