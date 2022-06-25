package com.software.helpdeskfull.domain.enums;

public enum Status {
    /*Tipos Enumerados
    * Os tiipos enumerados servem para criarmos valores que vão fica predefinidos no sistema */

    ABERTO(0,"ABERTO"),ANDAMENTO(1,"ANDAMENTO"),ENCERRADO(2,"ENCERRADO");

    private  Integer codigo;
    private String descricao;

    //Construtor
    Status(Integer codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public Integer getCodigo() {
        return codigo;
    }



    public String getDescricao() {
        return descricao;
    }

    /*Metodo estatico
     Para não precisarmos criar uma instancia para chamar esse metodos em outras partes do código.
     Nesse metodo ele verifica se o valor informado para perfil relamente existe se existir ele
     retornar o valor correspondente , se não ele retorna uma exceção
     */

    public static Status toEnum(Integer cod){
        if(cod == null){
            return null;
        }
        for(Status x: Status.values()){
            if(cod.equals(x.getCodigo())){
                return x;
            }
        }

        throw new IllegalArgumentException("Status informado é invalido");
    }
}
