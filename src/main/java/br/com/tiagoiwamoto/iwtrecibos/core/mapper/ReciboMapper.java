package br.com.tiagoiwamoto.iwtrecibos.core.mapper;

/*
 * Tiago Henrique Iwamoto
 * tiago.iwamoto@gmail.com
 * linkedin.com/in/tiago-iwamoto
 * System specialist
 * 01/02/2022 | 20:24
 */

import br.com.tiagoiwamoto.iwtrecibos.core.entity.Recibo;
import br.com.tiagoiwamoto.iwtrecibos.core.entity.Usuario;
import br.com.tiagoiwamoto.iwtrecibos.entrypoint.dto.ReciboDto;
import br.com.tiagoiwamoto.iwtrecibos.entrypoint.dto.UsuarioDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ReciboMapper {

//    ReciboMapper INSTANCE = Mappers.getMapper(ReciboMapper.class);

    @Mapping(source = "reciboDto.id", target = "id")
    @Mapping(source = "reciboDto.tipo", target = "tipo")
    @Mapping(source = "reciboDto.valor", target = "valor")
    @Mapping(source = "reciboDto.valorPorExtenso", target = "valorPorExtenso")
    @Mapping(source = "reciboDto.pagador", target = "pagador")
    @Mapping(source = "reciboDto.localRecebido", target = "localRecebido")
    @Mapping(source = "reciboDto.referente", target = "referente")
    @Mapping(source = "reciboDto.observacao", target = "observacao")
    @Mapping(source = "reciboDto.dataDeEmissao", target = "dataDeEmissao")
    @Mapping(source = "reciboDto.dataDeEmissaoPorExtenso", target = "dataDeEmissaoPorExtenso")
    @Mapping(source = "reciboDto.assinante", target = "assinante")
    @Mapping(source = "reciboDto.dataCriacao", target = "dataCriacao")
    Recibo toReciboEntity(ReciboDto reciboDto);

    @Mapping(source = "recibo.id", target = "id")
    @Mapping(source = "recibo.tipo", target = "tipo")
    @Mapping(source = "recibo.valor", target = "valor")
    @Mapping(source = "recibo.valorPorExtenso", target = "valorPorExtenso")
    @Mapping(source = "recibo.pagador", target = "pagador")
    @Mapping(source = "recibo.localRecebido", target = "localRecebido")
    @Mapping(source = "recibo.referente", target = "referente")
    @Mapping(source = "recibo.observacao", target = "observacao")
    @Mapping(source = "recibo.dataDeEmissao", target = "dataDeEmissao")
    @Mapping(source = "recibo.dataDeEmissaoPorExtenso", target = "dataDeEmissaoPorExtenso")
    @Mapping(source = "recibo.assinante", target = "assinante")
    @Mapping(source = "recibo.dataCriacao", target = "dataCriacao")
    ReciboDto toReciboDto(Recibo recibo);

}
