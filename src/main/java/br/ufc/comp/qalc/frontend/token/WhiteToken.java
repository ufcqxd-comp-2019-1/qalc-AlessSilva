package br.ufc.comp.qalc.frontend.token;

public class WhiteToken extends Token {

    public WhiteToken ( long line, long start, String value ){

        super(line,start,value);

    }

    @Override
    public void interpretAttributes(){

        if( stringValue != null && stringValue.charAt(0) == ' ' ){

            stringValue = stringValue.substring(0);

        }

    }

    @Override
    public String getTokenIdentifier(){

        return "WHITE";

    }

}
