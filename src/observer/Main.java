import java.util.ArrayList;
import java.util.List;

// Interfaz Observador
interface Jugador {
    void actualizar(int salud);
}

// Clase Sujeto (el jefe del juego)
class Jefe {
    private final List<Jugador> jugadores = new ArrayList<>();
    private int salud;

    public Jefe(int saludInicial) {
        this.salud = saludInicial;
    }

    // Método para agregar jugadores (observadores)
    public void agregarJugador(Jugador jugador) {
        jugadores.add(jugador);
    }

    // Método para eliminar jugadores
    public void eliminarJugador(Jugador jugador) {
        jugadores.remove(jugador);
    }

    // Notificar a todos los jugadores cuando cambia la salud del jefe
    public void notificarJugadores() {
        for (Jugador jugador : jugadores) {
            jugador.actualizar(salud);
        }
    }

    // Método para reducir la salud del jefe
    public void recibirDanio(int danio) {
        salud -= danio;
        System.out.println("El jefe ha recibido " + danio + " puntos de ataque. Salud restante: " + salud);
        notificarJugadores();
    }
}

// Implementación concreta del observador (jugador)
class JugadorConcreto implements Jugador {
    private final String nombre;

    public JugadorConcreto(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public void actualizar(int salud) {
        System.out.println(nombre + " ha sido notificado. La salud del jefe ahora es: " + salud);
        if (salud <= 0) {
            System.out.println(nombre + " celebra la derrota del jefe.");
        }
    }
}

// Clase principal para probar el patrón Observer
public class Main {
    public static void main(String[] args) {
        // Crear un jefe con 100 puntos de salud
        Jefe jefeFinal = new Jefe(100);

        // Crear algunos jugadores observadores
        Jugador jugador1 = new JugadorConcreto("Jugador 1");
        Jugador jugador2 = new JugadorConcreto("Jugador 2");
        Jugador jugador3 = new JugadorConcreto("Jugador 3");

        // Agregar los jugadores al jefe
        jefeFinal.agregarJugador(jugador1);
        jefeFinal.agregarJugador(jugador2);
        jefeFinal.agregarJugador(jugador3);

        // El jefe recibe daño y notifica a los jugadores
        jefeFinal.recibirDanio(30); // Salud restante: 70
        jefeFinal.recibirDanio(50); // Salud restante: 20
        jefeFinal.recibirDanio(20); // Salud restante: 0
    }
}
