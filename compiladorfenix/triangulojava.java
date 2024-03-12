/******************************************************************************

Eqüilátero > Todos lados iguais

Isósceles > Dois lados iguais

Escaleno > Todos os lados diferentes

*******************************************************************************/
import java.util.Scanner;

public class Main
{
	public static void main(String[] args) {
	    
	    //intancia obj de  de leitura 
	    Scanner ler = new Scanner (System.in);
	    int a,b,c;
	    
		System.out.println("digite o lado a : ");
		a=ler.nextInt();
		
		System.out.println("digite o lado b : ");
		b=ler.nextInt();
		
		System.out.println("digite o lado c : ");
		c=ler.nextInt();
		
        int  aux=0;
        
        if (a+b > c) {  //verifica se é um Triangulo
            if (a+c>b){
                if (b+c>a){
                     aux=1;
                     System.out.println ("É um Triangulo");
                     if (aux==1){  //verifica que tipo de Triangulo
                     
                     if (a== b) {
                     if (b== c) {System.out.println("Triangulo equilatero"); }
                     }
                     
                     if (a==b){
                       if (b!= c){ System.out.println("Triangulo isosceles"); } 
                     }
                     
                      if (a==c){
                      if ( c!= b){System.out.println("Triangulo isosceles");}
                     }
                     
                     if (b==c){
                     if (c!= a){ System.out.println("Triangulo isosceles"); } 
                     }
                     
                     if (a!=b){
                     if (b!= c){
                     if( a!=c ) {System.out.println("Triangulo escaleno"); } 
                     }
                     }   
                 }
              }
            }
           }
           if (aux == 0) {
               System.out.println ("Não é um Triangulo");
           }
    }
}