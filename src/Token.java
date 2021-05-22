public class Token
{
    public String tokenValue;
    public TokenType tokenType;
    public Token(String tokenValue, TokenType tokenType)
    {
        this.tokenValue = tokenValue;
        this.tokenType = tokenType;
    }
}

enum TokenType{
    IDENTIFIER,
    OPERATOR,

        }