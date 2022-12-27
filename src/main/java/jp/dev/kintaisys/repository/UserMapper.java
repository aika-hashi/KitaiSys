package jp.dev.kintaisys.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import jp.dev.kintaisys.domain.user.model.MUser;

@Mapper
public interface UserMapper {

    /** ユーザー登録 */
    public int insertOne(MUser user);

    /** ユーザー取得 */
    public List<MUser> findMany(MUser user);

    /** ユーザー取得(1件) */
    public MUser findOne(String loginId);

    /** ユーザー更新(1件) */
    public void updateOne(@Param("loginId") String loginId,
            @Param("password") String password,
            @Param("userName") String userName);

    /** ユーザー削除(1件) */
    public int deleteOne(@Param("loginId") String loginId);

    /** ログインユーザー取得 */
    public MUser findLoginUser(String loginId);
    
    //メモ
//    /** ユーザー登録 */
//	public int insertOne(MUser user);
//
//	/** ユーザー取得 */
//	public List<MUser> findMany(MUser user);
//
//	/** ユーザー取得(1件) */
//	public MUser findOne(String userId);
//
//	/** ユーザー更新(1件) */
//	public void updateOne(@Param("userId") String userId, @Param("password") String password,
//			@Param("userName") String userName);
//
//	/** ユーザー削除(1件) */
//	public int deleteOne(@Param("userId") String userId);
//
//	/** ログインユーザー取得 */
//	public MUser findLoginUser(String userId);

}
