package com.restaurant.restaurant_web.service;

import com.restaurant.restaurant_web.model.TableModel;
import com.restaurant.restaurant_web.repository.TableRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InMemoryTableServiceImpl implements TableService{
    private final TableRepository tableRepository;

    public InMemoryTableServiceImpl(TableRepository tableRepository) {
        this.tableRepository = tableRepository;
    }

    @Override
    public int countTables(){
        return (int)tableRepository.count();
    }

    @Override
    public List<TableModel> findAllTable(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("id"));
        return tableRepository.findAll(pageable).getContent();
    }

    @Override
    public List<TableModel> listAllTable() {
        return tableRepository.findAll();
    }
    @Override
    public TableModel findTableById(Long id) {
        return tableRepository.findById(id).orElse(null);
    }

    @Override
    public TableModel addTable(TableModel table){
        return tableRepository.save(table);
    }

    @Override
    public TableModel updateTable(TableModel table){
        if (tableRepository.existsById(table.getId())){
            return tableRepository.save(table);
        }
        return null;
    }

    @Override
    public void deleteTableLogically(Long id) {
        TableModel table = tableRepository.findById(id).orElse(null);
        if (table != null) {
            table.setDeleted(true);
            tableRepository.save(table);
        }
    }

    @Override
    public void deleteTablePhysically(Long id) {
        tableRepository.deleteById(id);
    }
}
