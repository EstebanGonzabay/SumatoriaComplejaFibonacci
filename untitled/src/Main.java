import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Solicitar N al usuario
        System.out.print("Ingrese el valor de N (cantidad de términos): ");
        int n = sc.nextInt();

        // Variables para la sumatoria total
        double sumatoria = 0.0;

        // Variables para Fibonacci (numeradores)
        long fib1 = 0; // F(0)
        long fib2 = 1; // F(1)
        long fibActual = 0;

        // Variable para rastrear en qué grupo estamos y posición dentro del grupo
        int grupoActual = 1;        // Tamaño del grupo actual (1, 2, 3, 4...)
        int posicionEnGrupo = 0;    // Posición dentro del grupo actual (0 a grupoActual-1)
        int contadorGrupos = 1;     // Contador de grupos completados

        // Procesar cada término de la sucesión
        for (int i = 1; i <= n; i++) {
            // ===== CALCULAR NUMERADOR (FIBONACCI) =====
            if (i == 1) {
                fibActual = 0;
            } else if (i == 2) {
                fibActual = 1;
            } else {
                fibActual = fib1 + fib2;
                fib1 = fib2;
                fib2 = fibActual;
            }

            // El denominador es simplemente la posición
            int denominador = i;

            // ===== DETERMINAR OPERACIÓN Y SIGNO SEGÚN EL GRUPO =====
            // Los grupos impares (1, 3, 5...) usan Potencia y signo +
            // Los grupos pares (2, 4, 6...) usan Raíz y signo -
            boolean esPotencia = (grupoActual % 2 == 1); // true si grupo impar
            boolean esPositivo = (grupoActual % 2 == 1); // true si grupo impar

            double valorTermino = 0.0;
            String operacionStr = "";

            if (esPotencia) {
                // ===== CALCULAR EL N-ÉSIMO NÚMERO PRIMO =====
                // Necesitamos el primo en la posición 'i'
                int contadorPrimos = 0;
                int numeroPrimo = 2;
                int candidato = 2;

                while (contadorPrimos < i) {
                    // Verificar si 'candidato' es primo
                    boolean esPrimo = true;

                    if (candidato < 2) {
                        esPrimo = false;
                    } else if (candidato == 2) {
                        esPrimo = true;
                    } else if (candidato % 2 == 0) {
                        esPrimo = false;
                    } else {
                        // Verificar divisibilidad desde 3 hasta raíz cuadrada
                        for (int div = 3; div * div <= candidato; div += 2) {
                            if (candidato % div == 0) {
                                esPrimo = false;
                                break;
                            }
                        }
                    }

                    if (esPrimo) {
                        contadorPrimos++;
                        numeroPrimo = candidato;
                    }
                    candidato++;
                }

                // Calcular potencia: (numerador/denominador)^primo
                double base = (double) fibActual / denominador;
                double resultado = 1.0;
                for (int exp = 0; exp < numeroPrimo; exp++) {
                    resultado *= base;
                }

                valorTermino = resultado;
                operacionStr = "(" + fibActual + "/" + denominador + ")^" + numeroPrimo;

            } else {
                // ===== CALCULAR RAÍZ CUADRADA =====
                double fraccion = (double) fibActual / denominador;
                valorTermino = Math.sqrt(fraccion);
                operacionStr = "√(" + fibActual + "/" + denominador + ")";
            }

            // Aplicar el signo
            if (!esPositivo) {
                valorTermino = -valorTermino;
            }

            // Sumar a la sumatoria total
            sumatoria += valorTermino;

            // Mostrar el término
            String signo = esPositivo ? "+" : "-";
            System.out.printf("Término %d: %s%s = %.6f (Sumatoria parcial: %.6f)\n",
                    i, signo, operacionStr, Math.abs(valorTermino), sumatoria);

            // ===== ACTUALIZAR CONTROL DE GRUPOS =====
            posicionEnGrupo++;

            // Si completamos el grupo actual, pasamos al siguiente
            if (posicionEnGrupo >= grupoActual) {
                grupoActual++;          // Siguiente grupo es más grande
                posicionEnGrupo = 0;    // Reiniciar posición
                contadorGrupos++;
            }
        }

        // Mostrar resultado final
        System.out.println("\n========================================");
        System.out.printf("SUMATORIA TOTAL de %d términos: %.6f\n", n, sumatoria);
        System.out.println("========================================");

        sc.close();
    }
}