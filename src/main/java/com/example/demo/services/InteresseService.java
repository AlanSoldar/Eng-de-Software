package com.example.demo.services;

import com.example.demo.data_transfer_objects.InteresseDTO;
import com.example.demo.entities.Interesse;
import com.example.demo.entities.Usuario;
import com.example.demo.repositories.InteresseRepository;
import com.example.demo.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class InteresseService {
    @Autowired
    private InteresseRepository interesseRepository;

    public Page<Interesse> listInteresses(Pageable page) {
        return interesseRepository.findAll(page);

    }
    public void processarMatch (Usuario usuarioA, Usuario usuarioB){

    }


    /**
        Cria um interesse em determinado produto e checa se houve um match
     **/
    public void processarInteresse(Interesse interesse){

        interesseRepository.save(interesse);

        interesseRepository.findByInteresseId(interesse.getId().getInteressadoId(),interesse.getId().getDonoId(),interesse.getId().getProdutoId())
                .ifPresent(interesse1 -> {

                });
        System.out.println("interesse salvo");
    }
}
