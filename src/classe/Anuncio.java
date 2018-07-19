/*
 * Projeto de redes de Computadores turma 2018.1.
 * Diciplina POO Java.
 * Professor Francisco Petrônio.
 * Classe de Anúncio
 */
package classe;

import java.awt.image.BufferedImage;
import java.io.Serializable;
import java.util.Objects;
/**
 * @author Joselito Muniz
 * @author Lucas Fernandes
 * @author Julio Cezar
 */
public class Anuncio implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Pessoa anunciante;
    private String nome;
    private String tipo;
    private String descricao;
    private Float preco;
    private BufferedImage imagem;

    public Anuncio() {
    }

    public Anuncio(Pessoa anunciante, String nome, String tipo, String descricao, Float preco) {
        this.anunciante = anunciante;
        this.nome = nome;
        this.tipo = tipo;
        this.descricao = descricao;
        this.preco = preco;
    }

    public Anuncio(Pessoa anunciante, String nome, String descricao, String tipo, Float preco, BufferedImage imagem) {
        this.anunciante = anunciante;
        this.nome = nome;
        this.descricao = descricao;
        this.tipo = tipo;
        this.preco = preco;
        this.imagem = imagem;
    }

    public Pessoa getAnunciante() {
        return anunciante;
    }

    public void setAnunciante(Pessoa anunciante) {
        this.anunciante = anunciante;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Float getPreco() {
        return preco;
    }

    public void setPreco(Float preco) {
        this.preco = preco;
    }

    public BufferedImage getImagem() {
        return imagem;
    }

    public void setImagem(BufferedImage imagem) {
        this.imagem = imagem;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.anunciante);
        hash = 53 * hash + Objects.hashCode(this.nome);
        hash = 53 * hash + Objects.hashCode(this.descricao);
        hash = 53 * hash + Objects.hashCode(this.tipo);
        hash = 53 * hash + Objects.hashCode(this.preco);
        hash = 53 * hash + Objects.hashCode(this.imagem);
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
        final Anuncio other = (Anuncio) obj;
        if (!Objects.equals(this.nome, other.nome)) {
            return false;
        }
        if (!Objects.equals(this.descricao, other.descricao)) {
            return false;
        }
        if (!Objects.equals(this.tipo, other.tipo)) {
            return false;
        }
        if (!Objects.equals(this.preco, other.preco)) {
            return false;
        }
        if (!Objects.equals(this.anunciante, other.anunciante)) {
            return false;
        }
        return Objects.equals(this.imagem, other.imagem);
    }

    @Override
    public String toString() {
        return "Anuncio{" + "anunciante=" + anunciante + ", nome=" + nome + ", descricao=" + descricao + ", tipo=" + tipo + ", preco=" + preco + ", imagem=" + imagem + '}';
    }
   
}
