package com.estudo.mongo.controller;

import com.estudo.mongo.model.Funcionario;
import com.estudo.mongo.service.FuncionarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/funcionarios")
public class FuncionarioController {

    @Autowired
    private FuncionarioService funcionarioService;

    @GetMapping
    public List<Funcionario> obterTodos(){
        return funcionarioService.obterTodos();
    }

    @GetMapping("/{codigo}")
    public Funcionario obterPorCodigo(@PathVariable String codigo){
        return this.funcionarioService.obterPorCodigo(codigo);
    }

    @PostMapping
    public Funcionario criar(@RequestBody Funcionario funcionario){

       return this.funcionarioService.criar(funcionario);

    }

    @DeleteMapping
    public void excluir(@RequestBody Funcionario funcionario){

        this.funcionarioService.excluir(funcionario);

    }

    @GetMapping("/range")
    public List<Funcionario> obterFuncionariosPorRangeDeIdade(@RequestParam("de") Integer de, @RequestParam("ate") Integer para){

        return this.funcionarioService.obterFuncionariosPorRangeDeIdade(de, para);
    }

    @GetMapping("/por-nome")
    public List<Funcionario> obterFuncionariosPorNome(@RequestParam("nome") String nome){

        return this.funcionarioService.obterFuncionariosPorNome(nome);
    }
}
