import java.util.Scanner;

public class JuegoDeCartas {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        //ingresar numero de jugadores
        System.out.println("Ingrese el numero de jugadores:");
        int n = sc.nextInt();
        //ingresar el numeor de partidas
        System.out.println("Ingrese el numero de partidas o rondas");
        int p = sc.nextInt();
        Juego juego = new Juego(n,p);
        juego.jugar();

    }
}
