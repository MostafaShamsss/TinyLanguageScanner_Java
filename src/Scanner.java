import javax.swing.plaf.nimbus.State;
import java.nio.ReadOnlyBufferException;
import java.util.ArrayList;

public class Scanner
{


    public static void main(String[] args)
    {
        String inputLine = new String("read x; {shs shsh shs}ss ss");
        ArrayList<Character> charArray = new ArrayList<Character>();
        States currentState =States.START;
        int k, j=0;
        String[] identifierList= new String[50];
        ArrayList<String> tokenList = new ArrayList<String>();
        for (char c : inputLine.toCharArray())
        {
            charArray.add(c);
        }


        for(int i=0; i<charArray.size(); i++) {
            String currentChar = charArray.get(i).toString();
            switch (currentState) {
                case START:

                    if (currentChar.matches("[a-zA-Z]")) {
                        currentState = States.INID;
                    }
                    if (currentChar.matches(" ")) {
                        currentState = States.START;
                    }
                    if (currentChar.matches("[0-9]")) {
                        currentState = States.INNUM;
                    }
                    if (currentChar.matches("[{]")) {
                        currentState = States.INCOMMENT;
                    }
                    if (currentChar.matches("[:]")) {
                        currentState = States.INASSIGN;
                    }
                    break;


                case INCOMMENT:
                    if (currentChar.matches("[}]")) {
                        currentState = States.START;
                    }
                    break;


                case INID:
                    if (currentChar.matches("[a-zA-Z]")) {
                        currentState = States.INID;
                    }
                    if (currentChar.matches(" ")) {
                        currentState = States.START;
                    }
                    if (currentChar.matches("[0-9]")) {
                        currentState = States.INNUM;
                    }
                    if (currentChar.matches("[{]")) {
                        currentState = States.INCOMMENT;
                    }
                    if (currentChar.matches("[:]")) {
                        currentState = States.INASSIGN;
                    }
                    break;


                case INNUM:
                    if (currentChar.matches("[a-zA-Z]")) {
                        currentState = States.INID;
                    }
                    if (currentChar.matches(" ")) {
                        currentState = States.START;
                    }
                    if (currentChar.matches("[0-9]")) {
                        currentState = States.INNUM;
                    }
                    if (currentChar.matches("[{]")) {
                        currentState = States.INCOMMENT;
                    }
                    if (currentChar.matches("[:]")) {
                        currentState = States.INASSIGN;
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
