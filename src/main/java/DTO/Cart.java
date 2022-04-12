/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import java.util.ArrayList;

/**
 *
 * @author tranh
 */
public class Cart {

    ArrayList<Item> cart;

    public Cart() {
        this.cart = new ArrayList<Item>();
    }

    public boolean add(Item item) {
        try {
            if (cart.contains(item)) {
                Item currentItem = cart.get(cart.indexOf(item));
                currentItem.setSoLuong(currentItem.getSoLuong() + 1);
            } else {
                cart.add(item);
            }
            return true;

        } catch (Exception e) {
            return false;
        }
    }

    public boolean remove(Item item) {
        try {
            if (cart.contains(item)) {
                int vt = cart.indexOf(item);
                cart.remove(vt);
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public float getTongTien() {
        float tongTien = 0;
        for (Item item : cart) {
            tongTien += item.getSoLuong() * item.getPrice();
        }
        return tongTien;
    }

//    public public ArrayList<>

}
