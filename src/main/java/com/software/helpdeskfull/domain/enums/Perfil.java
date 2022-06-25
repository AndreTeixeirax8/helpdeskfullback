package com.software.helpdeskfull.domain.enums;

public enum Perfil {
    /*Tipos Enumerados
    * Os tiipos enumerados servem para criarmos valores que vão fica predefinidos no sistema */

    ADMIN(0,"ROLE_ADMIN"),CLIENTE(1,"ROLE_CLIENTE"),TECNICO(2,"ROLE_CLIENTE");

    private  Integer codigo;
    private String descricao;

    //Construtor
    Perfil(Integer codigo, String descricao) {
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

    public static Perfil toEnum(Integer cod){
        if(cod == null){
            return null;
        }
        for(Perfil x: Perfil.values()){
            if(cod.equals(x.getCodigo())){
                return x;
            }
        }

        throw new IllegalArgumentException("Perfil informado é invalido");
    }
}
