package jp.dev.kintaisys.domain.user.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.dev.kintaisys.domain.user.model.MUser;
import jp.dev.kintaisys.domain.user.service.UserService;
import jp.dev.kintaisys.repository.UserRepository;

@Service
@Primary
public class UserServiceImpl2 implements UserService {

	@Autowired
	private UserRepository repository;

	@Autowired
	private PasswordEncoder encoder;

	/** ユーザー登録 */
	@Transactional
	@Override
	public void signup(MUser user) {
//存在チェック
		boolean exists = repository.existsById(user.getId());
		if (exists) {
			throw new DataAccessException("ユーザーが既に存在") {
			};
		}

		
		user.setRoleId("ROLE_GENERAL");

//パスワード暗号化
		String rawPassword = user.getPassword();
		user.setPassword(encoder.encode(rawPassword));

//insert
		repository.save(user);

	}

	/**
	 * ユーザ検索
	 */
	public List<MUser> findAll() {

		return repository.findAll();
	}

	/** ユーザー取得 */
	@Override
	public List<MUser> getUsers(MUser user) {
		/** 検索条件 */
		ExampleMatcher matcher = ExampleMatcher.matching()// and条件
				.withStringMatcher(StringMatcher.CONTAINING)// Like句
				.withIgnoreCase();// 大文字・小文字の両方

		return repository.findAll(Example.of(user, matcher));
	}

	/** ユーザー取得(1件) */
	@Override
	public MUser getUserOne(String loginId) {
		MUser user = new MUser();
		user.setLoginId(loginId);
		Optional<MUser> option = repository.findById(user.getId());
		user = option.orElse(null);
		return user;
	}

	/** ユーザー更新(1件) */
	@Transactional
	@Override
	public void updateUserOne(String userId, String password, String userName) {
		// パスワード暗号化
		String encryptPassword = encoder.encode(password);
		// ユーザー更新
		repository.updateUser(userId, encryptPassword, userName);
	}

	/** ユーザー削除(1件) */
	@Transactional
	@Override
	public void deleteUserOne(String loginId) {
		MUser user = new MUser();
		user.setLoginId(loginId);
		repository.deleteById(user.getId());
	}

	/** ログインユーザー取得 */
	@Override
	public MUser getLoginUser(String loginId) {
	 			return repository.findLoginUser(loginId);
		}
	}

