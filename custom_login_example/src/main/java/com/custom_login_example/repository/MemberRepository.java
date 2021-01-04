package com.custom_login_example.repository;

import com.custom_login_example.entity.Member;
import com.custom_login_example.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Long> {

    List<Member> findAllByOrderByLastNameAsc();

    Member findByUser(User user);
}
