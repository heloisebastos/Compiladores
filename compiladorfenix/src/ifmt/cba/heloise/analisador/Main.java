
package ifmt.cba.heloise.analisador;

import ifmt.cba.heloise.codigofinal.Final;
import ifmt.cba.heloise.codigofinal.Intermediario;

import java.io.IOException;

import ifmt.cba.heloise.codigofinal.TratamentoException;

public class Main{
    public static void main(String[] args) throws TratamentoException, IOException {
        new FlagStatus().verificaTk(args);

        String path = new FlagStatus().validatePath(args);

        var listaDeTokens = new Lexico().splitTk(new Arquivo().lerAquivo(path));
        System.out.println("Analise Léxica Concluida\n");

         new Sintatico(listaDeTokens).AnalisadorSintatico();
         System.out.println("Analise Sintática Concluida\n");

         var Semantico = new Semantico(listaDeTokens);
         Semantico.analise();
         System.out.println("Analise Semantica concluida");

         var intermed = new Intermediario(listaDeTokens,Semantico.getDeclarados());
         var listIntermed = intermed.geradorcodigo();

         var codigoFinal = new Final();
         var compilador= codigoFinal.codFinal(listIntermed);
         new Arquivo().criarArquivo(compilador);
    }
}


