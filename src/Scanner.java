import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Scanner
{
//remaining: GUI

    public static void main(String[] args)
    {
        String inputLine = new String("read:= dfdfd1+100:= x {sdsdsds  32 3 }{s}");
        ArrayList<Character> charArray = new ArrayList<Character>();
        List reservedWords = Arrays.asList("if","then","else","end","repeat","until","read","write");
        States currentState =States.START;
        States previousState = States.START;
        //int k, j=0;
        ArrayList<String> identifierList= new ArrayList<String>();
        String identifier1 = new String();
        ArrayList<String> numberList= new ArrayList<String>();
        String number1 = new String();
        ArrayList<String> commentList= new ArrayList<String>();
        String comment1 = new String();
        ArrayList<String> reservedWordsList= new ArrayList<String>();
        ArrayList<String> assignOperatorsList= new ArrayList<String>();
        String assignOperator1 = new String();
        ArrayList<Token> tokenList = new ArrayList<Token>();
        for (char c : inputLine.toCharArray())
        {
            charArray.add(c);
        }


        for(int i=0; i<charArray.size(); i++) {
            String currentChar = charArray.get(i).toString();
            switch (currentState) {

                case START:
                    if (currentChar.matches("[a-zA-Z]"))
                    {
                        identifier1+=currentChar;
                        currentState = States.INID;
                    }
                    if (currentChar.matches(" ")) {
                        currentState = States.START;
                    }
                    if (currentChar.matches("[0-9]")) {
                        number1+=currentChar;
                        currentState = States.INNUM;
                    }
                    if (currentChar.matches("[{]")) {
                        currentState = States.INCOMMENT;
                    }
                    if (currentChar.matches("[:]")) {

                        assignOperator1+=currentChar;
                        currentState = States.INASSIGN;
                    }
                    if(currentChar.matches("[+|-|*|/|=|<|>|(|)|,|;]"))
                    {
                        currentState = States.START;
                    }
                    break;


                case INCOMMENT:

                    if (currentChar.matches("[}]")) {
                        commentList.add(comment1);
                        comment1="";
                        currentState = States.START;
                    }
                    if (currentChar.matches("[^\n}]")) {

                        comment1+=currentChar;
                        currentState = States.INCOMMENT;
                    }

                    break;


                case INID:
                    if (currentChar.matches("[a-zA-Z]"))
                    {
                        identifier1+=currentChar;

                        /*if(currentChar=="\n")
                        {
                            identifierList.add(identifier1);
                            identifier1="";
                        }*/
                        currentState = States.INID;
                    }
                    if (currentChar.matches(" ")) {

                        if(reservedWords.contains(identifier1)) {
                            reservedWordsList.add(identifier1);
                            identifier1="";
                        }
                        else{
                            identifierList.add(identifier1);
                            identifier1 = "";
                        }
                        currentState = States.START;
                    }
                    if (currentChar.matches("[0-9]")) {

                        identifier1+=currentChar;
                        currentState = States.INID;
                    }
                    if (currentChar.matches("[{]")) {

                        if(reservedWords.contains(identifier1)) {
                            reservedWordsList.add(identifier1);
                            identifier1="";
                        }
                        else{
                            identifierList.add(identifier1);
                            identifier1 = "";
                        }
                        currentState = States.INCOMMENT;
                    }
                    if (currentChar.matches("[:]")) {

                        assignOperator1+=currentChar;

                        if(reservedWords.contains(identifier1)) {
                            reservedWordsList.add(identifier1);
                            identifier1="";
                        }
                        else{
                            identifierList.add(identifier1);
                            identifier1 = "";
                        }
                        currentState = States.INASSIGN;
                    }
                    if(currentChar.matches("[+|-|*|/|=|<|>|(|)|,|;]"))
                    {
                        if(reservedWords.contains(identifier1)) {
                            reservedWordsList.add(identifier1);
                            identifier1="";
                        }
                        else{
                            identifierList.add(identifier1);
                            identifier1 = "";
                        }
                        currentState = States.START;
                    }
                    break;


                case INNUM:
                    if (currentChar.matches("[a-zA-Z]")) {

                        identifier1+=currentChar;
                        currentState = States.INID;
                    }
                    if (currentChar.matches(" ")) {

                        numberList.add(number1);
                        number1 = "";
                        currentState = States.START;
                    }
                    if (currentChar.matches("[0-9]")) {

                        number1+=currentChar;
                        currentState = States.INNUM;
                    }
                    if (currentChar.matches("[{]")) {

                        currentState = States.INCOMMENT;
                    }
                    if (currentChar.matches("[:]")) {

                        assignOperator1+=currentChar;
                        currentState = States.INASSIGN;
                    }
                    if(currentChar.matches("[+|-|*|/|=|<|>|(|)|,|;]"))
                    {
                        numberList.add(number1);
                        number1 = "";
                        currentState = States.START;
                    }
                    break;


                case INASSIGN:
                    if (currentChar.matches("[=]")) {

                        assignOperator1+=currentChar;
                        assignOperatorsList.add(assignOperator1);
                        assignOperator1="";
                        currentState = States.START;
                    }
                    break;


                case DONE:
                    identifierList.add(identifier1);
                    identifier1="";
                    numberList.add(number1);
                    number1="";
                    break;

            }
        }
        for(int i=0; i<identifierList.size(); i++)
        {
            System.out.println("identifier is: "+identifierList.get(i));
        }

        for(int i=0; i<numberList.size(); i++)
        {
            System.out.println("number is: "+numberList.get(i));
        }

        for(int i=0; i<commentList.size(); i++)
        {
            System.out.println("comment "+(i+1)+ " is: "+commentList.get(i));
        }

        for(int i=0; i<reservedWordsList.size(); i++)
        {
            System.out.println("reserved word is: "+reservedWordsList.get(i));
        }

        for(int i=0; i<assignOperatorsList.size(); i++)
        {
            System.out.println("assign operator: "+assignOperatorsList.get(i));
        }
    }
}
enum States{
    START,
    INNUM,
    INID,
    INASSIGN,
    INCOMMENT,
    DONE
}
