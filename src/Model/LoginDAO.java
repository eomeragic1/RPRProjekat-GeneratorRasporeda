package Model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.*;
import java.util.Scanner;

public class LoginDAO {
    private static LoginDAO instance = null;
    private Connection conn;

    private PreparedStatement upitKorisnik, upitDodajKorisnika,upitDajKorisnikID;
    private LoginDAO() {
        try {
            conn = DriverManager.getConnection("jdbc:sqlite:GeneratorRasporeda.db");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            upitKorisnik = conn.prepareStatement("SELECT lozinka FROM korisnici " +
                    "WHERE korisnicko_ime=?");
        } catch (SQLException e) {
            kreirajBazu();
            try {
                upitKorisnik = conn.prepareStatement("SELECT lozinka FROM korisnici " +
                        "WHERE korisnicko_ime=?");
            } catch (SQLException f) {
                f.printStackTrace();
            }
        }
        try {
            upitDodajKorisnika =conn.prepareStatement("INSERT INTO korisnici VALUES (?,?,?,?,?,?,?)");
            upitDajKorisnikID=conn.prepareStatement("SELECT MAX(id)+1 FROM korisnici");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void kreirajBazu() {
        Scanner ulaz = null;
        try {
            ulaz = new Scanner(new FileInputStream("korisnici.sql"));
            String sqlUpit = "";
            while (ulaz.hasNext()) {
                sqlUpit += ulaz.nextLine();
                if (sqlUpit.length() > 1 && sqlUpit.charAt(sqlUpit.length() - 1) == ';') {
                    try {
                        Statement stmt = conn.createStatement();
                        stmt.execute(sqlUpit);
                        sqlUpit = "";
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }

                }
            } // ...
            ulaz.close();
        } catch (FileNotFoundException e) {
            System.out.println("Ne postoji SQL datoteka... nastavljam sa praznom bazom");
        }
    }


    public static LoginDAO getInstance() {
        if (instance == null)
            instance = new LoginDAO();
        return instance;
    }

    public static void removeInstance() {
        try {
            if (instance != null)
                instance.conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        instance = null;
    }
    public String dajLozinku(String korisnickoIme) throws UserNotFoundException {
        try{
            upitKorisnik.setString(1,korisnickoIme);
            ResultSet rs = upitKorisnik.executeQuery();
            if (!rs.next()) throw new UserNotFoundException("Korisnik ne postoji!");
            return rs.getString(1);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    public boolean daLiPostojiKorisnik(String korisnickoIme) {
        try {
            upitKorisnik.setString(1,korisnickoIme);
            ResultSet rs = upitKorisnik.executeQuery();
            if (!rs.next()) return false;
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return true;
        }
    }
    public void dodajKorisnika(Korisnik k) {
        try {
            upitDodajKorisnika.setInt(1,dajMaxID());
            upitDodajKorisnika.setString(2,k.getKorisnickoIme());
            upitDodajKorisnika.setString(3,k.getLozinka());
            upitDodajKorisnika.setInt(4,k.getTip());
            upitDodajKorisnika.setString(5,k.getIme());
            upitDodajKorisnika.setString(6,k.getPrezime());
            upitDodajKorisnika.setString(7,k.getJmbg());
            upitDodajKorisnika.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public int dajMaxID() {
        try {
            ResultSet rs = upitDajKorisnikID.executeQuery();
            rs.next();
            return rs.getInt(1);
        }
        catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }
}
