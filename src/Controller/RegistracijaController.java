package Controller;

import Model.Korisnik;
import Model.LoginDAO;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.stage.Window;

import java.io.IOException;

public class RegistracijaController {
    public Button btnRegistracija;
    public Label labelGreska;
    public TextField fldJmbg;
    public PasswordField fldLozinkaRepeat;
    public PasswordField fldLozinka;
    public TextField fldPrezime;
    public TextField fldIme;
    public RadioButton radioStudent;
    public RadioButton radioProfesor;

    public RegistracijaController() {}
    @FXML
    public void initialize() {
        final ToggleGroup group = new ToggleGroup();
        radioProfesor.setSelected(true);
        radioProfesor.setToggleGroup(group);
        radioStudent.setToggleGroup(group);

        fldIme.textProperty().addListener((obs,oldime,newime)-> {
            clearAllTags();
        });
        fldPrezime.textProperty().addListener((obs,oldime,newime)-> {
            clearAllTags();
        });
        fldJmbg.textProperty().addListener((obs,oldime,newime)-> {
            clearAllTags();
        });
        fldLozinka.textProperty().addListener((obs, oldIme, newIme) -> {
            clearAllTags();
            if (!newIme.isEmpty()) {
                if (newIme.equals((String) fldLozinkaRepeat.textProperty().get()) && isPasswordValid(newIme)) {
                    fldLozinka.getStyleClass().removeAll("poljeNijeIspravno");
                    fldLozinka.getStyleClass().add("poljeIspravno");
                    fldLozinkaRepeat.getStyleClass().removeAll("poljeNijeIspravno");
                    fldLozinkaRepeat.getStyleClass().add("poljeIspravno");
                } else {
                    fldLozinka.getStyleClass().removeAll("poljeIspravno");
                    fldLozinka.getStyleClass().add("poljeNijeIspravno");
                    fldLozinkaRepeat.getStyleClass().removeAll("poljeIspravno");
                    fldLozinkaRepeat.getStyleClass().add("poljeNijeIspravno");
                }
            } else {
                fldLozinka.getStyleClass().removeAll("poljeIspravno");
                fldLozinka.getStyleClass().add("poljeNijeIspravno");
            }
        });
        fldLozinkaRepeat.textProperty().addListener((obs, oldPassword, newPassword) -> {
            clearAllTags();
            if (!newPassword.isEmpty()) {
                if (newPassword.equals(fldLozinka.textProperty().get()) && isPasswordValid(newPassword)) {
                    fldLozinkaRepeat.getStyleClass().removeAll("poljeNijeIspravno");
                    fldLozinkaRepeat.getStyleClass().add("poljeIspravno");
                    fldLozinka.getStyleClass().removeAll("poljeNijeIspravno");
                    fldLozinka.getStyleClass().add("poljeIspravno");
                } else {
                    fldLozinka.getStyleClass().removeAll("poljeIspravno");
                    fldLozinka.getStyleClass().add("poljeNijeIspravno");
                    fldLozinkaRepeat.getStyleClass().removeAll("poljeIspravno");
                    fldLozinkaRepeat.getStyleClass().add("poljeNijeIspravno");
                }
            } else {
                fldLozinkaRepeat.getStyleClass().removeAll("poljeIspravno");
                fldLozinkaRepeat.getStyleClass().add("poljeNijeIspravno");
            }

        });
    }
    public void handleRegistracija(ActionEvent actionEvent) {
        if (!isImeValid(fldIme.textProperty().get())) {
            labelGreska.setTextFill(Color.LIGHTPINK);
            labelGreska.setText("Ime nije validno!");
            fldIme.getStyleClass().add("poljeNijeIspravno");
        }
        else if (!isImeValid(fldPrezime.textProperty().get())) {
            labelGreska.setTextFill(Color.LIGHTPINK);
            labelGreska.setText("Prezime nije validno!");
            fldPrezime.getStyleClass().add("poljeNijeIspravno");
        }
        else if (!isJMBGValid(fldJmbg.textProperty().get())) {
            labelGreska.setTextFill(Color.LIGHTPINK);
            labelGreska.setText("JBMG nije validan!");
            fldJmbg.getStyleClass().add("poljeNijeIspravno");
        }
        else if (!isPasswordValid(fldLozinka.textProperty().get()) || !fldLozinka.textProperty().get().equals(fldLozinkaRepeat.textProperty().get())) {
            labelGreska.setTextFill(Color.LIGHTPINK);
            labelGreska.setText("Lozinka nije validna!");
        }
        else {
            LoginDAO dao = LoginDAO.getInstance();
            Korisnik k;
            if (radioStudent.isSelected())
                k = new Korisnik(fldIme.textProperty().get(),fldPrezime.textProperty().get(),fldJmbg.textProperty().get(),generisiUsername(),fldLozinka.textProperty().get(),1);
            else
                k = new Korisnik(fldIme.textProperty().get(),fldPrezime.textProperty().get(),fldJmbg.textProperty().get(),generisiUsername(),fldLozinka.textProperty().get(),2);
            dao.dodajKorisnika(k);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Obavještenje");
            alert.setHeaderText(null);
            alert.setContentText("Registracija uspjesna! Vase korisnicko ime je: "+k.getKorisnickoIme());
            alert.show();
            alert.setOnHiding(dialogEvent -> {
                Node source = (Node) actionEvent.getSource();
                Window theStage = source.getScene().getWindow();
                theStage.hide();

            });

        }
    }
    private boolean isImeValid(String ime) {
        return ime.length() >= 3 && ime.matches("[A-Za-z-\\s]+");
    }
    private boolean isPasswordValid(String password) {
        return password.length()>=8;
    }
    private boolean isJMBGValid(String jmbg) {
        return jmbg.length()==13;
    }

    private String generisiUsername() {
        LoginDAO dao= LoginDAO.getInstance();
        String newUsername = new String("");
        newUsername = newUsername + prevediString(fldIme.textProperty().get().toLowerCase()).charAt(0);
        newUsername = newUsername + prevediString(fldPrezime.textProperty().get().toLowerCase());
        int number=0;
        newUsername=newUsername+number;
        do {
            newUsername = newUsername.substring(0,newUsername.length()-1);
            number++;
            newUsername=newUsername+number;
        }while(dao.daLiPostojiKorisnik(newUsername));
        return newUsername;
    }
    private String prevediString(String s) {
        String s2 = new String("");
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == 'ć' || s.charAt(i) == 'č')
                s2 = s2 + 'c';
            else if (s.charAt(i) == 'š')
                s2 = s2 + 's';
            else if (s.charAt(i) == 'đ')
                s2 = s2 + 'd';
            else if (s.charAt(i) == 'ž')
                s2 = s2 + 'z';
            else
                s2 = s2 + s.charAt(i);
        }
        return s2;
    }
    public void clearAllTags() {
        fldLozinka.getStyleClass().removeAll("poljeNijeIspravno");
        fldIme.getStyleClass().removeAll("poljeNijeIspravno");
        fldPrezime.getStyleClass().removeAll("poljeNijeIspravno");
        fldJmbg.getStyleClass().removeAll("poljeNijeIspravno");
        fldLozinkaRepeat.getStyleClass().removeAll("poljeNijeIspravno");
    }
}
