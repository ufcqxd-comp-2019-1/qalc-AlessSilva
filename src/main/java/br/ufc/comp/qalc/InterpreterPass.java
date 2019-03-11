package br.ufc.comp.qalc;

import java.io.Closeable;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * Representa cada uma das fases de interpretação da entrada.
 */
public enum InterpreterPass {
    /**
     * Fase de Análise Léxica.
     */
    LEXER,
    /**
     * Fase de Análise Sintática.
     */
    PARSER,
    /**
     * Fase de Análise Semântica.
     */
    SEMANTIC,
    /**
     * Fase de Execução.
     */
    RUNNER,
    ;

    /**
     * Gerencia os recursos do programa que devem ser liberados de forma automatizada
     * ao final da execução.
     * <p>
     * O uso mais útil no momento é o de garantir que a saída dos relatores seja exibida
     * antes que o programa encerre.
     */
    }
