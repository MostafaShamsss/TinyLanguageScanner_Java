public class Token
{
    public String tokenValue;
    public TokenType tokenType;
    public Token(String tokenValue, TokenType tokenType)
    {
        this.tokenValue = tokenValue;
        this.tokenType = tokenType;
    }
    String printToken() {
    return tokenValue +" is type of "+tokenType.toString();
    }
}


enum TokenType{
    IDENTIFIER,
    OPERATOR,
    COMMENT,
    ASSIGNMENT,
    RESERVED_WORD
        }