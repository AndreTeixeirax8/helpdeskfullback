package com.software.helpdeskfull.domain.enums;

public enum Prioridade {
    /*Tipos Enumerados
    * Os tiipos enumerados servem para criarmos valores que vão fica predefinidos no sistema */

    BAIXA(0,"BAIXA"),MEDIA(1,"MEDIA"),ALTO(2,"ALTO");

    private  Integer codigo;
    private String descricao;

    //Construtor
    Prioridade(Integer codigo, String descricao) {
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

    public static Prioridade toEnum(Integer cod){
        if(cod == null){
            return null;
        }
        for(Prioridade x: Prioridade.values()){
            if(cod.equals(x.getCodigo())){
                return x;
            }
        }

        throw new IllegalArgumentException("Prioridade informada é invalido");
    }
}
