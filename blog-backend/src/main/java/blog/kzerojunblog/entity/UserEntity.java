package blog.kzerojunblog.entity;

import blog.kzerojunblog.dto.request.auth.SignUpRequestDto;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "user")
@Table(name = "user")
public class UserEntity {

	@Id
	private String email;
	private String password;
	private String nickname;
	private String telNumber;
	private String address;
	private String addressDetail;
	private String profileImage;
	private Boolean agreedPersonal;
	public UserEntity(SignUpRequestDto dto) {
		this.email = dto.getEmail();
		this.password = dto.getPassword();
		this.nickname = dto.getNickname();
		this.telNumber = dto.getTelNumber();
		this.address = dto.getAddress();
		this.addressDetail = dto.getAddressDetail();
		this.profileImage = null;
		this.agreedPersonal = dto.getAgreedPersonal();
	}

	public void updateNickname(String nickname) {
		this.nickname = nickname;
	}

	public void updateProfileImage(String profileImage) {
		this.profileImage = profileImage;
	}
}
