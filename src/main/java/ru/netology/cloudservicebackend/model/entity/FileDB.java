package ru.netology.cloudservicebackend.model.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class FileDB {
    @Id @GeneratedValue(strategy = IDENTITY)
    private Long id;
    private String filename;
    private String type;
    @Lob
    private byte[] data;
}
