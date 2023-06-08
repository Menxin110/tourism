package com.springboot.tourism;

import com.springboot.tourism.enums.Sex;
import com.springboot.tourism.enums.Status;
import com.springboot.tourism.mapper.CustomerMapper;
import com.springboot.tourism.pojo.Customer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author oneda
 * @version 菜鸟
 */

@SpringBootTest
public class EnumsTest {

    @Autowired
    private CustomerMapper customerMapper;

    @Test
    public void enumTest(){

        Customer customer = new Customer();
        customer.setName("king");
        customer.setPhone("15569685204");
        customer.setSex(Sex.FEMALE);
        customer.setIdNumber("123456789123456789");
        customer.setStatus(Status.Normal);

        customerMapper.insert(customer);
    }

    @Test
    public void enum1Test(){

        Customer customer = customerMapper.selectById(1);
        System.out.println(customer);
    }
}
