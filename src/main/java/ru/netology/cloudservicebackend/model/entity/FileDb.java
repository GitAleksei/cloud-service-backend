package ru.netology.cloudservicebackend.model.entity;

import lombok.*;

import javax.persistence.*;

import java.util.Arrays;
import java.util.Objects;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class FileDb {
    @Id @GeneratedValue(strategy = IDENTITY)
    private Long id;
    private String filename;
    private String type;
    @Lob
    @Column(columnDefinition = "LONGBLOB")
    private byte[] data;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FileDb fileDb = (FileDb) o;
        return Objects.equals(id, fileDb.id) && Objects.equals(filename, fileDb.filename) && Objects.equals(type, fileDb.type) && Arrays.equals(data, fileDb.data);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(id, filename, type);
        result = 31 * result + Arrays.hashCode(data);
        return result;
    }
}
