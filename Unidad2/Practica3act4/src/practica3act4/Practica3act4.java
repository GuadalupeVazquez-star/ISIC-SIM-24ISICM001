//declaracion del paquete principal 
package practica3act4;

//clase principal
public class Practica3act4 {
    
    //metodo principal main
    public static void main(String[] args) {
        
        //declaracion de la semilla xn(X0)=1234, con la variable tipo long (long para el metodo cuadrado medio,(por el paso 1. elevar al cuadrado) guarda numeros enteros, pero mas grandes que int)
        long xn = 1000;
        //imprime la cabecera de la tabla \t = tabulacion para alinear columnas
        //i iteracion, Xi semilla(x0) , Xi al cuadrado, 8Dig 8digitos, Centr extraer 4 digitos centrales, Xi+1, Ui formula para llegar al resultado) 
        System.out.println("i   \tXi    \tXi^2    \t8Dig    \tCentr  \tXi+1  \tUi");
        
        //bucle for de 0 a 10, i es el numero de iteracion
        for(int i=0; i<=10; i++){
            
            //paso 1 del metodo elevar Xi al cuadrado (ejmp:1234 al cuadrado= 1522)
            long xn2 = xn * xn;
            
            //paso 2 convertir el cuadrado a texto con 8 digitos (%08d = rellenar con ceros a la izquierda hasta 8)
            String s = String.format("%08d", xn2);
            
            //imprime las primeras 4 columnas 
            System.out.printf("%d \t%d  \t%d  \t%s  \t", i, xn, xn2, s);
            
            //paso 3 extrae los 4 digitos centrales (substring(2,6))toma del indice 2 al 5 (Long.parseLong) lo convierte a numero luego actualiza xn para la sig vuelta
            xn = Long.parseLong(s.substring(2, 6));
            
            //imprime las ultimas 3 columnas (%04d= centrales con 4 digitos, %d= Xi+1, %4.f= Ui con 4 decimales xn/10000.0= Xi+1/10´4, \n= salto de linea)
            System.out.printf("%04d  \t%d  \t%.4f  \n", xn, xn, xn/10000.0);
        }
    }
}