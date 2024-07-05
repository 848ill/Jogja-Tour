package AppProto;

import java.time.LocalDate;

public class RegUser {
    private String nama;
    private LocalDate birthDate;
    private String email;
    private String password;

    public RegUser(String nama, LocalDate birthDate, String email, String password) {
        this.nama = nama;
        this.birthDate = birthDate;
        this.email = email;
        this.password =  password;
    }

    public String getNama() {
        return nama;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password =  password;
    }

}
