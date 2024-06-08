package Test;

import java.util.Random;
import java.util.concurrent.ForkJoinPool;

import Datos.QuicksortForkJoinRandomized;
import Datos.QuickSortPivotAleatorio;
import java.util.Arrays;
public class Testeo {
	
	public static int[] generarArrayAleatorio(int n, int min, int max) {
	    // Declaración del array
	    int[] arr = new int[n];
	    
	    // Generación de números aleatorios
	    Random random = new Random();
	    for (int i = 0; i < n; i++) {
	        arr[i] = random.nextInt(max - min + 1) + min;
	    }
	    
	    return arr;
	}
	
	private static String arrayToString(int[] array){
        String aux = "[" + array[0];
        for(int i = 1; i < array.length; i++){
            aux += ", " + array[i];
        }
        return aux + "]";
    }
	
	///---------------------------------------------------------------------------------------------------------------------------///	
	///------------------------------------------------------MAIN-----------------------------------------------------------------///
	///---------------------------------------------------------------------------------------------------------------------------///
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//Arrays que serán ordenados
		int[] aux = generarArrayAleatorio(10000000, 1, 90000);
        int[] A = Arrays.copyOf(aux, aux.length);
        int[] B = Arrays.copyOf(aux, aux.length);
        System.out.println("El arreglo a ordenar tiene "+ aux.length +" elementos");
        
        ///---------------------------------------------------------------------------------------------------------------------------///	
    	///----------------------------------------------------CONCURRENTE------------------------------------------------------------///
    	///---------------------------------------------------------------------------------------------------------------------------///
        
        //Se crea la tarea que va a ordenar al vector, todavía no la hace
        QuicksortForkJoinRandomized quicksortTaskRandom = new QuicksortForkJoinRandomized(A);
        
        //Creo la pileta de hilos que genera muchos hilos, tantos como pueda
        ForkJoinPool pool = new ForkJoinPool();
        
        //Muestro la cantidad de hilos ornadas
        System.out.println("Cantidad de hilos creados por la pileta -> " + pool.getParallelism());
        
        //Tiempo de inicio concurrente
        double tiempoInicio = System.currentTimeMillis();
       
        //Desde la pileta de hilos invocamos a la tarea
        pool.invoke(quicksortTaskRandom);
        
        //Tiempo Final concurrente
        double tiempoFinal = System.currentTimeMillis() - tiempoInicio;
        
        System.out.println("A tiempo con concurrencia --> " + tiempoFinal);
        
        //System.out.println("A (ordenado com Quicksort com pivô aleatório) = " + arrayToString(A));
        
        
        ///---------------------------------------------------------------------------------------------------------------------------///	
    	///-----------------------------------------------------SECUENCIAL------------------------------------------------------------///
    	///---------------------------------------------------------------------------------------------------------------------------///
        
        //piso el valor del tiempo de inicio
        tiempoInicio = System.currentTimeMillis();
        
        //ejecuto el ordenamiento secuencial
        QuickSortPivotAleatorio.sort(B, 0, B.length - 1);
        
        //piso el valor del tiempo de final
        tiempoFinal = System.currentTimeMillis() - tiempoInicio;
  
        System.out.println("B tiempo con secuencial --> " + tiempoFinal);
        //System.out.println("B (ordenado con secuencial) = " + arrayToString(B));
	}

}


