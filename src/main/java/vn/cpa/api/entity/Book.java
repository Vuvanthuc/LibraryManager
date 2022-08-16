package vn.cpa.api.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "book")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id")
    private Long bookId;

    @Column(name = "book_name")
    private String bookName;

    @Column(name = "id_author")
    private Long authorId;

    @Column(name = "publishing_year")
    private Integer publishingYear;

    @Column(name = "page_number")
    private Integer pageNumber;

    @Column(name = "price")
    private Double price;

    @Column(name = "image")
    private String image;

    @Column(name = "id_type_book")
    private Long typeBookId;

    @Column(name = "id_company")
    private Long companyId;

    @Column(name = "amount")
    private Integer amount;

    @Column(name = "status")
    private Integer status;
}
