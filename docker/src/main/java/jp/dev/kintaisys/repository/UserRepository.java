package jp.dev.kintaisys.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import jp.dev.kintaisys.domain.user.model.MUser;

public interface UserRepository extends JpaRepository<MUser, Long> {

	/** ログインユーザー検索  */
	@Query("select user from MUser user where loginId = :loginId")
	public MUser findLoginUser(@Param("loginId") String loginId);

	/** ユーザー更新 */
	@Modifying
	@Query("update MUser"+" set"+" password = :password" +" ,userName = :userName" +" where" +" loginId = :loginId")
	public Integer updateUser(@Param("loginId") String loginId,@Param("password") String password,@Param("userName") String userName);
	
}
