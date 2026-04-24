//paquete 
package practica2act3;

//clase principal
public class Practica2act3 {
    
    //metodo main, metodo principal
    public static void main(String[] args) {
        
        //Modulo m, Multiplicador a, Incremento c, Semilla xn (declaro las 4 variables)
        int m=65536, a=22695477, c=1, xn=101;
        
        //sout para imprimir en la consola (imprime la parte superior de la cabecera de la tabla, (\t para alinear)
        System.out.println("i\tXn\ta*Xn\tXn+1\tUn");
        //imprime en la fila 0 junto a la semilla (- no calcula nada)
        System.out.println("0\t"+xn+"\t-\t"+xn+"\t-");
        
        //bucle for que se repite 10 veces, i es el numero de iteracion 
        for(int i=1; i<=10; i++){
            //calculos en una sola linea (formula Xn+1=(a*Xn) mod m, Xn+1=(a*Xn+c) mod m)columna a(Xn), columna Rn(Xn+1)
            int axn=a*xn+c, xn1=axn%m;
            //imprime la primera fila de la tabla (%d = entero),(%.4f = decimal con 4 cifras),((double)xn1/m = calcula Un = Xn+1/m),(double para que la iteracion de decimales) 
            System.out.printf("%d\t%d\t%d\t%d\t%.4f\n", i, xn, axn, xn1, (double)xn1/m);
            //actualiza Xn con el nuevo valor para la sig vuelta del for
            xn=xn1;
        }
    }
}