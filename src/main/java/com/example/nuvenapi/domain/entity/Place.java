package com.example.nuvenapi.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.UUID;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Place {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, length = 50)
    private String name;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "adress_id")
    private Address address;

    @Column(nullable = false, length = 200)
    private String description;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime updatedAt;

    @PostPersist
    public void onCreate(){
        this.createdAt = LocalDateTime.now(ZoneId.of("America/Recife"));
        this.updatedAt = LocalDateTime.now(ZoneId.of("America/Recife"));
    }

    @PostUpdate
    public void onUpdate(){
        this.updatedAt = LocalDateTime.now(ZoneId.of("America/Recife"));
    }

}
