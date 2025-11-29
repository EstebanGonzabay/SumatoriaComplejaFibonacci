# Solución Completa del Ejercicio

Te proporciono la explicación detallada del código Java que resuelve el ejercicio cumpliendo todas las restricciones.

## Explicación de la Lógica Implementada

### 1. Lógica de Grupos y Alternancia

Se utiliza un sistema de control para manejar los grupos de tamaño creciente.

```java
int grupoActual = 1;        // Tamaño del grupo (1, 2, 3, 4...)
int posicionEnGrupo = 0;    // Posición dentro del grupo

Grupos de tamaño creciente: El primer grupo tiene 1 término, el segundo grupo 2 términos, el tercer grupo 3 términos, etc.

Alternancia de operaciones:

Grupos impares (1, 3, 5...) utilizan potencia con signo positivo (+).

Grupos pares (2, 4, 6...) utilizan raíz con signo negativo (-).

Transición: Después de procesar cada término, se incrementa posicionEnGrupo. Cuando este iguala al tamaño del grupoActual, se reinicia la posición y se aumenta el tamaño del grupo.

2. Cálculo de Números Primos (sin métodos externos)
Para cumplir la restricción de no usar métodos auxiliares, calculamos el primo correspondiente dentro del bucle principal.

int contadorPrimos = 0;
int candidato = 2;

while (contadorPrimos < i) {
    // Verificar si 'candidato' es primo
    boolean esPrimo = true;
    
    for (int div = 2; div <= candidato / 2; div++) {
        if (candidato % div == 0) {
            esPrimo = false;
            break;
        }
    }
    
    if (esPrimo) {
        contadorPrimos++;
        numeroPrimo = candidato;
    }
    if (contadorPrimos < i) {
        candidato++;
    }
}

Se usa un contador que itera desde 2 en adelante.

Para cada número candidato, se verifica si es primo probando su divisibilidad.

Cuando se encuentra el i-ésimo primo, se guarda y se sale del bucle de búsqueda.

3. Fibonacci sin Arrays
No se almacenan todos los números, solo los necesarios para el cálculo actual.

long fib1 = 0, fib2 = 1, fibActual = 0;
// En cada iteración: 
// fibActual = fib1 + fib2; 
// fib1 = fib2; 
// fib2 = fibActual;

Solo se mantienen las dos últimas posiciones en memoria (fib1 y fib2), actualizándolas en cada paso de la iteración principal.

4. Cálculo de Potencia sin Math.pow()
Para cumplir con el uso de estructuras básicas:

double resultado = 1.0;
for (int exp = 0; exp < numeroPrimo; exp++) {
    resultado *= base;
}

Se multiplica la base por sí misma n veces (donde n es el número primo calculado anteriormente) mediante un bucle for simple.

