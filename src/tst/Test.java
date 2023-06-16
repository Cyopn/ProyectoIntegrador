package tst;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test {

    private static final double EPSILON = 1e-10; // Pequeña tolerancia para comparación de números

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Introduce tu ecuacion \nFormato: Ax2+Bx+C");
        String a = sc.nextLine();
        Pattern cn = Pattern.compile("[+-]");
        HashMap<String, Integer> fn = new HashMap<String, Integer>();
        String[] cu = {"d", "x", "c"};
        if (a.charAt(0) != '-') {
            char[] d = a.toCharArray();
            String s = "+";
            a = s.concat(a);
        }
        Matcher op = cn.matcher(a);
        String[] ts = cn.split(a);
        if (a.charAt(0) != '-' && ts.length > 4) {
            System.out.println("El formato de la ecuacion es incorrecta");
        } else if (a.charAt(0) == '-' && ts.length <= 4) {
            int id = 0;
            ts[0] = ts[0].substring(1);
            for (String i : ts) {
                if (i.charAt(0) == 'x') {
                    i = "1x";
                }
                if (i.indexOf("x") != -1) {
                    i = i.substring(0, i.indexOf("x"));
                    fn.put(cu[id], Integer.parseInt(op.group(id) + i));
                } else {
                    if (id == 1) {
                        fn.put("x", 0);
                        fn.put("c", Integer.parseInt(op.group(id) + i));
                        id += 1;
                    } else {
                        fn.put(cu[id], Integer.parseInt(op.group(id) + i));
                    }
                }
                id += 1;
            }
            if (id == 2) {
                fn.put("c", 0);
            }
        } else if (a.charAt(0) == '+' && ts.length <= 4) {
            int id = 0;
            System.out.println(Arrays.toString(ts));
            ts = Arrays.copyOfRange(ts, 1, ts.length);
            for (String i : ts) {
                if (i.charAt(0) == 'x') {
                    i = "1x";
                }
                if (i.indexOf("x") != -1) {
                    i = i.substring(0, i.indexOf("x"));
                    fn.put(cu[id], Integer.parseInt(op.group(id) + i));
                } else {
                    if (id == 1) {
                        fn.put("x", 0);
                        fn.put("c", Integer.parseInt(op.group(id) + i));
                        id += 1;
                    } else {
                        fn.put(cu[id], Integer.parseInt(op.group(id) + i));
                    }
                }
                id += 1;
            }
            if (id == 2) {
                fn.put("c", 0);
            }
        } else {
            System.out.println("El formato de la ecuacion es incorrecta");
        }
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
