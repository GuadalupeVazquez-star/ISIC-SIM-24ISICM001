//declaracion del paquete principal 
package practica3act4fpiso;

//clase principal
public class Practica3act4fpiso {
    
    //cambio************************************* refactorizacion del generador, es metodo y detcta d solo
      public static double[] generarSecuencia(long semilla, int cantidad){
        
        int d = String.valueOf(semilla).length(); //cambio**********detecta digitos automaticamente
        int longitudCuadrado = 2 * d; //cambio*******************2 digitos para el cuadrado
        double[] secuencia = new double [cantidad];
        long xn = semilla; // Semilla 
        
        System.out.println("i   \tXi    \tXi^2" + longitudCuadrado + "   \tCentr  \tXi+1  \tUi");
        
        //bucle for de 0 a 5 iteraciones
        for(int i=0; i < cantidad; i++){ //cambio***********************++
            
            //paso 1: elevar Xi al cuadrado
            long xn2 = xn * xn;
            
            String s = String.format("%0" + longitudCuadrado + "d", xn2);
            int inicio = (longitudCuadrado - d) / 2;
            String centro = s.substring(inicio, inicio + d);

            System.out.printf("%d \t%d \t%s \t", i, xn, xn2, s);

            xn = Long.parseLong(centro);
            secuencia[i] = xn / Math.pow(10, d);

            System.out.printf("%0" + d + "d \t%d \t%.4f \n", xn, xn, secuencia[i]);
            
            //paso 2: asegurar 8 digitos usando funcion piso y modulo
            //Si xn2 < 10^7, le faltan ceros
            //long dig8 = xn2;
            //if (dig8 < Math.pow(10, 2*d - 1)) {
             //   dig8 = dig8 + (long)Math.pow(10, 2*d - 1) * (int)Math.floor(xn2 / Math.pow(10, 2*d - 1));
            //}
            //Para mostrar los 8 digitos con ceros: %08d 
            //String s = String.format("%08d", xn2);
            
            //System.out.printf("%d \t%d  \t%d  \t%s  \t", i, xn, xn2, s);
            
            //paso 3: extraer D digitos centrales con funcion piso y modulo
            //Formula: piso( Xi^2 / 10^(D/2) ) mod 10^D
            //long divisor = (long)Math.pow(10, d/2); // 10^2 = 100 para D=4
            //long modulo = (long)Math.pow(10, d);    // 10^4 = 10000 para D=4
            //xn = (long)Math.floor(xn2 / divisor) % modulo;
            
            //Ui = Xi+1 / 10^D usando funcion piso para evitar decimales en la division
            //double ui = xn / Math.pow(10, d);
            
            //System.out.printf("%04d  \t%d  \t%.4f  \n", xn, xn, ui);
            
            //cambio**************************************
            // Rellenar con ceros a la izquierda hasta tener 2d dígitos
        }
          return secuencia;
    
      }
       // PUNTO 2: Refactorización del Método Chi-cuadrado
    public static void realizarPruebaChi(double[] muestra, int k, double valorCritico) {
        int n = muestra.length;
        double esperado = (double) n / k;
        int[] observados = new int[k];

        // Llenar histograma
        for (double num : muestra) {
            int intervalo = (int) (num * k);
            if (intervalo == k) intervalo--;
            observados[intervalo]++;
        }

        System.out.println("\n===========================================================================");
        System.out.println(" PRUEBA CHI-CUADRADA DE UNIFORMIDAD");
        System.out.println("===========================================================================");
        System.out.println("Total de números en la muestra (n): " + n);
        System.out.println("Número de subintervalos (k): " + k);
        System.out.printf("Frecuencia Esperada (E_i) por intervalo: %d / %d = %.1f\n\n", n, k, esperado);

        System.out.printf("%-15s | %-15s | %-20s\n", "Intervalo", "Observado (O_i)", "Esperado (E_i)", "(O_i - E_i)^2 / E_i");
        System.out.println("---------------------------------------------------------------------------");

        double chiCuadradaCalculada = 0;
        int sumaObservados = 0;

        for (int i = 0; i < k; i++) {
            String rango = String.format("[%.1f - %.1f)", (double)i/k, (double)(i+1)/k);
            int o_i = observados[i];
            double calculoParcial = Math.pow(o_i - esperado, 2) / esperado;
            chiCuadradaCalculada += calculoParcial;
            sumaObservados += o_i;
            System.out.printf("%-15s | %-15d | %-15.1f | %-20.4f\n", rango, o_i, esperado, calculoParcial);
        }

        System.out.println("---------------------------------------------------------------------------");
        System.out.printf("%-15s | %-15d | %-15.1f | Estadístico = %.4f\n\n", "TOTAL", sumaObservados, (k * esperado), chiCuadradaCalculada);

        int gradosLibertad = k - 1;
        System.out.println("Grados de libertad (k - 1): " + gradosLibertad);
        System.out.printf("Valor crítico de tablas: %.4f\n\n", valorCritico);

        // PUNTO 5: Veredicto final
        System.out.println("CONCLUSIÓN:");
        if (chiCuadradaCalculada <= valorCritico) {
            System.out.printf("Como %.4f <= %.4f:\n", chiCuadradaCalculada, valorCritico);
            System.out.println("-> H0 Aceptada: Distribución Uniforme");
        } else {
            System.out.printf("Como %.4f > %.4f:\n", chiCuadradaCalculada, valorCritico);
            System.out.println("-> H0 Rechazada: Distribución No Uniforme");
        }
    }

    public static void main(String[] args) {
        // PUNTO 3: Validación con Caso Real X0 = 21435
        System.out.println("=== PUNTO 3: VALIDACIÓN X0 = 21435 ===");
        double[] prueba = generarSecuencia(21435, 5);
        System.out.println("\nPrimeros 5 valores para comparar con cálculo manual:");
        for(int i = 0; i < 5; i++){
            System.out.printf("U%d = %.5f\n", i+1, prueba[i]);
        }

        // PUNTO 4: Prueba de Estrés n = 50
        System.out.println("\n\n=== PUNTO 4: PRUEBA DE ESTRÉS n=50 ===");
        double[] secuencia50 = generarSecuencia(21435, 50);
        realizarPruebaChi(secuencia50, 5, 9.488);
    }
      
}