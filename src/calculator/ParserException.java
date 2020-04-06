package calculator;

public class ParserException extends Exception {
    private String entry;

    public ParserException(String entry){
        this.entry = entry;
    }

    public String toString(){
        return "Error during parsing : " + entry;
    }
}
