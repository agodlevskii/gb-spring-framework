package ru.gb.gbspringframework.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "authorities")
public class Role {
    @Id
    @Column(name = "username")
    private String username;

    @Column(name = "authority")
    private String name;

    @OneToOne()
    @JoinColumn(name = "username", nullable = false, insertable = false, updatable = false)
    private User user;
}
