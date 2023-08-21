package com.nsm.myapp.contact;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactActivityRepository extends JpaRepository<ContactActivity, Long> {
}