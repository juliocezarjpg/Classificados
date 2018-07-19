/*
 * Projeto de redes de Computadores turma 2018.1.
 * Diciplina POO Java.
 * Professor Francisco Petrônio.
 * Coleção de Anuncios
 */
package colecoes;
import classe.Anuncio;
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
public class Anuncios implements Serializable {    
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<Anuncio> anuncios = new ArrayList<>();
	/**
	 * Metodo para adicionar um anuncio
	 * @param a
	 */ 
	public void adicionarAnuncio (Anuncio a) {
            anuncios.add(a);
	}
	/**
         * Metodo para pesquisar um anunciante
         * @param anunciante
	 * @ param anunciante
	 * @return
	 * @throws Exception
	 */
        public  Anuncio  pesquisaPeloAnunciante (Pessoa anunciante) throws  Exception{
            for (Anuncio a : anuncios) {
                if (a.getAnunciante().equals(anunciante)) {
                    return a;
                }
            }		
            throw  new  Exception( " Anuncio não encontrado " );
	}    
         /**
	 * Metodo paravey by Produto
	 * @param nome
	 * @return
	 * @throws Exception
	 */
	public Anuncio pesquisaPeloProduto(String produto)throws Exception{
            for(Anuncio a : anuncios) {
                if (a.getNome().equals(produto)){
                    return a;
                }
            }		
            throw  new  Exception(" Produto não encontrado ");
	}        
	/**
	 * Metodo para um produto pelo preço
	 * @param preco
	 * @return
	 * @throws Exception
	 */
	public Anuncio pesquisaPeloPreco(Float  preco) throws  Exception{
            for (Anuncio a : anuncios) {
                if (a.getPreco().equals(preco)) {
                    return a;
                }
            }			
            throw  new  Exception(" não há produtos com esse preço");
	}      
        /**
	 * Metodo para pesquisar os maiores preços
	 * @param preco
	 * @return
	 */
	public ArrayList< Anuncio > pesquisaPeloPrecoMaior(Float preco ){		
            ArrayList< Anuncio > maiores =  new ArrayList<>();
                for (Anuncio a : anuncios) {
                    if (a.getPreco() >= preco) {
                        maiores.add(a);
                    }
                }
                return maiores;
	}       
 	/**
	 * Metodo para pesquisar os menores preços
	 * @param preco
	 * @return
	 */
        public ArrayList< Anuncio > pesquisaPeloPrecoMenor(Float preco){		
            ArrayList<Anuncio> menor = new ArrayList<>();		
                for (Anuncio a : anuncios) {
                    if (a.getPreco() <= preco) {
                        menor.add(a);
                    }
                }
                return menor;
	}       
        /** 
 	 * Metodo para remover um produto pelo anunciante 
 	 * @param produto 
 	 * @param anunciante 
 	 * @return 
 	 * @throws Exception 
 	 */ 
 	public boolean removePeloProdutoAnuciante(String produto, Pessoa anunciante) throws Exception{ 
            Anuncio a; 
            try { 
                a = pesquisaPeloProduto(produto); 
            }catch(Exception e) { 
                throw new Exception(e.getMessage()); 
            } 
                if (a != null) { 
                    if(a.getAnunciante().equals(anunciante)) { 
                        anuncios.remove(a); 
                    } 
                } 
            return false; 
 	}      
	@Override 
 	public String toString() { 
            return "ColecaoAnuncio [anuncios=" + anuncios + "]"; 
 	} 
 	public void salvaEmXML(){ 
            XStream xStream = new XStream(new StaxDriver()); 
            xStream.alias("pessoa", Pessoa.class); 
            xStream.alias("endereco", Endereco.class); 
            xStream.alias("listaAnuncios", ArrayList.class); 
   
         File arquivo = new File("anuncios.xml"); 
         FileOutputStream gravar; 
            try { 
                gravar = new FileOutputStream(arquivo); 
                gravar.write(xStream.toXML(anuncios).getBytes()); 
                gravar.close(); 
            } catch (IOException ex) { 
                ex.printStackTrace(); 
            }  
 	} 
 	@SuppressWarnings("unchecked") 
 	public void lerDoXML(){ 
            try { 
                XStream xStream = new XStream(new StaxDriver()); 
                //Questões de segurança 
                XStream.setupDefaultSecurity(xStream); 
                xStream.addPermission(AnyTypePermission.ANY);  
                xStream.alias("listaAnuncios", ArrayList.class); 

                xStream.processAnnotations(Pessoa.class); 
                xStream.processAnnotations(Endereco.class); 
                BufferedInputStream input = new BufferedInputStream(new FileInputStream("anuncios.xml")); 
                anuncios = (ArrayList<Anuncio>) xStream.fromXML(input); 

                input.close(); 
                for (Anuncio anuncio : anuncios) { 
                        System.out.println(anuncio); 
                } 

            } catch (IOException ex) { 
                    ex.printStackTrace(); 
            } 
 	}  	 
 } 