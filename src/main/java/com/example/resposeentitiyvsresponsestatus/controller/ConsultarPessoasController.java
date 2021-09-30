package com.example.resposeentitiyvsresponsestatus.controller;

import com.example.resposeentitiyvsresponsestatus.PessoaRepository;
import com.example.resposeentitiyvsresponsestatus.controller.response.PessoaResponse;
import com.example.resposeentitiyvsresponsestatus.exception.PessoaNotFoundException;
import com.example.resposeentitiyvsresponsestatus.exception.RequestParamNotExistException;
import com.example.resposeentitiyvsresponsestatus.model.Pessoa;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@RequestMapping("/api/v1")
@RestController
public class ConsultarPessoasController {
    private final PessoaRepository repository;

    public ConsultarPessoasController(PessoaRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/pessoas")
    @ResponseStatus(HttpStatus.OK)
    public List<PessoaResponse> listarPesoas() {
        List<Pessoa> resultList = repository.findAll();
        return resultList.stream().map(PessoaResponse::new).collect(Collectors.toList());
    }

    //tratando com exceptions
    @ResponseStatus(HttpStatus.OK)//para consultas é opcional pois, por padrao o retorno é um status 200|OK
    @GetMapping("/pessoa")
    public PessoaResponse consultarPesoas(@RequestParam(required = false) String nome) throws Exception {

        if (!Objects.isNull(nome)) {
            final Optional<Pessoa> possivelPessoa = repository.findByNome(nome);
            possivelPessoa.orElseThrow(() -> new PessoaNotFoundException("Pessoa nao cadastrada"));
            return new PessoaResponse(possivelPessoa.get());
        }
        throw new RequestParamNotExistException("paramentro não informado");

    }
    //forma diferente de implementar
    @GetMapping("/pesssoas")
    public ResponseEntity<?> consultarPessoasResponse(@RequestParam(required = false) String nome) {

        if (Objects.nonNull(nome)) {

            Optional<Pessoa> pessoa = repository.findByNome(nome);

            if (pessoa.isEmpty()){
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(new PessoaResponse(pessoa.get()));

        }

        List<Pessoa> pessoas = repository.findAll();

        List<PessoaResponse> response = pessoas.stream()
                .map(PessoaResponse::new)
                .collect(Collectors.toList());

        return ResponseEntity.ok().body(response);
    }
}
