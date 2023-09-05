package ma.cigma.springsecurity.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import ma.cigma.springsecurity.service.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
	User findByUsername(String userName);
}
