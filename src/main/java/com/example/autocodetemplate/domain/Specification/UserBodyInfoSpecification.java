package com.example.autocodetemplate.domain.Specification;

import com.example.autocodetemplate.domain.UserBodyInfo;
import com.example.autocodetemplate.domain.UserBodyInfoMixDO;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.util.Date;


public class UserBodyInfoSpecification implements Specification<UserBodyInfo> {

    @Override
    public Predicate toPredicate(Root<UserBodyInfo> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
        //1.混合条件查询
        Path<String> exp1 = root.get("name");
        Path<Date>  exp2 = root.get("birthday");
        Path<String> exp3 = root.get("sex");
        Predicate predicate = criteriaBuilder.and(criteriaBuilder.like(exp1, "%name%"),criteriaBuilder.equal(exp3, 1));
        criteriaBuilder.or(predicate, criteriaBuilder.lessThan(exp2, new Date()));

        //2.多表查询
        Join<UserBodyInfo, UserBodyInfoMixDO> join = root.join("userBodyInfoMixDO", JoinType.INNER);
        Path<String> exp4 = join.get("name");

        return criteriaBuilder.like(exp4, "%name%");
    }
}
