/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sistema.aplicacao;

import com.sistema.model.Cartao;
import com.sistema.model.Cliente;
import java.util.Calendar;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 *
 * @author Jonathan Romualdo
 */
public class CrudCartao {

    private final static EntityManagerFactory EMF = Persistence.createEntityManagerFactory("sistemapetshopPU");

    public static void main(String[] args) {
        Calendar calendario = Calendar.getInstance();
        calendario.set(2018, 7, 17);

        Cartao cartao = new Cartao("Visa", "2001-2002-2003", calendario.getTime());
        
        // Inserir ------------------------------------ OK
        //inserirCartao(cartao);
        
        // Consultar ------------------------------------ OK
        Cartao cartaoResultado = consultarCartao(Long.parseLong("2"));

        System.out.println("Bandeira: " + cartaoResultado.getBandeira());
        System.out.println("Numero: " + cartaoResultado.getNumero());
        System.out.println("Validade: " + cartaoResultado.getDataValidade());

        // Atualizar ------------------------------- OK
//        cartaoResultado.setBandeira("Master Card");
//        atualizarCartao(cartaoResultado);
//        System.out.println("Bandeira: " + cartaoResultado.getBandeira());
//        
        // Deletar ------------------------------------ NOK
        deletarCartao(cartaoResultado);
   

    }

    public static void inserirCartao(Cartao cartao) {
        EntityManager em = null;
        EntityTransaction et = null;

        try {
            em = EMF.createEntityManager();
            et = em.getTransaction();

            et.begin();
            em.persist(cartao);
            et.commit();
        } catch (Exception ex) {
            if (et != null && et.isActive()) {
                et.rollback();
            }
        } finally {
            if (em != null) {
                em.close();
            }
        }

    }

    public static void atualizarCartao(Cartao cartao) {
        EntityManager em = null;
        EntityTransaction et = null;

        try {
            em = EMF.createEntityManager();
            et = em.getTransaction();

            et.begin();
            em.merge(cartao);
            et.commit();
        } catch (Exception ex) {
            if (et != null && et.isActive()) {
                et.rollback();
            }
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public static void deletarCartao(Cartao cartao) {
        EntityManager em = null;
        EntityTransaction et = null;

        try {
            em = EMF.createEntityManager();
            et = em.getTransaction();
            
            Cartao cartaoRemove = em.merge(cartao);
            
            et.begin();
            em.remove(cartaoRemove);
            et.commit();
        } catch (Exception ex) {
            if (et != null && et.isActive()) {
                et.rollback();
            }
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public static Cartao consultarCartao(Long idCartao) {
        EntityManager em = null;

        Cartao cartaoResultado = null;

        try {
            em = EMF.createEntityManager();

            cartaoResultado = em.find(Cartao.class, idCartao);
        } finally {
            if (em != null) {
                em.close();
            }
        }

        return cartaoResultado;
    }

}
