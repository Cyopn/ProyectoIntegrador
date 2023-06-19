package tst;

import java.util.*;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test {

    private static final double EPSILON = 1e-10; // Pequeña tolerancia para comparación de números

    public static void main(String[] args) {
        List<String> ec = new ArrayList<>();
        ec.add("2x+3y+4z=3");
        ec.add("2x+6y+8z=5");
        ec.add("4x+9y-4z=4");

        Pattern sm = Pattern.compile("[+-=]");
        Pattern ig = Pattern.compile("[xyz]");
        List<String> fe = new ArrayList<>();
        List<Integer> fc = new ArrayList<>();
        List<List<String>> gbe = new ArrayList<>();
        for (String a : ec) {
            String[] b = a.split("=");
            fe.add(b[0]);
        }
        for (String a : ec) {
            String[] b = a.split("=");
            fc.add(Integer.parseInt(b[1]));
        }

        for (String a : fe) {
            if (a.charAt(0) != '-') {
                char[] d = a.toCharArray();
                char[] newD = new char[d.length + 1];
                newD[0] = '+';
                System.arraycopy(d, 0, newD, 1, d.length);
                a = new String(newD);
            }
            String[] im = sm.split(a);
            String[] ii = ig.split(a);
            List<String> sf = new ArrayList<>();
            sf.add("");
            sf.add("");
            sf.add("");
            int j = 0;
            int i = 0;
            String[] nm = new String[3];
            for (String r : im) {
                if (!r.isEmpty()) {
                    nm[i] = r;
                    i++;
                }
            }
            for (String r : ii) {
                if (r.length() <= 1) {
                    if (nm[j].equals("x")) {
                        System.out.println("x");
                        sf.set(0, "1");
                    } else if (nm[j].equals("y")) {
                        System.out.println("y");
                        sf.set(1, "1");
                    } else if (nm[j].equals("z")) {
                        System.out.println("z" + j);
                        sf.set(2, "1");
                    } else {
                        sf.set(j, "0");
                    }
                } else {
                    if (nm[j].equals("x")) {
                        sf.set(0, r);
                    } else if (nm[j].equals("y")) {
                        sf.set(1, r);
                    } else if (nm[j].equals("z")) {
                        sf.set(2, r);
                    } else {
                        sf.set(j, "0");
                    }
                }
                j++;
            }
            gbe.add(sf);
        }
        double[] re = new proyectointegrador.Functions("").gauss(gbe, fc);
        System.out.println(re[0]);
        System.out.println(re[1]);
        System.out.println(re[2]);

    }

    public static void simplex(double[][] tableau) {
        int m = tableau.length; // Número de restricciones
        int n = tableau[0].length - 1; // Número de variables

        while (true) {
            // Encontrar columna pivote
            int pivotColumn = findPivotColumn(tableau);
            if (pivotColumn == -1) {
                // No hay columna pivote, hemos terminado
                break;
            }

            // Encontrar fila pivote
            int pivotRow = findPivotRow(tableau, pivotColumn);
            if (pivotRow == -1) {
                // El problema es no acotado
                break;
            }

            // Realizar la operación pivote
            pivot(tableau, pivotRow, pivotColumn);
        }

        // Imprimir la solución óptima
        printSolution(tableau, m, n);
    }

    public static int findPivotColumn(double[][] tableau) {
        int m = tableau.length - 1; // Número de restricciones
        int n = tableau[0].length - 1; // Número de variables

        int pivotColumn = -1;
        for (int j = 0; j < n; j++) {
            if (tableau[m][j] > EPSILON) {
                if (pivotColumn == -1 || tableau[m][j] > tableau[m][pivotColumn]) {
                    pivotColumn = j;
                }
            }
        }

        return pivotColumn;
    }

    public static int findPivotRow(double[][] tableau, int pivotColumn) {
        int m = tableau.length; // Número de restricciones
        int n = tableau[0].length - 1; // Número de variables

        int pivotRow = -1;
        for (int i = 0; i < m; i++) {
            if (tableau[i][pivotColumn] > EPSILON) {
                if (pivotRow == -1 || tableau[i][n] / tableau[i][pivotColumn] < tableau[pivotRow][n] / tableau[pivotRow][pivotColumn]) {
                    pivotRow = i;
                }
            }
        }

        return pivotRow;
    }

    public static void pivot(double[][] tableau, int pivotRow, int pivotColumn) {
        int m = tableau.length - 1; // Número de restricciones
        int n = tableau[0].length - 1; // Número de variables

        // Hacer que el elemento pivote sea 1
        double pivotValue = tableau[pivotRow][pivotColumn];
        for (int j = 0; j <= n; j++) {
            tableau[pivotRow][j] /= pivotValue;
        }

        // Hacer que los demás elementos en la columna pivote sean 0
        for (int i = 0; i <= m; i++) {
            if (i != pivotRow) {
                double factor = tableau[i][pivotColumn];
                for (int j = 0; j <= n; j++) {
                    tableau[i][j] -= factor * tableau[pivotRow][j];
                }
            }
        }
    }

    public static void printSolution(double[][] tableau, int m, int n) {
        System.out.println("Solución óptima encontrada:");
        for (int i = 0; i < m; i++) {
            System.out.printf("x%d = %.2f\n", i + 1, tableau[i][n]);
        }
        System.out.printf("Valor objetivo: %.2f\n", -tableau[m][n]);
    }

}
