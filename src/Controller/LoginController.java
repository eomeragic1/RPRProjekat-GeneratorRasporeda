package Controller;

import Model.LoginDAO;
import Model.UserNotFoundException;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import static javafx.scene.control.PopupControl.USE_COMPUTED_SIZE;

public class LoginController {

    public Button btnRegistracija;
    public Button btnPrijava;
    public PasswordField fldLozinka;
    public TextField fldKorisnickoIme;
    public Label labelGreska;

    public LoginController() {}
    @FXML
    public void initialize() {
        fldKorisnickoIme.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String old, String news) {
                fldLozinka.getStyleClass().removeAll("poljeNijeIspravno");
                fldKorisnickoIme.getStyleClass().removeAll("poljeNijeIspravno");
                labelGreska.setText("");
            }
        });
    }
    public void handlePrijava(ActionEvent actionEvent) {
        boolean uspjesnaPrijava=checkData();
        if (uspjesnaPrijava) {
            fldLozinka.getStyleClass().removeAll("poljeNijeIspravno");
            fldKorisnickoIme.getStyleClass().removeAll("poljeNijeIspravno");
            fldKorisnickoIme.getStyleClass().add("poljeIspravno");
            fldLozinka.getStyleClass().add("poljeIspravno");
            labelGreska.setText("Uspješna prijava");
            labelGreska.setTextFill(Color.GREENYELLOW);
            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {

                }
            },1000);

        }
        else {
            labelGreska.setTextFill(Color.LIGHTPINK);
            fldLozinka.getStyleClass().removeAll("poljeIspravno");
            fldLozinka.getStyleClass().add("poljeNijeIspravno");
            fldKorisnickoIme.getStyleClass().removeAll("poljeIspravno");
            fldKorisnickoIme.getStyleClass().add("poljeNijeIspravno");
        }
    }

    private boolean checkData() {
        String username = fldKorisnickoIme.textProperty().get();
        String password = fldLozinka.textProperty().get();
        LoginDAO dao = LoginDAO.getInstance();
        try {
            String dbPassword=dao.dajLozinku(username);
            if (!dbPassword.equals(password)) {
                labelGreska.setText("Pogresna lozinka");
                return false;
            }
            else {
                return true;
            }
        }
        catch (UserNotFoundException e) {
            labelGreska.setText(e.getMessage());
            return false;
        }
    }

    public void handleRegistracija(ActionEvent actionEvent) throws IOException {
        LoginDAO dao = LoginDAO.getInstance();
        RegistracijaController ctrl = new RegistracijaController();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/registracija.fxml"));
        loader.setController(ctrl);
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.setTitle("Registracija novog korisnika");
        stage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
        stage.show();
        stage.setResizable(false);
    }
}
