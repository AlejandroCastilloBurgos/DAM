/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.proyectooberver;

/**
 *
 * @author Castillo
 */
public class ProyectoOberver {

    public static void main(String[] args) {
    Escalera escalera = new Escalera();
    Persona ana = new Persona("Ana");
    Persona pepe = new Persona ("pepe");
    
    
    //Gente se subsicriba
    escalera.addCotilla(ana);
    escalera.addCotilla(pepe);
    escalera.addCotilla(
            new Escalera.ObservadorDeMirilla() {
        @Override
        public void aviso(String info) {
            System.out.println("arggggggghhh!!" + info);
        }
            }
    );
    
    
    //lanzar el evento
    escalera.dispararEvento("Ha pasado Juan");
    escalera.dispararEvento("El del segundo ha llegado a las 2AM");
    
    }
}
