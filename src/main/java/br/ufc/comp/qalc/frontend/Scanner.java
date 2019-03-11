package br.ufc.comp.qalc.frontend;

import br.ufc.comp.qalc.frontend.token.*;

import java.io.IOException;

/**
 * Analisador Léxico da linguagem.
 * <p>
 * Funciona como uma fonte de tokens, de onde o Analisador Sintático
 * deverá ler.
 *
 * @see Source
 */
public class Scanner {

    /**
     * Último token reconhecido.
     */
    protected Token currentToken;
    /**
     * Fonte de caracteres de onde extrair os tokens.
     */
    protected Source source;

    /**
     * Constrói um Analisador Léxico a partir de uma fonte de caracteres.
     *
     * @param sourceStream Fonte a ser utilizada.
     */
    public Scanner(Source sourceStream) {
        // FIXME Lidar corretamente com as exceções.
        this.source = sourceStream;
    }

    /**
     * Consome caracteres da fonte até que eles componham um lexema de
     * um dos tokens da linguagem, coinstrói um objeto representando esse
     * token associado ao lexema lido e o retorna.
     *
     * @return Token reconhecido.
     * @throws IOException Caso haja problema na leitura da fonte.
     */
    public Token getNextToken() throws IOException {
        // TODO Reconhecimento de tokens

        if (source.getCurrentChar() == Source.EOF) { // EOFToken

            return new EOFToken(source.getCurrentLine(), source.getCurrentColumn());

        } else if (Character.isDigit(source.getCurrentChar())) { // NumberToken

            StringBuilder lexema = new StringBuilder();

            long currentLine = source.getCurrentLine();
            long lexemeStart = source.getCurrentColumn();

            do {
                lexema.append(source.getCurrentChar());
                source.advance();
            } while (Character.isDigit(source.getCurrentChar()));

            if( source.getCurrentChar() == '.' ){

                do {
                    lexema.append(source.getCurrentChar());
                    source.advance();
                } while (Character.isDigit(source.getCurrentChar()));

            }

            String stringValue = lexema.toString();

            return new NumberToken(currentLine, lexemeStart, stringValue);

        } else if ( source.getCurrentChar() == '$' ){ // Variable or ResultIdentfier Token

            StringBuilder lexema = new StringBuilder();
            long currentLine = source.getCurrentLine();
            long lexemaStart = source.getCurrentColumn();

            lexema.append(source.getCurrentChar());
            source.advance();

            if( source.getCurrentChar() == '?' ){

                lexema.append(source.getCurrentChar());
                source.advance();
                String stringValue = lexema.toString();
                return new ResultIdentifierToken(currentLine,lexemaStart,stringValue);

            }else if( Character.isDigit(source.getCurrentChar()) ){ //  ResultIdentfier Token

                int contZeros = 0;

                do {
                    lexema.append(source.getCurrentChar());

                    if( source.getCurrentChar() == '0' ){
                        contZeros++;
                    }

                    source.advance();
                }while (Character.isDigit(source.getCurrentChar()));

                String stringValue = lexema.toString();

                if( contZeros != lexema.length() ){ //Não pode ser formado apenas de zeros
                    return new ResultIdentifierToken(currentLine,lexemaStart,stringValue);
                }

            }else if( ( source.getCurrentChar() >= 'a' && source.getCurrentChar() <= 'z' ) || ( source.getCurrentChar() >= 'A' && source.getCurrentChar() <= 'Z') ){ // VariableToken

                do {
                    lexema.append(source.getCurrentChar());
                    source.advance();
                }while ( ( source.getCurrentChar() >= 'a' && source.getCurrentChar() <= 'z' ) || ( source.getCurrentChar() >= 'A' && source.getCurrentChar() <= 'Z') );

                String stringValue = lexema.toString();

                return new VariableIdentifierToken(currentLine,lexemaStart,stringValue);

            }

        }else if ( source.getCurrentChar() == '@' ){ //FunctionIdentifier

            long currentLine = source.getCurrentLine();
            long lexemaStart = source.getCurrentColumn();
            StringBuilder lexema = new StringBuilder();

            do {

                lexema.append(source.getCurrentChar());
                source.advance();

            }while ( Character.isDigit(source.getCurrentChar()) || ( source.getCurrentChar() >= 'a' && source.getCurrentChar() <= 'z' ) || ( source.getCurrentChar() >= 'A' && source.getCurrentChar() <= 'Z')  );

            String stringValue  = lexema.toString();

            return new FunctionIdentifierToken(currentLine,lexemaStart,stringValue);

        }else if ( source.getCurrentChar() == '=' ){ //AtribToken

            long currentLine = source.getCurrentLine();
            long lexemaStart = source.getCurrentColumn();
            StringBuilder lexema = new StringBuilder();
            lexema.append(source.getCurrentChar());
            source.advance();

            return new AtribToken(currentLine,lexemaStart,lexema.toString());

        }else if ( source.getCurrentChar() == '+' ){ //PlusToken

            long currentLine = source.getCurrentLine();
            long lexemaStart = source.getCurrentColumn();
            StringBuilder lexema = new StringBuilder();
            lexema.append(source.getCurrentChar());
            source.advance();

            return new PlusToken(currentLine,lexemaStart,lexema.toString());

        }else if ( source.getCurrentChar() == '-' ){ //MinusToken

            long currentLine = source.getCurrentLine();
            long lexemaStart = source.getCurrentColumn();
            StringBuilder lexema = new StringBuilder();
            lexema.append(source.getCurrentChar());
            source.advance();

            return new MinusToken(currentLine,lexemaStart,lexema.toString());

        }else if ( source.getCurrentChar() == '*' ){ //TimesToken

            long currentLine = source.getCurrentLine();
            long lexemaStart = source.getCurrentColumn();
            StringBuilder lexema = new StringBuilder();
            lexema.append(source.getCurrentChar());
            source.advance();

            return new TimesToken(currentLine,lexemaStart,lexema.toString());

        }else if ( source.getCurrentChar() == '/' ){ //DivToken

            long currentLine = source.getCurrentLine();
            long lexemaStart = source.getCurrentColumn();
            StringBuilder lexema = new StringBuilder();
            lexema.append(source.getCurrentChar());
            source.advance();

            return new DivToken(currentLine,lexemaStart,lexema.toString());

        }else if ( source.getCurrentChar() == '%' ){ //ModToken

            long currentLine = source.getCurrentLine();
            long lexemaStart = source.getCurrentColumn();
            StringBuilder lexema = new StringBuilder();
            lexema.append(source.getCurrentChar());
            source.advance();

            return new ModToken(currentLine,lexemaStart,lexema.toString());

        }else if ( source.getCurrentChar() == '^' ){ //PowToken

            long currentLine = source.getCurrentLine();
            long lexemaStart = source.getCurrentColumn();
            StringBuilder lexema = new StringBuilder();
            lexema.append(source.getCurrentChar());
            source.advance();

            return new PowToken(currentLine,lexemaStart,lexema.toString());

        }else if ( source.getCurrentChar() == '(' ){ //LParenToken

            long currentLine = source.getCurrentLine();
            long lexemaStart = source.getCurrentColumn();
            StringBuilder lexema = new StringBuilder();
            lexema.append(source.getCurrentChar());
            source.advance();

            return new LParenToken(currentLine,lexemaStart,lexema.toString());

        }else if ( source.getCurrentChar() == ')' ){ //RParenToken

            long currentLine = source.getCurrentLine();
            long lexemaStart = source.getCurrentColumn();
            StringBuilder lexema = new StringBuilder();
            lexema.append(source.getCurrentChar());
            source.advance();

            return new RParenToken(currentLine,lexemaStart,lexema.toString());

        }else if ( source.getCurrentChar() == ',' ){ //CommaToken

            long currentLine = source.getCurrentLine();
            long lexemaStart = source.getCurrentColumn();
            StringBuilder lexema = new StringBuilder();
            lexema.append(source.getCurrentChar());
            source.advance();

            return new CommaToken(currentLine,lexemaStart,lexema.toString());

        }else if ( source.getCurrentChar() == ';' ){ //SemiToken

            long currentLine = source.getCurrentLine();
            long lexemaStart = source.getCurrentColumn();
            StringBuilder lexema = new StringBuilder();
            lexema.append(source.getCurrentChar());
            source.advance();

            return new SemiToken(currentLine,lexemaStart,lexema.toString());

        }else if( Character.isWhitespace(source.getCurrentChar()) ){

            long currentLine = source.getCurrentLine();
            long lexemaStart = source.getCurrentColumn();
            StringBuilder lexema = new StringBuilder();

            do{

                lexema.append(source.getCurrentChar());
                source.advance();

            }
            while( Character.isWhitespace(source.getCurrentChar()) );

            return new WhiteToken(currentLine,lexemaStart,lexema.toString());

        }else if( source.getCurrentChar() == '#' ){

            long currentLine = source.getCurrentLine();
            long lexemaStart = source.getCurrentColumn();
            StringBuilder lexema = new StringBuilder();

            do{

               lexema.append(source.getCurrentChar());
               source.advance();

            }while ( source.getCurrentChar() != '\n' && source.getCurrentChar() != '\r' );

            return new ComToken( currentLine,lexemaStart,lexema.toString() );

        }

        // TODO Recuperação de erros.

        else{

            long currentLine = source.getCurrentLine();
            long lexemaStart = source.getCurrentColumn();
            StringBuilder lexema = new StringBuilder();

            do{

                lexema.append(source.getCurrentChar());
                source.advance();

            }while ( source.getCurrentChar() != '$' && source.getCurrentChar() != '@'
                    && source.getCurrentChar() != '+' && source.getCurrentChar() != '-'
                    && source.getCurrentChar() != '*' && source.getCurrentChar() != '/'
                    && source.getCurrentChar() != '(' && source.getCurrentChar() != ')'
                    && source.getCurrentChar() != '#' && source.getCurrentChar() != '%'
                    && source.getCurrentChar() != '^' && source.getCurrentChar() != ','
                    && source.getCurrentChar() != ';' && source.getCurrentChar() != '='
                    && !Character.isDigit(source.getCurrentChar()) && !Character.isWhitespace(source.getCurrentChar()));

            return new ErrorToken(currentLine,lexemaStart,lexema.toString());

        }

        return null;
    }

    /**
     * Obtém o último token reconhecido.
     *
     * @return O último token reconhecido.
     */
    public Token getCurrentToken() {
        return currentToken;
    }
}
