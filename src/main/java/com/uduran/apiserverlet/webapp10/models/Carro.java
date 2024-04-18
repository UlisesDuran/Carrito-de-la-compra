package com.uduran.apiserverlet.webapp10.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

public class Carro {
    private Logger logger = Logger.getLogger(Carro.class.getName());
    List<ItemCarro> items;

    public Carro() {
        this.items = new ArrayList<>();
    }

    public void addItemCarro(ItemCarro itemCarro){
        if(items.contains(itemCarro)){
            Optional<ItemCarro> optionalItemCarro = items.stream()
                    .filter(i -> i.equals(itemCarro))
                    .findFirst();
            if (optionalItemCarro.isPresent()){
                ItemCarro i = optionalItemCarro.get();
                i.setCantidad(i.getCantidad()+1);
            }
        }else{
            this.items.add(itemCarro);
        }
    }

    public List<ItemCarro> getItems(){
        return items;
    }

    public Double getTotal(){
        return (double) Math.round((((items.stream().mapToDouble(ItemCarro::getImporte).sum()) * 100.0) / 100.0));
    }

    public void removeItems(List<String> itemsIds){
        if (itemsIds != null){
            itemsIds.forEach(this::removeItem);
        }
    }

    public void removeItem(String itemNombre){
        Optional<ItemCarro> item = findItem(itemNombre);
        logger.info(String.valueOf(item.isPresent()));
        logger.info(String.valueOf(items.size()));
        item.ifPresent(itemCarro -> items.remove(itemCarro));
    }

    public void updateCantidad(String  itemNombre, int cantidad){
        Optional<ItemCarro> item = findItem(itemNombre);
        item.ifPresent(itemCarro -> itemCarro.setCantidad(cantidad));
    }

    private Optional<ItemCarro> findItem(String itemNombre){
        return items.stream()
                .filter(item -> {
                    if (item.getCurso() != null){
                        return item.getCurso().getNombre().equals(itemNombre);
                    } else if (item.getProducto() != null){
                        return item.getProducto().getNombre().equals(itemNombre);
                    }
                    return false;
                })
                .findFirst();
    }
}
