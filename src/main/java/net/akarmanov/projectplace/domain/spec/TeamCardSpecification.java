package net.akarmanov.projectplace.domain.spec;

import net.akarmanov.projectplace.domain.TeamCard;
import org.springframework.data.jpa.domain.Specification;

public class TeamCardSpecification {
    public static Specification<TeamCard> nameEquals(String name) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("name"), name);
    }

    public static Specification<TeamCard> statusEquals(String status) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("status"), status);
    }

    public static Specification<TeamCard> nameAndStatusEquals(String name, String status) {
        return nameEquals(name).and(statusEquals(status));
    }
}
