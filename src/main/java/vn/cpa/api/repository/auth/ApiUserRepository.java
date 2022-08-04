package vn.cpa.api.repository.auth;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import vn.cpa.api.entity.Account;
import vn.cpa.api.entity.SysUser;

import java.util.Optional;

@Repository
public interface ApiUserRepository extends PagingAndSortingRepository<Account, Long>, JpaSpecificationExecutor<Account> {
	Optional<Account> findByUsername(String username);
}
