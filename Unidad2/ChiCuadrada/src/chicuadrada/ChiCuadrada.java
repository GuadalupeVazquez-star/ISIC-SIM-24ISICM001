package chicuadrada;

public class ChiCuadrada {

    public static void main(String[] args) {
        // 1. Definimos la misma muestra de 30 números pseudoaleatorios
        // Muestra uniforme
        //double[] muestra = {
            //0.15, 0.22, 0.89, 0.45, 0.56, 0.78, 0.12, 0.34, 0.90, 0.67,
            //0.44, 0.21, 0.99, 0.81, 0.55, 0.32, 0.11, 0.29, 0.61, 0.73,
            //0.84, 0.92, 0.05, 0.49, 0.51, 0.66, 0.38, 0.19, 0.77, 0.25 
        //};
        // Muestra sesgada
         double[] muestra = {
           0.01, 0.02, 0.03, 0.04, 0.05, 0.06, 0.07, 0.08, 0.09, 0.10,
           0.11, 0.12, 0.13, 0.14, 0.15, 0.16, 0.17, 0.18, 0.19, 0.01,
           0.02, 0.03, 0.04, 0.05, 0.06, 0.07, 0.08, 0.09, 0.10, 0.11
         };




        int n = muestra.length;
        
        // 2. Definimos el número de intervalos (k). Usaremos 5 intervalos.
        int k = 5;
        double esperado = (double) n / k; // E_i = 30 / 5 = 6.0
        
        // Arreglo para guardar cuántos caen en cada intervalo (Frecuencia Observada)
        int[] observados = new int[k];
        double[] limites = {0.0, 0.2, 0.4, 0.6, 0.8, 1.0};

        // Llenar el histograma (Frecuencias Observadas)
        for (double num : muestra) {
            int intervalo = (int) (num * k);
            if (intervalo == k) {
                intervalo--; // Asegurar que el 1.0 caiga en el último intervalo
            }
            observados[intervalo]++;
        }

        // --- IMPRESIÓN DEL PROCESO PARA LA CLASE ---
        System.out.println("===========================================================================");
        System.out.println("      EJEMPLO PASO A PASO: PRUEBA CHI-CUADRADA DE UNIFORMIDAD (JAVA)");
        System.out.println("===========================================================================");
        System.out.println("Total de números en la muestra (n): " + n);
        System.out.println("Número de subintervalos (k): " + k);
        System.out.printf("Frecuencia Esperada (E_i) por intervalo: %d / %d = %.1f\n\n", n, k, esperado);
        
        // Encabezado de la tabla
        System.out.printf("%-15s | %-15s | %-15s | %-20s\n", "Intervalo", "Observado (O_i)", "Esperado (E_i)", "(O_i - E_i)^2 / E_i");
        System.out.println("---------------------------------------------------------------------------");
        
        double chiCuadradaCalculada = 0;
        int sumaObservados = 0;
        
        // 3. Iteramos sobre cada intervalo para hacer el cálculo fila por fila
        for (int i = 0; i < k; i++) {
            String rango = String.format("[%.1f - %.1f)", limites[i], limites[i+1]);
            int o_i = observados[i];
            
            // Aplicamos el modelo matemático
            double calculoParcial = Math.pow(o_i - esperado, 2) / esperado;
            
            // Sumamos al estadístico total y a la comprobación de observados
            chiCuadradaCalculada += calculoParcial;
            sumaObservados += o_i;
            
            // Imprimimos la fila de la tabla
            System.out.printf("%-15s | %-15d | %-15.1f | %-20.4f\n", rango, o_i, esperado, calculoParcial);
        }
        
        System.out.println("---------------------------------------------------------------------------");
        System.out.printf("%-15s | %-15d | %-15.1f | Estadístico = %.4f\n\n", "TOTAL", sumaObservados, (k * esperado), chiCuadradaCalculada);
        
        // 4. Conclusión con el valor crítico de tablas
        double nivelConfianza = 0.95;
        double alfa = 1 - nivelConfianza;
        int gradosLibertad = k - 1;
        
        // En Java estándar no hay función ppf como en Python.
        // Para este ejercicio educativo (alpha=0.05, gl=4), el valor de tablas fijo es 9.4877
        double valorCritico = 9.4877; 
        
        System.out.println("Grados de libertad (k - 1): " + gradosLibertad);
        System.out.printf("Nivel de significancia (alpha): %.2f\n", alfa);
        System.out.printf("Valor crítico de tablas (X^2_%.2f,_%d): %.4f\n\n", alfa, gradosLibertad, valorCritico);
        
        System.out.println("CONCLUSIÓN:");
        if (chiCuadradaCalculada <= valorCritico) {
            System.out.printf("Como %.4f <= %.4f:\n", chiCuadradaCalculada, valorCritico);
            System.out.println("-> NO SE RECHAZA H0. Los números se distribuyen de manera uniforme (U(0,1)).");
        } else {
            System.out.printf("Como %.4f > %.4f:\n", chiCuadradaCalculada, valorCritico);
            System.out.println("-> SE RECHAZA H0. Los números NO se distribuyen de manera uniforme.");
        }
    }
}