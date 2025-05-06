package br.com.alura;

import br.com.alura.refl.Transformer;

import java.lang.reflect.InvocationTargetException;

public class PessoaService {

    public PessoaDTO list() throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        Pessoa pessoa = new PessoaRepository().list();

        Transformer transformer = new Transformer();
        PessoaDTO pessoaDTO = transformer.transformToDTO(pessoa);

        System.out.println("O teste saiu assim");
//        Arrays.stream(teste.getDeclaredFields()).forEach(item -> System.out.println(item));
//        PessoaDTO pessoaDTO = new PessoaDTO(pessoa.getNome(), pessoa.getCpf());
        return pessoaDTO;
    }
}
