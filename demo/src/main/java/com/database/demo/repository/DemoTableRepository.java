package com.database.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.database.demo.entity.DemoTable;


@Repository
public interface DemoTableRepository extends JpaRepository<DemoTable,Integer>{

	
}
