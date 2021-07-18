package com.estudo.mongo.impl;

import com.estudo.mongo.model.Funcionario;
import com.estudo.mongo.repository.FuncionarioRepository;
import com.estudo.mongo.service.FuncionarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FuncionarioServiceImpl implements FuncionarioService {

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Override
    public List<Funcionario> obterTodos() {
        return this.funcionarioRepository.findAll();
    }

    @Override
    public Funcionario obterPorCodigo(String codigo) {
        return this.funcionarioRepository
                .findById(codigo)
                .orElseThrow(()-> new IllegalArgumentException("Funcionario não existe"));
    }

    @Override
    public Funcionario criar(Funcionario funcionario) {

        if(funcionario.getChefe() != null){
            Funcionario chefe =
                    this.funcionarioRepository
                            .findById(funcionario.getChefe().getCodigo())
                            .orElseThrow(() -> new IllegalArgumentException("Chefe inexistente."));

            funcionario.setChefe(chefe);
        }


        return this.funcionarioRepository.save(funcionario);
    }

    @Override
    public void excluir(Funcionario funcionario) {
        this.funcionarioRepository.delete(funcionario);
        System.out.println("Funcionario excluido com sucesso");
    }


    @Override
    public List<Funcionario> obterFuncionariosPorRangeDeIdade(Integer de, Integer ate) {
        return this.funcionarioRepository.obterFuncionariosPorRangeDeIdade(de, ate);
    }

    @Override
    public List<Funcionario> obterFuncionariosPorNome(String nome) {
        return this.funcionarioRepository.findByNome(nome);
    }
}
