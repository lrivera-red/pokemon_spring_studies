package usr.lrivera.pokemonestudo;

public class FormErrorsDTO {

    private String campo;
    private String erro;

    public FormErrorsDTO(String campo, String erro) {
        this.campo = campo;
        this.erro = erro;
    }

    public String getCampo() {
        return campo;
    }

    public String getErro() {
        return erro;
    }
}
