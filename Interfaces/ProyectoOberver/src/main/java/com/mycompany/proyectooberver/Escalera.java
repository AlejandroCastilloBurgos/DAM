/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyectooberver;

import java.util.ArrayList;

/**
 *
 * @author Castillo
 */
public class Escalera {
   public interface ObservadorDeMirilla {
       void aviso (String info);
       
   }
    
  private ArrayList<ObservadorDeMirilla> misCotillas;
  public Escalera (){
      misCotillas = new ArrayList<ObservadorDeMirilla>();
  }
  public void addCotilla(ObservadorDeMirilla cotilla){
      misCotillas.add(cotilla);
  }
  public void dispararEvento(String info){
      for(ObservadorDeMirilla om: misCotillas){
      om.aviso(info);
      }
      
      
  }
}
