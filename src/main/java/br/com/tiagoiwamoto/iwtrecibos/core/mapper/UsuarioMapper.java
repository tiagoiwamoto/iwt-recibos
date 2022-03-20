package br.com.tiagoiwamoto.iwtrecibos.core.mapper;

/*
 * Tiago Henrique Iwamoto
 * tiago.iwamoto@gmail.com
 * linkedin.com/in/tiago-iwamoto
 * System specialist
 * 01/02/2022 | 20:24
 */

import br.com.tiagoiwamoto.iwtrecibos.core.entity.Usuario;
import br.com.tiagoiwamoto.iwtrecibos.entrypoint.dto.UsuarioDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {

//    UsuarioMapper INSTANCE = Mappers.getMapper(UsuarioMapper.class);

    @Mapping(source = "usuarioDto.id", target = "id")
    @Mapping(source = "usuarioDto.uuid", target = "uuid")
    @Mapping(source = "usuarioDto.username", target = "username")
    @Mapping(source = "usuarioDto.password", target = "password")
    @Mapping(source = "usuarioDto.dataCriacao", target = "dataCriacao")
    Usuario toUsuarioEntity(UsuarioDto usuarioDto);

    @Mapping(source = "usuario.id", target = "id")
    @Mapping(source = "usuario.uuid", target = "uuid")
    @Mapping(source = "usuario.username", target = "username")
    @Mapping(source = "usuario.password", target = "password")
    @Mapping(source = "usuario.dataCriacao", target = "dataCriacao")
    UsuarioDto toUsuarioDto(Usuario usuario);

}
