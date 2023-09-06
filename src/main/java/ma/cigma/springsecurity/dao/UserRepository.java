package ma.cigma.springsecurity.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import ma.cigma.springsecurity.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {
	User findByUsername(String userName);
}
