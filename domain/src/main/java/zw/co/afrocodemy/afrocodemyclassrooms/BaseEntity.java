package zw.co.afrocodemy.afrocodemyclassrooms;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.*;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;

@MappedSuperclass
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public abstract class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;
    protected ZonedDateTime createdDate;
    protected String creatorUsername;

    public BaseEntity(ZonedDateTime createdDate, String creatorUsername){
        this.createdDate = createdDate;
        this.creatorUsername = creatorUsername;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BaseEntity that = (BaseEntity) o;

        if (!id.equals(that.id)) return false;
        if (!createdDate.equals(that.createdDate)) return false;
        return creatorUsername.equals(that.creatorUsername);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + createdDate.hashCode();
        result = 31 * result + creatorUsername.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "BaseEntity{" +
                "id=" + id +
                ", createdDate=" + createdDate +
                ", username='" + creatorUsername + '\'' +
                '}';
    }
}
