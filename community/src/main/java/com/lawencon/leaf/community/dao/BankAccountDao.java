package com.lawencon.leaf.community.dao;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.lawencon.base.AbstractJpaDao;
import com.lawencon.base.ConnHandler;
import com.lawencon.leaf.community.model.BankAccount;
import com.lawencon.leaf.community.model.File;
import com.lawencon.leaf.community.model.User;

@Repository
public class BankAccountDao extends AbstractJpaDao {
	public Optional<BankAccount> getById(String id) {
		BankAccount bankAccount = null;

		try {
			final StringBuilder sql = new StringBuilder();
			sql.append("SELECT u.id, u.account_number, u.bank_name, u.file_id, u.user_id ");
			sql.append("FROM t_bank_account u ");
			sql.append("INNER JOIN t_user r ON r.id = u.user_id ");
			sql.append("INNER JOIN t_file p ON p.id = u.file_id ");
			sql.append("WHERE u.user_id = :id ");
			sql.append("AND u.is_active = TRUE");

			final Object result = ConnHandler.getManager().createNativeQuery(sql.toString())
					.setParameter("id", id).getSingleResult();

			if (result != null) {
				bankAccount = new BankAccount();
				final Object[] objArr = (Object[]) result;

				bankAccount.setId(objArr[0].toString());
				bankAccount.setAccountNumber(objArr[1].toString());
				bankAccount.setBankName(objArr[2].toString());

				final User user = new User();
				user.setId(objArr[4].toString());
				bankAccount.setUser(user);

				final File file = new File();
				file.setId(objArr[3].toString());
				bankAccount.setFile(file);


			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return Optional.ofNullable(bankAccount);
	}
}
