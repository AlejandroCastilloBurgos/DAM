/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.persistenciamodulo;

/**
 *
 * @author Castillo
 */
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class PersistenciaModulo {

    public static void main(String[] args) {
        // Crear una instancia de la entidad Modulo
        final Modulo modulo = new Modulo();
        modulo.setNombre("BASES DE DATOS");

        EntityManagerFactory emf = null;
        EntityManager em = null;

        try {
            // Crear el EntityManagerFactory y EntityManager
            emf = Persistence.createEntityManagerFactory("PersistenciaModuloPU");
            em = emf.createEntityManager();

            // Iniciar una transacción
            em.getTransaction().begin();

            // Persistir la entidad en la base de datos
            em.persist(modulo);

            // Comprometer la transacción
            em.getTransaction().commit();

            System.out.println("Modulo guardado exitosamente!");
        } catch (Exception e) {
            e.printStackTrace();
            if (em != null) {
                // En caso de error, revertir la transacción
                em.getTransaction().rollback();
            }
        } finally {
            if (em != null) {
                em.close(); // Cerrar el EntityManager
            }
            if (emf != null) {
                emf.close(); // Cerrar el EntityManagerFactory
            }
        }
    }
}

