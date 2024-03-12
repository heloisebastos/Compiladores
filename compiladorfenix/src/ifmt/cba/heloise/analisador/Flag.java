package ifmt.cba.heloise.analisador;

public enum Flag {

    TOKENS("-It", false),
    SINTATICO("-ls", false),
    SEMANTICO("-lse", false),
    CODIGO_FINAL("-fim",false),
    TODOS("-todos", false);


   // private String value;
    private Boolean status;

    Flag(String value, boolean status) {
        //this.value = value;
        this.status = status;
    }

    public Boolean getStatus() {
        return this.status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}
