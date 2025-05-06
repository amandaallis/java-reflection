package br.com.alura;

import br.com.alura.refl.Transformer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;

public class TransformerTest {

    @Test
    void shouldTransformToObjectDto() throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        Pessoa entityExample = new Pessoa(1, "João", "1234");
        Transformer transformer = new Transformer();
        PessoaDTO pessoaDTO = transformer.transformToDTO(entityExample);

        Assertions.assertInstanceOf(PessoaDTO.class, pessoaDTO);

        Assertions.assertAll(() -> {
            Assertions.assertEquals(pessoaDTO.getNome(), entityExample.getNome());
            Assertions.assertEquals(pessoaDTO.getCpf(), entityExample.getCpf());
        });
    }

   @Test
   void shouldNotCreateWhenDoNotExistDTO() throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        Endereco endereco = new Endereco("Nome da rua", 40);
        Assertions.assertThrows(ClassNotFoundException.class, () -> {
            Transformer transformer = new Transformer();
            transformer.transformToDTO(endereco);
        });
   }

   @Test
   void shouldTransformWhenSomeFieldIsNull() throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
       Pessoa entityExample = new Pessoa(1, "João", null);
       Transformer transformer = new Transformer();
       PessoaDTO pessoaDTO = transformer.transformToDTO(entityExample);

       Assertions.assertInstanceOf(PessoaDTO.class, pessoaDTO);
       Assertions.assertEquals(pessoaDTO.getCpf(), entityExample.getCpf());
       Assertions.assertNull(pessoaDTO.getCpf());
   }
}
/*. O termo "fixture" no contexto de desenvolvimento de software refere-se a um conjunto de dados de teste
ou a um estado predefinido que é usado como base para realizar testes.*/
