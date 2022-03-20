package br.com.tiagoiwamoto.iwtrecibos.core.mapper;

/*
 * Tiago Henrique Iwamoto
 * tiago.iwamoto@gmail.com
 * linkedin.com/in/tiago-iwamoto
 * System specialist
 * 01/02/2022 | 20:24
 */

import br.com.tiagoiwamoto.iwtrecibos.core.entity.Pessoa;
import br.com.tiagoiwamoto.iwtrecibos.entrypoint.dto.PessoaDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface PessoaMapper {

//    PessoaMapper INSTANCE = Mappers.getMapper(PessoaMapper.class);

    @Mapping(source = "pessoaDto.id", target = "id")
    @Mapping(source = "pessoaDto.nome", target = "nome")
    @Mapping(source = "pessoaDto.dataNascimento", target = "dataNascimento")
    @Mapping(source = "pessoaDto.cpfCnpj", target = "cpfCnpj")
    @Mapping(source = "pessoaDto.cep", target = "cep")
    @Mapping(source = "pessoaDto.endereco", target = "endereco")
    @Mapping(source = "pessoaDto.cidade", target = "cidade")
    @Mapping(source = "pessoaDto.uf", target = "uf")
    @Mapping(source = "pessoaDto.dataCriacao", target = "dataCriacao")
    Pessoa toPessoaEntity(PessoaDto pessoaDto);

    @Mapping(source = "pessoa.id", target = "id")
    @Mapping(source = "pessoa.nome", target = "nome")
    @Mapping(source = "pessoa.dataNascimento", target = "dataNascimento")
    @Mapping(source = "pessoa.cpfCnpj", target = "cpfCnpj")
    @Mapping(source = "pessoa.cep", target = "cep")
    @Mapping(source = "pessoa.endereco", target = "endereco")
    @Mapping(source = "pessoa.cida" +
            "de", target = "cidade")
    @Mapping(source = "pessoa.uf", target = "uf")
    @Mapping(source = "pessoa.dataCriacao", target = "dataCriacao")
    PessoaDto toPessoaDto(Pessoa pessoa);

}
