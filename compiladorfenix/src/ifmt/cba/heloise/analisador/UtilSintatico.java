package ifmt.cba.heloise.analisador;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.Arrays.asList;

public class UtilSintatico {

    public static Map<String, Integer> Terminal;
    public static Map<String, Integer> naoTerminal;
    public static Map<String, String> palavraChave;

    static {
        Terminal = new HashMap<>(); // horizontal

        //tokens
        Terminal.put("$", 0);
        Terminal.put("inicio_pgm", 1);
        Terminal.put("fim_pgm", 2);
        Terminal.put("leia", 3);
        Terminal.put("escreva", 4);
        Terminal.put("inicio_condicional", 5);
        Terminal.put("se_nao", 6);
        Terminal.put("end_if", 7);
        Terminal.put("inicio_repeticao", 8);
        Terminal.put("fimrepeticao", 9);
        Terminal.put("tipo", 10);
        Terminal.put("string", 11);
        Terminal.put("num", 12);
        Terminal.put("variavel", 13);
        Terminal.put("=", 14);
        Terminal.put("(", 15);
        Terminal.put(")", 16);
        Terminal.put(";", 17);
        Terminal.put("+", 18);
        Terminal.put("-", 19);
        Terminal.put("*", 20);
        Terminal.put("/", 21);
        Terminal.put("<", 22); 
        Terminal.put(">", 23);
        Terminal.put("|",24); //op logico igual
        Terminal.put("!",25); //operador logico diferente



        naoTerminal = new HashMap<>(); // vertical
        naoTerminal.put("PROGRAM", 0);
        naoTerminal.put("COMANDO_LIST", 1);
        naoTerminal.put("COMANDO", 2);
        naoTerminal.put("WRITE", 3);
        naoTerminal.put("READ", 4);
        naoTerminal.put("DECLARATION", 5);
        naoTerminal.put("ATRIBUICAO", 6);
        naoTerminal.put("UMA_EXPRESSION", 7);
        naoTerminal.put("EXPRESSAO", 8);
        naoTerminal.put("TERM", 9);
        naoTerminal.put("TERM_TAIL", 10);
        naoTerminal.put("SOMA_SUBTRACAO", 11);
        naoTerminal.put("SOMA", 12);
        naoTerminal.put("SUBTRACAO", 13);
        naoTerminal.put("FACTOR", 14);
        naoTerminal.put("FACTOR_TAIL", 15);
        naoTerminal.put("MULT_DIV", 16);
        naoTerminal.put("MULT", 17);
        naoTerminal.put("DIV", 18);
        naoTerminal.put("LOGICAL_OPERATOR",19);
        naoTerminal.put("LOGICAL_EXPRESSION",20);
        naoTerminal.put("CONDITIONAL",21);
        naoTerminal.put("LOOP",22);
        naoTerminal.put("WHILE",23);
        naoTerminal.put("IF",24);
        naoTerminal.put("ELSE",25);

    }


    public static Map<String, Integer> getTerminal() {
        return Terminal;
    }

    public static Map<String, Integer> getNaoTerminal() {
        return naoTerminal;
    }

