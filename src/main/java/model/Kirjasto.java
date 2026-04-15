package model;

import tools.jackson.databind.ObjectMapper;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Kirjasto {

    /**
     * Kirjasto-luokan keskeiset attribuutit
     */
    private static Kirjasto instance;
    private List<Kirja> kirjat = new ArrayList<>();


    /**
     * Lainaa kirjan annetulle lainaajalle.
     * Metodi tarkistaa ensin, että kirja on olemassa eikä ole jo lainassa. Sen jälkeen kirja merkitään lainatuksi, asetetaan lainaaja,
     * kasvatetaan lainausmäärää ja lisätään uusi merkintä lainaushistoriaan. Muutokset tallennetaan tiedostoon.
     *
     * @param kirja kirja, joka lainataan
     * @param lainaaja lainauksen suorittava henkilö
     */
    public void lainaaKirja(Kirja kirja, String lainaaja) {

        /// Tarkistaa onko kirja olemassa
        if (kirja == null) {

            return;
        }

        /// Tarkistaa onko kirja lainassa. Printtaa konsoliin virheilmoituksen
        if (kirja.isLainassa()) {

            System.out.println("Kirja on jo lainassa!");
            return;
        }

        kirja.setLainassa(true);
        kirja.setNykyinenLainaaja(lainaaja);
        kirja.setLainausMaara(kirja.getLainausMaara() + 1);

        /// Asettaa uuden lainaushistorian, jos sellaista ei ole olemassa
        if (kirja.getLainausHistoria() == null) {

            kirja.setLainausHistoria(new ArrayList<>());
        }

        ///  Tekee uuden merkinnän lainaushistoriaan
        kirja.getLainausHistoria().add(new Lainaus(kirja.getIsbn(), lainaaja, LocalDate.now().toString(), null));

        tallenna();
    }


    /**
     * Palauttaa kirjan takaisin kirjastoon.
     * Metodi tarkistaa ensin, että kirja on olemassa ja että se on lainassa. Sen jälkeen kirja merkitään vapaaksi, lainaaja poistetaan
     * ja viimeisimmän lainauksen palautuspäivä asetetaan. Lopuksi muutokset tallennetaan tiedostoon.
     *
     * @param kirja kirja, joka palautetaan
     */
    public void palautaKirja(Kirja kirja) {

        /// Tarkistaa onko kirja olemassa
        if (kirja == null) {

            return;
        }

        /// Tarkistaa onko kirja lainassa. Printaa konsoliin virheilmoituksen
        if (!kirja.isLainassa()) {

            System.out.println("Kirja ei ole lainassa!");
            return;
        }

        /// Merkitsee kirjan palautetuksi ja pyyhii nykyisen lainaajan
        kirja.setLainassa(false);
        kirja.setNykyinenLainaaja(null);

        if (kirja.getLainausHistoria() != null && !kirja.getLainausHistoria().isEmpty()) {

            /// Hakee viimeisen lainauksen kirjasta
            Lainaus viimeinen = kirja.getLainausHistoria().get(kirja.getLainausHistoria().size() - 1);

            /// Asettaa palautuspäiväksi tämänhetkisen päivän
            viimeinen.setPalautettu(LocalDate.now().toString());
        }

        tallenna();
    }


    /**
     * Hakee kirjaston kirjat JSON-tiedostosta.
     * Metodi tarkistaa ensin löytyykö tiedosto. Jos tiedostoa ei ole olemassa, niin se alustetaan tyhjä kirjaluettelo.
     * Mikäli tiedosto löytyy, kirjat luetaan ObjectMapperin avulla ja muunnetaan Kirja-olioiden listaksi.
     * Virheellisessä haussa metodi palauttaa tyhjän listan.
     *
     */
    public void lataa() {

        ObjectMapper mapper = new ObjectMapper();

        try {
            ///  Muodostaa polun tiedostoon
            Path path = Path.of(System.getProperty("user.dir"), "data", "kirjat.json");

            /// Printtaa konsoliin haettavan tiedoston polun (Debuggaus).
            System.out.println("Ladataan tiedosto: " + path.toAbsolutePath());

            ///  Palauttaa tyhjän listan, jos tiedostoa ei ole olemassa
            if (!Files.exists(path)) {

                kirjat = new ArrayList<>();
                return;
            }

            /// Lukee haetun tiedoston ja muodostaa siitä oliolistan
            kirjat = mapper.readValue(path.toFile(), mapper.getTypeFactory().constructCollectionType(List.class, Kirja.class));

        }

        /// Virheen sattuessa tulostetaan virhe ja palautetaan tyhjä lista.
        catch (Exception exception) {

            exception.printStackTrace();
            kirjat = new ArrayList<>();
        }
    }


    /**
     * Tallentaa kirjaston kirjat JSON-tiedostoon.
     * Metodi varmistaa ensin, että tallennuskansio on olemassa, minkä jälkeen kirjalista kirjoitetaan tiedostoon ObjectMapperin avulla.
     *
     */
    public void tallenna() {
        try {
            /// Muodostaa polun tiedostoon
            Path path = Path.of(System.getProperty("user.dir"), "data", "kirjat.json");

            Files.createDirectories(path.getParent());

            ObjectMapper mapper = new ObjectMapper();

            /// Tallennetaan kirjalista JSON-muotoon
            mapper.writeValue(path.toFile(), kirjat);
        }

        /// Virheen sattuessa tulostetaan virheilmoituksen konsoliin.
        catch (Exception exception) {

            exception.printStackTrace();
        }
    }


    /**
     * Lisää uuden kirjan kirjaluetteloon. Muutokset tallennetaan JSON-tiedostoon kutsumalla metodi.
     *
     * @param kirja lisättävä kirja
     */
    public void lisaaKirja(Kirja kirja) {

        if (kirja == null) {

            throw new IllegalArgumentException("Ei saa olla null!");
        }

        kirjat.add(kirja);
        tallenna();
    }


    /**
     * Poistaa kirjan kirjaluettelosta. Muutokset tallennetaan JSON-tiedostoon kutsumalla metodi.
     *
     * @param kirja poistettava kirja
     */
    public void poistaKirja(Kirja kirja) {

        kirjat.remove(kirja);
        tallenna();
    }


    /// Getterit. Metodit, jotka hakevat olion ominaisuuksia

    /**
     * Palauttaa Kirjasto-luokan instanssin (Singleton-malli).
     * Jos instanssia ei ole vielä olemassa, se luodaan ja samalla ladataan kirjat JSON-tiedostosta.
     *
     * @return Kirjasto-luokan instanssi
     */
    public static Kirjasto getInstance() {

        if (instance == null) {
            instance = new Kirjasto();
            instance.lataa();
        }

        return instance;
    }


    /**
     * Palauttaa kirjaston kirjat listana.
     * Palautettava lista on suojattu. Muokkaukset tulee tehdä Kirjasto-luokan metodien kautta.
     *
     * @return lista kirjoista
     */
    public List<Kirja> getKirjat() {

        return Collections.unmodifiableList(kirjat);
    }
}