package ejercicio.d;

public class EjercicioD {
    public static void main(String[] args) {
        final int A = 22695477;   //multiplicar a
        final int C = 1;   //incremento c
        final int M = 65536;  //modulo m
        int x = 10;         //semilla X0
        
        //inicializamos los parametros formula Xn+1=(aXn + c) mod m

        System.out.println("Iter\tXn\tNext\tUn");
        for (int i = 0; i < 6; i++) {
            double u = (double) x / M;
            int next = (A * x + C) % M;
            System.out.printf("%d\t%d\t%d\t%.4f%n", i, x, next, u);
            x = next;
        }
    }
}