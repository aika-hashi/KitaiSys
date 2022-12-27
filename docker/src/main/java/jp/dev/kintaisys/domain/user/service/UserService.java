package jp.dev.kintaisys.domain.user.service;

import java.util.List;

import jp.dev.kintaisys.domain.user.model.MUser;

public interface UserService {

	/** ユーザー登録 */
	public void signup(MUser user);
	
	
	/**　
	 * ユーザ検索 
	 */
	public List<MUser> findAll();

	/** ユーザー取得 */
	public List<MUser> getUsers(MUser user);

	/** ユーザー取得(1件) */
	// public MUser getUserOne(MUserKey userKey);
	//引数難しい
	public MUser getUserOne(String loginId);

	/** ユーザー更新(1件) */
	public void updateUserOne(String loginId, String password, String userName);

	/** ユーザー削除(1件) */
	public void deleteUserOne(String loginId);
	/** ログインユーザー情報取得 */
	public MUser getLoginUser(String loginId);

	
	//メモ
//	/** ユーザー登録 */
//	public void signup(MUser user);
//
//	/** ユーザー取得 */
//	public List<MUser> getUsers(MUser user);
//
//	/** ユーザー取得(1件) */
//	public MUser getUserOne(String userId);
//
//	/** ユーザー更新(1件) */
//	public void updateUserOne(String userId, String password, String userName);
//
//	/** ユーザー削除(1件) */
//	public void deleteUserOne(String userId);
//
//	/** ログインユーザー情報取得 */
//	public MUser getLoginUser(String userId);
	
}
