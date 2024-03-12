package ifmt.cba.heloise.analisador;


public class FlagStatus {

    public void verificaTk(String[] cmd) {

        for (String s : cmd) {

            if (s.equals("-It")) {
                Flag.TOKENS.setStatus(true);
            } else if (s.equals("-ls")) {
                Flag.SINTATICO.setStatus(true);
            }else  if (s.equals("-lse")) {
                Flag.SEMANTICO.setStatus(true);
            }else  if (s.equals("-todos")) {
                Flag.TODOS.setStatus(true);
            }else  if (s.equals("-fim")) {
                Flag.CODIGO_FINAL.setStatus(true);
            }
        }
    }

    public String validatePath(String[] cmd) {
        for (String arg : cmd) {
            if (arg.contains(".txt")) return arg;
        }
        throw new IllegalArgumentException("Arquivo não reconhecido");
    }

}