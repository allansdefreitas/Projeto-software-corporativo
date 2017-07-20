/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sistemapetshop.bean;

import br.com.sistemapetshop.util.Recaptcha;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import org.hibernate.validator.constraints.NotBlank;

/**
 *
 * @author jonathanpereira
 */
@ManagedBean(name = "managedLoginBean")
@RequestScoped
public class LoginBean {
    
    @NotBlank
    private String login;
    
    @NotBlank
    private String senha;
    private FacesContext facesContext;

    public String login() {
        
        try {
            
            facesContext = FacesContext.getCurrentInstance();
            Recaptcha recaptcha = new Recaptcha(facesContext);

            if (recaptcha.validar()) {
                HttpServletRequest request = (HttpServletRequest) facesContext.getExternalContext().getRequest();
                request.login(login, senha);
                
                facesContext.getExternalContext().getSession(true);
            } else {
                
                setLogin(null);
                adicionarMensagem("Captcha inválido!");
                
                return "falha";
            }

        } catch (ServletException ex) {
            
            ex.printStackTrace();
            setLogin(null);
            adicionarMensagem("Senha ou usuário inválidos!");
            
            return "falha";
        }

        return "sucesso";
    }

    private void adicionarMensagem(String mensagem) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, mensagem, null);
        facesContext.addMessage(null, message);
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String usuario) {
        this.login = usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

}
