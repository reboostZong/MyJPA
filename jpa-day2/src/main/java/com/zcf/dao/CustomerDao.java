package com.zcf.dao;

import com.zcf.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CustomerDao extends JpaRepository<Customer, Long>, JpaSpecificationExecutor<Customer> {

    @Query(value = "from Customer where custName = ?")
    public Customer findJpql(String name);


    @Query(value = "from Customer where custName = ?2 and custId = ?1")
    public Customer findCustNameAndId(Long id, String name);


    @Query(value = "update Customer set custName = ?2 where custId = ?1")
    @Modifying
    public void updateCustName(Long id, String name);

    @Query(value = "select * from cst_customer", nativeQuery = true)
    public List<Object[]> findSql();

    @Query(value = "select * from cst_customer where cust_name like ?1", nativeQuery = true)
    public List<Object[]> findSqlLikeName(String name);

    public Customer findByCustName(String name);

    public List<Customer> findByCustNameLike(String name);

    public List<Customer> findByCustNameLikeAndCustIndustry(String name, String industry);
}
