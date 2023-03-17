package com.lawencon.leaf.community.dao;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.lawencon.base.AbstractJpaDao;
import com.lawencon.base.ConnHandler;
import com.lawencon.leaf.community.model.File;
import com.lawencon.leaf.community.model.Profile;
import com.lawencon.leaf.community.model.Role;
import com.lawencon.leaf.community.model.User;

@Repository
public class UserDao extends AbstractJpaDao {

	public Optional<User> getEmail(final String email) {
		User user = null;

		try {
			final StringBuilder sql = new StringBuilder();
			sql.append("SELECT u.id, u.profile_id, p.full_name, p.file_id, r.role_code, u.pass ");
			sql.append("FROM t_user u ");
			sql.append("INNER JOIN t_role r ON r.id = u.role_id ");
			sql.append("INNER JOIN t_profile p ON p.id = u.profile_id ");
			sql.append("WHERE u.email = :email ");
			sql.append("AND u.is_active = TRUE");

			final Object result = ConnHandler.getManager().createNativeQuery(sql.toString())
					.setParameter("email", email).getSingleResult();

			if (result != null) {
				user = new User();
				final Object[] objArr = (Object[]) result;

				user.setId(objArr[0].toString());
				user.setPass(objArr[5].toString());

				final Role role = new Role();
				role.setRoleCode(objArr[4].toString());
				user.setRole(role);

				final Profile profile = new Profile();
				profile.setId(objArr[1].toString());
				profile.setFullName(objArr[2].toString());

				final File file = new File();
				file.setId(objArr[3].toString());

				profile.setFile(file);

				user.setProfile(profile);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return Optional.ofNullable(user);
	}
	
	public Optional<User> getById(final String id){
		return Optional.ofNullable(super.getById(User.class, id));
		
	}
	
	public Optional<User> getUserByRole(final String code) {
		User user = null;

		try {
			final StringBuilder sql = new StringBuilder();
			sql.append("SELECT u.id, u.profile_id, p.full_name, p.file_id, r.role_code, u.pass ");
			sql.append("FROM t_user u ");
			sql.append("INNER JOIN t_role r ON r.id = u.role_id ");
			sql.append("INNER JOIN t_profile p ON p.id = u.profile_id ");
			sql.append("WHERE r.role_code = :code ");

			final Object result = ConnHandler.getManager().createNativeQuery(sql.toString())
					.setParameter("code", code).getSingleResult();

			if (result != null) {
				user = new User();
				final Object[] objArr = (Object[]) result;

				user.setId(objArr[0].toString());
				user.setPass(objArr[5].toString());

				final Role role = new Role();
				role.setRoleCode(objArr[4].toString());
				user.setRole(role);

				final Profile profile = new Profile();
				profile.setId(objArr[1].toString());
				profile.setFullName(objArr[2].toString());

				final File file = new File();
				file.setId(objArr[3].toString());

				profile.setFile(file);

				user.setProfile(profile);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return Optional.ofNullable(user);
	}
}
