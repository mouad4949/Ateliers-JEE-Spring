package ma.fstt.entities;



public class Client {
    private String id;
    private  String nom;
    private String email;
    private String address;
    private String telephone;

    public Client(String id, String nom, String email, String address, String telephone) {
        this.id = id;
        this.nom = nom;
        this.email = email;
        this.address = address;
        this.telephone = telephone;
    }

    public Client() {
    }

    public String getNom() {
        return nom;
    }

    public String getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public Client(String nom, String email, String address, String telephone) {
        this.nom = nom;
        this.email = email;
        this.address = address;
        this.telephone = telephone;
    }
}
