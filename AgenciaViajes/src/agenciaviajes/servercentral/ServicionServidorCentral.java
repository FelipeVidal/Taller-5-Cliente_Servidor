/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agenciaviajes.servercentral;


import agenciaviajes.acceso.ServicioRegistraduriaSocket;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Felipe
 */
public class ServicionServidorCentral implements IServidorCentral {
    private Socket socket = null;
    private Scanner entradaDecorada;
    private PrintStream salidaDecorada;
    private final String IP_SERVIDOR = "localhost";
    private final int PUERTO = 6000;

    @Override
    public void registrarCliente(int id, String nombres, String apellidos, String direccion, int celular, String email, String sexo) {
        try {
            conectar(IP_SERVIDOR, PUERTO);
            leerFlujoEntradaSalida(id,nombres,apellidos,direccion,celular,email,sexo);
            cerrarFlujos();
            desconectar();

        } catch (IOException ex) {
            Logger.getLogger(ServicioRegistraduriaSocket.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private void leerFlujoEntradaSalida(int id, String nombres, String apellidos, String direccion, int celular, String email, String sexo) throws IOException {
        String respuesta = "";
        entradaDecorada = new Scanner(socket.getInputStream());
        salidaDecorada = new PrintStream(socket.getOutputStream());
        salidaDecorada.flush();
        // Usando el protocolo de comunicación
        salidaDecorada.println("RegistrarCliente," + id + "," + nombres + "," + apellidos + "," + direccion + "," + celular + "," + email + "," + sexo);
        //salidaDecorada.println("RegistrarCliente,123,'felipe','vidal',calle1,310255555,'felipe@dflksl',Masculino");

        if (entradaDecorada.hasNextLine()) {
            respuesta = entradaDecorada.nextLine();
        }
        
    }

    private void cerrarFlujos() {
        salidaDecorada.close();
        entradaDecorada.close();
    }

    private void desconectar() {
        try {
            socket.close();
        } catch (IOException ex) {
            Logger.getLogger(ServicioRegistraduriaSocket.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void conectar(String address, int port) throws IOException {
        socket = new Socket(address, port);
        System.out.println("Conectado");
    }
    
    
}
