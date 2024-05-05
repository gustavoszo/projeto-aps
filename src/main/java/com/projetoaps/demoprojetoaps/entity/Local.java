package com.projetoaps.demoprojetoaps.entity;

public enum Local {
    SAO_PAULO("São Paulo", "sao-paulo"),
    SANTO_ANDRE("Santo André", "santo-andre"),
    SAO_BERNARDO("São Bernardo", "sao-bernardo"),
    SAO_CAETANO("São Caetano", "sao-caetano"),
    MAUA("Mauá", "maua");

    private String descricao;
    private String pathString;

    Local(String descricao, String pathString) {
        this.descricao = descricao;
        this.pathString = pathString;
    }

    public String getDescricao() {
        return this.descricao;
    }

    public String getPathString() {
        return this.pathString;
    }
    
}
