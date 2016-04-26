package iisi.polynomial;

import java.util.HashMap;
import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * Created by lyszkows on 26/04/16.
 */
public class PolynomialTerm {

    private Double coefficient;
    private HashMap<Integer, Integer> variablesExponents;

    public PolynomialTerm(Double coefficient){
        this.coefficient = coefficient;
    }

    public PolynomialTerm updatePolynomialTerm(Integer variableId){
        variablesExponents.computeIfPresent(variableId, getNewExponentValue());
        variablesExponents.computeIfAbsent(variableId, getInitialExponentValue());
        return this;
    }

    private Function<Integer,Integer> getInitialExponentValue(){
      return variableId -> 1;
    }

    private BiFunction<Integer,Integer,Integer> getNewExponentValue(){
        return (variableId, exponent) -> exponent+1;
    }
}
