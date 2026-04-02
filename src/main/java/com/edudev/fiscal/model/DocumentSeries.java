package com.edudev.fiscal.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "document_series")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DocumentSeries {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false,unique = true)
    private DocumentType documentType;

    @Column(nullable = false, length = 10, unique = true)
    private String series;

    @Column(nullable = false)
    private Long currentNumber;

    @Column(nullable = false)
    private boolean active;

}
