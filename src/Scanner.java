import javax.swing.plaf.nimbus.State;
import java.nio.ReadOnlyBufferException;
import java.util.ArrayList;

public class Scanner
{


    public static void main(String[] args)
    {
        String inputLine = new String("read x; {shs shsh shs}ss ss");
        ArrayList<Character> charArray = new ArrayList<Character>();
        String [] reservedWords = {"if","then","else","end","repeat","until","read","write"};
        States currentState =States.START;
        States previousState = States.START;
        //int k, j=0;
        ArrayList<String> identifierList= new ArrayList<String>();
        String identifier1 = new String();
        ArrayList<String> numberList= new ArrayList<String>();
        String number1 = new String();
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
                        identifier1.concat(currentChar);
                        currentState = States.INID;
                    }
                    if (currentChar.matches(" ")) {
                        currentState = States.START;
                    }
                    if (currentChar.matches("[0-9]")) {
                        previousState = States.START;
                        currentState = States.INNUM;
                    }
                    if (currentChar.matches("[{]")) {
                        currentState = States.INCOMMENT;
                    }
                    if (currentChar.matches("[:]")) {
                        currentState = States.INASSIGN;
                    }
                    if(currentChar.matches("[+|-|*|/|=|<|>|(|)|,|;]"))
                    {
                        currentState = States.DONE;
                    }
                    break;


                case INCOMMENT:
                    if (currentChar.matches("[}]")) {
                        currentState = States.START;
                    }
                    break;


                case INID:
                    if (currentChar.matches("[a-zA-Z]"))
                    {
                        identifier1.concat(currentChar);
                        currentState = States.INID;
                    }
                    if (currentChar.matches(" ")) {
                        identifierList.add(identifier1);
                        identifier1 = new String();
                        currentState = States.START;
                    }
                    if (currentChar.matches("[0-9]")) {

                        identifier1.concat(currentChar);
                        previousState = States.INID;
                        currentState = States.INNUM;
                    }
                    if (currentChar.matches("[{]")) {

                        identifierList.add(identifier1);
                        identifier1 = new String();
                        currentState = States.INCOMMENT;
                    }
                    if (currentChar.matches("[:]")) {

                        identifierList.add(identifier1);
                        identifier1 = new String();
                        currentState = States.INASSIGN;
                    }
                    if(currentChar.matches("[+|-|*|/|=|<|>|(|)|,|;]"))
                    {
                        identifierList.add(identifier1);
                        identifier1 = new String();
                        currentState = States.DONE;
                    }
                    break;


                case INNUM:
                    if (currentChar.matches("[a-zA-Z]")) {

                        identifier1.concat(currentChar);
                        currentState = States.INID;
                    }
                    if (currentChar.matches(" ")) {

                        if(previousState==States.INID)
                        {
                            identifierList.add(identifier1);
                            identifier1 = new String();
                        }

                        if(previousState==States.START)
                        {
                            numberList.add(number1);
                            number1 = new String();
                        }

                        currentState = States.START;
                    }
                    if (currentChar.matches("[0-9]")) {

                        number1.concat(currentChar).toString();
                        currentState = States.INNUM;
                    }
                    if (currentChar.matches("[{]")) {

                        if(previousState==States.INID)
                        {
                            identifierList.add(identifier1);
                            identifier1 = new String();
                        }
                        currentState = States.INCOMMENT;
                    }
                    if (currentChar.matches("[:]")) {

                        if(previousState==States.INID)
                        {
                            identifierList.add(identifier1);
                            identifier1 = new String();
                        }
                        currentState = States.INASSIGN;
                    }
                    if(currentChar.matches("[+|-|*|/|=|<|>|(|)|,|;]"))
                    {
                        if(previousState==States.INID)
                        {
                            identifierList.add(identifier1);
                            identifier1 = new String();
                        }
                        currentState = States.DONE;
                    }
                    break;


                case INASSIGN:
                    if (currentChar.matches("[=]")) {
                        currentState = States.DONE;
                    }
                    break;


                default: //done state

            }

            /*String identifier1 = new String();

            if(charArray.get(i).toString().matches("[a-zA-Z]"))
            {
                for (k = i; charArray.get(k).toString().matches("[a-zA-Z]"); k++)
                {
                    currentState = States.INID;
                    identifier1.concat(charArray.get(i).toString());
                }

                identifierList[j] = identifier1;
                j++;
                i = k;
            }
            if(charArray.get(i).toString().matches(" "))
            {
                currentState = States.START;
            }
            if(charArray.get(i).toString().matches("[0-9]"))
            {
                currentState =States.INNUM;
            }
            if(charArray.get(i).toString().matches("[{]"))
            {
                currentState =States.INCOMMENT;
            }
            if(charArray.get(i).toString().matches("[}]"))
            {
                currentState =States.START;
            }
            if(charArray.get(i).toString().matches("[:=]"))
            {
                currentState =States.INASSIGN;
            }*/
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
