package com.example.demo.repository;



import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.model.Expenses;

public interface ExpensesDataRepository extends JpaRepository<Expenses, Integer> {
	@Query(value= "select e from Expenses e")
	List<Expenses> FindAllExpenses();
	
}
