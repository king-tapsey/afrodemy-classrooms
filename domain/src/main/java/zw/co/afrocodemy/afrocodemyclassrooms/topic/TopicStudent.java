package zw.co.afrocodemy.afrocodemyclassrooms.topic;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class TopicStudent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;
    @ManyToOne
    protected Topic topic;
    protected String takerUsername;
    protected TopicStudentProgress progress;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TopicStudent that = (TopicStudent) o;

        if (!id.equals(that.id)) return false;
        if (!topic.equals(that.topic)) return false;
        if (!takerUsername.equals(that.takerUsername)) return false;
        return progress == that.progress;
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + topic.hashCode();
        result = 31 * result + takerUsername.hashCode();
        result = 31 * result + progress.hashCode();
        return result;
    }
}
