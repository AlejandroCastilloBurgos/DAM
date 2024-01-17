/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyectooberver;

/**
 *
 * @author Castillo
 */
public class Persona implements Escalera.ObservadorDeMirilla{
    private String nombre;
    public Persona(String name){
        nombre = name;
    }
    
    
    public void aviso(String info){
        System.out.println(String.format("Soy %s y ha pasado %s", nombre, info));
    }
    
}
