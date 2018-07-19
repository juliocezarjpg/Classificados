/*
 * Projeto de redes de Computadores turma 2018.1.
 * Diciplina POO Java.
 * Professor Francisco Petrônio.
 * Classe de Pessoa
 */
package classe;

import java.io.Serializable;
import java.util.Objects;
/**
 * @author Joselito Muniz
 * @author Lucas Fernandes
 * @author Julio Cezar 
 */
public class Pessoa implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String nome;
    private Endereco endereco;
    private String email;
    private String telefone;
    private String usuario;
    private String senha;        

    public Pessoa(String nome, Endereco endereco, String email, String telefone, String usuario, String senha) {
        this.nome = nome;
        this.endereco = endereco;
        this.email = email;
        this.telefone = telefone;
        this.usuario = usuario;
        this.senha = senha;
    }

    public Pessoa(boolean selected, String text, Endereco end, String text0, String text1, String text2, String text3) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }



    @Override
    public int hashCode() {
        int hash = 5;
        hash = 17 * hash + Objects.hashCode(this.nome);
        hash = 17 * hash + Objects.hashCode(this.endereco);
        hash = 17 * hash + Objects.hashCode(this.email);
        hash = 17 * hash + Objects.hashCode(this.telefone);
        hash = 17 * hash + Objects.hashCode(this.usuario);
        hash = 17 * hash + Objects.hashCode(this.senha);
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
        final Pessoa other = (Pessoa) obj;
        if (!Objects.equals(this.nome, other.nome)) {
            return false;
        }
        if (!Objects.equals(this.endereco, other.endereco)) {
            return false;
        }
        if (!Objects.equals(this.email, other.email)) {
            return false;
        }
        if (!Objects.equals(this.telefone, other.telefone)) {
            return false;
        }
        if (!Objects.equals(this.usuario, other.usuario)) {
            return false;
        }
        return Objects.equals(this.senha, other.senha);
    }

    @Override
    public String toString() {
        return "Pessoa{" + "nome=" + nome + ", endereco=" + endereco + ", email=" + email + ", telefone=" + telefone + ", usuario=" + usuario + ", senha=" + senha + '}';
    } 
    
    public void mostrarInformacoes() {
    		System.out.println(toString() + "nome=" + nome + ", endereco=" + endereco + ", email=" + email + ", telefone=" + telefone + ", usuario=" + usuario + ", senha=" + senha);
    }
}
