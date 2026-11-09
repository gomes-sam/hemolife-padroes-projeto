package com.hemolife.adapter;
import com.hemolife.model.Usuario;

// A interface que o nosso sistema HemoLife espera utilizar.
public interface RegistroNacionalService {
    void sincronizarDoacaoNacional(Usuario usuario);
}
