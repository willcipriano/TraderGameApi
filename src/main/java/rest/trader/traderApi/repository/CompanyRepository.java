package rest.trader.traderApi.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import rest.trader.traderApi.entity.Company.Company;

import java.util.UUID;

@Repository
public interface CompanyRepository extends CrudRepository<Company, UUID> {
}
