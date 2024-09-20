package com.restaurant.restaurant_web.service;

import com.restaurant.restaurant_web.model.TableModel;

import java.util.List;

public interface TableService {
    public List<TableModel> findAllTable(int page, int size);
    public List<TableModel> listAllTable();
    public int countTables();
    public TableModel findTableById(Long id);
    public TableModel addTable(TableModel table);
    public TableModel updateTable(TableModel table);
    public void deleteTableLogically(Long id);
    public void deleteTablePhysically(Long id);
}
