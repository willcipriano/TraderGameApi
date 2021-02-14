package rest.trader.traderapi.specification;

import org.springframework.data.jpa.domain.Specification;
import rest.trader.traderapi.entity.commodity.Commodity;
import rest.trader.traderapi.entity.commodity.CommodityType;
import rest.trader.traderapi.request.CommoditySearchRequest;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

public class CommoditySpecification implements Specification<Commodity> {

    CommoditySearchRequest commoditySearchRequest;

    public CommoditySpecification(CommoditySearchRequest commoditySearchRequest) {
        super();
        this.commoditySearchRequest = commoditySearchRequest;
    }

    @Override
    public Predicate toPredicate(Root<Commodity> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();

        if (commoditySearchRequest.getName() != null) {
            predicates.add(criteriaBuilder.equal(criteriaBuilder.lower(root.<String> get("name")),
                    commoditySearchRequest.getName()));
        }

        if (commoditySearchRequest.getDescription() != null) {
            predicates.add(criteriaBuilder.equal(criteriaBuilder.lower(root.<String> get("name")),
                    commoditySearchRequest.getDescription()));
        }

        if (commoditySearchRequest.getTypeName() != null) {
            Join<Commodity, CommodityType> commodityTypeJoin = root.join("type");
            predicates.add(criteriaBuilder.equal(criteriaBuilder.lower(commodityTypeJoin.<String> get("name")),
                    commoditySearchRequest.getTypeName()));
        }

        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }
}
