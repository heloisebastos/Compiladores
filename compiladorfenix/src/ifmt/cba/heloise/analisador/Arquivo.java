package ifmt.cba.heloise.analisador;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Arquivo {

    public List<String> lerAquivo(String url) {
        //esta classe cria uma variavel para ler o arquivo a partir do seu endereco
        //e vai colocar o codigo em posicao na matriz de string para ser organizado e verificado
        List<String> codigo = new ArrayList<>();
        try (var arquivo = new BufferedReader(new FileReader(url))) {
            while (arquivo.ready()) {
                codigo.add(arquivo.readLine());
            }

        } catch (FileNotFoundException e) {
           System.err.println("Arquivo não foi encontrado " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Não foi possível ler o arquivo" + e.getMessage());
        }
        return codigo;
    }

    public void criarArquivo(String codigo) throws IOException {

        File file = new File ("C:\\Users\\heloi\\Downloads\\compilador\\compiladorfenix\\src\\ifmt\\cba\\heloise\\compilanasm\\arquivo.asm");
        //("C:\\Users\\heloi\\Downloads\\compilador\\compiladorfenix\\src\\ifmt\\cba\\heloise\\analisador\\triangulo.asm");
        file.createNewFile();
        FileWriter fw = new FileWriter(file.getAbsolutePath());
        var bw = new BufferedWriter(fw);

        bw.write(codigo);
        bw.close();
    } 

}