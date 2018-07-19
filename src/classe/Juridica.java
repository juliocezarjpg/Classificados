/*
 * Projeto de redes de Computadores turma 2018.1.
 * Diciplina POO Java.
 * Professor Francisco Petrônio.
 * Classe de Pessoa jurídica
 */
package classe;

import java.io.Serializable;
import java.util.Objects;
/**
 * @author Joselito Muniz
 * @author Lucas Fernandes
 * @author Julio Cezar
 */
public class Juridica extends Pessoa implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String cnpj;

    public Juridica(String cnpj, String nome, Endereco endereco, String email, String telefone, String usuario, String senha) {
        super(nome, endereco, email, telefone, usuario, senha);
        this.cnpj = cnpj;
    }



    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + Objects.hashCode(this.cnpj);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Juridica other = (Juridica) obj;
        return Objects.equals(this.cnpj, other.cnpj);
    }

    @Override
    public String toString() {
        return "Username: " + this.getUsuario() + " CNPJ: " + this.cnpj;
    }
  
}
