package ifmt.cba.heloise.analisador;

/*Metodo regex- expressoes regulares */

import java.util.*;

public class Lexico {
    //declação de metodo de atribuição dos tokens
    //map tipo estatico para armazenar os tokens
    public final static Map<String, String> palavraChave;

    static {
        //Hashmap -> Chave-valor 
        //lexema - token 
        palavraChave = new HashMap<>();
        palavraChave.put( "begin","inicio_pgm");
        palavraChave.put("end","fim_pgm");
        palavraChave.put("if","inicio_condicional");
        palavraChave.put("else","se_nao");
        palavraChave.put("endif","end_if");
        palavraChave.put("while","inicio_repeticao");
        palavraChave.put("endloop","fimrepeticao");
        palavraChave.put( "write","escreva");
        palavraChave.put("read","leia");
        palavraChave.put( "int","tipo");
    }

    int lineCounter = 1;
    int columnCounter = 1;
    List<String> line = new ArrayList<>();
    List<UtilLexico> listOfTokens = new ArrayList<>();

    //casos determinado
    public static boolean isKeyword(String s) {
        //palavras chave
        switch (s) {
            case "begin":
            case "end":
            case "if":
            case "else":
            case "endif":
            case "while":
            case "endloop":
            case "write":
            case "read":
            case "int":
                return true;
        }

        return false;
    }

    public static boolean isSymbol(String s) {
        //simbolos 
        switch (s) {
            case ";":
            case "(":
            case ")":
            case "=":
            case "+":
            case "-":
            case "*":
            case "/":
            case "<":
            case ">":
            case "|": //op logico igual
            case "!": //operador logico diferente
                return true;
        }

        return false;
    }

    // verifica se o primeiro caractere é  uma letras 
    public static boolean isId(String s) {
        if (!Character.isLetter(s.charAt(0))) {
            return false;
        }
        return s.chars().allMatch(Character::isLetterOrDigit);
    }

      // verifica se o primeiro caractere é um numero
    public static boolean isNumber(String s) {
        return s.chars().allMatch(Character::isDigit);
    } 

    //metodo de analise do codigo
    public List<UtilLexico> splitTk(List<String> codigo) {  // Split é usado para dividir uma string em pequenos pedaços.

        //Aqui usamos o regex para quebrar espaços nas leituras do codigo onde ele aceita somente os simbolos necessário
        //criterios de divisao - expressao regular
        String splitter = "[+*\\-<>/=\\s)(\";]";   
        String regex = "((?<=" + splitter + ")|"  /*lookahead*/ + "(?=" + splitter + "))"; /*lookbehind*/

        for (String s : codigo) { //inicia a analise linha por linha 

            if (s.isEmpty()) { //caso tenha um espaço vazio continue
                lineCounter++;
                continue;
            }
            //linha recebe o regex como parametro uma divisão dentro da lista de string
            line = Arrays.asList(s.split(regex));

            // o for vai percorre as linhas do codigo fazendo a verificação do lexema
            for (int i = 0; i < line.size(); i++) { //percorre a lista de linha quebradas pelo regex
                String lexeme = line.get(i);

                if (lexeme.isBlank()) continue; // tratamento para string vazia ou espaço em branco

                if (isKeyword(lexeme)) { //add caso for um simbolo ou palavra chave e ir organizando na lista
                    var terminal = palavraChave.get(lexeme);
                    listOfTokens.add(new UtilLexico(terminal, lexeme, lineCounter, columnCounter));
                    columnCounter += lexeme.length();
                } else 
                
                if (isSymbol(lexeme)) {//simbolos que foram declarados no programa
                    listOfTokens.add(new UtilLexico(lexeme, lexeme, lineCounter, columnCounter));
                    columnCounter += lexeme.length();
                } else 
                
                
                if (isId(lexeme)) { // add caso for um variavel
                    listOfTokens.add(new UtilLexico("variavel", lexeme, lineCounter, columnCounter));
                    columnCounter += lexeme.length();
                } else
                
                if (isNumber(lexeme)) { // add caso for um numeral
                    listOfTokens.add(new UtilLexico("num", lexeme, lineCounter, columnCounter));
                    columnCounter += lexeme.length();
                } else
                
                if (lexeme.equals("\"")) { // add strings
                    StringBuilder string = new StringBuilder();

                    do {
                        string.append(line.get(i));

                        if (i + 1 == line.size()) { // caso chegue no final de linha sem encontrar o fecha aspas
                            System.err.println("ERRO LEXICO  [" + lineCounter + ", " + columnCounter + "] " +
                                    "Faltou aspas.");
                            System.exit(-1);
                        }

                    } while (!line.get(++i).equals("\"")); //enquanto o proximo lexema for diferente de fecha aspas

                    string.append('"');
                    listOfTokens.add(new UtilLexico("string", string.toString(), lineCounter, columnCounter));
                    columnCounter += string.length();

                } else { // caso seja  um lexema que não se encaixa na gramatica
                    System.err.println("ERRO LEXICO  [" + lineCounter + ", " + columnCounter + "] " +
                            lexeme + " nao foi reconhecido.");
                    System.exit(-1);
                }
            }
            //no final de verificação de cada linha 
            lineCounter++; //incrementa um no contador de linha
            columnCounter = 1; //seta coluna
        }

        listOfTokens.add(new UtilLexico("$", "$", lineCounter, columnCounter));
        
        //Printa a lista de tokens caso a flag -ls seja passada como parametro
        if (Flag.TOKENS.getStatus()|| Flag.TODOS.getStatus()) {
            for (UtilLexico listOfToken : listOfTokens) {
                System.out.println(listOfToken.toString());
            }
        }
        return listOfTokens;
    }
}
