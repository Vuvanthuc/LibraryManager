package vn.cpa.api.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "author")
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_author")
    private Long authorId;

    @Column(name = "name_author")
    private String authorName;

    @Column(name = "address")
    private String address;

    @Column(name = "note")
    private String note;

    @Column(name = "code")
    private String code;

    @Column(name = "position")
    private String position;
}
