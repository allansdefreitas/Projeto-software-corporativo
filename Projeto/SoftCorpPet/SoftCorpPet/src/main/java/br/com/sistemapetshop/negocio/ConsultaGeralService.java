/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sistemapetshop.negocio;

import static br.com.sistemapetshop.acesso.Papel.ADMINISTRADOR;
import static br.com.sistemapetshop.acesso.Papel.CLIENTE;
import static br.com.sistemapetshop.acesso.Papel.FUNCIONARIO;
import br.com.sistemapetshop.model.ConsultaGeral;
import javax.annotation.security.DeclareRoles;
import javax.ejb.Stateless;

/**
 *
 * @author joanthanpereira
 */
@Stateless
@DeclareRoles({ADMINISTRADOR, FUNCIONARIO, CLIENTE})
public class ConsultaGeralService extends Service<ConsultaGeral>{
    
    public ConsultaGeralService(){
        super(ConsultaGeral.class);
    }
}