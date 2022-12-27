package jp.dev.kintaisys.domain.user.service.impl;

import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl{

//    @Autowired
//    private UserMapper mapper;
//
//    @Autowired
//    private PasswordEncoder encoder;
//
//    /** ユーザー登録 */
//    @Override
//    public void signup(MUser user) {
//        user.setDepartmentId(1); // 部署
//        user.setRoleId("ROLE_GENERAL"); // ロール
//
//        // パスワード暗号化
//        String rawPassword = user.getPassword();
//        user.setPassword(encoder.encode(rawPassword));
//
//        mapper.insertOne(user);
//    }
//    
//    /**
//	 * ユーザ検索
//	 */
//	public List<MUser> findAll() {
//
//		return null;
//	}
//
//    /** ユーザー取得 */
//    @Override
//    public List<MUser> getUsers(MUser user) {
//        return mapper.findMany(user);
//    }
//
//    /** ユーザー取得(1件) */
//    @Override
//    public MUser getUserOne(String loginId) {
//        return mapper.findOne(loginId);
//    }
//
//    /** ユーザー更新(1件) */
//    @Transactional
//    @Override
//    public void updateUserOne(String loginId,
//            String password,
//            String userName) {
//
//        // パスワード暗号化
//        String encryptPassword = encoder.encode(password);
//
//        mapper.updateOne(loginId, encryptPassword, userName);
//
//        // 例外を発生させる
//        // int i = 1 / 0;
//    }
//
//    /** ユーザー削除(1件) */
//	@Override
//	public void deleteUserOne(String loginId) {
//		int count = mapper.deleteOne(loginId);
//	}
//       
//    
//
//    /** ログインユーザー情報取得 */
//    @Override
//    public MUser getLoginUser(String loginId) {
//        return mapper.findLoginUser(loginId);
//    }
}
