/*
 * Projeto de redes de Computadores turma 2018.1.
 * Diciplina POO Java.
 * Professor Francisco Petrônio.
 * Coleção de Pessoas
 */
package colecoes;
import classe.Endereco;
import classe.Pessoa;
import java.io.Serializable;
import java.util.ArrayList;
import com.thoughtworks.xstream.XStream; 
import com.thoughtworks.xstream.io.xml.StaxDriver; 
import com.thoughtworks.xstream.security.AnyTypePermission; 
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
/**
 * @author Joselito Muniz
 * @author Lucas Fernandes
 * @author Julio Cezar
 */
public class Pessoas implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<Pessoa> pessoas = new ArrayList<>(); 
    /** 
     * Metodo para adicionar pessoa 
     * @param p 
     */ 
    public void adicionarPessoa (Pessoa p) { 
            pessoas.add(p); 
    } 
    /** 
    * Metodo para Pesquisar pelo usuario 
    * @param username 
    * @return 
    * @throws Exception 
    */ 
    public Pessoa pesquisaPeloUsername (String username) throws Exception{ 
        for (Pessoa p : pessoas) { 
            if (p.getUsuario().equals(username)) { 
                return p; 
            } 
        }		 
        throw new Exception("Usuario:" + username + " nao encontrado."); 
    } 
    /** 
     * Metodo para pesquisar pelo Email 
     * @param email 
     * @return 
     * @throws Exception 
     */ 
    public Pessoa pesquisaPeloEmail (String email) throws Exception { 
        for (Pessoa p : pessoas) { 
            if (p.getEmail().equals(email)) { 
                return p; 
            } 
        }		 
        throw new Exception("Usuario com Email:" + email + "n]ao encontrado."); 
    } 
    /** 
     * Metodo para Lista o usuarios 
     */ 
    public void listagemPessoas (){          
            for (Pessoa p : pessoas) { 
            System.out.println(p); 
        } 
    } 
    /** 
     * Metodo para remover pessoas 
     */ 
    public boolean removePeloUsername (String username) { 
        try{ 
            if (pesquisaPeloUsername(username) != null) { 
                pessoas.remove(pesquisaPeloUsername(username)); 
                return true; 
            }  
        }catch (Exception e) { 
            System.out.println(e.getMessage()); 
        } 
        return false;
    } 
    public void salvaEmXML(){ 
        XStream xStream = new XStream(new StaxDriver()); 
        xStream.alias("pessoa", Pessoa.class); 
        xStream.alias("endereco", Endereco.class); 
        xStream.alias("listaPessoas", ArrayList.class); 

        File arquivo = new File("pessoas.xml"); 
        FileOutputStream gravar; 
         try { 
            gravar = new FileOutputStream(arquivo); 
            gravar.write(xStream.toXML(pessoas).getBytes()); 
            gravar.close(); 
        } catch (IOException ex) { 
        }  
    } 
    @SuppressWarnings("unchecked") 
    public void lerDoXML(){ 
        try { 
            XStream xStream = new XStream(new StaxDriver()); 
            //Questões de segurança 
            XStream.setupDefaultSecurity(xStream); 
            xStream.addPermission(AnyTypePermission.ANY);  
            xStream.alias("listaPessoas", ArrayList.class); 

            xStream.processAnnotations(Pessoa.class); 
            xStream.processAnnotations(Endereco.class); 
            try ( //xStream.processAnnotations(List.class);
                    BufferedInputStream input = new BufferedInputStream(new FileInputStream("pessoas.xml"))) {
                pessoas = (ArrayList<Pessoa>) xStream.fromXML(input);
                //System.out.println(lista2); 
            }
            //System.out.println(lista2);
//            for (Pessoa pessoa : pessoas) { 
//                System.out.println(pessoa); 
//            } 
        } catch (IOException ex) { 
        } 
    } 	 

    public int size() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
 }