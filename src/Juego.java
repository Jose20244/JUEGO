import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Juego {
    private List<Jugador> jugadores;
    private List<Cartas> cartas;
    private int numeroRondas;

    // Constructor
    public Juego(int numeroJugadores, int numeroRondas) {
        this.jugadores = crearJugadores(numeroJugadores);
        this.numeroRondas = numeroRondas;
        this.cartas = crearBaraja();
        barajar();
    }

    private List<Jugador> crearJugadores(int numeroJugadores) {
        List<Jugador> jugadores = new ArrayList<>();
        for (int i = 0; i < numeroJugadores; i++) {
            jugadores.add(new Jugador("Jugador" + (i + 1), 50));
        }
        return jugadores;
    }

    public List<Cartas> crearBaraja() {
        List<Cartas> baraja = new ArrayList<>();
        String[] simbolos = {"Corazón", "Diamante", "Trébol", "Espadas"};
        String[] valores = {"As", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};

        for (String simbolo : simbolos) {
            for (String valor : valores) {
                Cartas carta = new Cartas(valor, simbolo);
                baraja.add(carta);
            }
        }
        return baraja;
    }

    private void barajar() {
        Collections.shuffle(cartas);
    }

    private void repartirCartas() {
        for (Jugador jugador : jugadores) {
            jugador.agregarCarta(cartas.remove(0));
            jugador.agregarCarta(cartas.remove(0));
        }
    }

    private void mostrarCartas() {
        for (Jugador jugador : jugadores) {
            jugador.mostrarMano();
        }
    }

    public void jugarBlackjack() {
        repartirCartas();
        mostrarCartas();

        for (Jugador jugador : jugadores) {
            while (jugador.getTotalPuntos() < 17) {
                jugador.agregarCarta(cartas.remove(0));
                jugador.sumarPuntos(jugador.getCartas().get(jugador.getCartas().size() - 1).getValorNumerico());
                jugador.mostrarMano();
            }
        }

        // Determinar el ganador
        Jugador ganador = null;
        for (Jugador jugador : jugadores) {
            if (jugador.getTotalPuntos() <= 21) {
                if (ganador == null || jugador.getTotalPuntos() > ganador.getTotalPuntos()) {
                    ganador = jugador;
                }
            }
        }

        if (ganador != null) {
            System.out.println("El ganador es: " + ganador.getNombre() + " con " + ganador.getTotalPuntos() + " puntos.");
        } else {
            System.out.println("Todos los jugadores se pasaron de 21. No hay ganador.");
        }
    }
}