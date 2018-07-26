/*
 * Projeto de redes de Computadores turma 2018.1.
 * Diciplina POO Java.
 * Professor Francisco Petrônio.
 * Classe Principal
 */
package principal;

import classe.Anuncio;
import classe.Endereco;
import classe.Fisica;
import classe.Juridica;
import classe.Pessoa;
import colecoes.Anuncios;
import colecoes.Pessoas;
import java.util.Scanner;
import java.net.*;
import java.io.*;

/**
 * @author Joselito Muniz
 * @author Lucas Fernandes
 * @author Julio Cezar 
 */
public class Classificados {
    /**
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        Pessoas col = new Pessoas(); //cole��o co1 do tipo Pessoas
        Anuncios colA = new Anuncios(); //cole��o colA do tipo Anuncios
        
        int porta = 1236; //por utilizada
        String host = "192.168.43.190"; //ip para o qual sera enviado os objetos atraves dos sockets       
        Socket s = new Socket(host,porta); //socket
		OutputStream  saida1 = s.getOutputStream(); //declarando um outputstream para enviar objetos para o socket
		ObjectOutputStream saida = new ObjectOutputStream(saida1); //passando objeto pelo socket
		      
        col.lerDoXML(); // salvando no XML
        colA.lerDoXML();      //Lendo do XML
            
        int op = 1;
        while(op != 0){ //La�o para o Menu principal enquanto o usuario n�o digitar 0 n�o saira do Menu
            print_menu(); //exibe o menu
            
            Scanner inteiro = new Scanner(System.in); //declara um Scanner
            op = leInteiro(inteiro); //ler a op��o desejada
            
            realizar_operacao(op, colA, col, saida); //case para execu��o de uma opera��o que sera digitada pelo usuario.
        }
    }
    /**
     * 
     * @param col
     * @param saida
     * @throws Exception
     * Metodos para o Menu principal
     * Metodo para cadastrar um cliente
     */
    public static void op1(Pessoas col, ObjectOutputStream saida) throws Exception{	
        Scanner corda = new Scanner(System.in);
        Scanner inteiro = new Scanner(System.in);
         
            int r = 0;
            int escolha = 0;
            String FouJ;
            String nome;
            String email;                
            String telefone;
            String usuario;
            String senha;
            
            String rua;
            String bairro;                
            String cidade;    
            String uf;
            
            boolean parar = false;
            while(parar != true) {
                System.out.println(":::::::::::::::::::::: Cadastrar Cliente :::::::::::::::::::::::::::");
                System.out.println("::                                                                ::");                
                System.out.println("::  Digite 1 para pessoa Fisica ou 2 para Juridica:               ::");
                escolha = leInteiro(inteiro);
                if (escolha == 1) 
                System.out.println("::  Digite o CPF:                                                 ::");
                else 
                	System.out.println("::  Digite o CNPJ:                                                ::");
                FouJ = leString(corda);
                System.out.println("::  Digite o nome:                                                ::");
                nome = leString(corda);
                System.out.println("::  Digite o e-mail:                                              ::");
                email = leString(corda);                
                System.out.println("::  Digite o telefone:                                            ::");
                telefone = leString(corda);                
                System.out.println("::  Digite o nome de usuário:                                     ::");
                usuario = leString(corda);                 
                System.out.println("::  Digite a senha:                                               ::");
                senha = leString(corda); 
                System.out.println(":::::::::::::::::::::: Cadastrar Endereco ::::::::::::::::::::::::::");
                System.out.println("::                                                                ::");                
                System.out.println("::  Digite a rua:                                                 ::");
                rua = leString(corda);
                System.out.println("::  Digite a barrio:                                              ::");
                bairro = leString(corda);                
                System.out.println("::  Digite a cidade:                                              ::");
                cidade = leString(corda); 
                System.out.println("::  Digite o UF:                                                  ::");
                uf = leString(corda);          
                System.out.println("::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::");                        
                
                Endereco end = new Endereco(rua, bairro, cidade, uf);
            switch (escolha) {
                case 1:
                    {
                        Fisica f = new Fisica(FouJ, nome, end, email, telefone, usuario, senha);
                        col.adicionarPessoa(f);
                        break;
                    }
                case 2:
                    {
                        Juridica f = new Juridica(FouJ, nome, end, email, telefone, usuario, senha);
                        col.adicionarPessoa(f);
                        break;
                    }
                default:
                    System.out.println("::  Digite 1 para CPF ou 2 para CNPJ:                                                ::");
                    escolha = leInteiro(inteiro);
                    System.out.println("::  Digite:                                                ::");
                    FouJ = leString(corda);
                    break;
            }
                System.out.println("::: Digite 1 para adicionar outro parciente ou 0 para voltar para o menu principal :::");
                
                r = leInteiro(inteiro);
                if(r == 0) break;
            }
            
            col.salvaEmXML(); //salva o objeto em XML
            try {
    			saida.writeObject(col); //manda objeto pelo socket
    			saida.flush();
    			Thread.sleep (1000);
            } catch (Exception e) {
            		System.out.println(e.getMessage());
            }
            
            try {
	            	saida.writeObject(col); //manda objeto pelo socket
	    			saida.flush();
	    			Thread.sleep (1000); 
            } catch (Exception e) {
            		System.out.println(e.getMessage());
            }
            
            
    }
   /**
    * Metodo para pesquisar o cliente pelo nome
    * @param col
    */
    public static void op2(Pessoas col){
        Scanner corda = new Scanner(System.in);
        System.out.println(":::::::::::::::::::: Pesquisa cliente pelo nome de usuário :::::::::::::::::::"); 
        System.out.println("::                                                                          ::");          
        System.out.println("::  Digite o username do usuário a ser pesquisado:                          ::");
        System.out.println("::                                                                          ::");       
        String username = leString(corda);
        try {
            Pessoa x = col.pesquisaPeloUsername(username);
            System.out.println(x);
        }catch(Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println(":::::::::::::::::::::::::::::::::::::::::::::::::::::::;;:::::::::::::::::::::");
        System.out.println("Pressione enter para voltar ao menu...");
        leString(corda);
    }
    
    /**
     * Metodo para persquisar o Cliente pelo e-mail
     * @param col
     */
    public static void op3(Pessoas col){
        Scanner corda = new Scanner(System.in);
        System.out.println("::::::::::::::::::::::::: Pesquisa cliente pelo e-mail :::::::::::::::::::::::"); 
        System.out.println("::                                                                          ::");          
        System.out.println("::  Digite o e-mail a ser pesquisado:                                       ::");
        System.out.println("::                                                                          ::");       
        String email = leString(corda);
        try {
            Pessoa x = col.pesquisaPeloEmail(email);
            System.out.println(x);
        }catch(Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println(":::::::::::::::::::::::::::::::::::::::::::::::::::::::;;:::::::::::::::::::::");
        System.out.println("Pressione enter para voltar ao menu...");
        leString(corda);
    }   
    
    /**
     * Metodo para listar todas as pessoas cadastradas
     * @param col
     */
    public static void op4(Pessoas col){
        Scanner corda = new Scanner (System.in);
    		System.out.println("::::::::::::::::::::::: Lista de pessoas cadastradas ::::::::::::::::::::::::::"); 
        System.out.println("::                                                                          ::");          
            col.listagemPessoas();
        System.out.println(":::::::::::::::::::::::::::::::::::::::::::::::::::::::;;:::::::::::::::::::::");
        System.out.println("Pressione enter para voltar ao menu...");
        leString(corda);
    }
    
    /**
     * Metodo para pesquisar pelo usuario
     * @param col
     */
    public static void op5(Pessoas col){
        Scanner corda = new Scanner(System.in);
        System.out.println("::::::::::::::::::::::: Informacoes da Pessoa  :::::::::::::::::::::::::::::::"); 
        System.out.println("::                                                                          ::");          
        System.out.println("::  Digite o username da pessoa:                                            ::");
        System.out.println("::                                                                          ::");       
        String username = leString(corda);
        try {
            Pessoa p = col.pesquisaPeloUsername(username);
            p.mostrarInformacoes();
        }catch(Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println(":::::::::::::::::::::::::::::::::::::::::::::::::::::::;;:::::::::::::::::::::");
        System.out.println("Pressione enter para voltar ao menu...");
        leString(corda);
    }
    
    /**
     * Metodo para cadastrar um anuncio
     * @param colA
     * @param col
     * @param saida
     * @throws Exception
     */
    public static void op6(Anuncios colA, Pessoas col, ObjectOutputStream saida) throws Exception{
        Scanner corda = new Scanner(System.in);
        Scanner flo = new Scanner(System.in);
            int r = 0;
            String anunciante;
            String nome;
            String tipo;
            String descricao;
            Float preco;
            
            boolean parar = false;
            while(parar != true) {
                System.out.println(":::::::::::::::::::::: Cadastrar anuncio :::::::::::::::::::::::::::");
                System.out.println("::                                                                 ::");                
                System.out.println("::  Digite o username do anunciante:                               ::");       
                anunciante = leString(corda);
                try {
	            		Pessoa ranunciante = col.pesquisaPeloUsername(anunciante);
	            		System.out.println("::  Digite o nome do produto:                                      ::");
	                nome = leString(corda);
	                System.out.println("::  Digite o tipo do anúncio:                                      ::");
	                tipo = leString(corda);                
	                System.out.println("::  Digite a descrição:                                            ::");
	                descricao = leString(corda);                
	                System.out.println("::  Digite o preço:                                                ::");
	                preco = leFloat(flo);                 
	                System.out.println(":::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::");                        
	  
	                Anuncio a = new Anuncio(ranunciante, nome, tipo, descricao, preco);
	                colA.adicionarAnuncio(a);
	                colA.salvaEmXML();
	                try {
	            		saida.writeObject(colA); //Manda objeto pelo socket
	        			saida.flush();
	        			Thread.sleep (1000);
	                } catch (Exception e) {
	                		System.out.println(e.getMessage());
	                }
	                System.out.println("::: Digite 1 para adicionar ou 0 para Menu :::");
	                Scanner inteiro = null;                
	                r = leInteiro(inteiro);
	                if(r == 0) break;
	            }                
	            catch (Exception e) {
	            		System.out.println(e.getMessage());
	            		break;
	            }
            }
    }
    
    /**
     * Metodo para pesquisar anuncio pelo anunciante
     * @param colA
     * @param col
     * @throws Exception
     */
    public static void op7(Anuncios colA, Pessoas col) throws Exception{
        Scanner corda = new Scanner(System.in);
        System.out.println(":::::::::::::::::: Pesquisar anúncio pelo anunciante :::::::::::::::::::::::::"); 
        System.out.println("::                                                                          ::");          
        System.out.println("::  Digite o username do anunciante:                                            ::");
        System.out.println("::                                                                          ::");       
        String username = leString(corda);
        try {
        		Pessoa p = col.pesquisaPeloUsername(username);
        		System.out.println(colA.pesquisaPeloAnunciante(p));
        }
        catch (Exception e) {
        		System.out.println(e.getMessage());
        }
        
        
        System.out.println(":::::::::::::::::::::::::::::::::::::::::::::::::::::::;;:::::::::::::::::::::");
        System.out.println("Pressione enter para voltar ao menu...");
        leString(corda);
    }
    
    /**
     * Metodo para pesquisar anuncio pelo produto
     * @param colA
     */
    public static void op8(Anuncios colA){
        Scanner corda = new Scanner(System.in);
        System.out.println(":::::::::::::::::::::: Pesquisar anúncio pelo produto ::::::::::::::::::::"); 
        System.out.println("::                                                                      ::");          
        System.out.println("::  Digite o nome do produto:                                           ::");
        System.out.println("::                                                                      ::");       
        String nome = leString(corda);
        try {
            System.out.println(colA.pesquisaPeloProduto(nome));
        }catch(Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println(":::::::::::::::::::::::::::::::::::::::::::::::::::;;:::::::::::::::::::::");
        System.out.println("Pressione enter para voltar ao menu...");
        leString(corda);
    }
    
    /**
     * Meto para pesquisar anuncio pelo pre�o
     * @param colA
     * @throws Exception
     */
    public static void op9(Anuncios colA) throws Exception{
        Scanner flo = new Scanner(System.in);
            System.out.println("::::::::::::::: Pesquisar anúncio por preço ::::::::::::::::::::::::");
            System.out.println("::                                                                ::");                
            System.out.println("::  Digite o preço que deseja para pesquisa:                      ::");
            float preco = leFloat(flo);
            System.out.println("::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::");
        try {
            System.out.println(colA.pesquisaPeloPreco(preco));
        }catch(Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println("Pressione enter para voltar ao menu...");
        leString(flo);
    }
    
    /**
     * Metodo para pesquisar anuncio pelo maior pre�o
     * @param colA
     */
    public static void op10(Anuncios colA){
        Scanner flo = new Scanner(System.in);
            System.out.println(":::::::::::: Pesquisar anúncio por preço maior :::::::::::::::::::::");
            System.out.println("::                                                                ::");                
            System.out.println("::  Digite o maior preço para pesquisa:                           ::");
            float preco = leFloat(flo);
            System.out.println("::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::");
        try {
            System.out.println(colA.pesquisaPeloPrecoMaior(preco));
        }catch(Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println("Pressione enter para voltar ao menu...");
        leString(flo);
    }
    
    /**
     * Metodo para pesquisar anuncio pelo menor pre�o
     * @param colA
     */
    public static void op11(Anuncios colA){
        Scanner flo = new Scanner(System.in);
            System.out.println(":::::::::::: Pesquisar anúncio por preço menor :::::::::::::::::::::");
            System.out.println("::                                                                ::");                
            System.out.println("::  Digite o menor preço para pesquisa:                           ::");
            float preco = leFloat(flo);
            System.out.println("::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::");
        try {
            System.out.println(colA.pesquisaPeloPrecoMenor(preco));
        }catch(Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println("Pressione enter para voltar ao menu...");
        leString(flo);
    }
    
    /**
     * Metdo para remover anuncio pelo produto e anunciante
     * @param colA
     * @param col
     * @param saida
     * @throws Exception
     */
    public static void op12(Anuncios colA, Pessoas col, ObjectOutputStream saida) throws Exception{
        Scanner corda = new Scanner(System.in);
        System.out.println(":::::::::::::::::: Remover anúncio pelo produto e anunciate ::::::::::::::::::"); 
        System.out.println("::                                                                          ::");          
        System.out.println("::  Digite o username do anúnciante:                                            ::");
        String nome = leString(corda);
        
        try {
        		Pessoa anunciante = col.pesquisaPeloUsername(nome);
        		try {
        			System.out.println("::  Digite o nome do produto:                                               ::");
        			String produto = leString(corda);   
        	        System.out.println(":::::::::::::::::::::::::::::::::::::::::::::::::::::::;;:::::::::::::::::::::");
        			colA.removePeloProdutoAnuciante(produto, anunciante);//remove anunciante pelo produto
        			colA.salvaEmXML();
        			try {
        	    		saida.writeObject(colA);//manda objeto pelo socket
        				saida.flush();
        				Thread.sleep (1000);
        	        } catch (Exception e) {
        	        		System.out.println(e.getMessage());
        	        }
        			System.out.println("Produto removido com sucesso!");
        		}catch(Exception e) {
        			System.out.println(e.getMessage());
        		}     		
        }catch(Exception e) {
        		System.out.println(e.getMessage());
        }
        System.out.println("Pressione enter para voltar ao menu...");
        leString(corda);
    }
    
    /**
     *Metodo que mostra os anuncios cadastrados 
     * @param colA
     */
    public static void op13(Anuncios colA){
        Scanner corda = new Scanner (System.in);
    		System.out.println("::::::::::::::::::::::: Lista de anuncios cadastradas ::::::::::::::::::::::::::"); 
        System.out.println("::                                                                          ::");          
        System.out.println(colA);
        System.out.println(":::::::::::::::::::::::::::::::::::::::::::::::::::::::;;:::::::::::::::::::::");
        System.out.println("Pressione enter para voltar ao menu...");
        leString(corda);
    }
    
    /**
     * Métudos para garantir a leitura de um inteiro
     * @param in
     * @return 
     */
    private static int leInteiro(Scanner in) {
        while(!in.hasNextInt()) {
                in.nextLine();
                System.out.print("Tipo de dado inválido. Digite um inteiro: ");
        }
        int r = in.nextInt();
        in.nextLine(); 
        return r;
    }
    
    /**
     * metodo que garante a leitura de uma string
     * @param strin
     * @return
     */
    private static String leString(Scanner strin) {
        String r = strin.nextLine();
        return r;
    }
    
    /**
     * Metodo que garante a leitura de um float
     * @param flo
     * @return
     */
    private static float leFloat(Scanner flo){
		while(!flo.hasNextFloat())
                {
			flo.nextLine();
			System.out.print("Tipo de dado inválido. Digite um valor real: ");
		}
		float r = flo.nextFloat();
		flo.nextLine();
		return r;
    }
    
    
    /**
     * Menu principal
     */
    private static void print_menu() {
    	System.out.println("::::::::::::::::::::::::::::: MENU ::::::::::::::::::::::::::::::::::");
        System.out.println("::                                                                 ::");
        System.out.println("::   1 ->> Cadastrar pessoa                                        ::");
        System.out.println("::   2 ->> Pesquisa pessoa pelo nome de usuario                    ::");
        System.out.println("::   3 ->> Pesquisa pessoa pelo e-mail                             ::");
        System.out.println("::   4 ->> Listar pessoas cadastrados                              ::");
        System.out.println("::   5 ->> Obter informacoes de uma pessoa cadastrada              ::");
        System.out.println("::                                                                 ::"); 
        System.out.println("::   6 ->> Cadastrar anuncio                                       ::");
        System.out.println("::   7 ->> Pesquisar anuncio pelo anunciante                       ::");
        System.out.println("::   8 ->> Pesquisar anuncio pelo produto                          ::");
        System.out.println("::   9 ->> Pesquisar anuncio por preco                             ::");
        System.out.println("::   10 ->> Pesquisar anuncio por preco maior                      ::");
        System.out.println("::   11 ->> Pesquisar anuncio por preco menor                      ::");
        System.out.println("::   12 ->> Remover anuncio pelo produto e anunciate               ::");
        System.out.println("::   13 ->> Listar todos os anuncios                               ::");
        System.out.println("::                                                                 ::");      
        System.out.println("::   0 ->> Para sair                                               ::");
        System.out.println("::                                                                 ::");
        System.out.println(":::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::");
    }
    
    /**
     * Case que receberar um inteiro e executara a fun��o indicada pelo valor
     * @param op
     * @param colA
     * @param col
     * @param saida
     * @throws Exception
     */
    private static void realizar_operacao(int op, Anuncios colA, Pessoas col, ObjectOutputStream saida) throws Exception {
    	switch(op){
        case 1: op1(col, saida);
        break;
        case 2: op2(col);
        break;
        case 3: op3(col);
        break;
        case 4: op4(col);
        break;
        case 5: op5(col);
        break;
        case 6: op6(colA, col, saida);
        break;
        case 7: op7(colA, col);
        break;
        case 8: op8(colA);
        break;
        case 9: op9(colA);
        break;
        case 10: op10(colA);
        break;
        case 11: op11(colA);
        break;
        case 12: op12(colA, col, saida);
        break;
        case 13: op13(colA);
        break;
        default:
        break;
    }	
    }
}
