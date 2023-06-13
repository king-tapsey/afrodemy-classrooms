package zw.co.afrocodemy.afrocodemyclassrooms.topic;

import jakarta.persistence.*;
import lombok.*;

import java.time.ZonedDateTime;
import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
public class TopicMaterial {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected long id;
    protected ZonedDateTime createdDate;
    protected ZonedDateTime modifiedDate;
    protected String adderUsername;
    protected String modifierUsername;
    protected String descriptorText;
    protected TopicMaterialType type;
    protected String materialUrl;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TopicMaterial that = (TopicMaterial) o;

        if (id != that.id) return false;
        if (!Objects.equals(createdDate, that.createdDate)) return false;
        if (!Objects.equals(modifiedDate, that.modifiedDate)) return false;
        if (!Objects.equals(adderUsername, that.adderUsername)) return false;
        if (!Objects.equals(modifierUsername, that.modifierUsername)) return false;
        if (!Objects.equals(descriptorText, that.descriptorText)) return false;
        if (type != that.type) return false;
        return Objects.equals(materialUrl, that.materialUrl);
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (createdDate != null ? createdDate.hashCode() : 0);
        result = 31 * result + (modifiedDate != null ? modifiedDate.hashCode() : 0);
        result = 31 * result + (adderUsername != null ? adderUsername.hashCode() : 0);
        result = 31 * result + (modifierUsername != null ? modifierUsername.hashCode() : 0);
        result = 31 * result + (descriptorText != null ? descriptorText.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (materialUrl != null ? materialUrl.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "TopicMaterial{" +
                "id=" + id +
                ", createdDate=" + createdDate +
                ", modifiedDate=" + modifiedDate +
                ", adderUsername='" + adderUsername + '\'' +
                ", modifierUsername='" + modifierUsername + '\'' +
                ", descriptorText='" + descriptorText + '\'' +
                ", type=" + type +
                ", materialUrl=" + materialUrl +
                '}';
    }
}
