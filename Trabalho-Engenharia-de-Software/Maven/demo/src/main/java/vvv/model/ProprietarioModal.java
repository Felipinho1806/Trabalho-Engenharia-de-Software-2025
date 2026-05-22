package vvv.model;

public class ProprietarioModal {
    private String email;
    private String telefone;

    public ProprietarioModal(String email, String telefone){
        this.email = email;
        this.telefone = telefone;
    }

    public ProprietarioModal(){

    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}

