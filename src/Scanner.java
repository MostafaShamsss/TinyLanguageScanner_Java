import java.util.ArrayList;

public class Scanner
{


    public static void main(String[] args)
    {
        String inputLine = new String("{sdsdsds  32 3 }{s}");
        ArrayList<Character> charArray = new ArrayList<Character>();
        String [] reservedWords = {"if","then","else","end","repeat","until","read","write"};
        States currentState =States.START;
        boolean nextState=false;


        //created one variable that collects all characters
        //called token. created a boolean called nextState which we use
        //after DONE state
        //to set currentState based on the current currentCharacter
        // for example
        // x;
        // x will be considered as an identifier in IDENTIFIER State
        //then when another character comes that does not match our identifier's regex
        //we will move to OTHER State where we will set nextState to true
        //and then move to DONE where we will tokenize "x" alone and then set current state
        //based on ";" type

        String token="";
        ArrayList<Token> tokenList = new ArrayList<Token>();
        for (char c : inputLine.toCharArray())
        {
            charArray.add(c);
        }


        for(int i=0; i<charArray.size(); i++) {
            String currentChar = charArray.get(i).toString();


                if(currentState==States.START) {
                    if (currentChar.matches("[a-zA-Z]")) {

                        currentState = States.INID;
                    }
                    if (currentChar.matches(" ")) {
                        currentState = States.START;
                        continue;
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
                    if (currentChar.matches("[+|-|*|/|=|<|>|(|)|,|;]")) {
                        tokenList.add(new Token(currentChar,TokenType.OPERATOR));
                        token="";
                        nextState=false;
                        currentState = States.START;
                    }

                }

                else if(currentState==States.INCOMMENT) {

                    if (currentChar.matches("[}]")) {
                        currentState = States.DONE;
                    }
                    else {
                        currentState = States.INCOMMENT;
                    }
                }



                else if(currentState==States.INID) {
                    if (currentChar.matches("[a-zA-Z_0-9]")) {
                        currentState = States.INID;
                    }
                    else if (currentChar.matches(" ")) {
                        currentState = States.DONE;
                    }
                    else{
                        currentState=States.OTHER;
                    }
                }


                else if(currentState== States.INNUM) {

                    if (currentChar.matches("[0-9]")) {

                        currentState = States.INNUM;
                    }
                    else if (currentChar.matches(" ")) {

                        currentState = States.DONE;
                    }
                    else{
                        currentState = States.OTHER;
                    }
                }


                else if(currentState==States.INASSIGN) {
                    if (currentChar.matches("[=]")) {
                        currentState = States.DONE;
                    }
                    else {
                        currentState=States.OTHER;
                    }
                }

                if(currentState==States.OTHER)
                {
                    currentState=States.DONE;
                    nextState=true;
                }

                if(currentState!=States.OTHER)
                {
                    token+=currentChar;
                }

                 if(currentState==States.DONE)
                 {
                     if(token.matches("^\\{.+}")){
                        tokenList.add(new Token(token, TokenType.COMMENT));
                     }
                     token="";
                     currentState=States.START;
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
        for(int i=0; i<tokenList.size();i++)
        {
            System.out.println(tokenList.get(i).printToken());
        }

    }
}
enum States{
    START,
    INNUM,
    INID,
    INASSIGN,
    INCOMMENT,
    OTHER,
    DONE
}
