package blog.kzerojunblog.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Generated;
import lombok.NoArgsConstructor;

@Generated
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "search_log")
@Table(name="search_log")
public class SearchLogEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int sequence;
	private String searchWord;
	private String relationWord;
	private boolean relation;

	public SearchLogEntity(String searchWord, String relationWord, boolean relation) {
		this.searchWord = searchWord;
		this.relationWord = relationWord;
		this.relation = relation;
	}

}