    public static List<List<String>> getRegrasProducao() {

        return asList(
                //0 - <PROGRAM> ::= inicio_pgm <COMANDO_LIST> fim_pgm
                asList("inicio_pgm","COMANDO_LIST", "fim_pgm"),
                //1 -  <PROGRAM> ::= î
                List.of(),
                //2 -  	<COMANDO_LIST> ::= <COMANDO> <COMANDO_LIST>
                asList("COMANDO","COMANDO_LIST"),
                //3 -  	<COMANDO_LIST> ::= î
                List.of(),
                //4 -  	<COMANDO> ::= <WRITE>
                asList("WRITE"),
                //5 -  	<COMANDO> ::= <READ>
                asList("READ"),
                // 6 -  <COMANDO> ::= <DECLARATION>
                asList("DECLARATION"),
                //7 -  <COMANDO> ::= <ATRIBUICAO>
                asList("ATRIBUICAO"),
                //8 -  	<COMANDO> ::= <CONDITIONAL>
                asList("CONDITIONAL"),
                //9 -  	<COMANDO> ::= <LOOP>
                asList("LOOP"),
                //10 -  <WRITE> ::= escreva "(" <EXPRESSAO> ")" ";"
                asList("escreva","(","EXPRESSAO",")",";"),
                //11 -  	<READ> ::= leia "(" variavel ")" ";"
                asList("leia","(","variavel",")",";"),
                // 12 -  	<DECLARATION> ::= tipo <ATRIBUICAO>
                asList("tipo","ATRIBUICAO"),
                //13 - 	<ATRIBUICAO> ::= variavel <UMA_EXPRESSION>
                asList("variavel","UMA_EXPRESSION"),
                //14 - 	<UMA_EXPRESSION> ::= "=" <EXPRESSAO> ";"
                asList("=","EXPRESSAO",";"),
                //15 -  	<UMA_EXPRESSION> ::= î
                List.of(),
                //  16 -  	<EXPRESSAO> ::= <TERM> <TERM_TAIL>
                asList("TERM","TERM_TAIL"),
                //17 - <TERM_TAIL> ::= <SOMA_SUBTRACAO> <TERM> <TERM_TAIL>
                asList("SOMA_SUBTRACAO","TERM","TERM_TAIL"),
                //18 -  	<TERM_TAIL> ::= î
                List.of(),
                //19 - <TERM> ::= <FACTOR> <FACTOR_TAIL>
                asList("FACTOR","FACTOR_TAIL"),
                // 20 - <FACTOR_TAIL> ::= <MULT_DIV> <FACTOR> <FACTOR_TAIL>
                asList("MULT_DIV","FACTOR","FACTOR_TAIL"),
                // 21 - <FACTOR_TAIL> ::= î
                List.of(),
                //22 -  	<FACTOR> ::= "(" <EXPRESSAO> ")"
                asList("(","EXPRESSAO",")"),
                //23 - <FACTOR> ::= variavel
                asList("variavel"),
                //24 - <FACTOR> ::= num
                asList("num"),
                //25 -  <FACTOR> ::= string
                asList("string"),
                //26 -  <SOMA_SUBTRACAO> ::= <SOMA>
                asList("SOMA"),
                //27 - <SOMA_SUBTRACAO> ::= <SUBTRACAO>
                asList("SUBTRACAO"),
                //28 -  	<SOMA> ::= "+"
                asList("+"),
                //29 -  	<SUBTRACAO> ::= "-"
                asList("-"),
                //30 - <MULT_DIV> ::= <MULT>
                asList("MULT"),
                // 31 - <MULT_DIV> ::= <DIV>
                asList("DIV"),
                // 32 -  <MULT> ::= "*"
                asList("*"),
                //33 -  <DIV> ::= "/"
                asList("/"),
                // 34 - <LOOP> ::= <WHILE> fimrepeticao
                asList("WHILE","fimrepeticao"),
                // 35 - 	<WHILE> ::= inicio_repeticao "(" <LOGICAL_EXPRESSION> ")" <COMANDO_LIST>
                asList("inicio_repeticao","(","LOGICAL_EXPRESSION",")","COMANDO_LIST"),
                // 36 - <CONDITIONAL> ::= <IF>
                asList("IF"),
                // 37 - 	<IF> ::= inicio_condicional "(" <LOGICAL_EXPRESSION> ")" <COMANDO_LIST> <ELSE> end_if
                asList("inicio_condicional","(","LOGICAL_EXPRESSION",")","COMANDO_LIST","ELSE","end_if"),
                //38 -  <ELSE> ::= se_nao <COMANDO_LIST>
                asList("se_nao","COMANDO_LIST"),
                //39 - <ELSE> ::= î
                List.of(),
                ///40 - <LOGICAL_EXPRESSION> ::= <EXPRESSAO> <LOGICAL_OPERATOR> <EXPRESSAO>
                asList("EXPRESSAO","LOGICAL_OPERATOR","EXPRESSAO"),
                //41 -  	<LOGICAL_OPERATOR> ::= "<"
                asList("<"),
                //42 -  	<LOGICAL_OPERATOR> ::= ">"
                asList(">"),
                //43 -  	<LOGICAL_OPERATOR> ::= "|"
                asList("|"),
                //44 -  	<LOGICAL_OPERATOR> ::= "!"
                asList("!")
        );
    }

    public static List<List<Integer>> getTabelaSintatica() {

        return asList(
                asList(1,0,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1),
                asList(-1,-1,3,2,2,2,3,3,2,3,2,-1,-1,2,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1),
                asList(-1,-1,-1,5,4,8,-1,-1,9,-1,6,-1,-1,7,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1),
                asList(-1,-1,-1,-1,10,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1),
                asList(-1,-1,-1,11,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1),
                asList(-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,12,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1),
                asList(-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,13,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1),
                asList(-1,-1,15,15,15,15,15,15,15,15,15,-1,-1,15,14,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1),
                asList(-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,16,16,16,-1,16,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1),
                asList(-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,19,19,19,-1,19,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1),
                asList(-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,18,18,17,17,-1,-1,18,18,18,18),
                asList(-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,26,27,-1,-1,-1,-1,-1,-1),
                asList(-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,28,-1,-1,-1,-1,-1,-1,-1),
                asList(-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,29,-1,-1,-1,-1,-1,-1),
                asList(-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,25,24,23,-1,22,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1),
                asList(-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,21,21,21,21,20,20,21,21,21,21),
                asList(-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,30,31,-1,-1,-1,-1),
                asList(-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,32,-1,-1,-1,-1,-1),
                asList(-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,33,-1,-1,-1,-1),
                asList(-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,41,42,43,44),
                asList(-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,40,40,40,-1,40,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1),
                asList(-1,-1,-1,-1,-1,36,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1),
                asList(-1,-1,-1,-1,-1,-1,-1,-1,34,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1),
                asList(-1,-1,-1,-1,-1,-1,-1,-1,35,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1),
                asList(-1,-1,-1,-1,-1,37,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1),
                asList(-1,-1,-1,-1,-1,-1,38,39,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1));
    }

}
