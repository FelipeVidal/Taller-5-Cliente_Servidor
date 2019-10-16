/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agenciaviajes.servercentral;

/**
 *
 * @author Felipe
 */
public interface IServidorCentral {
     public void registrarCliente(int id,String nombres,String apellidos,String direccion,int celular,String email,String sexo);
}
