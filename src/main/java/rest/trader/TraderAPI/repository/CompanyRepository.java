package rest.trader.TraderAPI.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import rest.trader.TraderAPI.entity.Company.Company;

import java.util.UUID;

@Repository
public interface CompanyRepository extends CrudRepository<Company, UUID> {
}
