package controller;

import fi.jyu.ohj2.jseppane.kirjasto.Paneelit;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import model.Kirja;
import model.Kirjasto;

import java.util.Optional;

public class KirjastoController {

    /**
     * KirjastoController-luokan FXML- ja tavalliset attribuutit
     */
    @FXML private ListView<Kirja> kirjaLista;
    @FXML private TextField haeKirja;
    private ObservableList<Kirja> kaikkiKirjat;
    private Kirjasto kirjasto;

    /**
     * Alustaa KirjastoController-luokan käyttöliittymäkomponentit.
     * Alustuksessa tapahtuu seuraavat asiat:
     * - Haetaan Kirjasto-instanssi
     * - Ladataan kirjat ObservableListiin
     * - Asetetaan kirjalista ListView-komponenttiin
     * - Lisätään hakukenttään reaaliaikainen suodatin
     * - Määritetään ListView:n solujen ulkoasu
     */
    @FXML
    public void initialize() {

        kirjasto = Kirjasto.getInstance();

        /// Luo ObservableList kirjaston kirjoista UI:n käyttöön
        kaikkiKirjat = FXCollections.observableArrayList(kirjasto.getKirjat());

        /// Asettaa kirjalista ListView-komponenttiin
        kirjaLista.setItems(kaikkiKirjat);

        /// Suodattaa kirjalistan reaaliajassa hakukenttä-komponentin sisällä
        haeKirja.textProperty().addListener((obs, oldVal, newVal) -> {filter(newVal);});

        /// Määrittää ListView-komponentin ulkoasun
        kirjaLista.setCellFactory(list -> new ListCell<>() {

            @Override
            protected void updateItem(Kirja kirja, boolean empty) {super.updateItem(kirja, empty);

                /// Ei näytä mitään, jos solu on tyhjä
                if (empty || kirja == null) {

                    setText(null);
                }

                /// Muuten näyttää kirjan tiedot
                else {

                    setText(kirja.getNimi() + " - " + kirja.getTekija() + " | " + kirja.getIsbn());
                }
            }
        });
    }


    /**
     * Suodattaa kirjalistan hakusanan perusteella.
     * Metodi käy läpi kaikki kirjat ja näyttää vain ne, joiden nimi, tekijä tai ISBN sisältää annetun hakusanan.
     * Jos hakusana on tyhjä tai null, näytetään koko kirjalista.
     *
     * @param hakusana käyttäjän syöte
     */
    private void filter(String hakusana) {

        /// Palauttaa koko listan, jos hakusana on tyhjä
        if (hakusana == null || hakusana.isBlank()) {

            kirjaLista.setItems(kaikkiKirjat);

            return;
        }

        String haku = hakusana.toLowerCase();
        ObservableList<Kirja> filtered = FXCollections.observableArrayList();

        /// For-silmukka käy läpi kaikki kirjat ja tarkistaa löytyykö hakusana mistään kentästä
        for (Kirja kirja : kaikkiKirjat) {

            String nimi = kirja.getNimi();
            String tekija = kirja.getTekija();
            String isbn = kirja.getIsbn();

            /// Varsinainen suodatin
            if (nimi.toLowerCase().contains(haku) || tekija.toLowerCase().contains(haku) || isbn.toLowerCase().contains(haku)) {

                filtered.add(kirja);
            }
        }

        /// Asettaa suodatetun listan näkyviin
        kirjaLista.setItems(filtered);
    }


    /**
     * Poistaa valitun kirjan kirjastosta.
     * Metodi tarkistaa ensin, onko käyttäjä valinnut kirjan listalta, minkä jälkeen käyttäjälle ilmestyy varmistusikkuna.
     * Kirjan poistamissatapahtuu seuraavat asiat:
     * - kirja poistetaan listalta
     * - muutokset tallennetaan JSON-tiedostoon
     * - käyttöliittymän lista päivitetään
     */
    @FXML
    private void poistaKirja() {

        /// Asettaa valitun kirjan muuttujaan
        Kirja valittu = kirjaLista.getSelectionModel().getSelectedItem();

        /// Varmistaa sen, että jokin kirja on valittu
        if (valittu == null){

            return;
        }

        /// Ponnahdusikkuna.
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Poista kirja");
        alert.setHeaderText("Haluatko varmasti poistaa kirjan?");
        alert.setContentText(valittu.getNimi());
        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {

            /// Poistetaan kirja tietomallista
            kirjasto.poistaKirja(valittu);

            /// Tallennetaan muutokset tiedostoon
            kirjasto.tallenna();

            /// Päivitetään käyttöliittymän lista
            kaikkiKirjat.setAll(kirjasto.getKirjat());
        }
    }


    /**
     * Siirtää käyttäjän "Lisää kirja" -näkymään.
     */
    @FXML
    private void avaaLisaaKirja() {

        Paneelit.vaihdaPaneeli("lisaysPaneeli.fxml");
    }


    /**
     * Siirtää käyttäjän palautusnäkymän.
     * Metodi tarkistaa ensin sen, että käyttäjä on valinnut kirjan listalta, jotta sen voisi käsitellä seuraavassa näkymässä
     *
     */
    @FXML
    private void avaaPalauta() {

        Kirja valittu = kirjaLista.getSelectionModel().getSelectedItem();

        /// Varmistaa sen, että jokin kirja on valittu
        if (valittu == null){

            return;
        }

        Paneelit.setValittuKirja(valittu);

        Paneelit.vaihdaPaneeli("palautusPaneeli.fxml");
    }


    /**
     * Siirtää käyttäjän tietonäkymään.
     * Metodi tarkistaa ensin sen, että käyttäjä on valinnut kirjan listalta, jotta sen voisi käsitellä seuraavassa näkymässä
     *
     */
    @FXML
    private void avaaTiedot() {

        Kirja valittu = kirjaLista.getSelectionModel().getSelectedItem();

        /// Varmistaa sen, että jokin kirja on valittu
        if (valittu == null){

            return;
        }

        Paneelit.setValittuKirja(valittu);

        Paneelit.vaihdaPaneeli("tietoPaneeli.fxml");
    }


    /**
     * Siirtää käyttäjän lainausnäkymään.
     * Metodi tarkistaa ensin sen, että käyttäjä on valinnut kirjan listalta, jotta sen voisi käsitellä seuraavassa näkymässä
     *
     */
    @FXML
    private void avaaLainaa() {

        Kirja valittu = kirjaLista.getSelectionModel().getSelectedItem();

        /// Varmistaa sen, että jokin kirja on valittu
        if (valittu == null){

            return;
        }

        Paneelit.setValittuKirja(valittu);

        Paneelit.vaihdaPaneeli("lainausPaneeli.fxml");
    }
}
