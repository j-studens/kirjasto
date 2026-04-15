package fi.jyu.ohj2.jseppane.kirjasto;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Kirja;

import java.io.IOException;

public class Paneelit {

    /**
     * Paneelit-luokan keskeiset attribuutit
     */
    private static Stage paneeli;
    private static Kirja valittuKirja;

    /**
     * Lataa uuden käyttöliittymän näkymän.
     *
     * @param fxmlFile ladattavan FXML-näkymän tiedosto
     */
    public static void vaihdaPaneeli(String fxmlFile) {

        try {

            FXMLLoader loader = new FXMLLoader(Paneelit.class.getResource(fxmlFile));
            Parent root = loader.load();

            paneeli.setScene(new Scene(root));
            paneeli.show();
        }

        /// Virheilmoitus
        catch (IOException exception) {

            System.err.println("Virhe näkymän latauksessa: " + fxmlFile);
            exception.printStackTrace();
        }
    }


    /// Getterit. Metodit, jotka hakevat olion ominaisuuksia

    /**
     * Palauttaa valitun kirjan.
     * Mahdollistaa näkymien välistä kommunikointia.
     *
     * @return valittu Kirja-olio
     */
    public static Kirja getValittuKirja() {

        return valittuKirja;
    }


    /// Setterit. Metodit, jotka säätää olion ominaisuuksia

    /**
     * Asettaa sovelluksen pääikkunan.
     *
     * @param uusiPaneeli JavaFX Stage, johon näkymät asetetaan
     */
    public static void setStage(Stage uusiPaneeli) {

        paneeli = uusiPaneeli;
    }


    /**
     * Asettaa valitun kirjan, jota käytetään seuraavassa näkymässä.
     * Mahdollistaa näkymien välistä kommunikointia.
     *
     * @param kirja Kirja-olio.
     */
    public static void setValittuKirja(Kirja kirja) {

        valittuKirja = kirja;
    }

}