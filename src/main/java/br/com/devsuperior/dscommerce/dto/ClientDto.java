package br.com.devsuperior.dscommerce.dto;

import br.com.devsuperior.dscommerce.entitites.User;

public class ClientDto {
    private Long id;
    private String name;

    public ClientDto() {}

    public ClientDto(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public ClientDto(User client) {
        id = client.getId();
        name = client.getName();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
